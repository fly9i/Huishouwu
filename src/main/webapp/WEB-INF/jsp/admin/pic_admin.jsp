<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../header.jsp"%>
<script type="text/javascript" src="${mainpath}/js/admin/pic.js"></script>
</head>
<body>
<div class="span9">
<form id="form-upload" method="post" action="./upimg" enctype="multipart/form-data">
	<div class="control-group">
			<label class="control-label" for="file">上传图片</label>
			<div class="controls">
				<input type="file" name="file" id="file" placeholder="选择上传的图片"/>
			</div>
	</div>
	<div class="controls">
		<a class="btn btn-primary" id="upload_pic" >上传</a>
	</div>
</form>
<div>
<table class="table">
<tr><td>id</td><td>图片</td><td>创建时间</td><td>删除</td></tr>
<c:forEach var="pic" items="${pics}" varStatus="status">
<tr><td>${status.index+1 }</td>
<td><img style="width:100px;" src="${mainpath}/uploads/slideshow/${pic.getName()}"/></td>
<td>${pic.getCreateAt() }</td>
<td><a class="btn btn-danger del_pic" href="javascript:;"  pic_id="${pic.getId() }" data-loading-text="正在删除">删除</a></td>
</tr>
</c:forEach>
</table>
</div>
</div>
</body>
</html>