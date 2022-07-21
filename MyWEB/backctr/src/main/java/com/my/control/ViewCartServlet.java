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
import com.my.dto.Product;

public class ViewCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//session의 카트 내용 확인하고 카트 내용을 json 형태로 응답
		HttpSession session = request.getSession();
		Map<Product, Integer> cart = (Map)session.getAttribute("cart");
		
		ObjectMapper mapper = new ObjectMapper();// json문자열 하나하나 만들지 않고 jackson사용
//		if(cart == null || cart.size() == 0) { // 장바구니가 비어있는 경우
//			Map<String, Object> map = new HashMap<>(); 
//			
//		}
		List list = new ArrayList<>();
		for(Product p : cart.keySet()) { //카트에있는 자료들 꺼내는 것
			Integer quantity = cart.get(p);
			Map map = new HashMap<>(); //응답 할 json문자열이 될 맵
			map.put("p", p);
			map.put("quantity", quantity);
//			mapper.writeValueAsString(map); // {"p":{"prodNo":"C0001",~}, "quantity":1} - JavaScript형태의 {} property"p" 
			list.add(map);
		}
		String result = mapper.writeValueAsString(list); //배열형태 [{},{}]
		System.out.println(result);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}

}
