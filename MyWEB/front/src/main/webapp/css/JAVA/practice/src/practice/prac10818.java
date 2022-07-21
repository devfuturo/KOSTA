package practice;

import java.util.Scanner;

public class prac10818 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num1 = sc.nextInt();
		int [] arr = new int[num1];
		for(int i=0;i<num1;i++) {
			arr[i] = sc.nextInt();
		}
		int max = arr[0];//20
		int min = arr[0];//20
		for(int j=0; j<arr.length;j++) {
			if(arr[j]>max) 
			max = arr[j];
			if(arr[j]<min) 
			min = arr[j];
			
			
		}
		System.out.println(min+" "+max);
		

	}

}
