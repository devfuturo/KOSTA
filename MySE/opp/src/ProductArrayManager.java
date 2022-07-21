//import com.my.dto.Product;
//import com.my.repository.ProductRepository;
//
///**
// * --상품--
// * 상품번호  F0001  	 G0001  B0001
// * 상품명	  얼그레이스콘	 머그1   아메리카노
// * 상품가격 1000		 2000	1000
// * 상품상세정보 "~~"
// * 상품제조일자 22/04/20
// * 			 220420
// * 			 2022/04/20
// * 			 Date
// * @author gram
// *
// */
//import com.my.dto.Product;
//import com.my.repository.ProductRepository;
//public class ProductManager {
//	public static void main(String[] args) {
//		ProductRepository repository; 
//		repository = new ProductRepository();// 최대 5개까지 저장할 수 있는 저장소를 생성한다.
////		repository = new ProductRepository(10); // 최대 10개까지 저장할 수 있는 저장소를 생성한다.
////		Product p1 = new Product("B0001","아메리카노",1000,new Date());
////		repository.insert(p1);
////		repository.insert(new Product("B0002","아이스아메리카노",1500,new Date()));
////		repository.insert(new Product("B0003","아이스라떼",1500,new Date()));
////		repository.insert(new Product("B0004","카푸치노",1500,new Date()));
////		repository.insert(new Product("B0005","자몽허니블랙티",1500,new Date()));
////		System.out.println("상품종류개수 : " + repository.cnt);
////		repository.insert(new Product("B0006","카페모카",1500,new Date())); //index 5개까지 사용할 수 있기 때문에 6번째는 오류남
//
//		for(int i =1 ; i<=2;i++) {
//			try {
//			repository.insert(new Product("D000"+i, "상품"+i,1500,new Date()));
//		}catch(ArrayIndexOutOfBoundsException e) {
//			System.out.println("저장소가 꽉찼습니다.");
//		}
//		
//		Product[] products = repository.selectAll() ; //저장된 상품들을 반환한다. 상품 정보를 모두 저장소에서 검색 해 오는 method (저장 되어있는 상품만)
//		
//		for(int i=0;i<products.length;i++) {
//			products[i].print();
//			// 상품번호:F0001, 상품명:상품1, 가격:1500, 상세정보:null, 제조일자:~~가 출력됨
//			// 상품번호:F0002, 상품명:상품2, 가격:1500, 상세정보:null, 제조일자:~~가 출력됨
//			// 상품번호:F0003, 상품명:상품3, 가격:1500, 상세정보:null, 제조일자:~~가 출력됨
//			// 상품번호:F0004, 상품명:상품4, 가격:1500, 상세정보:null, 제조일자:~~가 출력됨
//			// 상품번호:F0005, 상품명:상품5, 가격:1500, 상세정보:null, 제조일자:~~가 출력됨
//		}
//	}
//	//여러 class가 한 파일 내에 저장될 때 main method를 가진 class가 public이어야하고 class 이름이 java파일 이름이어야함
//
//	}
//	}
import java.util.Date;

import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.repository.ProductArrayRepository;

/**
 * 실행 클래스
 * @author jasonmilian
 *
 */
public class ProductArrayManager {
	
	public static void main(String[] args) {
		ProductArrayRepository repository;
		repository =  new ProductArrayRepository();
//		repository =  new ProductRepository(10); //최대 5개까지 저장할수있는 저장소를생성한다.
//		repository =  new ProductRepository(10); //최대 10개까지 저장
//		Product p1 = new Product("B0001", "아메리카노", 10000);
//		repository.insert(p1);
//		repository.insert(new Product("B0002", "아바라", 1500, new Date()));
//		repository.insert(new Product("B0003", "에이드", 2500, new Date()));
//		repository.insert(new Product("B0004", "에스프레소", 2300, new Date()));
//		repository.insert(new Product("B0005", "프라푸치노", 3500, new Date()));
//		repository.insert(new Product("B0005", "프라푸치노", 3500, new Date()));
		
		/**
		 * 반복문을 돌면서 객체를 생성한것을 insert()를 통해 저장소에 삽입함.
		 */
		try {
		for (int i = 1; i < 10; i++ ) {
//			try {
				repository.insert(new Product("D000"+i, "상품"+i,1500,new Date()));
//			}catch(ArrayIndexOutOfBoundsException e) {
//				System.out.println("저장소가 꽉찼습니다.");
//			}
		}
//			repository.insert(new Product("B000"+i, "음료" + i, 3500, new Date()));
		}catch(AddException e) {
			System.out.println(e.getMessage());
		}
		
//		A a = new A();
//		B b = new B();
//		C c = new C();
//		try {
//			a.~~();
//			b.~~();
//			c.~~();
//		}catch(AException ae) {
//			~~~
//		}catch(BException be) {
//			~~~
//		}catch(CException ce) {
//		}
		
		/**
		 * 저장소의 selectAll()를 사용하여 반환된 값을 product배열타입의 products에값을 할당함.
		 */
		Product[] products = repository.selectAll();
		for(int i = 0; i < products.length; i++) {
			products[i].print();
		}

		}
		
	}
	

