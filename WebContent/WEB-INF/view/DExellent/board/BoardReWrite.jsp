<%@page import="java.util.ArrayList"%>
<%@page import="poly.util.CmmUtil"%>
<%@page import="java.util.List"%>
<%@page import="poly.dto.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	BoardDTO pDTO = (BoardDTO) request.getAttribute("pDTO");

if (pDTO == null) {
	pDTO = new BoardDTO();
}

String content = CmmUtil.nvl(pDTO.getContent());
%>
<html>


<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>자유게시판 수정</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<!-- 
    <script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
    <script src="/summernote/js/summernote-ko-KR.js"></script>
    <script>
    
        $(document).ready(function () {
            $('#summernote').summernote({
                placeholder: '내용을 입력해 주세요.',
                minHeight: 370,
                maxHeight: null,
                focus: true,
                lang: 'ko-KR',
                onImageUpload : function(files, editor, welEditable) {
                    sendFile(files[0], editor, welEditable);
                }
            });

            function sendFile(file, editor, welEditable) {
                data = new FormData();
                data.append("uploadFile", file);
                $.ajax({
                    data : data,
                    type : "POST",
                    url : "/imageUpload",
                    cache : false,
                    contentType : false,
                    processData : false,
                    success : function (data) {
                        editor.insertImage(welEditable, data.url);
                    }
                })
            }
        });

    </script>  -->
<link href="/css/styles.css" rel="stylesheet" />
</head>
<header>
	<%
		String seq = CmmUtil.nvl((String) request.getAttribute("seq"));
	String user_name = CmmUtil.nvl((String) session.getAttribute("user_name"));
	/* 	String upd_date = CmmUtil.nvl((String)request.getAttribute("upd_date")); */
	%>

</header>
<body
	style="overflow-x: hidden; width: 350px;background-image: url('../../assets/img/DEIMG/BoardList.jpg');">
	<form method="post"
		action="/DExellent/board/BoardReWriteTry.do?seq=<%=seq%>">
		<input value="<%=seq%>" name="seq" hidden="hidden">
		<div>
			<div style="font-size: 20px; font-weight: 550; width: 370px;">
				<div
					style="text-align: center; display: inline-block; padding: 10px 0px 10px 20px;">제
					목</div>
				<div
					style="display: inline-block; padding: 20px 5px 10px 20px; width: 230px;">
					<div>
						<input type="text" name="title" id="title" maxlength="100"
							style="width: 250px" placeholder='제목을 입력해주세요'
							value="<%=CmmUtil.nvl(pDTO.getTitle())%>">
					</div>
				</div>
			</div>
			<div>
				<textarea id="contentCheck" name="content"
					style="width: 330px; height: 455px; margin-left: 10px; resize: none;"
					placeholder="내용을 입력해주세요"><%=CmmUtil.nvl(pDTO.getContent())%></textarea>
			</div>

			<div
				style="text-align: center; margin-left: 23px; width: 300px; padding-top: 5px;">
				<div class="form-group" id="submit">
					<!-- submit이 input에 들어가면 유효성 검사가 실행이X -->
					<input type="submit" id="subBtn" class="btn btn-primary btn-block"
						readonly="readonly" style="cursor: pointer;" value="작성완료">
				</div>
			</div>
		</div>
	</form>
	<div style="margin-left: 55px;">
		<form name="DDelete" id="DDelete" method="post"
			action="/DExellent/board/BoardDelete.do?Seq=<%=seq%>"
			style="display: inline-block;">
			<input value="<%=seq%>" name="seq" hidden="hidden">
			<div
				style="margin-left: 10px; text-align: center; display: inline-block;">
				<input type="button" id="" value="게시글 삭제" onclick="removeCheck3()"
					style="cursor: pointer; color: white; border: 0px; background-color: #fed136;">
			</div>
		</form>


		<form name="BackMove" id="BackMove" method="post"
			action="/DExellent/board/BoardList.do?Pno=1"
			style="display: inline-block;">
			<div
				style="margin-left: 10px; text-align: center; display: inline-block;">
				<input type="button" value="글 수정 종료" onclick="removeCheck2()"
					style="cursor: pointer; color: white; border: 0px; background-color: #fed136;">
			</div>
		</form>
	</div>



	<script>
		$('#subBtn').click(function() {
			var title = $('#title').val();
			var content = $('#contentCheck').val();

			if (title == "") {
				alert("제목을 입력해 주세요.");
				return false;
				$('#title').focus();
			} else if (content == "") {
				alert("내용을 입력해 주세요.");
				return false;
				$('#content').focus();
			}

		});
	</script>
	<script type="text/javascript">
		function removeCheck3() {
			if (confirm("이 게시글을 정말 삭제하시겠습니까??") == true) { //확인
				$('#DDelete').submit()
			} else { //취소
				return false;
			}
		}

		function removeCheck2() {
			if (confirm("정말로 종료하시겠습니까? 작성중이던 내용은 저장되지 않으며, 게시판 리스트로 이동합니다.") == true) { //확인
				$('#BackMove').submit()
			} else { //취소
				return false;
			}
		}
	</script>
</body>
</html>











