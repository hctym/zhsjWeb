<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
long id = (Long)request.getAttribute("id");
%>

<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <base href="<%=basePath%>">
    <title>编辑用户account</title>
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
		   
		   $.post("account/getById",{id:<%=id%>},function(result){
			   console.log(result); 
			   if(result.code == 0){
				   var obj = result.data;
				   $("#account").val(obj.account);
				   $("#name").val(obj.name);
				   $($("input[name=gender]")[0]).prop("checked","false");
				   $($("input[name=gender]")[1]).prop("checked","false");
				   if(obj.gender == 1){
					   $($("input[name=gender]")[0]).prop("checked","true");
				   }else{
					   $($("input[name=gender]")[1]).prop("checked","true");
				   }
				   $("#mobile").val(obj.mobile);
				   $("#email").val(obj.email);
			   }else{
				   alert("查询用户数据失败了");
			   }
		   });
		   
		   $("#update").click(function(){
			   if($("#mobile").val() == ''){
				    alert("输入手机号");
			    	return false;
			   }
			   if($("#email").val() == ''){
				    alert("输入邮箱");
			    	return false;
			   }
			    $.post("account/update",{
			    	id:<%=id%>,
			    	name:$("#name").val(),
			    	gender:$("input[name='gender']:checked").val(),
			    	mobile:$("#mobile").val(),
			    	email:$("#email").val(),
			    	status:-1,
			    	valid:-1,
			    },function(data){
			    	if(data.code == 0){
			    		alert("修改用户成功");
			    		location.href="page/accountList";
			    	}else{
			    		alert("修改用户失败");
			    	}
			    });
			   
		   });
		   
		   
		   
	   });
	 
	</script>
</head>
<body>
                 <div class="contentNav">
                      /&nbsp;&nbsp;<span>编辑用户</span>
                 </div>
                 <div class="taps nav-tabs">
                     <span class="defaultSel">
                                                                    编辑用户
                     </span>
                 </div>
                 
                 <div class="content">
				     <div class="form-horizontal ajaxfrom" role="form" id="form-user">
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">账户名称(手机号作为登录名)</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="account" name="account" type="text" class="form-control" readonly>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">真实姓名</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="name" name="name" type="text" class="form-control" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">性别</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<label class="radio-inline"><input type="radio" name="gender" value="1" > 男</label>
								<label class="radio-inline"><input type="radio" name="gender" value="2" > 女</label>
							</div>
						</div>
						<div class="form-group" class="padding-top:20px;">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">手机号</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="mobile" type="text" class="form-control" placeholder="输入手机号">
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">邮箱</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="email" type="text" class="form-control" placeholder="输入邮箱">
							</div>
						</div>
					   <div class="form-group">
							<div class="col-sm-offset-2 col-md-offset-2 col-lg-offset-1 col-xs-12 col-sm-10 col-md-10 col-lg-11">
								<input type="submit" id="update" class="btn btn-primary span3" name="submit" value="确认修改">
							</div>
						</div>
					</div>
                 </div>
</body>
</html>	