package kr.tpc;

public class Inflearn {
	private Inflearn() {
		
	}
	// 인스턴스 메서드
	public static void tpc() {
		System.out.println("TPC 강의 듣는중");
	}
	
	// 클래스 메서드(static 메서드)
	public static void java() {
		System.out.println("Java 강의 듣는 중");
	}
	
}

// private생성자 있을 지 모든 메서드에 static 붙어있어야함