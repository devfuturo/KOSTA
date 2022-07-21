package com.my.repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.exception.FindException;

public class ProductListRepositoryPrac {
	Scanner sc = new Scanner(System.in); //Scanner 타입의 sc변수에 매개변수 System.in이라는 Scanner 객체를 생성하여 대입
	private List <Product> products;
//	String msg = "한미래";
	public ProductListRepositoryPrac() {
		products = new ArrayList<>(); //10개의 index가 제공
		
	}
//	public ProductListRepository(int size) {
//		products = new ArrayList<>(size); //size 개수의 index가 제공
//	}
	public void prodAdd() { //return값이 없는 prodAdd method
//		msg.charAt(0);
//		System.out.println(msg.charAt(0));
//		int future = msg.indexOf("미");
//		System.out.println(future);
		
		System.out.print("상품번호를 입력하시오: "); //출력
		String prodNo = sc.nextLine(); //String타입의 prodNo라는 이름의 변수에 sc라는 이름의 Scannar객체의 nextLine()사용하겠다.
		
		System.out.print("상품명을 입력하시오: "); //출력
		String prodName = sc.nextLine(); //입력된 엔터값 이전 문자열 String타입의 prodName이라는 이름의 변수에 입력값(sc라는 이름의 Scannar객체의 nextLine()사용하겠다.)
		
		System.out.print("상품가격을 입력하시오: "); //출력
		int prodPrice = Integer.parseInt(sc.nextLine()); //int 타입의 prodPrice 
		
		try { //오류를 무시한다
			insert(new Product(prodNo, prodName, prodPrice));
		}catch(AddException ae) { //
		}
	}
	
	public void insert(Product product) throws AddException {
		int i = 0;
		for(; i<products.size() ; i++) { //products.size = 0 . 따라서 조건 false
//			Product p = products.get(i);
//			if(prodNo.equals(p.getProdNo())) {
//				break;
//			}
			if(product.equals(products.get(i))) {
				break;
			}
		}
		
		if(i == products.size()) {  // 여기에서 product add
			products.add(product); //products 리스트에 넣어준다. 참
			
		}else {
			throw new AddException("이미 존재하는 상품입니다.");
		}
		//product의 prodNo와
		//products 저장소 각 요소의 prodNo가 같으면 
		//sysout("이미 존재하는 상품입니다");
		//같지 않으면 products.add(product);
	}
	
	public List<Product> selectAll() throws FindException { //selectAll을 호출한 곳에 오류를 던진다(throws FindException)
		if(products.size() == 0) { //products의 길이가 0이면 상품이 없습니다라는 매개변수를 가진 FindException 객체를 throw한다.
			throw new FindException("상품이 없습니다"); 
		}else {
			for(int i =0; i<products.size() ;i++) { //i가 products의 길이보다 적을경우 
				System.out.println(products.get(i)); //i번째 참조값 출력
			}
		}
		return products; //products 반환
		
		

	}
	public Product selectByProdNo(String prodNo) throws FindException {
		
		for(int i = 0 ;i<products.size();i++) { // i가 0부터 products의 길이만큼까지 반복문 실행
			Product p = products.get(i); //Product 타입의 p 변수에 products의 i번째 참조값 대입
//		for(Product p : products) {
			if(p.getProdNo().equals(prodNo)) { //둘이 같으면 
				System.out.println(p);
				return p; //p를 반환(products의 i번째 참조값)
			}
		}
		System.out.println("상품이 없습니다.");
		throw new FindException("상품이 없습니다.");
	}
	
//	public Product selectByProdName(String prodName) throws FindException{
//		for(int i = 0 ; i<products.size();i++) {
//			Product p = products.get(i);
//			if(p.getProdName().equals(prodName)){
//				System.out.println(p);
//				return p;
//			}
//		}
//		System.out.println("상품이 없습니다.");
//		throw new FindException("상품이 없습니다.");
//	}
		
	public Product selectByProdNoOrName(String prodNoOrName) throws FindException{
		for(int i = 0 ; i<products.size();i++) {
			Product p = products.get(i);
//			String str1 = "D000" ; 
//			String str2 = "상품" ;
//			System.out.println(p.getProdName().contains(prodNoOrName));
			if(p.getProdName().contains(prodNoOrName)||(p.getProdNo().contains(prodNoOrName))){
				System.out.println(p);
//				return p; //i번째 참조값 리턴..
			}else {
				System.out.println("검색어를 포함한 상품이 없습니다.");
			}
		}
		throw new FindException("상품이 없습니다.");
	}
	public Product modify(String prodNameOrPrice) throws FindException {
		for(int i = 0 ; i<products.size() ;i++) {
			Product p = products.get(i);
			if(p.getProdNo().equals(prodNameOrPrice)) {
				System.out.println(p);
				System.out.print("상품명["+p.getProdName()+"]변경 안하려면 enter를 누르세요 ");
				String skuName = sc.nextLine();
				products.get(i).setProdName(skuName);
				System.out.println(products.get(i));
				
				System.out.print("상품가격["+p.getProdPrice()+"]변경 안하려면 enter를 누르세요 ");
				String scan = sc.nextLine();
//				int scann = Integer.parseInt(sc.nextLine());
					try{
						if(!"".equals(scan)) {
						int skuPrice = Integer.parseInt(scan);
//						int skuPrice = sc.nextInt();
						products.get(i).setProdPrice(skuPrice);
						System.out.println(products.get(i));
						
						}else {
							break;
						}		
					}catch (NumberFormatException e){
					} 
					throw new FindException("상품이 없습니다.");//System.out.println("상품이 없습니다.");
			}
		}throw new FindException("상품이 없습니다."); 
	}
}
	
//	public Product modify(String prodNoOrName) throws FindException {
//		for(int i = 0 ; i<products.size() ;i++) {
//			Product p = products.get(i);
//			if(p.getProdNo().equals(prodNoOrName)) {
//			System.out.println(p);
//			System.out.print("상품명["+p.getProdName()+"]변경 안하려면 enter를 누르세요 ");
//			String skuName = sc.nextLine();
//			products.get(i).setProdName(skuName);
//			System.out.println(products.get(i));
//			
//			System.out.print("상품가격["+p.getProdPrice()+"]변경 안하려면 enter를 누르세요 ");
//			String scan = sc.nextLine();
////			int scan = Integer.parseInt(sc.nextLine());
//			
//					if(scan != "") {
//					int skuPrice = Integer.parseInt(scan);
////					int skuPrice = sc.nextInt();
//					products.get(i).setProdPrice(skuPrice);
//					System.out.println(products.get(i));
//					
//					}else {
//						break;
//					}		
//					System.out.println("상품이 없습니다.");
//		}
//		}throw new FindException(); 
//}
//}

	
