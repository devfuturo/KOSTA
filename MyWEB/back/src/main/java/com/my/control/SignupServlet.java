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

import com.my.sql.MyConnection;

public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id"); // signup.html의 아이디 name=id와 연결됨
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		String buildingno = request.getParameter("buildingno");
		// -------------------
		String result = "{\"status\":0, \"msg\": \"가입실패\"}";
		// 술꾼 한미래 왈 : 술 없으면 인생에 낙이 없다 .. 
		//메모...
		//DB와의 연결
		Connection con = null;
	
		//SQL 송신
		PreparedStatement pstmt = null;
		int rs = 0;
		
		try {
			con = MyConnection.getConnection(); //com.my.sql 패키지의 MyConnection과 연결
			String insertSQL = "INSERT INTO customer(id,pwd,name, address, status, buildingno) VALUES (?,?,?,?,1,?)";
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, addr);
//			pstmt.setString(5, "1");  // 지정된 값은 인덱스에 추가하면X. 부적합한 열 인덱스 오류
			pstmt.setString(5, buildingno);
			rs= pstmt.executeUpdate(); //DB로 위에 입력된 값을 넘겨주고 DB에서 결과값을 보내줌 -> 결과값 보내주는 함수 executeUpdate();
			//그 값을 rs에 대입
			if(rs == 1) { //rs 가 true일 때. (rs == 0이면 rs가 false일 때)
				result = "{\"status\":1, \"msg\": \"가입성공\"}";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB연결 닫기
			MyConnection.close(pstmt, con);
		}
		
		// -------------------
		
//		System.out.println(id+":"+pwd+":"+name+":"+addr+":"+buildingno);
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
//		out.print("{\"status\":1, \"msg\": \"가입성공\"}");
		out.print(result);
		
	}
}
