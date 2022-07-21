
public class Array1 {
	public static void main(String[] args) {
		java.util.Scanner sc = new java.util.Scanner(System.in);
		int []score; //배열선언  score -> 메인 메서드 안에서 선언했기 떄문에 score 변수 같은 이름으로 재선언 불가능
		score = new int[7]; //배열생성
		score[0] = 10;
 		score[1] = 3;
		score[2] = 3;
		score[3] = 5;
		score[4] = 2;
		score[5] = 1;
		score[6] = 9;
//		System.out.println(score);
		
////		System.out.print("점수를 입력하세요");
////		score[0] = sc.nextInt();
////		
////		System.out.print("점수를 입력하세요");
////		score[1] = sc.nextInt();
////		
////		System.out.print("점수를 입력하세요");
////		score[2] = sc.nextInt();
////		
////		System.out.print("점수를 입력하세요");
////		score[3] = sc.nextInt();
////		
////		System.out.print("점수를 입력하세요");
////		score[4] = sc.nextInt();
////		
////		System.out.print("점수를 입력하세요");
////		score[5] = sc.nextInt();
////		
////		System.out.print("점수를 입력하세요");
////		score[6] = sc.nextInt();
//		
		for(int index=0;index<score.length;index++) {
			System.out.println(score[index]);
//			score[index] = sc.nextInt();
		} //for 내부에서 선언된 index이기 때문에 for안에서만 index변수 사용 가능 따라서 밑에 for 내부에 index 또 사용 가능. 첫 for 바깥 } 밖 sop(index)시 오류
//		
//		int totalScore; //총점
////		totalScore = score[0] + score[1]+score[2]+score[3];
//		totalScore = 0;
////		totalScore += score[0];
////		totalScore += score[1];
////		totalScore += score[2];
////		totalScore += score[3];
//				
//		System.out.println(totalScore);
//
//		for(int index=0; index<score.length;index++) { //index<7 대신 score.length 사용하는 것이 더 좋음 
//			totalScore+=score[index];
//		}
//		System.out.println(totalScore);
//		System.out.println((float)totalScore/score.length); //평균값은 정확한 실수값이 되도록 출력 4 대신 4.0 혹은 4F
//		
//		
		
		//12지 동물들
//		String[]zodiacSign; //선언
//		zodiacSign = new String[12]; //생성
//		zodiacSign[0] = "원숭이"; // 대입
//		zodiacSign[1] = "닭";
//		zodiacSign[2] = "개";
//		zodiacSign[3] = "돼지";
//		//:
//		zodiacSign[11] = "양"; 
		
		// 배열 초기화 - 선언, 생성, 대입 한번에 할 수 있는 것 / 선언 = {} 중괄호 열고닫고
		String []zodiacSign = {"원숭이","닭","개","돼지","쥐","소","호랑이","토끼","용","뱀","말","양"};
		
		//선언 따로 해 놓고 생성+대입만 묶어서
//		String []zodiacSign; //선언
//		zodiacSign = new String[] {"원숭이","닭","개","돼지","쥐","소","호랑이","토끼","용","뱀","말","양"}; //생성+대입
		
		int year = 2022;
		//int ganzi = year%12;
//		System.out.println(zodiacSign[year%12]); //condition에서 했던 if, switch보다 배열 사용 시 훨씬 코딩 양이 줄어듦
		//int로 ganzi 선언했으니 year%12 대신 ganzi 이 경우 사용해도 OK
		
//		int year=2022;
//		int ganzi =year%zodiacSign.length;
//		System.out.println(zodiacSign[ganzi]);
		
		//num배열의 값은 1부터 10사이의 숫자들이다.
		//아래와 같이 숫자들의 출현횟수를 출력하시오.
		//1의 출현횟수 : 3회
		//2의 출현횟수 : 1회
		//3의 출현횟수 : 2회
		//4의 출현횟수 : 0회
		//5의 출현횟수 : 0회
		//6의 출현횟수 : 1회
		//7의 출현횟수 : 1회
		//8의 출현횟수 : 0회
		//9의 출현횟수 : 1회
		
//		int []nums = {1, 10, 3, 3, 1, 2, 7, 1, 6, 9};
//		int []cnts; //출현횟수 cnts[0]는 숫자 1이 출현횟수가 저장될 공간
//		       		//출현횟수 cnts[1]는 숫자 2이 출현횟수가 저장될 공간
//					//출현횟수 cnts[2]는 숫자 3이 출현횟수가 저장될 공간
//		cnts= new int[10];
//		for(int index=0;index<nums.length;index++) { //.length는 10임 1~9까지 10개의 수가 num에 담겨져있음
//			cnts[nums[index]-1]++;
//		}
//		
//		for(int index=0;index<cnts.length;index++) {
//			System.out.println((index+1)+"의 출현횟수:"+ cnts[index] +"회");
//		}
//		
		int []nums1 = {9 , 3 , 1 , 4 , 5};
		//nums1 배열의 최소값을 찾아 출력하시오
		//1,2번째 값 비교하고 그 중 작은 값 기억하고 2,3 비교해서 그 중 작은 값과 이전에 기억해 둔 값과 비교해서 최소값 찾기
		int min = nums1[0];
		for(int i=1;i<nums1.length;i++){
			if(min>nums1[i]){
				min=nums1[i];
			}
		}
//		System.out.println("최소값은?"+ min);
		//최대값도 구할 수 있고, 값들 정렬 가능(순서대로 오른,내림차순)
		
			//do while 구문, break, continue공부
			//로또숫자 6개 출력 배열활용해서. 중복되지 않은 숫자 6개 출력
			//밑의 최대값 다시 구하기
		
		
		//연습
		int[]scores = {83,90,87};
//		System.out.println("scores[0]"+scores[0]);
//		System.out.println("scores[1]"+scores[1]);
//		System.out.println("scores[2]"+scores[2]);
		
		int sum=0;
		for(int qq=0;qq<3;qq++){
			sum+=scores[qq];
		}
//		System.out.println("총합:"+sum);
//		System.out.println("평균:"+sum/3);
		}
		
}



