package io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class KeyboardTest {

	   public static void main(String[] args) {   
		      InputStream is = System.in; //node stream
//		      try {
//		         int readValue = is.read(); //byte단위로 읽음
//		         System.out.println(readValue + "=" + (char)readValue);
//		      }catch (IOException e) {
//		         e.printStackTrace();
//		      }
//		      Reader r = new InputStreamReader(is); //filter stream
//		      try {
//		         int readValue = -1; 
//		         while((readValue = r.read()) != -1){ //char단위로 읽음
//		        	 //r.read한 값을 readValue에 받아오고 그 값이 -1(스트림의 끝을 만나는게)이 아니라면 while 구문실행  // char단위로 읽기   
//		            System.out.println(readValue + "=" + (char)readValue);  //13->첫번쨰 컬럼으로 이동 10->다음으로 이동(13,10은 엔터의값)
//		         }    //ctrl + z -> 스트림 끝 -1이 나와서 반복문 종료
//		      }catch (IOException e) {
//		         e.printStackTrace();
//		      }
		      Reader r = new InputStreamReader(is); //filter stream
		      try {
		         int readCnt = -1; 
		         char []cbuf = new char[3];
		         int off = 0;
		         int len = cbuf.length;
		         while((readCnt = r.read(cbuf, off, len)) != -1){ //char단위로 읽음
//		        	 for(char c : cbuf) { //buf 처음부터 끝까지 사용
//		        	 for(int i =0 ; i<readCnt ; i++) { // buf [0]부터 readCnt만큼만 사용
//		        		 System.out.println(cbuf[i]);
//		        	 }
//		        	 System.out.println("---");
		        	 System.out.println(new String(cbuf,0,readCnt));
		        	 // buf에다가 3개씩 문자값 저장해라
//		             System.out.println(readValue + "=" + (char)readValue);
		         }    
		      }catch (IOException e) {
		         e.printStackTrace();
		      }
		   }
}
