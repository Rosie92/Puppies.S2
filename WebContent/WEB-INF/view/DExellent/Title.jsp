<%@page import="java.util.List"%>
<%@page import="poly.dto.TitleDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<TitleDTO> eList = (List<TitleDTO>) request.getAttribute("eList");
List<TitleDTO> rList = (List<TitleDTO>) request.getAttribute("rList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="/js/jquery-3.4.1.min.js"></script>
<!-- <script type="text/javascript">
	$(window).on("load", function() {
		//페이지 로딩 완료 후, 크롤링 정보 가져오기 함수 실행함
		getTitle();
	});

	// 크롤링 정보 가져오기
	function getTitle() {

		//Ajax 호출
		$
				.ajax({
					url : "/DExellent/getTitle.do",
					type : "post",
					dataType : "JSON",
					contentType : "application/json; charset=UTF-8",
					success : function(json) {

						$
								.ajax({

									url : "/DExellent/getContent.do",
									type : "post",
									dataType : "JSON",
									contentType : "application/json; charset=UTF-8",
									success : function(data) {

										var title = "";
										var content = "";

										for (var i = 0; i < json.length; i++) { // 이미지 더 구하거나 10개까지 제한으로 변경 필요함
											title += "<div style='display: inline-block;'>";
											title += "<img src='../../assets/img/DEIMG/PP" + i + ".jpg' width='60px' height='60px'></div>";
											title += "<div style='display: inline-block; width: 260px; line-height: 30px; margin-left: 8px;'>";
											/* title += "<a href='" + data[i].content + " ' target='_self'";
 											title += "style='text-decoration: none; font-weight: 500;'>";  
											title += "<span style='font-size: 17px; color: black;'>";
											title += (json[i].title);
											title += "</span></a></div><br><hr>"; */
											title += "<button data-toggle='modal' data-target='#intro'>";
											title += (json[i].title); + "</button>";
											
										}
										$('#title').html(title);
									}
								})
					}
				})
	}
</script> -->
<title>동물뉴스</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body
	style="width: 347px; background-image: URL(../../assets/img/DEIMG/news_back.jpg)">
	<div style="overflow-x: hidden">
		<br>
		<%
			int j = eList.size();
		for (int a = 0; a < j; a++) {
		%>
		<div
			style='display: inline-block; width: 100%; line-height: 10px; margin-left: 6px;'>
			<button data-toggle='modal' data-target='#intro<%=a%>'
				style="background-color: white; border: 0px;">
				<img src='../../assets/img/DEIMG/PP<%=a%>.jpg' width='80px'
					height='80px'
					style='display: inline-block; vertical-align: middle;'>
				<div
					style="display: inline-block; font-size: 13px; line-height: 40px; width: 235px; vertical-align: middle;"><%=eList.get(a).getTitle()%></div>
			</button>
			<br>
			<br>
		</div>
		<%
			}
		%>


		<%
			int k = rList.size();
		for (int b = 0; b < k; b++) {
		%>
		<div class="modal fade" id="intro<%=b%>" role="dialog"
			aria-labelledby="introHeader" aria-hidden="true" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content" style="height: 650px;">
					<div class="modal-header">
						<h4 class="modal-title" style="height: 10px; display: inline;">N
							E W S</h4>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							style="margin-left: 170px;">Ⅹ</button>
					</div>
					<div class="modal-body" style="height: 550px;">
						<iframe src="<%=rList.get(b).getContent()%>" width="295px"
							height="530px" name="Title" id="Title" frameborder="1"
							scrolling="yes" style="overflow-x: hidden" /></iframe>
					</div>
					<div class="modal-footer"></div>
				</div>
			</div>
		</div>
		<%
			}
		%>


	</div>
</body>
</html>