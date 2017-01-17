<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.zhsj.model.StoreAccount"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
StoreAccount storeAccount = (StoreAccount)request.getSession().getAttribute("user");
long id = storeAccount.getId();
%>

<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <base href="<%=basePath%>">
    <title>优惠列表</title>
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
		   var page = 1,
		       pageSize = 10,
		       storeNo = "";
            $.get("storeAccount/getStoreNoByAccountId",{id:<%=id%>},function(result){
            	if(result.code == 0){
            		storeNo = result.data.storeNo;
		            load(page,result.data.storeNo);
            	}else{
            		alert(result.msg);
            	}
            });
		   
		   function createPage(pageSize, total) {
		        $(".pagination").jBootstrapPage({
		            pageSize : pageSize,
		            total : total,
		            maxPageButton:5,
		            onPageClicked: function(obj, pageIndex) {
		            	page++;
		                load((pageIndex+1));
		            }
		        });
		    }
		   function load(page){
			   $.post("discount/getListByPage",{
				   storeNo:storeNo,
				   page:page,
				   pageSize:pageSize
			   },function(result){
				   if(result.code == 0){
					   $("table").find("tr:not(:first)").remove();
					   var tbody = $("tbody");
					   console.log(result);
// 					   return false;
					   var list = result.data.list;
					   for(var i in list){
							if(list[i].ctime >0 ){
								list[i].ctime =  new Date(list[i].ctime*1000).Format("yyyy-MM-dd hh:mm:ss");
							}
						   tbody.append($("<tr>")
								   .append($("<td>").text(list[i].id))
								   .append($("<td>").text(list[i].name))
								   .append($("<td>").text(new Date(list[i].startTime*1000).Format("yyyy-MM-dd hh:mm:ss")))
								   .append($("<td>").text(new Date(list[i].endTime*1000).Format("yyyy-MM-dd hh:mm:ss")))
								   .append($("<td>").text(list[i].valid == 1?"有效":"无效"))
								   .append($("<td>").text(list[i].ctime))
								   .append($("<td>").text(list[i].type == 1?'固定立减':list[i].type==2?'随机立减':'门店折扣'))
								   .append($("<td>")
										   .append($("<span>").text("添加规则").attr("data-id",list[i].id).on("click",function(){
// 											   alert($(this).attr("data-id")+"   添加规则");
											   location.href="page/addRule?id="+$(this).attr("data-id");
										   }))
										    .append($("<span>").text("查看规则").attr("data-id",list[i].id).on("click",function(){
// 											   alert($(this).attr("data-id")+"   查看规则");
											   location.href="page/ruleList?id="+$(this).attr("data-id");
										   }))
										   .append($("<span>").text("查看详情").attr("data-id",list[i].id).on("click",function(){
											   alert($(this).attr("data-id")+"   查看详情");
										   }))
										   ));
					   }
					   if(page == 1){
						    createPage(pageSize,result.data.count);
					  }
				   }else{
					   alert(result.msg);
				   }
			   }); 
			   
		   }
		   
	   });
	 
	</script>
</head>
<body>
      
                 <div class="contentNav">
                      /&nbsp;&nbsp;<span>优惠列表</span>
                 </div>
                 <div class="taps nav-tabs clearfix">
                     <span class="defaultSel">
                                                                    优惠列表
                     </span>
                     <a class="add" href="page/addDiscount">
                                                                    添加优惠
                     </a>
                 </div>
                 <div class="content">
                    <div class="table-responsive panel-body">
						<table class="table table-hover">
							<thead class="navbar-inner">
								<tr>
									<th style="width:50px;">id</th>
									<th style="width:100px;">名称</th>
									<th style="width:100px;">开始时间</th>
									<th style="width:100px;">结束时间</th>
									<th style="width:100px;">是否有效</th>
									<th style="width:100px;">创建时间</th>
									<th style="width:100px;">优惠类型</th>
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