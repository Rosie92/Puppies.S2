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
<style type="text/css"></style>
<style>
div#memory_board {
	background: #ffe8e8;
	border: #999 1px solid;
	width: 300px;
	height: 420px;
	padding: 24px;
	margin: 0px auto;
}

div#memory_board>div {
	background: url(tile_bg.jpg) no-repeat;
	border: #000 1px solid;
	width: 8px;
	height: 5px;
	float: left;
	margin: 10px;
	padding: 20px;
	font-size: 17px;
	cursor: pointer;
	text-align: center;
}
</style>
<link rel="icon" type="image/x-icon" href="/assets/img/DEIMG/title.png" />
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
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>

</head>
<body id="page-top" style="width: 375px;">
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav"
		style="width: 375px;">
		<div class="container">
			<a class="navbar-brand js-scroll-trigger" href="#page-top"
				style="margin-left: 10px;"> <img
				src="/assets/img/DEIMG/title.png" width="50" height="50" />
				<div class="title"
					style="display: inline; font-family: 'Nanum Brush Script', serif; margin-left: 5px;">
					강아지는 훌륭하다</div>
			</a>
			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation"
				style="font-family: 'Nanum Brush Script', serif; font-size: 20px;">
				Menu<i class="fas fa-bars ml-1"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav text-uppercase ml-auto">


					<%
						if (session.getAttribute("user_name") == null) {
					%>


					<a
						href="https://kauth.kakao.com/oauth/authorize?client_id=928bbb3f0e2b7934f85c85beaa13b7ea&redirect_uri=http://localhost:8080/kakaologin.do&response_type=code">
						<div style="">
							<img src="/assets/img/DEIMG/kakaologin3.png"
								style="width: 350px;">
							<!-- margin-left: 40px; margin-top: 10px; -->
						</div>
					</a>


					<%
						} else {
					%>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#QuickWeather">QuickWeather</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#services">NEWS</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#about">INFORMATION</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#team">KINDERGARTEN</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#mypage">MYPAGE</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#contact">BOARD</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#ETC">ETC</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="/kakaologout.do">LOGOUT</a></li>

					<%
						}
					%>
				</ul>
			</div>
		</div>
	</nav>
	<!-- Masthead-->
	<header class="masthead">
		<div class="container">
			<%
				if (session.getAttribute("user_name") == null) {
			%>
			<input type="button" value="W E L C O M E"
				class="btn btn-primary btn-xl text-uppercase js-scroll-trigger"
				onclick="alert('먼저 로그인 해주세요')">
			<%
				} else {
			%>
			<a class="btn btn-primary btn-xl text-uppercase js-scroll-trigger"
				href="#QuickWeather">W&nbspE&nbspL&nbspC&nbspO&nbspM&nbspE</a>
			<%
				}
			%>
		</div>
	</header>
	<%
		if (session.getAttribute("user_name") == null) {
	%>
	<hr>
	<span style="color: black; font-size: 12px; text-align: center;">※
		해당 페이지는 모바일 환경(375x812)에 알맞게 구성되어있습니다</span>
	<!-- Footer-->
	<footer class="footer py-4">
		<div class="container">
			<div class="row align-items-center">

				<div class="col-lg-4 text-lg-left">Copyright © Your Website
					2020</div>
				<div class="col-lg-4 my-3 my-lg-0">

					<a class="btn btn-dark btn-social mx-2"
						onclick="alert('(Twitter) 준비중입니다.')"> <i
						class="fab fa-twitter" style="color: white;"></i>
					</a> <a class="btn btn-dark btn-social mx-2"
						onclick="alert('(Facebook) 준비중입니다.')"> <i
						class="fab fa-facebook-f" style="color: white;"></i>
					</a> <a class="btn btn-dark btn-social mx-2"
						onclick="alert('(Instagram) 준비중입니다.')"> <i
						class="fab fa-linkedin-in" style="color: white;"></i>
					</a>

				</div>
				<div class="col-lg-4 text-lg-right">

					<a class="mr-3"
						onclick="alert('강아지는 훌륭하다의 개인보호 정책은 카카오 로그인에 기반을 둡니다.')"
						style="color: black;">Privacy Policy</a> <a class="mr-3"
						onclick="alert('Terms of Use : 강아지를 사용하는 모든 분들입니다.'))"
						style="color: black;">Terms of Use</a>
				</div>
			</div>
		</div>
	</footer>

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
	<%
		} else {
	%>
	<!-- Services-->
	<hr style="margin-top: 50px;">
	<span style="color: black; font-size: 12px; text-align: center;">※
		해당 페이지는 모바일 환경(375x812)에 알맞게 구성되어있습니다</span>


	<!-- 빠른 날씨 정보 -->
	<section class="page-section" id="QuickWeather">
		<h1 style="text-align: center; margin-top: 10px;">Quick Weather</h1>
		<p style="text-align: center">
		실시간 날씨 정보입니다.
			<img class="img-fluid" src="../../assets/img/DEIMG/weather.png"
				style="width: 370px; height: 220px;">
		</p>
		<script>
	$.getJSON('http://api.openweathermap.org/data/2.5/forecast?id=1835848&appid=375625767fd514b11cd4f5166ca87b8e'
			,function(data){
		var $minTemp = data.list[0].main.temp_min;
		var $maxTemp = data.list[0].main.temp_max;
		var $humidity = data.list[0].main.humidity;
		var $type = data.list[0].weather[0].description;
		var $sky = data.list[0].weather[0].main;
		var $probability = data.list[0].clouds.all;
		
		if($sky == "Clouds")
			$sky = "구름";
		else if($sky == "Rain")
			$sky = "비";
		else
			$type = "맑음";
		
		$('.clowtemp').append(Math.round(($minTemp-273.15)*10)/10.0 + "도");
	    $('.chightemp').append(Math.round(($maxTemp-273.15)*10)/10.0 + "도");
		$('.chumidity').append($humidity + "%");
		$('.csky').append($sky);
		$('.ctype').append($type);
		$('.cprobability').append($probability + "%");
	});
</script>
		<div style="color: black; text-align: center; margin-bottom: 170px;">

			<div class="csky" style="float: left; width: 120px;">
				<img src="/assets/img/DEIMG/weather11.png"
					style="width: 60px; height: 60px;">
			</div>
			<div class="cprobability" style="float: left; width: 120px;">
				<img src="/assets/img/DEIMG/weather33.jpg"
					style="width: 60px; height: 60px;">
			</div>

			<div class="chumidity" style="width: 120px; float: left;">
				<img src="/assets/img/DEIMG/weather22.jpg"
					style="width: 60px; height: 60px;">
			</div>
			<div class="chightemp"
				style="float: left; width: 120px; margin-left: 8px;">
				<img src="/assets/img/DEIMG/up.png"
					style="width: 60px; height: 60px;">
			</div>

			<div class="clowtemp" style="float: left; width: 120px;">
				<img src="/assets/img/DEIMG/down.png"
					style="width: 60px; height: 60px;">
			</div>

		</div>

	</section>

	<!-- 동물뉴스 -->
	<section class="page-section" id="services">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">NEWS</h2>
			</div>

			<p style="text-align: center">
				<img class="img-fluid" src="../../assets/img/DEIMG/news.jpg"
					style="width: 350px; height: 300px;">
			</p>
			<div class="portfolio-caption">
				<h3 class="section-subheading text-muted"
					style="text-align: center; line-height: 30px;">
					동물전문매체 Happy Pet에서 <br>동물과 관련된 뉴스를 제공합니다.
				</h3>
			</div>


			<iframe src="/DExellent/Title.do" width="351px" height="680px"
				name="Title" id="Title" frameborder="1" scrolling="yes"
				style="overflow-x: hidden" /></iframe>
		</div>
	</section>

	<!-- 동물백과-->
	<section class="page-section" id="about">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">INFORMATION</h2>
				<p style="text-align: center">
					<img class="img-fluid" src="../../assets/img/DEIMG/qwerqwer.jpg"
						style="width: 350px; height: 210px;">
				</p>
				<div class="portfolio-caption">
					<h3 class="section-subheading text-muted"
						style="text-align: center; line-height: 30px;">
						BeMyPet에서 <br>견종백과 정보를 제공받습니다.
					</h3>
				</div>

				<iframe src="/DExellent/Information.do" width="351px" height="680px"
					name="Information" id="Information" frameborder="1" scrolling="yes"
					style="overflow-x: hidden" target="" /></iframe>
			</div>
		</div>
	</section>


	<!-- 강아지 유치원 -->
	<section class="page-section bg-light" id="team">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">KINDERGARTEN</h2>
				<p style="text-align: center">
					<img class="img-fluid" src="../../assets/img/DEIMG/kinder.jpg"
						style="width: 350px; height: 210px;">
				</p>
				<div class="portfolio-caption">
					<h3 class="section-subheading text-muted"
						style="text-align: center; line-height: 30px;">
						Kakao Map에서 <br>강아지 유치원 위치 정보를 제공받습니다.
					</h3>
				</div>

				<iframe src="/DExellent/Kindergarten.do" width="351px"
					height="600px" name="Kindergarten" id="Kindergarten"
					frameborder="1" scrolling="yes" style="overflow-x: hidden"
					target="" /></iframe>
			</div>
		</div>
	</section>

	<!-- 마이페이지 -->
	<section class="page-section" id="mypage">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">MYPAGE</h2>
				<h3 class="section-subheading text-muted2">
					회원님의 카카오 정보를 제공합니다.<br>정보 확인 및 정보 수정이 가능합니다.
				</h3>
			</div>
			<iframe src="/DExellent/mypage.do" width="351px" height="670px"
				name="mypage1" id="mypage1"></iframe>
		</div>
	</section>

	<!-- 게시판 -->
	<section class="page-section" id="contact">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">BOARD</h2>
				<p style="text-align: center">
					<img class="img-fluid" src="../../assets/img/DEIMG/board.jpg"
						style="width: 350px; height: 210px;">
				</p>
				<div class="portfolio-caption">
					<h3 class="section-subheading text-muted2"
						style="text-align: center; line-height: 30px;">
						자유롭게 의견을 나눌 수 있는 <br> 자유게시판 입니다.
					</h3>
				</div>

				<iframe src="/DExellent/board/BoardList.do?Pno=1" width="355px"
					height="630px" name="Board" id="Board" frameborder="1"
					scrolling="yes" style="overflow-x: hidden" /></iframe>
	</section>

	<!-- ETC -->
	<p id="ETC" style="text-align: center">
		<img class="img-fluid" src="../../assets/img/DEIMG/ETC.jpg"
			style="width: 350px;">
	</p>
	<section class="page-section bg-light"
		style="background-image: url('/assets/img/DEIMG/graph-back.png')">

		<!-- 뮤직 플레이어 -->
		<script>
    
        window.addEventListener('load', () =>{
            //NodeList 객체(array가 아니다)에 선택된 모든 노드를 담는다.
            const sounds = document.querySelectorAll(".sound");
            const pads = document.querySelectorAll(".pads div");
            //console.log(sounds);
            const music1/* visual */ = document.querySelector('.music1');
            const music2/* title */ = document.querySelector(".music2")
            const colors = [
                "lightseagreen",
                "rgb(178, 32, 112)",
                "rgb(214, 141, 30)",
                "rgb(64, 55, 196)",
                "rgb(4, 184, 13)",
                "rgb(216, 55, 189)"
            ]

            //재생되는 음악이 끝나면 visual에서 비주얼과 제목을 제거
            sounds.forEach(snd => {
                snd.onended = function(){
                    music1.innerHTML = "";
                    music2.innerHTML = "";
                };
            });

            pads.forEach((pad, index) => {
                pad.addEventListener('click', function(){
                    //기존에 재생되는 음악을 중지 시켜야 한다.
                   sounds.forEach(inx => {
                    //console.log(inx);
                    inx.pause();
                   });
                   if(sounds[index]){
                    sounds[index].currentTime = 0;
                    sounds[index].play();

                    //뮤직 제목을 출력해 준다.
                    //console.log(sounds[index].src) ;
                    const strArray = sounds[index].src.split("sound/");
                    music2.innerHTML = strArray[1];
                   }
                   
                    //볼만들고 애니메이션 하기
                    createBubbles(index);
                });
            });

            const createBubbles = (index) => {
                //기존의 애니메이션 visual을 모두 제거하여 초기화 
                music1.innerHTML = "";
                const bubble = document.createElement("div");
                music1.appendChild(bubble);
                bubble.style.backgroundColor =  colors[index];
                bubble.style.top = '300px';
                bubble.style.animation = 'animation 2000ms linear infinite both';
            }

        });
</script>

		<div class="music">
			<!-- app -->
			<header>
				<h1>Color Tap Music</h1>
			</header>
			<!-- <div class="music1"></div>visual -->
			<p class="music2"></p>
			<!-- title -->
			<div class="pads">
				<div class="pad1">
					<audio class="sound"
						src="/sound/DayBreak-I'llSetFireToYourHeart.mp3"></audio>
				</div>
				<div class="pad2">
					<audio class="sound" src="/sound/THORNAPPLE-Seoul.mp3"></audio>
				</div>
				<div class="pad3">
					<audio class="sound" src="/sound/SiamShade-DontTellLies.mp3"></audio>
				</div>
				<div class="pad4">
					<audio class="sound"
						src="/sound/RomanticPunch-SleepwalkingDisease.mp3"></audio>
				</div>
				<div class="pad5">
					<audio class="sound" src="/sound/BolbbalkanSachungi-Travel.mp3"></audio>
				</div>
				<div class="pad6">
					<audio class="sound" src="/sound/SiamShade-Risk.mp3"></audio>
				</div>
			</div>
		</div>
		<br> <br> <br>

		<!-- 채팅 -->
		<%
			String user_name = (String) session.getAttribute("user_name");
		%>
		<section>
			<br>
			<div class="container">
				<div class="text-center">
					<h2 class="section-heading text-uppercase">Chatting</h2>
				</div>
			</div>
			<div id="_chatbox">
				<!--  style="display: none;" -->
				<fieldset style="text-align: center;">
					<textarea id="messageWindow" rows="6" cols="40" readonly="true"
						style="background-color: #fffbf7;"></textarea>
					<br /> <input id="inputMessage" type="text" onkeyup="enterkey()"
						style="width: 220px; background-color: #fffbf7;" /> <input
						type="submit" value="send" onclick="send()" style="width: 60px;" />
				</fieldset>
			</div>
			<!-- <img class="chat" src="/assets/img/DEIMG/ChatOpen.png" style="width: 85px; height: 60px;"/> -->
			<!-- 		
		<script>
    $(".chat").on({
        "click" : function() {
            if ($(this).attr("src") == "/assets/img/DEIMG/ChatOpne.png") {
                $(".chat").attr("src", "/assets/img/DEIMG/ChatClose.png");
                $("#_chatbox").css("display", "block");
            } else if ($(this).attr("src") == "/assets/img/DEIMG/ChatClose.png") {
                $(".chat").attr("src", "/assets/img/DEIMG/ChatOpne.png");
                $("#_chatbox").css("display", "none");
            }
        }
    });
	</script> -->

			<script type="text/javascript">
        var textarea = document.getElementById("messageWindow");
        var webSocket = new WebSocket('ws://localhost:8080/broadcasting');
        var inputMessage = document.getElementById('inputMessage');
    webSocket.onerror = function(event) {
      onError(event)
    };

    webSocket.onopen = function(event) {
      onOpen(event)
    };

    webSocket.onmessage = function(event) {
      onMessage(event)
    };

    function onMessage(event) {
        textarea.value += "상대 : " + event.data + "\n";
    }

    function onOpen(event) {
        textarea.value += "채팅방에 입장하였습니다.\n";
    }

    function onError(event) {
      alert(event.data);
    }

    function send() {
    	let user_name = '<%=user_name%>'
    	console.log(user_name)
        textarea.value += user_name + " : " + inputMessage.value + "\n";
        webSocket.send(inputMessage.value);
        inputMessage.value = "";
    }
    
    //     엔터키를 통해 send함
    function enterkey() {
        if (window.event.keyCode == 13) {
            send();
        }
    }
    
    //     채팅이 많아져 스크롤바가 넘어가더라도 자동적으로 스크롤바가 내려가게함
    window.setInterval(function() {
        var elem = document.getElementById('messageWindow');
        elem.scrollTop = elem.scrollHeight;
    }, 0);
	</script>
			<br> <br>
		</section>

		<!-- 미니게임 -->
		<section class="page-section" id="services">
			<div class="container">
				<div class="text-center">
					<h2 class="section-heading text-uppercase">Mini Game</h2>
					카드의 짝을 찾는 게임입니다.
				</div>
			</div>
			<!-- 	<iframe src="/DExellent/BlockSelect.do" width="351px" height="450px"
			name="Title" id="Title" frameborder="1" scrolling="yes"
			style="overflow-x: hidden; margin-left: 12px;" /></iframe> -->


			<script>
		var memory_array = ['A','A','B','B','C','C','D','D','E','E','F','F','G','G','H','H','I','I','J','J','K','K','L','L'];
		var memory_values = [];
		var memory_tile_ids = [];
		var tiles_flipped = 0;
		Array.prototype.memory_tile_shuffle = function(){
		    var i = this.length, j, temp;
		    while(--i > 0){
		        j = Math.floor(Math.random() * (i+1));
		        temp = this[j];
		        this[j] = this[i];
		        this[i] = temp;
		    }
		}
		function newBoard(){
			tiles_flipped = 0;
			var output = '';
		    memory_array.memory_tile_shuffle();
			for(var i = 0; i < memory_array.length; i++){
				output += '<div id="tile_'+i+'" onclick="memoryFlipTile(this,\''+memory_array[i]+'\')"></div>';
			}
			document.getElementById('memory_board').innerHTML = output;
		}
		function memoryFlipTile(tile,val){
			if(tile.innerHTML == "" && memory_values.length < 2){
				tile.style.background = '#FFF';
				tile.innerHTML = val;
				if(memory_values.length == 0){
					memory_values.push(val);
					memory_tile_ids.push(tile.id);
				} else if(memory_values.length == 1){
					memory_values.push(val);
					memory_tile_ids.push(tile.id);
					if(memory_values[0] == memory_values[1]){
						tiles_flipped += 2;
						// Clear both arrays
						memory_values = [];
		            	memory_tile_ids = [];
						// Check to see if the whole board is cleared
						if(tiles_flipped == memory_array.length){
							alert("Board cleared... generating new board");
							document.getElementById('memory_board').innerHTML = "";
							newBoard();
						}
					} else {
						function flip2Back(){
						    // Flip the 2 tiles back over
						    var tile_1 = document.getElementById(memory_tile_ids[0]);
						    var tile_2 = document.getElementById(memory_tile_ids[1]);
						    tile_1.style.background = 'url(tile_bg.jpg) no-repeat';
		            	    tile_1.innerHTML = "";
						    tile_2.style.background = 'url(tile_bg.jpg) no-repeat';
		            	    tile_2.innerHTML = "";
						    // Clear both arrays
						    memory_values = [];
		            	    memory_tile_ids = [];
						}
						setTimeout(flip2Back, 700);
					}
				}
			}
		}
		</script>
			<div id="memory_board"></div>
			<script>newBoard();</script>
		</section>

		<!-- 그래프 -->
		<section class="page-section bg-light">
			<div class="container">
				<div class="text-center">
					<h2 class="section-heading text-uppercase">GRAPH</h2>
					<p style="text-align: center">
						<img class="img-fluid" src="../../assets/img/DEIMG/Graph.jpg"
							style="width: 350px; height: 210px;">
					</p>
					<div class="portfolio-caption">
						<h3 class="section-subheading text-muted"
							style="text-align: center; line-height: 30px;">재미로 알아보는 견종
							그래프</h3>

						<iframe src="/DExellent/Graph.do" width="351px" height="600px"
							name="Graph" id="Graph" frameborder="1" scrolling="yes"
							style="overflow-x: hidden" target="" /></iframe>
					</div>
				</div>
			</div>
		</section>
	</section>

	<!-- Footer-->
	<footer class="footer py-4">
		<div class="container">
			<div class="row align-items-center">

				<div class="col-lg-4 text-lg-left">Copyright © Your Website
					2020</div>
				<div class="col-lg-4 my-3 my-lg-0">

					<a class="btn btn-dark btn-social mx-2"
						onclick="alert('(Twitter) 준비중입니다.')"> <i
						class="fab fa-twitter" style="color: white;"></i>
					</a> <a class="btn btn-dark btn-social mx-2"
						onclick="alert('(Facebook) 준비중입니다.')"> <i
						class="fab fa-facebook-f" style="color: white;"></i>
					</a> <a class="btn btn-dark btn-social mx-2"
						onclick="alert('(Instagram) 준비중입니다.')"> <i
						class="fab fa-linkedin-in" style="color: white;"></i>
					</a>

				</div>
				<div class="col-lg-4 text-lg-right">

					<a class="mr-3"
						onclick="alert('강아지는 훌륭하다의 개인보호 정책은 카카오 로그인에 기반을 둡니다.')"
						style="color: black;">Privacy Policy</a> <a class="mr-3"
						onclick="alert('Terms of Use : 강아지를 사용하는 모든 분들입니다.'))"
						style="color: black;">Terms of Use</a>
				</div>
			</div>
		</div>
	</footer>

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
</html>
<%
	}
%>
<!-- 끝! -->