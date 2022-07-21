public class Struct {
	public static void main(String[] args) {
		//배열 : 동일 자료형의 데이터 묶음, 크기 고정, 길이 얻기 length
		int[] arr = new int[10];
		arr[0]=5;
		arr[1]=8;
//		arr[2]="HELLO";
//		arr[10]=999;
		
		//맵 : 키와 값을 저장, 크기 자동변경 , 길이 얻기 size()
		java.util.Map map; //map 자바의 라이브러리
		map= new java.util.HashMap();
		map.put("name","한미래"); //, 앞을 첫번째 인자 뒤를 두번째 인자
		map.put("addr", "분당구");
		map.put("sal", 3000); //map 알아서 크기를 결정해 줌 .데이터 1 저장하면 크기 1 2개 저장하면 크기 2, 3개 저장하면 크기 3
		System.out.println(map.size()); //3
		System.out.println(map.get("name")); // 한미래
		System.out.println(map.get("sal")); //3000
		System.out.println(map.get("addr"));
		//맵 - 데이터 저장하고 관리하기 쉬운 가방, 자료만 들어감, 기능X
		}

}
