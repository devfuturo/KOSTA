import java.util.Calendar;
import java.util.Map;
public class Function {
	//public static void method6(zodiac함수) {} //절대 불가능 함수를 인자로 전달하는 과정은 java,c,c++에선 불가능 
	//함수를 인자로 지정하지 못하는 것은 기능을 또다른 기능으로 X
	public static void method5 (Map map) {}
	public static void method4(int []arr) {}
	public static void method3() {
		System.out.println("파일이 만들어집니다");
		
	}
	public static String method2() {
		Calendar c = Calendar.getInstance(); //패키지
		switch(c.get(Calendar.AM_PM)){
		case Calendar.AM:
			return "오전";
		default:
			return "오후"; //함수를 빠져 나간다. break를 굳이 쓰지 않아도 됨
		//case에 return이 있다면 else에 해당하는 default가 있어야함
			//java.util.Canlder c-> Calendar c로 바꾸기 위해선 맨 위에 import java.util.Calendar;
		}
		 
	}
	public static void method1(int i) { //method1이 홀수인지 짝수인지 구분하는 것.
		System.out.println(i%2==0?"짝수":"홀수");
	}
	public static String zodiac(int year) { //함수명 앞에 함수의 반환타입을 적어주면 됨, 함수인지 알리기 위해 (), 할 일은 블럭 내부에 적어줌
		//int year -> 변수선언 - int year은 매개변수(parameter)
		String []zodiacSign; //선언
		//생성+대입
		zodiacSign = new String[] {"원숭이", "닭", "개", "돼지", "쥐", "소", "호랑이", "토끼", "용", "뱀", "말", "양" };
			
		int ganzi = year%zodiacSign.length;
//		System.out.println(zodiacSign[ganzi]);
		return zodiacSign[ganzi];
	}
	
	public static void main(String[] args) {
		method3();
		
		String ampm = method2();
		System.out.println(ampm);
		method1(5); // method 내부에서 출력됨. 출력은 method1이 출력하고 main에게 돌려주는 값x
		
		
		java.util.Scanner sc = new java.util.Scanner(System.in);
		System.out.print("출생년도를 입력하세요 : ");
		int year = sc.nextInt();
		String result;
		result = zodiac(year); //함수 호출 / ()에 2022 입력 ->인자값(argument value) -> 결과: 호랑이
		System.out.println(result);
	}
}
