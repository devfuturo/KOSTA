package kr.tpc;
public class BookDTO {
	public String title;
	public int price;
	public String company;
	public int page; 
	// 디폴트 생성자 메서드 생략되어있음
	public BookDTO() {
		// (눈에 보이지는 않지만) 객체를 생성하는 작업을 한다. (기계어 코드)
		// 생성자 메서드 안에서 기억공간에 객체 만드는 작업
		// 객체 만들어짐과 동시에 this라는 객체도 만들어짐
	}
}
