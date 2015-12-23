<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Love you forever</title>

<style type="text/css">
@font-face {
	font-family: digit;
	src: url('digital-7_mono.ttf') format("truetype");
}
</style>

<link href="../../static/love/css/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="../../static/love/js/jquery.js"></script>
<script type="text/javascript" src="../../static/love/js/garden.js"></script>
<script type="text/javascript" src="../../static/love/js/functions.js"></script>

</head>

<body>

<div id="mainDiv">
	<div id="content">
		<div id="code">
			<span class="comments">/**</span><br />
			<span class="space"/><span class="comments">*2015-09-24</span><br />
			<span class="space"/><span class="comments">*/</span><br />
			Boy name = <span class="keyword">兔子哥哥</span> <br />
			Girl name = <span class="keyword">小汤汤</span> <br />
			<span class="comments">// first~</span><br />
			缤果空间喝奶茶;<br />
			<span class="comments">// second~</span><br />
			北站站前广场憨凉;<br />
			<span class="comments">// third~</span><br />
			KTV里娇羞的萌妹纸;<br />
			<span class="comments">// forth~</span><br />
			搬家打扫时的主妇形象;<br />
			<span class="comments">// fivth~</span><br />
			扎金花的大赢家;<br />
			<span class="comments">// sixth~</span><br />
			<span class="keyword">9.23庆生晚会</span><br />
			年年都是18岁;<br />
			<span class="comments">// Whether it is right now<br />
			<span class="comments">// Still in the distant future.</span><br />
			<span class="placeholder"/>The boy has but one dream;<br />
			<span class="placeholder"/> The boy wants the girl could well have been happy.</span><br />
			<span class="comments">// As time goes on </span><br />
			Some exciting moment will come...<br />
			<br>
			<br>
			I want to say:<br />
			Happy Birthday to xiaotangtang;<br />
		</div>
		<div id="loveHeart">
			<canvas id="garden"></canvas>
			<div id="words">
				<div id="messages">
					小汤汤，这是兔子哥哥给你的生日surprise！
					<div id="elapseClock"></div>
				</div>
				<div id="loveu">
					永远幸福快乐。<br/>
					<div class="signature">- 兔子哥哥</div>
				</div>
			</div>
		</div>
	</div>
	<div id="copyright">
	</div>
</div>

<script type="text/javascript">
var offsetX = $("#loveHeart").width() / 2;
var offsetY = $("#loveHeart").height() / 2 - 55;
var together = new Date();
//together.setUTCFullYear(2015, 8, 23);
together.setFullYear(2015, 8, 23);
together.setHours(0);
together.setMinutes(0);
together.setSeconds(0);
together.setMilliseconds(0);

if (!document.createElement('canvas').getContext) {
	var msg = document.createElement("div");
	msg.id = "errorMsg";
	msg.innerHTML = "Your browser doesn't support HTML5!<br/>Recommend use Chrome 14+/IE 9+/Firefox 7+/Safari 4+"; 
	document.body.appendChild(msg);
	$("#code").css("display", "none")
	$("#copyright").css("position", "absolute");
	$("#copyright").css("bottom", "10px");
	document.execCommand("stop");
} else {
	setTimeout(function () {
		startHeartAnimation();
	}, 5000);
	timeElapse(together);
	setInterval(function () {
		timeElapse(together);
	}, 500);

	adjustCodePosition();
	$("#code").typewriter();
}
</script>

</body>
</html>