public class Employee {
	String no; // 사번
	String name; // 이름 
	int salary; // 급여
	Employee(){ //default Constructor가 X. 
		System.out.println("생성자가 호출됨!");
	}
	
	Employee(String no, String name){
//		this.no = no;
//		this.name = name;
//		this.salary= 1000; // 이 세 줄 하기와 같이 한 줄로 만들 수 O
//		System.out.println("this 생성자 호출은 생성자의 처음위치(첫줄)에서만 가능하다");
		this(no,name,1000); // 현재객체의 다른 생성자를 호출하라, 생성자에서만 사용 가능

	}
	Employee(String no, String name, int salary){
		this.no = no;
		this.name = name;
		this.salary = salary;
	}
	// compile타임에 소스코드에 생성자 없을 때 자동으로 만들어지는 것이 기본 생성자 
	// 생성자는 return타입 이름 자체가 없고, 반드시 class이름과 같아야함
	void Employee() {} // method. return타입이 반드시 형성되어 있어야함
	// this(); X -> this 생성자는 생성자 안에서만 호출 가능. 
	
}
