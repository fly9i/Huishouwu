<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="header.jsp" %>
<script type="text/javascript">
	var tab="news";
</script>
</head>
<%@ include file="body_header.jsp" %>
<div class="span12">
<ul>
<c:forEach var="n" items="${news }">
<li>
<a href="${mainpath }/news/${n.getId()}">${n.getTitle() }</a>
</li>
</c:forEach>
</ul>
</div>
<%@ include file="body_footer.jsp" %>
</html>
