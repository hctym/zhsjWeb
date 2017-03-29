<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <title>活动列表</title>
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
	<script type="text/javascript" src="js/lib/jedate.min.js"></script>
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
	}
	.orderIdInput,.zhi,.end{
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
		   
		   
           var  page = 1,pageSize = 10;
           $("#startTime").val((new Date).Format("yyyy-MM-dd")+" 00:00");
           $("#endTime").val((new Date).Format("yyyy-MM-dd")+" 23:59");
           
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
           $("#serach").on("click",function(){
        	   page = 1;
        	   load(page);
           });
           load(page);
           function load(page){
	           $.post("discount/getListByParam",{
	        	   startTime:(new Date($("#startTime").val()).getTime())/1000,
	        	   endTime:(new Date($("#endTime").val()).getTime())/1000,
	        	   status:$("#status").val(),
	        	   type:$("#type").val(),
	        	   name:$("#name").val(),
	        	   page:page,
	        	   pageSize:pageSize
	           },function(result){
	        	   console.log(result);
	        	   if(result.code ==1){
	        		   alert(result.msg);
	        	   }else if(result.code == 0){
	        		   $("table").find("tr:not(:first)").remove();
					   var tbody = $("tbody");
					   var list = result.data.list;
					   for(var i in list){
							if(list[i].ctime >0 ){
								list[i].ctime =  new Date(list[i].ctime*1000).Format("yyyy-MM-dd hh:mm");
							}
						   var tr = $("<tr>")
								   .append($("<td>").text(list[i].id))
								   .append($("<td>").text(list[i].type==1?'满立减':list[i].type==2?'随机立减':'折扣'))
								   .append($("<td>").text(list[i].name))
								   .append($("<td>").text(new Date(list[i].startTime*1000).Format("yyyy-MM-dd hh:mm")+"-"+new Date(list[i].endTime*1000).Format("yyyy-MM-dd hh:mm")))
								   .append($("<td>").text(list[i].planAmount))
								   .append($("<td>").text(list[i].status==0?'未开始':list[i].status==1?'进行中':'已结束'));
						   var td = $("<td>").attr("data-id",list[i].id)
						   .append($("<span>").text("详情").on("click",function(){
							   alert("详情"+$(this).parent("td").attr("data-id"));
						   }));
						   
						   if(list[i].status != 2){
							   td.append($("<span>").text("结束")
									   .attr("data-status",list[i].status).on("click",function(){
								   $.post("discount/update",{
									   discountId:$(this).parent("td").attr("data-id")
								   },function(){
									   alert('操作成功');
									   location.reload();
								   });
							   }));
						   }
						   tbody.append(tr.append(td));
					   }
					   if(page == 1){
						    createPage(pageSize,result.data.count);
					  }
	        	   }
	           });
           }
	   })
	</script>
</head>
<body>
      
                 <div class="contentNav">
                      /&nbsp;&nbsp;<span>立减折扣活动</span>
                 </div>
                 <div class="taps nav-tabs clearfix">
                     <span class="defaultSel">
                                                                   活动筛选
                     </span>
                 </div>
                 <div class="condition">
		                 <div class="group clearfix">
		                    <label>活动时间</label>
		                    <div class="time clearfix">
		                       <div class="start">
		                          <input type="text" id="startTime" class="form-control" readonly onclick="jeDate({
		              				dateCell:'#startTime',
		            				format:'YYYY-MM-DD hh:mm',
		            				isinitVal:true,
		            				isTime:true, //isClear:false,
		            				minDate:'2014-09-19 00:00'
		            			});"/>
		                       </div>
		                       <div class="zhi">至</div>
		                       <div class="end">
		                          <input type="text" id="endTime" class="form-control" readonly onclick="jeDate({
				                   dateCell:'#endTime',
		            				format:'YYYY-MM-DD hh:mm',
		            				isinitVal:true,
		            				isTime:true, //isClear:false,
		            				minDate:'2014-09-19 00:00'
			                      });"/>
		                       </div>
		                    </div>
		                 </div>
		                 <div class="group clearfix">
		                   <div class="g-left">
			                    <label>活动状态</label>
			                    <div class="time " >
			                        <select id="status" class="form-control">
			                           <option value="0">选择活动状态</option>
			                           <option value="1">未开始</option>
			                           <option value="2">进行中</option>
			                           <option value="3">已结束</option>
			                        </select>
			                    </div>
		                    </div>
		                    <div class="g-right">
			                    <label>活动类型</label>
			                    <div class="time">
			                           <select id="type" class="form-control">
			                               <option value="0">选择活动类型</option>
			                               <option value="1">固定立减</option>
			                               <option value="2">随机立减</option>
			                               <option value="3">门店折扣</option>
			                           </select>
			                    </div>
		                    </div>
		                 </div>
		                 <div class="group clearfix">
		                    <div class="g-left">
			                    <label>活动名称</label>
			                    <div class="time">
			                       <div class="start orderIdInput" >
			                          <input type="text" class="form-control" id="name" style="width:300px"/>
			                       </div>
			                    </div>
		                    </div>
		                    <div>
			                    <div class="time">
			                       <div class="start">
			                          <input type="button" class="form-control" value="查询" id="serach"/>
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
									<th style="width:100px;">活动编号</th>
									<th style="width:100px;">活动方式</th>
									<th style="width:100px;">活动名称</th>
									<th style="width:100px;">活动时间</th>
									<th style="width:100px;">总预算</th>
									<th style="width:100px;">状态</th>
									<th style="width:100px;">操作</th>
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