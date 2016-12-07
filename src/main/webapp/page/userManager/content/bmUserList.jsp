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
	}
	</style>
	<script type="text/javascript">
	   $(function(){
		   //上面导航
		   $(".nav>ul>li").on("click",function(){
			  $(".nav>ul .active").removeClass("active");
			  $(this).addClass("active");
		   });
		   //左边导航
		   $(".clearfix a").on("click",function(){
			   $(".leftnav .sel").removeClass("sel");
			   $(this).addClass("sel");
		   });
		   //退出
		   $("#logout").click(function(){
			   $.post("user/logout",function(data){
				   if(data.code == 0){
					   alert("退出成功");
					   window.location.href="<%=basePath%>";
				   }else{
					   alert("退出失败。系统异常");
				   }
			   });
		   });
	   });
	 
	</script>
</head>
<body>
      
                 <div class="contentNav">
                      /&nbsp;&nbsp;<span>用户列表</span>
                 </div>
                 <div class="taps nav-tabs">
                     <span class="defaultSel">
                                                                    用户列表
                     </span>
                 </div>
                 <div class="content">
                    <div class="table-responsive panel-body">
						<table class="table table-hover">
							<thead class="navbar-inner">
								<tr>
									<th style="width:200px;">代理名称</th>
									<th style="width:150px;">管理员用户名</th>
									<th style="width:150px;">区域</th>
									<th style="width:150px;">身份</th>
									<th style="width:100px;">状态</th>
									<th style="width:200px;"><span style="float:right"><a href="page/addBmUser" class="btn btn-primary">添加省级用户</a></span></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>测试商圈</td>
									<td>131950898</td>
									<td>河北省</td>
									<td><span class="label label-info">省级代理</span></td>
									<td><span class="label label-success">正常状态</span></td>
									<td></td>
								</tr>
								<tr>
									<td>辽宁省总后台</td>
									<td>131402807</td>
									<td>辽宁省</td>
									<td><span class="label label-info">省级代理</span></td>
									<td><span class="label label-success">正常状态</span></td>
									<td></td>
								</tr>
								<tr>
									<td>北京总后台</td>
									<td>131102788</td>
									<td>北京市</td>
									<td><span class="label label-info">省级代理</span></td>
									<td><span class="label label-success">正常状态</span></td>
									<td></td>
								</tr>
								<tr>
									<td>山东省后台</td>
									<td>131581849</td>
									<td>山东省</td>
									<td><span class="label label-info">省级代理</span></td>
									<td><span class="label label-success">正常状态</span></td>
									<td></td>
								</tr>
							</tbody>
						</table>
	                 </div>
                 </div>
	       
</body>
</html>	