package world;

import world.asia.Japan;
import world.asia.Korea;
import java.util.Date;
import java.util.Scanner;
//import java.sql.Date; //중복된 이름의 class를 import에 써주는 것 적절하지X
//import java.lang.String; //컴파일 시에 자동포함되기 때문에 다시 써주지 않아도 됨 ?!
public class CountryTest {
	public static void main(String[] args) {
//		world.asia.Korea k;
//		k = new world.asia.Korea();
		Korea k;
		k = new Korea();
		
		Japan j = new Japan();
		
		Date d1 = new Date();
		Scanner sc = new Scanner(System.in);
		
		java.sql.Date d2;
		
//		k.language = "일본어";
//		k.capital = "평양";
//		k.population = -1;
		// 접근제한자 
		// public -> 누구나 접근 
		// default(아무것도 안 붙이는 것)-> 같은 package 내에서만 접근 O
		// private -> 자기 class에서만 접근 O, 외부에서 접근x (like 주민번호)
		k.setPopulation(-1);
	}

}
