<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="main" class="container">
	<div id="top" class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<a class="brand" href="${mainpath }">
					回收屋&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 12px;">${tip}</span>
				</a>
				<ul class="nav pull-right">
					<c:choose>
						<c:when test="${sessionScope.user!=null}">
							<li class="dropdown"><a class="dropdown-toggle"
								data-toggle="dropdown" href="javascript:;">
									${sessionScope.user.getName()} <b class="caret"></b>
							</a>
								<ul class="dropdown-menu">
									<li><a href="#">我的小屋</a></li>
									<li><a href="#">我要回收</a></li>
									<li class="divider"></li>
									<li><a href="javascript:;" id="logout">退出</a></li>
								</ul></li>
						</c:when>
						<c:otherwise>
							<li class="dropdown">
								<form id="form_login" class="navbar-form">
									<input id="login_name" name="login_name" type="text"
										class="span2" placeholder="用户名/邮箱/手机" /> <input
										id="login_password" name="login_password" type="password"
										class="span2" placeholder="密码" /> <input type="button"
										id="login_btn" value="登录" class="btn"
										data-loading-text="登录中..." />
								</form>
							</li>

							<li>&nbsp; <input type="button" id="reg" class="btn"
								value="注册">
							</li>
							<li>&nbsp;<input type="button" id="apply"
								class="btn  btn-primary" value="申请成为注册商" /></li>
						</c:otherwise>
					</c:choose>
					<li><a href="${mainpath}/cart"><span
							class="label  label-info">回收车</span></a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="row">