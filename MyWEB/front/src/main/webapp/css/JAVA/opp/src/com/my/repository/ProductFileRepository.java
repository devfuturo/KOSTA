package com.my.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.exception.FindException;

public class ProductFileRepository implements ProductRepository {
	//products.txt 파일이 없다면 현재 project폴더에 파일 생성 java.io.File 
	//파일 있다면 파일의 끝에 상품정보(상품번호:상품명:가격) 이어붙이기 -> append / true로 
	private String fileName = "products.txt"; // String타입 fileName 변수에 products.txt대입

	public void insert(Product product) throws AddException { //Product타입의 product를 매개변수로 가지는 insert메서드
		Scanner sc = new Scanner(System.in); //Scanner 타입의 sc변수에 Scanner객체 생성(System.in이라는 매개변수 가짐)
		//		System.in은 입력한 값을 바이트 단위로 읽는 것. 입력하기 위해 Scanner객체 생성
		//		Scanner sc = null;
		//File 생성
		File file = new File(fileName); // File타입의 file변수에 fileName매개변수로 가지는 File객체 생성하여 대입

		//FileWriter -> 문자 기반 스트림. 텍스트 데이터를 파일에 저장할 때 사용. 문자 단위로 저장
		FileWriter fw = null; //FileWriter 타입 fw변수에 null값 대입.

		//Product.java의 p를 쓴다 -> ProductFileRepository의 product
		//System.out.println(product.toProductsTxt());
		
		try {
			if(file.exists() == false) { //파일 존재여부 확인하는 메서드
				file.createNewFile(); //파일 생성하는 메서드
			}
			sc = new Scanner(file);
			if(!sc.hasNextLine()) {
				fw = new FileWriter(fileName,true);//append / FileWriter객체 생성하여 대입 
				fw.write(product.toProductsTxt()+"\r\n"); //write매서드(값 입력) product의 toProductsTxt메서드에 접근
			}else {
				//중복 제거
				// Product의 prodNo와 입력한prodNo값이 같으면 등록 실패..
				// 상품 이미 있다고 출력되어야함
				while(sc.hasNextLine()) {
					String line = sc.nextLine();
					String arr[] =line.split(":",3);
					String prodNo = arr[0];
					if(product.getProdNo().equals(prodNo)) {
						throw new AddException("상품이 이미 있습니다");
					}
				}
				fw = new FileWriter(fileName,true);//append / FileWriter객체 생성하여 대입 
				fw.write(product.toProductsTxt()+"\r\n");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if(fw != null) {
				try {
					//					sc.close();
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}		
	}

	// 상품전체조회
	// 시나리오1. private FileInputStream
	// 시나리오2. FileReader -> 한 줄 단위로 읽어오기에 적절하지 x. Scanner의 도움을 받아야함.  
	public List<Product> selectAll() throws FindException{
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(fileName)); //Scanner객체에 매개변수로 새로운 객체(File
			List<Product> all = new ArrayList<>(); //List는 직접 객체생성x Array도움 받아 객체 생성
			while(sc.hasNextLine()) { // 읽어올 다음 줄이 있을 경우 hasNextLine()가 true를 반환함
				//while((sc.hasNextLine() == true)와 동일함
				String line = sc.nextLine(); 
				String [] arr = line.split(":",3) ; // 3조각으로 자르기
				String prodNo = arr[0];
				String prodName = arr[1];
				int prodPrice = Integer.parseInt(arr[2]);
				Product p = new Product(prodNo, prodName, prodPrice);
				all.add(p); //리스트에 저장한다.
			}
			if(all.size() == 0) {
				throw new FindException("상품이 없습니다");
			}
			return all;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FindException("상품이 없습니다");
		}finally { //return되기 전에 , throw 되기 전에 finally 구문 실행됨
			if(sc != null) { //nullPointerException 피하기 위함
				sc.close(); //scanner close 파일 자원과의 연결닫기를 위해 close반드시 사용해야함
			} //e.printStackTrace -> finally 의 if구문 -> return all -> catch의 throw 순서로 실행됨 

		}
	}
	//상품 번호로 조회
	// 입력한 prodNo와 prodNo와 동일하면 상품 정보 출력
	// close로 닫아줘..
	public Product selectByProdNo(String prodNo) throws FindException{
		Scanner sc = null;
		try {
			sc = new Scanner (new FileReader(fileName));
			Product p = new Product();
			while(sc.hasNext()){
				String line = sc.nextLine();
				String[]arr = line.split(":",3);
				String skuNo = arr[0];
				if(skuNo.equals(prodNo)) {
					String productNo = arr[0];
					String skuName = arr[1];
					int skuPrice = Integer.parseInt(arr[2]);
					Product prodNoInfo = new Product(productNo, skuName, skuPrice) ;
					p = prodNoInfo;
					return p;
				}
			} 
			throw new FindException("상품번호에 해당하는 상품이 없숩니다");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FindException("상품이 없숩니다");
		}finally {
			if(sc != null) {
				sc.close();
			}
		}
	}


	public List<Product> selectByProdNoOrProdName(String word) throws FindException{
		Scanner sc = null;
		List <Product> resultList = new ArrayList();
		try {
			sc = new Scanner(new FileReader(fileName));
			Product p = new Product();
			while(sc.hasNext()) {
				String line = sc.nextLine();
				String []arr = line.split(":",3);
				String skuNo = arr[0];
				String skuName = arr[1];
				if(skuNo.contains(word) || skuName.contains(word)) {
					String prodNo = arr[0];
					String prodName = arr[1];
					int prodPrice = Integer.parseInt(arr[2]);
					Product inFo = new Product(prodNo, prodName, prodPrice);
					resultList.add(inFo); //리스트에 inFo저장
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(sc != null) {
				sc.close();
			}
		}
		return resultList;
	}
}
