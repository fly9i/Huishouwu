<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>说明</title>
<%@ include file="header.jsp" %>
<script type="text/javascript" src="${mainpath}/js/collector.js"></script>
</head>
<body>
<div style="width:780px;">
<form id="form_apply" class="well form-horizontal">
	<fieldset>
		<legend>回收商申请</legend>
		<div class="control-group">
			<label class="control-label" for="corpname">企业名称</label>
			<div class="controls">
				<input type="text" id="corpname" name="corpname" required minLength='3' maxLength='50' placeholder="输入企业名称">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="corpsize">企业规模</label>
			<div class="controls">
				<select id="corpsize" name="corpsize" required>
					<option>请选择</option>
					<option value="1">10人以下</option>
					<option value="2">10-50（含）人</option>
					<option value="3">50-100（含）人</option>
					<option value="4">100-500（含）人</option>
					<option value="5">500人以上</option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="corpowner">负责人</label>
			<div class="controls">
				<input type="text" id="corpowner" name="corpowner" required minLength='2' maxLength='50' placeholder="负责人">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="corpphone">联系电话</label>
			<div class="controls">
				<input type="text" id="corpphone" name="corpphone" required minLength='3' maxLength='50' placeholder="联系电话">
			</div>
		</div>
		<!-- 
		<div class="control-group">
			<label class="control-label" for="corplicense">企业法人营业执照</label>
			<div class="controls">
				<input type="file" id="corplicense" name="corplicense" required placeholder="企业法人营业执照">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="corpownerid1">负责人身份证（正面）</label>
			<div class="controls">
				<input  type="file"  id="corpownerid1" name="corpownerid1" required placeholder="负责人身份证（正面）">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="corpownerid2">负责人身份证（背面）</label>
			<div class="controls">
				<input type="file" id="corpownerid2" name="corpownerid2" required placeholder="负责人身份证（背面）">
			</div>
		</div>
		-->
		<div class="control-group">
			<label class="control-label" for="corpshow">营业状况展示</label>
			<div class="controls">
				<input type="file" id="corpshow" name="corpshow" placeholder="负责人身份证（背面）">
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="showsite">展示网址</label>
			<div class="controls">
				<input type="text" id="showsite" name="showsite" minLength='8' maxLength='50' placeholder="展示网址">
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