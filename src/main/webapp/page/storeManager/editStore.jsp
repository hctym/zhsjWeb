<%@page import="com.zhsj.util.SessionThreadLocal"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
long id = (Long)request.getAttribute("id");
Map<String,Object> map = SessionThreadLocal.getSession();
String flag = (String)map.get("flag");
String url = "";
if("account".equals(flag)){
	url="page/storeList";
}else{
	url="page/stores";
}
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
		   
		   
		     $.post("store/getById",{
		    	 id:<%=id%>
		     },function(result){
		    	 if(result.code == 0){
		    		    var obj =result.data;
		    		    console.log(obj);
		    		    $("#name").val(obj.name);
		    		    if(obj.cc != null){
		    		    	$("#province").empty().append($("<option>").val(obj.cc.code).text(obj.cc.name));
		    		    }
	                    $("#cityCode").val(obj.cityCode);//test
				    	$("#address").val(obj.address);
				    	$("#phone").val(obj.phone);
				    	$("#showImg").attr("src","<%=basePath%>"+obj.shopLogo).on("error",function(){
				    		$("#showImg").attr("src","<%=basePath%>image/nopic.jpg");
				    	});
				    	$("#latitude").val(obj.latitude/1000000);
				    	$("#longitude").val(obj.longitude/1000000);
				    	$("#intro").val(obj.intro);
				    	loadCity(0,$("#province"));
		    	 }else{
		    		 alert(result.msg);
		    	 }
		     });
		        
		       var logo = "";
				$("#uploadLogo").click(function(){
					$.ajaxFileUpload({
				        url : 'image/upload',
				        type : 'POST',
				        fileElementId : 'logoImage',  //这里对应html中上传file的id
				        dataType: 'JSON',
				        success: function(data){
				           console.log($.parseJSON($(data).text()));
				           var obj = $.parseJSON($(data).text());
				           if(obj.code == 0){
				        	   $("#showImg").prop("src","<%=basePath%>"+obj.data);
				        	   logo = obj.data;
				           }else{
				        	   alert("上传图片失败");
				           }
				        }
				    });
				});
		        //删除按钮
				$("#deleteLogo").on("click",function(){
					logo = "";
					$("#showImg").prop("src","image/nopic.jpg");
				});
		     
		       //编辑
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
		     
		     
		   $("#submit").click(function(){
			   if($("#name").val() == ''){
			    	alert("输入账户名称");
			    	return false;
			   }
			   if($("#address").val() == ''){
				    alert("详细地址");
			    	return false;
			   }
			   if($("#cityCode").val() == ''){
				    alert("城市代码");
			    	return false;
			   }
			   if($("#contactPhone").val() == ''){
				    alert("联系人手机号");
			    	return false;
			   }
			   if(logo == ''){
				    alert("添加门店图片logo");
			    	return false;
			   }
			   if($("#intro").val() == ''){
				    alert("输入介绍");
			    	return false;
			   }
			    var cityCode = $("#county").val() != 0?$("#county").val():$("#city").val()!=0?$("#city").val():$("#province").val();
				if(cityCode == 0){
					alert("请选择城市");
					return false;
				}
			   
			    $.post("store/update",{
			    	id:<%=id%>,
			    	name:$("#name").val(),
                    cityCode:cityCode,//test
			    	address:$("#address").val(),
			    	phone:$("#phone").val(),
			    	shopLogo:logo,
			    	latitude:($("#latitude").val())*1000000,
			    	longitude:($("#longitude").val())*1000000,
			    	intro:$("#intro").val()
			    },function(data){
			    	if(data.code == 0){
			    		alert("更新门店成功");
			    		location.href="<%=url%>";
			    	}else{
			    		alert("更新门店失败");
			    	}
			    });
			   
		   });
		   
		   
	   });
	 
	</script>
</head>
<body>
                 <div class="contentNav">
                      /&nbsp;&nbsp;<span>门店管理</span>
                 </div>
                 <div class="taps nav-tabs">
                     <span class="defaultSel">
                                                                    编辑门店
                     </span>
                 </div>
                 
