package inheritance;

public class Child extends Parent{ //extends+class명 -> class로부터 확장된다 (상속 받는다.)
	private String c1; //고유 속성
	
	public Child(){}
	
	public Child(String c1){ 
		super("자식에서 설정한 부모변수",1000); //부모 생성자 중 내가 원하는 것을 골라 쓸 수 있음. 두개짜리 생성자 호출->this.c1에 대입됨
		this.c1=c1;
	}
	public void cm() { //기능
		System.out.println("자식의 기능입니다.");
		System.out.println("c1=" + c1);
		pm();
//		System.out.println("p1"+ p1); // 아무리 자식이어도 부모의 private field, method에는 접근할 수 X
//		상속은 가능하나 직접 접근은 불가능	(public으로 선언된 method를 통해서는 사용할 수 있음
	}
}
