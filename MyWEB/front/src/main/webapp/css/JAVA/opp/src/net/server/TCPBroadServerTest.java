package net.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Vector;

class TCPBroadThread extends Thread{
	private Socket s; // 멤버필드
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	private String clientIP = null;
	private List<TCPBroadThread> list;
	public TCPBroadThread(Socket s, List<TCPBroadThread> list) throws IOException {
		this.s = s;
		this.list = list;
		//객체 생성 시 초기화. 객체 생성되는 시점에 초기화 작업하는 것이 (스레드 시작 전) 더 좋음
		InetAddress client = s.getInetAddress(); //커넥티드 된 곳(클라이언트)의 주소 반환.   
		clientIP = client.getHostAddress();
		dis = new DataInputStream(s.getInputStream());
		dos = new DataOutputStream(s.getOutputStream());
		broadcast (clientIP + "가 접속했습니다");
//		for(int i=0 ; i<list.size() ; i++) {
//			System.out.println(clientIP + "가 접속했습니다");
//			TCPBroadThread t = list.get(i);
//			t.dos.writeUTF(clientIP + "가 접속했습니다");
//		}
	}
	public void broadcast(String msg) {
		for(int i=0 ; i<list.size() ; i++) {
			TCPBroadThread t = list.get(i);
			try {
				t.dos.writeUTF(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void run() { // run 메서드에 오버라이딩 / CPU에 의해 내쳐질 수 있는 영역
		try {
			String receiveData = null;
			while(!(receiveData = dis.readUTF()).equals("quit")) {
//				System.out.println(clientIP + "가 보내준 내용:" + receiveData);
//				for(int i=0 ; i<list.size() ; i++) {
//					TCPBroadThread t = list.get(i);
//					t.dos.writeUTF(receiveData);
//				}
				broadcast(clientIP + ">" + receiveData);
			} 
		} catch(SocketException e) { //클라이언트가 강제로 소켓을 끊은 경우, 연결이 끊긴 경우
			System.out.println("소켓이 끊겼습니다. 클라이언트 장애인지 확인하세요");
		} catch(IOException e) { 
			// e.printStackTrace();
		} finally {
			list.remove(this); //연결이 끊기면 list에서 자기 객체를 삭제해야함
			if(s != null) {
				try {
					s.close(); 
				}catch(IOException e) {
				}
			}
//			System.out.print(clientIP == null?"클라이언트" : clientIP);
//			System.out.println("와 연결을 종료합니다");
//			for(int i=0 ; i<list.size() ; i++) {
//				TCPBroadThread t = list.get(i);
//				t.dos.writeUTF(clientIP + "와 연결을 종료합니다");
//			}
			broadcast(clientIP + "와 연결을 종료합니다");
		}
	}
}
public class TCPBroadServerTest { //Client에게 받은 메세지를 다시 client쪽으로 전달 -> echo
	public static void main(String[] args) {
		int port = 5433;
		ServerSocket ss = null;
		try {
			// 1. port 열기
			ss = new ServerSocket(port);
			// List 생성
			List<TCPBroadThread> list = new Vector<>(); // ArrayList는 공유객체로 쓰이지 않을 때 사용 가능 / 멀티스레드이면서 공유객체로 쓰이기 때문에 
			//vector는 삭제, 삽입시 다른 스레드가 접근하지 못함
			while(true) { 
				Socket s =null;

				try {
					// 2. client 기다리기, 소켓생성
					s = ss.accept();  //s -> Socket타입의 변수

					// ▲ 여기까지 메인스레드가 할 일?

					// 새로운 스레드 생성
					TCPBroadThread t = new TCPBroadThread(s, list); //객체생성 static보다는 생성자를 통해 s변수 전달할 수 있도록 하는게 더 좋음
					// 리스트 추가
					list.add(t);
					// 스레드 시작
					t.start();
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