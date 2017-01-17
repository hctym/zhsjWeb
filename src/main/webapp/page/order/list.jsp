<%@ page language="java" import="java.util.*,com.zhsj.model.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String flag = (String)request.getSession().getAttribute("flag");
String url = "";
if("account".equals(flag)){
	url="order/getListByPageAndParamOrgId";
}else{
	url="order/getListByPageAndParamStoreNo";
}
%>

<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <base href="<%=basePath%>">
    <title>订单列表</title>
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
		   
		   

           $("#startTime").val((new Date).Format("yyyy-MM-dd")+" 00:00");
           $("#endTime").val((new Date).Format("yyyy-MM-dd")+" 23:59");
           
           
           $("#serach").on("click",function(){
        	    load(1);
           });
		   var page = 1;
		   var pageSize = 10;
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
			   var options = {
					   startTime:$("#startTime").val() != ""?(new Date($("#startTime").val()).getTime()/1000):0,
					   endTime:$("#endTime").val() != ""?(new Date($("#endTime").val()).getTime()/1000):0,
					   payType:$("input[name=payType]:checked").val(),
					   payMethod:$("input[name=payMethod]:checked").val(),
					   status:$("input[name=payStatus]:checked").val(),
					   startAmount:$("#startAmount").val(),
					   endAmount:$("#endAmount").val(),
					   orderId:$("#orderId").val()
			   };
// 			   console.log($.extend(options,{page:page,pageSize:pageSize}));
// 			   return false;
			   $.post('<%=url%>',$.extend(options,{
				   page:page,
				   pageSize:pageSize
			   }),function(result){
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
							console.log(result.data[i]);
						   tbody.append($("<tr>")
								   .append($("<td>").text(list[i].orderId))
								   .append($("<td>").text(list[i].user != null?list[i].user.nickName:""))
								   .append($("<td>").text(list[i].store != null?list[i].store.name:""))
								   .append($("<td>").text(list[i].planChargeAmount))
								   .append($("<td>").text(list[i].actualChargeAmount))
								   .append($("<td>").text(list[i].discountType))
								   .append($("<td>").text(list[i].discountId))
								   .append($("<td>").text(list[i].ctime))
								   .append($("<td>").text(list[i].status==0?"支付中":list[i].status==1?'支付成功':'支付失败'))
								   .append($("<td>")
										   .append($("<span>").text("查看").attr("data-id",list[i].id).on("click",function(){
											   alert($(this).attr("data-id")+"   查看");
										   }))
// 										   .append($("<span>").text("申请退款").attr("data-id",list[i].id).on("click",function(){
// 											   alert($(this).attr("data-id")+"   申请退款");
// 										   }))
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
                      /&nbsp;&nbsp;<span>订单列表</span>
                 </div>
                 <div class="taps nav-tabs clearfix">
                     <span class="defaultSel">
                                                                    订单列表
                     </span>
                 </div>
                 <div class="condition">
		                 <div class="group clearfix">
		                    <label>结算日期</label>
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
			                    <label>支付类型</label>
			                    <div class="time clearfix" >
			                       <div class="start">
			                          <label><input type="radio" name="payType" value="0" checked />全部</label>
			                       </div>
			                       <div class="start">
			                          <label><input type="radio" name="payType" value="1"/>中信</label>
			                       </div>
			                       <div class="end">
			                          <label><input type="radio" name="payType" value="2"/>其他</label>
			                       </div>
			                    </div>
		                    </div>
		                    <div class="g-right">
			                    <label>支付方法</label>
			                    <div class="time clearfix">
			                       <div class="start">
			                          <label><input type="radio" name="payMethod" value="0"checked/>全部</label>
			                       </div>
			                       <div class="start">
			                          <label><input type="radio" name="payMethod" value="1"/>微信</label>
			                       </div>
			                       <div class="end">
			                          <label><input type="radio" name="payMethod" value="2"/>支付宝</label>
			                       </div>
			                    </div>
		                    </div>
		                 </div>
		                 <div class="group clearfix">
		                     <div class="g-left">
				                    <label>支付状态</label>
				                    <div class="time clearfix">
				                       <div class="start">
				                          <label><input type="radio" name="payStatus" value="0" checked/>支付中</label>
				                       </div>
				                       <div class="start">
				                          <label><input type="radio" name="payStatus" value="1" />支付成功</label>
				                       </div>
				                       <div class="end">
				                          <label><input type="radio" name="payStatus" value="2" />支付失败</label>
				                       </div>
				                    </div>
		                    </div>
		                    <div class="g-right">
			                    <label>金额范围</label>
			                    <div class="time clearfix">
			                       <div class="start">
			                          <input type="text" class="form-control" id="startAmount"/>
			                       </div>
			                       <div class="zhi">至</div>
			                       <div class="end">
			                          <input type="text" class="form-control" id="endAmount"/>
			                       </div>
			                    </div>
		                    </div>
		                 </div>
		                 <div class="group clearfix">
		                    <div class="g-left">
			                    <label>订单号</label>
			                    <div class="time">
			                       <div class="start">
			                          <input type="text" class="form-control" id="orderId" style="width:300px"/>
			                       </div>
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
									<th style="width:100px;">订单号</th>
									<th style="width:100px;">付款者名称</th>
									<th style="width:100px;">收款台名称</th>
									<th style="width:100px;">应付金额</th>
									<th style="width:100px;">实付金额</th>
									<th style="width:100px;">折扣类型</th>
									<th style="width:100px;">优惠类型</th>
									<th style="width:200px;">交易时间</th>
									<th style="width:100px;">交易状态</th>
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