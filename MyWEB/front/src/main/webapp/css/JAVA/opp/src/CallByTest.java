public class CallByTest {
	public static int m(int i) { 
//		System.out.println(i); //10 -> main method의 i값 그대로 나옴
		i = 99; // m method의 i값이 99로 바뀐 것
		return i;
		
	}
	public static void m(int[]arr){
		arr[0] = 99;
	}
	public static void main (String[] args) {
		int i =10; // main method의 i값 10을 위 m method의 i에 그대로 넣는다. 10
		int j = m(i);//첫번째 m method의 i값 대입
		System.out.println(i); //10
		System.out.println(j); //99
	
		int [] arr = {10, 20, 30}; // stack영역에 arr 변수 있음 -> heap영역에 10,20,30이 들어있음
		m(arr); // 두번쨰 public의 m method arr값 대입
		System.out.println(arr[0]);//99
		
		// byte b = 7; 
		// m (b) ; -> 바이트 타입의 매개변수 갖는 method 없을 때 short타입 찾아보고 없으면 int타입 찾아봄 
		//-> 자동 형변환 되어 첫번째 m method에 대입됨
		// float f= 7.8F;
		// m(f); -> double 타입 찾아보고 없음 -> 컴파일 에러
		
		// 인자값을 매개변수에 전달하는 방법
		//1. CallByValue -> 자바에선 무조건 1번만 사용 -> 변수값이 그대로 복붙된다. 인자값이 복사되어 매개변수에 붙여넣기 된다.
		//2. CallByReference
	}
	
}
