<%@page import="java.util.List"%>
<%@page import="poly.dto.InformationDTO"%>
<%
	List<InformationDTO> aList = (List<InformationDTO>) request.getAttribute("aList");
List<InformationDTO> bList = (List<InformationDTO>) request.getAttribute("bList");
List<InformationDTO> cList = (List<InformationDTO>) request.getAttribute("cList");
List<InformationDTO> dList = (List<InformationDTO>) request.getAttribute("dList");
List<InformationDTO> eList = (List<InformationDTO>) request.getAttribute("eList");
List<InformationDTO> fList = (List<InformationDTO>) request.getAttribute("fList");
List<InformationDTO> gList = (List<InformationDTO>) request.getAttribute("gList");
List<InformationDTO> hList = (List<InformationDTO>) request.getAttribute("hList");
List<InformationDTO> iList = (List<InformationDTO>) request.getAttribute("iList");
List<InformationDTO> jList = (List<InformationDTO>) request.getAttribute("jList");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="/js/jquery-3.4.1.min.js"></script>
<!-- <script type="text/javascript">
	$(window).on("load", function() {
		//페이지 로딩 완료 후, 크롤링 정보 가져오기 함수 실행함
		getInformation();
	});

	// 크롤링 정보 가져오기
	function getInformation() {

		//Ajax 호출
		$.ajax({
			url : "/DExellent/getInformation.do",
			type : "post",
			dataType : "JSON",
			contentType : "application/json; charset=UTF-8",
			success : function(json) {
				
				console.log(json.length);

				var Information = "";

				for (var i = 0; i < json.length; i++) {
					Information += (json[i].information);
				}
				$('#Information').html(Information);
			}
		})
	}
</script> -->
<title>Information</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body
	style="width: 347px; background-image: URL(../../assets/img/DEIMG/infor_back.jpg); overflow-x: hidden">
	<div style="overflow-x: hidden">
		<br>
		<!-- @@@@@@@@@@@@@@@@@@@@@@@@@@ 버튼 @@@@@@@@@@@@@@@@@@@@@ -->
		<!-- 골든리트리버 -->
		<%
			String a0 = "골든 리트리버";

		for (int i = 0; i < 10; i++) {
		%>
		<div
			style='display: inline-block; width: 90%; line-height: 10px; margin-left: 17px;'>
			<button data-toggle='modal' data-target='#intro<%=i%>'
				style="background-color: white; border: 0px;">
				<img src='../../assets/img/DEIMG/dog/a<%=i%>.jpg' width='300px'
					height='190px'
					style='display: inline-block; vertical-align: middle;'>
				<div
					style="display: inline-block; font-size: 20px; font-weight: bolder; line-height: 40px; width: 235px; vertical-align: middle;">
					<%
						if (i == 0) {
					%>
					골든 리트리버
					<%
						} else if (i == 1) {
					%>
					래브라도 리트리버
					<%
						} else if (i == 2) {
					%>
					말티즈
					<%
						} else if (i == 3) {
					%>
					보더콜리
					<%
						} else if (i == 4) {
					%>
					비숑프리제
					<%
						} else if (i == 5) {
					%>
					사모예드
					<%
						} else if (i == 6) {
					%>
					셔틀랜드 쉽독
					<%
						} else if (i == 7) {
					%>
					요크셔테리어
					<%
						} else if (i == 8) {
					%>
					재패니스 스피츠
					<%
						} else if (i == 9) {
					%>
					치와와
					<%
						}
					%>
				</div>
			</button>
			<br>
			<br>
		</div>

		<%
			}
		%>
		<!-- @@@@@@@@@@@@@@@@@@@@@@@@@ 팝업 @@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
		<!-- 골든 리트리버 -->
		<%
			int a = aList.size();
		int b = bList.size();
		int c = cList.size();
		int d = dList.size();
		int e = eList.size();
		int f = fList.size();
		int g = gList.size();
		int h = hList.size();
		int i = iList.size();
		int j = jList.size();

		for (int w = 0; w < 10; w++) {
		%>
		<div class="modal fade" id="intro<%=w%>" role="dialog"
			aria-labelledby="introHeader" aria-hidden="true" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content" style="height: 650px;">
					<div class="modal-header">
						<h4 class="modal-title" style="height: 10px; display: inline;">견종백과</h4>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							style="margin-left: 170px;">Ⅹ</button>
					</div>
					<div class="modal-body" style="height: 550px;">
						<img src='../../assets/img/DEIMG/dog/a<%=w%>.jpg' width='293px'
							height='200px'
							style='display: inline-block; vertical-align: middle;'>
						<hr>
						<div
							style="overflow: scroll; height: 310px; background-attachment: fixed; background-image: URL(../../assets/img/DEIMG/title2.jpg);">
							<%
								if (w == 0) {
								for (int q = 0; q < a; q++) {
							%>
							<%=aList.get(q).getInformation()%><hr>

							<%
								}
							} else if (w == 1) {
								for (int q = 0; q < b; q++) {
							%>
							<%=bList.get(q).getInformation()%><hr>

							<%
								}
							} else if (w == 2) {
								for (int q = 0; q < c; q++) {
							%>
							<%=cList.get(q).getInformation()%><hr>

							<%
								}
							} else if (w == 3) {
								for (int q = 0; q < d; q++) {
							%>
							<%=dList.get(q).getInformation()%><hr>

							<%
								}
							} else if (w == 4) {
								for (int q = 0; q < e; q++) {
							%>
							<%=eList.get(q).getInformation()%><hr>

							<%
								}
							} else if (w == 5) {
								for (int q = 0; q < f; q++) {
							%>
							<%=fList.get(q).getInformation()%><hr>

							<%
								}
							} else if (w == 6) {
								for (int q = 0; q < g; q++) {
							%>
							<%=gList.get(q).getInformation()%><hr>

							<%
								}
							} else if (w == 7) {
								for (int q = 0; q < h; q++) {
							%>
							<%=hList.get(q).getInformation()%><hr>

							<%
								}
							} else if (w == 8) {
								for (int q = 0; q < i; q++) {
							%>
							<%=iList.get(q).getInformation()%><hr>

							<%
								}
							} else if (w == 9) {
								for (int q = 0; q < j; q++) {
							%>
							<%=jList.get(q).getInformation()%><hr>

							<%
								}
							}
							%>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%
			}
		%>

	</div>
</body>

</html>