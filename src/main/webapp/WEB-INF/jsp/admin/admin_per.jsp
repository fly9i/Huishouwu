<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../header.jsp" %>
<script type="text/javascript" src="${mainpath}/js/admin.js"></script>
</head>
<%@ include file="../body_header_reg.jsp"%>
<%@ include file="../body_top.jsp" %>
<c:choose>
<c:when test="${user!=null && user.getRole()==2 }">
<div class="span2">
<ul id="admin_list" class="nav nav-list">
  <li class="nav-header">订单检索</li>
  <li class="active"><a href="#" class="adminnav" link="${mainpath}/admin/user">订单管理</a></li>
</ul>
</div>
<div class="span10">
<iframe id="admin_frame" src="${mainpath}/admin/user" style="width:100%;height:500px;border:0px;padding:0px;margin:0px;" frameborder="0">
</iframe>

</div>
</c:when>
<c:otherwise>
<div class="span12" style="text-align:center"><h4>请先以管理员身份登录。</h4></div>
<script type="text/javascript">
	alert("请先以管理员身份登录。");
</script>
</c:otherwise>
</c:choose>


<%@ include file="../body_footer.jsp" %>
</html>