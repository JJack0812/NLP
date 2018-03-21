package 第一套用servlet和java_server的解决方案;
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
		// 创建服务器端口
		try {
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
			
			String msg;
			while((msg = br.readLine())!=null){
				System.out.println("收到来自："+socket.toString()+"d字符串："+msg);
//				if(msg.startsWith("src:")){
//					System.out.println("收到来自浏览器：字符串："+msg);
//					//sendToApp(msg);
//				}else{
//					System.out.println("app处理后的字符串："+msg);
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public static void sendToUser(String msg)  {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
			bw.write(msg+"\n");
			bw.flush();
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
