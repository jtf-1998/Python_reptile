<%@page import="com.lq.bean.BinLin"%>
<%@page import="java.util.ArrayList"%>
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
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="/GZBD//static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="/GZBD//static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="/GZBD//lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="/GZBD//static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="/GZBD//static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>统计图</title>
</head>
<style>
 #list{
 	min-width:700px;
 	height:20%;
 }
 #map{
 	width:90%;
 	height:600px;
 }
</style>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/GZBD//lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="/GZBD//lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/GZBD//static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="/GZBD//static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/GZBD//charts/echarts.min.js"></script>
<script type="text/javascript" src="/GZBD//charts/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/GZBD//charts/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/GZBD//charts/china.js"></script>
<script type="text/javascript">
window.onload=function(){
	 var nav=document.getElementById("nav");
	nav.innerHTML='<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 各省实时疫情<span class="c-gray en">&gt;</span> 数据展示 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>';
	var map=document.getElementById("map");
	var myChartmap = echarts.init(map,'shine');
	var table=document.getElementById('tb');
	var tbody=table.tBodies[1];
	var trs=tbody.getElementsByTagName("tr");
	var yiqins=[];
	var diques=[];
	for(var i=0;i<trs.length;i++)
		{
			var m={};
			var n={};
			var dq=trs[i].cells[0].innerHTML;
			var reg = new RegExp("(<h4>)([\u4e00-\u9fa5|0-9].*)(</h4>)");
			var diqu=dq.match(reg);
			var df=trs[i].cells[1].innerHTML;
			var dconfirm=df.match(reg);
			m["name"]=diqu[2];
			m["value"]=dconfirm[2];
			n["name"]=diqu[2];
			yiqins.push(m);
			diques.push(n);
		}
}
</script>
<body>
<nav class="breadcrumb" id="nav">
</nav>
<div id="list">
<%
	request.setCharacterEncoding("utf-8");
	String shen=request.getParameter("shen");
	String diqu=null;
	String dconfirm=null;
	String dheal=null;
	String ddead=null;
%>
	<div>
		<h3 style="text-align:center"><%=shen %>实时数据</h3>
	<table align="center" id="tb">
		<tr>
			<td  align="center" width="150px"><h4>地区</h4></td>
			<td  align="center" width="150px"><h4>确诊</h4></td>
			<td  align="center" width="150px"><h4>治愈</h4></td>
			<td  align="center" width="150px"><h4>死亡</h4></td>
			
		</tr>
		<tbody>

<%
ArrayList<BinLin> binLins=(ArrayList<BinLin>)request.getAttribute("binlin");
for(BinLin binLin:binLins){
	shen=request.getParameter("shen");
	diqu=binLin.getDiqu();
	dconfirm=binLin.getDconfirm()+"";
	dheal=binLin.getDheal()+"";
	ddead=binLin.getDdead()+"";

%>
		<tr id="country_list">
			<td align="center" width="150px"><h5><%=diqu %></h5></td>
			<td align="center" width="150px"><h5><%=dconfirm %></h5></td>
			<td align="center" width="150px"><h5><font color="green"><%=dheal %></font></h5></td>
			<td align="center" width="150px"><h5><font color="red"><%=ddead %></font></h5></td>
		</tr>
	<%
		}
	
	%>
	</tbody>
	</table>
	</div>
</div >
	<div id="map">
	</div>
<script>


</script>
<footer class="footer mt-20">
	<div class="container">
		<p>感谢你们的使用 &copy;2020新型冠状病毒统计<br>
			本后台系统由<a href="" target="_blank" title="">石家庄铁道大学软件工程</a>提供技术支持</p>
			
	</div>
</footer>
</body>
</html>
</body>
</html>