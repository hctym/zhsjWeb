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
	<script type="text/javascript" src="js/lib/Dutil.js"></script>
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
	td span{
	  margin-left:10px;
	  color:#428bca;
	  cursor:pointer;
	}
	</style>
	<script type="text/javascript">
	   $(function(){
		   
		   $(document).ajaxSend(function(){
			   alert("数据疯狂加载中..");
		   });  
		   $.post("userGroup/getlist",function(result){
			   if(result.code == 0){
				   var tbody = $("tbody");
				   for(var i in result.data){
						if(result.data[i].ctime >0 ){
							   result.data[i].ctime =  new Date(result.data[i].ctime).Format("yyyy-MM-dd hh:mm:ss");
						}
						console.log(result.data[i]);
					   tbody.append($("<tr>")
							   .append($("<td>").text(result.data[i].id))
							   .append($("<td>").text(result.data[i].name))
							   .append($("<td>").text(result.data[i].isValid))
							   .append($("<td>").text(result.data[i].ctime))
							   .append($("<td>")
									   .append($("<span>").text("编辑").attr("data-id",result.data[i].id).on("click",function(){
										   alert($(this).attr("data-id")+"   编辑");
									   }))
									   .append($("<span>").text("删除").attr("data-id",result.data[i].id).on("click",function(){
										   alert($(this).attr("data-id")+"   删除");
									   }))
									   .append($("<span>").text("分配模块").attr("data-id",result.data[i].id).on("click",function(){
// 										   alert($(this).attr("data-id")+"   分配模块");
                                            window.location.href="page/modules?id="+$(this).attr("data-id");
									   }))));
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
                      /&nbsp;&nbsp;<span>用户组列表</span>
                 </div>
                 <div class="taps nav-tabs">
                     <span class="defaultSel">
                                                                    用户组列表
                     </span>
                 </div>
                 <div class="content">
                    <div class="table-responsive panel-body">
						<table class="table table-hover">
							<thead class="navbar-inner">
								<tr>
									<th style="width:200px;">用户组id</th>
									<th style="width:200px;">用户组名称</th>
									<th style="width:150px;">是否有效</th>
									<th style="width:150px;">创建时间</th>
									<th style="width:150px;">操作</th>
								</tr>
							</thead>
							<tbody>
<!-- 								<tr> -->
<!-- 									<td>测试商圈</td> -->
<!-- 									<td>131950898</td> -->
<!-- 									<td>河北省</td> -->
<!-- 									<td><span class="label label-info">省级代理</span></td> -->
<!-- 									<td><span class="label label-success">正常状态</span></td> -->
<!-- 									<td></td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td>辽宁省总后台</td> -->
<!-- 									<td>131402807</td> -->
<!-- 									<td>辽宁省</td> -->
<!-- 									<td><span class="label label-info">省级代理</span></td> -->
<!-- 									<td><span class="label label-success">正常状态</span></td> -->
<!-- 									<td></td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td>北京总后台</td> -->
<!-- 									<td>131102788</td> -->
<!-- 									<td>北京市</td> -->
<!-- 									<td><span class="label label-info">省级代理</span></td> -->
<!-- 									<td><span class="label label-success">正常状态</span></td> -->
<!-- 									<td></td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td>山东省后台</td> -->
<!-- 									<td>131581849</td> -->
<!-- 									<td>山东省</td> -->
<!-- 									<td><span class="label label-info">省级代理</span></td> -->
<!-- 									<td><span class="label label-success">正常状态</span></td> -->
<!-- 									<td></td> -->
<!-- 								</tr> -->
							</tbody>
						</table>
	                 </div>
                 </div>
	       
</body>
</html>	