package kr.tpc;
public class OverLoad {
	// 동작(method)으로만 이루어진 객체
	public void hap(int a, int b) {
		System.out.println(a+b);
	}

	public void hap(float a, int b) {
		System.out.println(a+b);
	}
	
	public void hap(float a, float b) {
		System.out.println(a+b);
	}
	
	//위 세 개는 메서드 이름이 같지만 매개변수 타입이 다르기 때문에 컴파일 오류 X 오버로딩 된 것
	
}
