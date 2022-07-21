package jcf;

import java.util.ArrayList;
import java.util.List;
/**
 * Element Generic
 * @author gram
 *
 */
public class GenericCollectionTest {

	public static void main(String[] args) {
		List list1;
		List<String> list2; // Element Generic
		
		//list의 의도는 String 타입으로 저장 1,2 두 가지 방법 가능
		list1 = new ArrayList(); 
		list2 = new ArrayList<>();
		
		//의도와는 다르게 실수로 String이 아닌 값이 저장될 수 있음. 컴파일은 성공 (21번째 줄)
		list1.add(new String("A"));
		list1.add(new Integer(1)); 
		list1.add(true);
		String s1 = (String) list1.get(0); // 정상실행. Object타입으로 return됨 -> 따라서 String타입으로 형변환 시켜야함
		String s2 = (String) list1.get(1); // ClassCastException 발생, 프로그램 종료
		String s3 = (String) list1.get(2); //  
		
		
		//String 타입의 값만 저장할 수 있다고 컴파일러에게 알리는 것이 Element Generic. 따라서 Integer타입 오류 발생됨
		list2.add(new String("ABC"));
		list2.add("DEF");
//		list2.add(new Integer(1)); //컴파일 에러. String자료만 저장 가넝 ~
		list2.add("GHIJ");
		list2.get(0); //return 타입이 String(get()에 마우스 올려서 확인) . 자동 casting
		String s4 = list2.get(0);
		String s5 = list2.get(1);
		String s6 = list2.get(2);
		
		
	}

}
