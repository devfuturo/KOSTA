public class ArrayPrac {
	public static void main(String[] args) {

		//1  2  3  4
		//5  6  7  8
		//9 10 11 12
		
		int arr[][] = new int[3][4];
		int num=1;
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				arr[i][j] = num; // 0 + 0, 0 + 1, 0 + 2
				System.out.print(arr[i][j]+" ");
				num+=1;
			}
			System.out.println();	
		}
		
		
		
	}

}
