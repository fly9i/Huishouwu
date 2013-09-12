<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../header.jsp"%>

<script type="text/javascript"
	src="${mainpath}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${mainpath}/ueditor/ueditor.all.js"></script>
</head>
<body>
<c:choose>
<c:when test="${user!=null && user.getRole()==2 }">
<div class="span9">
<form id="form_news" class="form-horizontal" action="${mainpath}/news/add" method="post">
	<fieldset>
		
		<div class="control-group">
			<label class="control-label" for="title">标题</label>
			<div class="controls">
				<input type="text" id="title" name="title" required minLength='3'
					maxLength='150' placeholder="输入标题">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="author">作者</label>
			<div class="controls">
				<input type="text" id="author" name="author" required minLength='3'
					maxLength='150' placeholder="输入作者">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="content">作者</label>
			<div class="controls">
				<textarea id="content" name="content" ></textarea>
			</div>
		</div>
		<div class="controls" >
			<input type="submit" class="btn" id="addnews" value="提交" />
		</div>
	</fieldset>
</form>
</div>
<script type="text/javascript">
	var editor = new UE.ui.Editor();
	editor.render("content");
	//1.2.4以后可以使用一下代码实例化编辑器
	//UE.getEditor('myEditor')
	
</script>
</c:when>
<c:otherwise>
<script type="text/javascript">
	alert("管理界面，请先登录");
	window.location.href="${mainpath}";
</script>
</c:otherwise>
</c:choose>
</body>
</html>