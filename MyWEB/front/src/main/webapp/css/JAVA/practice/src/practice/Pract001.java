package practice;


public class Pract001 {
	class MySuper {
		int i;
		MySuper(int i) {
		this.i = i * i;
		}
		}
		class MySub extends MySuper {
		public static void main(String[] args) {
		MySub b = new MySub(2);
		}
		MySub(int i) {
		super(i);
		System.out.println(i);
		}
		}
	
	

}
