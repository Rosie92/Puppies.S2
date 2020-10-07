<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>http://www.blueb.co.kr</title>
	<link href="../css/demo.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../css/jqbar.css" />
</head>

<body style="background-image: URL(../../assets/img/DEIMG/graph-back.png);">
<div style="margin-top:20px;">
<h1 style="text-align:center;">지능 순위</h1>
	<div id="bar-1"></div>
	<div id="bar-2"></div>
	<div id="bar-3"></div>
	<div id="bar-4"></div>
	<div id="bar-5"></div>
	<div id="bar-6"></div>
	<div id="bar-7"></div>
	<div id="bar-8"></div>
	<div id="bar-9"></div>
	<div id="bar-10"></div>
</div>


<div>
<h1 style="text-align:center; margin-top:40px;">세계 인기 순위</h1>
	<div id="bar-11"></div>
	<div id="bar-21"></div>
	<div id="bar-31"></div>
	<div id="bar-41"></div>
	<div id="bar-51"></div>
	<div id="bar-61"></div>
	<div id="bar-71"></div>
	<div id="bar-81"></div>
	<div id="bar-91"></div>
	<div id="bar-101"></div>
</div>


<div>
<h1 style="text-align:center; margin-top:40px;">가격 순위</h1>
	<div id="bar-111"></div>
	<div id="bar-211"></div>
	<div id="bar-311"></div>
	<div id="bar-411"></div>
	<div id="bar-511"></div>
	<div id="bar-611"></div>
</div>



<script src="../js/jquery-3.4.1.min.js" type="text/javascript"></script>
<script src="../js/jqbar.js" type="text/javascript"></script>
<script type="text/javascript">

	$(document).ready(function () {
		$('#bar-1').jqbar({ label: '보더콜리', value: 99, barColor: '#D64747' });
		$('#bar-2').jqbar({ label: '푸들', value: 93, barColor: '#FF681F' });
		$('#bar-3').jqbar({ label: '셰퍼드', value: 90, barColor: '#ea805c' });
		$('#bar-4').jqbar({ label: '리트리버', value: 85, barColor: '#88bbc8' });
		$('#bar-5').jqbar({ label: '도베르만', value: 83, barColor: '#939393' });
		$('#bar-6').jqbar({ label: '셔틀랜드쉽독', value: 81, barColor: '#3a89c9' });
		$('#bar-7').jqbar({ label: '라브라도', value: 76, barColor: '#D64747'});
		$('#bar-8').jqbar({ label: '빠삐용', barColor: '#FF681F', value: 73 });
		$('#bar-9').jqbar({ label: '롯트와일러', barColor: '#ea805c', value: 71});
		$('#bar-10').jqbar({ label: '캐틀독', barColor: '#88bbc8', value: 70});
	});
	
	$(document).ready(function () {
		$('#bar-11').jqbar({ label: '레브라도 리트리브', value: 99, barColor: '#D64747' });
		$('#bar-21').jqbar({ label: '저먼 셰퍼드', value: 93, barColor: '#FF681F' });
		$('#bar-31').jqbar({ label: '골든 리트리버', value: 90, barColor: '#ea805c' });
		$('#bar-41').jqbar({ label: '불독', value: 85, barColor: '#88bbc8' });
		$('#bar-51').jqbar({ label: '푸들', value: 83, barColor: '#939393' });
		$('#bar-61').jqbar({ label: '비글', value: 81, barColor: '#3a89c9' });
		$('#bar-71').jqbar({ label: '로트 바일러', value: 76, barColor: '#D64747'});
		$('#bar-81').jqbar({ label: '프렌치 불독', barColor: '#FF681F', value: 73 });
		$('#bar-91').jqbar({ label: '요크 셔테리어', barColor: '#ea805c', value: 71});
		$('#bar-101').jqbar({ label: '복서', barColor: '#88bbc8', value: 70});
	});
	
	$(document).ready(function () {
		$('#bar-111').jqbar({ label: '티베탄마스티프', value: 99, barColor: '#D64747' });
		$('#bar-211').jqbar({ label: '킹찰스 스패니얼', value: 93, barColor: '#FF681F' });
		$('#bar-311').jqbar({ label: '사모예드', value: 90, barColor: '#ea805c' });
		$('#bar-411').jqbar({ label: '잉글리쉬 불독', value: 85, barColor: '#88bbc8' });
		$('#bar-511').jqbar({ label: '로첸', value: 83, barColor: '#939393' });
		$('#bar-611').jqbar({ label: '파라오 하운드', value: 81, barColor: '#3a89c9' });
	});
</script>


</body>
</html>
