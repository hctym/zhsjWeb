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
    <title>添加优惠</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="keywords" content="">
	<meta name="description" content="">
	<link rel="shortcut icon" href="image/wechat.jpg">
	<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="js/lib/jquery-1.11.1.min.js"></script>
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
	.defaultSel{
	   background:#44b549;
	   color:#fff;
	   padding:10px;
	}
	.content{
	   margin-top:10px;
	   border:1px solid #ccc;
	   padding-top:20px;
	}
	</style>
	<script type="text/javascript">
	   $(function(){
		   var storeNo = "";
		   $.get("storeAccount/getStoreNoByAccountId",{id:<%=id%>},function(result){
           	if(result.code == 0){
           		storeNo = result.data.storeNo;
           	}else{
           		alert(result.msg);
           	}
           });
		   $("#submit").click(function(){
			   if($("#name").val() == ''){
				    alert("输入名称");
			    	return false;
			   }
			   if($("#content").val() == ''){
				    alert("输入内容");
			    	return false;
			   }
			   if($("#startTime").val() == ''){
				    alert("开始时间");
			    	return false;
			   }
			   if($("#endTime").val() == ''){
				    alert("结束时间");
			    	return false;
			   }
			    $.post("discount/add",{
			    	storeNo:storeNo,
			    	name:$("#name").val(),
			    	content:$("#content").val(),
			    	startTime:new Date($("#startTime").val()).getTime()/1000,
			    	endTime:new Date($("#endTime").val()).getTime()/1000,
			    	type:$("#type").val()
			    },function(data){
			    	if(data.code == 0){
			    		alert("添加优惠成功");
			    		location.href="page/discountList";
			    	}else{
			    		alert("添加优惠失败");
			    	}
			    });
			   
		   });
		   
		   
		   jeDate({
				dateCell:"#startTime",
				format:"YYYY-MM-DD hh:mm:ss",
				isinitVal:true,
				isTime:true, //isClear:false,
				minDate:"2014-09-19 00:00:00",
				okfun:function(t){
					$("#starttime").val(t);
				}
			});
			jeDate({
				dateCell:"#endTime",
				format:"YYYY-MM-DD hh:mm:ss",
				isinitVal:true,
				isTime:true, //isClear:false,
				minDate:"2014-09-19 00:00:00",
				okfun:function(t){
					$("#endtime").val(t);
				}
			});
		   
	   });
	 
	</script>
</head>
<body>
                 <div class="contentNav">
                      /&nbsp;&nbsp;<span>添加优惠</span>
                 </div>
                 <div class="taps nav-tabs">
                     <span class="defaultSel">
                                                                    添加优惠
                     </span>
                 </div>
                 
                 <div class="content">
				     <div class="form-horizontal ajaxfrom" role="form" id="form-user">
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">名称</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="name"  type="text" class="form-control" placeholder="输入名称">
								<span class="help-block">请输入名称</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">开始时间</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input type="text" class="form-control" id="startTime" placeholder="请输入开始时间"  readonly />
								<span class="help-block">请输入开始时间</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">结束时间</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input type="text" class="form-control" id="endTime" placeholder="请输入结束时间"  readonly />
								<span class="help-block">请输入结束时间</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">描述</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="content"  type="text" class="form-control" placeholder="输入描述">
								<span class="help-block">请输入描述</span>
							</div>
						</div>
						<div class="form-group">
								<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">折扣类型</label>
								<div class="col-sm-10 col-lg-9 col-xs-12">
									<select name="provincecode" class="form-control" id="type">
										<option value="1">固定立减</option>
										<option value="2">随机立减</option>
										<option value="3">门店折扣</option>
									</select>
								</div>
							</div>
						
					   <div class="form-group" style="margin-top:100px; text-align:center;">
							<div class="col-sm-offset-2 col-md-offset-2 col-lg-offset-1 col-xs-12 col-sm-10 col-md-10 col-lg-11">
								<input type="submit" id="submit" class="btn btn-primary span3" name="submit" value="保存">
							</div>
						</div>
					</div>
                 </div>
</body>
</html>	