<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
int id = (Integer)request.getAttribute("id");
%>

<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <base href="<%=basePath%>">
    <title>规则列表</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="keywords" content="">
	<meta name="description" content="">
	<link rel="shortcut icon" href="image/wechat.jpg">
	<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="js/lib/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/lib/jBootstrapPage.js"></script>
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
	.defaultSel, .add{
	   background:#44b549;
	   color:#fff;
	   padding:10px;
	   margin-right:10px;
	   cursor:pointer;
	   dispaly:block;
	}
	.defaultSel{
	   float:left;
	}
	.add{
	   float:right;
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
		   
// 		   $(document).ajaxSend(function(){
// 			   alert("数据疯狂加载中..");
// 		   }); 
		   
		   
			   $.post("discount/getRuleListByDiscountId",{
				   discountId:<%=id%>
			   },function(result){
				   if(result.code == 0){
					   $("table").find("tr:not(:first)").remove();
					   var tbody = $("tbody");
					   console.log(result);
// 					   return false;
					   var list = result.data;
					   for(var i in list){
							if(list[i].ctime >0 ){
								list[i].ctime =  new Date(list[i].ctime*1000).Format("yyyy-MM-dd hh:mm:ss");
							}
						   tbody.append($("<tr>")
								   .append($("<td>").text(list[i].id))
								   .append($("<td>").text(list[i].expendAmount))
								   .append($("<td>").text(list[i].discount1))
								   .append($("<td>").text(list[i].discount2))
								   .append($("<td>").text(list[i].ctime))
								   .append($("<td>")
										   .append($("<span>").text("删除").attr("data-id",list[i].id).on("click",function(){
											   alert($(this).attr("data-id")+"   删除");
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
                      /&nbsp;&nbsp;<span>规则列表</span>
                 </div>
                 <div class="taps nav-tabs clearfix">
                     <span class="defaultSel">
                                                                    规则列表
                     </span>
                     <a class="add" href="page/addRule?id=<%=id%>">
                                                                    添加规则
                     </a>
                 </div>
                 <div class="content">
                    <div class="table-responsive panel-body">
						<table class="table table-hover">
							<thead class="navbar-inner">
								<tr>
									<th style="width:50px;">id</th>
									<th style="width:100px;">满减</th>
									<th style="width:100px;">第一个参数</th>
									<th style="width:100px;">第二个参数</th>
									<th style="width:100px;">创建时间</th>
									<th style="width:200px;">操作</th>
								</tr>
							</thead>
							<tbody>
<!-- 					data			-->
							</tbody>
						</table>
	                 </div>
                 </div>
                 <div style="float:right;">
			          <ul class="pagination"></ul>
			       </div>
	       
</body>
</html>	