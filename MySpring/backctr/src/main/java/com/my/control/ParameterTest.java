package com.my.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.dto.Product;

@Controller
public class ParameterTest {
	@GetMapping("a") // http://localhost:8888/backctr/a
	public void a() { // 컨트롤러용 메서드 (요청을 처리 해 줄 메서드) ~매핑이 붙어있는 메서드	
		//	매개변수 없어도 정상 요청
		System.out.println("a 메서드 호출");
	}

	@GetMapping("b") // http://localhost:8888/backctr/b?no=1
	public void b(HttpServletRequest request) {
		System.out.println(request.getParameter("no")); //no= 로 입력한 값 출력됨
	}

	@GetMapping("c") // http://localhost:8888/backctr/c
	public void c(HttpServletResponse response) throws IOException {
		response.sendRedirect("http://www.google.com");
	}

	@GetMapping("d") // http://localhost:8888/backctr/d
	public void d(HttpSession session) {
		System.out.println("세션 새로 생성 여부 : " + session.isNew()); // 세션 지금 새로 생성 되었는지 확인하는 메서드
		// 첫번째 요청 시 true, 두번째 요청 시 false 나옴
	}

	@GetMapping("e") // http://localhost:8888/backctr/e?prodNo=C0001&prodName=제주비자림콜드브루&prodPrice=6000
	public void e(String prodNo, String prodName, int prodPrice) {
		System.out.println("prodNo = " + prodNo);
		System.out.println("prodName = " + prodName);
		System.out.println("prodPrice = " + prodPrice);
		// 대소문자가 같아야 응답함
		// 요청전달 데이터와 같은 매개변수만 선언하면?! 알아서 자동 대입됨
		// 요청 전달데이터로 얻은 값은 모두 String. 따라서 형변환 하기 위해 parseInt로 형변환 해 주어야함
		// 스프링에서는 요청전달데이터 int 타입은 String으로 받아와도 int로 알아서 형변환 해 줌 - 개쩌는ㄷㅔ..?
	}

	@GetMapping("f")// http://localhost:8888/backctr/f?prod_no=C0001&prodName=제주비자림콜드브루&prodPrice=6000
	// 상품명 전달 X  http://localhost:8888/backctr/f?prod_no=C0001&prodPrice=6000
	// 상품번호만 전달되고 나머지 전달X  http://localhost:8888/backctr/f?prod_no=C0001
	public void f(	@RequestParam(name = "prod_no") String prodNo, 
					// ▲ 스네이크케이스로 선언된 요청전달 데이터 카멜케이스 매개변수에 매핑될 수 있도록 name값 줌
					@RequestParam(required = false)String prodName, 
					// ▲ required = false를 주면 요청전달데이터로 줄 수도 있고 안 줄 수도 있다는 것을 나타냄 
					// required의 기본 속성은 true / false -> 선택 전달 사항
					// String값이기 때문에 기본 null
					@RequestParam(required = false, defaultValue="0") int prodPrice) {
					// required = false로 주면 int타입인 price는 기본 값 0 될 것 같지만 절대 X
					// 요청 전달 데이터는 기본 String이기 때문에(int타입은 내부에서 integer.ParseInt 변환) 
					// String 기본값인 null을 int로 변환하려고 하기 때문에 에러
					// 따라서 defaultValue값도 0으로 지정 해 주어야 0이 나옴
		System.out.println("prodNo = " + prodNo);
		System.out.println("prodName = " + prodName);
		System.out.println("prodPrice = " + prodPrice);
	}

	@GetMapping("g")
	public void g(Product p) { // 매개변수가 Product라는 dto / command 객체?!
		// http://localhost:8888/backctr/g?prodNo=C0001&prodName=제주비자림콜드브루&prodPrice=6000
		// http://localhost:8888/backctr/g?prodNo=C0001  -> prodName, prodPrice 멤버변수의 초기 값은 null, 0 이기 때문에 null, 0으로 출력
		System.out.println("prodNo =" + p.getProdNo());
		System.out.println("prodName =" + p.getProdName());
		System.out.println("prodPrice =" + p.getProdPrice());
	}

	@GetMapping("h") //http://localhost:8888/backctr/h?arr=one&arr=two&arr=three
	public void h(String[] arr) { //주로 체크박스 할 때 사용
		for(String str:arr) {
			System.out.println(str);
		}
	}
	
	//http://localhost:8888/backctr/i?prodNo=1&prodName=a&prodNo=2&prodName=b
	@PostMapping("i")
//	public void i(String[]prodNo, String[]prodName) {
//		/*for(String no : prodNo) {
//			System.out.println(no);
//		}
//		for(String name : prodName) {
//			System.out.println(name);
//		} */
//		for(int i=0 ; i<prodNo.length ; i++) {
//			System.out.println(prodNo[i] + ":" + prodName[i]);
//		}
//	}
	public void i(@RequestBody List<Product> list) { // List<Product> 대신 Product[]도 OK
		//Stirng타입을 list타입으로 변환 해 줄 것이 잆어야함
		for(Product p : list) {
			System.out.println(p);
	
		}
	}

}