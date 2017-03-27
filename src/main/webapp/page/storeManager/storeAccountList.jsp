<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String storeNo = (String)request.getAttribute("storeNo");
%>

<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <base href="<%=basePath%>">
    <title>门店用户的账户列表</title>
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
	</style>
	<script type="text/javascript">
	   $(function(){
		   
// 		   $(document).ajaxSend(function(){
// 			   alert("数据加载中..");
// 		   });  
		   $.post("storeAccount/getList",{
			   page:1,
			   pageSize:100,
			   storeNo:'<%=storeNo%>'
		   },function(result){
			   if(result.code == 0){
				   var tbody = $("tbody");
				   for(var i in result.data){
					   if(result.data[i].ctime >0 ){
						   result.data[i].ctime =  new Date(result.data[i].ctime*1000).Format("yyyy-MM-dd");
					   }
					   tbody.append($("<tr>")
							   .append($("<td>").text(result.data[i].id))
							   .append($("<td>").text(result.data[i].account))
							   .append($("<td>").text(result.data[i].name))
							   .append($("<td>").append($("<img width='30px;'>").attr("src",result.data[i].headImg)))
							   .append($("<td>").text(result.data[i].email))
							   .append($("<td>").text(result.data[i].mobile))
							   .append($("<td>").text(result.data[i].gender == 1?'男':'女'))
							   .append($("<td>").text(result.data[i].status == 1?'正常':'下线'))
							   .append($("<td>").text(result.data[i].valid == 1?'有效':'无效'))
							   .append($("<td>").text(result.data[i].ctime))
							   .append($("<td>").append($("<span>").attr("class","label label-success").
									   attr("data-id",result.data[i].id)
									   .text("编辑").on("click",function(){
								   alert("edit  "+$(this).attr("data-id"));
							   }))
									   ));
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
                      /&nbsp;&nbsp;<span>商户门店账户列表</span>
                 </div>
                 <div class="taps nav-tabs">
                     <span class="defaultSel">
                                                                    商户门店账户列表
                     </span>
                 </div>
                 <div class="content">
                    <div class="table-responsive panel-body">
						<table class="table table-hover">
							<thead class="navbar-inner">
								<tr>
									<th style="width:100px;">ID</th>
									<th style="width:150px;">账户</th>
									<th style="width:150px;">姓名</th>
									<th style="width:150px;">头像</th>
									<th style="width:100px;">邮箱</th>
									<th style="width:100px;">手机</th>
									<th style="width:100px;">性别</th>
									<th style="width:100px;">状态</th>
									<th style="width:100px;">是否有效</th>
									<th style="width:100px;">添加时间</th>
									<th style="width:200px;">操作</th>
								</tr>
							</thead>
							<tbody>
<!-- 								-->
							</tbody>
						</table>
	                 </div>
                 </div>
	       
</body>
</html>	