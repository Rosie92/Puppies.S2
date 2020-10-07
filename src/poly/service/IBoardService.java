package poly.service;

import java.util.HashMap;
import java.util.List;

import poly.dto.BoardDTO;
import poly.dto.CommentDTO;

public interface IBoardService {

	// ---------------------------게시판-------------------------

	// 총 게시글 수 세기
	int TotalCount() throws Exception;

	// 게시판 리스트 불러오기
	List<BoardDTO> getBoardList(HashMap<String, Integer> hMap) throws Exception;

	// 게시판 글 작성 실행하기
	int InsertBoardWriteProc(BoardDTO bDTO) throws Exception;

	// 게시판 글 디테일
	BoardDTO getBoardDetail(String seq) throws Exception;

	// 게시판 글 수정/삭제 이동 전 권한 확인하기
	BoardDTO UserCheck(String board_seq) throws Exception;

	// 게시판 글 수정/삭제 창으로 이동하기
	BoardDTO BoardReWrite(BoardDTO pDTO) throws Exception;

	// 게시판 글 수정 실행하기
	int BoardReWriteTry(BoardDTO pDTO) throws Exception;

	// 게시판 글 삭제 실행하기
	int BoardDelete(BoardDTO pDTO) throws Exception;

	// 게시글 삭제하며 해당 게시글에 달린 댓글 모두 삭제
	int BoardDeleteWithCommentDelete(CommentDTO cDTO) throws Exception;

	// -----------------------------댓글--------------------------
	// 댓글 리스트 불러오기
	List<CommentDTO> readReply(String seq) throws Exception;

	// 댓글 작성 실행하기
	int InsertComment(CommentDTO bDTO) throws Exception;

	// 댓글 수정/삭제 이동 전 권한 확인하기
	CommentDTO UserCheck2(String rno) throws Exception;

	// 댓글 삭제 실행하기
	int CommentDelete(CommentDTO pDTO) throws Exception;

	// 댓글 수정 창 이동을 위해 정보 불러오기
	CommentDTO CommentUpdate(CommentDTO pDTO) throws Exception;

	// 댓글 수정 실행하기
	int CommentUpdateTry(CommentDTO pDTO) throws Exception;

}
