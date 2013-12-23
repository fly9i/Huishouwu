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
	$(function() {
		$('.flexslider').flexslider({
			animation : "fade",
			startAt : 0,
			slideshow : true,
			slideshowSpeed : 3000,
			pauseOnHover : false,
			pauseOnAction : true
		});
	});
</script>
<script type="text/javascript" src="${mainpath }/js/news.js"></script>
<link rel='stylesheet' href="${mainpath}/css/flexslider.css" />
</head>
<%@ include file="body_header.jsp"%>

<div class="span7">
	<!-- 
	<div id="slides" class="flexslider"
		style="width: 530px; height: 300px; padding-left: 0px; margin-left: 0px;">
		<ul class="slides">
			<c:forEach var="img" items="${files }">
				<li><img src="${mainpath}/uploads/slideshow/${img.getName()}"
					style="width: 100%; height: 300px;" /></li>
			</c:forEach>
		</ul>
	</div>
	<div style="text-align: center;"></div>
	-->

	<div id="myCarousel" class="carousel slide">
		<ol class="carousel-indicators">
			<c:forEach var="img" items="${files }" varStatus="status">
				<li data-target="#myCarousel" data-slide-to="${status.index }" ${status.index==0?"class='active'":""}></li>
			</c:forEach>
		</ol>
		<!-- Carousel items -->
		<div class="carousel-inner">
			<c:forEach var="img" items="${files }" varStatus="status">
				<div class='item ${status.index==0?"active":"" }'>
					<img src="${mainpath}/uploads/slideshow/${img.getName()}"
						style="width: 100%; height: 300px;" />
				</div>
			</c:forEach>
		</div>
		<!-- Carousel nav -->
		<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
		<a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
	</div>





</div>

<div class="span5">
	<h5>新闻</h5>
	<div>
		<ul class="news_show" style="list-style-type: none;">

			<li id="news_title" style="margin: 5px;">正在加载新闻...</li>

			<li class="pull-right"><a href="news">&gt;&gt;更多</a></li>
		</ul>
	</div>
</div>

<%@ include file="body_footer.jsp"%>
</html>


