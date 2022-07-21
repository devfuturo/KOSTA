import kr.tpc.MemberVO;

public class TPC14 {

	public static void main(String[] args) {
		
		MemberVO m = new MemberVO();
		
		m.setName("홍길동");
		m.setAge(50);
		m.setTel("010-1111-1111");
		m.setAddr("서울");
		
		System.out.print(m.getName()+"\t");
		System.out.print(m.getAge()+"\t");
		System.out.print(m.getTel()+"\t");
		System.out.println(m.getAddr()+"\t");
		
		/*
		m.name = "홍길동";
		m.age = 1000;
		m.tel = "010-1111-1111";
		m.addr = "서울";
		
		System.out.print(m.name+"\t");
		System.out.print(m.age+"\t");
		System.out.print(m.tel+"\t");
		System.out.println(m.addr+"\t");
		*/
//		상태 정보가 public -> private로 변경되었기 때문에 m.으로 호출하는 것 불가
		
	}

}
