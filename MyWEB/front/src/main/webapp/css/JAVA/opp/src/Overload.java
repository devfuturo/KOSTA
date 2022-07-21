import java.lang.reflect.Method;

public class Overload {
	public static void plus(int a, int b, int c) {
		int result = a + b + c;
		System.out.println(result);
	}
	public static void plus(int a, int b) {
		int result = a + b;
		System.out.println(result);
	}
	public static void plus(double a, double b) {
		double result = a + b;
		System.out.println(result);
	}
	public static void main(String[] args) {
		System.out.println("ABC"); //ABC
		System.out.println(1+2); //3
		System.out.println(1>2); //false 
		//println -> 대표적인 method overloading 어떤값도 출력하려는 목적(기능 같음) 가지고 있음
		// ABC의 String타입, 1+2의 int, boolean타입 모두 출력 O
		// method의 기능(println. console창에 출력하겠다)은 같은데 
		// 매개변수 형태를 다르게 하는 것 -> method overloading
		// 같은 기능하는 것 plus1,2,3과같이 이름 다 다르게 하지 않고 plus로 method이름
		// 같게 하는 것이 좋음. method 같은 이름으로 사용해도 O
		// 한 class 내에서 method 이름 같은 것 over load로 부름

		// over load 조건
//		1. Method 이름 같아야함
//		2. 매개변수 유형 다르거나 갯수 다르거나 순서 달라야함 / ex. plus(int a, float b) 
//		3. ex. plus(int c, int d)와 같은 경우 -> over load가 아닌 중.복.
//		같은 목적의 기능을 만들기 위해 over load를 하는 것
		plus(1,2,3); // 6
		plus(1,2); //3
		plus(1.2,3.4); //4.6


	}

}
