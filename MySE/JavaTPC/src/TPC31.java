import kr.tpc.Inflearn;

public class TPC31 {
	public static void main(String[] args) {

		
//		Inflearn inf = new Inflearn(); // 생성자 private로 하면 객체 생성 불가
//		inf.tpc();
//		inf.java(); //노란 밑줄 -> access하는 방법이 불안전하다고 나옴.
		
		Inflearn.tpc(); // static 메서드에 접근하는 방법.
		Inflearn.java(); // 클래스명.메서드명();
		
		//Java API 생성자 private
//		System sys = new System(); 생성자 private임 따라서 객체 생성하여 사용할 수 없음
		System.out.println("출력"); // class이름으로 접근하여 찍어 사용
		
//		Math m = new Math(); 객체 생성하지 않고 바로 사용하게 하기 위해 static으로 만들어 둔 것
		int v = Math.max(10,20);
		System.out.println(v);
		
	
	}

}
