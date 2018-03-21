String.prototype.endWith=function(str){
    	if(str==null||str==""||this.length==0||str.length>this.length)
    	  return false;
    	if(this.substring(this.length-str.length)==str)
    	  return true;
    	else
    	  return false;
    	return true;
    	}
        //点击导入按钮,使files触发点击事件,然后完成读取文件的操作
        $("#fileImport").click(function () {
            $("#files").click();
        })
function fileImport() {
	
    //获取读取我文件的File对象
    var selectedFile = document.getElementById('files').files[0];
    var name = selectedFile.name;//读取选中文件的文件名
    if (!name.toString().endWith(".txt")){
    	alert("请上传UTF-8编码的文本")
    	return 
    }
    var size = selectedFile.size;//读取选中文件的大小
    console.log("文件名:"+name+"大小:"+size);

    var reader = new FileReader();//这是核心,读取操作就是由它完成.
    
    if(/windows|win32/i.test(navigator.userAgent)){
    	reader.readAsText(selectedFile,'GBK');//读取文件的内容,也可以读取文件的URL
    }else{
    	reader.readAsText(selectedFile,'UTF-8');//读取文件的内容,也可以读取文件的URL
    }
    
    
    
    reader.onload = function () {
        //当读取完成后回调这个函数,然后此时文件的内容存储到了result中,直接操作即可
        console.log(this.result);
        $("#txtContent").text(this.result)
        $("#txtSrc").text(this.result)
        
        var sendPkg ={
			"src":"user",
			"type":"file",
			"content": ""
        }
	//获取输入框的值
		var content = $('#txtSrc').val();
		sendPkg.content=content;
	 
	//json转字符串发送
		ws.send(JSON.stringify(sendPkg))
        
    }
}

$(document).on('ready', function() {
    $("#files").fileinput({
        showUpload: false,
        maxFileCount: 10,
        mainClass: "input-group-lg"
    });
});

        
  