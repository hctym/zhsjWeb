<%@ page language="java" import="java.util.*,com.zhsj.model.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String flag = (String)request.getSession().getAttribute("flag");
long orgId=0;
if("account".equals(flag)){
	Account account = (Account)request.getSession().getAttribute("user");
    Org org = account.getOrg();
    if(org != null){
    	orgId = account.getOrg().getId();
    }
}
%>

<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <base href="<%=basePath%>">
    <title>账户列表</title>
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
		   var page = 1;
		   var pageSize = 2;
		   load(page);
		   
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
			   $.post("account/getList",{
				   orgId:<%=orgId%>,
				   page:page,
				   pageSize:pageSize
			   },function(result){
				   if(result.code == 0){
					   $("table").find("tr:not(:first)").remove();
					   var tbody = $("tbody");
// 					   console.log(result);
					   var list = result.data.list;
					   for(var i in list){
							if(list[i].ctime >0 ){
								list[i].ctime =  new Date(list[i].ctime*1000).Format("yyyy-MM-dd hh:mm:ss");
							}
							console.log(result.data[i]);
						   tbody.append($("<tr>")
								   .append($("<td>").text(list[i].id))
								   .append($("<td>").text(list[i].account))
								   .append($("<td>").text(list[i].name))
								   .append($("<td>").text(list[i].headImg))
								   .append($("<td>").text(list[i].gender==1?'男':'女'))
								   .append($("<td>").text(list[i].mobile))
								   .append($("<td>").text(list[i].email))
								   .append($("<td>").text(list[i].role != null ?list[i].role.name:'没有角色'))
								   .append($("<td>").text(list[i].status==1?'启用':'禁用'))
								    .append($("<td>").text(list[i].valid==1?'有效':'无效'))
								   .append($("<td>").text(list[i].ctime))
								   .append($("<td>")
										   .append($("<span>").text("编辑").attr("data-id",list[i].id).on("click",function(){
											   alert($(this).attr("data-id")+"   编辑");
										   }))
										   .append($("<span>").text("删除").attr("data-id",list[i].id).on("click",function(){
											   alert($(this).attr("data-id")+"   删除");
										   }))
										   .append($("<span>").text("分配角色").attr("data-id",list[i].id).on("click",function(){
											   alert($(this).attr("data-id")+"   分配角色");
//	                                             window.location.href="page/modules?id="+$(this).attr("data-id");
										   }))
										   .append($("<span>").text("查看角色").attr("data-id",list[i].id).on("click",function(){
											   alert($(this).attr("data-id")+"   查看角色");
//	                                             window.location.href="page/modules?id="+$(this).attr("data-id");
										   }))));
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
                      /&nbsp;&nbsp;<span>用户账户列表</span>
                 </div>
                 <div class="taps nav-tabs clearfix">
                     <span class="defaultSel">
                                                                    用户账户列表
                     </span>
                     <a class="add" href="page/addAccount">
                                                                            添加用户
                     </a>
                 </div>
                 <div class="content">
                    <div class="table-responsive panel-body">
						<table class="table table-hover">
							<thead class="navbar-inner">
								<tr>
									<th style="width:50px;">账户id</th>
									<th style="width:100px;">账户</th>
									<th style="width:100px;">名称</th>
									<th style="width:50px;">头像</th>
									<th style="width:50px;">性别</th>
									<th style="width:100px;">手机</th>
									<th style="width:100px;">邮箱</th>
									<th style="width:100px;">角色</th>
									<th style="width:100px;">状态</th>
									<th style="width:50px;">是否有效</th>
									<th style="width:100px;">创建时间</th>
									<th style="width:200px;">操作</th>
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
                 <div style="float:right;">
			          <ul class="pagination"></ul>
			       </div>
	       
</body>
</html>	