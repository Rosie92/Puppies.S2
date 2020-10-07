package poly.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.BoardDTO;
import poly.dto.CommentDTO;
import poly.persistance.mapper.BoardMapper;
import poly.service.IBoardService;

@Service("BoardService")
public class BoardService implements IBoardService {

	@Resource(name = "BoardMapper")
	private BoardMapper BoardMapper;

	// -------------------------게시판--------------------------------

	// 총 게시글 수 확인
	@Override
	public int TotalCount() throws Exception {
		return BoardMapper.TotalCount();
	}

	// 게시판 리스트 불러오기
	@Override
	public List<BoardDTO> getBoardList(HashMap<String, Integer> hMap) throws Exception {
		return BoardMapper.getBoardList(hMap);
	}

	// 게시판 글 작성 실행
	@Override
	public int InsertBoardWriteProc(BoardDTO bDTO) throws Exception {
		return BoardMapper.InsertBoardWriteProc(bDTO);
	}

	// 게시판 글 디테일 실행
	@Override
	public BoardDTO getBoardDetail(String seq) throws Exception {
		return BoardMapper.getBoardDetail(seq);
	}

	// 게시판 글 수정/삭제 이동 전 권한 확인
	@Override
	public BoardDTO UserCheck(String board_seq) throws Exception {
		return BoardMapper.UserCheck(board_seq);
	}

	// 게시판 글 수정/삭제 창으로 이동
	@Override
	public BoardDTO BoardReWrite(BoardDTO pDTO) throws Exception {
		return BoardMapper.BoardReWrite(pDTO);
	}

	// 게시판 글 수정 실행
	@Override
	public int BoardReWriteTry(BoardDTO pDTO) throws Exception {
		return BoardMapper.BoardReWriteTry(pDTO);
	}

	// 게시판 글 삭제 실행
	@Override
	public int BoardDelete(BoardDTO pDTO) throws Exception {
		return BoardMapper.BoardDelete(pDTO);
	}

	// 게시글 삭제하며 해당 게시글에 달린 댓글 모두 삭제
	@Override
	public int BoardDeleteWithCommentDelete(CommentDTO cDTO) throws Exception {
		return BoardMapper.BoardDeleteWithCommentDelete(cDTO);
	}

	// ------------------------댓글--------------------------

	// 댓글 리스트 불러오기
	@Override
	public List<CommentDTO> readReply(String seq) throws Exception {
		return BoardMapper.readReply(seq);
	}

	// 댓글 등록하기
	@Override
	public int InsertComment(CommentDTO bDTO) throws Exception {
		return BoardMapper.InsertComment(bDTO);
	}

	// 댓글 수정/삭제 이동 전 권한 확인
	@Override
	public CommentDTO UserCheck2(String rno) throws Exception {
		return BoardMapper.UserCheck2(rno);
	}

	// 댓글 삭제하기
	@Override
	public int CommentDelete(CommentDTO pDTO) throws Exception {
		return BoardMapper.CommentDelete(pDTO);
	}

	// 댓글 수정을 위해 정보 불러오기
	@Override
	public CommentDTO CommentUpdate(CommentDTO pDTO) throws Exception {
		return BoardMapper.CommentUpdate(pDTO);
	}

	@Override
	// 댓글 수정 실행하기
	public int CommentUpdateTry(CommentDTO pDTO) throws Exception {
		return BoardMapper.CommentUpdateTry(pDTO);
	}

}
