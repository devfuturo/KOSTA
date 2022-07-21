package com.my.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller // 컨트롤러 타입은 누구나 접근 가능하도록 public으로 선언해야함
public class ReturnTest {
	
	@GetMapping("a1") 
	public ModelAndView a() {
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("greeting","HELLO"); 
		//	컨트롤러에서 만든 계산 값을 뷰어에게 전달해야하는데 request에 attribute를 하여 전달해야 하는데 
		//	spring에서는 addObject를 활용해야함
		mnv.setViewName("/WEB-INF/jsp/a.jsp"); // 해당 뷰어로 이동
		return mnv;
	}
	
	@GetMapping("b1")
	public String b(Model model) { // 매개변수 ModelANdView타입과 같은 효과를 나타냄
		model.addAttribute("greeting","안녕하세요");
		return "/WEB-INF/jsp/a.jsp";  // 뷰 이름으로 /WEB-INF/jsp/a.jsp 반환
		// String 타입으로 리턴하게 하면 무조건 뷰 이름 리턴
	}
	
	@GetMapping("c1") // return "WEB-INF/jsp/c1.jsp" 과 같음 
	public void c() { //default view name generation 오류가 나타남
		// void로 설계되고 return타입이 없음
		// void 타입으로 설계 시 viewresolvers 에서 해당 prefix와 suffix를 찾아서 알아서 return하도록 되어있음	
	}
	@GetMapping(value="d1" , produces="application/json;charset=UTF-8") 
	// produces : 응답형태 작성. 
	// text/plain 형태로 응답도 OK
	@ResponseBody // 뷰 이름으로 문자열을 반환하는 것이 아닌 문자열 내용 그대로 반환하도록 함
	//type : class, interface, method 앞에 사용 가능
	//실행시에 효과를 내는 어노테이션
	//코멘트 : 소스코드에 아무 영향 주지 X 컴파일, 실행에 어떠한 효과도 주지 않는 것
	//어노테이션 : 컴파일 타임, 실행 타임에 효과를 낼 수 있는 주석
	//설명서의 Retention : runtime라는 것은 컴파일 타임에 효과를 내는 것
	public String d() { 
		//view 이름이 아닌 문자열을 리턴하도록 함
		String responseData = "응답내용입니다";
		return responseData; // 뷰 이름으로 "응답 내용입니다" 라는 것을 반환
		// @ResponseBody 사용 시 뷰 이름으로 응답내용입니다를 반환하는 것이 아닌 문자열  그대로 "응답 내용입니다"를 응답
	}
}
