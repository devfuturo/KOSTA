package com.my.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestServlet
 */
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer url = request.getRequestURL();
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		System.out.println("url = "+ url); // http://localhost:8888/back/request
		System.out.println("contextPath = " + contextPath); // /back
		System.out.println("servletPath = " + servletPath); // /request
		
		/*
		 *http://localhost:8888/back/request?opt=add요청일 경우 opt값은 "add"	요청 전달데이터 O 값 O 
		 *http://localhost:8888/back/request?opt=   요청일 경우 opt값은 " " 	요청 전달데이터 O 값 O 
		 *http://localhost:8888/back/request		요청일 경우 opt값은 null	요청 전달데이터 X 값 X
		 */
		// backEnd는 다수의 client가 다양한 경우의 수로 요청하기 때문에 request parameter 알아야함
		
		String opt = request.getParameter("opt"); 
		System.out.println("요청전달데이터 opt = " + opt); // add
//		if(opt.equals("add")){ // NullPointerException에 빠질 수 있음
//		if(opt != null && opt.equals("add"){  //코드양이 많음
		if("add".equals(opt)) {			
			System.out.println("등록작업을 선택했습니다");
		} 
		
		/*
		 * http://localhost:8888/back/request?c=c1&c=c2 요청일 경우 c 값은 c1, c2
		 * http://localhost:8888/back/request?c=		요청일 경우 cArr.length는 0
		 * http://localhost:8888/back/request 			요청일 경우 cArr가 null (NullPointerException)
		 */
		
		String [] cArr = request.getParameterValues("c");
		if(cArr != null) {
			for(String c : cArr) {
				System.out.println("요청전달데이터 c = " + c);
			}
		}
	}	
}
