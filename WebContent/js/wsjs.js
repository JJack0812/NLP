var ws  = new WebSocket('ws://192.168.109.1:8080/NLP/websocket');
$("#txt1").keydown(function() {// 给输入框绑定按键事件
	if (event.keyCode == 13) {// 判断如果按下的是回车键则执行下面的代码
		send()
	}
});
function send() {
	
	//做一个发送的包
	var sendPkg ={
			"src":"user",
			"type":"string",
			"content": ""
	}
	//获取输入框的值
	var content = $('#txt1').val();
	sendPkg.content=content;
	 
	//json转字符串发送
	if(ws.readyState!=WebSocket.OPEN){
		//如果点击发送但是客户端已经掉线，重连
		alert("服务器已断开，请刷新重新连接")
		return 
	}else{
		ws.send(JSON.stringify(sendPkg))
	}
	
}
var msg;
ws.onmessage = function(e) {
	//收到消息
	msg = e.data;
	console.log('收到服务器消息：' + e.data)
	
	//解包，把相应的content展示
	var result = jQuery.parseJSON(e.data)
	
	console.log(result)
	if(result.type=="string")
		$('#txtHint').text(result.content)
	else
		$('#txtRes').text(result.content)
};
