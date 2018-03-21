package ��һ����servlet��java_server�Ľ������;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;

public class ServerThread extends Thread {

	static Socket socket = null;
	Socket messageSocket=null;
	
	public ServerThread() {
		
	}
	public Socket getS() {
		return socket;
	}
	public void setS(Socket s) {
		this.socket = s;
	}


	BufferedReader br = null;
	InputStreamReader isr = null;
	BufferedWriter bw = null;

	public void run() {
		// �����������˿�
		try {
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
			
			String msg;
			while((msg = br.readLine())!=null){
				System.out.println("�յ����ԣ�"+socket.toString()+"d�ַ�����"+msg);
//				if(msg.startsWith("src:")){
//					System.out.println("�յ�������������ַ�����"+msg);
//					//sendToApp(msg);
//				}else{
//					System.out.println("app�������ַ�����"+msg);
//					//sendToUser(msg);
//				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

	}
	public static void sendToApp(String msg)  {
		BufferedWriter bw= Server.appBW;
		try {
			bw.write(msg);
			bw.flush();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	public static void sendToUser(String msg)  {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
			bw.write(msg+"\n");
			bw.flush();
		} catch (UnsupportedEncodingException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}

}
