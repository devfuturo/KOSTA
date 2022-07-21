package net.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

class TCPEchoThread extends Thread{
	private Socket s; // 멤버필드
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	private String clientIP = null;
	public TCPEchoThread(Socket s) throws IOException {
		this.s = s;
		//객체 생성 시 초기화. 객체 생성되는 시점에 초기화 작업하는 것이 (스레드 시작 전) 더 좋음
		InetAddress client = s.getInetAddress(); //커넥티드 된 곳(클라이언트)의 주소 반환.   
		clientIP = client.getHostAddress();
		dis = new DataInputStream(s.getInputStream());
		dos = new DataOutputStream(s.getOutputStream());
		System.out.println(clientIP + "가 접속했습니다");
	}
	public void run() { // run 메서드에 오버라이딩 / cpu에 의해 내쳐질 수 있는 영역
		try {
			// cpu를 점유하러 갔을 때 초기화. -> 언제 내쳐질 지 모르기 때문에 점유 시간 짧을 수도 o 따라서 미리 초기화 하는 것이 좋음 
//			InetAddress client = s.getInetAddress(); //커넥티드 된 곳(클라이언트)의 주소 반환.   
//			clientIP = client.getHostAddress();
//			dis = new DataInputStream(s.getInputStream());
//			dos = new DataOutputStream(s.getOutputStream());
			
			String receiveData = null;
			while(!(receiveData = dis.readUTF()).equals("quit")) {
//				System.out.println(clientIP + "가 보내준 내용:" + receiveData);
				dos.writeUTF(receiveData);
			} 
		} catch(SocketException e) { //클라이언트가 강제로 소켓을 끊은 경우, 연결이 끊긴 경우
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
}
public class TCPMultiEchoServerTest { //Client에게 받은 메세지를 다시 client쪽으로 전달 -> echo
	public static void main(String[] args) {
		int port = 9493;
		ServerSocket ss = null;
		try {
			// 1. port 열기
			ss = new ServerSocket(port);
			while(true) {
				Socket s =null;

				try {
					// 2. client 기다리기, 소켓생성
					s = ss.accept();  //s -> Socket타입의 변수

					// ▲ 여기까지 메인스레드가 할 일?

					// 새로운 스레드 생성하여 시작
					TCPEchoThread t = new TCPEchoThread(s); //객체생성 static보다는 생성자를 통해 s변수 전달할 수 있도록 하는게 더 좋음
					t.start(); // 스레드 시작
					//Thread용 객체 생성할 때 문제 생김 -> 스타트 불가. throws 로 예외처리 시 메인 처리 X
				}catch(IOException e) {

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