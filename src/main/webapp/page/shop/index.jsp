<%@page import="com.zhsj.model.BusinessUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
BusinessUser user = (BusinessUser)request.getSession().getAttribute("user");
String name = user.getAccount();
int type = user.getType();
int businessInfoId = user.getBusinessInfo().getId();
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
	<link href="css/bootstrap.min.css" type="text/css" media="screen" rel="stylesheet">
	<script type="text/javascript" src="js/lib/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/lib/iframe.js"></script>
	<script type="text/javascript">
	      startInit('rightIframe', 560);
	</script>
	<style>
	*{
	    box-sizing: border-box;
	    xfont-size: 12px;
	    margin: 0;
	    padding: 0;
	}
	body {
	    background: #f9f9f9;
	    font-size: 12px;
	    margin:0; 
	    padding:0;
	}
	html, body, h1, h2, h3 {
        font-family: arial, 'Hiragino Sans GB', 'Microsoft Yahei', '微软雅黑', '宋体', \5b8b\4f53, Tahoma, Arial, Helvetica, STHeiti;
    }
    a,a:hover,a:active,a:focus{
    text-decoration:none;
    }
    .wrapper{
       height: 100%;
       background: #e7e8eb;
    }
	.topest{
	   height: 30px;
       background-color: #3D4749;
	}
	.admin{
	    float: right;
	    width: 350px;
	    color: white;
	    line-height:30px;
	}
	.admin a{
	   color:#fff;
	}
	.topnav{
		border-top: 3px solid #44b549;
	    background: #fff;
	    border-bottom: 0px;
	    z-index: 1001;
        margin-bottom: 0;
        position: relative;
	    min-height: 50px;
	    height:auto;
	    overflow:hidden;
	    margin-bottom: 20px;
	}
	.clearfix:before,.topnav:before{
	   display:table;
	   content:" ";
	}
	.topnav:after{
	   clear:both;
	}
	.biglogo{
	   float:left;
	   margin:20px 20px 0px 50px;
	}
	.nav{
	   float:left;
	   margin:10px 40px 0px 50px;
	}
	.active {
	   border-bottom: 4px solid #44b549;
	}
	.nav ul{
	   list-style: none;
       padding-left: 20px;
       min-width: 768px;
       width:100%;
	}
	.nav ul li {
	    float:left;
	    padding-top: 10px;
	    padding-bottom: 10px;
	    padding-left: 15px;
	    padding-right: 25px;
	}
	.nav ul li a{
	    display: block;
		font-size:16px;
	    background: #fff !important;
	    color: #222222 !important;
        line-height: 50px;
	}
	.content{
	    background: #fff;
	    margin-left: 15px;
	    margin-right: 15px;
	    margin-top: 15px;
	    padding-left: 0px;
	    padding-right: 0px;
	    overflow:hidden;
	}
	.content:after{
	   clear:both;
	}
	.leftnav{
	    background: #fff;
	    min-height: 300px;
	    border: none;
	    width: 15%;
	    margin-left: 1%;
	    float: left;
	    margin-top: 20px;
	}
	.leftnav .page-header {
	    color: #8d8d8d;
	    text-align: left;
	    background: #f5f5f5;
	    border: 0px;
	    height: 40px;
	    line-height: 40px;
	    font-size: 14px;
	    margin-left: 0px;
	    padding-left: 1em;
	}
	.leftnav .tile {
	    background: #fff;
	    padding: 0px;
	    padding-left: 40px;
	    margin: 0px;
	    height: 40px;
	    line-height: 40px;
	    width: 100%;
	    text-align: left;
	}
	.tile {
	    display: block;
	    margin: 0.4em;
	    padding: .2em 1em .5em 1em;
	    width: 8em;
	    text-align: center;
	    background: #EEE;
	    color: #333;
	    text-decoration: none;
    }
	.leftnav .sel {
	    background: #44b549;
	    color: #fff;
	}
	.clearfix{
	   margin:20px 0 0;
	}
	.clearfix a:hover{
	   background:#428bca;
	}
	.mainContent{
	    background: #fff;
	    width: 82%;
	    float: right;
	    display: block;
	    margin-top: 20px;
	    border-radius: 0px;
	    min-height: 600px;
	    height: 100%;
	    border-left: 1px solid #e3e3e3;
	    box-shadow: inset 0 1px 1px rgba(0,0,0,.05);
	    padding: 19px;
        margin-bottom: 20px;
        border: 1px solid #e3e3e3;
	}
	.mainContent .contentNav {
	   background:#f5f5f5;
       padding:8px 15px;
       color:#777;
	}
	.mainContent .taps{
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
	.footer{
	   font-size: 1.3em;
       padding: 2em 0;
       height:30px;
       border:1px solid #aaa;
       text-align:center;
	}
	.footer span{
	   
	}
	iframe{
	  overflow:hidden;
	  display:block;
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
  <div class="wrapper">
      <!-- top -->
	  <div class="topest">
	     <div class="admin">
	                    您好，<%=name%>
	        <a href="javascript:void(0)" id="logout">&nbsp;&nbsp;退出</a> 
	            &nbsp;&nbsp;&nbsp;&nbsp;客服/投诉电话:400-661-0003         
	     </div>
	  </div>
	  <!-- nav(logo和导航) -->
	  <div class="topnav">
	     <div class="biglogo">
	        <img src="image/biglogo.png" width="160px;">
	     </div>
	     <div class="nav">
	       <ul>
	          <li class="active">
	             <a href="javascript:void(0)" target="center">门店管理</a>      
	          </li>
	       </ul>
	     </div>
	  </div>
	  <!-- 主内容 -->
	  <div class="content">
	      <div class="leftnav">
	          <h5 class="page-header">
	             <i class="fa fa-briefcase"></i>&nbsp;&nbsp;门店管理
	          </h5>
	          <div class="clearfix">
					<a href="page/shopInfo?id=<%=businessInfoId%>" class="tile sel" target="right">
						<span>门店信息</span>
					</a>
					<a href="page/shopUsers?id=<%=businessInfoId %>" class="tile" target="right">
						<span>门店员工</span>
					</a>
					<% if(type == 1){%>
					<a href="page/addShopUser?type=2&id=<%=businessInfoId %>" class="tile" target="right">
						<span>添加门店员工</span>
					</a>
					<% } %>
			  </div>
	      </div>
	      <!-- 主要显示内容 -->
	      <div class="mainContent">
	         <iframe id="rightIframe" scrolling="no" frameborder="0" src="page/shopInfo?id=<%=businessInfoId%>" name="right" width="100%" border="0" marginwidth="0" marginheight="0" ></iframe>
	      </div>
	  </div>
	  <!-- footer -->
	  <div class="footer">
	       <span>万物通科技(北京)有限公司 </span>&nbsp;&nbsp;
	  </div>
  </div>
</body>
</html>	