package 第一套用servlet和java_server的解决方案;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyConnector {
	Socket socket;
	BufferedReader br;
	BufferedWriter bw;
	
	public MyConnector(){
		try {
			socket = new Socket("localhost",8888);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
		} catch (UnknownHostException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public void send(String str){
		try {
			bw.write(str+"\n");
			bw.flush();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public String get(){
		String result=null;
		try {
			result = br.readLine();
			return result;
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return "error";
	}
	
}
