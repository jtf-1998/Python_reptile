<%@page import="java.util.List"%>
<%@page import="com.lq.bean.XinJian"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<title>信件列表</title>
</head>
<style>
	
</style>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<!--请在下方写此页面业务相关的脚本-->

<script type="text/javascript">
window.onload=function(){

<%
	int id=0;  //信件列号
	String Id=null; //信件代码
	String type=null; //信件类型
	String massage=null; //信件概要
	String statue=null;  //回复状态
	String send_time=null;  //发起时间
	String addresser=null;  //发信用户
	String answering=null;  //回答方
   List<XinJian> xinjians=(List<XinJian>)request.getAttribute("xinjians");
   String count=request.getAttribute("count").toString();
%>
setTimeout(()=>
	var count=<%=count%>;   //插入总条数
	var tbody=document.getElementById("tbody");
	var id=tbody.rows.length+1;
	function add(){
		
			<%
			for(int j=0;j<100;j++)
			{
			   XinJian xJian=xinjians.get(j);
			    id=j+1;  //信件列号
				Id=xJian.getId(); //信件代码
				type=xJian.getType(); //信件类型
				massage=xJian.getMassage(); //信件概要
				statue=xJian.getStatue();  //回复状态
				send_time=xJian.getSend_time();  //发起时间
				addresser=xJian.getAddresser();  //发信用户
				answering=xJian.getAnswering();  //回答方
			%>
			var xj=document.createElement('tr');
        
        	var id=document.createElement('td');
        	id.innerHTML=<%=id%>;
        	xj.appendChild(id);
        	var Id=document.createElement('td');
        	Id.innerHTML=<%=Id%>;
        	ntr.appendChild(Id);
       	 	var type=document.createElement('td');
       	 	type.innerHTML=<%=type%>;
        	xj.appendChild(type);
        	var massage=document.createElement('td');
       	 	massage.innerHTML=<%=massage%>;
        	xj.appendChild(massage);
        	var statue=document.createElement('td');
       	 	statue.innerHTML=<%=statue%>;
        	xj.appendChild(statue);
        	var send_time=document.createElement('td');
       	 	send_time.innerHTML=<%=send_time%>;
        	xj.appendChild(send_time);
        	var addresser=document.createElement('td');
       	 	addresser.innerHTML=<%=addresser%>;
        	xj.appendChild(addresser);
        	var answering=document.createElement('td');
       	 	answering.innerHTML=<%=answering%>;
        	xj.appendChild(answering);
        	
        	tbody.appendChild(ntr);
    <%
	}
	%>
	}
,0)

}
</script>
<body>
<nav class="breadcrumb" id="nav">
<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 信件统计管理 <span class="c-gray en">&gt;</span> 信件类型 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i>
</nav>
<div id="div1">
<span>信件总数：</span><span><%=count %>条</span>
	<table class="table">
		<thead>
			<tr>
				<th colspan="2" scope="col">信件列号</th>
				<th colspan="2" scope="col">信件代码</th>
				<th colspan="2" scope="col">信件类型</th>
				<th colspan="2" scope="col">信件概要</th>
				<th colspan="2" scope="col">回复状态</th>
				<th colspan="2" scope="col">发起时间</th>
				<th colspan="2" scope="col">发信用户</th>
				<th colspan="2" scope="col">回答方</th>
			</tr>
		</thead>
		<tbody id="tbody">


		</tbody>
	</table>
</div>
	<div class="container">
		<p>感谢你们的使用 &copy;2020 北京信件统计<br>
			本后台系统由<a href="" target="_blank" title="">石家庄铁道大学软件工程</a>提供技术支持</p>
			
	</div>

</body>
</html>