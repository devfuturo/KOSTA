import kr.tpc.MemberVO;

public class TPC15 {
	public static void main(String[] args) {

		MemberVO m = new MemberVO ("홍길동",40,"010-1111-1111","서울");
		//setter method - 필요X (생성자 메서드에 저장하여 값을 받았기 때문)
		
		System.out.println(m.toString());
		//to.String()이 없으면 sysout(m.getName());과 같이 하나하나 입력해서 봐야함
		
		MemberVO m1 = new MemberVO();
		// 매개변수 없기 때문에 m1이 가지고 있는 전체를 출력하라고 인지하여
		//m1 출력 시 m1.toString()과 동일한 결과값이 나옴
		
		m1.setName("나길동");
		m1.setAge(34);
		m1.setTel("010-1234-1234");
		m1.setAddr("경기도");
		
		System.out.print(m1.getName()+"\t");
		System.out.print(m1.getAge()+"\t");
		System.out.print(m1.getTel()+"\t");
		System.out.println(m1.getAddr()+"\t");
	
		System.out.println(m1); // m1이라고 해도 toString()로 출력됨
	}

}
