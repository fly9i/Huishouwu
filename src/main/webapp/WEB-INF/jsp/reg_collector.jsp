<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>说明</title>
<%@ include file="header.jsp" %>
</head>
<body>
<div style="width:780px;">
<form id="form_register" class="well form-horizontal">
	<fieldset>
		<legend>回收商申请</legend>
		<div class="control-group">
			<label class="control-label" for="corpname">企业名称</label>
			<div class="controls">
				<input type="text" id="corpname" name="corpname" required
					checkuser='true' minLength='3' maxLength='50' placeholder="输入企业名称">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="corpname">企业名称</label>
			<div class="controls">
				<input type="text" id="corpname" name="corpname" required
					checkuser='true' minLength='3' maxLength='50' placeholder="输入企业名称">
			</div>
		</div>
		<div class="controls">
			<input type="submit" class="btn btn-primary" id="apply_corp_button" value="申请" data-loading-text="注册中..."/>
		</div>
	</fieldset>
</form>
</div>
</body>
</html>