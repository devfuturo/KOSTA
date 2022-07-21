package com.my.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.OrderInfo;
import com.my.dto.OrderLine;
import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.sql.MyConnection;

public class OrderOracleRepository implements OrderRepository {

	@Override
	public void insert(OrderInfo info) throws AddException {
		Connection con = null;
		try {
			con = MyConnection.getConnection();
			insertInfo(con, info); 
			insertLines(con, info.getLines()); // 같은 커넥션(세션-DB와의 커넥션. 세션트래킹의 세션X) 내에서
			//같은 DB연결 내에서 두 메서드(insertInfo, insertLines) 사용
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(null, con);
		}
	}
	private void insertInfo(Connection con, OrderInfo info) throws SQLException{
		PreparedStatement pstmt = null;
		String insertInfoSQL = 
				"INSERT INTO order_info(ORDER_NO,ORDER_ID,ORDER_DT) VALUES (order_seq.NEXTVAL, ?, SYSDATE)"; // 시퀀스 사용(일련번호)
		//같은 세션 내에서는 NEXTVAL을 CURRENT VAL 할 수 있음?!
		pstmt = con.prepareStatement(insertInfoSQL);
		pstmt.setString(1, info.getOrderId()); 
		// ▲ 재사용성을 높이기 위해서 (1, session.getAttribute("loginId")와 같이 웹과 연관된 내용 쓰지 않는 것이 좋음
		pstmt.executeUpdate();
	}
	private void insertLines(Connection con, List<OrderLine> lines) throws SQLException{
		PreparedStatement pstmt = null;
		String insertLineSQL = 
				"INSERT INTO order_line(ORDER_NO, ORDER_PROD_NO,ORDER_QUANTITY) VALUES (order_seq.CURRVAL, ?, ?)"; 
		pstmt = con.prepareStatement(insertLineSQL);
		for(OrderLine line: lines) {
			String prodNo = line.getOrderP().getProdNo();
			int orderQuantity = line.getOrderQuantity();
			pstmt.setString(1, prodNo);
			pstmt.setInt(2, orderQuantity);
			pstmt.addBatch(); //반복 돌 때마다 Batch에다가만 일괄 추가(저장)
		}
		pstmt.executeBatch(); // 반복이 끝나면 일괄처리 
	}
 	@Override
	public List<OrderInfo> selectById(String orderId) throws FindException {
 		// 주문 내역은 꺼내 오는 것이기 때문에 DB와 연결을 해서 꺼내와야함
 		// insert는 넣어주는 값이기 때문에 DB와 연결 할 필요가 X
		// repository에선 웹과 관련된 일 절대 하지X
 		// 로그인 된 orderId를 가지고 와서 orderInfo(주문자가 주문한 내용만 반환)를 List에 넣어서 
 		// 반환된 내용 서블릿이 받아와서 서블릿이 json문자열로 만들어서 응답
 		// 최근 주문번호부터 내림차순, 같은 주문번호라면 상품 번호로 오름차순하여 정렬
 		// select 구문 만들어져야하고 
// 		
 		Connection con = null;
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		String viewOrderInfoSQL = 
 				"SELECT * FROM ORDER_LINE, ORDER_INFO, PRODUCT "
 				+ "WHERE ORDER_LINE.ORDER_NO = ORDER_INFO.ORDER_NO";
// 				+ "ORDER BY ORDER_DT DESC, PROD_NAME";

 		// 		String viewOrderInfoSQL = 
// 				"SELECT I.ORDER_NO, I.ORDER_ID, I.ORDER_DT, L.ORDER_NO,P.PROD_NO,P.PROD_NAME,P.PROD_PRICE,L.ORDER_QUANTITY "
// 				+ "FROM ORDER_LINE L JOIN ORDER_INFO I ON (L.ORDER_NO = I.ORDER_NO)"
// 				+ "JOIN PRODUCT P ON (P.PROD_NO = L.ORDER_PROD_NO)";
//				+ "ORDER BY 3 DESC, 5";
 		
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(viewOrderInfoSQL);
			rs = pstmt.executeQuery(); // rs라는 변수에 주문 내역을 할당
			// 여기까지 DB에서 주문 내역을 꺼내 오는 것
			
			// 값이 한줄한줄 들어가는 것
			List<OrderInfo> orderInfo = new ArrayList<>();
			while(rs.next()) {
				int orderNo = rs.getInt("order_no"); 
				String order_id = rs.getString("order_id"); 
				Date orderDt = rs.getDate("order_dt");
				String orderProdNo = rs.getString("order_prod_no");
				int orderQuantity = rs.getInt("order_quantity");
				String prodName = rs.getString("prod_name");
				int prodPrice = rs.getInt("prod_price");
				
				Product orderP = new Product(orderProdNo, prodName, prodPrice); //생성자 선언
				
				OrderLine orderLine = new OrderLine(orderNo, orderP, orderQuantity);
				
				List<OrderLine> lines = new ArrayList<>();
				lines.add(orderLine);
							
				OrderInfo info = new OrderInfo(orderNo, orderId, orderDt, lines);
				info.setOrderNo(orderNo);
				info.setOrderId(orderId);
				info.setOrderDt(orderDt);
				info.setLines(lines);
				
				System.out.println("---------------");
			}		
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			MyConnection.close(rs,  pstmt, con);
		}
		
		}
 	} 
}
