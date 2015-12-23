<%@include file="./include/common.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>联系兔兔</title>
  <link href="${basePath}static/love/qq_erweima/css/css.css" rel="stylesheet" type="text/css" />
</head>
<body style="height:2000px;">
<center>
  <c:import url="lottery.jsp">
  </c:import>
</center>
<div class="main-im">
  <div id="open_im" class="open-im"></div>
  <div class="im_main" id="im_main">
    <div id="close_im" class="close-im"><a href="javascript:void(0);" title="点击关闭"></a></div>
    <a href="tencent://message/?uin=568644820&Site=fdfdf&Menu=yes" class="im-qq qq-a" title="在线QQ客服">
      <div class="qq-container"></div>
      <div class="qq-hover-c"><img class="img-qq" src="${basePath}static/love/qq_erweima/images/qq.png"></div>
      <span> QQ在线咨询</span> </a>
    <div class="im-tel">
      <div>兔兔24h热线</div>
      <div class="tel-num">520-1314</div>
      <div><a target="_self" href="http://user.wxad2014.com:7758/love">surprise</a></div>
    </div>
    <div class="im-footer" style="position:relative">
      <div class="weixing-container">
        <div class="weixing-show">
          <div class="weixing-txt">获得更多惊喜</div>
          <img class="weixing-ma" src="${basePath}static/love/qq_erweima/images/liantu.png">
          <div class="weixing-sanjiao"></div>
          <div class="weixing-sanjiao-big"></div>
        </div>
      </div>
      <div class="go-top"><a href="javascript:;" title="返回顶部"></a> </div>
      <div style="clear:both"></div>
    </div>
  </div>
</div>
<script src="${basePath}static/jquery/jquery-1.8.3.min.js"></script>
<script>
  $(function(){
    $('#close_im').bind('click',function(){
      $('#main-im').css("height","0");
      $('#im_main').hide();
      $('#open_im').show();
    });
    $('#open_im').bind('click',function(e){
      $('#main-im').css("height","272");
      $('#im_main').show();
      $(this).hide();
    });
    $('.go-top').bind('click',function(){
      $(window).scrollTop(0);
    });
    $(".weixing-container").bind('mouseenter',function(){
      $('.weixing-show').show();
    })
    $(".weixing-container").bind('mouseleave',function(){
      $('.weixing-show').hide();
    });
  });
</script>
</body>
</html>
