package thread;

import java.text.SimpleDateFormat;
import java.util.Date;

class First extends Thread{
	@Override
	public void run() { // run method에는 thread가 할 일을 정해주는 것
		for(int i = 1 ; i<=100 ; i++) {
			System.out.println(Thread.currentThread().getName()+":i="+i);
		}
	}
}
class Second implements Runnable{
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
	@Override
	public void run() {
		for(int i=0 ; i<10 ; i++) {
			Date dt = new Date();
			String formatStr = sdf.format(dt);
			System.out.println(Thread.currentThread().getName()+"현재시간="+formatStr);
			//System.out.println(dt);
			long millis = 1000L;
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace(); //예외종류, 예외내용, 예외발생위치 차례차례 분석해서 콘솔에 출력해 줌 
				// cf) e.getMessage() 예외내용만 반환 실제 현업에선 e.getMessage 추천
			}
		}
	}
}
public class ThreadTest {
	public static void main(String [] args) {
		Thread ct = Thread.currentThread();
		String ctName = ct.getName();
		System.out.println("현재 사용중인 스레드 이름:" + ctName);
		
		First one = new First();
		First two = new First();
		Second second = new Second();
		Thread three = new Thread(second);
		Thread four = new Thread(second);
		
//		one.run(); //main thread에서 run()가 호출됨. thread 시작하려면 start통해 호출해야함
		one.start(); //새로운 thread가 맹글어짐 / 새로운 thread의 run method가 호출됨
		two.start();
		//second.start(); // First method는 start() 상속되었으나, Second method는 start() 상속되지 않음 따라서 start() 사용 불가
		three.start(); //스레드 시작
		four.start();
		System.out.println("끝"); //main thread가 할 일 
	}
}
