<%@page import="com.zhsj.util.SessionThreadLocal"%>
<%@ page language="java" import="java.util.*,com.zhsj.model.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Map<String,Object> map = SessionThreadLocal.getSession();
String flag = (String)map.get("flag");
long orgId=0;
if("account".equals(flag)){
	Account account = (Account)map.get("user");
	AccountBindOrg abrOrg = account.getAccountBindOrg();
    if(abrOrg != null){
    	orgId = abrOrg.getOrgId();
    }
}
%>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>组织架构</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" href="image/wechat.jpg">
	<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<link rel="stylesheet" href="css/lib/zTreeStyle.css" />
	<script type="text/javascript" src="js/lib/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/lib/jquery.ztree.core.min.js" ></script>
	<script type="text/javascript" src="js/lib/jBootstrapPage.js"></script>
	<script type="text/javascript" src="js/lib/Dutil.js"></script>
	<script type="text/javascript">
	$(function(){
		var setting = {  
		        view: {  
		            expandSpeed: 400//"slow"//节点展开速度  
		        },  
		        data: {  
		            simpleData: {//是否为简单数据类型JSON  
		                enable:true
		            }  
		        },  
		        async: {  
		                enable: true,//异步加载  
		                url:"org/getChildrenByOrgId",  
		                contentType:"application/x-www-form-urlencoded",//按照标准的 Form 格式提交参数  
		                autoParam:["id"],//提交的节点参数，可用“id=xx”取请求提交时的别名  
		                dataType:"json",//返回数据类型  
		                type:"post",//请求方式  
		                dataFilter: ajaxDataFilter//数据过滤  
		                },  
		        callback: {  
		            onClick:reLoadOpenURL,//节点被点击时调用的函数  
		            onAsyncError: onAsyncError
		          }  
		     };  
		
		     function ajaxDataFilter(treeId, parentNode, responseData){
		    	 var d = [],data = responseData.data;
		    	 for(var i in data){
	 					d.push({
	 						id:data[i].id,
	 						pId:data[i].patentId, 
	 						name:data[i].name,
	 						open:true,
	 						isParent:true
	 					});
	 				}
		    	 return d;
		     }
		     //如果是父节点不处理，如果是子节点，打开对应的连接  
		     function reLoadOpenURL(event, treeId, treeNode){  
		    	         $("#curorg_name").text(treeNode.name);//显示组织名称
		    	         if(treeNode.id != <%=orgId%>){
		    	              loadOrg(treeNode.id);
		    	         }
					     var zTree = $.fn.zTree.getZTreeObj(treeId);  
		                if (treeNode.isParent) {//如果是父节点  
		                    if(treeNode.open){//父节点为展开状态，折叠父节点  
		                          zTree.expandNode(treeNode,false,true,true,false);  
		                      }else{//父节点是折叠的  
			                      if(treeNode.id!=1){
			                    	  zTreeBeforeExpand(treeId, treeNode);//如果不是根节点（本例根节点为1），则强制异步刷新子节点数据  
			                      }else{  
			                          zTree.expandNode(treeNode,true,false,true,false);//如果是根节点则展开  
			                      }
		                    }  
		                    return false;  
		                } 
		            }  
		    //用于捕获父节点展开之前的事件回调函数，并且根据返回值确定是否允许展开操作 ，false不代开      
		     function zTreeBeforeExpand(treeId, treeNode) {  
		          var zTree = $.fn.zTree.getZTreeObj(treeId);  
                  if(treeNode.isParent&&treeNode.id!="1") {  
	                    zTree.reAsyncChildNodes(treeNode, "refresh");//异步刷新，清空后加载，加载后打开,需要不打开加参数true  
	                    return false;//使用了异步强行加载，如果用true,节点展开将不会按照expandSpeed属性展开，false将按照设定速度展开  
                    }else{  
                        return true;  
                    }  
		         };  
		      
		     //异步加载失败时调用的方法  
		     function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {  
		            alert("加载失败！");  
		        }  
		var defaultOrgId = "";   
		loadOrg(<%=orgId%>);//默认加载当前的组织id
		function loadOrg(orgid){
			$.post("org/getOrgById",{
				id:orgid
			},function(result){
				if(result.code == 0){
					var data = [];
					var p = result.data;
					if(orgid == <%=orgId%>){
						data.push({
							id:p.id, pId:p.parentId, name:p.name,open:true,isParent:true
						});
						defaultOrgId = p.id;
						$.fn.zTree.init($("#tree"), setting, data);  
					}
					
					console.log(p);
                    $("#editOrgId").val(p.id);
                    $("#eorgname").val(p.name);
                    $("#eorgcontactPhone").val(p.contactPhone);
                    $("#eorgemail").val(p.email);
//                     $("#city").val(p.cityId);
                    $($("input[name=eisAllow]")[0]).prop("checked","false");
                    $($("input[name=eisAllow]")[1]).prop("checked","false");
                    if(p.isAllow == 0){
                    	$($("input[name=eisAllow]")[0]).prop("checked","true");
                    }else{
                    	$($("input[name=eisAllow]")[1]).prop("checked","true");
                    }
                    if(p.cityCode != null){
                    	$("#eprovince").empty().append($("<option>").val(p.cityCode.code).text(p.cityCode.name));
                    }
					$("#curorg_name").text(p.name);//显示组织名称
				}else{
					alert(result.msg);
				}
			});
		}
		
			//
		$(".a").find("span").on("click",function(){
			$(".a").find("span").removeClass("spanClick");
		    $(this).addClass("spanClick");
		    $(".oper-detail").find(".form").removeClass("select");
		    $($(".oper-detail").find(".form")[$(this).index()]).addClass("select");
		});
		
		//添加组织
		$("#addOrg").click(function(){
			var obj = $.fn.zTree.getZTreeObj("tree").getSelectedNodes()[0];
			var orgId = defaultOrgId;
			if(obj){
				orgId = obj.id;
			}
			if(!$("#orgname").val()){
				alert("组织名称不允许为空");
				return false;
			}
			if(!$("#orgemail").val()){
				alert("邮箱不允许为空");
				return false;
			}
			if(!$("#orgcontactPhone").val()){
				alert("联系电话不允许为空");
				return false;
			}
			var cityCode = $("#county").val() != 0?$("#county").val():$("#city").val()!=0?$("#city").val():$("#province").val();
			if(cityCode == 0){
				alert("请选择城市");
				return false;
			}
// 			console.log(cityCode);
// 			return false;
			$.post("org/add",{
				parentId:orgId,
				name:$("#orgname").val(),
				contactPhone:$("#orgcontactPhone").val(),
				email:$("#orgemail").val(),
				cityId:cityCode,
				isAllow:$("input[name=isAllow]:checked").val()
			},function(result){
// 				console.log(result);
				if(result.code == 0){
					alert("添加组织成功!");
					location.reload();
				}else{
					alert(result.msg);
				}
			});
		});
		//编辑组织
		$("#editOrg").click(function(){
			if(!$("#eorgname").val()){
				alert("组织名称不允许为空");
				return false;
			}
			if(!$("#eorgemail").val()){
				alert("邮箱不允许为空");
				return false;
			}
			if(!$("#eorgcontactPhone").val()){
				alert("联系电话不允许为空");
				return false;
			}
			var cityCode = $("#ecounty").val() != 0?$("#ecounty").val():$("#ecity").val()!=0?$("#ecity").val():$("#eprovince").val();
			if(cityCode == 0){
				alert("请选择城市");
				return false;
			}
// 			console.log(cityCode);
// 			return false;
			$.post("org/update",{
				id:$("#editOrgId").val(),
				name:$("#eorgname").val(),
				cityId:cityCode,
				contactPhone:$("#eorgcontactPhone").val(),
				email:$("#eorgemail").val(),
				isAllow:$("input[name=eisAllow]:checked").val()
			},function(result){
				console.log(result);
				if(result.code == 0){
					alert("编辑组织成功!");
					location.reload();
				}else{
					alert(result.msg);
				}
			});
		});
		
		
		 var page = 1;
		   var pageSize = 10;
		   load(page);
		   
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
		   $("#checkAccount").on("click",function(){
			   page = 1;
			   load(page);
		   });
		   function load(page){
			   var obj = $.fn.zTree.getZTreeObj("tree");
			   var orgId = 1;
				if(obj == null){
					orgId = <%=orgId%>;
				}else{
					orgId = obj.getSelectedNodes()[0].id;
				}
			   $.post("account/getList",{
				   orgId:orgId,
				   page:page,
				   pageSize:pageSize
			   },function(result){
				   if(result.code == 0){
					   $("table").find("tr:not(:first)").remove();
					   var tbody = $("tbody");
//					   console.log(result);
					   var list = result.data.list;
					   for(var i in list){
							if(list[i].ctime >0 ){
								list[i].ctime =  new Date(list[i].ctime*1000).Format("yyyy-MM-dd hh:mm:ss");
							}
							console.log(result.data[i]);
						   tbody.append($("<tr>")
								   .append($("<td>").text(list[i].id))
								   .append($("<td>").text(list[i].account))
								   .append($("<td>").text(list[i].name))
								   .append($("<td>").append($("<img>").attr("src",list[i].headImg).width("30px")))
								   .append($("<td>").text(list[i].gender==1?'男':'女'))
								   .append($("<td>").text(list[i].mobile))
								   .append($("<td>").text(list[i].email))
								   .append($("<td>").text(list[i].status==1?'启用':'禁用'))
								    .append($("<td>").text(list[i].valid==1?'有效':'无效'))
								   .append($("<td>").text(list[i].ctime)));
					   }
					   if(page == 1){
						    createPage(pageSize,result.data.count);
					  }
				   }else{
					   alert(result.msg);
				   }
			   }); 
			   
		   }
	});
		
	</script>
    <style type="text/css">
	  li{
	    cursor:pointer;
	  }
	  .active{
	     color:#38c;
	     font-weight:bold;
	     font-size:1.2em;
	  }
	  .org{
	     width:15%;
/* 	     border:1px solid #ccc; */
	     float:left;
	  }
	  .ocontent{
	     width:83%;
	     border:1px solid #ccc;
	     float:left;
	     margin-left:20px;
	  }
	  
	  .form{
	    display:none;
	   }
	  .select{
	    display:block;
	   }
	   .operate{
	     background-color:#38c;
	   }
	   .operate .a{
	     padding-top:10px;
	     padding-bottom:20px;
	     color:#fff;
	   }
	   .a span{
	      width:100px;
	      line-height:30px;
	      background-color:green;
	      border-radius:5px;
		  display: inline-block;
		  margin-left:20px;
		  text-align: center;
		  cursor:pointer;
		  box-shadow: 1px 1px 5px #888888;
	   }
	   .spanClick{
	      background-color:red !important;
	   }
	   .curorg{
	      margin: 10px auto;
          text-align: center;
          font-size:2em;
	   }
	</style>
	</head>
	<body>
		<div class="main clearfix">
		   <div class="org">
                <ul id="tree" class="ztree">
				<!-- tree data -->
				</ul>
		   </div>
		   <div class="ocontent">
		       <div class="operate">
		          <div class="a">
			           <span class="add-org spanClick">添加子组织</span>
			           <span class="edit-org">编辑组织</span>
			           <span class="add-account">添加用户</span>
			           <span class="check-account" id="checkAccount">查看用户</span>
		          </div> 
		       </div>
		       <div class="curorg">
		          <label >当前组织：</label>
		          <span id="curorg_name"></span>
		       </div>
		       <div class="oper-detail">
		          <div class="form add-org select">
		                   <div class="content">
						     <div class="form-horizontal ajaxfrom" role="form">
								<div class="form-group" style="margin-top:20px;">
									<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">组织名称</label>
									<div class="col-sm-10 col-lg-9 col-xs-12">
										<input id="orgname"  type="text" class="form-control" placeholder="组织名称">
										<span class="help-block">请输入组织名称</span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">联系电话</label>
									<div class="col-sm-10 col-lg-9 col-xs-12">
										<input id="orgcontactPhone"  type="text" class="form-control" placeholder="联系电话">
										<span class="help-block">请输入联系电话</span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">邮箱</label>
									<div class="col-sm-10 col-lg-9 col-xs-12">
										<input id="orgemail"  type="text" class="form-control" placeholder="输入邮箱">
										<span class="help-block">请输入邮箱</span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">城市</label>
									<div class="col-sm-10 col-lg-9 col-xs-12">
										<select class="form-control" id="province">
										   <option value="0">请选择</option>
										</select>
										<select class="form-control" id="city" style="display:none;">
										   <option value="0">请选择</option>
										</select>
										<select class="form-control" id="county" style="display:none;">
										   <option value="0">请选择</option>
										</select>
										
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">是否允许招商</label>
									<div class="col-sm-10 col-lg-9 col-xs-12">
										<label class="radio-inline"><input type="radio" name="isAllow" value="0" checked> 不允许</label>
										<label class="radio-inline"><input type="radio" name="isAllow" value="1" > 允许</label>
									</div>
								</div>
							   <div class="form-group" style="margin-top:100px; text-align:center;">
									<div class="col-sm-offset-2 col-md-offset-2 col-lg-offset-1 col-xs-12 col-sm-10 col-md-10 col-lg-11">
										<input type="submit" id="addOrg" class="btn btn-primary span3" name="submit" value="保存">
									</div>
								</div>
							</div>
		                 </div>
		          </div>
		          <div class="form edit-org">
		                   <div class="content">
						     <div class="form-horizontal ajaxfrom" role="form">
								<div class="form-group" style="margin-top:20px;">
									<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">组织名称</label>
									<div class="col-sm-10 col-lg-9 col-xs-12">
								        <input id="editOrgId" type="hidden">
										<input id="eorgname"  type="text" class="form-control" placeholder="组织名称">
										<span class="help-block">请输入组织名称</span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">联系电话</label>
									<div class="col-sm-10 col-lg-9 col-xs-12">
										<input id="eorgcontactPhone"  type="text" class="form-control" placeholder="联系电话">
										<span class="help-block">请输入联系电话</span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">邮箱</label>
									<div class="col-sm-10 col-lg-9 col-xs-12">
										<input id="eorgemail"  type="text" class="form-control" placeholder="输入邮箱">
										<span class="help-block">请输入邮箱</span>
									</div>
								</div>
								<div class="form-group" id="eCityCode">
									<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">城市</label>
									<div class="col-sm-10 col-lg-9 col-xs-12">
										<select class="form-control" id="eprovince">
										   <option value="0">请选择</option>
										</select>
										<select class="form-control" id="ecity" style="display:none;">
										   <option value="0">请选择</option>
										</select>
										<select class="form-control" id="ecounty" style="display:none;">
										   <option value="0">请选择</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">是否允许招商</label>
									<div class="col-sm-10 col-lg-9 col-xs-12">
										<label class="radio-inline"><input type="radio" name="eisAllow" value="0" > 不允许</label>
										<label class="radio-inline"><input type="radio" name="eisAllow" value="1" > 允许</label>
									</div>
								</div>
							   <div class="form-group" style="margin-top:100px; text-align:center;">
									<div class="col-sm-offset-2 col-md-offset-2 col-lg-offset-1 col-xs-12 col-sm-10 col-md-10 col-lg-11">
										<input type="submit" id="editOrg" class="btn btn-primary span3" name="submit" value="修改">
									</div>
								</div>
							</div>
		                 </div>
		          </div>
		         <div id="add-account" class="form add-account">
		         <div class="content">
				     <div class="form-horizontal ajaxfrom" role="form" id="form-user">
						<div class="form-group" style="margin-top:20px;">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">账户名称(手机号作为登录名)</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="account" name="account" type="text" class="form-control" placeholder="输入账户名称">
								<span class="help-block">请输入账户名称</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">真实姓名</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="name" name="name" type="text" class="form-control" placeholder="输入真实姓名">
								<span class="help-block">请输入真实姓名</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">登录密码</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="password" name="password" type="password" class="form-control" placeholder="输入登录密码">
								<span class="help-block">请填写密码，最小长度为 8 个字符</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">确认密码</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="repassword" type="password" class="form-control" placeholder="输入重复密码">
								<span class="help-block">重复输入密码，确认正确输入</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">性别</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<label class="radio-inline"><input type="radio" name="gender" value="1" checked> 男</label>
								<label class="radio-inline"><input type="radio" name="gender" value="2" > 女</label>
							</div>
						</div>
						<div class="form-group" class="padding-top:20px;">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">手机号</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="mobile" type="text" class="form-control" placeholder="输入手机号">
								<span class="help-block">输入手机号</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">邮箱</label>
							<div class="col-sm-10 col-lg-9 col-xs-12">
								<input id="email" type="text" class="form-control" placeholder="输入邮箱">
								<span class="help-block">输入邮箱</span>
							</div>
						</div>
						<div class="form-group">
								<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">角色</label>
								<div class="col-sm-10 col-lg-9 col-xs-12">
									<select name="provincecode" class="form-control" id="roleid">
                                             <!--  -->									
                                    </select>
								</div>
							</div>
					   <div class="form-group">
							<div class="col-sm-offset-2 col-md-offset-2 col-lg-offset-1 col-xs-12 col-sm-10 col-md-10 col-lg-11">
								<input type="submit" id="submit" class="btn btn-primary span3" name="submit" value="确认注册">
							</div>
						</div>
					</div>
                 </div>
		          </div>
		          <div id="check-account" class="form check-account">
		                   <div class="content">
                    <div class="table-responsive panel-body">
						<table class="table table-hover">
							<thead class="navbar-inner">
								<tr>
									<th style="width:50px;">账户id</th>
									<th style="width:100px;">账户</th>
									<th style="width:100px;">名称</th>
									<th style="width:50px;">头像</th>
									<th style="width:50px;">性别</th>
									<th style="width:100px;">手机</th>
									<th style="width:100px;">邮箱</th>
									<th style="width:50px;">状态</th>
									<th style="width:100px;">是否有效</th>
									<th style="width:100px;">创建时间</th>
								</tr>
							</thead>
							<tbody>
                               <!-- -->
							</tbody>
						</table>
	                 </div>
                 </div>
                   <div style="float:right;">
			          <ul class="pagination"></ul>
			       </div>
		          </div>
		       </div>
		   </div>
	    </div>
  </body>
