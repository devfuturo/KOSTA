package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.sql.MyConnection;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//post방식으로 보내는 것이기 때문에 deGet이 아닌 doPost만 있으면 됨
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청 전달 데이터 얻기
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		// 2. 요청 전달데이터를 이용하여 DB에 해당 데이터 있는지 확인
		// DB와 연결
		Connection con= null;
		// SQL 송신
		PreparedStatement pstmt = null;
		// 송신 결과
		ResultSet rs = null;
		// 응답 결과
		String result = "{\"status\":0}"; //주로 실패인 경우 0값, 성공인 경우 1값 / 따라서 초기값 0으로 초기화
		
		// 세션(클라이언트용 서버쪽 객체) 얻기
		HttpSession session = request.getSession();
		session.removeAttribute("loginInfo");
		try {
			con = MyConnection.getConnection();
			String selectIdNPwdSQL = "SELECT * FROM customer WHERE id=? AND pwd=?"; //최대 1개 행 반환 (id -> pk / pk 행 개수 :1개) 최소 0개 행
			pstmt = con.prepareStatement(selectIdNPwdSQL);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 로그인 성공인 경우 
			// rs.next() : 결과 행 집합을 다음으로 커서 이동시키는 것 
			// 최대 1개 행 반환하기 때문에 반복문 필요 없음 
				result = "{\"status\":1}" ;
				session.setAttribute("loginInfo", id); //attribute는 어떤 정보도 저장할 수 있음
				// 로그인이 성공했을 때만 위 작업 진행
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			//DB와의 연결 닫기
			MyConnection.close(rs, pstmt, con);
		}
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
//		if("id1".equals(id) && "p1".equals(pwd)) {
//			out.print("{\"status\":1}");
//		}else {
//			out.print("{\"status\":2}");
//		}
		out.print(result); 
		//여기까지가 DB와의 연결 부분 추가 한 코드
		
	}

}
