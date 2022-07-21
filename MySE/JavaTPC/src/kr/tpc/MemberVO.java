package kr.tpc;
public class MemberVO {
	private String name;
	private int age;
	private String tel;
	private String addr;
	
	public MemberVO() {}// 디폴트 생성자 - 만들어두는 것이 좋음
	// 디폴트 생성자 없을 시 TPC14 의 MemberVO m = new MemberVO(); 오류남
	
	public MemberVO (String name, int age, String tel, String addr) {
		this.name = name;
		this.age = age;
		this.tel = tel;
		this.addr = addr;
	}
	public MemberVO(String name) {
		this.name = name;
	}
	
	// setter 메서드, getter 메서드 
	public void setName(String name) { // void -> 리턴값이 없음
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() { // 모든 값 하나로 묶어 하나의 문자열로 출력 도와주는 메서드
		return "MemberVO [name=" + name + ", age=" + age + ", tel=" + tel + ", addr=" + addr + "]";
	}
	
	
}

// 잘 설계된 class
// 1. 모든 멤버는 private로 정보 은닉한다
// 2. 디폴트 생성자 명시적으로 만들어 줌
// 3. 디폴트 생성자 중복 정의하여 적절히 사용할 수 있도록 함
// 4. 
