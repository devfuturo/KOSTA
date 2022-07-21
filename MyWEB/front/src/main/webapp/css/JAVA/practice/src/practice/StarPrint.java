package practice;
public class StarPrint {
	public static void main(String[] args) {
		
//		*
//		**
//		***
//		****
//		*****
//		char star = '*';
//		for(int i = 1 ; i<=5 ; i++) {
//			for(int cnt=1;cnt<=i;cnt++) { 
//				
//				System.out.print(star);
//			}
//			System.out.println();
//		}
		
		char star = '*';
		for(int i = 5; i>=1 ; i--) {
			for(int j=1 ; j<=i ; j++) {
				
				System.out.print("*");
			}
			System.out.println();
		}
//		int cnt = 0;
//		for(int i =1;i<=100;i++) {
//			if(i%3==0) {
//				cnt++;
//			}
//		}
//		System.out.println(cnt);
			
	}

}
