<%@page import="com.zhsj.util.SessionThreadLocal"%>
<%@page import="com.zhsj.model.StoreAccount"%>
<%@page import="com.zhsj.model.Account"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Map<String,Object> map = SessionThreadLocal.getSession();
String flag = (String)map.get("flag");
String name = "";
if("account".equals(flag)){
	Account account = (Account)map.get("user");
	name = account.getName();
}else if("storeAccount".equals(flag)){
	StoreAccount storeAccount = (StoreAccount)map.get("user");
    name = storeAccount.getName();
}
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
        font-size:1.8em;
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
	.leftnav ul {
	    color: #fff;
	    text-align: center;
	    height: 40px;
	    line-height: 40px;
	    font-size: 20px;
	    cursor: pointer;
	    margin: 0 0 10px !important;
	    list-style: none;
        text-align: center;
	}
	.leftnav ul li {
	   background: #aaa;
       margin-bottom: 10px;
       color:#fff;
	}
	.leftnav ul li a{
	    color: #fff;
	    display: block;
        width: 100%;
        height: 100%;
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
	</style>
	<script type="text/javascript">
	   $(function(){
		   //上面导航
		   $(".nav>ul>li").on("click",function(){
			  $(".nav>ul .active").removeClass("active");
			  $(this).addClass("active");
		   });
		 //左边导航
		   $("#leftUl>li").on("click",function(){
			   alert(1);
			   $("#leftUl .sel").removeClass("sel");
			   $(this).addClass("sel");
		   });
		   //退出
		   $("#logout").click(function(){
			   $.post("account/logout",function(data){
				   if(data.code == 0){
					   alert("退出成功");
					   window.location.href="<%=basePath%>";
				   }else{
					   alert("退出失败。系统异常");
				   }
			   });
		   });
		   //
					   $.post("role/getParentModuleByAccount",function(result){
						  var arr = result.data,len=arr.length;
						  for(var i =0;i<len;i++){
							  if(i==0){
							     $(".nav ul").append($("<li>").prop("class","active").append($("<a>").prop("href","javascript:void(0)")
							    		                .attr("data-id",arr[i].id).text(arr[i].name).on("click",function(){
			                                                left($(this).attr("data-id"));
			                                                $(".nav>ul .active").removeClass("active");
			                      						    $(this).parent("li").addClass("active");
							    		                })));
							  }else{
								 $(".nav ul").append($("<li>").append($("<a>").attr("data-id",arr[i].id)
										            .prop("href","javascript:void(0)")
										            .text(arr[i].name).on("click",function(){
										            	left($(this).attr("data-id"));
										            	$(".nav>ul .active").removeClass("active");
														  $(this).parent("li").addClass("active");
										            })));
							  }
						  }
						  if(len > 0){
							  left(arr[0].id);
						  }
					   });	
					   
					   function left(id){
						   $.post("role/getModulesByPmIdandAccount",{
								  moduleId:id
							  },function(obj){
								  var leftnav = $("#leftUl");
								  leftnav.empty();
								  var tarr=obj.data,length=tarr.length;
								  for(var j=0;j<length;j++){
									  var li;
									  if(j == 0){
										   li = $("<li>").prop("class","sel").on("click",function(){ $("#leftUl .sel").removeClass("sel");
										   $(this).addClass("sel");})
								           .html("<a href='"+tarr[j].url+"' target='right'><span>"+tarr[j].name+"</span><a/>");
										   $("#rightIframe").prop("src",tarr[j].url);
									  }else{
										   li = $("<li>").on("click",function(){ $("#leftUl .sel").removeClass("sel");
										   $(this).addClass("sel");})
								           .html("<a href='"+tarr[j].url+"' target='right'><span>"+tarr[j].name+"</span><a/>"); 
									  }
									  leftnav.append(li);
								  }
							  });
					   }
					   
					   
		   //根据当前用户的用户组加载上边父模块
		     
		   
		   
		   
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
	           <!-- 上导航 -->
<!-- 	          <li> -->
<!-- 	             <a href="page/shopPaySet"  target="center">收款设置</a>       -->
<!-- 	          </li> -->
<!-- 	          <li > -->
<!-- 	             <a href="javascript:void(0)"  target="center">流水汇总</a>       -->
<!-- 	          </li> -->
<!-- 	          <li> -->
<!-- 	             <a href="javascript:void(0)" target="center">账号设置</a>       -->
<!-- 	          </li> -->
<!-- 	          <li class="active"> -->
<!-- 	             <a href="page/userManager" target="center">用户管理</a>       -->
<!-- 	          </li> -->
<!-- 	          <li> -->
<!-- 	             <a href="page/shopManager" target="center">门店管理</a>       -->
<!-- 	          </li> -->
<!-- 	          <li> -->
<!-- 	             <a href="javascript:void(0)" target="center">收款管理</a> -->
<!-- 	          </li> -->
<!-- 	          <li> -->
<!-- 	             <a href="javascript:void(0)" target="center">商户通管理</a>       -->
<!-- 	          </li> -->
<!-- 	          <li> -->
<!-- 	             <a href="javascript:void(0)" target="center">商场管理</a> -->
<!-- 	          </li> -->
	       </ul>
	     </div>
	  </div>
	  <!-- 主内容 -->
	  <div class="content">
	      <div class="leftnav">
	         <ul id="leftUl">
<!-- 	            <li class="sel"> -->
<!-- 	               <a href="javascript:void(0)" target="right"> -->
<!-- 	                    <span>省级合作商管理</span> -->
<!-- 	               </a> -->
<!-- 	            </li> -->
<!-- 	            <li> -->
<!-- 	               <a href="javascript:void(0)"  target="right"> -->
<!-- 	                    <span>省级合作商管理</span> -->
<!-- 	               </a> -->
<!-- 	            </li> -->
	         </ul>
	      </div>
	      <!-- 主要显示内容 -->
	      <div class="mainContent">
	         <iframe id="rightIframe" scrolling="no" frameborder="0" src="" name="right" width="100%" border="0" marginwidth="0" marginheight="0" ></iframe>
	      </div>
	  </div>
	  <!-- footer -->
	  <div class="footer">
	       <span>万物通科技(北京)有限公司 </span>&nbsp;&nbsp;
<!-- 	       <span>总部地址:北京市海淀区学清路16号学知轩大厦14层</span> -->
	  </div>
  </div>
</body>
</html>	