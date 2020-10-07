<%@page import="java.util.ArrayList"%>
<%@page import="poly.util.CmmUtil"%>
<%@page import="java.util.List"%>
<%@page import="poly.dto.BoardDTO"%>
<%@page import="poly.dto.CommentDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	BoardDTO bDTO = (BoardDTO) request.getAttribute("bDTO");
List<CommentDTO> cList = (List<CommentDTO>) request.getAttribute("cList");
if (bDTO == null) {
	bDTO = new BoardDTO();
}
%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>게시판디테일</title>
<head>
<link href="/css/styles.css" rel="stylesheet" />
<!--     <script src="/summernote/summernote-lite.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
    <script src="/summernote/lang/summernote-ko-KR.js"></script>
     -->

<style>
</style>
</head>

<header>
	<%
		//CmmUtil 붙이기 수정
	String session_id = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID"));
	String seq = CmmUtil.nvl((String) request.getAttribute("seq"));
	%>
</header>
<body
	style="overflow-x: hidden; width: 350px; background-image: url('../../assets/img/DEIMG/BoardList.jpg');">
	<div style="font-size: 20px; font-weight: 550; width: 760px;">
		<div
			style="text-align: center; display: inline-block; padding: 10px 10px 10px 20px;">제
			목</div>
		<div
			style="display: inline-block; padding: 10px 0px 10px 10px; width: 245px;">
			<div>
				<input type="text" name="title" maxlength="100" readonly="readonly"
					style="width: 245px; border: none;"
					value='<%=bDTO.getTitle().replaceAll("<", "&lt;").replaceAll(">", "&gt;")%>'>
			</div>
		</div>
	</div>
	<div>
		<div name="content" readonly="readonly"
			style="color: black; word-break: break-all; overflow: auto; width: 330px; height: 450px; margin-left: 10px; resize: none; border: 1px solid black; padding: 5px 5px 5px 5px"><%=CmmUtil.nvl(bDTO.getContent()).replaceAll("<", "&lt;").replaceAll(">", "&gt;")%></div>
	</div>

	<!-- ===================== 댓글리스트 ============================= -->
	<%
		int o = cList.size();
	for (int a = 0; a < o; a++)/* (CommentDTO cDTO : cList) */ {
	%>
	<div
		style="background-color: #ffffffcc; width: 330px; margin-left: 10px; margin-top: 5px;">
		<span> <input type="hidden"
			value="<%=CmmUtil.nvl(cList.get(a).getBoard_seq())%>"> <input
			type="hidden" value="<%=CmmUtil.nvl(cList.get(a).getRno())%>">
		</span> <span>
			<div
				style="width: 50px; text-align: center; display: inline-block; word-break: break-all;">
				<%=CmmUtil.nvl(cList.get(a).getWriter())%>
			</div>
		</span> <span>
			<div
				style="color: black; width: 170px; text-align: center; display: inline-block; word-break: break-all;">
				<%=CmmUtil.nvl(cList.get(a).getContent()).replaceAll("<", "&lt;").replaceAll(">", "&gt;")%>
			</div>
		</span>
		<script type="text/javascript">
				function removeCheck2<%=CmmUtil.nvl(cList.get(a).getRno())%>(){
				   if (confirm("글을 삭제하시겠습니까?") == true) {   
					      document.location.href ="/DExellent/board/CommentDelete.do?rno=" + <%=cList.get(a).getRno()%>
					      return true;
					   } else {
					      return false;
					   }
				 }
				function removeCheck<%=CmmUtil.nvl(cList.get(a).getRno())%>(){
					   if (confirm("댓글 수정창으로 이동합니다.") == true) {   
						      document.location.href ="/DExellent/board/CommentUpdate.do?rno=" + <%=cList.get(a).getRno()%>
						      return true;
						   } else {
						      return false;
						   }
					 }
			</script>
		<div
			style="margin-left: 2px; text-align: center; display: inline-block; width: 45px;">
			<input type="button" value="수정"
				onclick="removeCheck<%=CmmUtil.nvl(cList.get(a).getRno())%>()"
				style="background-color: #fff46e73; border: 0px;">
		</div>
		<div
			style="margin-left: 2px; text-align: center; display: inline-block; width: 45px;">
			<input type="button" value="삭제"
				onclick="removeCheck2<%=CmmUtil.nvl(cList.get(a).getRno())%>()"
				style="background-color: #fff46e73; border: 0px;">
		</div>
	</div>
	<%
		}
	%>

	<div style="overflow-x: hidden">
		<div style="width: 740px; margin: left;">
			<div class="row">
				<div class="col-md-12">
					<!-- 댓글 작성 -->
					<div style="width: 750px; margin: auto;">
						<form id="gogo" name="gogo" method="post"
							action="/DExellent/board/CommentProc.do?seq=<%=CmmUtil.nvl(bDTO.getBoard_seq())%>">
							<div>
								<textarea id="content" name="content"
									style="width: 325px; height: 50px; margin-left: 12.5px; margin-top: 10px; resize: none;"
									placeholder="댓글을 입력해주세요"></textarea>
							</div>
							<div class="form-group" style="width: 155px; padding-top: 5px;">
								<div>
									<input type="button" id="submitA"
										class="btn btn-primary btn-block" readonly="readonly"
										style="cursor: pointer; margin-left: 95px;" value="댓글쓰기">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ================================================== -->
	<hr>
	<div
		style="text-align: center; width: 300px; padding-top: 5px; display: inline; vertical-align: middle;">
		<div style="margin-left: 40px; display: inline;">
			<!-- submit이 input에 들어가면 유효성 검사가 실행이X -->
			<input type="button"
				onclick="location.href='/DExellent/board/BoardReWrite.do?seq=<%=seq%>'"
				class="btn btn-primary btn-block" value="수정/삭제하기"
				style="width: 130px; display: inline;"> <input type="button"
				onclick="location.href='/DExellent/board/BoardList.do?Pno=1'"
				class="btn btn-primary btn-block" value="돌아가기"
				style="width: 130px; display: inline; margin-bottom: 6px;">
			<hr>
		</div>
	</div>

	<!--     <script src="/js/Board/jquery.min.js"></script> -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script type="text/javascript">
	$(function(){ //window.onload
		var submit = 0;
		console.log(submit);
		
		var rePw = /^[a-zA-Z0-9]{4,12}$/; // userid

		    $("#submitA").click(function() {

		        //userid 를 param.
		        var content =  $("#content").val();
		        console.log(content);

		        if(content == ""){
		            alert("내용을 입력해 주세요.");
		            return false;
		        }else{
		        //유효성 검사가 끝나면 아이디 f를 찾아서 액션을 실행해라.
		        document.getElementById('gogo').submit();		        
		        }
		        

		 });


	});

</script>
</body>
</html>