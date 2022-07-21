import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.my.sql.MyConnection;

public class TransactionTest {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = MyConnection.getConnection();
			con.setAutoCommit(false); // 자동커밋 해제하는 구문 -> 롤백 하기 위해
			
			String insertInfoSQL = "INSERT INTO order_info(order_no, order_id, order_dt) " 
							+ " VALUES (order_seq.NEXTVAL, ? , SYSDATE)";
			String insertLineSQL =
					"INSERT INTO order_line(order_no, order_prod_no, order_quantity) "
					+" VALUES (order_seq.CURRVAL , ? , ?)";
			
			pstmt = con.prepareStatement(insertInfoSQL); //주문 기본 추가 SQL
			pstmt.setString(1, "id1");
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(insertLineSQL); // 주문 상세 추가 SQL
			pstmt.setString(1, "C0001"); // 첫번째 물음표, 들어갈 값
			pstmt.setInt(2, 10);
			pstmt.executeUpdate();

			pstmt.setString(1, "X"); // 없는 상품추가
			pstmt.setInt(2, 10);
			pstmt.executeUpdate(); // 예외 예상
			con.commit(); // 트랜잭션 완료 / 정상 출력되어 끝까지 갔다면 커밋 호출
			
		} catch (SQLException e) {
			if(con != null) {
				try {
					con.rollback(); // 트랜잭션 취소
				} catch (SQLException e1) {
				}
			}
			e.printStackTrace();
		}
		
	}

}

// INSERT 되기 전에 NEXTVAL 실행됨 sequence의 값은 실행한 수만큼 증가됨 (실패해도 증가됨)
// 기억할 것..
//	con.setAutoCommit() 
//	con.commit()
//	con.rollback()
