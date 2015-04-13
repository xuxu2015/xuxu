<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String msg = (String) request.getAttribute("loginFailed");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>登录</title>
		<script type="text/javascript" src="<%=path%>/js/jquery-2.1.1.js"></script>
		<script type="text/javascript" src="<%=path%>/js/login/login.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/normalize.css">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/login/login.css">
	</head>
	<body>
		<div align="center" id="bg">
			<div id="logo">
			</div>
			<div id="content">
				<div id="left">
					<img src="<%=path%>/img/bg/a1.jpg" >
				</div>
				<div id="center">
					<div id="c_title">
						用户登录
					</div>
					<div id="login">
						<form action="<%=path%>/login/login.action" method="post">
							<ul>
								<li>
									用户名：
									<input name="user.name" />
								</li>
								<li>
									密&nbsp;&nbsp;码：
									<input type="password" name="user.pwd" />
								</li>
								<li>
									验证码：
									<input size="10" id="verifyCode" name="verifyCode"/>
									<span><img src="<%=path%>/common/verifyCode" id='img_verifyCode'/></span>
								</li>
							</ul>
							<p id="msg">${msg}</p>
							<input type="submit" value="登录" />
							<input id="btn_register" type="button" value="注册"/>

						</form>
					</div>
				</div>
				<div id="right"></div>
			</div>
			<%@include file="/jsp/common/bottom.jsp"%>
		</div>
	</body>
</html>