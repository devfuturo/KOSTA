import java.util.Scanner;

import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.repository.ProductListRepositoryPrac;

public class ProductManagerConsolePrac{ //PMC라는 public class 생성
	private Scanner sc = new Scanner(System.in); //Scanner타입의 sc변수에 Scanner객체 생성 (System.in이라는 매개변수 대입)
	ProductListRepositoryPrac prodRepo = new ProductListRepositoryPrac(); // PLR 타입의 prodRepo 변수에 PLR 객체 생성 대입
//	Product = new Product();
	
	public void add() { // return값이 없는 add() -> 상품을 등록하기 위해
		System.out.println(">>상품등록<<");
//		prodRepo.prodAdd(); //prodRepo 이름의 PLR 객체의 prodAdd() 사용한다. PLR에서 호출해 옴 // repository 재사용성을 위해 
		
		System.out.print("상품번호를 입력하시오: "); //출력
		String prodNo = sc.nextLine(); //String타입의 prodNo라는 이름의 변수에 sc라는 이름의 Scannar객체의 nextLine()사용하겠다.
		
		System.out.print("상품명을 입력하시오: "); //출력
		String prodName = sc.nextLine(); //입력된 엔터값 이전 문자열 String타입의 prodName이라는 이름의 변수에 입력값(sc라는 이름의 Scannar객체의 nextLine()사용하겠다.)
		
		System.out.print("상품가격을 입력하시오: "); //출력
		int prodPrice = Integer.parseInt(sc.nextLine()); //int 타입의 prodPrice 
		
		Product p = new Product(prodNo, prodName, prodPrice);
		try {
			prodRepo.insert(p);
		} catch (AddException e) {
			e.printStackTrace();
//			System.out.println(e.getMessage()); 위와 같은 내용
		}
	}
	
	// ProductListRepository selectAll 호출
	public void findAll() { //return값이 없는 findAll() -> 등록한 전체 상품을 조회하기 위함
		System.out.println(">>상품 전체조회<<");
		try { //prodRepo.prodAdd()는 컴파일 에러 없지만 prodRepo.selectAll은 컴파일 에러가 뜸 -> PLR의 selectAll에 throws FindException있기 때문
			prodRepo.selectAll(); //ProductListRepository의 selectAll() 호출 -> 전체 상품을 조회할 수 있기 때문에
		} catch (FindException e) { 
			e.printStackTrace(); //오류가 날 때 오류 확인 가능하도록 나오게하는 메서드
		}
	}
	
// 	상품 번호로 조회-3	
	public void findByProdNo() { //상품번호 입력 시 상품 정보 조회되어야함 . 입력 x 시 상품이 없습니다 출력
		System.out.println(">>상품번호로 조회<<");
		System.out.print("상품번호를 입력하시오: ");
			try {
				prodRepo.selectByProdNo(sc.nextLine());
			} catch (FindException e) {
				System.out.println(e.getMessage());
			}
		}
	
// 	검색어로 조회-4
	public void findByProdNoOrName() {
		System.out.println(">>상품번호나 이름으로 조회<<");
		System.out.print("검색어를 입력하세요: ");
		
		try {
			prodRepo.selectByProdNoOrName(sc.nextLine());
		}catch (FindException e) {
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
//	상품수정-5
	private void modify() {
		System.out.println(">>상품 수정<<");
		System.out.print("상품번호를 입력하시오: ");
		try {
			prodRepo.modify(sc.nextLine());
		} catch (FindException e) {
//			e.printStackTrace();
		}
	}
	
	
	//ProductListRepository도 완성하세요
	public static void main(String[] args) {
		ProductManagerConsole managerConsole = new ProductManagerConsole(); //PMC 타입의 magagerConsole변수에 매개변수 없는 PMC 객체 생성하여 대입
		Scanner sc = new Scanner(System.in); // Scanner 타입의 sc변수에 Scanner객체 생성하여 System.in이라는 매개변수를 대입
		
		while(true) { //조건이 true이면 출력
			System.out.println("작업구분 :상품등록-1, 상품전체조회-2, 상품번호로 조회-3, 검색어로 조회-4, 상품수정-5, 종료-9");

		
			String opt = sc.nextLine(); //String 타입의 opt라는 변수에 sc라는 이름의 Scannar 객체의 nextLine() 사용하게따!
			switch(opt) {
			case "1": 
				managerConsole.add(); //managerConsole이라는 이름의 ProductManagerConsole 객체의 add라는 method 실행?!
				break; //멈추겠다
			case "2":
				managerConsole.findAll();
				break;
			case "3":
				managerConsole.findByProdNo();
				break;
			case "4":
				managerConsole.findByProdNoOrName();
				break;
			case "5":
				managerConsole.modify();
				break;
			case "9":
				System.exit(0);
			}
		}
	}
}


		
	


