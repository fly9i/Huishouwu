<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="header.jsp" %>
<script type="text/javascript" src="${mainpath}/js/news.js"></script>
<script type="text/javascript">
	var tab="news";
</script>
<link rel="stylesheet" href="${mainpath}/css/news.css" />
</head>
<%@ include file="body_header.jsp" %>
<div class="span12">
<ul>
<c:forEach var="n" items="${news }">
<li class="article">
<a href="javascript:;" link="${mainpath }/news/${n.getId()}">${n.getTitle() }</a>
<div class="desc">
作者：${n.getAuthor() } &nbsp;&nbsp;&nbsp;&nbsp;时间：
${n.getTime() }
</div>
</li>
</c:forEach>
</ul>
</div>
<%@ include file="body_footer.jsp" %>
</html>
