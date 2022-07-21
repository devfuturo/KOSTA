package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.Product;
import com.my.exception.FindException;
import com.my.repository.ProductOracleRepository;
import com.my.repository.ProductRepository;

public class ViewProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//서블릿이 직접 결과를 응답(json형태. jsp X)
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String result ="";
		
		//1. 요청 전달데이터 얻기
		String prod_no = request.getParameter("prod_no");
		
		//2. DB에서 상품 검색
		ProductRepository respository = new ProductOracleRepository();
		try {
			Product p = respository.selectByProdNo(prod_no);
			Map<String, Object> map = new HashMap<>();
			map.put("status", 1);
			map.put("p", p);
			
			//json 문자열 응답하기 위해 json용 라이브러리 사용 -> Jackson이라는 라이브러리
			
			// JSON라이브러리(JACKSON)활용
			//result = "{\"status\":1, \"p\":{\"prod_no\": \""+p.getProdNo() +"\"}  }";
			ObjectMapper mapper = new ObjectMapper();
			// String pJsonValue = mapper.writeValueAsString(p); // return 타입 string으로 변경
			// System.out.println("pJsonValue"+ pJsonValue);
			// result = "{\"status\":1, \"p\":" + pJsonValue +"}";
			
			String jsonValue = mapper.writeValueAsString(map);
			System.out.println("jsonValue : " + jsonValue); 
			//▲ 출력 jsonValue : {"p":{"prodNo":"C0001","prodName":"제주비자림콜드브루","prodPrice":1100,"prodInfo":null,"prodMfd":null},"status":1}
			
			result = mapper.writeValueAsString(map);
			System.out.println("result : " + result);
			out.print(result);
			//json 형태 -> 기존 페이지에 추가로 끼워넣기 / 주소 url변경 -> clear해서 새로 처리(채워짐)
			
			//3. request의 속성(이름:"p",값 : 상품객체) 설정 (응답 형식 지정)
//			request.setAttribute("p",p);
			
//			//4. "/back/jsp/viewproduct.jsp"로 이동
//			String path = "/jsp/viewproduct.jsp";
//			RequestDispatcher rd = request.getRequestDispatcher(path);
//			rd.forward(request, response);
			
			
		} catch (FindException e) { 
			e.printStackTrace();
			Map<String, Object> map = new HashMap<>();
			map.put("status", 0);
			map.put("msg", e.getMessage());
			
			ObjectMapper mapper = new ObjectMapper();
			result =  mapper.writeValueAsString(map);
			System.out.println("result : " + result);
			out.print(result);
		}
		
	}

}
