/**
자바의 자료형에 대한 실습코드
*/
public class DataType{
  /**
    메인 메서드는 클래스 실행 시 진입점의 역할을 하는 메서드이다
  */
  public static void main(String[] args){
    byte b;
    b = 1;
    System.out.println(b); //1
    //b = 128; //byte 범위는 -128~127임

    // 정수 -> 정수 자동 형변환
    short sh;
    sh = b;
    System.out.println(sh);

    //실수 자료형
    float f;
    //f= 01234.567
    f = 01234.567F;

    long lon;
    //lon = 123456789123456789;
    lon = 123456789123456789L; //(뒤에 L을 붙여줘야함)

    //정수->실수 자동형변환
    sh = 1234;
    f = sh;
    System.out.println(sh); //1234
    System.out.println(f);  //1234.0(예상값)

    //실수 -> 정수 형변환
    //sh = f; //자동 형변환 불가
    sh = (short)f; //강제로 변환시킴 강제 형변환
    System.out.println(sh); //1234
    b = (byte)f; //강제 형변환
    System.out.println(b); //-46 float의 맨 뒤의 bit에서 앞으로 끌어오기 때문에 그 값이 1일 시 -가 나올 수 있음

    /*
    //단일 문자형 2byte unicode
    char c = 'A';
    int i= c; // 자동형변환
    System.out println(i); //65

    //논리형 1byte 
    boolean flag;
    flag = true;
    //flag =1; //컴파일 오류
    */
  }
}