<%@page import="java.util.ArrayList"%>
<%@page import="poly.util.CmmUtil"%>
<%@page import="java.util.List"%>
<%@page import="poly.dto.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	BoardDTO bDTO = (BoardDTO) request.getAttribute("bDTO");

if (bDTO == null) {
	bDTO = new BoardDTO();
}
%>
<html>


<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link href="/css/styles.css" rel="stylesheet" />

<title>자유게시판 작성</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<!-- 
    <script src="/summernote/summernote-lite.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
    <script src="/summernote/lang/summernote-ko-KR.js"></script>
    
    
  <script>
    
        $(document).ready(function () {
            $('#summernote').summernote({
                placeholder: '내용을 입력해 주세요.',
                minHeight: 300,
                maxHeight: null,
                focus: true,
                lang: 'ko-KR',
                onImageUpload : function(files, editor, welEditable) {
                	for (var i = files.length -1; i >= 0; i--) {
                    uploadSummernoteImageFile(files[i],this);
                	}
                }
            });

            function uploadSummernoteImageFile(file, editor) {
                data = new FormData();
                data.append("file", file);
                $.ajax({
                    data : data,
                    type : "POST",
                    url : "/uploadSummernoteImageFile",
                    contentType : false,
                    enctype: "multipart/form-data",
                    processData : false,
                    success : function(data) {
                        /* $(el).summernote("editor.insertImage", url); */
                        $(editor).summernote('insertImage', data.url);
                    }
                });
            }
        });
    </script>  -->
</head>

<body
	style="overflow-x: hidden; background-image: url('../../assets/img/DEIMG/BoardList.jpg');">
	<form method="post" action="/DExellent/board/BoardWriteProc.do">
		<div>
			<div style="font-size: 20px; font-weight: 550; width: 760px;">
				<div
					style="text-align: center; display: inline-block; padding: 10px 20px 10px 30px;">제
					목</div>
				<div
					style="display: inline-block; padding: 10px 0px 10px 10px; width: 545px;">
					<div>
						<input type="text" name="title" id="title" maxlength="100"
							style="width: 230px" placeholder="제목을 입력해주세요">
					</div>
				</div>
			</div>
			<div>
				<textarea id="contentCheck" name="content"
					style="width: 337px; height: 470px; margin-left: 5px; resize: none;" placeholder="내용을 입력해주세요"></textarea>
			</div>
			<div
				style="text-align: center; width: 255px; padding-top: 5px; margin-left: 45px;">
				<div class="form-group" id="submit">
					<!-- submit이 input에 들어가면 유효성 검사가 실행이X -->
					<input type="submit" id="subBtn" class="btn btn-primary btn-block"
						readonly="readonly" style="cursor: pointer;" value="작성완료">
				</div>
				<a class="forgot" href="/DExellent/board/BoardList.do?Pno=1">돌아가기</a>
			</div>
		</div>

	</form>

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
			/*         else if(board_pw==""){
			 alert("비밀번호를 입력해 주세요.");
			 return false;
			 $('#board_pw').focus();
			 } */
		});
	</script>

</body>
</html>











