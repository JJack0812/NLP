package servlet;

import javax.websocket.server.ServerEndpoint;
//test git
//test git bash
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.swing.plaf.synth.SynthSpinnerUI;
import javax.websocket.*;

@ServerEndpoint("/websocket")
public class MySocket {
	//私有的保存客户端的id和会话session
	private Session mySession;
	private  long sessionIdByServer;
	
	//公有的，保存在服务器，保存app端的会话信息，最大id，
	static Session appSession;
	static long maxId=1;
	
	//客户端连接会话的map，通过给客户端赋值id，来查找相应客户端的session
	private static Map<Long, Session> sessionMap= new HashMap();
	
	@OnOpen
	public void onOpen(Session session) {
		//新连接加入，保存session，赋值id。
		this.mySession = session;
		this.sessionIdByServer = maxId;
		
		System.out.println("新增用户>>" + session.toString());
		System.out.println("新增用户的id>>"+this.sessionIdByServer);
		
		//放入服务器的会话集合
		sessionMap.put(this.sessionIdByServer, session);
		
		for(Long key : sessionMap.keySet()){
			System.out.println("现在的key有："+key);
		}
		
		//最大id自增
		maxId++;
		
	}

	@OnClose
	public void onClose() {

		System.out.println("=============有一连接关闭=============");
		//关闭后把服务器端会话集合中相应的会话移除
		sessionMap.remove(this.sessionIdByServer);
	}

	public void sendMessage(JSONObject json, Session session) {
		try {
			//向相应会话发送字符串
			session.getBasicRemote().sendText(json.toString());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	@OnMessage
	public void handleMessage(String message, Session session) {
		System.out.println("来自客户端" + session.toString() + "的消息:" + message);
		
		//收到string消息，先判断是不是app，防止掉线后无法识别
		if("iamtheapp".equals(message)){
			System.out.println("收到app端的消息");
			//是app的话，保存app会话
			appSession = session;
			return ;
		}
		try {
			//先打包为json，判断消息来源，
			JSONObject msgJson = new JSONObject(message);
			
			if ("user".equals(msgJson.get("src"))) {
				System.out.println("数据来自user");
				//如果来自用户浏览器，则这是一条新来的未处理的消息
				//给增加上id标识，修改消息来源并转发给app
				msgJson.put("userId", this.sessionIdByServer);
				
				sendToApp(msgJson);
			} else if ("app".equals(msgJson.get("src"))) {
				System.out.println("数据来自APP");
				//如果来自APP，则这是一条处理后的消息，需要回传给User
				
				//通过消息里的id，找到客户端的id值
				sendToUser(msgJson , msgJson.getLong("userId"));
			}
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		// sendMessage(message);
	}

	public void sendToApp(JSONObject json) {
		System.out.println("我要给app发信息>>>"+json.toString());
		sendMessage(json, appSession);
		System.out.println("给APP发送完毕");
	}

	public void sendToUser(JSONObject json , Long id) {
		//通过id在map集合中找session
		Session session = sessionMap.get(id);
		sendMessage(json, session);
	}
}
