package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResponseServlet
 */
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 응답형식 설정 : MIME (text/plain, text/html, application/json) 
		// 응답형식 지정 안할 시 html형태로 지정됨
		// Multipurpose Internet Mail Extensions - 전자 우편을 위한 인터넷 표준 포맷이다.
		// 이런 포맷으로 응답할거야.라는 뜻으로 가장 먼저 해야함
		response.setContentType("text/html;charset=UTF-8"); // 기본 인코딩 ISO_88859_1
		
		//응답출력스트림 얻기
		PrintWriter out = response.getWriter(); // 문자로 변환해서 출력 해 주는 메서드 printWriter
		
		// 응답출력스트림에 쓰기
		// System.out의 자료형은 PrintStream
		out.print("<html>"); 
		out.print("<body>"); // out.print로 응답할 내용 만들어냄
		for(int i=1; i<=5; i++) { 
			out.println("<h" + i +">");
			out.println("제목" + i); 
			out.println("</h" + i + ">"); 
			// 응답되는 내용에 줄바꿈 포함 하려면 println 
			// 결과창은 달라지는게 없음.페이지 소스보면 줄바꿈 적용
			// println보다 print가 네트워크 속도가 빠름 -> 네트워크 비용 절약 
			// 따라서 print사용 할 것
			// System.out.println -> 상관없음
		}	
		out.print("</body>");
		out.print("</html>");
		
	}

}
