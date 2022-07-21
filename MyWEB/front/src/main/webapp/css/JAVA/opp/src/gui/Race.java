package gui;
import java.awt.Canvas;
/*이벤트 프로그래밍 
Listener 인터페이스는 감시자 
ActionEvent : 클릭되었을때 발생되는 이벤트     --> actionPerformed 라는 메서드를 갖고 있음(추상메서드)
ActionEvent(클릭)가 발생하면 actionPerformed가 자동 호출됨 
상위 클래스를 interface 또는 abstract class로 만드는 것에 대한 차이 
- 하위 클래스가 extends로 abstract class로부터 상속을 받으면 단일상속밖에 되지 않음.
- 하위 클래스가 implements로 interface를 연결하면 ,를 통해 다른 interface를 구현할 수있음. 추가적으로 또다른 부모클래스1개로부터 상속받을 수도 있음.
- 즉 interface가 구현할 수 있는 가지가 더 많기에 유연함. 
- 요약하면 추상클래스 -> 단일 상속
           인터페이스 -> 여러인터페이스 + 단일상속 
- 자식 입장에서 인터페이스를 통해 더 많이 받을 수 있음 따라서 인터페이스로 부모를 만드는 것을 권장함 

- 상위 인터페이스가 다른 인터페이스로부터 상속관계에 있다면, 하위 클래스가 상위 인터페이스를 구현할때 상속된 인터페이스들도 구현해야함.
- 상위 인터페이스의 매서드를 호출해도 하위 클래스에 오버라이딩된 매서도가 호출됨 

--중첩 클래스
- inner class : 마치 멤버변수 선언한것처럼 사용 가능 
  			  outer class의 멤버변수나 메서드를 자기것처럼 사용 가능
  			  
*/
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
/**
 * 이벤트 프로그램 절차
 * 1. EventSource(이벤트 발생될 곳)을 결정 ex) btReady
 * 2. 이벤트 종류를 결정 ex) ActionEvent
 * 3. EventHandler(이벤트가 발생했을 때 할 일)을 작성
 * 	  ex) class MyHandler implements ActionListener{
 * 	  	   public void actionPerformed(ActionEvent e)
 * 		   	   sysout("준비되었습니다.");
 * 	       }
 *        }
 * 4. EventSource와 EventHandler를 연결
 *    ex) btReady.add~~(new MyHandler());
 * @author gram
 *
 */
//이름 있는 클래스 타입의 객체코드 -> 가장 재사용성 적고 코딩양이 적음
//class MyHandler implements ActionListener{ //3. EventHandler작성 
//	public void actionPerformed(ActionEvent e) { //자동 호출 되어야하는 actionPerformed를 재정의(추상클래스라 오버라이딩 필수)
//	System.out.println("준비되었습니다");
//	}
//}
//inner class 안 쓸 때, 나쁜 예)
//class MyRunHandler implements ActionListener{ 여기부터
//	private JTextField jtf;
//	public MyRunHandler(JTextField jtf) {
//		this.jtf = jtf;
//	}											여기까지 inner class로 갈 떄 없어도됨

//	public void actionPerformed(ActionEvent e) {
//		jtf.setText("달립니다");
//	}
//}

//말 세마리가 달리기 경주
class Horse extends Canvas implements Runnable { //순서 extends 먼저
	String name; 
	int x =10 , y =10 ;
	Horse(String name){
		this.name = name;
	}
	@Override
	public void paint(Graphics g) {
		g.drawString(name, x, y);
	}
//	@Override
//	public void update(Graphics g) {
//		for(int step = 0 ; step<20 ; step++) {
//			x+=20 ; 
//			super.update(g);  // clear -> paint() 자동호출됨
//			long millis = (long)(Math.random()*1000);//0.0<=r<1000.0
//			try{
//				Thread.sleep(millis);
//			} catch(InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	@Override
	public void run() {
//		this.repaint();
		for(int step = 0 ; step<20 ; step++) {
			x+=20 ; 
			this.repaint();//update ()에 의해 paint() 자동 호출됨 
			long millis = (long)(Math.random()*1000);//0.0<=r<1000.0
			try{
				Thread.sleep(millis);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
public class Race {
	private JFrame jf;
	private JButton btStart, btReady; //버튼 1개
	private JTextField jtf; //한줄 입력란을 알리는 컨퍼런스
	private Horse[] horses;	
	class MyRunHandler implements ActionListener{ //inner class
			public void actionPerformed(ActionEvent e) {
			jtf.setText("달립니다");
//			for(int step = 0 ; step<20 ; step++) {
//				horses[0].repaint();
//			}
//				horses[0].repaint(); horses[1].repaint();horses[2].repaint();
				//스레드 시작
				for(int i = 0 ; i<horses.length ; i++ ) {
					new Thread(horses[i]).start();
				}
			}
	}
		
	public Race() {
		jf = new JFrame("달리기"); //창의 타이틀
		btStart = new JButton("달려"); 
		btReady = new JButton("준비"); // 1. 이벤트 소스 결정
		jtf = new JTextField(10); //()안에 숫자 -> 입력란 가로 넓이 
		horses = new Horse[3];
		String [] horseNames = {"태풍","화랑","번개"};
		for( int i = 0 ; i<horses.length;i++ ) {
//			horses[i] = new Horse("말"+(i+1));
			horses[i] = new Horse(horseNames[i]);
		}
		
		Container c = jf.getContentPane(); //액자 뒷판
		c.setLayout(new FlowLayout()); //클릭되었을 때 할 일, 최대화 되었을 때 할 일 ... -> 이벤트 프로그래밍
		
		c.add(btReady);
		c.add(btStart);
		c.add(jtf);
		c.setLayout(new GridLayout(5,1));
		c.add(horses[0]);
		c.add(horses[1]);
		c.add(horses[2]);
		
		Panel panel = new Panel();
		panel.add(btReady);
		panel.add(btStart);
		c.add(panel);
		c.add(jtf);
		
		
//	    이름있는클래스타입의 객체 실행부	
//		MyHandler myHandler = new MyHandler();
//		btReady.addActionListener(myHandler);
		
//		btReady.addActionListener(
//		이름없는클래스타입의 객체 생성 - new 뒤에 인터페이스명 써주어야함
//				new ActionListener() { //익명 클래스
//					public void actionPerformed(ActionEvent e) { //actionListener 인터페이스를 구현한 하위 class
//						//System.out.println("준비되었습니다"); //익명 구현 객체
//						jtf.setText("준비되었습니다");
//						for(Horse h : horses) {
//							h.x = 0;
//							h.repaint();
//						}
//					}
//				}
//		);
		
		//람다 표현식
		btReady.addActionListener((ActionEvent e)-> { 
			jtf.setText("준비되었습니다");
			for(Horse h : horses) {
				h.x = 0;
				h.repaint();
			}
		}); 

//		btReady.addActionListener(new ActionListner()); //인터페이스는 객체생성X. (추상클래스) 따라서 class하나 만들어서 구현해야함
		btStart.addActionListener(
//				new MyRunHandler(jtf)
				new MyRunHandler()
//				new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//					//	System.out.println("달립니다");
//						jtf.setText("달립니다");
//					}
//				}
		);
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //x표시(닫기) 눌렀을 시 프로그램 완전히 종료되도록함
		
		
		jf.setSize(500,300);
		jf.setVisible(true);
	}
	public static void main(String[] args) {
		new Race();
	}


}

// 이름 없는 클래스 -> 재사용성 낮음. 코딩양 적음
// 이너 클래스     -> 재사용성 중간. 코딩양 중간
// 독립적인 클래스	-> 재사용성 높음. 코딩양 많음
