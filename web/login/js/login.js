var uk ="_uk_",pk="_pk_",rk="_rk_";
$(function(){
	layui.use("layer");
	$("#loginname").focus();
	$("#loginname").val($.localStorage.get(uk));
	$("#password").val($.localStorage.get(pk));
	if($.localStorage.get(rk)){
		$("#rememberPwd").attr("checked",true);
	}
	    /** 按了回车键 */
   $(document).keydown(function(event){
	   if(event.keyCode == 13){
		   $("#login-submit-btn").trigger("click");
	   }
   })

   /** 给登录按钮绑定点击事件  */
   $("#login-submit-btn").on("click",function(){
	   /** 校验登录参数 ctrl+K */
	   var loginname = $("#loginname").val();
	   var password = $("#password").val();
	   
	   var msg = "";
	   
	   if(!/^\w{4,20}$/.test(loginname)){
		     msg = "登录名长度必须是6~20之间";
	   }else if(!/^\w{4,20}$/.test(password)){
		     msg = "密码长度必须是6~20之间";
	   }
	   if(msg !=""){
		   $("#tishi").html(msg);
		   return;
	   }
	   if( $("#rememberPwd").is(':checked')){
	   	$.localStorage.set(uk, loginname);
	   	$.localStorage.set(pk, password);
	   	$.localStorage.set(rk, true);
	   }else{
	   	$.localStorage.remove(uk);
	   	$.localStorage.remove(pk);
	   	$.localStorage.remove(rk);
	   }
	 window.location.replace("main.jsp");
	   // 异步提交登录请求
	 /*
		$.ajax({
	     type: "POST",
	     url: "/hrm/user/ajaxlogin",
	     async: true,
	     data:{
	    	 loginname:loginname.trim(),
	    	 password:password.trim()
	    	 },
	     dataType: "json",
	     error: function (XMLHttpRequest, textStatus, errorThrown) {
	     	$.MsgBox.Alert("消息","出错了，请于管理员联系");
	     },
	     success: function (json) {
	     	if(json.message!=""){
	     		$("#tishi").html(json.message);
	     	}else{
	     		window.location.replace("main");
	     		
	     		//alert(JSON.stringify(json));
	     	}
	     }
	 });
	   */

	   /** 提交表单 */
	   // 同步提交
	   $("#loginForm").submit();
   })
	   
})




//function subLogin(){
//    var $msg = $("#message"),$usrname=$("#username"),$pwd=$("#password"),$rememberPwd = $("#rememberPwd");
//    var usrname = $usrname.val()||"";
//
//    if(usrname.trim().length==0){
//        $("#tishi").html("用户名不能为空");
//        return ;
//    }
//    var pwd = $pwd.val()||"";
//    if(pwd.trim().length==0){
//    	$("#tishi").html("密码不能为空!");
//        return ;
//    }
//    if($rememberPwd.is(':checked')){
//    	$.localStorage.set(uk, usrname);
//    	$.localStorage.set(pk, pwd);
//    	$.localStorage.set(rk, true);
//    }else{
//    	$.localStorage.remove(uk);
//    	$.localStorage.remove(pk);
//    	$.localStorage.remove(rk);
//    }
//    
//    ajaxLoading();
//	
//	$.ajax({
//        type: "POST",
//        url: "/postbar/loginController/login",
//        async: true,
//        data:{
//        	userName:usrname.trim(),
//        	password:pwd.trim()
//       	 },
//        dataType: "json",
//        error: function (XMLHttpRequest, textStatus, errorThrown) {
//        	$.MsgBox.Alert("消息","出错了，请于管理员联系");
//        },
//        success: function (json) {
//        	ajaxLoadEnd();
//        	if(json.message!=""){
//        		$("#tishi").html(json.message);
//        	}else{
//        		window.location.replace("/postbar/index.html?menuUserName="+usrname.trim());
//        		
//        		//alert(JSON.stringify(json));
//        	}
//        }
//    });
//    
//
//}
//
//
//
//
//
//if(window !=top){
//	top.location.href=location.href;
//}
//function subReg(){
//	
//	var userName=$("#regusername").val();
//	var password=$("#regpassword").val();
//	var passwordCon=$("#regpasswordCon").val()
//	var regSex=$("#regsex").val();
//	var regAge=$("#regAge").val();
//	var regEmial=$("#regEmial").val();
//	if(typeof (userName) == 'undefined' || userName.trim()=="" ){
//		$("#zucetishi").html("用户名不能为空");
//		return;
//	}
//	if(userName.trim().length>20){
//		$("#zucetishi").html("用户名不能大于20个字符");
//		return;
//	}
//	if(typeof (password) == 'undefined' || password.trim()==""  ){
//		$("#zucetishi").html("密码不能为空");
//		return;
//	}
//	if(password.trim().length!=6){
//		$("#zucetishi").html("密码必须为6位");
//		return;
//	}
//	if(typeof (passwordCon) == 'undefined' || passwordCon.trim()==""){
//		$("#zucetishi").html("确认密码不能为空");
//		return;
//	}
//	if(password.trim()!=passwordCon.trim()){
//		$("#zucetishi").html("密码与确认密码必须保持一致");
//		return;
//	}
//	if(typeof (regEmial) == 'undefined' || regEmial.trim()==""){
//		$("#zucetishi").html("邮箱地址不能为空");
//		return;
//	}
//
//	if(!(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(regEmial.trim()))){
//		$("#zucetishi").html("邮箱地址格式不正确");
//		return;
//	}
//	
//	ajaxLoading();
//	
//	$.ajax({
//        type: "POST",
//        url: "/postbar/loginController/addRegister",
//        async: true,
//        data:{
//        	userName:userName.trim(),
//        	password:password.trim(),
//        	regSex:regSex.trim(),
//        	regAge:regAge.trim(),
//        	regEmial:regEmial.trim()
//       	 },
//        dataType: "json",
//        error: function (XMLHttpRequest, textStatus, errorThrown) {
//        	$.MsgBox.Alert("消息","出错了，请于管理员联系");
//        },
//        success: function (json) {
//        	ajaxLoadEnd();
//        	if(json.message!=""){
//        		$("#zucetishi").html(json.message);
//        	}else{
//        		
//        		$.MsgBox.Alert("消息", "注册成功，请重新登录！");
//        		gotoLogin();
//        		//alert(JSON.stringify(json));
//        	}
//        	
//        }
//    });
//}
//
//function gotoregister(){
//	$("#loginbox").hide();
//	$("#registerbox").show();
// 
//    $("#regusername").val("");
//	$("#regpassword").val("");
//	$("#regpasswordCon").val("")
//	$("#regsex").val("0");
//	$("#zucetishi").html("");
//	var regage=$("#regAge");
//	var selectPotion=$("#regAge option");
//	
//	if(selectPotion.length=="0"){
//		for(var i=14;i<100;i++){
//    		regage.append("<option value='"+i+"'>"+i+"</option>");
//    	}
//	}else{
//		regage.val("14");
//	}
//	
//	$("#regEmial").val("");
//}
//
//function gotoLogin(){
//	$("#loginbox").show();
//	$("#registerbox").hide();
//	
//	layui.use("layer");
//	$("#username").focus();
//	$("#username").val($.localStorage.get(uk));
//	$("#password").val($.localStorage.get(pk));
//	 $("#tishi").html("");
//	if($.localStorage.get(rk)){
//		$("#rememberPwd").attr("checked",true);
//	}
//}