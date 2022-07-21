package exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionTest {
	public static void test(int i) {
		if(i!=0) { //예외를 발생하지 않도록 하는 방법
		int j = 99 % i;//i가 0인 경우 ArithmeticException 발생
		System.out.println("99를 " + i + "로 나눈 나머지값은 " + j);
		}else {
			System.out.println("0으로는 나눌 수 없습니다"); //예외를 피하는 방법
		}
	}
//	public static void test(Object obj) {
//		try {
//		String str = obj.toString();
//		System.out.println(str);
///		}catch(NullPointerException e) {
//		System.out.println("obj가 null입니다."); //예외를 피하지 않고 발생하면 try catch로 잡는 방법
//		}
//	}
	public static void test(Object obj) throws NullPointerException{ //강제 예외발생
		String str = obj.toString();
		System.out.println(str);
	}
	public static void main(String[] args) {
		test(new Object());
		try{
			test(null);
		}catch(NullPointerException e){
			System.out.println("인자가 null입니다.");
		}
	
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자를 입력하세요 : ");
		int i =1;
		FileInputStream fis = null;
		try{//이미 발생한 예외를 처리하는 것
			i = sc.nextInt(); //숫자 입력되지 않으면 InputMismatchException 발생
			fis = new FileInputStream("a.txt");
			int readValue = fis.read(); //파일 내용 1byte 읽기
			System.out.println("파일의 1바이트 내용 = " + readValue);
		}catch(FileNotFoundException e) { //FileNotFoundException ->import 필요
			System.out.println("a.txt파일이 없습니다");
		}catch(IOException e) {
			
		}catch(InputMismatchException e) {
			System.out.println("숫자로 입력하세요. 숫자가 아니어서 1로 대신합니다.");
			
		}finally {
			System.out.println("finally 블럭입니다");
			if(fis != null) { //try안에서 if를 쓰는 것보다 if안에서 try catch를 쓰는 것이 더 빠른 퍼포먼스
			try{
				fis.close(); // 파일자원과의 연결해제
			}catch(IOException e) {
			}	
			}
		}
		test(i);
		System.out.println("끝");
		}
	}


	
	// 예외처리 방법
	// 1) try { // 예외발생가능코드 
	//	  }catch(예외종류 변수명){
	//      //예외처리
	//    }
	//
	//    try{
	//			//예외발생가능코드
	// 	  catch(예외종류 변수명){
	//			//예외처리
	//	  }finally{
	//          //예외발생여부와 관계없이 실행
	//	  }
	//
	// 2) throws
