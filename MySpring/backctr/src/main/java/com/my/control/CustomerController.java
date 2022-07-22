package com.my.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.service.CustomerService;

@Controller
public class CustomerController { // 이 클래스가 스프링컨테이너로 관리 될 객체가 되기 위하여는 Controller로 등록
	@Autowired
	private CustomerService service; //autowired로 빈(Bean) 객체 주입 받음
	
	@PostMapping("/login")
	@ResponseBody //클라이언트에게 json문자열로 응답하기 위함
//	public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
	public Map login(String id, String pwd, HttpSession session) {
	//Map이 알아서 json문자열로 변경되어 응답할 것 따라서 jackson 사용할 필요도 없음
		
		// 요청 전달 데이터 얻기
//		String id = request.getParameter("id");
//		String pwd = request.getParameter("pwd");

		//Jackson 사용하여 Json형태로 응답
//		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		map.put("status", 0);

		//응답 결과
//		String result = null; // "{\"status\":0}"; // 로그인 실패 

		// 세션(클라이언트별 서버쪽 객체) 얻기
//		HttpSession session = request.getSession();
		session.removeAttribute("loginInfo");

		//	비즈니스 로직 연결 (가장 핵심이 되는 작업)
		//	DB와 연결하여 DB에 id, pwd값이 있는지 확인함
		try {
			Customer c = service.login(id, pwd); //service 메서드 호출 시 id, pwd값 전달 
			map.put("status", 1);
			session.setAttribute("loginInfo", id);
			// session.setAttribute("loginInfo", c); 
			// ▲ 로그인된 아이디값 뿐만 아니라 로그인된 사람의 이름, 주소 등 관리하고 싶을 시 customer 타입의 객체로 세션 attribute해도 됨
		} catch (FindException e) {
			e.printStackTrace();
		} 

//		result = mapper.writeValueAsString(map);
//		return result;
		return map;
	}
	
	@PostMapping("signup") // '/'패턴 있어도 없어도 상관 없음
	@ResponseBody
	public Map Signup(String id, String pwd, String name, String address, String buildingno) {
		Map<String, Object> map = new HashMap<>();
		map.put("status", 0);
		map.put("msg", "가입실패");	
		Customer c = new Customer(id, pwd, name, address, 1, buildingno);
		
		try {
			service.signup(c);
			map.put("status", 1);
			map.put("msg", "가입성공");
		} catch (AddException e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	@PostMapping("iddupchk")
	@ResponseBody
	public Map Iddupchk(String id) {

		Map<String, Object> map = new HashMap<>();
		String result = null;
		map.put("status", 0);
		map.put("msg", "이미 사용중인 아이디입니다");				

		try {				
			Customer c = service.iddupchk(id);
		}catch (FindException e) { // 사용 가능한 아이디인 경우
			map.put("status", 1);
			map.put("msg", "사용 가능한 아이디입니다");
		}
		return map;
	}
	
	@GetMapping("loginstatus")
	public Map loginstatus(HttpSession session) {

		String loginedId = (String)session.getAttribute("loginInfo");
		Map<String, Object> map = new HashMap<>();

		if(loginedId == null) {
			map.put("status", 0);
			map.put("msg", "로그아웃 상태입니다");
		} else {
			map.put("status", 1);
			map.put("msg", "로그인 상태입니다");
		}
		return map;
	}
	
	@GetMapping(value="logout")
	private String logout(HttpSession session) {
		session.removeAttribute("loginInfo");
		
		String result = ("로그아웃 되었습니다");
		System.out.println(result);
//		return  result;
	
		return null; // 아무값도 응답하지 않겠다
	}	
}
