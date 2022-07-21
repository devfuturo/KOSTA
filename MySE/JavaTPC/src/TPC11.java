import kr.tpc.BookVO;

public class TPC11 {
	public static void main(String[] args) {
		//책 1권을 저장하기 위해 [객체를 생성]하시오.
		BookVO b = new BookVO();
		b.title = "정처기";
		b.price = 16000;
		b.company = "수제비";
		b.page = 700;
		
		System.out.print(b.title+"\t");
		System.out.print(b.price+"\t");
		System.out.print(b.company+"\t");
		System.out.println(b.page+"\t");

		BookVO b1 = new BookVO();
		b1.title = "오라클";
		b1.price = 16000;
		b1.company = "이지스퍼블리싱";
		b1.page = 500;
		
		System.out.print(b1.title+"\t");
		System.out.print(b1.price+"\t");
		System.out.print(b1.company+"\t");
		System.out.println(b1.page+"\t");
	}
}
