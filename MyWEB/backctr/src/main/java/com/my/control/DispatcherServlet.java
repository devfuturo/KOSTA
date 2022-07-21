package com.my.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		System.out.println(servletPath);
//		if("/login".equals(servletPath)) {
//			Controller control = new CustomerController();
//			control.execute(request,response);
//		}else if("/signup".equals(servletPath)) {
//			Controller control = new CustomerController();
//			control.execute(request,response);
//		}else if("/productlist".equals(servletPath)) {
//		}
		
		// 지금 실행 중인 웹 프로젝트의 실제 경로를 찾는다
		ServletContext sc = getServletContext();
		String envPath = sc.getRealPath("my.properties"); // my.properties가 톰캣에서 실행될 때의 실제 경로
		System.out.println(envPath);
		
		// my.properties 파일의 내용을 프로퍼티 이름과 값으로 JVM에 로드
		Properties env = new Properties();
		env.load(new FileInputStream(envPath));
		
		//로드된  properties들을 찾아보는데 servletpath과 연결된 class이름을 찾음(ex. /login)
		String clazzName = env.getProperty(servletPath); //key : /login , value : com.my.control.CustomerController
	 // JVM에 클래스파일(CustomerController.java) JVM에 로드
		Controller control = null;
		String result = null; //result 변수가 밖에 선언되어 있어야 밑에서 out.print(result)로 응답(출력)할 수 있음
		
		try {
			//refactoring -  요청할 url에 해당하는 contoller을 찾아서 execute메서드 호출
			Class clazz = Class.forName(clazzName);
			control = (Controller)clazz.newInstance(); // 로드된 프로퍼티스 파일에 로드되어있는 클래스 이름의 객체 생성
			//Controller타입으로 다운 캐스팅
			result = control.execute(request, response); // controller가 보낸 결과값 응답?! controller의 execute메서드 호출

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		// execute()로 나온 결과값 클라이언트에게 응답
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result); 
	}
}
