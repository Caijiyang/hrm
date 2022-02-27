
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="zh">
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>CSI员工之家</title>
		<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
		<meta content="width=device-width, initial-scale=1.0" name="viewport" />
		<meta content="" name="description" />
		<meta content="" name="author" />

		<link rel="stylesheet" href="js/metronic/plugins/bootstrap/css/bootstrap.css" />
		<!-- Font Awesome Icons -->
		<link rel="stylesheet" href="login/css/font-awesome.css" />
		<link rel="stylesheet" href="js/metronic/plugins/simple-line-icons/css/simple-line-icons.css"/>
		<link rel="stylesheet" href="login/css/animate.min.css"/>
		<!-- Ionicons -->
		<link href="login/css/ionicons.css" rel="stylesheet" type="text/css" />
		<!-- Skins. Choose a skin from the css/skins
	     folder instead of downloading all of them to reduce the load. -->
		<link href="login/css/_all-skins.css" rel="stylesheet" type="text/css" />
		<!-- Castle 1.0 -->
		<link href="login/css/castle.css" rel="stylesheet" type="text/css" />
		<!-- Theme style -->
		<link href="login/css/castle-main.css" rel="stylesheet" type="text/css" />
		<link href="js/metronic/plugins/layui/css/layui.css" rel="stylesheet" type="text/css" />
		<link href="js/metronic/plugins/scrollbar/perfect-scrollbar.css" rel="stylesheet" type="text/css" />
		<link href="login/css/fontsize.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="login/css/theme.css" />
		<link rel="stylesheet" href="login/css/cover.css" />
		<link rel="stylesheet" href="login/css/step.css" />
		<link href="login/css/login.css" rel="stylesheet" type="text/css">
	    <link rel="stylesheet" href="css/mask.css" />

		<script language="JavaScript">
		var context="/hrm",__contextPath="/hrm",__extendOptions="main/options",__jsPath="/hrm/gdsp/js",__scriptPath="/hrm/gdsp/script";</script>


		<script src="js/jquery-2.1.4.js"></script>
		<script src="js/utils-base.js"></script>

		<script src="js/metronic/plugins/layui/layui.js"></script>
		<script src="js/metronic/plugins/layui/layui-config.js"></script>
		<script src="js/util.js"></script>
		<script src="js/autoheight.js"></script>
		<script src="js/mask.js"></script>


		<script type="text/javascript" src="js/login/lufylegend-1.10.1.simple.min.js"></script>
		<script type="text/javascript" src="js/login/lufylegend.LoadingSample4-0.1.0.min.js"></script>
		<script type="text/javascript" src="js/login/TweenLite.min.js"></script>
		<script type="text/javascript" src="js/login/login.js"></script>
		<script type="text/javascript" src="js/metronic/plugins/jQuery-Storage-API/jquery.storageapi.js" ></script>
		<script type="text/javascript" src="login/js/login.js"></script>
		<script type="text/javascript" src="js/alert.js"></script>


		<script src="js/metronic/plugins/layui/layui.js"></script>
		<script src="js/metronic/plugins/layui/layui-config.js"></script>
		<script src="js/metronic/plugins/jQuery-Storage-API/jquery.storageapi.js"></script>
		<script src="js/metronic/plugins/app/app.js"></script>
	</head>


	<body style="width: 100%;height: 100%;margin: 0;padding: 0; ">
		<div class="bg-canvas">
			<canvas id="demo-canvas" width="100%" height="100%" style="width: 100%;height: 99%;overflow: hidden;"></canvas>
		</div>
		<div class="login-box" id="loginbox">
		    <!-- /.login-logo -->
			<div class="login"  id="main_login" >

				<div class="input_text" >
					<div id="message"style="position:absolute;color:red;margin-top:-20px;margin-left:100px;" > </div>

				  	<!--<form  action="login.d" method="post" onsubmit="return validateLogin()"> -->
					<form action="user" method="post" id="loginForm">
						<%-- 用于标识当前的操作是什么（告诉服务器当前的请求要执行什么操作） --%>
						<input type="hidden" name="operation" value="login"/>
						<div class="content" >
							<div class="navbar-header">
					           	<div>
									<span style="font-family:'Britannic Bold', 'Britannic';color:#3BC5BB;">CSI员工</span>
									<span style="font-family:'Segoe UI Emoji Bold', 'Segoe UI Emoji Normal', 'Segoe UI Emoji';color:#1D4474;"></span>
									<span style="font-family:'Segoe UI Emoji Bold', 'Segoe UI Emoji Normal', 'Segoe UI Emoji';color:#FF9A4C;">之家</span>
								</div>
					        </div>
					        <div class="formitem">
					        	<span class="icon fa fa-user"></span>
					        	<span class="input">
					        		<input id="loginname" name="loginname" value="${loginname == null ? "" : loginname}" class="form-control l35" fv_type="NOTCN" type="text" maxlength="20" placeholder="请输入您的用户名，默认admin" data-toggle="tooltip" data-placement="right">
					        	</span>
					        </div>
					        <div class="formitem">
					        	<span class="icon fa fa-key"></span>
					        	<span class="input">
					        		<input id="password" name="password" value="" class="form-control highlight_green l35" fv_type="NOTCN" type="password"  maxlength="20" placeholder="请输入您的密码，默认abc123"  data-toggle="tooltip" data-placement="right">
					        	</span>
					        </div>
					        <div class="formoperate">
					        	<span class="rememberPwd" >
					        		<input type="checkbox" id="rememberPwd"/><label for="rememberPwd">记住密码</label>
					        	</span>
					        </div>
					        <div >
					        	<button type="buttion" id="login-submit-btn" class="layui-btn  btn-login"  style="width:150px">&nbsp;&nbsp;登　录&nbsp;&nbsp;</button>
					       	    <button type="button" title="刷脸登录" data-search-open="" data-title="刷脸登录" modal-toggle="modal" data-href="face/face.html" data-width="780" data-height="500" class="layui-btn  btn-login"  style="width:150px">&nbsp;&nbsp;刷    脸&nbsp;&nbsp;</button>
						    </div>
						    <div class="formoperate" id="tishi" style="text-align: center;color: red;padding-top: 15px">
								${errormsg == null ? "" : errormsg}
					        </div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>       
        
        
        
        
