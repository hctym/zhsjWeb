<%@ page language="java" import="java.util.*,com.zhsj.model.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>创建商户收款码</title>
    <link rel="stylesheet" href="css/qrcode/base.css"/>
    <link rel="stylesheet" href="css/qrcode/MerchantCollection.css"/>
    <link rel="stylesheet" href="css/qrcode/Createmerchantcode.css"/>
	<script type="text/javascript" src="js/lib/jquery-1.11.1.min.js"></script>
</head>
<body>
<div class="busi_user2 clearfix">
    <div class="busi_ri fr">
        <h3 class="clearfix busi_top"><p class="busi_tit fl"><i class="busi_ma">创建商户收款码</i></p></h3>
        <div class="creat_number">
            <span>创建个数：</span><input type="text" class="createmer_text" id="count"/>
            <p class="createmer_remind createmer_bot createmer_remind_dis2">请输入数字，单次创建不超过20个</p>
            <span class="createmer_padd">说明：</span><textarea id="des"></textarea>
            <p class="createmer_remind createmer_remind_dis">最多输入50个字</p>
        </div>
        <div><input type="button" value="创建" class="createbut" id="create"/> </div>
    </div>
</div>
</body>
</html>

<script>
$(function(){
	$("#create").on("click",function(){
		var count = $("#count").val(),des = $("#des").val();
		if("" == count || "" == des){
			alert("请填写完整");
			return;
		}
		if(!/^[1-9]+\d?$/.test(count)){
			alert("输入有误");
			return;
		}
		if(Number(count) > 20){
			alert('不能超过20');
			return false;
		}
		$.post("qrcode/create",{
			count:count,
		    des:des
		},function(result){
			console.log(result);
			if(result.code == 0){
				alert('创建成功');
			    location.href="page/qrCodeList";
			}else{
				alert('创建失败');
			}
		});
	});
});
</script>