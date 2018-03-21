<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<title>Title</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<!--  已经实现的基本上传功能
	<form action="FileUpServlet" method="post"
		enctype="multipart/form-data">
		最简单的文件上传：<input type="file" name="fileupload" /> 
		描述：<input type="text" name="desc" /> 
		<input type="submit" value="submit" />
	</form>
	
-->
	<form id="fileForm" method="POST"  enctype="multipart/form-data">
		简单的文件上传：<input type="file" name="fileupload" />
		<input type="button" onclick="cli()" id="up_btn" value="提交"/>
	</form>
	<p id="txtContent"></p>
</body>
<script type="text/javascript">
	function cli(event){
		console.log("1111");
		//if(event.preventDefault) 
		//	event.preventDefault();
		//else event.returnValue = false;    
		
		//对于IE的取消方式
		
		
		var formDOM = $("#fileForm");
		//将form的DOM对象当作FormData的构造函数
		var formData = new FormData(formDOM);
		var req = new XMLHttpRequest();
		req.open("POST", "FileUpServlet",true);
		//请求完成
		req.onload = function() {
			console.log(this)
			if (this.status === 200) {
				console.log(this)
				$("#txtContent").text(this.data)
				//对请求成功的处理
			}
		}
		//将form数据发送出去
		req.send(formData);
		//避免内存泄漏
		req = null;
		
		
	}


</script>

</html>