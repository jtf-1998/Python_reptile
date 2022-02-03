<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />

<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<title>admin v1.0</title>
</head>
<body>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="/aboutHui.shtml">2020新型冠状病毒实时动态系统</a> <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="/aboutHui.shtml">H-ui</a> 
			<span class="logo navbar-slogan f-l mr-10 hidden-xs">v1.0</span> 

			<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
			<nav class="nav navbar-nav">
				<ul class="cl">
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" onclick="member_add('添加用户','member-add.html','','510')"><i class="Hui-iconfont">&#xe60d;</i> 用户</a></li>
					</ul>
				</li>
			</ul>
		</nav>
		<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
			<ul class="cl">
				<li>超级管理员</li>
				<li class="dropDown dropDown_hover">
					<a href="#" class="dropDown_A">admin <i class="Hui-iconfont">&#xe6d5;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li>
						<li><a href="#">切换账户</a></li>
						<li><a href="#">退出</a></li>
				</ul>
			</li>
				<li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li>
				<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
						<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
						<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
						<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
						<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
						<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</div>
</div>
</header>
<aside class="Hui-aside">

	<div class="menu_dropdown bk_2">
		<dl id="menu-article">
			<dt><i class="Hui-iconfont">&#xe616;</i> 全国实时疫情<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="/GZBD//charts/charts-country.jsp" data-title="数据展示" href="javascript:void(0)">数据展示</a></li>
			</ul>
		</dd>
	</dl>

		<dl id="menu-tongji">
			<dt><i class="Hui-iconfont">&#xe61a;</i> 各省实时疫情<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="QueryByShen?shen=重庆" data-title="重庆" href="javascript:void(0)">重庆</a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=广东" data-title="广东" href="javascript:void(0)">广东</a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=四川" data-title="四川" href="javascript:void(0)">四川</a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=河南" data-title="河南" href="javascript:void(0)">河南</a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=上海" data-title="上海" href="javascript:void(0)">上海</a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=湖北" data-title="湖北" href="javascript:void(0)">湖北</a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=安徽" data-title="安徽" href="javascript:void(0)">安徽</a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=北京" data-title="北京" href="javascript:void(0)">北京</a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=海南" data-title="海南" href="javascript:void(0)">海南</a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=天津" data-title="天津" href="javascript:void(0)">天津</a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=山东" data-title="山东" href="javascript:void(0)">山东</a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=广西" data-title="广西" href="javascript:void(0)">广西</a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=湖南" data-title="湖南" href="javascript:void(0)">湖南</a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=云南" data-title="云南" href="javascript:void(0)">云南</a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=黑龙江" data-title="黑龙江" href="javascript:void(0)">黑龙江</a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=江苏" data-title="江苏" href="javascript:void(0)">江苏</a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=辽宁 " data-title="辽宁 " href="javascript:void(0)">辽宁 </a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=江西 " data-title="江西 " href="javascript:void(0)">江西 </a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=陕西 " data-title="陕西 " href="javascript:void(0)">陕西 </a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=新疆 " data-title="新疆 " href="javascript:void(0)">新疆 </a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=山西 " data-title="山西 " href="javascript:void(0)">山西 </a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=浙江 " data-title="浙江 " href="javascript:void(0)">浙江 </a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=内蒙古 " data-title="内蒙古 " href="javascript:void(0)">内蒙古 </a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=河北 " data-title="河北 " href="javascript:void(0)">河北 </a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=甘肃 " data-title="甘肃 " href="javascript:void(0)">甘肃 </a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=吉林 " data-title="吉林 " href="javascript:void(0)">吉林 </a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=贵州 " data-title="贵州 " href="javascript:void(0)">贵州 </a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=福建 " data-title="福建 " href="javascript:void(0)">福建 </a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=宁夏 " data-title="宁夏 " href="javascript:void(0)">宁夏 </a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=青海 " data-title="青海 " href="javascript:void(0)">青海 </a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=西藏 " data-title="西藏 " href="javascript:void(0)">西藏 </a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=香港 " data-title="香港 " href="javascript:void(0)">香港 </a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=澳门 " data-title="澳门 " href="javascript:void(0)">澳门 </a></li>
			</ul>
			<ul>
					<li><a data-href="QueryByShen?shen=台湾 " data-title="台湾 " href="javascript:void(0)">台湾 </a></li>
			</ul>
		</dd>
	</dl>
	
	<dl id="menu-tongji">
			<dt><i class="Hui-iconfont">&#xe61a;</i>数据分析<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
		</dd>
	</dl>

	
</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active">
					<span title="我的桌面" data-href="welcome.html">我的桌面</span>
					<em></em></li>
		</ul>
	</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="welcome.html"></iframe>
	</div>
</div>
</section>

<div class="contextMenu" id="Huiadminmenu">
	<ul>
		<li id="closethis">关闭当前 </li>
		<li id="closeall">关闭全部 </li>
</ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>

</body>
</html>