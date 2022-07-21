package net.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServerTest {
	public static void main(String[] args) {
		int port = 9493;
		ServerSocket ss = null;
		//A - while(){
		try {
			//1. port 열기
			ss = new ServerSocket(port);
			while(true) {
				Socket s =null;
				DataInputStream dis = null;
				String clientIP = null;
				try {
					//B - while(){
					//2. client 기다리기, 소켓생성
					s = ss.accept();
					InetAddress client = s.getInetAddress(); //커넥티드 된 곳(클라이언트)의 주소 반환.   
					clientIP = client.getHostAddress();
					//C - while(){ 
					// A,B,C 중 다음 클라이언트 접속을 기다리기 좋은 곳은? 
					// A - 5432 또 열려고하여 BindException 발생 가능
					dis = new DataInputStream(s.getInputStream());
					String receiveData = null;
					while(!(receiveData = dis.readUTF()).equals("quit")) {
						System.out.println(clientIP + "가 보내준 내용:" + receiveData);
						//한번 client에서 보내면 한번만 받고 두번 보내면 두번 받아야함
					} 
					//receiveData = dis.readUTF();
					//System.out.println("클라이언트가 보내준 내용:"+ receiveData);
				} catch(SocketException e) { //클라이언트가 강제로 소켓을 끊은 경우, 연결이 끊긴 경우
					// 예외의 이유를 하나하나 다 써주지 않아도 됨
					System.out.println("소켓이 끊겼습니다. 클라이언트 장애인지 확인하세요");
				} catch(IOException e) { //readUTF(),s.getInputStream(),ss.accept()에서 발생할 수 있는 예외
					// e.printStackTrace();
				} finally {
					if(s != null) {
						try {
							s.close(); //close()호출하다가 예외발생 시 근접한 catch로 가게되어 맨 마지막의 IOException으로 감
						}catch(IOException e) {
						}
					}
					System.out.print(clientIP == null?"클라이언트" : clientIP);
					System.out.println("와 연결을 종료합니다");
				}
			}
		} catch (BindException e) {
			System.out.println(port+"포트가 이미 사용중입니다.");
		} catch (IOException e) { //SeverSocket생성할 때 생길 수 있는 예외
			e.printStackTrace(); 
		} finally {
			if (ss != null) {
				try {
					ss.close(); //서버 안전하게 종료
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}