<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>支付方式</title>
    
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
	    
    </style>
    <script type="text/javascript">
      $(function(){
    	  $.post("store/getPayInfoListByStoreNo",function(result){
    		  console.log(result);
    		  if(result.code == 0){
    			  var obj = result.data,
    			      len = obj.length;
    			  $("table").find("tr:not(:first)").remove();
    			  if(len > 0){
    				  $(".add").hide();
    				  for(var i = 0;i<len;i++){
    				    $("tbody").append($("<tr>")
    				    		  .append($("<td>").text(obj[i].id))
    				    		  .append($("<td>").text(obj[i].payType==2?'中信':'其它'))
    				    		  .append($("<td>").text((obj[i].payMethod).indexOf(",")==1?'微信,支付宝':obj[i].payMethod == 1?'微信':'支付宝'))
    				    		  .append($("<td>").text(obj[i].field1))
    				    		  .append($("<td>").text(obj[i].field2))
    				    		  .append($("<td>").text(obj[i].remark))
    				    		  .append($("<td>").text(obj[i].status==1?'启用':'禁用'))
    				    		  .append($("<td>").text(obj[i].valid==1?'有效':'无效'))
    				    		  .append($("<td>")
    				    				  .append($("<span>").text("编辑").attr("data-id",obj[i].id).on("click",function(){
									              location.href="page/editPayInfo?id="+$(this).attr("data-id");
								         }))
								         .append($("<span>").text(obj[i].status==1?'禁用':'启用').attr("data-id",obj[i].id).attr("data-status",obj[i].status)
								        		 .on("click",function(){
									              $.post("store/updateStorePayInfo",{
									            	  id:$(this).attr('data-id'),
									            	  status:$(this).attr('data-status')==1?2:1
									              },function(result){
									            	  if(result.code == 0){
									            		  alert('修改状态成功');
									            		  location.reload()
									            	  }else{
									            		  alert('修改状态失败')
									            	  }
									              })
								         }))
								   )
    				    );
    				  }
    			  }else{
    				  $("tbody").append($("tr").prop("rowspan","9").css("text-align","center").text("暂无数据"));
    			  }
    		  }else{
    			  alert("加载支付方式失败");
    		  }
    	  });
    	  
      });
    
    </script>
  </head>
  
  <body>
                 <div class="contentNav">
                      /&nbsp;&nbsp;<span>支付方式</span>
                 </div>
                 <div class="taps nav-tabs clearfix">
                     <span class="defaultSel">
                                                                   支付方式
                     </span>
                     <a class="add" href="page/addPayInfo">
                                                                    添加支付
                     </a>
                 </div>
                 <div class="content">
                    <div class="table-responsive panel-body">
						<table class="table table-hover">
							<thead class="navbar-inner">
								<tr>
									<th style="width:100px;">id</th>
									<th style="width:100px;">支付类型</th>
									<th style="width:100px;">支付方法</th>
									<th style="width:100px;">field1</th>
									<th style="width:100px;">field2</th>
									<th style="width:100px;">费率</th>
									<th style="width:100px;">状态</th>
									<th style="width:200px;">是否有效</th>
									<th style="width:200px;">操作</th>
								</tr>
							</thead>
							<tbody>
                            <!-- 			data			-->
							</tbody>
						</table>
	                 </div>
                 </div>
  </body>
</html>
