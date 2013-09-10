<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="header.jsp" %>

</head>
<%@ include file="body_header_reg.jsp"%>
<%@ include file="body_top.jsp"%>
<div class="span12">
<div style="text-align:center;"><h3>${news.getTitle() }</h3></div>

<div style="font:10px grey;text-align:center;">作者：${news.getAuthor() } &nbsp;&nbsp;&nbsp;&nbsp;时间：${news.getTime() }</div>
<div>
${news.getContent()}
</div>
</div>
<%@ include file="body_footer.jsp" %>
</html>