<div class="form-horizontal ajaxfrom" role="form" id="form-user">
						<div class="panel-body">
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">
						门店名称
					</label>
					<div class="col-sm-8 col-lg-9 col-xs-12">
						<input type="text" class="form-control" id="name" value="" placeholder="门店名称">
						<div class="help-block">
							请填写门店名称
						</div>
					</div>
				</div>
				<div class="form-group">
						<label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">城市</label>
						<div class="col-sm-8 col-lg-9 col-xs-12">
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
					<label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">
						详细地址
					</label>
					<div class="col-sm-8 col-lg-9 col-xs-12">
						<input type="text" class="form-control" id="address" value="" placeholder="详细地址">
						<div class="help-block">
							请填写门店详细地址
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">
						联系电话
					</label>
					<div class="col-sm-8 col-lg-9 col-xs-12">
						<input type="text" class="form-control" id="phone" value="" placeholder="联系电话">
						<div class="help-block">
							请填写门店联系电话
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">
						门店缩略图
					</label>
					<div class="col-sm-8 col-lg-9 col-xs-12">
						
		<script type="text/javascript">
			function showImageDialog(elm, opts, options) {
				require(["util"], function(util){
					var btn = $(elm);
					var ipt = btn.parent().prev();
					var val = ipt.val();
					var img = ipt.parent().next().children();
					options = {'dest_dir':'articles','global':false,'class_extra':'','direct':true,'multiple':false};
					util.image(val, function(url){
						if(url.url){
							if(img.length > 0){
								img.get(0).src = url.url;
							}
							ipt.val(url.attachment);
							ipt.attr("filename",url.filename);
							ipt.attr("url",url.url);
						}
						if(url.media_id){
							if(img.length > 0){
								img.get(0).src = "";
							}
							ipt.val(url.media_id);
						}
					}, null, options);
				});
			}
			function deleteImage(elm){
				require(["jquery"], function($){
					$(elm).prev().attr("src", "image/nopic.jpg");
					$(elm).parent().prev().find("input").val("");
				});
			}
		</script>
		<div class="input-group ">
			<input type="file" name="logoImage" class="form-control" id="logoImage">
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" id="uploadLogo">上传</button>
			</span>
		</div>
		<div class="input-group " style="margin-top:.5em;">
			<img src="image/nopic.jpg" onerror="this.src='image/nopic.jpg'; this.title='图片未找到.'" class="img-responsive img-thumbnail" width="150" id="showImg">
			<em class="close" style="position:absolute; top: 0px; right: -14px;" title="删除这张图片" id="deleteLogo">×</em>
		</div>					</div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">经纬度</label>
					<div class="col-sm-9 col-xs-12">
						<input type="text" id="latitude" id="latitude" class="form-control" value="">
						<input type="text" id="longitude" id="longitude" class="form-control" value="">
						（选择地图获取经纬度）

						<div style="padding-top: 20px; margin-bottom: 5px;">
							<input type="text" placeholder="输入搜索地区" class="form-control" style="width:30%; display: inline-block;" name="address" id="address" value=""> &nbsp;
							<input class="btn btn-success btn-sm" type="button" style="display: inline-block;" value="获取经纬度" onclick="searchByStationName();">
						</div>
						<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script><script type="text/javascript" src="http://api.map.baidu.com/getscript?v=1.4&amp;ak=&amp;services=&amp;t=20150522093217"></script>
						<div id="allmap" style="width: 100%; height: 500px; overflow: hidden; margin: 0px; position: relative; z-index: 0; background-color: rgb(243, 241, 236); color: rgb(0, 0, 0); text-align: left;"><div style="overflow: visible; position: absolute; z-index: 0; left: 1px; top: 2px; cursor: default;"><div class="BMap_mask" style="position: absolute; left: -1px; top: -2px; z-index: 9; overflow: hidden; user-select: none; width: 477px; height: 500px;"></div><div style="position: absolute; height: 0px; width: 0px; left: 0px; top: 0px; z-index: 200;"><div style="position: absolute; height: 0px; width: 0px; left: 0px; top: 0px; z-index: 800;"></div><div style="position: absolute; height: 0px; width: 0px; left: 0px; top: 0px; z-index: 700;"></div><div style="position: absolute; height: 0px; width: 0px; left: 0px; top: 0px; z-index: 600;"></div><div style="position: absolute; height: 0px; width: 0px; left: 0px; top: 0px; z-index: 500;"><label class="BMapLabel" unselectable="on" style="position: absolute; display: none; cursor: inherit; background-color: rgb(190, 190, 190); border: 1px solid rgb(190, 190, 190); padding: 1px; white-space: nowrap; font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 12px; line-height: normal; font-family: arial, sans-serif; z-index: -20000; color: rgb(190, 190, 190);">shadow</label><label class="BMapLabel" unselectable="on" style="position: absolute; cursor: inherit; background: rgb(255, 255, 255); border: 1px solid rgb(153, 153, 153); padding: 1px; white-space: nowrap; font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 12px; line-height: normal; font-family: arial, sans-serif; z-index: -8006376; max-width: none; user-select: none; left: 18px; top: 336px; display: none;">116.428128,40.03188</label></div><div style="position: absolute; height: 0px; width: 0px; left: 0px; top: 0px; z-index: 400;"></div><div style="position: absolute; height: 0px; width: 0px; left: 0px; top: 0px; z-index: 300;"></div><div style="position: absolute; height: 0px; width: 0px; left: 0px; top: 0px; z-index: 201;"></div><div style="position: absolute; height: 0px; width: 0px; left: 0px; top: 0px; z-index: 200;"></div></div><div style="position: absolute; overflow: visible; top: 0px; left: 0px; z-index: 1;"><div style="position: absolute; overflow: visible; z-index: -100; left: 238px; top: 248px;"><img src="http://online3.map.bdimg.com/tile/?qt=tile&amp;x=6329&amp;y=2364&amp;z=15&amp;styles=pl&amp;udt=20150518" style="position: absolute; border: none; width: 256px; height: 256px; left: -116px; top: -17px; max-width: none; opacity: 1;"><img src="http://online4.map.bdimg.com/tile/?qt=tile&amp;x=6330&amp;y=2364&amp;z=15&amp;styles=pl&amp;udt=20150518" style="position: absolute; border: none; width: 256px; height: 256px; left: 140px; top: -17px; max-width: none; opacity: 1;"><img src="http://online0.map.bdimg.com/tile/?qt=tile&amp;x=6330&amp;y=2365&amp;z=15&amp;styles=pl&amp;udt=20150518" style="position: absolute; border: none; width: 256px; height: 256px; left: 140px; top: -273px; max-width: none; opacity: 1;"><img src="http://online3.map.bdimg.com/tile/?qt=tile&amp;x=6330&amp;y=2363&amp;z=15&amp;styles=pl&amp;udt=20150518" style="position: absolute; border: none; width: 256px; height: 256px; left: 140px; top: 239px; max-width: none; opacity: 1;"><img src="http://online2.map.bdimg.com/tile/?qt=tile&amp;x=6328&amp;y=2364&amp;z=15&amp;styles=pl&amp;udt=20150518" style="position: absolute; border: none; width: 256px; height: 256px; left: -372px; top: -17px; max-width: none; opacity: 1;"><img src="http://online2.map.bdimg.com/tile/?qt=tile&amp;x=6329&amp;y=2363&amp;z=15&amp;styles=pl&amp;udt=20150518" style="position: absolute; border: none; width: 256px; height: 256px; left: -116px; top: 239px; max-width: none; opacity: 1;"><img src="http://online4.map.bdimg.com/tile/?qt=tile&amp;x=6329&amp;y=2365&amp;z=15&amp;styles=pl&amp;udt=20150518" style="position: absolute; border: none; width: 256px; height: 256px; left: -116px; top: -273px; max-width: none; opacity: 1;"><img src="http://online1.map.bdimg.com/tile/?qt=tile&amp;x=6328&amp;y=2363&amp;z=15&amp;styles=pl&amp;udt=20150518" style="position: absolute; border: none; width: 256px; height: 256px; left: -372px; top: 239px; max-width: none; opacity: 1;"><img src="http://online3.map.bdimg.com/tile/?qt=tile&amp;x=6328&amp;y=2365&amp;z=15&amp;styles=pl&amp;udt=20150518" style="position: absolute; border: none; width: 256px; height: 256px; left: -372px; top: -273px; max-width: none; opacity: 1;"></div></div><div style="position: absolute; overflow: visible; top: 0px; left: 0px; z-index: 2;"></div></div><div class=" anchorBL" style="height: 32px; position: absolute; z-index: 10; text-size-adjust: none; bottom: 0px; right: auto; top: auto; left: 1px;"><a title="到百度地图查看此区域" target="_blank" href="http://map.baidu.com/?sr=1" style="outline: none;"><img style="border:none;width:77px;height:32px" src="http://api.map.baidu.com/images/copyright_logo.png"></a></div><div id="zoomer" style="position:absolute;z-index:0;top:0px;left:0px;overflow:hidden;visibility:hidden;cursor:default"><div class="BMap_zoomer" style="top:0;left:0;"></div><div class="BMap_zoomer" style="top:0;right:0;"></div><div class="BMap_zoomer" style="bottom:0;left:0;"></div><div class="BMap_zoomer" style="bottom:0;right:0;"></div></div><div unselectable="on" class=" BMap_stdMpCtrl BMap_stdMpType0 BMap_noprint anchorTL" style="width: 62px; height: 192px; bottom: auto; right: auto; top: 10px; left: 10px; position: absolute; z-index: 1100; text-size-adjust: none;"><div class="BMap_stdMpPan"><div class="BMap_button BMap_panN" title="向上平移"></div><div class="BMap_button BMap_panW" title="向左平移"></div><div class="BMap_button BMap_panE" title="向右平移"></div><div class="BMap_button BMap_panS" title="向下平移"></div><div class="BMap_stdMpPanBg BMap_smcbg"></div></div><div class="BMap_stdMpZoom" style="height: 147px; width: 62px;"><div class="BMap_button BMap_stdMpZoomIn" title="放大一级"><div class="BMap_smcbg"></div></div><div class="BMap_button BMap_stdMpZoomOut" title="缩小一级" style="top: 126px; background-position: 0px -265px;"><div class="BMap_smcbg"></div></div><div class="BMap_stdMpSlider" style="height: 108px;"><div class="BMap_stdMpSliderBgTop" style="height: 108px;"><div class="BMap_smcbg"></div></div><div class="BMap_stdMpSliderBgBot" style="top: 25px; height: 87px;"></div><div class="BMap_stdMpSliderMask" title="放置到此级别"></div><div class="BMap_stdMpSliderBar" title="拖动缩放" style="cursor: url(&quot;http://api.map.baidu.com/images/openhand.cur&quot;) 8 8, default; top: 25px;"><div class="BMap_smcbg"></div></div></div><div class="BMap_zlHolder"><div class="BMap_zlSt"><div class="BMap_smcbg"></div></div><div class="BMap_zlCity"><div class="BMap_smcbg"></div></div><div class="BMap_zlProv"><div class="BMap_smcbg"></div></div><div class="BMap_zlCountry"><div class="BMap_smcbg"></div></div></div></div></div><div unselectable="on" class=" BMap_scaleCtrl BMap_noprint anchorBL" style="bottom: 18px; right: auto; top: auto; left: 81px; width: 89px; position: absolute; z-index: 10; text-size-adjust: none;"><div class="BMap_scaleTxt" unselectable="on" style="background-color: transparent; color: black;">500&nbsp;米</div><div class="BMap_scaleBar BMap_scaleHBar" style="background-color: black;"><img style="border:none" src="http://api.map.baidu.com/images/mapctrls.png"></div><div class="BMap_scaleBar BMap_scaleLBar" style="background-color: black;"><img style="border:none" src="http://api.map.baidu.com/images/mapctrls.png"></div><div class="BMap_scaleBar BMap_scaleRBar" style="background-color: black;"><img style="border:none" src="http://api.map.baidu.com/images/mapctrls.png"></div></div><div unselectable="on" class=" BMap_omCtrl BMap_ieundefined BMap_noprint anchorBR quad4" style="width: 13px; height: 13px; bottom: 0px; right: 0px; top: auto; left: auto; position: absolute; z-index: 10; text-size-adjust: none;"><div class="BMap_omOutFrame" style="width: 149px; height: 149px;"><div class="BMap_omInnFrame" style="bottom: auto; right: auto; top: 5px; left: 5px; width: 142px; height: 142px;"><div class="BMap_omMapContainer"></div><div class="BMap_omViewMv" style="cursor: url(&quot;http://api.map.baidu.com/images/openhand.cur&quot;) 8 8, default;"><div class="BMap_omViewInnFrame"><div></div></div></div></div></div><div class="BMap_omBtn BMap_omBtnClosed" style="bottom: 0px; right: 0px; top: auto; left: auto;"></div></div><div unselectable="on" class=" BMap_cpyCtrl BMap_noprint anchorBL" style="cursor: default; white-space: nowrap; color: black; background: none; font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 11px; line-height: 15px; font-family: arial, sans-serif; bottom: 2px; right: auto; top: auto; left: 81px; position: absolute; z-index: 10; text-size-adjust: none;"><span _cid="1" style="display: inline;"><span style="font-size:11px">© 2015 Baidu&nbsp;- Data © <a target="_blank" href="http://www.navinfo.com/" style="display:inline;">NavInfo</a> &amp; <a target="_blank" href="http://www.cennavi.com.cn/" style="display:inline;">CenNavi</a> &amp; <a target="_blank" href="http://www.365ditu.com/" style="display:inline;">道道通</a></span></span></div></div>
						<script type="text/javascript">
							var temp = {
								pt: [],
								mk: [],
								iw: [],
								iwOpenIndex: null,
								mouseLabel: null,
								poiSearchMark: null,
								geoCoder: null
							};

							//鼠标滑过提示的内容
							function createMouseMoveLabel(c) {
								var a = map.pixelToPoint(new BMap.Pixel(0, 0));
								var b = c.lng + "," + c.lat;
								var d = new BMap.Label(b, {
									point: a,
									offset: new BMap.Size(13, 20),
									enableMassClear: false
								});
								d.setStyle({
									background: "#fff",
									"max-width": "none",
									border: "#999 solid 1px",
									zIndex: 10000000
								});
								map.addOverlay(d);
								temp.mouseLabel = d;
							}

							//根据地址获取经纬度
							var map = new BMap.Map("allmap");
							map.centerAndZoom(new BMap.Point(116.395645, 39.929986), 12);// 初始化地图,设置中心点坐标和地图级别
							map.enableScrollWheelZoom();//添加鼠标滚动缩放
							map.setDefaultCursor("default");
							map.setDraggingCursor("default")

							map.addControl(new BMap.NavigationControl());//添加缩放平移控件
							map.addControl(new BMap.ScaleControl());//添加比例尺控件
							map.addControl(new BMap.OverviewMapControl());//添加缩略图控件

							//地图点击事件
							map.addEventListener("click", function (c) {
								var b = c.point;
								console.log(c)
								if (c.overlay && c.overlay instanceof BMap.Marker && c.overlay.point != undefined) {
									b = c.overlay.point
								}

								//alert(b.lng + "," + b.lat);
								$('#longitude').val(b.lng);//经度
								$('#latitude').val(b.lat);//纬度
							});

							//鼠标在地图滑动事件
							map.addEventListener("mousemove", function (c) {
								if (!temp.mouseLabel) {
									createMouseMoveLabel(c.point)
								}
								if (!temp.mouseLabel.isVisible()) {
									temp.mouseLabel.show()
								}
								var k = temp.mouseLabel;
								var h = map.getContainer();
								var g = h.clientWidth;
								var f = h.clientHeight;
								var j = 132;
								var i = 19;
								var n = map.pointToPixel(c.point).x + 13;
								var l = map.pointToPixel(c.point).y + 20;
								var m = map.pixelToPoint(new BMap.Pixel(g - j - 13, f - i - 20));
								var b = map.pixelToPoint(new BMap.Pixel(n - j - 33, f - i - 20));
								var d = c.point;
								if (g - n < j) {
									d = new BMap.Point(m.lng, d.lat)
								}
								if (f - l < i) {
									d = new BMap.Point(d.lng, m.lat)
								}
								if (g - n - 16 < j && f - l < i) {
									d = b
								}

								k.setPosition(d);
								k.setContent(c.point.lng + "," + c.point.lat);
							});

							document.body.addEventListener("mousemove", function (c) {
								var c = window.event || c;
								var b = c.srcElement || c.target;
								if (b.className != "BMap_mask" && temp.mouseLabel && temp.mouseLabel.isVisible()) {
									temp.mouseLabel.hide()
								}
							});

							document.body.addEventListener("mouseout", function (c) {
								var c = window.event || c;
								var b = c.srcElement || c.target;
								if (b.className == "BMap_mask" && temp.mouseLabel && temp.mouseLabel.isVisible()) {
									temp.mouseLabel.hide()
								}
							});

							//地图搜索事件
							function searchByStationName() {
								var keyword = document.getElementById("address").value;
								if (isEmpty(keyword)) {
									alert("输入搜索的地址");
									return false;
								}

								var localSearch = new BMap.LocalSearch(map, {
									renderOptions: {map: map}
								});

								localSearch.setSearchCompleteCallback(function (searchResult) {
									if (searchResult != undefined) {
										var poi = searchResult.getPoi(0);
										if (poi != undefined) {
											if (isEmpty(poi.point.lng) || isEmpty(poi.point.lat)) {
												alert("不存在该地址，请重新输入");
											} else {
												//alert(poi.point.lng + "," + poi.point.lat);
												$('#longitude').val(poi.point.lng);//经度
												$('#latitude').val(poi.point.lat);//纬度
											}
										} else {
											alert("不存在该地址，请重新输入");
										}
									}
								});
								localSearch.search(keyword);
							}

							//空判断 为空返回true否则返回false;
							//@author kwh
							function isEmpty(str) {
								if (str == "" || str == null || str == undefined || str == 0 || str.length <= 0 || str == '') {
									return true;
								}

								return false;
							}

							//用经纬度设置地图中心点
							theLocation();
							function theLocation() {
								if (!isEmpty($("#longitude").val()) && !isEmpty($("#latitude").val())){
									map.clearOverlays();
									var new_point = new BMap.Point($("#longitude").val(), $("#latitude").val());
									var marker = new BMap.Marker(new_point);  // 创建标注
									map.addOverlay(marker);              // 将标注添加到地图中
									map.panTo(new_point);
								}
							}
						</script>
					</div>
				</div>


				<div class="form-group">
					<label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">
						门店简介
					</label>
					<div class="col-sm-8 col-lg-9 col-xs-12">
					
					<textarea rows="5" cols="40" id="intro"></textarea>
									</div>
				</div>
				  <div class="form-group">
							<div class="col-sm-offset-2 col-md-offset-2 col-lg-offset-1 col-xs-12 col-sm-10 col-md-10 col-lg-11">
								<input type="submit" id="submit" class="btn btn-primary span3" name="submit" value="提交">
							</div>
				</div>
			</div>
                 </div>
</body>
</html>	
<script type="text/javascript">
  $(function(){
	  
  })
</script>