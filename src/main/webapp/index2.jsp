<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
	<meta charset="utf-8">
	<title>商家管理平台</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="pragma" content="no-cache">
    <link rel="shortcut icon" href="image/wechat.jpg">
	<link href="css/dpl-min.css" type="text/css" media="screen" rel="stylesheet">
	<link href="css/bui-min.css" type="text/css" media="screen" rel="stylesheet">
	<link href="css/dingmore-style-20140528.css?t=20140528" type="text/css" rel="stylesheet">

	<script type="text/javascript" src="js/lib/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/unslider.min.js"></script>
	<script type="text/javascript" src="js/bui-min.js"></script>
	<style type="text/css">

		.login_form{
			height:310px;
		}
		.login_form_content{
			height:260px;
		}

		.login_form_content .login_submit {
			width: 228px;
			height: 48px;
			border: none;
			background: url("image/login_submit.png") no-repeat;
		}
        .loginSelect{
           display:inline;
           margin-top:5px;
           margin-left:5px;
           font-size:14px;
           font-weight:bold;
        }
        .loginSelect label{
             line-height: 3;
             cursor: pointer;
        }
	</style>
</head>
<body class="login">
<!-- begin 头部 -->
<div class="login_header">
	<div class="container ">
		<div class="login_logo">
			<a href="/"><img alt="商家管理平台" src="image/logo.png"></a>
		</div>

	</div>
</div>
<!-- end 头部 -->
<!-- begin banner -->
<div class="login_banner">
	<div class="banner" style="overflow: hidden; width: 100%; height: 402px;">
		<ul style="position: relative; width: 100%; left: 0%; height: 402px;">
			<li style="width: 749px; float: left;"><img src="image/login_flash_01.jpg" style="left: -585.5px; position: relative;"></li>
		</ul>
	</div>

	<div class="login_form" style="left: 577.5px; top: 108px; position: absolute;"></div>
	<div class="login_form_content" style="left: 607.5px; top: 118px; position: absolute;">
		<form action=""  method="post" class="form-horizontal bui-form bui-form-field-container" id="J_Form" aria-disabled="false" aria-pressed="false">
			<div style="display:none">
		</div>			<!--错误信息验证提示-->
			<p><img src="image/welcome_title.png"></p>

			<!-- username -->
			<div class="row">
				<div class="control-group">
					<label class="control-label"><img src="image/login_username.png"></label>
					<div class="controls">
						<input type="text" name="username" id="username" data-rules="{required:true,minlength:1,maxlength:30}" class="input-normal bui-form-field" data-messages="{required:'帐号不能为空!'}" placeholder="请输入您的帐号" style="width:180px;" aria-disabled="false">
					</div>
				</div>
			</div>
			<!-- password -->
			<div class="row">
				<div class="control-group">
					<label class="control-label"><img src="image/login_pwd.png"></label>
					<div class="controls">
						<input type="password" name="password" id="password" class="input-normal bui-form-field" data-rules="{required:true,minlength:1,maxlength:30}" data-messages="{required:'密码不能为空!'}" placeholder="请输入您的密码" style="width:180px;" aria-disabled="false">		
					</div>
				</div>
			</div>
			<!--  -->
			<div class="row">
				<div class="control-group">
					    <div class="loginSelect">
						   <input type="radio" name="login" value="1" id="admin" checked>
						   <label for="admin">管理员登录</label>
						</div>
						<div class="loginSelect"> 
						   <input type="radio" name="login" value="2" id="shop">
						   <label for="shop">商户门店登录</label>
						</div>
				</div>
			</div>



			<div class="row">
				<button type="button" id="submit" name="submit" value="登录" class="login_submit"></button>
			</div>
	</form></div>
		</div>

