package net.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPEchoClientTest {

	public static void main(String[] args) {
		//1. 서버에 접속
		String serverIP = "127.0.0.1"; //네트워크 끊겨도 나혼자 테스트 가능한 IP 주소 : 127.0.0.1 / 접속할 서버의 IP값 입력하면됨
		int serverPORT = 9493; //port번호 ServerTest에서 5432로 지정해줌
		Socket s = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		Scanner sc = new Scanner(System.in);
		try {
			s = new Socket(serverIP,serverPORT);
			dos = new DataOutputStream(s.getOutputStream());
			dis = new DataInputStream(s.getInputStream());
			//1. for 2. while 3.do-while

			String sendData ;//= sc.nextLine();

			do {
				sendData = sc.nextLine();
				System.out.println(sendData);
				dos.writeUTF(sendData);
				String receiveData = dis.readUTF();
				System.out.println(receiveData);
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
		} catch(EOFException e) {
		} catch (IOException e) { //PORT번호 오타 시 실제 발생할 Exception은 ConnectException인데 상위 Exception인 IOException이 작동
			e.printStackTrace();
		} finally {
			if(s!=null) { //socket이나 sc 둘 중 하나만 닫으면 됨
				try {
					s.close(); //Socket close
				} catch (IOException e) {
					//					e.printStackTrace();
				}
			}
		}

	}

}
