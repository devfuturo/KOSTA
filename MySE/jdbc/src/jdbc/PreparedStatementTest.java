package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.my.sql.MyConnection;

public class PreparedStatementTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Connection con = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null;
		try {
			con = MyConnection.getConnection();
//			stmt = con.createStatement();
			String insertSQL = "INSERT INTO customer(id, pwd, name, status) "
							 	+ "VALUES(?, ?, ?, ?)"; // 값의 위치에 ???로 작성. 값의 위치에서만 ? 사용 가능
			// ? -> 바인드 변수
			pstmt = con.prepareStatement(insertSQL);
			
			System.out.print("추가할 아이디를 입력하세요 : ");
			String id = sc.nextLine();

			System.out.print("추가할 비밀번호를 입력하세요 : ");
			String pwd = sc.nextLine();
			
			System.out.print("추가할 이름을 입력하세요 : ");
			String name = sc.nextLine();
			
			System.out.print("일반고객이면 1, 기업고객이면 2 :");
			int status = sc.nextInt();
			
//			String insertSQL = "INSERT INTO customer(id, pwd, name) "
//								+ "VALUES ('"+ id +"','"+ pwd +"' , '"+ name +"' )"; // 오라클 문법''. 변수와 이어주기 위해 ' '
//			stmt.executeUpdate(insertSQL);
			pstmt.setString(1, id);;
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setInt(4, status); // ? 에 대입한다. ? 매서드만큼setter 메서드로 채워주고 excuteUpdate하면 입력받은 값이 나옴
			pstmt.executeUpdate();
			System.out.println("고객 등록 완료");

		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}finally {
			MyConnection.close(pstmt, con);
		}
	}

	// Statement보다 preparedStatement (Statement 상속 라이브러리) 추천
}
