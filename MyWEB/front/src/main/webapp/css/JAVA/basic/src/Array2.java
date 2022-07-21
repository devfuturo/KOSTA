public class Array2 { 
	public static void main(String[] args) {
//		int [][]arr = new int[3][4];
//		
//		for(int i=0; i<arr.length; i++) {
//			for(int j=0; j<arr[i].length; j++) {	
//				arr[i][j] = j+1;
//				System.out.print(arr[i][j]);
//			}
//			System.out.println();
//		}
//		for(int i=0; i<arr.length; i++) {
//			for(int j=0; j<arr[i].length; j++) {	
//				System.out.print(arr[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println("------");
		
		//1  2  3  4
		//5  6  7  8
		//9 10 11 12
		int arr2[][] = new int[3][4];
		int num2 = 1;

		for (int i=0; i<arr2.length; i++) {
			for (int j=0; j<arr2[i].length; j++, num2++) {
				arr2[i][j] = num2;
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
		
		
//		int[][]scores = new int[5][3];
//		scores[0][0]=9;
//		scores[0][1]=8;
//		scores[0][2]=5;
		int[][]scores = {{9, 8,  5},
						 {10,3,  5},
						 {7, 10, 8},
						 {3,  2, 1},
						 {5,  6, 7}
						 }; //배열 초기화
		//각 학생의 총점과 평균 계산하시오
//		int totalScore = scores[0][0]+scores[0][1]+scores[0][2];
		int totalScore = 0;
		for(int col=0; col < scores[0].length; col++) {
			totalScore+= scores[0][col];
		}
		float avg = totalScore /scores[0].length;
		//1번 학생의 총점은  이고 평균은  이다.
		//2번 학생의 총점은  이고 평균은  이다. 
		//3번 학생의 총점은  이고 평균은  이다.
		//4번 학생의 총점은  이고 평균은  이다.
		//5번 학생의 총점은  이고 평균은  이다.
		 
			int studentCnt = scores.length;
			int subjectCnt = scores[0].length;	
			
			for(int row=0; row<studentCnt; row++) {
				int studentTotalScore = 0;
				for(int col=0; col < subjectCnt; col++) {
					studentTotalScore += scores[row][col];
				}
				float studentAvg = (float)studentTotalScore /subjectCnt;
				System.out.println(row+1+"번 학생의 총점은" + studentTotalScore+"이고  평균은" + studentAvg   +"이다");
			}
			
			//과목별 평균을 계산하시오
			/*
	국어과목의 평균은 6.8
	영어과목의 평균은 5.8
	수학과목의 평균은 5.2
			 */
			String []subjects = { "국어", "영어", "수학"};
			int []subjectTotalScores = new int[subjectCnt];		
			
			for(int row=0; row<studentCnt; row++) {
				for(int col=0; col < subjectCnt; col++) {
					subjectTotalScores[col] += scores[row][col];
				}
			}
			for(int i=0; i<subjectCnt; i++) {
				float subjectAvg = (float)subjectTotalScores[i]/studentCnt;
				System.out.println(subjects[i] + "과목의 평균은 " + subjectAvg);
		
			}
			
			//arr6 배열값을 90도 회전시켜서 arr7배열에 저장하기
			//1  2   3   4      13  9   5  1
			//5  6   7   8      14  10  6  2
			//9  10  11  12     15  11  7  3
			//13 14  15  16     16  12  8  4	
			
			//a7[0][3]=a6[0][0];
			//a7[1][3]=a6[0][1];
			//a7[2][3]=a6[0][2];
			//a7[3][3]=a6[0][3];
			int[][]arr6= {{1,2,3,4},
						  {5,6,7,8},
						  {9,10,11,12},
						  {13,14,15,16}};
			int[][]arr7 = new int[4][4];
			
			for(int i=0;i<arr6.length;i++){
				for(int j=0;j<arr6[i].length;j++) {
					arr6[i][j]=arr7[j-3][i];
				}
			}
			for(int i=0;i<arr6.length;i++) {
				for(int j=0;j<arr6[i].length;j++) {
					System.out.println(arr7[i][j]+" ");
				}
				System.out.println();
			}
//			// 선언부
//			int[][] arr6 = {{1, 2, 3, 4}, {5, 6, 7, 8},{9, 10, 11, 12}, {13, 14, 15, 16}};
//			int[][] arr7 = new int[4][4];
//			
//			// 반복문
//			for (int i = 0; i < arr6.length; i++ ) {
//				for(int j = 3; j > -1; j-- ) {
//					arr1[i][j] =  arr[j][i];
//					System.out.print(arr7[i][j] + " ");
//				}
//				System.out.println();
//				
//			}

		 String str; //참조형 
		 str = new String("HELLO");
	}
	
}
		
	


