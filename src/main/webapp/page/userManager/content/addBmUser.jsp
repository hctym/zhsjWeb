<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <base href="<%=basePath%>">
    <title>智慧商街</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="keywords" content="">
	<meta name="description" content="">
	<link rel="shortcut icon" href="image/wechat.jpg">
	<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="js/lib/jquery-1.11.1.min.js"></script>
	<style>
	*{
	    box-sizing: border-box;
	    xfont-size: 12px;
	    margin: 0;
	    padding: 0;
	}
	body {
	    overflow-y: scroll;
	    background: #f9f9f9;
	    font-size: 12px;
	}
	html, body, h1, h2, h3 {
        font-family: arial, 'Hiragino Sans GB', 'Microsoft Yahei', '微软雅黑', '宋体', \5b8b\4f53, Tahoma, Arial, Helvetica, STHeiti;
    }
    a,a:hover,a:active,a:focus{
    text-decoration:none;
    }
    
	
	
    .contentNav {
	   background:#f5f5f5;
       padding:8px 15px;
       color:#777;
	}
    .taps{
	   margin-bottom: 20px;
       border-color: #44b549;
       padding:10px 10px 10px 0;
	}
	.nav-tabs {
	    border-bottom: 1px solid #ddd;
	}
	.defaultSel{
	   background:#44b549;
	   color:#fff;
	   padding:10px;
	}
	.content{
	   margin-top:10px;
	   border:1px solid #ccc;
	   padding-top:20px;
	}
	</style>
	<script type="text/javascript">
	   $(function(){
		   $("#submit").click(function(){
			   if($("#account").val() == ''){
			    	alert("输入账户名称");
			    	return false;
			   }
			   if($("#password").val() == ''){
				    alert("输入密码");
			    	return false;
			   }
			   if($("#repassword").val() == ''){
				    alert("输入重复密码");
			    	return false;
			   }
			   if($("#repassword").val() != $("#password").val()){
				    alert("输入的密码和重复密码不一样");
			    	return false;
			   }
			   if($("#mobile").val() == ''){
				    alert("输入手机号");
			    	return false;
			   }
			   if($("#email").val() == ''){
				    alert("输入邮箱");
			    	return false;
			   }
			    $.post("bmuser/add",{
			    	account:$("#account").val(),
			    	password:$("#password").val(),
			    	name:$("#name").val(),
			    	gender:$("input[name='gender']:checked").val(),
			    	mobile:$("#mobile").val(),
			    	email:$("#email").val(),
			    	userGroupId:$("#groupid").val()
			    },function(data){
			    	if(data.code == 0){
			    		alert("添加用户成功");
			    		location.href="page/bmUserList";
			    	}else{
			    		alert("添加用户失败");
			    	}
			    });
			   
		   });
		   
		   
		   //获取用户组
		   
		   $.post("userGroup/getlist",function(result){
			   if(result.code == 0){
				   for(var i in result.data){
				     $("#groupid").append($("<option>").attr("value",result.data[i].id).text(result.data[i].name));
				   }
			   }else{
				   alert(result.msg);
			   }
		   });
		   
		   
	   });
	 
	</script>
</head>
<body>
                 <div class="contentNav">
                      /&nbsp;&nbsp;<span>添加用户</span>
                 </div>
                 <div class="taps nav-tabs">
                     <span class="defaultSel">
                                                                    添加用户
                     </span>
                 </div>
                 
                 <div class="content">
				     <div class="form-horizontal ajaxfrom" role="form" id="form-user">
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">账户名称(手机号作为登录名)</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="account" name="account" type="text" class="form-control" placeholder="输入账户名称">
								<span class="help-block">请输入账户名称</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">真实姓名</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="name" name="name" type="text" class="form-control" placeholder="输入真实姓名">
								<span class="help-block">请输入真实姓名</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">登录密码</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="password" name="password" type="password" class="form-control" placeholder="输入登录密码">
								<span class="help-block">请填写密码，最小长度为 8 个字符</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">确认密码</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="repassword" type="password" class="form-control" placeholder="输入重复密码">
								<span class="help-block">重复输入密码，确认正确输入</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">性别</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<label class="radio-inline"><input type="radio" name="gender" value="1" checked> 男</label>
								<label class="radio-inline"><input type="radio" name="gender" value="2" > 女</label>
							</div>
						</div>
						<div class="form-group" class="padding-top:20px;">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">手机号</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="mobile" type="text" class="form-control" placeholder="输入手机号">
								<span class="help-block">输入手机号</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">邮箱</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="email" type="text" class="form-control" placeholder="输入邮箱">
								<span class="help-block">输入邮箱</span>
							</div>
						</div>
						<div class="form-group">
								<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">所属用户组</label>
								<div class="col-sm-10 col-lg-9 col-xs-12">
									<select name="provincecode" class="form-control" id="groupid">
<!-- 										<option value="0">请-选-择</option> -->
<!-- 										<option value="820000">澳  门</option> -->
									</select>
									<span class="help-block">分配用户所属用户组后，该用户会自动拥有此用户组内的模块操作权限
设置用户组后，系统会根据对应用户组的服务期限对用户的服务开始时间和结束时间进行初始化</span>
								</div>
							</div>
					   <div class="form-group">
							<div class="col-sm-offset-2 col-md-offset-2 col-lg-offset-1 col-xs-12 col-sm-10 col-md-10 col-lg-11">
								<input type="submit" id="submit" class="btn btn-primary span3" name="submit" value="确认注册">
							</div>
						</div>
					</div>
                 </div>
</body>
</html>	