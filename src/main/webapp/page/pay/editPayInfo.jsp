<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
int id = (Integer)request.getAttribute("id");
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>编辑支付方式</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
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
  </head>
  
  <body>
    <div class="contentNav">
                      /&nbsp;&nbsp;<span>编辑支付方式</span>
                 </div>
                 <div class="taps nav-tabs">
                     <span class="defaultSel">
                                                                    编辑支付方式
                     </span>
                 </div>
                 
                 <div class="content">
				     <div class="form-horizontal ajaxfrom" role="form" id="form-user">
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">支付类型</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<select id="payType" class="form-control">
			                           <option value="2">民生</option>
			                   </select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">支付方法</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
			                       <span>
			                          <label><input type="checkbox" name="payMethod" value="1"/>微信</label>
			                       </span>
			                       <span style="margin-left:50px;">
			                          <label><input type="checkbox" name="payMethod" value="2"/>支付宝</label>
			                       </span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">field1</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="field1" name="field1" type="text" class="form-control" placeholder="输入field1">
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">field2</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="field2" name="field2" type="text" class="form-control" placeholder="输入field2">
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">费率</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="remark" name="remark" type="text" class="form-control" placeholder="输入费率">
							</div>
						</div>
						
					   <div class="form-group">
							<div class="col-sm-offset-2 col-md-offset-2 col-lg-offset-1 col-xs-12 col-sm-10 col-md-10 col-lg-11">
								<input type="submit" id="submit" class="btn btn-primary span3" name="submit" value="修改">
							</div>
						</div>
					</div>
                 </div>
  </body>
</html>
<script type="text/javascript">
  $(function(){
	  $.post("store/getPayInfoById",{
		  id:<%=id%>
	  },function(result){
		  if(result.code == 0){
			  var obj = result.data;
			  if(obj.payMethod.indexOf(",") > -1){
				  var pm = (obj.payMethod).split(","),pmLen = pm.length;
				  for(var i =0;i<pmLen;i++){
					 $($("input[name=payMethod]")[Number(pm[i])-1]).attr("checked","true");
				  }
			  }else{
				  $($("input[name=payMethod]")[Number(obj.payMethod)-1]).attr("checked","true");
			  }
			  $("#field1").val(obj.field1);
			  $("#field2").val(obj.field2);
			  $("#remark").val(obj.remark);
		  }else{
			  alert("加载支付方式失败");
		  }
	  })
	  
	  
	  $("#submit").on("click",function(){
		    var payM =  $("input[name=payMethod]:checked"),pm = [];
		    for(var i = 0,len = payM.length;i < len;i++){
		    	   pm.push($(payM[i]).val());
		    }
		    if(pm.length == 0){
		    	alert("请选择支付方式");
		    	return false;
		    }
		    if($("#field1").val() == ""){
		    	alert("field1 不能为空");
		    	return false;
		    }
		    if($("#field2").val() == ""){
		    	alert("field2 不能为空");
		    	return false;
		    }
		    if($("#remark").val() == ""){
		    	alert("remark 不能为空");
		    	return false;
		    }
		    $.post("store/updateStorePayInfo",{
		    	id:<%=id%>,
		    	payType:$("#payType").val(),
		    	payMethod:pm.join(),
		    	field1:$("#field1").val(),
		    	field2:$("#field2").val(),
		    	remark:$("#remark").val()
		    },function(result){
		    	console.log(result);
		    	if(result.code == 0){
		    		alert("编辑支付方式成功");
		    		location.reload();
		    	}else{
		    		alert("编辑支付方式失败");
		    	}
		    });
	  });
  });   
</script>

