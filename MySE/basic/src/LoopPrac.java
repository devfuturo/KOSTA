
public class LoopPrac {

	public static void main(String[] args) {
        // num은 0이다.
        int num = 0;
        int num1;
//        // num이 5되기 전까지 반복한다.
//        
////        while(num<5) {
////        	num++;
////        	System.out.println(num);      	 
////        }
////        
//        
//       
//        
        for(num=0, num1=1; num<10 && num1<15; num+=3) {
        	System.out.println(num);


        }
        // num = 0,1,2,3,4,5,6
        // num1 = 1,3,5,7,9,11,13
                         
      //피보나치 수열 : (0) 1, 1, 2, 3, 5, 8, 13, 21, 34
//		int bb=1; //이전이전수                		
//		int b=0; // 이전수1/
//		int current = bb+b; //1
//		System.out.println(current); //1
//		
//		bb=b; //이전수를 이전이전수에 대입 bb=0
//		b=current; //현재수를 이전수에 대입 b=1
//		current =bb+b; //현재수1
//		System.out.println(current); //1
//		
//		bb=b; //이전수를 이전이전수에 대입 bb=1과 같음
//		b=current; //현재수를 이전수에 대입 b=1
//		current =bb+b; //현재수2
//		System.out.println(current); //2
//
//		bb=b; //이전수를 이전이전수에 대입 bb=1과 같음
//		b=current; //현재수를 이전수에 대입 b=2
//		current =bb+b; //현재수3
//		System.out.println(current); //3

        int []nums = {1, 10, 3, 3, 1, 2, 7, 1, 6, 9};
		int []cnts; //출현횟수 cnts[0]는 숫자 1이 출현횟수가 저장될 공간
		       		//출현횟수 cnts[1]는 숫자 2이 출현횟수가 저장될 공간
					//출현횟수 cnts[2]는 숫자 3이 출현횟수가 저장될 공간
		cnts= new int[10];
		for(int index=0;index<nums.length;index++) { //.length는 10임 1~9까지 10개의 수가 num에 담겨져있음
			cnts[nums[index]-1]++;
		}
		
		for(int index=0;index<cnts.length;index++) {
			System.out.println((index+1)+"의 출현횟수:"+ cnts[index] +"회");
		} 
	}
}
