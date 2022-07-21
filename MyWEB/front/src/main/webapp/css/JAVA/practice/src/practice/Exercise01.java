package practice;
public class Exercise01 {
	public static void main(String[] args) {
		
		// 변수 선언 위치가 잘못됨.
		while(true) {
			int a = (int) (Math.random()*6)+1 ; //1~6까지의 무작위 수
			int b = (int) (Math.random()*6)+1 ; //1~6까지의 무작위 수
			
			if(a+b !=5 ) {
			System.out.println("("+a+","+b+")");
			}else {
				System.out.println("("+a+","+b+")");
				break;
			}
		}
		
		
	}
		

}
