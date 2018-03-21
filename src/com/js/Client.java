package com.js;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
/**
 * ģ��web�˵�websocket��ģ���õġ���ʱ���ɹ���
 * @author ����
 *
 */
public class Client {
	Socket socket;
	BufferedReader br ;
	BufferedWriter bw ;
	public static void main(String[] args) {
		Client client  =  new  Client();
		System.out.println("kaishi");
		try {
			client.socket = new Socket("192.168.120.1",8080);
			System.out.println(client.socket.toString());
			client.br= new BufferedReader(new InputStreamReader(client.socket.getInputStream(),"utf-8"));
			client.bw = new BufferedWriter(new OutputStreamWriter(client.socket.getOutputStream(), "utf-8"));
			
			StringBuilder sb = new StringBuilder();
			sb.append("GET ws://192.168.120.1:8080/Serv/websocket HTTP/1.1\r\n")
			.append("Host: 192.168.120.1:8080\r\n")
			.append("Connection: Upgrade\r\n")
			.append("Pragma: no-cache\r\n")
			.append("Cache-Control: no-cache\r\n")
			.append("Upgrade: websocket\r\n")
			.append("Origin: http://192.168.120.1:8080")
			.append("Sec-WebSocket-Version: 13\r\n")
			.append("User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36\r\n")
			.append("Accept-Encoding: gzip, deflate\r\n")
			.append("Accept-Language: zh-CN,zh;q=0.9\r\n")
			.append("Cookie: JSESSIONID=FF4AD4F3066ABA032C4FC5697476AC99v\r\n")
			.append("Sec-WebSocket-Key: LgalDxz97X7OYbE383AEHQ==\r\n")
			.append("Sec-WebSocket-Extensions: permessage-deflate; client_max_window_bits\r\n\r\n");
			
			client.bw.write(sb.toString()+"\n");
			
			String temp;
			while((temp = client.br.readLine()) != null){
				System.out.println("�յ�: "+temp);
			}
			
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			System.out.println("���쳣");
			e.printStackTrace();
		}
		
	}
}
