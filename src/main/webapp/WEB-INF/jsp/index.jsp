<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="header.jsp"%>

<script type="text/javascript">
	var tab = "index";
</script>
<script type="text/javascript"
	src="${mainpath}/js/jquery.flexslider-min.js"></script>

<script type="text/javascript">
$(function(){
	$('.flexslider').flexslider({
		animation: "fade",
		startAt: 0,
		slideshow: true,
		slideshowSpeed: 3000,
		pauseOnHover: false,
		pauseOnAction: true
	});
});
</script>
<link rel='stylesheet' href="${mainpath}/css/flexslider.css" />
</head>
<%@ include file="body_header.jsp"%>

<div class="span7"
	style="height: 500px;">
	<div id="slides" class="flexslider"
		style="width: 530px; height: 300px; padding-left: 0px; margin-left: 0px;">
		<!-- Place somewhere in the <body> of your page -->
		<ul class="slides">
		<c:forEach var="img" items="${files }">
			<li><img src="${mainpath}/uploads/slideshow/${img}" style="width:100%;height:300px;"/></li>
		</c:forEach>
		</ul>
	</div>
</div>
<div class="span5" style="height: 500px; background-color: blue;">
asdfasdfasda<br/>
fasdfasd<br/>
fasd<br/>
fas<br/>
<div class="pull-right">
<a href="news">>>更多</a>
</div>
</div>
<%@ include file="body_footer.jsp"%>
</html>


