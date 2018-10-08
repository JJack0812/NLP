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
	//˽�еı���ͻ��˵�id�ͻỰsession
	private Session mySession;
	private  long sessionIdByServer;
	
	//���еģ������ڷ�����������app�˵ĻỰ��Ϣ�����id��
	static Session appSession;
	static long maxId=1;
	
	//�ͻ������ӻỰ��map��ͨ�����ͻ��˸�ֵid����������Ӧ�ͻ��˵�session
	private static Map<Long, Session> sessionMap= new HashMap();
	
	@OnOpen
	public void onOpen(Session session) {
		//�����Ӽ��룬����session����ֵid��
		this.mySession = session;
		this.sessionIdByServer = maxId;
		
		System.out.println("�����û�>>" + session.toString());
		System.out.println("�����û���id>>"+this.sessionIdByServer);
		
		//����������ĻỰ����
		sessionMap.put(this.sessionIdByServer, session);
		
		for(Long key : sessionMap.keySet()){
			System.out.println("���ڵ�key�У�"+key);
		}
		
		//���id����
		maxId++;
		
	}

	@OnClose
	public void onClose() {

		System.out.println("=============��һ���ӹر�=============");
		//�رպ�ѷ������˻Ự��������Ӧ�ĻỰ�Ƴ�
		sessionMap.remove(this.sessionIdByServer);
	}

	public void sendMessage(JSONObject json, Session session) {
		try {
			//����Ӧ�Ự�����ַ���
			session.getBasicRemote().sendText(json.toString());
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}

	}

	@OnMessage
	public void handleMessage(String message, Session session) {
		System.out.println("���Կͻ���" + session.toString() + "����Ϣ:" + message);
		
		//�յ�string��Ϣ�����ж��ǲ���app����ֹ���ߺ��޷�ʶ��
		if("iamtheapp".equals(message)){
			System.out.println("�յ�app�˵���Ϣ");
			//��app�Ļ�������app�Ự
			appSession = session;
			return ;
		}
		try {
			//�ȴ��Ϊjson���ж���Ϣ��Դ��
			JSONObject msgJson = new JSONObject(message);
			
			if ("user".equals(msgJson.get("src"))) {
				System.out.println("��������user");
				//��������û��������������һ��������δ�������Ϣ
				//��������id��ʶ���޸���Ϣ��Դ��ת����app
				msgJson.put("userId", this.sessionIdByServer);
				
				sendToApp(msgJson);
			} else if ("app".equals(msgJson.get("src"))) {
				System.out.println("��������APP");
				//�������APP��������һ����������Ϣ����Ҫ�ش���User
				
				//ͨ����Ϣ���id���ҵ��ͻ��˵�idֵ
				sendToUser(msgJson , msgJson.getLong("userId"));
			}
		} catch (JSONException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}

		// sendMessage(message);
	}

	public void sendToApp(JSONObject json) {
		System.out.println("��Ҫ��app����Ϣ>>>"+json.toString());
		sendMessage(json, appSession);
		System.out.println("��APP�������");
	}

	public void sendToUser(JSONObject json , Long id) {
		//ͨ��id��map��������session
		Session session = sessionMap.get(id);
		sendMessage(json, session);
	}
}
