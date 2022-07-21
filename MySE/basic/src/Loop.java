public class Loop {
	public static void main(String[] args) {
        int score1 = 10;       
        int score2 = 20;
        int score3 = 30;
        int score4 = 10; //메인메서드 블록 안에서 변수 사용 가능 다른메서드에서 사용x
        int totalScore = score1+score2+score3+score4;
        System.out.println(totalScore);
        							
        int num;
//        num=1;
//        System.out.println(num);
//        
//        num=2;
//        System.out.println(num);
//        
//        num=3;
//        System.out.println(num);
//        
//        num=4;
//        System.out.println(num);
//        
//        num=5;
//        System.out.println(num);
        
        num=0;
        while(num<5) {
        	num++;
        	System.out.println(num);      	 
          }
//        	위의 구문은 for구문이 적절치 않고, 아래의 구문은 for구문 적절
//        	위의 내용은 num++의 순서가 조건식 다음이 아닌 전이기 때문?! while이 더 적절
        	
//          num=1;
//          while(num<=5) {
//        	 System.out.println(num);
//        	 num++;
//          }
//        for(num=0;num<5;num++) {
//    		System.out.println(num);
//        }
//        
        
          int sum = 0; //합
//          num=1;
//          sum+=num; //sum=sum+num;
//          
//          num=2;
//          sum+=num;
//          
//          num=3;
//          sum+=num;
//          
//          num=4;
//          sum+=num;
//          
//          num=5;
//          sum+=num;
            
//          	num=0;
//            while(num<100) {
//            	num++;
//            	sum+=num;
//            }
//             System.out.println(sum);
          
//          	num=1;
//          	System.out.println(num);
//          	
//          	num=3;
//          	System.out.println(num);
//          	
//          	num=5;
//          	System.out.println(num);
//          	
//          	num=7;
//          	System.out.println(num);
//          	
          //숫자 1부터 총 4개의 홀수들을 출력하시오.
//          	num=1;
//          	while(num<8) {
//          		System.out.println(num);
//          		num+=2;
//          	}
//          	int cnt=0; //출력횟수
//          	num=1;
//          	while(cnt<4) {
//          		System.out.println(num);
//          		num+=2;
//          		cnt++;
//          	}
          	
//          	int cntd = 1;
//          	num=1;
//          	while(cntd<=4) {
//          		System.out.println(num);
//          		num+=2;
//          		cntd++;
//          	}
          		
          		//피보나치 수열 : 1, 1, 2, 3, 5, 8, 13, 21, 34
//          		int bb=1; //이전이전수                		
//          		int b=0; // 이전수1/
//          		int current = bb+b;
//          		System.out.println(current); //1
//          		
//          		bb=b; //이전수를 이전이전수에 대입 bb=0
//          		b=current; //현재수를 이전수에 대입 b=1
//          		current =bb+b; //현재수1
//          		System.out.println(current); //1
//          		
//          		bb=b; //이전수를 이전이전수에 대입 bb=1과 같음
//          		b=current; //현재수를 이전수에 대입 b=1
//          		current =bb+b; //현재수2
//          		System.out.println(current); //2
//
//          		bb=b; //이전수를 이전이전수에 대입 bb=1과 같음
//          		b=current; //현재수를 이전수에 대입 b=2
//          		current =bb+b; //현재수3
//          		System.out.println(current); //3
//          		
          		
          		for(int cnt=1,bb=1,b=0;cnt<10;cnt++) {
          			int current=bb+b;
          			System.out.println(current);
          			bb=b;
          			b=current; 
          		}  
          		
          		//Math.random(); 함수는 0.0<=r<1.0 사이의 값을 가지는 double
          		//이를 int로 변환하여 0<=r<45의 값이 나오도록해라
//          		int rand = (int)(Math.random()*45); //0<= r <45
//          		System.out.println(rand);
//          		
//          		int rand = (int)(Math.random()*45); //0<= r <45
//          		System.out.println(rand);
//          		
//          		int rand = (int)(Math.random()*45); //0<= r <45
//          		System.out.println(rand);
//          		
//          		int rand = (int)(Math.random()*45); //0<= r <45
//          		System.out.println(rand);
//          		
//          		int rand = (int)(Math.random()*45); //0<= r <45
//          		System.out.println(rand);
//          		
//          		int rand = (int)(Math.random()*45); //0<= r <45
//          		System.out.println(rand);
          		
          		System.out.println("중복되지 않는 로또숫자[1~45]를 6개 출력하기");
          		//6개의 값이 한번에 저장되어야함 = 배열
//          		for(int cnt=0;cnt<6;cnt++) {
//          			int rand = (int)(Math.random()*45)+1; //0<= r <46
//          			System.out.println(rand);
//					}
          		int[]lotto=new int[6];
          		for(int cnt=0;cnt<lotto.length;cnt++) {
          			lotto[cnt] = (int)(Math.random()*45)+1; //0<= r <46
          			//System.out.println(rand);
          			for(int index=0;index<cnt;index++) {
          				if(lotto[index] == lotto[cnt]) { //중복
          					cnt--;
          					break;
          					
          				}
          			}
          			
				}
          		for(int i=0;i<lotto.length;i++) {
          			System.out.println(lotto[i]);
          		}
          		
          		
          		
          		
          		
          		
	}
}
