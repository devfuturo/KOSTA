package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dto.Product;

public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opt = request.getParameter("opt");
		if("forward".equals(opt)) { // 이동의 기능
			// 요청 속성 ( 속성명 : 'test', 값: 상품객체) 추가
			Product sample = new Product("F0001","샌드위치",2000);
			request.setAttribute("test",sample);
			
			
			// ----- FORWARD 전의 응답
			PrintWriter out = response.getWriter();
			out.print("BEFORE FORWARD");
			String path = "/iddupchk"; //iddupchk 과 연결 / 서버 차원에서의 연결(이동)
			RequestDispatcher rd = request.getRequestDispatcher(path); // 현재 사용중인 WebContextPath 내에서만 사용 가능(/back프로젝트 내에서만)
			rd.forward(request, response); // forward 이동
			
			// ----- FORWARD 후의 응답
			out.print("AFTER FORWARD"); // BEFORE, AFTER 볼 수 없음 -> forward는 전달했다가 다시 돌아오지 않음
			
			
		} else if("redirect".equals(opt)){  
			//forward -> 서버 차원의 이동, 기존 객체를 forward된 페이지에서도 사용 가능, 같은 웹컨텍스트에서만 이동가능 
	        //redirect -> 클라이언트 차원의 이동, 기존 객체를 redirect된 페이지에서 사용 불가, 다른 웹컨텍스트로 이동가능
	        //forward가 기본 이동 방법임 
			response.sendRedirect("http://www.google.com/");
			
		} else if("include".equals(opt)) { // 포함의 기능
			//include -> request 와 response를 전달 했다가 다시 돌아오는 것
			//include -> request 와 response를 iddupchk에 전달 했다가 다시 돌아옴 (버퍼에 채워짐)
			
			// ----- INCLUDE 전의 응답
			PrintWriter out = response.getWriter();
			out.print("BEFORE INCLUDE");
			String path = "/iddupchk"; 
			RequestDispatcher rd = request.getRequestDispatcher(path); 
			rd.include(request, response);
			// ----- INCLUDE 후의 응답
			out.print("AFTER INCLUDE");
			
		} else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<ul>");
			
			out.print("<li>");
			out.print("<a href=\"move?opt=forward\">FORWORD</a>");
			out.print("</li>");
			
			out.print("<li>");
			out.print("<a href=\"move?opt=redirect\">REDIRECT</a>");
			out.print("</li>");

			out.print("<li>");
			out.print("<a href=\"move?opt=include\">INCLUDE</a>");
			out.print("</li>");
			
			out.print("</ul>");
		}
		
	}

}
