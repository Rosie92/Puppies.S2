package poly.persistance.mapper;

import java.util.HashMap;
import java.util.List;

import config.Mapper;
import poly.dto.BoardDTO;
import poly.dto.CommentDTO;

@Mapper("BoardMapper")
public interface BoardMapper {

	// ----------------------------게시판-----------------------------
	// 게시판 총 게시글 수 확인하기
	int TotalCount() throws Exception;

	// 게시판 리스트 불러오기
	List<BoardDTO> getBoardList(HashMap<String, Integer> hMap);

	// 게사판 글 작성 실행하기
	int InsertBoardWriteProc(BoardDTO bDTO) throws Exception;

	// 게시판 글 디테일 실행하기
	BoardDTO getBoardDetail(String seq) throws Exception;

	// 게시판 글 수정/삭제 이동 전 권한 확인하기
	BoardDTO UserCheck(String board_seq) throws Exception;

	// 게시판 글 수정/삭제 창으로 이동하기
	BoardDTO BoardReWrite(BoardDTO pDTO) throws Exception;

	// 게시판 글 수정하기 실행
	int BoardReWriteTry(BoardDTO pDTO) throws Exception;

	// 게시판 글 삭제 실행
	int BoardDelete(BoardDTO pDTO) throws Exception;

	// 게시글 삭제하며 해당 글에 달린 댓글 모두 삭제
	int BoardDeleteWithCommentDelete(CommentDTO cDTO) throws Exception;

	// -----------------------------댓글-----------------------------------
	// 댓글 리스트 불러오기
	List<CommentDTO> readReply(String seq) throws Exception;

	// 댓글 작성 실행하기
	int InsertComment(CommentDTO bDTO) throws Exception;

	// 댓글 삭제 이동 전 권한 확인하기
	CommentDTO UserCheck2(String rno) throws Exception;

	// 댓글 삭제 실행하기
	int CommentDelete(CommentDTO pDTO) throws Exception;

	// 댓글 수정 창 이동을 위한 정보 불러오기
	CommentDTO CommentUpdate(CommentDTO pDTO) throws Exception;

	// 댓글 수정 실행하기
	int CommentUpdateTry(CommentDTO pDTO) throws Exception;

}
