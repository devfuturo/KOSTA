package inheritance;
/**
 * 자바 최상위 클래스는 java.lang.Object클래스이다.
 * 대표적인 method - hashCode(), toString(), equals() 
 * @author gram
 *
 */
class A{ //class A extends Object{로 컴파일 시 바뀐다.
	int i;
	A(int i){
		this.i = i;
	}
	void print() {
		System.out.println("멤버변수 i값은 "+ i +"입니다");
	}
	public String toString() {
		return "멤버변수 i값은 "+ i +"입니다" ; //A class toString method overriding 재정의
	}
	@Override // 붙여주면 override라는걸 알려주고 오타 시 오타도 알려줌
	public int hashCode() { 
		return i;
	}
	/**
	 * 현재 객체의 i변수 값와 매개변수 객체의 i값이 같으면 true 반환, 그 외에는 false 반환
	 */ 
	@Override //컴파일러에게 밑에 선언된 메소드를 보고 오버라이드된 메소드인지 문법 체크?!
	public boolean equals(Object obj) {
		try {
			A a = (A)obj;
			if(this.i == a.i ) {
				return true;
			}
			
		} catch (ClassCastException e) {
			System.out.println("ClassCastException 오류입니다.");
		} return false;
//		if(obj instanceof A) {
//			A a = (A)obj;  //downCasting A로 강제형변환 
			// 자식객체가 부모타입으로 업캐스팅 된 후에만 다시 자식타입으로 다운캐스팅 가능
			// 부모객체가 직접 자식타입으로 다운캐스팅 불가능 -> ClassCastException 발생
//			if(this.i == a.i) { //object 객체에는 i라는 인스턴스변수가 없음
//			return true;
//			}
//		}
//		return false;
		
		
	}
}
public class ObjectTest {
	public static void toStringTest(Object o) {
		System.out.println(o.toString());
	}
	public static void equalsTest(Object obj1, Object obj2) {
		System.out.println(obj1.equals(obj2));
	}
	public static void main(String[] args) {
		A a1, a2;
		a1 = new A(3); //i를 3으로 초기화
		a2 = new A(3); //
		a1.print(); //멤버변수 i값은 3입니다.
		a2.print(); //멤버변수 i값은 3입니다.
		
		equalsTest(a1, a2); 

//		System.out.println(a1.toString()); //(A의)멤버변수 i값은 3입니다.
//		System.out.println(a2.toString()); //(A의)멤버변수 i값은 3입니다.
		toStringTest(a1); //upCasting - Object o타입에 upCasting
		toStringTest(a2);
		
		Object o1, o2, o3; //import안 해도 됨
		o1 = new Object();
		o2 = new Object(); //o1과 o2가 참조하는 값이 다르기 때문에 hashCode값이 다름
		o3 = o1; // o1과 o3의 hashCode값이 같음
		System.out.println(o1.hashCode());
		System.out.println(o2.hashCode());
		System.out.println(o3.hashCode());
		
//		System.out.println(o1.toString());
//		System.out.println(o2.toString());
//		System.out.println(o3.toString()); //지금 사용 중인 class의 이름을 얻어온다
		toStringTest(o1);
		toStringTest(o2);
		toStringTest(o3); //바로 위 3줄과 아래 결과 출력 같음
		
		System.out.println(o1.equals(o2)); //false - o1과 o2가 참조하는 값이 다름 false
		System.out.println(o1.equals(o3)); //true - o1과 o3이 참조하는 값이 같음 true
		System.out.println("-------------equals overriding test------------");
		
		equalsTest(a1, a2); // true 각 객체의(a1, a2의 i값) 인스턴스 변수(=멤버변수)값이 같으면 true, 다르면 false
		equalsTest(o1, o2); // false 각 객체가 같은 메모리면 true, 서로 다른 메모리이면 false 
		equalsTest(o1, o3); // true
		equalsTest(o1, a1); // false 각 객체의 자료형이 다르면 false
		equalsTest(a1, o1); // false 각 객체의 자료형이 다르면 false -> 23번째 줄 오류 why? o1이 A class 값 참조하고 있지 않기 때문에 A로 형변환 시켜주어도 컴파일까진 ok 실행에서 오류
	}

}
