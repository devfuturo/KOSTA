public class Operator{
  public static void main(String []args){
    //산술연산자
    int a,b,c;
    a=10; b=3;
    c=a+b;
    System.out.println(c); //13
    c=a%b;
    System.out.println(c); //1

    short s1,s2,s3;
    s1=10; s2=3;
    //s3=s1+s2; //컴파일 오류 발생됨 s1+s2에서 int타입의 결과가 나왔음 short에 대입하려니 오류가 나옴 why? 4byte(int)보다 작은 메모리는 연산에 참여할 수X , 자동으로 int로 변환됨
    //short = int+short 이기 때문에 오류 발생. 13이라는 값이 나오기 위해선 강제형변환이 필요 , 큰 산술형 작은 산술형 연산하면 작은 산술형 결과 나옴
    s3=(short)(s1+s2);  //컴파일 성공 

    float f;
    a=10;
    b=3;
    f=a+b;
    System.out.println(f); //13.0
    a=10;
    b=3;
    f = a/b;
    System.out.println(f); // 3.0

    f = (float)a/b; // a만 참여시키면 됨
    System.out.println(f); // 3.3333333

    //대입 연산자
    a+=10;
    System.out.println(a); //20

    //비교 연산자
    a=10;
    System.out.println(a%2 == 0); //true
    a=8;
    System.out.println(a%2 == 0); //true
    b=3;
    System.out.println(b%2 == 1); //true
    System.out.println(b%2 != 0); //true
    System.out.println(a>b); //true

    //논리 연산자
    System.out.println(true && true); // true &&를 좀더 빠른 연산이라고 함 &&는 왼쪽이 false이면 뒤의 연산을 참여시키지 않음. 
    System.out.println(true &   true); // true 결과는 위와 동일. &는 왼쪽(앞)이 false라고 해도 오른쪽(뒤)을 연산에 참여시킴. 
    // 속도에 영향을 미치기 때문에 주로 &&를 사용함. 오른쪽 메서드(뒤쪽)가 반드시 참여해야할 땐 &연산자를 사용함(count에 필요할 때)
    System.out.println(false &&  true); // false
    System.out.println(false &   true); // false

    //비트 연산자 
    System.out.println(0 & 1); // 참여하는 자료형이 숫자일 때 논리 연산자X 비트 연산자  

    //단항 연산자
    a=10;
    a++;
    System.out.println(a); //11

    a=10;
    a--;
    System.out.println(a); //9

    a=10;
    b=a++;
    System.out.println(b); //10 -> a먼저 연산 ++ 가장 나중에
    System.out.println(a); //11

    a=10;
    b=++a;
    System.out.println(b); //11 -> ++ 앞에 붙었기 때문에 먼저 실행
    System.out.println(a); //11  

    byte b1;
    b1=127;
    b1++; //cf) b1=128; -> 컴파일 오류 ++값은 출력 됨(-로)
    System.out.println(b1); // 최대 127이기 때문에 128이 출력되지 않고 내부에서 순환(0->1, 1->0)됨 -> 따라서 최소값으로 가게됨 -128
 
    //삼항 연산자
    a=10;
    System.out.println(a%2==0?"짝수":"홀수");
  }
}