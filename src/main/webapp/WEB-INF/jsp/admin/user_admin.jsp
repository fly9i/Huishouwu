<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../header.jsp"%>
</head>
<body>
	<c:choose>
		<c:when test="${user!=null && user.getRole()==2 }">
			<div class="span9">
					<form id="form_register" class="well form-horizontal">
						<fieldset>
							<legend>用户注册</legend>
							<div class="control-group">
								<label class="control-label" for="role">角色</label>
								<div class="controls">
									<select id="role" name="role">
										<option value="0">商户</option>
										<option value="1">普通用户</option>
										<option value="2">管理员</option>
									</select>
								</div>
							</div>
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
									<input type="text" required email='true' id="email"
										name="email" placeholder="输入邮箱">
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
									<input type="password" id="password_2" name="password_2"
										required equalTo='#password1' placeholder="再次输入密码">
								</div>
							</div>
							<div class="controls">
								<input type="submit" class="btn" id="reg_button" value="注册" />
							</div>
						</fieldset>
					</form>
				</div>
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