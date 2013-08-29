<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<div id="cover">
		<img src="./img/close.png"
			style="position: absolute; border: 0px; right: 20px; top: 20px; width: 25px; height: 25px; cursor: pointer;"
			id="close" />
	</div>
<div id="form_reg">
	<form id="form_register" class="well form-horizontal">
		<fieldset>
			<legend>用户注册</legend>
			<div class="control-group">
				<label class="control-label" for="username">用户名</label>
				<div class="controls">
					<input type="text" id="username" name="username" required
						minLength='3' maxLength='20' placeholder="输入用户名">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="email">邮箱</label>
				<div class="controls">
					<input type="text" required email='true' id="email" name="email"
						placeholder="输入邮箱">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="role">角色</label>
				<div class="controls">
					<select name="role" id="role">
						<option value="1">普通用户</option>
						<option value="2">回收商</option>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="mobile">手机</label>
				<div class="controls">
					<input type="text" id="mobile" name="mobile" required number
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
				<input type="submit" class="btn" id="reg_button" value="注册" />
			</div>
		</fieldset>
	</form>
</div>