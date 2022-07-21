public class TPC08 {
	public static void main(String[] args) {
		int a = 30;
		int b = 50;
		int v = add(a,b); //static method를 호출
		System.out.println(v);
	}
	
	public static int add(int x, int y) {
		int sum = x+y;
		return sum;
	}

}
