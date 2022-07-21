package kr.tpc;
// 책(Object) -> 제목, 가격, 출판사, 페이지 수...
public class BookVO {
	public String title;
	public int price;
	public String company;
	public int page;
	// 4개의 멤버 변수를 가지고 있는 class BookVO 생성
	// 디폴트 생성자 메서드(생략)
	public BookVO() {
		//초기화 작업
		this.title = "자바";
		this.price = 14000;
		this.company = "한빛";
		this.page = 780;
	}
	// 생성자 메서드의 중복정의(overloading)
	public BookVO(String title, int price, String company, int page) {
		this.title = title;
		this.price = price;
		this.company =company;
		this.page = page;
		// 매개변수의 개수 or 데이터 타입이 달라야함
	}
}
