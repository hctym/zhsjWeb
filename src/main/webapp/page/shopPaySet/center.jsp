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
	    margin: 0px !important;
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
	iframe{
	  overflow:hidden;
	  display:block;
	}
	</style>
	<script type="text/javascript">
	   $(function(){
		   //左边导航
		   $(".clearfix a").on("click",function(){
			   $(".leftnav .sel").removeClass("sel");
			   $(this).addClass("sel");
		   });
	   });
	 
	</script>
</head>
<body>
	      <!-- 左导航 -->
	      <div class="leftnav">
	          <h5 class="page-header">
	             <i class="fa fa-briefcase"></i>&nbsp;&nbsp;支付设置
	          </h5>
	          <div class="clearfix">
					<a href="page/wechatPay" class="tile sel" target="right">
						<span>微信支付</span>
					</a>
					<a href="page/aliPay" class="tile" target="right">
						<span>支付宝</span>
					</a>
			  </div>
			  <h5 class="page-header">
			      &nbsp;&nbsp;收款设置
			  </h5>
			  <div class="clearfix">
					<a href="javascript:void(0)" class="tile" target="right">
						<span>我的收款码</span>
					</a>
			  </div>
	      </div>
	      <!-- 主要显示内容 -->
	      <div class="mainContent">
	         <iframe id="rightIframe" scrolling="no" frameborder="0" src="page/wechatPay" name="right" width="100%" border="0" marginwidth="0" marginheight="0" ></iframe>
	      </div>
</body>
</html>	