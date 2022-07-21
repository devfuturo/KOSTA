package io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIOTest {
	public static void write(String fileName) {
		/*
		 * destination : fileName node stream : FileOutputStream filter stream :
		 * DataOutputStream / 1byte씩 쓰는 것이 아닌 데이터 타입별로 씀
		 */
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		try {
			fos = new FileOutputStream(fileName);
			dos = new DataOutputStream(fos);
			dos.writeInt(10);
			dos.writeBoolean(false);
			dos.writeDouble(1.2);
			dos.writeUTF("가나다");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dos != null) {// fos~dos 연결통로
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void read(String fileName) {
		FileInputStream fis = null;
		DataInputStream dis = null;
		try {
			fis = new FileInputStream(fileName);
			dis = new DataInputStream(fis);
			int intValue = dis.readInt();
			boolean booleanValue = dis.readBoolean();
			double doubleValue = dis.readDouble();
			String strValue = dis.readUTF();
			System.out.println(intValue + ":" + booleanValue + ":"+ doubleValue+":"+ strValue) ;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dis != null) {// fos~dos 연결통로
				try {
					dis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		String fileName = "a.dat";
		write(fileName);
		read(fileName);
	}
}
