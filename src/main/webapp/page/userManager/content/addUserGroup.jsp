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
			  
			   if($("#name").val() == ''){
				    alert("输入用户组名称");
			    	return false;
			   }
			    $.post("userGroup/add",{
			    	name:$("#name").val()
			    },function(data){
			    	if(data.code == 0){
			    		alert("添加用户成功");
			    		location.href="page/userGroupList";
			    	}else{
			    		alert("添加用户失败");
			    	}
			    });
			   
		   });
		   
		   
		   //获取用户组
		   
// 		   $.post("usersGroup/getlist",function(result){
// 			   if(result.code == 0){
// 				   for(var i in result.data){
// 				     $("#groupid").append($("<option>").attr("value",result.data[i].id).text(result.data[i].name));
// 				   }
// 			   }else{
// 				   alert(result.msg);
// 			   }
// 		   });
		   
		   
	   });
	 
	</script>
</head>
<body>
                 <div class="contentNav">
                      /&nbsp;&nbsp;<span>添加用户组</span>
                 </div>
                 <div class="taps nav-tabs">
                     <span class="defaultSel">
                                                                    添加用户组
                     </span>
                 </div>
                 
                 <div class="content">
				     <div class="form-horizontal ajaxfrom" role="form" id="form-user">
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">用户组名称</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="name" name="name" type="text" class="form-control" placeholder="输入用户组名称">
								<span class="help-block">请输入用户组名称</span>
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