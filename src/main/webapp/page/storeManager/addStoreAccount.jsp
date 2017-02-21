<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String storeNo = (String)request.getAttribute("storeNo");
String falg = (String)request.getAttribute("flag");
%>

<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <base href="<%=basePath%>">
    <title>添加商户门店账户</title>
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
// 			   if($("#name").val() == ''){
// 			    	alert("输入账户名称");
// 			    	return false;
// 			   }
// 			   if($("#address").val() == ''){
// 				    alert("详细地址");
// 			    	return false;
// 			   }
// 			   if($("#cityCode").val() == ''){
// 				    alert("城市代码");
// 			    	return false;
// 			   }
// 			   if($("#contactPhone").val() == ''){
// 				    alert("联系人手机号");
// 			    	return false;
// 			   }
// 			   if($("#shopLogo").val() == ''){
// 				    alert("添加门店图片logo");
// 			    	return false;
// 			   }
// 			   if($("#intro").val() == ''){
// 				    alert("输入介绍");
// 			    	return false;
// 			   }
			    $.post("storeAccount/add",{
			    	storeNo:'<%=storeNo%>',
			    	account:$("#account").val(),
			    	password:$("#password").val(),//test
			    	name:$("#name").val(),
			    	headImg:$("#headImg").val(),
			    	gender:$("input[name='gender']:checked").val(),
			    	mobile:$("#mobile").val(),
			    	email:$("#email").val(),
			    	roleId:$("#roleid").val()
			    },function(data){
			    	if(data.code == 0){
			    		alert("添加门店账户成功");
			    		location.href="page/storeList";
			    	}else{
			    		alert("添加门店失败");
			    	}
			    });
			   
		   });
		   
		 //获取角色
		   $.post("role/getListByType",{
			   type:2
		   },function(result){
			   if(result.code == 0){
				   for(var i in result.data){
				     $("#roleid").append($("<option>").attr("value",result.data[i].id).text(result.data[i].name));
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
                      /&nbsp;&nbsp;<span>门店管理</span>
                 </div>
                 <div class="taps nav-tabs">
                     <span class="defaultSel">
                                                                    添加门店员工
                     </span>
                 </div>
                 
<div class="form-horizontal ajaxfrom" role="form" id="form-user">
						<div class="panel-body">
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">
						商户门店账户
					</label>
					<div class="col-sm-8 col-lg-9 col-xs-12">
						<input type="text" class="form-control" id="account" value="" placeholder="商户门店账号">
						<div class="help-block">
							请填写商户门店账户
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">
						密码
					</label>
					<div class="col-sm-8 col-lg-9 col-xs-12">
						<input type="text" class="form-control" id="password" value="" placeholder="密码">
						<div class="help-block">
							密码
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">
						用户名称
					</label>
					<div class="col-sm-8 col-lg-9 col-xs-12">
						<input type="text" class="form-control" id="name" value="" placeholder="用户名称">
						<div class="help-block">
							请填写用户名称
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">
						头像
					</label>
					<div class="col-sm-8 col-lg-9 col-xs-12">
						<input type="text" class="form-control" id="headImg" value="" placeholder="头像">
						<div class="help-block">
							请填写头像
						</div>
					</div>
				</div>
				<div class="form-group" style="margin-bottom:20px;">
					<label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">性别</label>
					<div class="col-sm-10 col-lg-9 col-xs-12">
							<label class="radio-inline"><input type="radio" name="gender" value="1" checked> 男</label>
							<label class="radio-inline"><input type="radio" name="gender" value="2" > 女</label>
					</div>
			</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">
						手机号
					</label>
					<div class="col-sm-8 col-lg-9 col-xs-12">
						<input type="text" class="form-control" id="mobile" value="" placeholder="手机号">
						<div class="help-block">
							请填写手机号
						</div>
					</div>
				</div>
                <div class="form-group">
					<label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">
						邮箱
					</label>
					<div class="col-sm-8 col-lg-9 col-xs-12">
						<input type="text" class="form-control" id="email" value="" placeholder="邮箱">
						<div class="help-block">
							请填写邮箱
						</div>
					</div>
				</div>
                <div class="form-group" style="margin-bottom:20px;">
					<label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">角色</label>
					<div class="col-sm-8 col-lg-9 col-xs-12">
						<select name="provincecode" class="form-control" id="roleid">
<!-- 										<option value="0">请-选-择</option> -->
<!-- 										<option value="820000">澳  门</option> -->
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">
						门店简介
					</label>
					<div class="col-sm-8 col-lg-9 col-xs-12">
					
					<textarea rows="5" cols="40" id="intro"></textarea>
									</div>
				</div>
				  <div class="form-group">
							<div class="col-sm-offset-2 col-md-offset-2 col-lg-offset-1 col-xs-12 col-sm-10 col-md-10 col-lg-11">
								<input type="submit" id="submit" class="btn btn-primary span3" name="submit" value="提交">
							</div>
				</div>
			</div>
                 </div>
</body>
</html>	