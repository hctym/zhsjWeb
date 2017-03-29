<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>创建折扣</title>
    
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
	.ultap ul{
	  list-style:none;
	}
	.ultap ul li{
	  width:27%;
	  text-align:center;
	  float:left;
	  font-size:18px;
	  position:relative;
	  color:#000;
	  border:1px solid #CCC;
	  margin-right:40px;
	}
	
	.liselect{
	  background:#44b549 !important;
	  color:#FFF !important;
	}
	.ultap ul li span{
	  padding:10px 0; 
	  display:block;
	}
	.ultap ul li:after{
	   content:'';
	   position:absolute;
	   right:-17px;
	   width:32px;
	   height:32px;
	   background:#f9f9f9;
	   top:6px;
	   -webkit-transform:rotate(45deg);
	   -moz-transform:rotate(45deg);
	   -o-transform:rotate(45deg);
	   transform:rotate(45deg);
	   border-right:1px solid #CCC;
	   border-top:1px solid #CCC;
	}
	
	.liselect:after{
	   background:#44b549 !important;
	}
	.clear:after{
	  content:'';
	  display:table;
	  clear:both;
	}
	.detail,
	.usestyle,
	.qrcreate{
	   padding:30px;
	}
	.baseinfo{
	   padding:10px;
	   border:1px solid #CCC;
	}
	.start, .end{
	  float:left;
	}
	.zhi{
	  text-align:center;
	  float:left;
	  margin:0 40px;
	}
	.baseinfo input[type=text],
	.baseinfo input[type=number]{
	  width:200px !important;
	}
	.ys_sum div{
	  float:left;
	}
	.showm{
	   line-height: 2.8;
       padding-left: 20px;
	
	}
	.ruleList{
	    padding-left: 150px;
	}
	.fixed_minus_rule,
	.random_minus_rule{
	    font-weight: bold;
	    font-size: 1.2em;
	}
	.fixed_rule,
	.random_rule{
	   padding-left: 90px;
       padding-top: 20px;
	}
	.rulegroup{
	   padding-top:10px;
	}
	.rule_ys label,.rule_ys .input,.rule_ys .rule_font_m{
	   float:left;
	   padding-right:20px;
	   line-height: 2.8;
	} 
	.rule_ys label{
	  font-weight:500;
	}
	.rule_consume_row,.rule_btns{
	  padding-top:15px;
	}
	.rule_consume_row input,.rule_consume_row span{
	  display:inline;
	}
	.rule_consume_row span:not(:first-child){
	  padding:0 10px;
	}
	.rule_consume_row .money{
	      margin-left: 5px;
	}
	.rule_btns{
	    padding-left: 90px;
        padding-top: 20px;
	    color:blue;
	    margin-left:40px;
	    cursor:pointer;
	    font-size:1.1em;
	}
	.rule_btns span:last-child{
	   padding-left:20px;
	}
	.random_rule .rule_ys label{
	      padding-right: 30px !important;
	}
	.rule_minus_interval input:first-child{
	       margin-left: 3px !important;
	}
	.random_rule .rule_consume .money{
	      margin-left: 15px;
	}
	.random_rule .rule_consume_row{
	   position:relative;
	}
	.count,.average{
        position: absolute;
	    right: 200px;
	    top: 15px;
	}
	.hide{
	  display:none;
	}
	.show{
	  display:block;
	}
	
	.one_next,
	.two_next,
	.three_next{
	   margin-top:30px;
	   margin-bottom:10px;
	   text-align:center;
	}
	.one_next span,
	.two_next span,
	.three_next span{
	   padding:8px 10px;
	   background:#44b549;
	   color:#FFF;
	   font-size:1.2em;
/* 	   font-weight:bold; */
	   box-shadow:0 0 5px #44b549;
	}
	
	.usestyle,.qrcreate{
	  display:none;
	}
	.two_next span:first-child,
	.three_next span:first-child{
	   margin-right:10px;
	}
	.two_next span:last-child,
	.three_next span:last-child{
	   margin-left:10px;
	   background:#CCC !important;
	   color:#000 !important;
	   box-shadow:0 0 5px #CCC;
	}
	/*----usestyle----*/ 
	.usestyle{
	
	}
	.usestyle_content{
	  margin-left:150px;
	}
	.usestyle_content > p{
	  padding: 10px 0;
	}
	.usestyle_content > p{
	  font-size:1.2em;
/* 	  font-weight:bold; */
	}
	
	.jt_operate  {
	      padding-left: 10px;
	}
	.jt_operate .jtgroup{
	    padding-bottom:10px;
	}
	.jt_operate .jtgroup label,
	.jt_operate .jtgroup .jtright{
	   float:left;
	}
	.jt_operate .jtgroup .jtright{
	   margin-left:20px;
	}
	.jtright p{
	   padding:5px 0;
	}
	.jtright label{
	  font-weight:500;
	  margin:0 10px;
	}
	
	.qrcreate_content,.endRuleList,.minus_limit{
	   margin-left:150px;
	   font-size:1.2em;
	}
	.qrcreate_content .qrgroup{
	   padding:5px 0;
	}
	.qrgroup > label,
	.qrgroup > div{
	  display:inline-block;
	}
	.qrgroup > label{
	  width:10%;
	  text-align:right;
	}
	.qrgroup > div{
	   margin-left:30px;
	}
	.ky_stores{
	}
	.ky_stores >label,
	.ky_stores > div{
	   float:left;
	}
	.endRuleList{
	  margin-top:20px;
	  border-top:1px solid #CCC;
	}
	.end_rule > label{
	   padding:10px 0;
	   width:10%;
	   text-align:right;
	   float:left;
	}
	.end_rule .endrule_detail{
	   float:left;
	   margin-left: 45px;
	   width:80%;
	}
	.end_rule .endrule_detail .endrule_row{
	      padding: 10px 0;
	}
	.endrule_row .row >label,
	.endrule_row .row >div {
	   display:inline-block;
	}
	.endrule_row .row:last-child{
	   float:right;
	   text-align:left;
	   width:50%;
	}
	
	.endrule_row .row:first-child{
	   float:left;
	}
	.minus_limit{
	   border-top:1px solid #CCC;
	   margin-top:20px;
	}
	.minus_limit .limitrow{
	    padding: 10px 0;
	}
	.minus_limit .limitrow > label{
	    width: 10%;
	    text-align: right;
	    float: left;
	}
	.minus_limit .limitrow > div{
	   float: left;
	   margin-left: 32px;
	}
	</style>
	<script type="text/javascript">
		  $(function(){
			  
			  var ljInfo = {};//用于存放立减信息
			  
			  
			  //添加固定立减规则
			  $("#add_fixed_rule").on("click",function(){
				  var s = '<div class="rulegroup">'
		           +'<div class="rule_ys clear">'
		           +' <label>预算</label>'
		           +'<div class="input">'
		           +' <input type="number" class="form-control fixed_ys_lj_money">'
		           +' </div>'
		           +'<div class="rule_font_m">'
		           +'   <span>元</span>'
		           +' </div>'
		           +' </div>'
		           +'<div class="rule_consume">'
		           +'  <div class="rule_consume_row">'
		           +'   <span>消费满</span>'
		           +'  <input type="number" class="form-control money"/>'
		           +'   <span>元，享受</span>'
		           +'  <input type="number" class="form-control"> '
		           +'    <span>折</span>'
		           +'   </div>'
		           +' </div>'
		           +' </div>';
		           
		           $(".fixed_rule").append(s);
		           $(".fixed_ys_lj_money").on("blur",function(){
		        	   cursorleave();
		           });
			  });
			  
			  //删除固定立减规则
              $("#del_fixed_rule").on("click",function(){
            	  var rules = $(".fixed_rule").find(".rulegroup"),len=rules.length;
            	  if(len==1){
            		  alert("只有一条了不能被删除。");
            		  return false;
            	  }else{
            		  $(rules[len-1]).remove();
            		  cursorleave();
            	  }
			  });
              
              $(".fixed_ys_lj_money").on("blur",function(){
            	  cursorleave();
              });
              
              function cursorleave(){
            	  var moneys = $(".fixed_ys_lj_money"),
        	      moneylen = moneys.length,
        	      sum = 0;
            	  console.log(moneylen);
	        	  for(var i = 0;i<moneylen;i++){
	        		  sum += Number($(moneys[i]).val());
	        	  }
	        	    $("#sum_ys").val(sum);
	          }
              
              
              
              
              //下一步(第一个)
              $("#oneNext").on("click",function(){
            	  if($("#minusName").val() == ""){
            		  alert("折扣名称不能为空");
            		  return false;
            	  }
            	  if($("#startTime").val() == "" || $("#endTime").val() == ""){
            		  alert("有效期时间不能为空");
            		  return false;
            	  }
            	  if(new Date($("#startTime").val()).getTime() < new Date(new Date()).getTime()){
            		  alert("开始时间不能小于当前时间");
            		  return false;
            	  }
            	  if(new Date($("#startTime").val()).getTime() > new Date($("#endTime").val())){
            		  alert("结束时间不能小于开始时间");
            		  return false;
            	  }
            	  var ruleList = [];
            	  
           		  var fixedrules = $(".fixed_rule").find(".rulegroup"),len = fixedrules.length;
           	      for(var i = 0;i < len;i ++){
           	    	  var rule = {};
           	    	  var inputs = $(fixedrules[i]).find("input"),inputlen = inputs.length;
           	    	  for(var j = 0;j < inputlen;j ++){
           	    		  if($(inputs[j]).val() == ""){
           	    			  alert("规则不能有空值");
           	    			  return false;
           	    		  }
           	    		  rule['f'+j] = $(inputs[j]).val();
           	    	  }
           	    	  ruleList.push(rule);
           	      }
            	  
            	  ljInfo.name = $("#minusName").val();
            	  ljInfo.type = 3;
            	  ljInfo.startTime = $("#startTime").val();
            	  ljInfo.endTime = $("#endTime").val();
            	  ljInfo.rules = ruleList;
            	  ljInfo.sum = $("#sum_ys").val();
//             	  console.log(ljInfo);
//             	  return false;
            	  $(".ultap").find("li").removeClass("liselect");
            	  $($(".ultap").find("li")[1]).addClass("liselect");
            	  $(".detail").hide();
            	  $(".usestyle").show();
              });
              //useStyle
              
              //下一步(第二个)
              $("#twoNext").on("click",function(){
            	  var storeNos =  $("#storeNos").val();
            	  if(storeNos == ''){
            		  alert("请添加商户编号");
            		  return false;
            	  }
            	  var stores = storeNos.replace(/\n/g, '@');//对textarea 的处理
            	  ljInfo.storeNos = stores.split("@");
            	  var paystyles = $("input[name=paystyle]:checked"),len = paystyles.length;
            	  var paylist = [];
            	  if(len == 0){
            		  alert("请选择支付方式");
            		  return false;
            	  }else{
            		  for(var i =0;i<len;i++){
            			  if($(paystyles[i]).val() == 1){//wechat
            				  paylist.push(1);
            			  }else if($(paystyles[i]).val() == 2){//alipay
            				  paylist.push(2);
            			  }
            		  }
            	  }
            	  ljInfo.payStyle = paylist;
            	  if($("input[name=astyle]:checked").val() == 3){
            		  ljInfo.aStyle = 3;
            	  }else{
            		  ljInfo.aStyle = 1;//总部
            	  }
//             	  console.log(ljInfo);
//             	  return false;
            	  
            	  $(".ultap").find("li").removeClass("liselect");
            	  $($(".ultap").find("li")[2]).addClass("liselect");
            	  $(".detail").hide();
            	  $(".usestyle").hide();
            	  //确认start
            	    $("#qr_ljStyle").text(ljInfo.name);
            	    $("#qr_startTime").text(ljInfo.startTime);
            	    $("#qr_endTime").text(ljInfo.endTime);
            	    $("#qr_use_storeNo").empty();//清空
            	    for(var i = 0,len = ljInfo.storeNos.length;i < len;i ++){
            	    	$("#qr_use_storeNo").append($("<p>").text(ljInfo.storeNos[i]));
            	    }
            	    $("#qr_sum_ys").text(ljInfo.sum);
            	    $("#limitUse").text(ljInfo.astyle == 1?'已关闭':'已开启');
            	    $("#limit_payStyle").empty();//
            	    for(var i =0,len= ljInfo.payStyle.length;i <len;i++){
            	    	if(ljInfo.payStyle[i] == 1){
            	    		$("#limit_payStyle").append($("<span style='margin:0 10px;'>").text('微信支付'));
            	    	}else if(ljInfo.payStyle[i] == 2){
            	    		$("#limit_payStyle").append($("<span style='margin:0 10px;'>").text('支付宝支付'));
            	    	}
            	    }
            	    //规则
            	    $(".endRuleList").empty();//清空
            	    	var rules = ljInfo.rules;
            	    	for(var i =0,len=rules.length;i<len;i++){
            	    		var s = '<div class="end_rule clear">'
                            +'<label>折扣规则</label>'
                            +' <div class="endrule_detail">'
                            +'<div class="endrule_row clear">'
                            +'  <div class="row">'
                            +'     <label>预算</label>'
                            +'      <div>'
                            +'          <span>'+rules[i].f0+'</span>元'
                            +'          </div>'
                            +'     </div>'
                            +'     </div>'
                                +'    <div class="endrule_row clear">'
                                +'     <div class="row">'
                                +'     <label>消费满</label>'
                                +'     <div>'
                                +'       <span>'+rules[i].f1+'</span>元，享受'
                                +'       <span>'+rules[i].f2+'</span>折'
                                +'     </div>'
                                +' </div>'
                                        +' </div>'
                                        +'</div>';
                            $(".endRuleList").append(s);             
            	    	}
            	  //确认end
            	  $(".qrcreate").show();
              });
              
              //
              $("#twoReturn").on("click",function(){
            	  $(".ultap").find("li").removeClass("liselect");
            	  $($(".ultap").find("li")[0]).addClass("liselect");
            	  $(".usestyle").hide();
            	  $(".qrcreate").hide();
            	  $(".detail").show();
              });
              
              //qrcreate
              
              $("#threeNext").on("click",function(){
//             	  alert("创建成功"); 
            	  var data ={
              			"payStyle":(ljInfo.payStyle).toString(), 
              			"rules":JSON.stringify(ljInfo.rules),
              			"storeNos":(ljInfo.storeNos).toString(),
              			"name":ljInfo.name,
              			"type":ljInfo.type,
              			"startTime":ljInfo.startTime,
              			"endTime":ljInfo.endTime,
              			"sumPlanAmount":ljInfo.sum,
              			"aStyle":ljInfo.aStyle,
              	  };
//               	  console.log(data);
//               	  console.log(ljInfo);
//               	  return false;
              	  $.post("discount/addDiscountAndRules",data,function(result){
              		  console.log(result);
              		  if(result.code == 1){
              			  alert(result.msg);
              		  }else if(result.code == 0){
              			  alert("创建成功");
              			  location.href="page/discounts";
              		  }
              	  });
              });
              
              $("#threeReturn").on("click",function(){
            	  $(".ultap").find("li").removeClass("liselect");
            	  $($(".ultap").find("li")[1]).addClass("liselect");
            	  $(".qrcreate").hide();
            	  $(".detail").hide();
            	  $(".usestyle").show();
              });
		  })
	</script>
