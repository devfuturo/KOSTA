package inheritance;

abstract class Shape{
	private double area; //protected 별로 좋은 방법x private, public 중 1개 사용
	double getArea() {
		return this.area;
	}
	void print() {
		System.out.println("이것은 도형입니다.");
	}
//	void calcArea() {} //오버라이딩 - 자식쪽에도 동일 method 선언되어있음
	abstract void calcArea();
}
class Circle extends Shape{
	private int radius;
	
	Circle(){}
	
	Circle(int radius){
		this.radius = radius;
	}
	void print() {
		super.print();
		System.out.println("반지름이 " +radius+"인 원입니다"); //Rectangle과 같은 method이긴 하지만 출력 내용이 다름. 따라서 공통 기능으로 뺄 수 X
	}
	void calcArea() {
//		this.area = (Math.PI)*(this.radius)*(this.radius); //원주율에 관련된 파이값 Math.PI -> double타입
		System.out.println(getArea());
	}
	
	//method overriding 재정의 부모쪽의 method와 자식쪽의 method가 같음 (void print)
	// 부모쪽의 메소드를 자식 클래스에서 재정의 한 것 따라서 오버라이드 된 영역이 출력됨
}

class Rectangle extends Shape{
	private int width;
	private int height;
	
	Rectangle(){}
	
	Rectangle(int width, int height){
		this.width = width;
		this.height = height;
		
	}
	Rectangle(int width){
		this(width, width);
	}
	
	void print() {
		super.print(); //자식쪽에서 super. 으로 부모의 method 출력
		System.out.println("가로"+width+" 세로"+height+"인 사각형입니다.");
	}
	void calcArea() {
//		this.area= width*height;
		System.out.println(getArea());
	}
	
}
class Triangle extends Shape{
	int width;
	int height;
	Triangle(int width, int height){
		this.width = width;
		this.height = height;
		
	}
	void calcArea() {
//		area = width * height /2;
		System.out.println(getArea());
	}
}

public class ShapeTest {

//	public static void printArea(Circle c) { // method화 , over road 하고 출력도 가능
//		System.out.println(c.getArea()); // 면적값 반환하여 출력	
//	}
//	public static void printArea(Rectangle r) {
//		System.out.println(r.getArea()); // 면적값 반환하여 출력
//	}
	public static void printArea(Shape s) {
		s.print();
		System.out.println(s.getArea());
	}
	public static void main(String[] args) {
		Shape[] arr = new Shape[4];
		arr[0] = new Circle(5);
		arr[1] = new Rectangle(3,4);
		arr[2] = new Rectangle(5);
		arr[3] = new Triangle(5,3);
		for(int i=0;i<arr.length;i++) {
//			arr[i].print();			//Shape클래스에는 print 메소드가 없기에 아무기능없는 Shape에 print만들어줌. Circle,Rectangle에는 Print메소드가 있음. 
			//메소드오버라이딩 > 부모클래스의 메소드가 자식클래스들의 메소드를 똑같이 생성하는것(이클립스 초록세모표시) 리턴타입도 같아야함 
			//자식클래스에서 print메소드를 재정의함 
			//Shape클래스와 Circle클래스 오버라이딩 중 중복된 메서드가 있으면 자식클래스(Circle클래스)의 메서드를 우선시함. 
			//때문에 print 가 동작하는 것, 이 코드 한줄로 다양한 양식의 클래스를 호출 가능(Shape, Circle, Rectangle) ----> 다형성  -->오버라이딩하는이유
			arr[i].calcArea();
			printArea(arr[i]);    //잘 생성됨
}
		for(int i=0;i<arr.length;i++) {
//			arr[i].print(); 
			arr[i].calcArea();
			printArea(arr[i]);
		}
//		Circle c = new Circle(5); //반지름이 5인 원객체 생성
//		c. print(); //반지름이 5인 원입니다 출력
//		c.calcArea(); //원 면적 계산
//		printArea(c); //자식 타입 인자-> 부모타입 매개변수로 자동 형변환 되는 것 Upcasting / 자식타입 변수 -> 부모타입의 변수 자동되는 것도 Upcasting
//		printArea("hello"); 컴파일 오류. String 타입 부모 class와 전혀 관계없음
//		
//		Rectangle r= new Rectangle(3,4); // 가로3,세로4인 사각형 객체생성
//		r.print(); // 가로 3, 세로 4인 사각형입니다 출력
//		r.calcArea(); //사각형 면적 계산
//		printArea(r);
//		
//		Rectangle r1 = new Rectangle(5); //가로 5, 세로 5인 사각형 객체 생성
//		r1.print(); // 가로 5 세로 5인 사각형입니다 출력
//		r1.calcArea(); //사각형 면적 계산
//		printArea(r1);
	}

		
}
