<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form id="form_dn" class="well form-horizontal">
	<fieldset>
		<legend>
			<img style="height: 30px;" src="img/dn.jpg" />&nbsp;电脑
		</legend>
		<div class="control-group">
			<label class="control-label">描述</label>
			<div class="controls">
				<label class="radio"> <input type="radio" name="bx_desc" 
					value="150-190" checked/>主机+CRT显示器
				</label>
				<label class="radio"> <input type="radio" name="dn_desc"
					value="190-240"/>主机+液晶显示器
				</label> 
				<label class="radio"> <input type="radio" name="dn_desc"
					value="240gt"/>主机
				</label> 
				<label class="radio"> <input type="radio" name="dn_desc"
					value="bg"/>液晶显示器
				</label>
				<label class="radio"> <input type="radio" name="dn_desc"
					value="bg"/>CRT显示器
				</label>
				<label class="radio"> <input type="radio" name="dn_desc"
					value="bg"/>笔记本电脑
				</label>
				<label class="radio"> <input type="radio" name="dn_desc"
					value="bg"/>液晶显示器
				</label>
			</div>
			<div class="control-group">
				<label class="control-label" for="count">数量</label>
				<div class="controls">
					<input type="text" name="count" value="1" id="count" placeholder="数量" />
				</div>
			</div>
		</div>

		<div class="control-group">
			<input type="button" value="添加电脑" addto="dn" class="btn controls add" />

		</div>

	</fieldset>
</form>