package com.my.control;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LifeCycleServlet
 */
public class LifeCycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LifeCycleServlet() {
        super();
        System.out.println("LifeCycleServlet의 생성자 호출됨");
//        ServletContext sc = this.getServletContext();
//        String developer = sc.getInitParameter("developer");
//        System.out.println(developer); //이 위치에 선언 시 init이 안 되기 때문에 null값 반환
    }
    // 자동 호출되는 init 메서드와 서블릿 context를 연결해 줌
	public void init(ServletConfig config) throws ServletException { //객체 생성과 동시에 초기화가 필요할 시 init()에 할 것
		super.init(config); //ServletContext 객체를 참조
		System.out.println("LifeCycleServlet의 init() 호출됨");
		ServletContext sc = this.getServletContext();
		String developer = sc.getInitParameter("developer"); // sc 변수를 얻어오는 절차
        System.out.println(developer);
        
        String fileName = this.getInitParameter("fileName");
        System.out.println(fileName);
	}

	public void destroy() {
		System.out.println("LifeCycleServlet의 destroy() 호출됨");
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifeCycleServlet의 service() 호출됨");
		super.service(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifeCycleServlet의 doGet() 호출됨");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifeCycleServlet의 doPost() 호출됨");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		System.out.println("요청전달데이터 id = " + id + ", pwd: = " + pwd);
	}

}
