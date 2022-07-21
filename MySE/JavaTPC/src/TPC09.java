public class TPC09 {
	public static void main(String[] args) {
		int a =56;
		int b = 40;
		//a+b?
		
//		int v = sum(a,b);  // sum() 는 non static이기 때문에 메모리에 객체 생성해야함
		TPC09 tpc = new TPC09(); //heap Area에 객체생성
		int v = tpc.sum(a,b);
		System.out.println(v);
	}
	
	public int sum(int a, int b) {
		int v = a+b;
		return v;
	}

}
