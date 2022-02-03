	function ajax(url,fun_success,fun_failed)
	{
		var oAjax;
		if(window.XMLHttpRequest){
			oAjax=new XMLHttpRequest();  //1,创建Ajax对象
		}else{
			oAjax=new ActiveXObject("Microsoft.XMLHTTP");
		}
		//2，连接服务器
		//（Ajax使用）异步：多事情一起做；同步：事情一件一件做
		oAjax.open("post",url,true); //open(方法，要读取的文件，异步传输（默认为true）)
		oAjax.send();      //3，发送请求
		oAjax.onreadystatechange=function(){//4，接受返回
			oAjax.readyState      //浏览器和服务器进行到哪一步了
			if(oAjax.readyState==4){    //读取完成
				if(oAjax.status==200){    //http状态码；200为成功
				  var strs=oAjax.responseText;
				  fun_success(strs)
				}else{
					var strs="操作失败"+oAjax.status;
					if(fun_failed){
						fun_failed(strs)
					}
				}
			}
		}
	}