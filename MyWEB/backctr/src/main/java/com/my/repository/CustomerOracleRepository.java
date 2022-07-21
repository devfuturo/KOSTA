package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.my.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.sql.MyConnection;

public class CustomerOracleRepository implements CustomerRepository {

	//	selectByIdAndPwd() 오버라이딩
//	@Override
//	public Customer selectByIdAndPwd(String id, String pwd) throws FindException {
//		// DB와 연결
//		Connection con= null;
//		// SQL 송신
//		PreparedStatement pstmt = null;
//		// 송신 결과
//		ResultSet rs = null;
//		try {
//			con = MyConnection.getConnection();
//			String selectIdNPwdSQL = "SELECT * FROM customer WHERE id=? AND pwd=?"; //최대 1개 행 반환 (id -> pk / pk 행 개수 :1개) 최소 0개 행
//			pstmt = con.prepareStatement(selectIdNPwdSQL);
//			pstmt.setString(1, id);
//			pstmt.setString(2, pwd);
//			rs = pstmt.executeQuery();
//
//			if(rs.next()) { // 로그인 성공인 경우 
//				return new Customer(rs.getString("id"),
//						rs.getString("pwd"),
//						rs.getString("name"),
//						rs.getString("address"),
//						rs.getInt("status"),
//						rs.getString("buildingno")
//						);
//			}
//			throw new FindException("고객이 없습니다");
//		}catch (Exception e) {
//			throw new FindException(e.getMessage());
//		} finally {
//			MyConnection.close(rs, pstmt, con);
//		}
//
//	}

	//	insert() 오버라이딩
	@Override
	public void insert(Customer customer) throws AddException {
		// DB와 연결
		Connection con= null;
		// SQL 송신
		PreparedStatement pstmt = null;

		String insertSQL = "INSERT INTO customer(id,pwd,name, address, status, buildingno) VALUES (?,?,?,?,1,?)";

		try {
			con = MyConnection.getConnection(); //com.my.sql 패키지의 MyConnection과 연결
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, customer.getId());
			pstmt.setString(2, customer.getPwd());
			pstmt.setString(3, customer.getName());
			pstmt.setString(4, customer.getAddress());
			pstmt.setString(5, customer.getBuildingno());
			pstmt.executeUpdate(); //DB로 위에 입력된 값을 넘겨주고 DB에서 결과값을 보내줌 -> 결과값 보내주는 함수 executeUpdate()
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			// DB연결 닫기
			MyConnection.close(pstmt, con);
		}
	}


	//selectById() 오버라이딩
	@Override
	public Customer selectById(String id) throws FindException {
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
			if(rs.next()) { // id에 만족하는 고객이 있을 경우
				return new Customer( 
						rs.getString("id"),
						rs.getString("pwd"),
						rs.getString("name"),
						rs.getInt("address"),
						rs.getString("status"),
						rs.getString("buildingno")			 
						);
			}
			throw new FindException("고객이 없습니다"); 
			// ▲ 재사용성을 위해 repository 쪽에 메세지 구체적으로 적는 것 좋지 않음
			// iddupchk 외에도 myinfo 조회 등에서도 이 메서드 사용 가능함
			// controller쪽에서 메세지 구체화, 세분화하여 작성
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt,con);
		}

	}
}