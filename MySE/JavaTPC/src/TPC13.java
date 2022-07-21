import kr.tpc.BookDTO;

public class TPC13 {
	public static void main(String[] args) {
		// 책 -> class(BookDTO)로 책의 구조 설계
		// class -> 하나로 담아야 하기 때문에 바구니로 설계
		String title = "스프링";
		int price = 25000;
		String company = "제이펍";
		int page = 890;
		// 1. 배열로 묶어서 -> 데이터 타입이 다 다르기 때문에 배열로 묶을 수 X
		// 2. 직접 설계 -> 따라서 직접 설계해야함 class로 . BookDTO 혹은 BookVO
		
		BookDTO dto; //dto (Object : 객체/ 객체가 아직 구별되지 않은 시점) 첵을 담는 변수일 뿐
		dto = new BookDTO(title, price, company, page); // dto (Instance: 인스턴스) dto라는 객체가 생성됨 
		// BookDT  -> 실체(인스턴스)
		// 객체의 구체적인 이름 -> 인스턴스(변수)
		// dto에 title, price, company, page 한번에 바구니로 묶어버림
	
		bookPrint(dto);
	}
	
	public static void bookPrint(BookDTO dto) {
		System.out.println(dto); // 번지값만 출력됨
		System.out.println(dto.title);
		System.out.println(dto.price);
		System.out.println(dto.company);
		System.out.println(dto.page);
	}
}
