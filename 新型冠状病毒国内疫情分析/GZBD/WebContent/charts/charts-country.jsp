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
<link rel="stylesheet" type="text/css" href="../static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="../lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/style.css" />
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
<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="echarts.min.js"></script>
<script type="text/javascript" src="jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="china.js"></script>
<script type="text/javascript">
window.onload=function(){
	jQuery(document).ready(function($) {
		 
		 var myChart = echarts.init(document.getElementById('container'));
		 var nav=document.getElementById("nav");
		var reg = new RegExp("(d=)([a-z]*)");
		var str=window.location.search.substr(1).match(reg);
		var tr=document.getElementById("country_list");
		var tds=tr.getElementsByTagName("td");
		var map=document.getElementById("map");
		nav.innerHTML='<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 全国实时疫情<span class="c-gray en">&gt;</span> 数据展示 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>';
		
		var myChartmap = echarts.init(map,'shine');
		function randomData() {
		    return Math.round(Math.random() * 1000);
		}

		function NumDescSort(a,b){
		    return a.value-b.value;
		}

		var option = {
		    title: [{                   //标题
		    	text: '全国确诊病例',
		   	 	subtext: '数据来自卫健委',
		    	x:'40%',
		    	y:'15%'
		    }],
		    tooltip: {
		    	trigger: 'item'
		    },
		    visualMap: {     //左下角拉条
		    	min: 0,
		   		max: 2500,
		    	left: '20%',
		    	top: '35%',
		    	text: ['目前', '从现在向前推发病地区总况'],
		    	calculable: true,
		    	colorLightness: [0.2, 100],
		    	color: ['#c05050','#e5cf0d','#5ab1ef'],
		    	dimension: 0
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {
		                show: true,
		               // type: ['pie', 'funnel']
		            },
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    series: [{              //地图
		    	z: 1,
		    	name: '全国疫情',
		    	type: 'map',
		    	map: 'china',
		    	left: '35%',
		    	top: '20%',

		    	zoom: 1,
		    	label: {
		        	normal: {
		        		show: true
		        	},
		        	emphasis: {
		        		show: true
		        	}
		    	},
		    //roam: true,
		    data:[]
		    }]
		};
		myChartmap.setOption(option); 
		
			//配置扇形图
		 var  option = {
		    title : {
		        text: '各省占比（确诊）',
		        subtext: '数据来自各省卫健委',
		        x:'40%',
		        y:'5%'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
	            data:[],
	            orient: 'vertical',
		        left:'15%',
		        top:'10%',
		        name:'各省小组件',
	        },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {
		                show: true,
		               // type: ['pie', 'funnel']
		            },
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    series : [
		        {
		            name:'各省占比（确诊）',
		            type:'pie',
		            radius: ['5%', '65%'],
		            center:['70%','70%'],
		            max: 40,                // for funnel
		            sort : 'ascending',     // for funnel
		            data:[]
		        }
		    ]
		};
		 
		 
		//设置图表
		myChart.setOption(option);
		 

		$.ajax({
		    type : "post",
		    async : true,
		    url : "../QueryAll",
		    type:"POST",
		    data:
		    	{
		    	"method":"shan"
		    	},
		    dataType:"json",
		    
		    success:function(result) {
		       // alert(result.selected);
		        if(result)
		        {
		        	    var d=result;
		        	    var leblist = [];
		        	    var valuelist2 = [];
		        	    var valuelist1=[];
		        	 	var list_country={"nd":d[0].nd,"confirm":d[0].confirm,"dead":d[0].dead,"suspect":d[0].suspect,"heal":d[0].heal};
		        	 	tds[4].innerHTML='<h5>'+list_country["nd"]+'</h5>';
		        	 	tds[0].innerHTML='<h5>'+list_country["confirm"]+'</h5>';
		        	 	tds[1].innerHTML='<h5><font color="red">'+list_country["dead"]+'</font></h5>';
		        	 	tds[2].innerHTML='<h5>'+list_country["suspect"]+'</h5>';
		        	 	tds[3].innerHTML='<h5><font color="green">'+list_country["heal"]+'</font></h5>';
		        	 	
		        	for(var i=0;i<d.length;i++){
		        	 
		        	         var m={};
		        	         var n={};
		        	        m["value"]=d[i].dconfirm;
		        	        m["name"]=d[i].shen;
							valuelist1.push(m);
		        	        valuelist2.push(m);
		        	        n["name"]=d[i].shen;
		        	        leblist.push(n);

		        	}
		        	myChartmap.setOption({
	        	        legend: {
	        	            data:leblist,
	        	            orient: 'vertical',
	        		        left:10,
	        		        name:'各省小组件',
	        	        },
	        	        series: [
	        	        {
	        	            name:'全国疫情',
	        	            data: valuelist1
	        	        }]
	        	    });
		        	// 将数据添加到数据图表中
		        	    myChart.setOption({
		        	        legend: {
		        	            data:leblist
		        	        },
		        	        series: [
		        	        {
		        	            name:'各省占比（确诊）',
		        	            data: valuelist2
		        	        }]
		        	    });
		                  
		   }
		    },
		    error : function(errorMsg) {
		        //请求失败时执行该函数
		    alert("请求数据失败!");
		   // myChart.hideLoading();
		    }
		});
		//获取和处理数据

		 
		//myChart.hideLoading();
		 
		});
}
</script>
<body>
<nav class="breadcrumb" id="nav">
</nav>
<div id="list">
	<div>
		<h3 style="text-align:center">全国实时数据</h3>
	<table style="align:center">
		<tr>
			<td  align="center"><h4>确诊</h4></td>
			<td align="center"><h4>死亡</h4></td>
			<td align="center"><h4>疑似</h4></td>
			<td align="center"><h4>治愈</h4></td>
			<td align="center"><h4>统计时间</h4></td>
		</tr>
		<tr id="country_list">
			<td align="center"></td>
			<td align="center"></td>
			<td align="center"></td>
			<td align="center"></td>
			<td align="center"></td>
		</tr>
	</table>
	</div>
</div >
	<div id="map">
	</div>

<div class="page-container">
	<div id="container" style="min-width:700px;height:400px">
	</div>
</div>
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