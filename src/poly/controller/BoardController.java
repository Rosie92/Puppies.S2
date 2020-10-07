package poly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.BoardDTO;
import poly.dto.CommentDTO;
import poly.dto.PagingDTO;
import poly.service.IBoardService;

@Controller
public class BoardController {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Resource(name = "BoardService")
	private IBoardService BoardService;

	// ---------------------------------게시판-------------------------------------

	// --------------------------리스트-------------------------
	@RequestMapping(value = "/DExellent/board/BoardList")
	public String BoardList(HttpServletRequest request, Model model) throws Exception {
		log.info(this.getClass().getName() + "########자유게시판 실행########");

		int page = Integer.parseInt(request.getParameter("Pno"));
		int listCnt = BoardService.TotalCount(); // 총 게시글 개수

		log.info("int page : " + page);
		log.info("int listCnt : " + listCnt);

		PagingDTO paging = new PagingDTO();

		paging.pageInfo(page, listCnt);
		HashMap<String, Integer> hMap = new HashMap<>();
		int i = paging.getStartList();
		int j = paging.getListSize();
		hMap.put("startlist", i);
		hMap.put("listsize", j);

		List<BoardDTO> bList = new ArrayList<>();

		log.info("bList : " + bList);

		try {
			bList = BoardService.getBoardList(hMap);
			log.info(bList.get(i).getBoard_seq());

		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("bList", bList);
		model.addAttribute("paging", paging);

		bList = null;
		paging = null;

		return "/DExellent/board/BoardList";
	}

	// ----------------------글작성 연결-----------------------
	@RequestMapping(value = "/DExellent/board/BoardWrite")
	public String BoardWrite(HttpSession session, HttpServletRequest request, ModelMap model) throws Exception {
		log.info("######## 자유게시판 글작성 페이지 시작 ##############");
		return "/DExellent/board/BoardWrite";
	}

	// ------------------------글작성 실행-------------------------
	@RequestMapping(value = "/DExellent/board/BoardWriteProc")
	public String BoardWriteProc(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "########자유게시판 작성Proc 실행########");

		try {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String user_name = (String) session.getAttribute("user_name");

			log.info(title);
			log.info(content);
			log.info(user_name);

			// 여기부터
			BoardDTO bDTO = new BoardDTO();
			bDTO.setTitle(title);
			bDTO.setContent(content);
			bDTO.setUser_name(user_name);

			log.info("========================");
			log.info("title : " + title);
			log.info("content : " + content);
			log.info("user_name : " + user_name);
			log.info("========================");

			log.info("bDTO에 들어간 name값 : " + bDTO.getUser_name());

			int res = 0;

			res = BoardService.InsertBoardWriteProc(bDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/DExellent/board/BoardList.do?Pno=1");
				model.addAttribute("msg", "등록되었습니다.");
			} else {
				model.addAttribute("url", "/DExellent/board/BoardList.do?Pno=1");
				model.addAttribute("msg", "등록에 실패했습니다.");
			}

			bDTO = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/Redirect";
	}

	// ------------------------디테일 실행----------------------

	@RequestMapping(value = "/DExellent/board/BoardDetail")
	public String BoardDetail(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "########자유게시판 디테일 실행########");

		String seq = request.getParameter("seq");

		BoardDTO bDTO = new BoardDTO();

		List<CommentDTO> cList = BoardService.readReply(seq);

		if (cList == null) {
			cList = new ArrayList<CommentDTO>();
		}

		try {
			bDTO = BoardService.getBoardDetail(seq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (bDTO == null) {
			bDTO = new BoardDTO();
		} else {
			BoardFilter(bDTO);
		}

		model.addAttribute("cList", cList);
		model.addAttribute("bDTO", bDTO);
		model.addAttribute("seq", seq);

		cList = null;
		bDTO = null;
		seq = null;

		return "/DExellent/board/BoardDetail";

	}

	// --------------------------글 수정/삭제 이동 전 권한 확인 및 이동-------------------
	@RequestMapping(value = "/DExellent/board/BoardReWrite")
	public String BoardReWrite(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "자유게시판 글 수정/삭제 이동 전 권한 확인 및 이동");

		String board_seq = request.getParameter("seq");
		String User = (String) session.getAttribute("user_name");
		/*
		 * List<BoardDTO> bList = new ArrayList<>(); bList =
		 * BoardService.UserCheck(board_seq);
		 */
		BoardDTO cDTO = new BoardDTO(); 
		cDTO = BoardService.UserCheck(board_seq);
		
		String UserCheck = cDTO.getUser_name();
		
		BoardDTO pDTO = new BoardDTO();
		if (User.equals("조정규") || UserCheck.equals(User)) {
			try {
				pDTO = new BoardDTO();
				pDTO.setBoard_seq(board_seq);
				pDTO = BoardService.BoardReWrite(pDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("pDTO", pDTO);
			model.addAttribute("seq", board_seq);

			pDTO = null;
			board_seq = null;
			User = null;
			UserCheck = null;
			cDTO = null;

			return "/DExellent/board/BoardReWrite";

		} else {
			model.addAttribute("url", "/DExellent/board/BoardList.do?Pno=1");
			model.addAttribute("msg", "권한이 없습니다. 게시판 리스트로 이동합니다.");

			pDTO = null;
			board_seq = null;
			User = null;
			UserCheck = null;
			cDTO = null;
		}
		return "/Redirect";
	}

	// ------------------------글 수정 실행------------------------------
	@RequestMapping(value = "/DExellent/board/BoardReWriteTry")
	public String BoardReWriteTry(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		log.info(this.getClass().getName() + "########  글 수정하기 실행########");

		String seq = request.getParameter("seq");

		BoardDTO pDTO = new BoardDTO();
		pDTO.setBoard_seq(seq);

		try {
			String title = (String) request.getParameter("title");
			String content = (String) request.getParameter("content");

			log.info("seq : " + seq);
			log.info("title : " + title);
			log.info("content : " + content);

			pDTO.setTitle(title);
			pDTO.setContent(content);

			log.info("========================");
			log.info("Board_seq : " + seq);
			log.info("title : " + title);
			log.info("content : " + content);
			log.info("========================");

			int res = BoardService.BoardReWriteTry(pDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/DExellent/board/BoardList.do?Pno=1");
				model.addAttribute("msg", "수정되었습니다.");
			} else {
				model.addAttribute("url", "/DExellent/board/BoardList.do?Pno=1");
				model.addAttribute("msg", "수정에 실패했습니다.");
			}

			pDTO = null;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/Redirect";

	}
	// ---------------------------글 삭제 실행-------------------------

	@RequestMapping(value = "/DExellent/board/BoardDelete")
	public String BoardDelete(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		log.info("######## 글 삭제하기 실행########");
		try {
			String seq = request.getParameter("seq");
			BoardDTO pDTO = new BoardDTO();
			pDTO.setBoard_seq(seq);
			log.info("seq : " + seq);

			CommentDTO cDTO = new CommentDTO();
			cDTO.setBoard_seq(seq);

			int res = BoardService.BoardDelete(pDTO);
			int res2 = BoardService.BoardDeleteWithCommentDelete(cDTO);
			log.info("res : " + res);
			log.info("res2 : " + res2);

			if (res > 0) {
				model.addAttribute("url", "/DExellent/board/BoardList.do?Pno=1");
				model.addAttribute("msg", "삭제되었습니다.");
			} else {
				model.addAttribute("url", "/DExellent/board/BoardList.do?Pno=1");
				model.addAttribute("msg", "삭제에 실패했습니다.");
			}

			cDTO = null;
			pDTO = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/Redirect";

	}

	// -------------------------------------댓글---------------------------------------
	// -------------------댓글작성------------------
	@RequestMapping(value = "/DExellent/board/CommentProc")
	public String CommentProc(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "######## 댓글 작성Proc 실행########");

		try {

			String content = request.getParameter("content");
			String writer = (String) session.getAttribute("user_name");
			String board_seq = request.getParameter("seq");
			log.info("content : " + content);
			log.info("writer : " + writer);
			log.info("board_seq : " + board_seq);

			// 여기부터
			CommentDTO bDTO = new CommentDTO();
			bDTO.setBoard_seq(board_seq);
			bDTO.setContent(content);
			bDTO.setWriter(writer);

			log.info("========================");
			log.info("board_seq : " + board_seq);
			log.info("content : " + content);
			log.info("writer : " + writer);
			log.info("========================");

			int res = 0;

			res = BoardService.InsertComment(bDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/DExellent/board/BoardList.do?Pno=1");
				model.addAttribute("msg", "등록되었습니다.");
			} else {
				model.addAttribute("url", "/DExellent/board/BoardList.do?Pno=1");
				model.addAttribute("msg", "등록에 실패했습니다.");
			}
			bDTO = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/Redirect";
	}

	// --------------------------댓글 수정 이동 전 권한 확인 및 댓글 삭제 실행-------------------
	@RequestMapping(value = "/DExellent/board/CommentUpdate")
	public String CommentUpdate(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "댓글 수정 이동 전 권한 확인 및 댓글 삭제 실행");

		String rno = request.getParameter("rno");
		String User = (String) session.getAttribute("user_name");

		/*
		 * List<CommentDTO> bList = new ArrayList<>(); bList =
		 * BoardService.UserCheck2(rno);
		 */
		CommentDTO cDTO = new CommentDTO();
		cDTO = BoardService.UserCheck2(rno);
		String UserCheck2 = cDTO.getWriter();

		CommentDTO pDTO = new CommentDTO();
		if (User.equals("조정규") || UserCheck2.equals(User)) {
			try {
				pDTO = new CommentDTO();
				pDTO.setRno(rno);
				pDTO = BoardService.CommentUpdate(pDTO);

				model.addAttribute("pDTO", pDTO);
				model.addAttribute("rno", rno);

			} catch (Exception e) {
				e.printStackTrace();
			}
			pDTO = null;
			cDTO = null;
			
			return "/DExellent/board/CommentUpdate";
		} else {
			model.addAttribute("url", "/DExellent/board/BoardList.do?Pno=1");
			model.addAttribute("msg", "권한이 없습니다. 게시판 리스트로 이동합니다.");

			pDTO = null;
			cDTO = null;
		}
		return "/Redirect";
	}

	// --------------------------댓글 삭제 이동 전 권한 확인 및 댓글 삭제 실행-------------------
	@RequestMapping(value = "/DExellent/board/CommentDelete")
	public String CommentDelete(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "댓글 삭제 이동 전 권한 확인 및 댓글 삭제 실행");

		String rno = request.getParameter("rno");
		String User = (String) session.getAttribute("user_name");

		/*
		 * List<CommentDTO> bList = new ArrayList<>(); bList =
		 * BoardService.UserCheck2(rno); String UserCheck2 = bList.get(0).toString();
		 */
		
		CommentDTO cDTO = new CommentDTO();
		cDTO = BoardService.UserCheck2(rno);
		String UserCheck2 = cDTO.getWriter();

		CommentDTO pDTO = new CommentDTO();
		if (User.equals("조정규") || UserCheck2.equals(User)) {
			try {

				pDTO.setRno(rno);
				log.info("rno : " + rno);

				int res = BoardService.CommentDelete(pDTO);
				log.info("res : " + res);

				if (res > 0) {
					model.addAttribute("url", "/DExellent/board/BoardList.do?Pno=1");
					model.addAttribute("msg", "삭제되었습니다.");
				} else {
					model.addAttribute("url", "/DExellent/board/BoardList.do?Pno=1");
					model.addAttribute("msg", "삭제에 실패했습니다. 다시 시도해주세요.");
				}
				pDTO = null;
				cDTO = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "/Redirect";
		} else {
			model.addAttribute("url", "/DExellent/board/BoardList.do?Pno=1");
			model.addAttribute("msg", "권한이 없습니다. 게시판 리스트로 이동합니다.");

			pDTO = null;
			cDTO = null;
		}
		return "/Redirect";
	}

	// ------------------------댓글 수정 실행------------------------------
	@RequestMapping(value = "/DExellent/board/CommentUpdateTry")
	public String CommentUpdateTry(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		log.info(this.getClass().getName() + "########  댓글 수정하기 실행 ########");

		String rno = request.getParameter("rno");
		String board_seq = request.getParameter("board_seq");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");

		log.info("rno : " + rno);
		log.info("board_seq : " + board_seq);
		log.info("content : " + content);
		log.info("writer : " + writer);

		CommentDTO pDTO = new CommentDTO();
		pDTO.setRno(rno);
		pDTO.setBoard_seq(board_seq);
		pDTO.setContent(content);
		pDTO.setWriter(writer);
		try {

			int res = BoardService.CommentUpdateTry(pDTO);
			log.info("pDTO :  " + pDTO);
			log.info("res : " + res);

			if (res > 0) {
				model.addAttribute("url", "/DExellent/board/BoardList.do?Pno=1");
				model.addAttribute("msg", "수정되었습니다.");
			} else {
				model.addAttribute("url", "/DExellent/board/BoardList.do?Pno=1");
				model.addAttribute("msg", "수정에 실패했습니다.");
			}

			pDTO = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/Redirect";

	}

	// ----------- 문자 필터 ------------
	public BoardDTO BoardFilter(BoardDTO pDTO) {
		pDTO.setTitle(pDTO.getTitle().replaceAll("& lt;", "&lt;").replaceAll("& gt;", "&gt;"));
		pDTO.setTitle(pDTO.getTitle().replaceAll("& #40;", "\\(").replaceAll("& #41;", "\\)"));
		pDTO.setTitle(pDTO.getTitle().replaceAll("& #39;", "'"));
		pDTO.setTitle(pDTO.getTitle().replaceAll("& #256;", "script"));
		if (pDTO.getContent() != null) {
			pDTO.setContent(pDTO.getContent().replaceAll("& lt;", "<").replaceAll("& gt;", ">"));
			pDTO.setContent(pDTO.getContent().replaceAll("& #40;", "\\(").replaceAll("& #41;", "\\)"));
			pDTO.setContent(pDTO.getContent().replaceAll("& #39;", "'"));
			pDTO.setContent(pDTO.getContent().replaceAll("& #256;", "script"));
		}
		return pDTO;
	}
}