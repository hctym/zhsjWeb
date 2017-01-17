<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
int id = (Integer)request.getAttribute("id");//优惠id
%>

<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <base href="<%=basePath%>">
    <title>添加规则</title>
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
		   $("#submit").click(function(){
			   if($("#expendAmount").val() == ''){
				    alert("输入满减");
			    	return false;
			   }
			   if($("#discount1").val() == ''){
				    alert("输入第一个参数");
			    	return false;
			   }
			   if($("#discount2").val() == ''){
				    alert("输入第二个参数");
			    	return false;
			   }
			    $.post("discount/addRule",{
			    	discountId:<%=id%>,
			    	expendAmount:$("#expendAmount").val(),
			    	discount1:$("#discount1").val(),
			    	discount2:$("#discount2").val()
			    },function(data){
			    	if(data.code == 0){
			    		alert("添加规则成功");
			    		location.href="page/discountList";
			    	}else{
			    		alert("添加规则失败");
			    	}
			    });
			   
		   });
		   
		   
		   
	   });
	 
	</script>
</head>
<body>
                 <div class="contentNav">
                      /&nbsp;&nbsp;<span>添加规则</span>
                 </div>
                 <div class="taps nav-tabs">
                     <span class="defaultSel">
                                                                    添加规则
                     </span>
                 </div>
                 
                 <div class="content">
				     <div class="form-horizontal ajaxfrom" role="form" id="form-user">
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">满减</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="expendAmount"  type="text" class="form-control" placeholder="输入满减">
								<span class="help-block">请输入满减</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">第一个参数</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="discount1"  type="text" class="form-control" placeholder="输入第一个参数">
								<span class="help-block">请输入第一个参数</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">第二个参数</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="discount2"  type="text" class="form-control" placeholder="输入第二个参数">
								<span class="help-block">请输入第二个参数</span>
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