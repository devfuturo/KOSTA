package practice;

public class CalculatorDemo {

	public static void sum(int left, int right) {
		System.out.println(left+right);
	}
	
	public static void avg(int left,int right) {
		System.out.println((left+right)/2);
	}
	
	
	public static void main(String[] args) {
		sum(10,20);
		sum(20,40);
		avg(10,20);
		avg(20,40);
	}

}
