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

import com.my.dto.Product;
import com.my.sql.MyConnection;

public class IdDupChkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product sample =(Product)request.getAttribute("test"); //Product타입으로 강제 형변환
		System.out.println(sample);
		
		String id = request.getParameter("id");
		
		//status와 msg 둘 다 응답해야함
		String result = "{\"status\":0, \"msg\": \"이미 사용중인 아이디입니다.\"}";
		
		//DB와 연결
		Connection con = null;
		
		//SQL송신
		PreparedStatement pstmt = null;
		ResultSet rs = null; //검색
		
		String selectIdDupChkSQL = "SELECT * FROM customer WHERE id = ?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectIdDupChkSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(!rs.next()) { //다음 행으로 커서 이동했는데 다음 행이 있더라 (true)
				result = "{\"status\":1, \"msg\": \"사용 가능한 아이디입니다.\"}";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			String msg = e.getMessage();
			result = "{\"status\":0, \"msg\": \""+msg+"\"}";
		} finally {
			MyConnection.close(rs, pstmt,con);
			
		}
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}

}
