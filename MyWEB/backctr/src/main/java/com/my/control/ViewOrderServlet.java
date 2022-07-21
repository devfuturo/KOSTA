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
import com.my.exception.FindException;
import com.my.repository.OrderOracleRepository;
import com.my.repository.OrderRepository;

public class ViewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 전달 데이터 X 로그인된 (주문자) id값은 세션 객체의 어트리뷰트로 있음
		// 주문 정보는 DB에 있음
		// 응답결
		// session의 주문 내용 확인하고 주문 내용을 json형태로 응답한다
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		
		HttpSession session = request.getSession();
		String loginedId = (String) request.getAttribute("loginInfo");
		String result = null;

		
		if(loginedId == null) { //로그인 안 되어있을 때 
			Map<String, Object> map = new HashMap<>();
			map.put("status", 0);
			map.put("msg", "로그인하세요");
			result = mapper.writeValueAsString(map);
		}else { // 로그인 되어있을 시
			OrderRepository repository = new OrderOracleRepository();
			OrderInfo info = new OrderInfo();
			info.setOrderId(loginedId);
			List<OrderInfo> infos = new ArrayList<>() ;
			try {
				infos = repository.selectById(loginedId);
				Map<String, Object> map = new HashMap<>();
				map.put("orderInfo", infos);
				result = mapper.writeValueAsString(map);
				out.print(result);
			} catch (FindException e) {
			}
			
		}
		
	}

//	
//		// 요청 전달 데이터 X 로그인된 (주문자) id값은 세션 객체의 어트리뷰트로 있음
//		// 주문 정보는 DB에 있음
//		// 응답결
//		// session의 주문 내용 확인하고 주문 내용을 json형태로 응답한다
//
//		// 서블릿이 결과 응답
//		response.setContentType("application/json;charset=UTF-8");
//		PrintWriter out = response.getWriter();
////		String result = "";
//		
//		// 로그인된 주문자 id값 얻기
//		HttpSession session = request.getSession();
////		Map<OrderInfo, Integer> loginInfo = (Map)session.getAttribute("loginInfo");
//		ObjectMapper mapper = new ObjectMapper();
//		
//		String id = mapper.writeValueAsString(session.getAttribute("loginInfo"));
//		String loginedId = (String)session.getAttribute("loginInfo");
//		
//		//DB에서 주문 정보 검색
//		OrderRepository repository = new OrderOracleRepository();
//		List list = new ArrayList<>();
//		try {
//			List<OrderInfo> info = repository.selectById(id);
//			out.print(id); // 로그인한 id가 나옴
//			
//			if (id == loginedId) {
//				
//				for(OrderInfo i : .keySet()) {
//					
//					Map<String, Object> map  = new HashMap<>();
//					map.put("status", 1);
//					map.put("info", info);
//				}
//				String result = mapper.writeValueAsString(list);
//				out.print(result);
//				
//			} else {
//				out.print("주문내역이 없습니다");
//			}
//			
//		} catch (FindException e) {
//			e.printStackTrace();
//		}
//		
//		
//		
//		// 주문 내역에 있는 결과를 꺼내기
////		List list = new ArrayList<>();
////		for(OrderInfo i : info.keySet()) {
////			Map map = new HashMap<>();
////			map.put(order_id, list);
////			map.put()
////		}
////		
		
		
	}
	
//}
