<%@page import="com.lq.bean.CiTiao"%>
<%@page import="java.util.List"%>
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
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>统计图</title>
</head>
<!--_footer 作为公共模版分离出去-->
<style>
  
        div.search {padding: 30px 0;}
		table{
		        text-align: center;
		}

        form {
            position: relative;
            width: 300px;
            margin: 0 auto;
        }

        input, button {
            
            outline: none;
        }

        input {
            width: 100%;
            height: 42px;
            padding-left: 13px;
        }

        button {
            height: 42px;
            width: 42px;
            cursor: pointer;
            position: absolute;
        }

        .bar7 form {
            height: 42px;
        }
        .bar7 input {
            width: 250px;
            border-radius: 42px;
            border: 2px solid #324B4E;
            background: #F9F0DA;
            transition: .3s linear;
            float: right;
        }
        .bar7 input:focus {
            width: 300px;
        }
        .bar7 button {
            background:url("outfile/button.png");
            background-repeat:no-repeat;
            width:28px;
            height:28px;
            top:10px;
            right:15px;
        }
        .bar7 button:before{
          font-family: FontAwesome;
          
        } 
        }
</style>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
window.onload=function(){
	var search=document.getElementById("search");
	var submit=document.getElementById("submit");
	var nav=document.getElementById("nav");
	nav.innerHTML='<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 热词管理<span class="c-gray en">&gt;</span> 热词搜索 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>';
	submit.onclick=function(){
		var keyword=search.value;
		if(keyword==""){
			alert("请输入搜索词");
		}
		
	}
}
</script>
<body>
<nav class="breadcrumb" id="nav">
</nav>
<div class="page-container">
    <div class="search bar7">
        <form method="post" action="QueryKey">
            <input id="search" name="keyword" type="text" placeholder="请输入您要搜索的内容...">
            <button type="submit" id="submit"></button>
        </form>
    </div>
	<%
	int id=0;
	String name=null;
	String des=null;
	String url=null;
	
	%>
	<table rules=all border frame=box>
		<thead>
		<tr>
			<td style="width: 40px align：center"><h4>序号</h4></td>
			<td style="width: 100px align：center"><h4>词条名称</h4></td>
			<td style="width: 880px align：center"><h4>词条概要</h4></td>
			<td style="width: 200px align：center"><h4>词条百科链接</h4></td>
		</tr>
		
		</thead>
		<tbody>
	<%
		List<CiTiao> ciTiaos=(List<CiTiao>)request.getAttribute("citiaos");
		for(int i=0;i<ciTiaos.size();i++){
			id=i+1;
			name=ciTiaos.get(i).getName();
			des=ciTiaos.get(i).getDes();
			url=ciTiaos.get(i).getUrl();
	%>
	
		<tr>
			<td style="width: 40px"><%=id %></td>
			<td style="width: 100px"><%=name %></td>
			<td style="width: 880px"><%=des %></td>
			<td style="width: 200px"><a href="<%=url %>"><%=url %></a></td>
		</tr>
	<%
		}
	
	%>
	</tbody>
	</table>
</div>
<footer class="footer mt-20">
	<div class="container">
		<p>感谢你们的使用 &copy;2020 信息热词统计<br>
			本后台系统由<a href="" target="_blank" title="">石家庄铁道大学软件工程</a>提供技术支持</p>
			
	</div>
</footer>
</body>
</html>