<%@page import="java.util.ArrayList"%>
<%@page import="poly.util.CmmUtil"%>
<%@page import="java.util.List"%>
<%@page import="poly.dto.CommentDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	CommentDTO pDTO = (CommentDTO) request.getAttribute("pDTO");

String content = CmmUtil.nvl(pDTO.getContent());
String rno = CmmUtil.nvl((String) request.getAttribute("rno"));
String writer = CmmUtil.nvl(pDTO.getWriter());
String seq = CmmUtil.nvl(pDTO.getBoard_seq());
%>
<html>


<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>댓글 수정</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>

<link href="/css/styles.css" rel="stylesheet" />
</head>
<header>
	<%
		
	%>
</header>
<body
	style="overflow-x: hidden; background-image: url('../../assets/img/DEIMG/BoardList.jpg');">
	<form method="post"
		action="/DExellent/board/CommentUpdateTry.do?rno=<%=rno%>">
		<input value="<%=rno%>" name="rno" hidden="hidden"> <input
			value="<%=writer%>" name="writer" hidden="hidden"> <input
			value="<%=seq%>" name="board_seq" hidden="hidden">
		<div>

			<div>
				<textarea id="content" name="content"
					style="width: 325px; height: 50px; margin-left: 10px; margin-top: 10px; resize: none;"
					placeholder="<%=content%>"></textarea>
			</div>

			<div
				style="text-align: center; margin-left: 48px; width: 250px; padding-top: 10px;">
				<div class="form-group" id="submit">
					<!-- submit이 input에 들어가면 유효성 검사가 실행이X -->
					<input type="submit" id="subBtn" class="btn btn-primary btn-block"
						readonly="readonly" style="cursor: pointer;" value="작성완료">
				</div>

			</div>
		</div>
	</form>

	<form name="BackMove" id="BackMove" method="post"
		action="/DExellent/board/BoardList.do?Pno=1"
		style="display: inline-block;">
		<div
			style="margin-left: 205px; text-align: center; display: inline-block;">
			<input class="btn btn-primary btn-block" type="button" value="수정 종료"
				onclick="removeCheck2()" readonly="readonly"
				style="cursor: pointer;">
		</div>
	</form>




	<script type="text/javascript">
		function removeCheck2() {
			if (confirm("정말로 종료하시겠습니까? 작성중이던 내용은 저장되지 않으며, 게시판 리스트로 이동합니다.") == true) { //확인
				$('#BackMove').submit()
			} else { //취소
				return false;
			}
		}
	</script>

	<script>
		$('#subBtn').click(function() {
			var content = $('#content').val();

			if (content == "") {
				alert("내용을 입력해 주세요.");
				return false;
				$('#content').focus();
			}
			/*         else if(board_pw==""){
			 alert("비밀번호를 입력해 주세요.");
			 return false;
			 $('#board_pw').focus();
			 } */
		});
	</script>
</body>
</html>











