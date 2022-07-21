class Student{
	String name;
	char gender;
	int age;
	String addr;
	String major;
	
	Student() {
	}
	Student(String name, char gender){
		this(name, gender,0,null,null);
	}
	Student(String name, char gender, int age){
		this(name,gender,age,null,null);
	}
	Student(String name, char gender, int age, String addr){
		this(name,gender,age,addr,null);
	}
	Student(String name, char gender, int age, String addr, String major){
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.addr = addr;
		this.major = major;
	}
	void print() {
		System.out.println("이름은 "+name+" 성별은 "+gender+" 나이는 "+age+" 주소는 "+addr+" 전공은 "+major+"");
	}
}

public class StudentList {
	public static void main(String[] args) {
		Student s = new Student("한미래",'여',29,"분당구","체육");
		s.print();
		//우리반 학생들의
		//이름, 성별, 나이, 사는곳, 전공비전공 여부
		Student s1 = new Student("조건우",'남',31);
		s1.print();
		Student s2 = new Student();
		s2.print();
		Student s3 = new Student("황초연",'여',27,"서울시");
		s3.print();
		Student s4 = new Student("전승현",'남');
		s4.print();
		
		
	}
	
	

}
