class A{
	int iv;
	static int sv; // 객체들 사이의 공유값으로 사용할 수 O. -> 메모리 차지가 크다 -> 프로그램 종료까지 값이 유지되어서
	void m() {
		System.out.println("non-static method입니다 iv=" + this.iv);
		System.out.println("sv=" + this.sv);
		System.out.println("A.sv="+A.sv);
		sm(); //static method 호출가능
		 
	}
	static void sm() {
		System.out.println("static method입니다. sv="+ A.sv);
		//Static method 안에서는 this 사용하는 예약어 사용 불가
//		System.out.println("iv=" + this.iv); // this. 예약어 사용x = iv만 사용도 x
//		System.out.println("iv="+iv);
//		m(); //this.m()
	}
}
public class StaticTest {
	public static void main(String[] args) {
		A a1, a2; //지역변수 a1,a2는 자동 초기화 X
		a1 = new A();
		a2 = new A();
		a1.iv++;
		a1.sv++;
		
		System.out.println(a2.iv); // 0
		System.out.println(a2.sv); // 1
		System.out.println(A.sv); //a.sv보다 A.sv(class 이름으로 쓰는 것)를 권장 //1
		
		a1.m(); //m method 호출 
		//A.m(); -> 불가
		
		a1.sm(); 
		A.sm();
		
		
		
	}

}