<script type="text/javascript">

	$(function(){
		function commonResultFunc(obj){
			if(obj.code == 0){
				if(obj.data == 0){
					alert("用户不存在");
					return false;
				}else if(obj.data == 1){
					window.location.href="<%=basePath%>main";
				}else if(obj.data == 2){
					alert("密码错误");
					return false;
				}
			}else{
				alert("系统错误");
				return false;
			}
		}
		$("#submit").on("click",function(){
			var log = $("input[type='radio']:checked").val();
			if(log == 1){// account
				$.post("account/login",{
					name:$("#username").val(),
					password:$("#password").val(),
					login:log
				},function(obj){
					commonResultFunc(obj);
				});
			}else if(log == 2){//businessUser
// 				alert("商户门店登录");
				$.post("storeAccount/login",{
					name:$("#username").val(),
					password:$("#password").val(),
					login:log
				},function(obj){
					commonResultFunc(obj);
				});
			}
				
			
		});
		//利用绝对位置，图片总偏移360px
		var body_width = $("body").width();
		var offset_width = (body_width - 1200)/2;

		$('.banner li').each(function(){
			$(this).find("img").css({left:"-"+(360-offset_width)+"px",position: "relative"});
			$(this).css({width:body_width});

		});
		var login_form_offset = offset_width+803;
		$('.login_form').css({left:login_form_offset+"px",top:"108px",position: "absolute"});//透明框
		$('.login_form_content').css({left:(login_form_offset+30)+"px",top:"118px",position: "absolute"});//不透明框
		$('.banner').unslider({
			fluid: true
		});
		$('.banner1').unslider({
			fluid: true
		});


		加载验证码
// 		$('#toggle').click(function() {
// 			$('#imgverify').prop('src', './index.php?c=utility&a=code&r='+Math.round(new Date().getTime()));
// 			return false;
// 		});

        /*$('#J_Form').submit(function() {
            var verify = $(':text[name="verify"]').val();
            if (verify == '') {
                alert('请填写验证码');
                return false;
            }
        });*/
	});

	$(window).resize(function(){
		//利用绝对位置，图片总偏移360px
		var body_width = $("body").width();
		var offset_width = (body_width - 1200)/2;

		$('.banner li').each(function(){
			$(this).find("img").css({left:"-"+(360-offset_width)+"px",position: "relative"});
			$(this).css({width:body_width});

		});
		var login_form_offset = offset_width+803;
		$('.login_form').css({left:login_form_offset+"px",top:"108px",position: "absolute"});//透明框

		$('.login_form_content').css({left:(login_form_offset+30)+"px",top:"118px",position: "absolute"});//不透明框
	});
</script>
<script type="text/javascript">
    //表单验证
    BUI.use('bui/form',function(Form){
        var formContent = new Form.Form({
            srcNode : '#J_Form',
            errorTpl : '<div class="login_error" ><div><img src="image/error_icon.png"></div><div>{error}</div></div>'
        }).render();
        formContent.on("blur",function(){
            formContent.valid();
        });
    });
</script>

