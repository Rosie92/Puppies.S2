<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<!--         <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" /> -->
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<meta name="description" content="" />
<meta name="author" content="" />
<title>강아지는 훌륭하다</title>
<link rel="icon" type="image/x-icon" href="/assets/img/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.12.1/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="/css/styles.css" rel="stylesheet" />
<link
	href="http://fonts.googleapis.com/earlyaccess/nanumbrushscript.css"
	rel="stylesheet" type="text/css">

<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

</head>
<body id="page-top"
	style="background-image: URL(../../assets/img/DEIMG/kakaofriends.png);">

	<!-- Masthead2-->
	<header class="masthead2">
		<div class="container">
			<div style="margin-top: 200px;">
				<%
					if (session.getAttribute("user_profile_image").equals("프로필 이미지가 없습니다.")) {
				%>
				<img src="../../assets/img/DEIMG/user_image.jpg" width="200px"
					height="200px">
				<%
					} else {
				%>
				<img src="<%=session.getAttribute("user_profile_image")%>"
					width="200px" height="200px">
				<%
					}
				%>
				<div
					style="font-size: 20px; font-weight: bolder; color: rgb(116, 116, 116); text-align: center;"><%=session.getAttribute("user_name")%></div>
				<div
					style="font-size: 20px; font-weight: bolder; color: rgb(116, 116, 116); text-align: center;">이메일 : <%=session.getAttribute("user_mail")%></div>
				<div
					style="font-size: 20px; font-weight: bolder; color: rgb(116, 116, 116); text-align: center;">성별 : <%=session.getAttribute("gender")%></div>
				<div
					style="font-size: 20px; font-weight: bolder; color: rgb(116, 116, 116); text-align: center;">생일 : <%=session.getAttribute("birthday")%></div>
				<div
					style="font-size: 20px; font-weight: bolder; color: rgb(116, 116, 116); text-align: center;">연령대 : <%=session.getAttribute("user_range")%></div>
				

			</div>
			<div style='display: inline-block; width: 100%;'>
				<button
					class="btn btn-primary2 btn-xl2 text-uppercase js-scroll-trigger"
					data-toggle='modal' data-target='#intro'
					style="display: inline-block; font-size: 15px; width: 200px; vertical-align: middle;">카카오
					계정 관리</button>
			</div>




			<div class="modal fade" id="intro" role="dialog"
				aria-labelledby="introHeader" aria-hidden="true" tabindex="-1">
				<div class="modal-dialog">
					<div class="modal-content" style="height: 650px;">
						<div class="modal-header">
							<h4 class="modal-title"
								style="height: 20px; width: 300px; display: inline;">카카오 계정
								관리</h4>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" style="margin-left: 50px;">Ⅹ</button>
						</div>
						<div class="modal-body" style="height: 550px;">
							<iframe src="https://accounts.kakao.com/weblogin/account/info"
								width="295px" height="500px" name="Title" id="Title"
								frameborder="1" scrolling="yes" style="overflow-x: hidden" /></iframe>
						</div>
						<div class="modal-footer"></div>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
	<!-- Third party plugin JS-->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
	<!-- Contact form JS-->
	<script src="/assets/mail/jqBootstrapValidation.js"></script>
	<script src="/assets/mail/contact_me.js"></script>
	<!-- Core theme JS-->
	<script src="/js/scripts.js"></script>
</body>
</html>
