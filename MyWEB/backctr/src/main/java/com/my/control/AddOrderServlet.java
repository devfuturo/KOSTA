package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.OrderInfo;
import com.my.dto.OrderLine;
import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.repository.OrderOracleRepository;
import com.my.repository.OrderRepository;

public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//viewcart.js 파일과 연결됨. 주문하기 버튼 클릭

		//요청 시 전달 할 자료도 없고, 세션에 저장되어있는 카트 자료를 주문에 추가 해 주면 됨
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		String result = "";

		HttpSession session = request.getSession();
		Map<Product, Integer> cart = (Map)session.getAttribute("cart");

		//----- Sample Data ------
		if(cart == null) { // 카트에 아무것도 없을 때
			cart = new HashMap<>(); // key,value로 이루어짐
		}
		//		cart.put(new Product("C0001","제주비자림콜드브루",1000), 1);
		//		cart.put(new Product("C0002","아메리카노",1000), 2);
		//		cart.put(new Product("G0001","보온병",1000), 3);
		//		cart.put(new Product("G0002","텀블러",1000), 4);
		//		session.setAttribute("loginInfo", "id9");	 // 샘플테이터로 테스트
		// -----------------------		

		// 장바구니가 비어 있는 케이스
		if(cart == null || cart.size() == 0) { // 장바구니 없거나 비어있는 경우
			// 주문 추가 실패
			Map<String, Object> map = new HashMap<>();
			map.put("status", -1);
			map.put("msg","주문 실패 : 장바구니가 비어있습니다.");
			result = mapper.writeValueAsString(map);
		}else {
			// 로그인 안 되어 있는 케이스
			// 세션에 로그인 된 사용자인가 확인
			String loginedId = (String)session.getAttribute("loginInfo");
			if(loginedId == null) { // 로그인 안 한 사용자인 경우
				Map<String, Object> map = new HashMap<>();
				map.put("status", 0);
				map.put("msg", "로그인하세요");
				result = mapper.writeValueAsString(map);
			} else {
				OrderRepository repository = new OrderOracleRepository();
				OrderInfo info = new OrderInfo();
				info.setOrderId(loginedId);
				List<OrderLine> lines = new ArrayList<>();
				for(Product p : cart.keySet()) {
					Integer quantity = cart.get(p); //p로 얻어온 값이 수량
					OrderLine line = new OrderLine();
					line.setOrderP(p);
					line.setOrderQuantity(quantity);
					lines.add(line); 
				}
				info.setLines(lines);
				try {
					repository.insert(info); // 주문 추가 되면
					session.removeAttribute("cart"); // 장바구니 비우기
					Map<String, Object> map = new HashMap<>();
					map.put("status", 1);
					result = mapper.writeValueAsString(map);
				} catch (AddException e) {
					e.printStackTrace();
				}
			}
		}
		out.print(result);
	}
}

// 도메인 주도 설계 (도메인 관점의 개발기술)
// 해당 도메인(주문)과 일치하도록(기능별) 소프트웨어를 모델링하는 데 중점을 둔 소프트웨어 설계 접근 방식이다.
// 데이터 중복을 피하기 위해 table 두개로 나눈 것
// 도메인의 기능(작업단위)로 보면 주문 추가, 주문 삭제 등으로 나누어지지 주문 기본추가, 주문 상세추가 로 나누어지지X
// SQL의 order_info와 order_line repository 만들어야함 (주문 목록을 쌓을)
// 각각의 repository를 만드는 것이 좋은가? (table별 repository)
// 기능적인 측면에서 하나의 repository로 만드는게 좋음
// repository에서 검색해 올 자료는 주문자 id에 만족하는 주문 내역들을 모두 가져옴 주문 일자, 상품 번호, 수량 등 
// 주문 내역에 모두 보여야함(주문 번호, 일자, 상품번호, 상품명, 가격, 수량) 
// 기능상 주문을 추가하는 작업 > 주문 추가용 메서드 하나, 주문 내역 조회 메서드 하나 > repository 1개..