package com.my.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.service.CustomerService;

public class CustomerController implements Controller {
	//	controller의 역할
	//	request 통해 요청을 받아 도메인 별 기능 호출, 결과값 만들어냄 만들어낸 결과값 (문자열) 을 return만 하는 역할
	// 실제 응답은 DispatcherServlet이 함

	//	private CustomerRepository repository;
	private CustomerService service;
	public CustomerController() {
		//repository = new CustomerOracleRepository();
		service = new CustomerService();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//	login url이 요청 되었을 때 controller을 찾아서 execute()를 찾아 로그인 관련 작업 진행하도록
		//	signup url이 요청 되었을 시 가입 작업 실행
		//	properties 파일의 한계 - 좀더 세분화 되어 value의 value로 들어가는 작업 불가
		//	spring framework에선 properties을 xml파일로 대신함
		//	XML 좀 더 세분화 할 수 있음

		String servletPath = request.getServletPath();
		if("/login".equals(servletPath)){ // 로그인 작업 (login url이 요청된 경우)
			return login(request, response);
		}else if("/signup".equals(servletPath)) { // 가입 작업 (signup url이 요청된 경우)
			return signup(request, response);
		}else if("/iddupchk".equals(servletPath)) { // 가입 작업 (signup url이 요청된 경우)
			return iddupchk(request, response);
		}else if("/loginstatus".equals(servletPath)){
			return loginstatus(request, response);
		}else if("/logout".equals(servletPath)){
			return logout(request, response);
		}	
		return null; //if, else if 에도 만족하지 않았을 때 return null
	}

	// 로그인 작업용 메서드
	private String login(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//loginServlet에서 했던 일들 
		//DB와의 일은 repository에게 맡김

		// 요청 전달 데이터 얻기
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		//Jackson 사용하여 Json형태로 응답
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		map.put("status", 0);

		//응답 결과
		String result = null; // "{\"status\":0}"; // 로그인 실패 

		// 세션(클라이언트별 서버쪽 객체) 얻기
		HttpSession session = request.getSession();
		session.removeAttribute("loginInfo");

		//	비즈니스 로직 연결 (가장 핵심이 되는 작업)
		//	DB와 연결하여 DB에 id, pwd값이 있는지 확인함
		//		CustomerRepository repository = new CustomerOracleRepository(); // 위에서 선언 해 줬기 때문에 필요 없음

//		try {
//			//DB와 연결하여 로그인을 담당 할 메서드
//			// repository의 selectByIdAndPwd 메서드 호출
//			//repository.selectByIdAndPwd(id, pwd); // 재사용성이 떨어져 없애버림
//			Customer c = repository.selectById(id);
//			if(c.getPwd().equals(pwd)) { // 로그인 성공 
//				// ▲ 아이디에 만족하는 비밀번호와 요청전달데이터와 같은지 비교
//
//				// result = "{\"status\": 1}"; // 로그인 성공
//				map.put("status", 1); // 로그인 성공 
//				// ▲ status 값이 초기에는 0이였다가 성공하면 1로 덮어쓰기 되는 것
//
//				session.setAttribute("loginInfo", id); //attribute는 어떤 정보도 저장할 수 있음
//				// 로그인이 성공했을 때만 위 작업 진행
//
//			} // else -> 아이디에 만족하는 고객이 있으나 비밀번호가 틀린 경우지만 상세정보 줄 필요가 없음
//		} catch(FindException e) { // 찾기 작업이기 때문에 FindException발생할 것을 예상
//
//		}
		try {
			Customer c = service.login(id, pwd); //service 메서드 호출 시 id, pwd값 전달 
			map.put("status", 1);
			session.setAttribute("loginInfo", id);
			// session.setAttribute("loginInfo", c); 
			// ▲ 로그인된 아이디값 뿐만 아니라 로그인된 사람의 이름, 주소 등 관리하고 싶을 시 customer 타입의 객체로 세션 attribute해도 됨
		} catch (FindException e) {
			e.printStackTrace();
			
		} 
		
		result = mapper.writeValueAsString(map);
		return result;		
	}

	// 가입 작업용 메서드
	private String signup(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		// 요청전달 데이터 얻기
		String id = request.getParameter("id"); // signup.html의 아이디 name=id와 연결됨
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		String buildingno = request.getParameter("buildingno");

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		map.put("status", 0);
		map.put("msg", "가입실패");

		// 응답 결과
		//		String result = "{\"status\":0, \"msg\": \"가입실패\"}";
		String result = null;

		//		CustomerRepository repository = new CustomerOracleRepository();
		Customer customer = new Customer (id, pwd, name, addr, 1, buildingno); // 생성자 사용

		try {
			// repository의 insert 메서드 호출
//			repository.insert(customer);  //customer를 인자로 사용
			//	result = "{\"status\":1, \"msg\": \"가입성공\"}";
			service.signup(customer);
			map.put("status", 1);
			map.put("msg", "가입성공");
		}catch(AddException e) {

		}
		result = mapper.writeValueAsString(map);
		return result;
	}

	// 아이디 중복확인용 메서드
	private String iddupchk(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		//요청 전달 데이터
		String id = request.getParameter("id");

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		String result = null;
		map.put("status", 0);
		map.put("msg", "이미 사용중인 아이디입니다");				

		//			CustomerRepository repository = new CustomerOracleRepository();

		try {				
//			Customer c = repository.selectById(id); // 이미 존재하는 아이디인 경우
			Customer c = service.iddupchk(id);
		}catch (FindException e) { // 사용 가능한 아이디인 경우
			map.put("status", 1);
			map.put("msg", "사용 가능한 아이디입니다");
		}
		result = mapper.writeValueAsString(map);
		return result;			
	}


	private String loginstatus(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginedId = (String)session.getAttribute("loginInfo");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();

		if(loginedId == null) {
			map.put("status", 0);
			map.put("msg", "로그아웃 상태입니다");
		} else {
			map.put("status", 1);
			map.put("msg", "로그인 상태입니다");
		}
		return mapper.writeValueAsString(map);
	}

	private String logout(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("loginInfo");
//		String result = ("로그아웃 되었습니다");
//		return result;
	
		return null; // 아무값도 응답하지 않겠다
		// front가 back에게 요청하여 응답할 내용이 없을  시 null
		// 응답은 하되 응답 할 내용이 없을 때

		//	return ""; //String 타입의 빈 문자열을 응답하겠다
		//	응답 내용은 있으나 빈 문자열로 return 시 
	}

}



