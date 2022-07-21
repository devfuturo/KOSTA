class Single{
	private static Single s = new Single(); //static private 자리 바뀌어도 OK
	private Single() {}
	public static Single getInstance(){
//		return new Single();
		return s;
	}
}
public class Singleton {
	public static void main(String[] args) {
//		Single s1 = new Single();
		Single s1 = Single.getInstance();
		Single s2 = Single.getInstance(); 
		System.out.println(s1 == s2); //false / getInstance할 때마다 새로운 객체 만듦. 따라서 false값이 나옴
		//객체 하나만 쓰도록 맨 윗줄 작성 - return s;하면 s1과 s2 true값 나옴
	}

}
