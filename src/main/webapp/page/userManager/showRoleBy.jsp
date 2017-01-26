<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
long id = (Long)request.getAttribute("id");
%>

<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <base href="<%=basePath%>">
    <title>修改当前角色</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="keywords" content="">
	<meta name="description" content="">
	<link rel="shortcut icon" href="image/wechat.jpg">
	<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="js/lib/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/lib/Dutil.js"></script>
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
	.content{
	   margin-top:10px;
	   border:1px solid #ccc;
	}
	
	ul li{
	  list-style:none;
	}
	.distRole{
	    width: 200px;
	    margin: 0 auto;
	    background: #44b549;
	    font-size: 16px;
	    color: #fff;
	    border: 1px solid #aaa;
	    text-align: center;
	    border-radius: 2px;
	    margin-top: 20px;
	    padding: 10px 10px 10px 0;
	    cursor: pointer;
	}
	.distRole span{
	   padding:10px 20px; 
	}
	</style>
	<script type="text/javascript">
	   $(function(){
		   $.post("role/getList",{
			   page:1,
			   pageSize:100
		   },function(result){
			   if(result.code == 0){
				   var obj = result.data;
				   console.log(obj);
				   var ul = $("#roleList"),lis="";
				   for(var i in obj){
                       lis +="<li><input type='checkbox' name='role' id='d"+obj[i].id+"' value='"+obj[i].id+"'/> " 
                            +"<label for='d"+obj[i].id+"'>"+obj[i].name+"</label></li>";
				   }
				   ul.html(lis);
				   //---
				   $.post("account/getRoleIdsByAccountId",{
					   accountId:<%=id%>
				   },function(result){
					   if(result.code == 0){
						   var roleIds = result.data,
						       input = $("input[name=role]");
						   for(var i in roleIds){
							   for(var j in input){
								   if(roleIds[i] == $(input[j]).val()){
									   $(input[j]).prop("checked","true");
									   break;
								   }
							   }
						   }
					   }else{
						   alert("请求角色失败");
					   }
				   });
			   }else{
				   alert(result.msg);
			   }
		   });
		   
		   
		   //
		   $("#submit").on("click",function(){
			   var arr = $("input[name=role]:checked"),roleIds = [];
			     for(var i=0,len=arr.length;i<len;i++){
			    	 roleIds.push($(arr[i]).val());
			     }
			     $.post("accountRole/addOrUpdate",{
			    	 accountId:<%=id%>,
			    	 roleIds:roleIds.toString()
			     },function(data){
			    	 if(data.code == 0){
			    		 alert("分配角色成功");
			    		 window.location.href="page/showRoleByAccountId?id="+<%=id%>;
			    	 }else{
			    		 alert("分配角色失败");
			    	 }
			     });
			   
		   });
	   });
	 
	</script>
</head>
<body>
      
                 <div class="contentNav">
                      /&nbsp;&nbsp;<span>角色列表</span>
                 </div>
                 <div class="taps nav-tabs clearfix">
                     <span class="defaultSel">
                                                                   角色列表
                     </span>
                 </div>
                 <div class="content">
                    <div class="table-responsive panel-body">
                       <ul id="roleList">
<!--                           <li> -->
<!-- 	                          <input type="checkbox" name="role" id="d1" disabled="true"/> -->
<!-- 	                          <label for="d1">管理员</label> -->
<!--                           </li> -->
                       </ul>
	                 </div>
                 </div>
                 <div class="distRole" id="submit">
                   <span>分配角色</span>    
                 </div>
	       
</body>
</html>	