package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FutureIOTest {

	public static void readNWriteforByte() {
	/*
	 * 입력
//	 * resource : future.txt 파일
	 * node stream : FileInputStream	
	 */
		String fileName = "future.txt";
		FileInputStream fis = null;
	
	/*
	 * 출력
	 * destination : futurecopy.txt
	 * node stream : FileOutputStream
	 */
		String copyFileName = "futurecopy.txt";
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream(fileName);
			fos = new FileOutputStream(copyFileName,true); //
			
			int something = -1;
			while((something = fis.read()) != -1 ) {
				fos.write(something);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("파일이 없습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}if(fos !=null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		readNWriteforByte();
	}

}
