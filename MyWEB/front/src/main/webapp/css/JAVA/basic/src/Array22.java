public class Array22 {
	public static void main(String[] args) {
		int [][]arr = new int[3][4];
		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {	
				arr[i][j] = j+1;
			}
		}
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {	
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
		System.out.println("------");
		
		//1  2  3  4
		//5  6  7  8
		//9 10 11 12
		int arr2[][] = new int[3][4];
		int num2 = 1;
		for (int i=0; i<arr2.length; i++) {
			for (int j=0; j<arr2[0].length; j++, num2++) {
				arr2[i][j] = num2;
			}
		}
		for (int i=0; i<arr2.length; i++) {
			for (int j=0; j<arr2[0].length; j++) {
				System.out.print(arr2[i][j]+" "); 
			}
			System.out.println();
		}
		System.out.println("------");	
		
		//1  4  7  10
		//2  5  8  11 
		//3  6  9  12
		int arr3[][] = new int[3][4];
		int num3 = 1;
		for (int col=0; col<arr3[0].length; col++) {
			for (int row=0; row<arr3.length; row++, num3++) {
				arr3[row][col] = num3;
			}
		}
		for (int row=0; row<arr3.length; row++) {
			for (int col=0; col<arr3[0].length; col++) {
				System.out.print(arr3[row][col]+" "); 
			}
			System.out.println();
		}
		System.out.println("------");	
		
		//A B C D
		//E F G H
		//I J K L
		char arr4[][] = new char[3][4];
		char alpha = 'A';
		for (int i=0; i<arr4.length; i++) {
			for (int j=0; j<arr4[0].length; j++, alpha++) {
				arr4[i][j] = alpha;
			}
		}
		for (int i=0; i<arr4.length; i++) {
			for (int j=0; j<arr4[0].length; j++) {
				System.out.print(arr4[i][j]+" "); 
			}
		}
		
		
		//여기서부터
//		int arr[][] = new int[3][4];
//		arr[0][0] = 1;
//		arr[0][1] = 2;
//		arr[0][2] = 3;
//		arr[0][3] = 4;
//		
//		arr[1][0] = 1;
//		arr[1][1] = 2;
//		arr[1][2] = 3;
//		arr[1][3] = 4;
//		
//		arr[2][0] = 1;
//		arr[2][1] = 2;
//		arr[2][2] = 3;
//		arr[2][3] = 4;
		
//		for(int i=0;i<arr.length;i++) {
//			for(int j=1;j<arr[i].length;j++) {
//			arr[i][j]=j=+1;
//			}
//		}
//		for(int i=0;i<arr.length;i++) {
//			for(int j=1;j<arr[i].length;j++) {
//			System.out.print(arr[i][j]);
//			}
//		}
//		System.out.println();
		//1  2  3  4
		//5  6  7  8
		//9 10 11 12
		
//		int arr[][] = new int[3][4];
//		int num=0;
//		for(int i=0;i<arr[i].length;i++) {
//			for(int j=0;j<arr[i].length;j++) {
//				num++;
//				arr[i][j]=num;
//			System.out.println(arr[i][j]+"");
//			}
//			System.out.println();
//		}
		int arr[][]=new int[3][4];
		int num=0;
		for(int i=0;i<arr[i].length;i++) {
			for(int j=0;j<arr[j].length;j++) {
				num++;
				System.out.println(arr[i][j]+" ");
			}
			System.out.println();
			System.out.println("-------------");
				
			}
		
		
		//1  4  7  10
		//2  5  8  11
		//3  6  9  12
//		int arr3[][] = new int[3][4];
//		int num3=1;
//		for(int i=0;i<arr3[0].length;i++) {
//			for(int j=0;j<arr3[i].length;j++,num3++) {
//				arr[i][j]=num3;
//			}
//			for(int k=0;k<arr3.length;k++) {
//				for(int j=0;j<arr3[0].length;j++) {
//					System.out.println(arr3[i][j]+"");
//				}
//				System.out.println();
//		}
		//A  B  C  D
		//E  F  G  H
		//I  J  K  L
		int arr3[][]=new int[3][4];
		char alpha='A';
		for(int i=0;i<arr3.length;i++) {
			for(int j=0;j<arr3.length;j++) {
				arr3[i][j]=alpha;
				System.out.println();
			}
		}
		//1
		//2 3
		//4 5 6
		//7 8 9 10
		int [][]arr5 = new int[4][];
//		arr5[0]= new int[1];
//		arr5[1]= new int[2];
//		arr5[2]= new int[3];
//		arr5[3]= new int[4];
		
		int num5=1;
		for(int row=0;row<arr5.length;row++) {
			arr5[row] = new int[row+1]; //열 생성
			//값 대입
			for(int col=0;col<arr5[row].length;col++,num5++);{
				arr5[row][col]=num5;
			}
		}
		for(int row=0; row<arr5.length;row++) {
			for(int col=0;col<arr5[row].length;col++) {
				System.out.println(arr5[row][col]);
			}
			System.out.println();
		}
		System.out.println("----------");	
		
		
	}

}
