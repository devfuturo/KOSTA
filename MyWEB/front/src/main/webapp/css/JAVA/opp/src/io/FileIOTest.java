package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIOTest {
	public static void readNWriteByChar() {
		/*
		 * resource : a.txt
		 * node stream : FileReader
		 */
		String fileName = "a.txt";
		FileReader fr = null;
		
		/*
		 * destination : acopy.txt
		 * node stream : FileWriter
		 */
		String copyFileName = "acopy1.txt";
		FileWriter fw = null;
		try {
			fr = new FileReader(fileName);
			fw = new FileWriter(copyFileName);
//			fw = new FileWriter(copyFileName,true); //true -> 같은 text 뒤에 계속 생성
			int readValue = -1;
			while((readValue = fr.read()) != -1) {
				fw.write(readValue);
				//fw.flush();
			}
			//fw.flush(); // 즉각 흘려보내라..
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("파일이 없습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fr != null) { //NullPointerExcepion 피하는 방법
				try {
					fr.close();
				} catch (IOException e) {
				}
			}
			if(fw !=null) { //NullPointerExcepion 피하는 방법
				try {
					fw.close();//flush() + 연결끊기
				} catch (IOException e) {
					e.printStackTrace();
				} 
				
			}
		}
	}
	public static void readNWriteByByte() {
	/* 입력
	 * resource : a.txt파일
	 * node stream : FileInputStream
	 */
	String fileName = "a.txt"; 			//절대경로
//			          "..\\..\\a.txt"; // 상대경로 / ..상위경로 -> 현재경로 기준 상위경로 
//	상위의 상위 경로 ..\\..\\ 
//	System.out.println(fileName);
	FileInputStream fis = null;
	/* 출력
	 * destination : acopy.txt
	 * node stream : FileOutputStream
	 */
	String copyFileName = "acopy.txt";
	FileOutputStream fos = null ;
	try {
		fis = new FileInputStream(fileName); //파일 자원과의 연결
		fos = new FileOutputStream(copyFileName); //파일 목적지와의 연결. 파일이 만들어짐
//		fos = new FileOutputStream(copyFileName, true);
		
		//파일 내용을 읽어 다른 곳에 쓰기하는 것
		int readValue = - 1;
		while((readValue = fis.read()) != -1) { //반복 계속 진행하고싶을 시 while문 안에 try~catch
			//read()가 더이상 흡수할 것이 없을 때 -1값을 내보냄
			//System.out.println((char)readValue);
			fos.write(readValue); 
		}
	} catch (FileNotFoundException e) { 
//		e.printStackTrace();
		System.out.println("a.txt파일이 없습니다");
	} catch (IOException e) { //catch절만 추가하여 기존의 try절과 연결
		e.printStackTrace();
	// FileNotFoundException이 IOException보다 하위 exception이기 때문에 자식 exception이 더 먼저 catch되어야함
	} 

}

	public static void main(String[] args) {
//		readNWriteByByte();
		readNWriteByChar();
	}
}