</head>
<body>
      
                 <div class="contentNav">
                      /&nbsp;&nbsp;<span>创建折扣</span>
                 </div>
                 <div class="taps nav-tabs clearfix">
                     <span class="defaultSel">
                                                                    创建折扣
                     </span>
                 </div>
                 <div class="content">
                     <div class="ultap">
                         <ul class="clear">
                            <li class="liselect">
                              <span>基本信息</span>
                            </li>
                            <li>
                              <span>使用方式</span>
                            </li>
                            <li>
                              <span>确认创建</span>
                            </li>
                         </ul>
                     </div>
                     <div class="detail">
                        <div class="baseinfo">
                           <div class="form-horizontal ajaxfrom" role="form">
	                          <div class="form-group">
								<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">名称</label>
								<div class="col-sm-10 col-lg-9 col-xs-12">
									<input id="minusName"  type="text" class="form-control" placeholder="9字以内">
								</div>
							  </div>
							 <div class="form-group">
								<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">有效期</label>
								<div class="col-sm-10 col-lg-9 col-xs-12 clear">
										<div class="start">
					                          <input type="text" id="startTime" class="form-control" readonly onclick="jeDate({
					              				dateCell:'#startTime',
					            				format:'YYYY-MM-DD hh:mm',
					            				isinitVal:true,
					            				isTime:true, //isClear:false,
					            				minDate:'2014-09-19'
					            			});"/>
				                        </div>
				                        <div class="zhi">至</div>
										<div class="end">
					                          <input type="text" id="endTime" class="form-control" readonly onclick="jeDate({
							                   dateCell:'#endTime',
					            				format:'YYYY-MM-DD hh:mm',
					            				isinitVal:true,
					            				isTime:true, //isClear:false,
					            				minDate:'2014-09-19'
						                      });"/>
				                       </div>
								</div>
			                 </div>
			                 <div class="form-group">
								<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">总预算</label>
								<div class="col-sm-10 col-lg-9 col-xs-12 ys_sum clear">
								   <div>
									   <input id="sum_ys"  type="text" class="form-control" readonly>
									</div>
									<div class="showm">
									  <span>元</span>
									</div>
								</div>
							  </div>
							  <div class="fixed_lj show">
								 <div class="ruleList">
							            <div class="fixed_minus_rule">折扣规则</div>
								        <div class="fixed_rule">
								           <div class="rulegroup">
									           <div class="rule_ys clear">
									             <label>预算</label>
									             <div class="input">
									                <input type="number" class="form-control fixed_ys_lj_money" >
									             </div>
									             <div class="rule_font_m">
									               <span>元</span>
									             </div>
									           </div>
									           <div class="rule_consume">
									              <div class="rule_consume_row">
									                <span>消费满</span>
									                <input type="number" class="form-control money"/>
									                <span>元，享受</span>
									                <input type="number" class="form-control"> 
									                <span>折</span>
									              </div>
									           </div>
								           </div>
								        </div>
								        <div class="rule_btns">
									               <span id="add_fixed_rule">新增规则</span>
									               <span id="del_fixed_rule">删除规则</span>
									    </div>
								 </div>
							  </div>
							  <div class="one_next">
							     <span id="oneNext">下一步</span>
							  </div>
						     </div>
						   </div>	  
                        </div>
                        <div class="usestyle">
                              <div class="usestyle_content">
                                 <p>在这里你可以设置立减优惠的使用方式</p>
                                 <div class="jt_operate">
                                    <div class="jtgroup clear">
                                      <label>可用商户</label>
                                      <div class="jtright">
                                         <p>用户可以在以下商户的订单中使用立减</p>
                                         <p>
                                           <a>添加商户编号</a>
                                         </p>
                                         <p>
                                           <textarea rows="10" cols="50" placeholder="请输入商户编号列表" id="storeNos"></textarea>
                                         </p>
                                         <p>
                                          <i>请输入商户编号，一行一个，每次最多可输入500个</i>
                                         </p>
                                      </div>
                                    </div>
                                    <div class="jtgroup clear">
                                      <label>支付方式</label>
                                      <div class="jtright">
                                         <label><input type="checkbox" name="paystyle" value="1" checked disabled>微信支付</label>
                                         <label><input type="checkbox" name="paystyle" value="2" checked disabled>支付宝支付</label>
                                      </div>
                                    </div>
                                    <div class="jtgroup clear">
                                      <label>同时使用</label>
                                      <div class="jtright">
                                         <label><input type="checkbox" name="astyle" value="3">该优惠可以和其他立减优惠同时使用</label>
                                      </div>
                                    </div>
                                 </div>
                              </div>                    
                              <div class="two_next">
							     <span id="twoNext">下一步</span>
							     <span id="twoReturn">返回</span>
							  </div>
                        </div>
                        <div class="qrcreate">
                              <div class="qrcreate_content">
                                  <div class="qrgroup">
                                     <label>名称:</label>
                                     <div>
                                       <span id="qr_ljStyle"></span>
                                     </div>
                                  </div>
                                  <div class="qrgroup">
                                     <label>有效期:</label>
                                     <div>
                                       <span id="qr_startTime"></span>
                                       <span>至</span>
                                       <span id="qr_endTime"></span>
                                     </div>
                                  </div>
                                  <div class="qrgroup ky_stores clear">
                                     <label>可用门店编号:</label>
                                     <div id="qr_use_storeNo">
                                         
                                     </div>
                                  </div>
                                  <div class="qrgroup">
                                     <label>总预算</label>
                                     <div>
                                       <span id="qr_sum_ys"></span>元
                                     </div>
                                  </div>
                              </div>
                              <div class="endRuleList">
