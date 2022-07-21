package inheritance;
public class InheritanceTest {
	public static void main(String[] args) {
		Parent p = new Parent("부모변수",1000); //default construct가 없기 때문에 new Parent(); 오류
//		p.p1 = "부모변수";
//		p.money = 1000;
		p.pm();
		
		
		Child c = new Child();//자식타입의 child 호출될 때 부모쪽인 Parent의 생성자가 먼저 호출됨. 부모의 매개변수 없는 생성자가 먼저 호출됨
		//new Child() 호출될 때 Parent 쪽의 매개변수 없는 생성자가 먼저 호출된다. 따라서 parent쪽 매개변수 없는 생성자가 없을 시 child 오류
		//따라서 Parent쪽 매개변수 없는 생성자 먼저 작성 해 주어야함
//		c.c1 = "자식변수";
//		System.out.println(c.p1); //p1의 초기값인 null값 출력
//		System.out.println(c.money); //money의 초기값인 0 출력
		System.out.println("--------------");
		c.cm();
		System.out.println("--------------");
		c.pm();
		
		c= new Child("자식변수");
		System.out.println("--------------");
		c.cm();
	}

}
