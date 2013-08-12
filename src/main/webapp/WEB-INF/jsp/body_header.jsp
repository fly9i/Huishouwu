<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<body>

	<div id="cover">
		<img src="./img/close.png"
			style="position: absolute; border: 0px; right: 20px; top: 20px; width: 25px; height: 25px; cursor: pointer;"
			id="close" />
	</div>
	<%@ include file="body_header_reg.jsp" %>
	<div id="main" class="container">
	<div id="top" class="navbar">
	<div class="navbar-inner">
		<div class="container">
			<a class="brand" href="#">回收DEMO</a>
			<ul class="nav pull-right">
				<li class="dropdown"><a class="dropdown-toggle" role="button"
					data-toggle="dropdown" href="#"> 登陆<b class="caret"></b>
				</a>

					<div class="dropdown-menu dropdown-div" role="menu">
						<form>
							<input type="text" class="input-medium" placeholder="用户名" /> <input
								type="password" class="input-medium" placeholder="密码" />
							<button type="submit" class="btn btn-primary pull-right">提交</button>
						</form>
					</div></li>
				<li><a id="reg" class="dropdown-toggle" data-toggle="dropdown"
					href="#">注册</a></li>
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
				</ul>
			</div>