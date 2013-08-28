<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<body>

	<div id="cover">
		<img src="./img/close.png"
			style="position: absolute; border: 0px; right: 20px; top: 20px; width: 25px; height: 25px; cursor: pointer;"
			id="close" />
	</div>
	<%@ include file="body_header_reg.jsp"%>
	<div id="main" class="container">
		<div id="top" class="navbar">
			<div class="navbar-inner">
				<div class="container">
					<a class="brand" href="home"> 回收屋--为您生活添色彩 </a>
					<ul class="nav pull-right">
					<li class="dropdown">
							<c:choose >
							<c:when test="${sessionScope.user!=null}">
								<a class="dropdown-toggle" data-toggle="dropdown" href="javascript:;">
								${sessionScope.user.getName()}
								<b class="caret"></b>
								</a>
								<ul class="dropdown-menu">
								<li>
								<a href="#">我的小屋</a>
								</li>
								<li>
								<a href="#">我要回收</a>
								</li>
								<li class="divider"></li>
								<li>
								<a href="javascript:;" id="logout">退出</a>
								</li>
								</ul>
							</c:when>
							<c:otherwise>
								<form id="form_login" class="navbar-form">
									<input id="login_name" name="login_name" type="text"
										class="span2" placeholder="用户名/邮箱/手机" /> <input
										id="login_password" name="login_password" type="password"
										class="span2" placeholder="密码" /> <input type="button"
										id="login_btn" value="登录" class="btn"  data-loading-text="登录中..." />
								</form>
							</c:otherwise>
							</c:choose>
						</li>
						<li>&nbsp; <input type="button" id="reg" class="btn"
							value="注册" href="#">
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="span12">
				<ul id="nav-bar" class="nav nav-tabs" style="margin-bottom: 0px;">
					<li id="index"><a href="./home">首页</a></li>
					<li id="sale_old"><a href="./old">我要卖旧货</a></li>
					<li id="market"><a href="./market">积分换礼</a></li>
					<li id="news"><a href="./news">新闻资讯</a></li>
					<li id="cart"><a href="./cart">回收车</a></li>
				</ul>
			</div>