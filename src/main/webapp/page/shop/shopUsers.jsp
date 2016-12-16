<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
int id  = (Integer)request.getAttribute("id");
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
		   $(document).ajaxSend(function(){
			   alert("数据疯狂加载中111..");
		   });  
		   $.get("businessUser/getbuserBybinfoId?businessInfoId=<%=id%>",{
		   },function(result){
			   if(result.code == 0){
				   var tbody = $("tbody");
				   var status ="",name="";
				   for(var i in result.data){
					   if(result.data[i].status == 1){
						   status="启用";
					   }else{
						   status="禁用";
					   }
					   if(result.data[i].type == 1){
						   name = "店长";
					   }else{
						   name = "员工";
					   }
					   tbody.append($("<tr>")
							   .append($("<td>").text(result.data[i].account))
							   .append($("<td>").text(result.data[i].name))
							   .append($("<td>").text(result.data[i].mobile))
							   .append($("<td>").text(result.data[i].email))
							   .append($("<td>").append($("<span>").attr("class","label label-info").text(name)))
							   .append($("<td>").append($("<span>").attr("class","label label-success").text(status)))
							   .append($("<td>")));
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
									<th style="width:200px;">账户名称</th>
									<th style="width:150px;">真实姓名</th>
									<th style="width:150px;">手机号</th>
									<th style="width:150px;">邮箱</th>
									<th style="width:100px;">类型</th>
									<th style="width:100px;">状态</th>
								</tr>
							</thead>
							<tbody>
							<!-- content -->
							</tbody>
						</table>
	                 </div>
                 </div>
	       
</body>
</html>	