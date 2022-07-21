package jcf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
class A {
	int i;
	A(int i){
		this.i = i;
		
	}
	@Override // 우측 마우스 -> source -> generate hashCode & equals 오버라이딩 자동 가능
	public int hashCode() {
		return Objects.hash(i);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)  //현재객체 == 매개변수 객체
			return true;
		if (obj == null) 
			return false;
		if (getClass() != obj.getClass()) // 현재 객체의 클래스와 매개변수의 클래스가 같은지 확인
			return false;
		A other = (A) obj; // A타입으로 다운캐스팅 
		return this.i == other.i; //return this.hashCode() == other.hashCode();
	}
	@Override
	public String toString() {
		return "A [i=" + i + "]";
	}
	
}

public class CollectionTest {
	
	public static void test(Collection c) {
		c.add(new String ("one")); //add()의 매개변수 타입은 Object이다. String 이 Object로 업캐스팅
		c.add(new Integer (2));   // Integer가 Object로 업캐스팅
		c.add(3); //AutoBoxing : 컴파일 시 c.add(new Integer(3));으로 바뀜. Integer가 Object로 업캐스팅
		c.add(new Integer (2));   //중복데이터 하나만 저장
		c.add(new A(5)); // 
		c.add(new A(5)); // 위와 서로 다른 객체로 판단해서 객체 두개 저장됨
		
		
		System.out.println("자료수"+c.size()); 
		System.out.println(c); //c.toString()자동호출됨
	}
	public static void test(Map m) {
		m.put("one",		 "first");
		m.put(new Integer(2),"second");
		m.put(3,			 "third");
		m.put(2,		  	 "fourth"); //new Integer(2)로 AutoBoxing됨
		// map은 key 중복 시 덮어쓰기 되기 때문에 2-=second가 아닌 2= fourth가 출력됨
		m.put(new A(5),		 "fi");
		m.put(new A(5),		 "si");
		
		System.out.println("자료수"+ m.size());
		System.out.println(m); // m.toString()자동 호출됨
	}
	public static void main(String[] args) {
//		List list = new ArrayList();
//		test(list); 
		test(new ArrayList()); //위 두 줄과 같음 list -> index O, 중복저장, 순차저장 가능
		test(new HashSet()); //set -> index X, 중복저장, 순차저장 불가능. 로또숫자와 같이 중복저장 불가능할 시 사용
//		test(new HashMap()); //컴파일 오류. why? list, set은 collection의 하위 계층 구조이기 때문에 자동 업캐스팅 가능. map은 collection과 아무 관계가 없기 때문에 강제형변환, 자동형변환 불가능 
		test(new HashMap());
	}

}
