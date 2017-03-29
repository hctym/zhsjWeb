<%@ page language="java" import="java.util.*,com.zhsj.model.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>生成二维码列表</title>
    <link rel="stylesheet" href="css/qrcode/base.css"/>
    <link rel="stylesheet" href="css/qrcode/MerchantCollection.css"/>
    <link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="js/lib/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/lib/Dutil.js"></script>
	<script type="text/javascript" src="js/lib/jBootstrapPage.js"></script>
	<style>
	  .download{
	     cursor:pointer;
	  }
	  .download a{
	    color:#108fe9;
	  }
	  ul{
	    margin-bottom:0 !important;
	  }
	</style>
</head>
<body>
    <div class="busi_user clearfix">
        <div class="busi_ri fr">
            <h3 class="clearfix busi_top">
	            <p class="busi_tit fl">
	            <i class="busi_ma">商户收款码</i>
	            <span class="busi_ac">
	               商户激活商户收款码后，可使用商户收款码收款，
	               <a href="javascript:;" class="busi_deail">查看详情</a>
	            </span>
	            </p>
	            <input type="button" value="创建商户收款码" class="fr creat_btn" id="createQrCode" style="font-size:12px;"/>
            </h3>
            <div class="busi_con" id="content">
                <ul class="clearfix border_bot">
                    <li class="fl busi_time text_center busi_weight text_color">创建时间</li>
                    <li class="fl busi_num text_center busi_weight text_color">创建数量</li>
                    <li class="fl busi_explain text_center busi_weight text_color">收款码说明</li>
                    <li class="fl busi_status text_center busi_weight text_color">状态</li>
                    <li class="fl busi_oprate text_center border_no busi_weight text_color">操作</li>
                </ul>
<!--                 <ul class="clearfix border_bot"> -->
<!--                     <li class="fl busi_time text_center busi_font">2017-03-24 17:54:40</li> -->
<!--                     <li class="fl busi_num text_center busi_font">1</li> -->
<!--                     <li class="fl busi_explain text_center busi_font">8888888</li> -->
<!--                     <li class="fl busi_status text_center busi_font">已生成</li> -->
<!--                     <li class="fl busi_oprate text_center border_no busi_font busi_color" >下载商户收款码</li> -->
<!--                 </ul> -->
<!--                 <ul class="clearfix border_bot"> -->
<!--                     <li class="fl busi_time text_center busi_font">2017-03-24 17:54:40</li> -->
<!--                     <li class="fl busi_num text_center busi_font">1</li> -->
<!--                     <li class="fl busi_explain text_center busi_font">8888888</li> -->
<!--                     <li class="fl busi_status text_center busi_font">已生成</li> -->
<!--                     <li class="fl busi_oprate text_center border_no busi_font busi_color">下载商户收款码</li> -->
<!--                 </ul> -->
<!--                 <ul class="clearfix border_bot"> -->
<!--                     <li class="fl busi_time text_center busi_font">2017-03-24 17:54:40</li> -->
<!--                     <li class="fl busi_num text_center busi_font">1</li> -->
<!--                     <li class="fl busi_explain text_center busi_font">8888888</li> -->
<!--                     <li class="fl busi_status text_center busi_font">已生成</li> -->
<!--                     <li class="fl busi_oprate text_center border_no busi_font busi_color">下载商户收款码</li> -->
<!--                 </ul> -->
<!--                 <ul class="clearfix move_bg"> -->
<!--                     <li class="fl busi_time text_center busi_font">2017-03-24 17:54:40</li> -->
<!--                     <li class="fl busi_num text_center busi_font">1</li> -->
<!--                     <li class="fl busi_explain text_center busi_font">8888888</li> -->
<!--                     <li class="fl busi_status text_center busi_font">已生成</li> -->
<!--                     <li class="fl busi_oprate text_center border_no busi_font busi_color">下载商户收款码</li> -->
<!--                 </ul> -->
            </div>
            
            <div style="float:right;">
			   <ul class="pagination"></ul>
			</div>
        </div>
    </div>
</body>
</html>
<script>
 $(function(){
	 $("#createQrCode").on("click",function(){
		location.href="page/createQrCode"; 
	 });
	 var page = 1,pageSize = 4, nog = [];
	 
	 load(1);//默认加载
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
	 var content = $("#content");
	 
	 function load(page){
		     nog = [];
			 $.post("qrcode/getList",{
				 page:page,
				 pageSize:pageSize
			 },function(result){
				 console.log(result);
				 var s = "";
				 if(result.code == 0){
					 var list = result.data.list,len = list.length;
					 if(len == 0){
						 return;
					 }else{
						 $("#content").find("ul:not(:first)").remove();
					 }
					 var s = "";
					 for(var i=0;i<len;i++){
						 var status = "";
						 if(list[i].status == 1){
							 status = "生成中";
							 nog.push(list[i].id);
						 }else if(list[i].status == 2){
							 status = "已生成";
						 }else{
							 status = "生成失败";
						 }
						   s += ' <ul class="clearfix border_bot">'
				             + '<li class="fl busi_time text_center busi_font">'+new Date(list[i].ctime*1000).Format("yyyy-MM-dd hh:mm:ss")+'</li>'
				             + '<li class="fl busi_num text_center busi_font">'+list[i].count+'</li>'
				             + '<li class="fl busi_explain text_center busi_font">'+list[i].des+'</li>'
				             + '<li class="fl busi_status text_center busi_font" id="status'+list[i].id+'">'+status+'</li>'
				             + '<li class="fl busi_oprate text_center border_no busi_font busi_color download" id="download'+list[i].id+'">';
				             if(list[i].status == 2){
				            	 s += '<a href="zip/download?path='+list[i].resourcePath+'">下载商户收款码</a>';
				             }else{
				            	 s += '下载商户收款码';
				             }
				             s += '</li></ul>';
					 }
					 content.append(s);
					 if(page == 1){
						 createPage(pageSize,result.data.count);
					 }
				 }else{
					 alert("加载失败");
				 }
			 });
	 
	 }
	 
	 setInterval(function(){
		 if(nog.length > 0){
			 requestNog();
		 }
	 },5000);
	 
	function requestNog(){
		$.post("qrcode/nog",{
			nogIds:nog.toString()
		},function(result){
			if(result.code == 0){
				var list = result.data,len = list.length;
				for(var i=0;i<len;i++){
					if(list[i].status == 2){
						$("#status"+list[i].id).text("已生成");
						$("#download"+list[i].id).text("").html('<a href="zip/download?path='+list[i].resourcePath+'">下载商户收款码</a>');
						nog.splice(i,1);
					}else if(list[i].status == 3){
						$("#status"+list[i].id).text("生成失败");
						nog.splice(i,1);
					}
// 					console.log($("#status"+list[i].id).text());
				}
			}else{
				alert("加载失败");
			}
		});
	}
	 
 });
 

</script>