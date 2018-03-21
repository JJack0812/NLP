# -*- coding: UTF-8 -*-
import json
from ws4py.client.threadedclient import WebSocketClient

class DummyClient(WebSocketClient):
	def opened(self):
		print('****open***')
		self.send("iamtheapp")
		self.ping('======this is a ping=====')
	def closed(self, code, reason=None):
		print("------close-----", code, reason)
		self.close()

	def received_message(self, message):
		
		print('我受到了消息：'+str(message))
		
		jo = json.loads(str(message))
		
		print("消息来源>>"+jo['src'])
		print('具体内容'+jo['content'])
		
		jo['content']= str(jo['content']).upper()
		jo['src']='app'
		tempstr = json.dumps(jo)
		print('我准备发送'+tempstr)
		
		self.send(tempstr)

if __name__ == '__main__':
	try:
		ws = DummyClient('ws://192.168.120.1:8080/NLP/websocket',heartbeat_freq=1)
		ws.connect()
		ws.run_forever()
	
		
		
	except KeyboardInterrupt:
		ws.close()
	
