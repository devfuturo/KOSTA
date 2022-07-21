import kr.tpc.BookDTO;

public class TPC10 {
	public static void main(String[] args) {
		//int, float, char, boolean -> PDT
		int a = 10;

		// 책 b; b에 책이라는 데이터를 넣고싶음
		// 책(BookDTO)이라는 자료형을 만들자 --> class를 이용하여
		// 객체 생성
		BookDTO b = new BookDTO(); // 객체 생성하는 메서드에서 객체를 생성함
		b.title = "자바"; // b.으로 객체 참조한다
		b.price = 17000;
		b.company = "한빛";
		b.page = 670;
		
		System.out.println(b.title);
		System.out.println(b.price);
		System.out.println(b.company);
		System.out.println(b.page);
	}

}
