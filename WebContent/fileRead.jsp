<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<title>Title</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/fileRead.js"></script>
</head>
<body>
	<div>
        <input type="file" id="files"  >
        <input type="button"  onclick="fileImport()" value="导入">
    </div>
    <p id="txtContent"></p>
   
</body>
</html>