<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	}
	ul {
	  margin-top:10px;
/* 	  margin-left:60px; */
	  list-style-type:none; 
	}
	li {
	  margin-top:10px;
	  margin-left:60px;
	}
	input[type="checkbox"] {
	    margin-right: 10px;
	}
	.distModule{
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
	.distModule span{
	   padding:10px 20px; 
	}
	</style>
	<script type="text/javascript">
	   $(function(){
		   //加载模块
		  $.get("module/getModules",function(result){
			  console.log(result);
			  var ul = $("<ul>");
			  var obj = result.data;
			  for(var i in obj){
				  var li = $("<li>"),span = $("<span>"),input = $("<input>").attr("type","checkbox").val(obj[i].id).on("click",inputFunc);
                  li.append(input).append(span.text(obj[i].mname+"  "+obj[i].id));
				  if(obj[i].childrenModules.length > 0){
						  var oul = $("<ul>"); 
					  for(var j in obj[i].childrenModules){
						  var oli = $("<li>"),
						      ospan = $("<span>"),
						      oinput = $("<input>").attr("type","checkbox").val(obj[i].childrenModules[j].id).on("click",inputFunc);
                              oli.append(oinput).append(ospan.text(obj[i].childrenModules[j].mname+"  "+obj[i].childrenModules[j].id));
							  if(obj[i].childrenModules[j].childrenModules.length > 0){
									  var tul = $("<ul>");
								  for(var n in obj[i].childrenModules[j].childrenModules){
									  var tli = $("<li>"),
									      tspan = $("<span>"),
									      tinput = $("<input>").attr("type","checkbox").val(obj[i].childrenModules[j].childrenModules[n].id).on("click",inputFunc).attr("class","three");
										  tli.append(tinput).append(tspan.text(obj[i].childrenModules[j].childrenModules[n].mname+"  "+obj[i].childrenModules[j].childrenModules[n].id));//
										  tul.append(tli);
								  }
									  oli.append(tul);
							  }
							  oul.append(oli);
					  }
							  li.append(oul);
				  }
				  ul.append(li);
			  }
			  $(".content").append(ul);
			  
		  });
		  
		  
		  
		  function inputFunc(){
			          //给所有的权限复选框添加点击事件
			          //自己选中或取消时，把所有的下级权限也都同时选中或取消
			          $(this).next().next().find("input").prop("checked",this.checked);
			          //当选中一个权限时，也要同时选中所有的上级权限
			          if(this.checked){
			              $(this).parents("li").children("input").prop("checked",true);
			  			  $(this).parent("li").parent("ul").prev().prev().prop("checked",true);
			  			  $(this).parent("li").parent("ul").parent("li").parent("ul").prev().prev().prop("checked",true);
			          }else{//当取消一个权限时，同级没有选中的权限了，就也取消了他的上级权限，再向上也是如此
			              if($(this).parent().siblings("li").children("input:checked").size() ==0){
			                  $(this).parent().parent().prev().prev().prop("checked",false);
			                  var start=$(this).parent().parent();
			                  if(start.parent().siblings("li").children("input:checked").size() ==0){
			                  }
			              }
			          }
		  }
		  
		  
		  $("#submit").on("click",function(){
			     console.log($(".three:checked").length); 
			     var arr = $(".three:checked"),modules = [];
			     console.log(arr[0]);
			     for(var i=0,len=arr.length;i<len;i++){
			    	 console.log($(arr[i]).val());
			    	 modules.put($(arr[i]).val());
			     }
			     $.post("",{
			    	 data:modules
			     },function(data){
			    	 console.log(data);
			     })
		  });
	   });
	 
	</script>
</head>
<body>
      
                 <div class="contentNav">
                      /&nbsp;&nbsp;<span>分配模块</span>
                 </div>
                 <div class="taps nav-tabs">
                     <span class="defaultSel">
                                                                    分配模块
                     </span>
                 </div>
                 <div class="content">
<!--                     <ul> -->
<!--                        <li> -->
<!--                          <span><input type="checkbox">测试1</span> -->
<!--                          <ul> -->
<!--                             <li> -->
<!--                               <span><input type="checkbox">ceshi1</span> -->
<!--                               <ul> -->
<!--                                  <li><input type="checkbox">ccccc</li> -->
<!--                                  <li><input type="checkbox">eeeee</li> -->
<!--                                  <li><input type="checkbox">sssss</li> -->
<!--                               </ul> -->
<!--                             </li> -->
<!--                             <li><input type="checkbox">ceshi1</li> -->
<!--                             <li><input type="checkbox">ceshi1</li> -->
<!--                          </ul> -->
<!--                        </li> -->
<!--                        <li><input type="checkbox">测试2</li> -->
<!--                        <li><input type="checkbox">测试3</li> -->
<!--                        <li><input type="checkbox">测试4</li> -->
<!--                     </ul> -->
                 
                 </div>
                 
                 <div class="distModule" id="submit">
                   <span>分配模块</span>    
                 </div>
	       
</body>
</html>	