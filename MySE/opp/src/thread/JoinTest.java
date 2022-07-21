package thread;
class Join extends Thread{
	int begin, end;
	int sum;
	Join (int begin, int end){
		this.begin = begin;
		this.end = end;
	}
	public void run() {
		for(int i = begin ; i<=end;i++) {
			sum+=i;  //합을 구하는건 j1 thread가 할 일
		}
	}
}

public class JoinTest {

	public static void main(String[] args) {
		Join j1 = new Join(1,10);
		Join j2 = new Join(11,20);
		j1.start();
		j2.start();
		try {
			j1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 	//join()실행하고, 예외처리 try catch 시 main thread를 잠시 중지시키고 합을 가지고 올 수 있음
		try {
			j2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(j1.begin + "부터 " + j1.end+"의 합은 " + j1.sum); //main thread가 할 일
		//main과 j1 중 누가 cpu를 차지해서 먼저 실행될 지 알 수 없음
		System.out.println(j2.begin + "부터 " + j2.end+"의 합은 " + j2.sum); //main thread가 할 일
	}

}
