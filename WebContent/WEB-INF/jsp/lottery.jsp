<%@include file="./include/common.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>jquery九宫格抽奖转盘插件lottery</title>
	<style type="text/css">
		#lottery{width:574px;height:584px;margin:20px auto 0;background:url(${basePath}static/love/surprise/images/bg.jpg) no-repeat;padding:50px 55px;}
		#lottery table td{width:142px;height:142px;text-align:center;vertical-align:middle;font-size:24px;color:#333;font-index:-999}
		#lottery table td a{width:284px;height:284px;line-height:150px;display:block;text-decoration:none;}
		#lottery table td.active{background-color:#ea0000;}
		#demo{width:300px; margin:50px auto 10px; overflow:hidden;}
		#demo a{float:left; width:90px; height:30px; line-height:30px; text-align:center; margin-left:10px; background-color:#000; color:#fff; text-decoration:none; font-weight:bold;}
		#demo a.cur{background-color:#933;}
	</style>
</head>
<body>
<div id="lottery">
	<table border="0" cellpadding="0" cellspacing="0">
		<tr class="lottery-group">
			<td class="lottery-unit"><img src="${basePath}static/love/surprise/images/1.png" /></td>
			<td class="lottery-unit"><img src="${basePath}static/love/surprise/images/2.png" /></td>
			<td class="lottery-unit"><img src="${basePath}static/love/surprise/images/4.png" /></td>
            <td class="lottery-unit"><img src="${basePath}static/love/surprise/images/3.png" /></td>
		</tr>
		<tr class="lottery-group">
			<td class="lottery-unit"><img src="${basePath}static/love/surprise/images/7.png" /></td>
			<td colspan="2" rowspan="2"><a href="#"></a></td>
			<td class="lottery-unit"><img src="${basePath}static/love/surprise/images/5.png" /></td>
		</tr>
		<tr class="lottery-group">
			<td class="lottery-unit"><img src="${basePath}static/love/surprise/images/1.png" /></td>
			<td class="lottery-unit"><img src="${basePath}static/love/surprise/images/6.png" /></td>
		</tr>
        <tr class="lottery-group">
			<td class="lottery-unit"><img src="${basePath}static/love/surprise/images/3.png" /></td>
			<td class="lottery-unit"><img src="${basePath}static/love/surprise/images/6.png" /></td>
			<td class="lottery-unit"><img src="${basePath}static/love/surprise/images/8.png" /></td>
            <td class="lottery-unit"><img src="${basePath}static/love/surprise/images/7.png" /></td>
		</tr>
	</table>
</div>
<script type="text/javascript" src="${basePath}static/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${basePath}static/love/surprise/js/lottery.js"></script>
<script type="text/javascript">
	window.onload = function () {
		lottery.lottery({
			selector:     '#lottery',
			width:        4,
			height:       4,
			index:        0,    // 初始位置
			initSpeed:    500,  // 初始转动速度
			// upStep:       50,   // 加速滚动步长
			// upMax:        50,   // 速度上限
			// downStep:     30,   // 减速滚动步长
			// downMax:      500,  // 减速上限
			// waiting:      5000, // 匀速转动时长
			beforeRoll: function () { // 重写滚动前事件：beforeRoll
				// console.log(this);
				// alert(1);
			},
			beforeDown: function () { // 重写减速前事件：beforeDown
				// console.log(this);
				// alert(1);
			},
		});
	}
</script>
</body>
</html>