<!--                                   <div class="end_rule clear"> -->
<!--                                      <label>减价规则</label> -->
<!--                                      <div class="endrule_detail"> -->
<!--                                          <div class="endrule_row clear"> -->
<!--                                            <div class="row"> -->
<!--                                               <label>预算</label> -->
<!-- 	                                           <div> -->
<!-- 	                                             <span>100</span>元 -->
<!-- 	                                           </div> -->
<!--                                            </div> -->
<!--                                          </div> -->
<!--                                          <div class="endrule_row clear"> -->
<!--                                            <div class="row"> -->
<!--                                               <label>消费满</label> -->
<!-- 	                                           <div> -->
<!-- 	                                             <span>100</span>元 -->
<!-- 	                                           </div> -->
<!--                                            </div> -->
<!--                                            <div class="row"> -->
<!--                                               <label>名额</label> -->
<!-- 	                                           <div> -->
<!-- 	                                             <span>33</span> -->
<!-- 	                                           </div> -->
<!--                                            </div> -->
<!--                                          </div> -->
<!--                                          <div class="endrule_row clear"> -->
<!--                                            <div class="row"> -->
<!--                                               <label>减价区间</label> -->
<!-- 	                                           <div> -->
<!-- 	                                             <span>1.00</span>元至<span>5.00</span>元 -->
<!-- 	                                           </div> -->
<!--                                            </div> -->
<!--                                            <div class="row"> -->
<!--                                               <label>均值</label> -->
<!-- 	                                           <div> -->
<!-- 	                                             <span>8.00</span>元 -->
<!-- 	                                           </div> -->
<!--                                            </div> -->
<!--                                          </div> -->
<!--                                      </div> -->
<!--                                   </div> -->
                              </div>
                              
                              <div class="minus_limit">
                                  <div class="limitrow clear">
                                    <label>同时使用</label>
                                    <div>
                                       <span id="limitUse"></span>
                                    </div>
                                  </div>
                                   <div class="limitrow clear">
                                    <label>支付方式</label>
                                    <div id="limit_payStyle">
<!--                                        <span>微信支付</span> -->
<!--                                        <span>支付宝支付</span> -->
                                    </div>
                                  </div>
                              </div>
                              <div class="three_next">
							     <span id="threeNext">确认创建</span>
							     <span id="threeReturn">返回</span>
							  </div>
                        </div>
                     </div>
                 </div>
</body>
</html>
