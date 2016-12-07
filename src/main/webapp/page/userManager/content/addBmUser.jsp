<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String name = (String)request.getSession().getAttribute("user");
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
			   //test 整个流程是否通
			    $.post("bmuser/add",{
			    	account:$("#shopname").val(),
			    	password:$("#password").val(),
			    	name:$("#truename").val(),
			    	gender:1,
			    	mobile:$("contactphone").val(),
			    	emial:$("contactphone").val(),
			    	userGroupId:10
			    },function(data){
			    	if(data.code == 0){
			    		alert("添加用户成功");
			    	}else{
			    		alert("添加用户失败");
			    	}
			    });
			   
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
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">代理名称</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="shopname" name="shopname" type="text" class="form-control" value="" id="shopname">
								<span class="help-block">请输入代理名称，代理名称为 3 到 15 个字符组成</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">真实姓名</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="" name="truename" type="text" class="form-control" value="" id="truename">
								<span class="help-block">请输入管理员真实姓名</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">登录密码</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="password" name="password" type="password" class="form-control" value="" autocomplete="off" id="password">
								<span class="help-block">请填写密码，最小长度为 8 个字符</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">确认密码</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="repassword" type="password" class="form-control" value="" autocomplete="off" id="repassword">
								<span class="help-block">重复输入密码，确认正确输入</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">所属区域</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<select name="provincecode" class="form-control" id="groupid">
									<option value="0">请-选-择</option>
														<option value="110000">北京市</option>
														<option value="120000">天津市</option>
														<option value="130000">河北省</option>
														<option value="140000">山西省</option>
														<option value="150000">内蒙古</option>
														<option value="210000">辽宁省</option>
														<option value="220000">吉林省</option>
														<option value="230000">黑龙江</option>
														<option value="310000">上海市</option>
														<option value="320000">江苏省</option>
														<option value="330000">浙江省</option>
														<option value="340000">安徽省</option>
														<option value="350000">福建省</option>
														<option value="360000">江西省</option>
														<option value="370000">山东省</option>
														<option value="410000">河南省</option>
														<option value="420000">湖北省</option>
														<option value="430000">湖南省</option>
														<option value="440000">广东省</option>
														<option value="450000">广  西</option>
														<option value="460000">海南省</option>
														<option value="500000">重庆市</option>
														<option value="510000">四川省</option>
														<option value="520000">贵州省</option>
														<option value="530000">云南省</option>
														<option value="540000">西  藏</option>
														<option value="610000">陕西省</option>
														<option value="620000">甘肃省</option>
														<option value="630000">青海省</option>
														<option value="640000">宁  夏</option>
														<option value="650000">新  疆</option>
														<option value="710000">台湾省</option>
														<option value="810000">香  港</option>
														<option value="820000">澳  门</option>
													</select>
								<span class="help-block">所属区域是用户层级管理的重要依据，请谨慎调整</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">所属用户组</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
				
								<input type="hidden" name="groupid" class="form-control" value="2" autocomplete="off">
								<input type="text" class="form-control" value="省级代理" autocomplete="off" readonly="readonly">
								<span class="help-block">分配用户所属用户组后，该用户会自动拥有此用户组内的模块操作权限</span>
								<span class="help-block"><strong class="text-danger">设置用户组后，系统会根据对应用户组的服务期限对用户的服务开始时间和结束时间进行初始化</strong></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">联系人</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input type="text" name="contact" class="form-control" value="" id="contact">
								<span class="help-block">请填写联系人</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">联系电话</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input type="text" name="contactphone" class="form-control" id="contactphone">
								<span class="help-block">请填写联系电话</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">备注</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<textarea id="" name="remark" style="height:80px;" class="form-control" id="remark"></textarea>
								<span class="help-block">方便注明此用户的身份</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">允许代理</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<label class="radio-inline"><input type="radio" name="is_display" value="1"> 显示</label>
								<label class="radio-inline"><input type="radio" name="is_display" value="0" checked=""> 不显示</label>
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