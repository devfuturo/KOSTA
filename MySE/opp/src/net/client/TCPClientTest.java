package net.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClientTest {

	public static void main(String[] args) {
		//1. 서버에 접속
		String serverIP = "127.0.0.1"; //네트워크 끊겨도 나혼자 테스트 가능한 IP 주소 : 127.0.0.1 / 접속할 서버의 IP값 입력하면됨
		int serverPORT = 9493; //port번호 ServerTest에서 5432로 지정해줌
		Socket s = null;
		DataOutputStream dos = null;
		Scanner sc = new Scanner(System.in);
		try {
			s = new Socket(serverIP,serverPORT);
			dos = new DataOutputStream(s.getOutputStream());
			//1. for 2. while 3.do-while

			String sendData ;//= sc.nextLine();

			do {
				sendData = sc.nextLine();
				dos.writeUTF(sendData);
			}while(!sendData.equals("quit"));


			//String sendData = "hello";
			//dos.writeUTF(sendData);
			//dos.writeUTF(sendData);
		} catch (UnknownHostException e) { //IP주소 오타 시 UnKnownHostException발생
			//				e.printStackTrace();
			System.out.println("IP가 잘못되었거나 호스트명이 잘못되었습니다");
		} catch (ConnectException e) { //정확한 Exception을 잡기 위해. 연결이 끊겼다.. 안됐다.
			System.out.println("서버와의 연결이 실패되었습니다."); // why? 정확히 알 수 없음. PORT정상적이지 않을 때, 다양한 이유로 ConnectException 발생 가능 
		} catch (SocketException e) {
			System.out.println("소켓이 끊겼습니다. 서버장애인지 확인하세요");
		} catch (IOException e) { //PORT번호 오타 시 실제 발생할 Exception은 ConnectException인데 상위 Exception인 IOException이 작동
			e.printStackTrace();
		}

	}

}
