function showTime(date) {
	var current = Date();
	var seconds = (Date.parse(current) - Date.parse(date)) / 1000;
	var days = Math.floor(seconds / (3600 * 24));
	seconds = seconds % (3600 * 24);
	var hours = Math.floor(seconds / 3600);
	if (hours < 10) {
		hours = "0" + hours;
	}
	seconds = seconds % 3600;
	var minutes = Math.floor(seconds / 60);
	if (minutes < 10) {
		minutes = "0" + minutes;
	}
	seconds = seconds % 60;
	if (seconds < 10) {
		seconds = "0" + seconds;
	}
	var currentTimeString = "Dear xiaotangtang:<br>"
		+ "I have been in love with you for:<br> "
		+ "&nbsp&nbsp&nbsp&nbsp&nbsp"
		+ "<c style=\"color: #CCFF99; text-shadow:2px 3px 3px #222; font-family:Microsoft YaHei; font-size:24px\" >" + days +" </c>day "
		+ "<c style=\"color: #CCFF99; text-shadow:2px 3px 3px #222; font-family:Microsoft YaHei; font-size:24px\" >" + hours+" </c>hour "
		+"<c style=\"color: #CCFF99; text-shadow:2px 3px 3px #222; font-family:Microsoft YaHei; font-size:24px\" >" + minutes+" </c>min "
		+"<c style=\"color: #CCFF99; text-shadow:2px 3px 3px #222; font-family:Microsoft YaHei; font-size:24px\" >" +seconds+" </c>sec<br>"
		+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp只愿执子之手,与子偕老.<br>"
		+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
		+	"<c style=\"color: #CCCCCC; text-shadow:2px 3px 3px #222; font-family:Microsoft YaHei; font-size:20px\" >----tuziilm   2015-12-16</c>";
	document.getElementById("show").innerHTML=currentTimeString;  //改这地方
}
