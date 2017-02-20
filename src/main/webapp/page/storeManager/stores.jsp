<%@ page language="java" import="java.util.*,com.zhsj.model.*" pageEncoding="utf-8"%>
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
	<script type="text/javascript" src="js/lib/jBootstrapPage.js"></script>
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
    color:#fff;
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
	   display:block;
	}
	.defaultSel{
	   float:left;
	}
	.add{
	   float:right;
	   cursor:pointer;
	}
	.content{
	   margin-top:10px;
	   border:1px solid #ccc;
	}
	.condition{
	  padding:10px;
	}
	.condition .group{
	  padding-bottom:5px;
	}
	.group label,.group .time{
	   float:left;
	}
	.group label{
	   line-height:3;
	}
	.group .time{
	  margin-left:15px;
	}
	.start,.zhi,.end{
	   float:left;
	   margin-left:10px;
	}
	.zhi{
	  text-align:center;
	  line-height:3;
	}
	.group input[type=radio]{
	    margin: 10px;
        width: 20px;
	}
	.g-left,.g-right{
	  width:45%;
	}
	.g-left{
	  float:left;
	}
	.g-right{
	  float:right;
	}
	.time label{
	  font-weight:100;
	  cursor:pointer;
	}
	</style>
	<script type="text/javascript">
	   $(function(){
		   
		   var page  = 1,pageSize = 5;
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
		   
		   //搜索
		   $("#serach").on("click",function(){
		    	load(1);
		   });
		    //默认加载
		   load(page);
		   //加载的函数
		   function load(page,obj){
			       var params = {
// 			    		   status:$("input[name='status']:checked").val(),
                           status:$("#status").val(),
						   page:page,
						   pageSize:pageSize
					   };
				   $.post("store/getListByStoreNo",params,function(result){
					   if(result.code == 0){
						   var tbody = $("tbody");
						   $("table").find("tr:not(:first)").remove();
						   var list = result.data.list,count =result.data.count;
						   for(var i in list){
							   if(list[i].ctime >0 ){
								   list[i].ctime =  new Date(list[i].ctime*1000).Format("yyyy-MM-dd");
							   }
							   tbody.append($("<tr>")
									   .append($("<td>").text(list[i].id))
									   .append($("<td>").text(list[i].name))
									   .append($("<td>").append($("<img>").prop("src","<%=basePath%>"+list[i].shopLogo).width("30px").error(function(){
										   $(this).attr("onerror","javascript:this.src='image/nopic.jpg';");
									   })))
									   .append($("<td>").text(list[i].phone))
									   .append($("<td>").text(list[i].status == 1?'正常':'下线'))
									   .append($("<td>").text(list[i].ctime))
									   .append($("<td>").attr("data-storeno",list[i].storeNo).append($("<span>").attr("class","label label-success").
											   attr("data-id",list[i].id)
											   .text("编辑").on("click",function(){
										   window.location.href="page/editStore?id="+$(this).attr("data-id");
									   })).append($("<span style='margin-left:10px;'>").attr("class","label label-info")
													   .attr("data-id",list[i].id)
													   .attr("data-status",list[i].status)
													   .text(list[i].status == 1?"下线":"上线").on("click",function(){
														   $.post("store/update",{
															   id:$(this).attr("data-id"),
															   status:$(this).attr("data-status") == 1?'2':'1'
														   },function(result){
															   alert("修改成功");
															   location.reload();
														   });
													   })).append($("<span style='margin-left:10px;'>").attr("class","label label-info")
															   .attr("data-id",list[i].id).text("添加用户账号").on("click",function(){
																   window.location.href="page/addStoreAccount?storeNo="+$(this).parent().attr("data-storeno");
															   })).append($("<span style='margin-left:10px;'>").attr("class","label label-info")
																	   .attr("data-id",list[i].id).text("查看用户账号").on("click",function(){
				 														     window.location.href ="page/storeAccountList?storeNo="+$(this).parent().attr("data-storeno");
																	   }))));
						   }
						   if(page == 1){
							    createPage(pageSize,count);
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
                      /&nbsp;&nbsp;<span>门店列表</span>
                 </div>
                 <div class="taps nav-tabs clearfix">
                     <span class="defaultSel">
                                                                    门店列表
                     </span>
                     <a class="add" href="page/addcStore">
                                                                      添加子门店
                     </a>
                 </div>
                 <div class="condition">
		                 <div class="group clearfix">
		                   <div class="g-left">
			                    <label>状态</label>
			                    <div class="time clearfix" >
			                        <select id="status" class="form-control">
			                           <option value="0">全部</option>
			                           <option value="1">上线</option>
			                           <option value="2">下线</option>
			                        </select>
<!-- 			                       <div class="start"> -->
<!-- 			                          <label><input type="radio" name="status" value="0" checked />全部</label> -->
<!-- 			                       </div> -->
<!-- 			                       <div class="start"> -->
<!-- 			                          <label><input type="radio" name="status" value="1"/>上线</label> -->
<!-- 			                       </div> -->
<!-- 			                       <div class="end"> -->
<!-- 			                          <label><input type="radio" name="status" value="2"/>下线</label> -->
<!-- 			                       </div> -->
			                    </div>
		                    </div>
		                    <div>
			                    <div class="time">
			                       <div class="start">
			                          <input type="button" class="form-control" value="搜索" id="serach"/>
			                       </div>
			                    </div>
		                    </div>
		                 </div>
                 </div>
                 <div class="content">
                    <div class="table-responsive panel-body">
						<table class="table table-hover">
							<thead class="navbar-inner">
								<tr>
									<th style="width:100px;">ID</th>
									<th style="width:150px;">商户名称</th>
									<th style="width:150px;">商户logo</th>
									<th style="width:150px;">电话</th>
									<th style="width:100px;">状态</th>
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
                  <div style="float:right;">
			          <ul class="pagination"></ul>
			       </div>
	       
</body>
</html>	