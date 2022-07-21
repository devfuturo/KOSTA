package inheritance;
class PP{
	int ppv;
	String sv;
}
class P extends PP{
	int pv;
}
class C extends P{
	int cv;
	int sv;
	void c() { //생성자x, 일반 method
		System.out.println(super.sv); //부모의 sv 출력됨 / null / 부모의 변수 지칭 시 super. 사용
		//super. 부모 영역을 지칭하는 예약어, 변수 이름 중복 시에도 사용 O, 
		System.out.println(super.pv); //0
	}
}
public class VariableDup {
	public static void main(String[] args) {
		C c = new C();
		System.out.println(c .sv); //sv 변수 PP,C 클래스에서 겹침 -> 부모보다 자식(나)이 우선되어 호출됨. "내"가 우선됨
//		System.out.println(c.super.sv); // 부모영역의 sv변수 지칭 시 해당과 같이 super불가능 
		c.c();
	}

}
