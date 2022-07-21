public class HR {
	public static void main(String[] args) {
		Employee e1;
		e1 = new Employee(); //Employee () ->  생성자 호출 
		// 객체 생성 시에만 생성자 (자동) 호출될 수 있음. 즉 new 다음에서만 
		// 객체 생성 후에 method 호출 언제든 가능
		e1.no = "2201"; e1.name ="한미래" ; e1.salary= 3000;
		
		Employee e2 = new Employee("1101","김자바",9000);
		// e2.no = "1101" ; e2.name = "김자바" ; e2.salary= 9000;
		
		Employee e3 = new Employee();
		e3.no = "1301" ; e3.name = "홍길동" ; e3.salary = 6000;
		
		Employee e4 = new Employee("1401","김"); //사원의 급여가 1000이 될 것을 예상 Employee파일 참조 
 
	}

}
