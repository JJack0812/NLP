package ��һ����servlet��java_server�Ľ������;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	static String msg;
//	static ArrayList<Socket> clientList=new ArrayList<>();
	static Socket appSocket;
	static BufferedReader appBR;
	static BufferedWriter appBW;
	
	public static void main(String[] args) throws IOException {
		//���Է������˿�
		InetAddress add=InetAddress.getLocalHost();
		System.out.println(add);
		//�����������˿�
		ServerSocket serverSocket = new ServerSocket(8888);
		//�����ͻ����б�
//		ArrayList<Socket> clientList=new ArrayList();
		appSocket = serverSocket.accept();
		
		appBR = new BufferedReader(new InputStreamReader(appSocket.getInputStream(),"utf-8"));
		appBW = new BufferedWriter(new OutputStreamWriter(appSocket.getOutputStream(), "utf-8"));
		
		//��һ�����ӣ���Ϊapp������
		System.out.println("�׸�app�����������ѽ�����"+appSocket.toString());
		
		ServerThread app=new ServerThread();
		app.setS(appSocket);
		app.start();
		while(true){
			ServerThread t=new ServerThread();
			Socket s=serverSocket.accept();
			System.out.println("�����û���"+s.getRemoteSocketAddress());
			t.setS(s);
			t.start();
		}
		
		
	}

}
class AppThread extends Thread{
	@Override
	public void run() {
		
	}
	
}
