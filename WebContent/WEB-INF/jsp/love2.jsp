<%@ page language="java" pageEncoding="GBK"%>
<html lang="en">
		<title>Love You Forever</title>
		<link rel="stylesheet" href="${basePath}static/love/love2/style.css" type="text/css" media="all">
		<!--����-->
		<script type="text/javascript" src="${basePath}static/love/love2/jquery.js"></script>
		<!-- ͼƬ�õ�ƬЧ��-->
		<script type="text/javascript" src="${basePath}static/love/love2/roundabout.js"></script>
		<script type="text/javascript" src="${basePath}static/love/love2/roundabout_shapes.js"></script>
		<script type="text/javascript" src="${basePath}static/love/love2/gallery_init.js"></script>
		<!-- ��ѩ-->
		<script type="text/javascript" src="${basePath}static/love/love2/snowstorm.js"></script>
		<!-- ���ֻ�Ч��-->		
		<script type="text/javascript" src="${basePath}static/love/love2/text.js"></script>
		<!-- ������-->
		<script type="text/javascript" src="${basePath}static/love/love2/ga.js" async=""></script>
		<script type="text/javascript" src="${basePath}static/love/love2/heart.js"></script>
		<!-- �ƶ�div-->
		<script type="text/javascript" src="${basePath}static/love/love2/move.js"></script>
		<!-- ��ʾʱ�� -->
		<script type="text/javascript" src="${basePath}static/love/love2/time.js"></script>
		<!-- �������ָ������ -->
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
				ԭ����ֻ���뵽�����ķ�ʽ�����,<br>
				�鲻֪����һ������,		<br>
				��֪��������,�ʹ��ио���ϲ������,<br>
				ÿ�����������¾����ܿ�������ҷ�����Ϣ,<br>
				ϣ���ܸ������ϼ���,����Ȼ�����ҵ�����.<br>
				�氮����,�һ�úð���.�������ٶ���ѹ��,<br>
				����ǰ���·������,���ܾ�����ʲô,</br>
				���Լ������������¾��Ǻ���һ����������.<br>
				����ĳ��˵������,�������¾���,��������.<br>
				һ���غ���һ��򵥶��԰׵�ɽ�˺��ģ�<br>
				����������ƽ��������ͬ�۹��ã������ĭ.<br>
				��ϣ�������Ǹ�������һֱ����ȥ����.<br>
				�������½ǵļ�ʱ��,����Զ������ȥ,ֱ���������.<br>
				<br>
				-----------------------Just for You, ��<br>
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
			<canvas id="garden" width="110%" height="105%"><c style="color: #FFF; text-shadow:2px 3px 3px #222; font-family:Microsoft YaHei; font-size:50px">����������ʱ��,���Ի����!</c></canvas>
		</div>

		<!-- #time -->
		<div id="time">
			<span id="show"></span>
			<script type="text/javascript">
				var together = new Date();
				together.setFullYear(2015,8,23); 			//ʱ��������
				together.setHours(0);						//Сʱ
				together.setMinutes(0);					//����
				together.setSeconds(0);					//��ǰһλ
				together.setMilliseconds(2);				//��ڶ�λ
				setTimeout("showTime(together)", 1000);
				setInterval(function () {
					showTime(together);
				}, 500);
			</script>
		</div>
</body>