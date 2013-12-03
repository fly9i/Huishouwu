<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../header.jsp"%>
</head>
<body>
<div class="span9">
	<form id="form_content" method="post" action="${mainpath}/admin/upconfig">
	<div class="control-group">
			<label class="control-label" for="title">副标题</label>
			<div class="controls">
				<input type="text" id="tip" name="tip" value="${tipReal}"/>
			</div>
	</div>
	
	<div class="controls">
		<a class="btn btn-primary" id="update_config" onclick="document.forms[0].submit();" data-loading-text="正在更新">更新</a>
	</div>
</form>
</div>
</body>
</html>