</html>

<script type="text/javascript">
	   $(function(){
		   
// 		   

$("#submit").click(function(){
			   var obj =$.fn.zTree.getZTreeObj("tree").getSelectedNodes()[0];
			   if(!obj.id){
					alert("请选择一个组织");
					return false;
			   }
			   if($("#account").val() == ''){
			    	alert("输入账户名称");
			    	return false;
			   }
			   if($("#password").val() == ''){
				    alert("输入密码");
			    	return false;
			   }
			   if($("#repassword").val() == ''){
				    alert("输入重复密码");
			    	return false;
			   }
			   if($("#repassword").val() != $("#password").val()){
				    alert("输入的密码和重复密码不一样");
			    	return false;
			   }
			   if($("#mobile").val() == ''){
				    alert("输入手机号");
			    	return false;
			   }
			   if($("#email").val() == ''){
				    alert("输入邮箱");
			    	return false;
			   }
			   $.ajax({
				   url:"account/isRegisterByAccount",
			       async: false,
			       data:{
			    	   account:account 
			       },
			       success:function(result){
					   console.log(result);
					   if(result.code == 1){
						   alert("异常");
						   return false;
					   }else if(result.code == 0){
						   alert("该手机号已经被注册了");
						   return false;
					   }else{
						   $.post("account/add",{
						    	orgId:obj.id,
						    	account:$("#account").val(),
						    	password:$("#password").val(),
						    	name:$("#name").val(),
						    	gender:$("input[name='gender']:checked").val(),
						    	mobile:$("#mobile").val(),
						    	email:$("#email").val(),
						    	roleId:$("#roleid").val()
						    },function(data){
						    	if(data.code == 0){
						    		alert("添加用户成功");
// 						    		location.href="page/accountList";
                                    location.reloade();
						    	}else{
						    		alert("添加用户失败");
						    	}
						    });
						   
					   }
				   }
			   });
			    
			   
		   });
		   
		   
		   //获取角色
		   $.post("role/getListByType",{
			   type:1
		   },function(result){
			   if(result.code == 0){
				   for(var i in result.data){
				     $("#roleid").append($("<option>").attr("value",result.data[i].id).text(result.data[i].name));
				   }
			   }else{
				   alert(result.msg);
			   }
		   });
		   
            //添加
		    loadCity(0,$("#province"));
			function loadCity(code,pobj){
				$.post("city/getListByCode",{
					code:code
				},function(result){
					console.log(result);
					if(result.code ==0){
						var ctys = result.data;
						pobj.find("option:not(:first)").remove();
						for(var i in ctys){
							pobj.append($("<option>").attr("value",ctys[i].code).text(ctys[i].name));
						}
					}else{
						alert(result.msg);
					}
				});
			}

			$("#province").change(function(){
				if($(this).val() != "0"){
					loadCity($(this).val(),$("#city"));
					$("#city").css("display","block");
				}else{
					$("#city").css("display","none").find("option:not(:first)").remove();
				}
					$("#county").css("display","none").find("option:not(:first)").remove();
			});
			$("#city").change(function(){
				if($(this).val() != "0"){
					loadCity($(this).val(),$("#county"));
					$("#county").css("display","block");
				}else{
					$("#county").css("display","none").find("option:not(:first)").remove();
				}
			});
			
			//编辑
			loadCity(0,$("#eprovince"));
			function loadCity(code,pobj){
				$.post("city/getListByCode",{
					code:code
				},function(result){
					console.log(result);
					if(result.code ==0){
						var ctys = result.data;
						pobj.find("option:not(:first)").remove();
						for(var i in ctys){
							pobj.append($("<option>").attr("value",ctys[i].code).text(ctys[i].name));
						}
					}else{
						alert(result.msg);
					}
				});
			}

			$("#eprovince").change(function(){
				if($(this).val() != "0"){
					loadCity($(this).val(),$("#ecity"));
					$("#ecity").css("display","block");
				}else{
					$("#ecity").css("display","none").find("option:not(:first)").remove();
				}
					$("#ecounty").css("display","none").find("option:not(:first)").remove();
			});
			$("#ecity").change(function(){
				if($(this).val() != "0"){
					loadCity($(this).val(),$("#ecounty"));
					$("#ecounty").css("display","block");
				}else{
					$("#ecounty").css("display","none").find("option:not(:first)").remove();
				}
			});

	   });
	</script>
