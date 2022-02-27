<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="icon" href="static/css/default/icon.png" type="image/x-icon" />
		<title>CSI员工之家</title>

		
		<link rel="stylesheet" href="login/css/font-awesome.min.css" />
		<link rel="stylesheet" href="js/metronic/plugins/simple-line-icons/css/simple-line-icons.css" />
		<link rel="stylesheet" href="js/metronic/plugins/animate.css/animate.min.css" />
		<link rel="stylesheet" href="js/metronic/plugins/toastr/toastr.min.css" />
		<link rel="stylesheet" href="js/metronic/plugins/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="js/metronic/plugins/app.css" />
		<link rel="stylesheet" href="js/metronic/plugins/theme.css" data-type="theme" />
		<link rel="stylesheet" href="js/metronic/plugins/cover.css" />
		<link rel="stylesheet" href="js/metronic/plugins/step.css" />
		<link rel="stylesheet" href="js/metronic/plugins/tabs/tabs.css" />
	
	</head>

	<body class="layout-fixed">
		<script language="JavaScript">
			var context="/hrm",__contextPath="/hrm",__extendOptions="main/options";
		</script>
		<div fragment="navbar" class="wrapper" role="navigation">
			<script type="text/javascript">
				var module = "09282248e3ed4fb4a7d3c1c8585a7eb0";
			</script>
			<nav class="navbar topnavbar" style="min-height:44px;" role="navigation" >
				<div class="navbar-header" style="width:20%;text-align: left;">
					<span class="navbar-brand navbar-brand-h" >
				
					   
		        		<div class="brand-text" style="padding-top: 3px;">
		        		    <img alt="" src="images/main/head/logo/LOGO.jpg">
							<span style="font-family:'Britannic Bold', 'Britannic';color:#3BC5BB;">CSI员工</span>
							<span style="font-family:'Segoe UI Emoji Bold', 'Segoe UI Emoji Normal', 'Segoe UI Emoji';color:#1D4474;"> </span>
							<span style="font-family:'Segoe UI Emoji Bold', 'Segoe UI Emoji Normal', 'Segoe UI Emoji';color:#FF9A4C;">之家</span>
						</div>
					</span>
				</div>

				<div class="sysMenu" style="display: none;">
					<ul>

					</ul>
				</div>
		<div class="nav-wrapper">
			<ul class="nav navbar-nav navbar-right">
				<li>
					<a>
						<span class="user-name-csdc">欢迎登录：${sessionScope.user.username}</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<img src="images/StatBar_time.gif">&nbsp;&nbsp;<span id="nowTime"></span>
					</a>
				</li>
		
				<li>
					<a class="menu-icons" title="修改密码" data-search-open="" data-title="修改密码" modal-toggle="modal" data-href="editPassword/editPassword.jsp" data-width="780" data-height="300">
						<em class="fa fa-gear main-op" style="font-size:13px">修改密码</em>
					</a>
				</li>
				<li>
					<a class="menu-icons" title="人脸注册" data-search-open="" data-title="人脸注册" modal-toggle="modal" data-href="user/face.jsp" data-width="780" data-height="500">
						<em class="fa fa-gear main-op" style="font-size:13px">人脸注册</em>
					</a>
				</li>
				<li class="dropdown">
					<a class="menu-icons" title="退出登录" data-no-persist="true" href="logout.jsp">
						<!--<a class="menu-icons" title="退出登录" data-no-persist="true" href="logout.d">-->
						<em class="fa fa-power-off  main-op" style="font-size:13px">退出登录</em>
					</a>
				</li>
			</ul>
		</div>
		</nav>
		
		<aside class="aside">
		
			<div class="aside-inner">
			 
				<nav class="sidebar" data-sidebar-anyclick-close="">
				
					<ul class="nav" id="leftMenuNav">
						
					</ul>
				
				</nav>
				
			</div>
				
		</aside>
	
	
		<section id="content">
			<div id="funTab"></div>
		</section>
			
		</div>
		
		<script src="js/metronic/plugins/sidebar/sidebar.js"></script>
		<script src="js/metronic/plugins/jquery/dist/jquery.js"></script>
		<script src="js/metronic/plugins/jquery.form/jquery.form.min.js"></script>
		<script src="js/metronic/plugins/jquery/plugins/scrollbar/perfect-scrollbar.jquery.min.js"></script>
		<script src="js/metronic/plugins/layui/layui.js"></script>
		<script src="js/metronic/plugins/layui/layui-config.js"></script>
		<script src="js/metronic/plugins/bootstrap/dist/js/bootstrap.js"></script>
		<script src="js/metronic/plugins/modernizr/modernizr.custom.js"></script>
		<script src="js/metronic/plugins/jQuery-Storage-API/jquery.storageapi.js"></script>
		<script src="js/metronic/plugins/parsleyjs/dist/parsley.min.js"></script>
		<script src="js/metronic/plugins/toastr/toastr.min.js"></script>
		
		<script src="js/metronic/plugins/common/util.js"></script>
		<script src="js/metronic/plugins/app/app.js"></script>
		<script src="js/metronic/plugins/app/menu.js"></script>
		<script src="js/metronic/plugins/common/autoheight.js"></script>
		<script type="text/javascript" src="js/fkjava_timer.js"></script>
	<script type="text/javascript">
		    /** 文档加载完成后立即执行的方法 */
		    // var weeks = new Array();
			
		    $(function(){
		    	$("#nowTime").runTimer();
		
			})
			
		    
		    
		</script>
	</body>

</html>