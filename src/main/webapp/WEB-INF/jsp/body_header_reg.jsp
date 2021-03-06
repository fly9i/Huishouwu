<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<div id="cover">
		<img src="${mainpath}/img/close.png"
			style="position: absolute; border: 0px; right: 20px; top: 20px; width: 25px; height: 25px; cursor: pointer;"
			id="close" />
	</div>
<div id="form_reg" class="pop">
	<form id="form_register" class="well form-horizontal">
		<fieldset>
			<legend>用户注册</legend>
			<div class="control-group">
				<label class="control-label" for="username">用户名</label>
				<div class="controls">
					<input type="text" id="username" name="username" required
						checkuser='true' minLength='3' maxLength='20' placeholder="输入用户名">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="email">邮箱</label>
				<div class="controls">
					<input type="text" required checkemail='true' email='true' id="email" name="email"
						placeholder="输入邮箱">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="mobile">手机</label>
				<div class="controls">
					<input type="text" id="mobile" name="mobile" required checkmobile='true' number='true'
						placeholder="输入手机号码">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="password1">密码</label>
				<div class="controls">
					<input type="password" id="password1" name="password1" required
						minLength='6' maxLength='50' placeholder="输入密码">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="password_2">重复密码</label>
				<div class="controls">
					<input type="password" id="password_2" name="password_2" required
						equalTo='#password1' placeholder="再次输入密码">
				</div>
			</div>
			<div class="controls">
				<input type="submit" class="btn" id="reg_button" value="注册" data-loading-text="注册中..."/>
			</div>
		</fieldset>
	</form>
</div>

<div id="apply_des" class="pop">
	<iframe src="${mainpath}/desc" frameborder=0 style="border:0px;margin:0px;padding:0px;width:100%;height:100%;">
	</iframe>
</div>
