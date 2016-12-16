<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
int id = (Integer)request.getAttribute("id");//门店id
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
	   padding-top:20px;
	}
	</style>
	<script type="text/javascript">
	   $(function(){
			    $.post("businessInfo/getById",{
			    	id:<%=id%>
			    },function(result){
			    	console.log(result);
			    	if(result.code == 0){
			    		$("#shopName").val(result.data.shopName);
			    		$("#address").val(result.data.address);
			    		if(result.data.iscooperate == 0){
			    			$("input[value='0']").prop("checked",true);
			    		}else{
			    			$("input[value='1']").prop("checked",true);
			    		}
			    		$("#contact").val(result.data.contact);
			    		$("#contactPhone").val(result.data.contactPhone);
			    		$("#latitude").val(result.data.latitude);
			    		$("#longitude").val(result.data.longitude);
			    		$("#intro").text(result.data.intro);
			    	}else{
			    		alert(result.msg);
			    	}
			    });
			   
		   
		   
		   
		   
		   
	   });
	 
	</script>
</head>
<body>
                 <div class="contentNav">
                      /&nbsp;&nbsp;<span>商家信息</span>
                 </div>
                 <div class="taps nav-tabs">
                     <span class="defaultSel">
                                                                    商家信息
                     </span>
                 </div>
                 
                 <div class="content">
				     <div class="form-horizontal ajaxfrom" role="form" id="form-user">
						<div class="form-group">
					<label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">
						门店名称
					</label>
					<div class="col-sm-8 col-lg-9 col-xs-12">
						<input type="text" class="form-control" id="shopName" value="" placeholder="门店名称" readonly>
					</div>
				</div>
				
               <div class="form-group">
					<label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">
						是否是合作母公司
					</label>
					<div class="col-sm-10 col-lg-9 col-xs-12">
								<label class="radio-inline"><input type="radio" name="iscooperate" value="0" readonly> 否</label>
								<label class="radio-inline"><input type="radio" name="iscooperate" value="1" readonly> 是</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">
						详细地址
					</label>
					<div class="col-sm-8 col-lg-9 col-xs-12">
						<input type="text" class="form-control" id="address" value="" placeholder="详细地址" readonly>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">
						联系人
					</label>
					<div class="col-sm-8 col-lg-9 col-xs-12">
						<input type="text" class="form-control" id="contact" value="" placeholder="联系人" readonly>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">
						联系电话
					</label>
					<div class="col-sm-8 col-lg-9 col-xs-12">
						<input type="text" class="form-control" id="contactPhone" value="" placeholder="联系电话" readonly>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">经纬度</label>
					<div class="col-sm-9 col-xs-12">
						<input type="text" id="latitude" id="latitude" class="form-control" value="" readonly>
						<input type="text" id="longitude" id="longitude" class="form-control" value="" readonly>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">
						门店简介
					</label>
					<div class="col-sm-8 col-lg-9 col-xs-12">
					
					<textarea rows="5" cols="40" id="intro" readonly></textarea>
									</div>
				</div>
				</div>
       </div>
</body>
</html>	