<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<title>Title</title>
</head>

<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
 
<!-- popper.min.js 用于弹窗、提示、下拉菜单 -->
<script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
 
<!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="css/fileinput-rtl.css">
<link rel="stylesheet" href="css/fileinput.css">
<link rel="stylesheet" href="css/my.css">
<script src="js/fileinput.js"></script>
<script src="js/locales/zh.js"></script>
<script src="js/wsjs.js"></script>
<script src="js/fileRead.js"></script>
<body>
<div class="container">
	<h5 class="text-center display-4">输入字符：</h5>
	<h6></h6>
	<input class=" form-control input-lg" type="text" id="txt1"
		class=" form-control " />

	<div class="btn btn-success btn-lg form-control margin"
		onclick='send()'>确认</div>
	<h5 class="text-center display-4">输出为：</h5>
	
	<textarea id="txtHint" class=" form-control	  "></textarea>
	
	<div class="btn btn-success btn-lg form-control margin"
		onclick="jsCopy()">复制</div>

	<h5 class="text-center display-4 mt-20">文件输入：</h5>
	<div class="file-loading mt-20">
	    <input id="files" name="input-b7[]"  type="file" 
	    class=" file " data-allowed-file-extensions='["txt"]'
	    data-show-preview="false"
	    onchange="fileImport()"
	    >
	</div>


    <div class = "row ">
    <div class="col-6 w-90">
    <textarea id="txtSrc" class="mh-300 form-control "></textarea>
    </div>
   	<div class="col-6 w-90">
	<textarea id="txtRes" class="mh-300 form-control "></textarea>
	</div>
	</div>
    

 


<p id = "txtContent"></p>
	


</div>
</body>
<script >
$("#txt1").keydown(function() {//给输入框绑定按键事件
	if(event.keyCode == 13) { //判断如果按下的是回车键则执行下面的代码
	    send();
	}
});
</script>
</html>