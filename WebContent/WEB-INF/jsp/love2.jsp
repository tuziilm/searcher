<%@ page language="java" pageEncoding="GBK"%>
<html lang="en">
		<title>Love You Forever</title>
		<link rel="stylesheet" href="${basePath}static/love/love2/style.css" type="text/css" media="all">
		<!--导库-->
		<script type="text/javascript" src="${basePath}static/love/love2/jquery.js"></script>
		<!-- 图片幻灯片效果-->
		<script type="text/javascript" src="${basePath}static/love/love2/roundabout.js"></script>
		<script type="text/javascript" src="${basePath}static/love/love2/roundabout_shapes.js"></script>
		<script type="text/javascript" src="${basePath}static/love/love2/gallery_init.js"></script>
		<!-- 下雪-->
		<script type="text/javascript" src="${basePath}static/love/love2/snowstorm.js"></script>
		<!-- 打字机效果-->		
		<script type="text/javascript" src="${basePath}static/love/love2/text.js"></script>
		<!-- 画爱心-->
		<script type="text/javascript" src="${basePath}static/love/love2/ga.js" async=""></script>
		<script type="text/javascript" src="${basePath}static/love/love2/heart.js"></script>
		<!-- 移动div-->
		<script type="text/javascript" src="${basePath}static/love/love2/move.js"></script>
		<!-- 显示时间 -->
		<script type="text/javascript" src="${basePath}static/love/love2/time.js"></script>
		<!-- 设置文字跟随鼠标 -->
		<script type="text/javascript" src="${basePath}static/love/love2/makesnake.js"></script>
		</head>
		<body>
		<!-- thanks for watching! -->
		<span id="span0" class="spanstyle">L</span><span id="span1" class="spanstyle">o</span>
		<span id="span2" class="spanstyle">v</span><span id="span3" class="spanstyle">i</span>
		<span id="span4" class="spanstyle">n</span><span id="span5" class="spanstyle">g</span><span id="span6" class="spanstyle">
		 </span><span id="span7" class="spanstyle">Y</span><span id="span8" class="spanstyle">o</span><span id="span9" class="spanstyle">u</span>
		 <span id="span10" class="spanstyle"> </span><span id="span11" class="spanstyle">f</span>
		 <span id="span12" class="spanstyle">o</span><span id="span13" class="spanstyle">r</span><span id="span14" class="spanstyle">e</span><span id="span15" class="spanstyle">h</span>
		 <span id="span16" class="spanstyle">v</span><span id="span17" class="spanstyle">e</span><span id="span18" class="spanstyle">r</span><span id="span19" class="spanstyle">!</span>

		<script type="text/javascript">
			setTimeout(makesnake, 1000);
		</script>
		<!--#Header-->
		<div id="header" style="opacity:0.85">
			<div id="title">
				I Love You Forever
			</div>
		</div>

		<!-- #myContent -->
		<div id="myContent">
				<span id="blink" style="display: none;">_</span></div>
		<div id="contentToWrite" style="display:none">
				原谅我只能想到这样的方式来表白,<br>
				情不知所起一往而深,		<br>
				不知从哪天起,就从有感觉到喜欢你了,<br>
				每天最奢望的事就是能看到你给我发的消息,<br>
				希望能跟你聊上几句,你已然成了我的世界.<br>
				真爱来了,我会好好把握.不管面临多大的压力,<br>
				不管前面的路如何崎岖,不管经历过什么,</br>
				我仍坚信最浪漫的事就是和你一起慢慢变老.<br>
				正如某人说的那样,但求岁月静好,现世安稳.<br>
				一生守候不是一句简单而苍白的山盟海誓，<br>
				而是无数个平淡的日子同舟共济，相濡以沫.<br>
				我希望你是那个能陪我一直走下去的人.<br>
				相信右下角的计时器,将永远继续下去,直至数据溢出.<br>
				<br>
				-----------------------Just for You, 汾<br>
		</div>
		<script type="text/javascript">
			writeContent(true);
		</script>
		<audio id="music" preload="none">
			<source src="http://7xp889.com1.z0.glb.clouddn.com/See%20You%20Again.mp3" type="audio/mp3" />
		</audio>
		<script>
			var win = ($.browser.msie) ? document : window;
			$(win).click(function(){
				play();
			});
			function play(){
				var audio = document.getElementById('music');
				audio.play();
				var onEnded = function() {
					this.play();
				};
				audio.addEventListener('ended', onEnded, false);
			}
			$(function(){
				play();
			});
		</script>
		<!-- #container -->
		<div id="container">
			<ul id="myRoundabout" class="roundabout-holder" style="padding: 0px; position: relative; z-index: 100;">
		  	<li class="roundabout-moveable-item roundabout-in-focus" current-scale="1.0000" style="position: absolute; left: 122px; top: 87px; width: 350px; height: 222px; opacity: 1; z-index: 400; font-size: 16px;"><img src="${basePath}static/love/love2/11.jpg" alt=""></li>
			<li class="roundabout-moveable-item" current-scale="0.7927" style="position: absolute; left: -0.4px; top: 110px; width: 277.445px; height: 175.9794px; opacity: 1; z-index: 296; font-size: 12.68px;"><img src="${basePath}static/love/love2/10.jpg" alt=""></li>
			<li class="roundabout-moveable-item" current-scale="0.4573" style="position: absolute; left: 473.8px; top: 147.2px; width: 160.055px; height: 101.5206px; opacity: 1; z-index: 129; font-size: 7.32px;"><img src="${basePath}static/love/love2/9.jpg" alt=""></li>
			<li class="roundabout-moveable-item" current-scale="0.4573" style="position: absolute; left: -39.8px; top: 147.2px; width: 160.055px; height: 101.5206px; opacity: 1; z-index: 129; font-size: 7.32px;"><img src="${basePath}static/love/love2/8.jpg" alt=""></li>
			<li class="roundabout-moveable-item" current-scale="0.7927" style="position: absolute; left: 317px; top: 110px; width: 277.445px; height: 175.9794px; opacity: 1; z-index: 296; font-size: 12.68px;"><img src="${basePath}static/love/love2/7.jpg" alt=""></li>
		  </ul>
		</div>
		<script type="text/javascript">
			setTimeout(move, 1000);
		</script>
  		
  		<!-- #bg -->
		<div id="bg">
			<canvas id="garden" width="110%" height="105%"><c style="color: #FFF; text-shadow:2px 3px 3px #222; font-family:Microsoft YaHei; font-size:50px">你的浏览器过时了,试试火狐吧!</c></canvas>
		</div>

		<!-- #time -->
		<div id="time">
			<span id="show"></span>
			<script type="text/javascript">
				var together = new Date();
				together.setFullYear(2015,8,23); 			//时间年月日
				together.setHours(0);						//小时
				together.setMinutes(0);					//分钟
				together.setSeconds(0);					//秒前一位
				together.setMilliseconds(2);				//秒第二位
				setTimeout("showTime(together)", 1000);
				setInterval(function () {
					showTime(together);
				}, 500);
			</script>
		</div>
</body>