<!-- end banner -->
<!-- begin 服务描述信息 -->
<div class="container login_about">
	<!-- 解决方案 -->
	<div class="row solve">
		<div class="til"><span>解决方案</span><!--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:14px;">前往<a style="font-size:20px;color:#FF8C00;" target="_blank" href="#">[产品中心]</a>了解更多!</span>-->
			<!--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:14px;">请至此<a style="font-size:20px;color:#FF8C00;" target="_blank" href="#">[商家入驻]</a>提交入驻资料!</span>-->
		</div>
		<div class="span8 left bg">
			<div class="left_til">智慧商圈基础服务</div>
			<div class="left_content">
				<ul class="toolbar">
					<li style="border-right: 1px solid rgb(238, 238, 238);">
						<div><img src="image/login_solve_left_1.png"></div>
						<div class="item_til">聚合支付</div>
						<div class="item_dec">聚合微信、支付宝主流支付方式</div>
					</li>
					<li style="border-right: 1px solid rgb(238, 238, 238);">
						<div><img src="image/login_solve_left_2.png"></div>
						<div class="item_til">全渠道发展会员</div>
						<div class="item_dec">实现支付即会员、营销即会员</div>
					</li>
					<li>
						<div><img src="image/login_solve_left_3.png"></div>
						<div class="item_til">门店诊断服务</div>
						<div class="item_dec">交易分布分析、客流结构属性分析</div>
					</li>
				</ul>
			</div>
		</div>
		<div class="span4 right bg">
			<div class="right_til">智慧商圈购物</div>
			<div class="right_content">
				<div class="right_img"><img src="image/login_solve_right.png"></div>
				<div class="item_til">社交电商</div>
				<div class="item_dec">商圈电商渠道，轻松搭建线上销售渠道</div>
			</div>

		</div>
	</div>

	<!-- 多效营销 -->
	<div class="row sell">
		<div class="til">多效营销</div>
		<div class="span8 left bg">
			<div class="left_content">
				<ul class="toolbar">
					<li style="border-right: 1px solid rgb(238, 238, 238);">
						<div><img src="image/login_sell_left_1.png"></div>
						<div class="item_til">多维度场景营销</div>
						<div class="item_dec">社交红包、商圈红包<br>高效推广</div>
					</li>
					<li style="border-right: 1px solid rgb(238, 238, 238);">
						<div><img src="image/login_sell_left_2.png"></div>
						<div class="item_til">增加门店客流</div>
						<div class="item_dec">商圈引流<br>汇聚人气的利器</div>
					</li>
					<li style="border-right: 1px solid rgb(238, 238, 238);">
						<div><img src="image/login_sell_left_3.png"></div>
						<div class="item_til">降低运营成本</div>
						<div class="item_dec">降低营销运营成本，<br>提升服务质量</div>
					</li>
					<li>
						<div><img src="image/login_sell_left_4.png"></div>
						<div class="item_til">增加销售机会</div>
						<div class="item_dec">提升消费体验，<br>触发消费欲望，销售机会大大提高</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- 智慧商街服务 -->
	<div class="row services">
		<div class="span6 serve left">
			<div class="til">智慧商街服务</div>
			<div class="content bg">
				<ul class="toolbar">
					<li style="border-right: 1px solid rgb(238, 238, 238);">
						<div><img src="image/login_serve_left_1.png"></div>
						<div class="item_til">用户沉淀系统</div>
						<div class="item_dec">100%的本地用户<br>一个公众号统一管理</div>
					</li>
					<li style="border-right: 1px solid rgb(238, 238, 238);">
						<div><img src="image/login_serve_left_2.png"></div>
						<div class="item_til">商圈营销系统</div>
						<div class="item_dec">统一营销<br>提升单店运营和营销能力</div>
					</li>
					<li>
						<div><img src="image/login_serve_left_3.png"></div>
						<div class="item_til">商圈培训支持</div>
						<div class="item_dec">全方位的跟进培训<br>提供完善的配套支持</div>
					</li>
				</ul>
			</div>
		</div>
		<div class="span4 more right">
			<div class="til">管理后台</div>
			<div class="content bg">
				<div class="span2 description">
					<div class="item_til">管理后台</div>
					<div class="item_dec">可以根据门店规模大小、地理位置等，设置不同的后台访问权限，并且运用多层级账号管理方法，给予每个门店最适宜的运营方案，提高资源利用率。</div>
				</div>
				<div class="span1 pic">
					<div><img src="image/login_serve_right.png"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- 微信服务号订制 -->
	<!--div class="row weixin"-->
	<div class="til">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;微信扫一扫，你关心的都在这里！</div>
	<div class="content">
		<img src="image/login_weixin.jpg">
	</div>
</div>

<!-- end 智慧商街服务描述信息 -->
<script type="text/javascript">
	//给不是最后的一个介绍内容加上右边框
	$(function(){
		$(".solve li").not(".solve li:last").each(function(i){
			$(this).css("border-right","1px solid #eeeeee");
		});
		$(".sell li").not(".sell li:last").each(function(i){
			$(this).css("border-right","1px solid #eeeeee");
		});
		$(".services li").not(".services li:last").each(function(i){
			$(this).css("border-right","1px solid #eeeeee");
		});
	})
</script>

<div class="login_footer">
	<div class="container">
		<div class="join"><img src="image/login_footer_join.png"></div>
		<div class="line"><img src="image/login_footer_line.png"></div>
		<ul class="breadcrumb">


			<!--<li><a href="/">关于我们</a> <span class="divider">|</span></li>-->
			<!--<li><a href="/">新闻动态</a> <span class="divider">|</span></li>-->
			<!--<li><a href="/">渠道合作</a> <span class="divider">|</span></li>-->
			<!--<li><a href="/">诚聘英才</a> <span class="divider">|</span></li>-->
			<!--<li><a href="/">合作伙伴</a> <span class="divider">|</span></li>-->
			<!--<li><a href="/">官方微博</a> <span class="divider">|</span></li>-->
			<!--<li><a href="/">产品中心</a> <span class="divider">|</span></li>-->
			<li>2014-2016 智慧商街版权所有 京ICP备15004903号—4</li>

		</ul>
	</div>
</div>


</body></html>
