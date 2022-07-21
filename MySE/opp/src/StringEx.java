import java.sql.SQLClientInfoException;

public class StringEx {
	public static void main(String[] args) {
		String s1 = new String("가나다");
		String s2 = new String("가나다");
		String s3 = "가나다";
		String s4 = "가나다";
		System.out.println(s1 == s2); // false 각자 참조하는 객체가 다름
		System.out.println(s1 == s3); // false 
		System.out.println(s3 == s4); // true 같은 메모리 참조
		
		System.out.println(s1.equals(s2)); // true / 문자열 내용 비교 -> equals
		System.out.println(s1.equals(s3)); // true
		System.out.println(s3.equals(s4)); // true
		
		System.out.println(s1+"라마"); // 가나다라마 -> 변수에 참조하지 않았기 때문에 s1+라마 객체가 만들어지고 출력 후 GC의 대상이 됨
		System.out.println(s1); // 가나다
		System.out.println(s1.substring(1,3)); //나다 substring 부분문자열을 가져온다. 1번 인덱스부터 (3-1)번 인덱스까지(end index-1)
		s1 += "라마"; //s1 = s1 + "라마" ; //가나다라마
		char c = s1.charAt(0) ; // 0번 인덱스의 문자열 반환
		System.out.println(c); // 가 
//		System.out.println(s1.charAt(-1)); // java에서는 음수값 입력x . data 분석에 적절치 않은 언어
		int size = s1.length(); //배열의 길이 : 참조변수형.length / 문자열의 길이 : .length() -> method를 쓰는 것
		System.out.println(size); // 5 / length 문자의 개수를 세줌
		
		// Palindrome 문자열 : 가나다나가 ABBA 
		String palindrome = "가나다나가";
		
//		palindrome = "ABBA";
//		palindrome = "ABCD";
		 
//		int leng = palindrome.length();
//		for(int i=0 ; i<((leng-1)/2;i++) {
//			for(int j = 4 ;j > leng/2;j--) {
//				if(palindrome.charAt(i)==palindrome.charAt(j)) {
//					System.out.println("palindrome 문자열입니다.");
//				}else {
//					System.out.println("palindrome 문자열이 아닙니다.");
//					break;
//				}
//			}
//		}
		
//		int length = palindrome.length();
//		int half = length / 2;
//		int i = 0;
//		for (int j = length - 1; i < half; i++, j--) {
//			if (palindrome.charAt(i) != palindrome.charAt(j)) {
//				break;
//			}
//		}
//		if (i == half) {
//			System.out.println("palindrome 문자열입니다.");
//		}else {
//			System.out.println("palindrome 문자열이아닙니다.");
//
// 	}
		int j = palindrome.length()-1;
		for(int i = 0 ; i<palindrome.length()/2;i++,j--) {
			if(palindrome.charAt(i)!=palindrome.charAt(j)) {
				System.out.println("palindrome 문자열이 아닙니다.");
			}else {
				System.out.println("palindrome 문자열입니다.");
				break;
			}
		}
		
//					
//		String str = "https://news.naver.com/main/clusterArticles.naver?id=c_202205031110_00000070&mode=LSD&mid=shm&sid1=105&oid=018&aid=0005206604";
//		str = "https://news.naver.com";
//		String hostName; //str의 host : news.naver.com
//		String path; //str의 path : main/clusterArticles.naver
//		String queryString; //str의 querystring : id=c_202205031110_00000070&mode=LSD&mid=shm&sid1=105&oid=018&aid=0005206604
//		
//		String regex = "\\?"; //regular expression자리에 regular expression인용 문법인 ? 가 오면 오류.. 따라서 \\ 를 붙여주어 그냥 문자열임을 알려줘야함
//		int limit = 2;
//		String[]arr = str.split(regex, limit); //? / ?를 기준으로 문자열을 자른다. ?를 기준으로 앞/뒤로 자른다라는 의미
//		String hostNameNPath = arr[0];  // https://news.naver.com/main/clusterArticles.naver / split ->runtimeException이라 컴파일 오류 미리 못 잡아냄
//		int beginIndex = hostNameNPath.indexOf(":")+3;//5+3
//		int endIndex = hostNameNPath.indexOf("/", beginIndex);//22 / 그 메소드는 해당 문자의 위치를 반환, 해당 문자를 반환하지 않을 때는 - 만환?!
//		
//		hostName = hostNameNPath.substring(beginIndex, endIndex);
//		path = hostNameNPath.substring(endIndex+1);
//				//hostNameNPath.substring(endIndex+1, hostNameNPath.length());
//		System.out.println(hostName);
//		System.out.println(path);
//		
//		if(arr.length >= 2) {
//		queryString = arr[1];
//		String[] queryStringArr = queryString.split("&");
//
//		for(int index=0; index<queryStringArr.length; index++) {
//			String q = queryStringArr[index];
//			String[]qArr = q.split("=",2);
//			
//			String qName =qArr[0] ;
//			String qValue = qArr[1] ;
//			System.out.println("쿼리스트링 이름은" + qName + ", 값은" + qValue); //쿼리스트링 이름은id, 값은c_202205031110_00000070
//			                                                                //쿼리스트링 이름은mode, 값은LSD
//																			//쿼리스트링 이름은mid, 값은shm
//            																//쿼리스트링 이름은sid1, 값은105
//																			//쿼리스트링 이름은oid, 값은018
//		}																	//쿼리스트링 이름은aid, 값은0005206604
		
		
		String str2 = "http://www.naver.com/";
		String [] arr2 = str2.split("/");
		System.out.println(arr2[2]);
		String [] arr3 = arr2[2].split("\\.");
		System.out.println(arr3[1]);
		
		String str3 = "naver";
		System.out.println(str3.substring(2,4)); //0n1a2v3e4r
	}	
		
}




