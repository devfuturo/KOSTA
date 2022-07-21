package practice;

public class StarPrintPrac {

	public static void main(String[] args) {

		char star = '*';
		for(int i=1 ; i<=5 ; i++) {
			for(int j = 0; j<i ; j++) {
				System.out.print(star);
			}
			System.out.println();
		}
	}

	
///	*
//	**
//	***
//	****
//	*****

}
