package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class JDBCTest {
	public static void search(){
		//1. JDBC 드라이버 설치
		//2. JDBC 드라이버 클래스로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return; // or System.exit?!
		}
		//3. DB와 연결
		Connection con = null ;
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
		String user = "hr" ;
		String password = "hr";
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		//4. SQL문 송신 	(ex. SELECT , 	INSERT/UPDATE/DELETE , CREATE/ALTER/DROP)
		Statement stmt = null; 
		//					(executeQuery(),	executeUpdate() ,					)
		//5. 4번의 결과 수신 (ex. 행들	, 		처리 건수	   , 		0			)
		//					(ResultSet  ,		int			  ,						)
		//ex. ResultSet rs = stmt.executeQuert("SELECT~"); // 반환 값이 행들
		//		int rowcnt = stmt.executeUpdate("INSERT~"); // 반환 값이 처리 건수
		//		int rowcnt = stmt.executeUpdate("CREATE~"); // 반환값이 무조건 0
		ResultSet rs = null; // 변수 사용을 위해 변수 선언 위치를 위로 올림

		try {
			stmt = con.createStatement();
			String selectSQL = "SELECT employee_id, first_name, salary, hire_date FROM employees" ;// java에서 sql 구문 송신할 경우 employees 뒤에 세미콜론 찍어서 보내면 안됨 
			rs = stmt.executeQuery(selectSQL); //executeQuery()를 사용 해 주어야 JAVA쪽으로 송신 가능 
			SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd hh:mm:ss"); //simpledateformat으로 자료형 깔끔하게 볼 수 있도록

			//6. 결과 활용
			while(rs.next()) { //커서를 다음 행(row)으로 이동 시켜주는 메서드(반복한다)
				int id = rs.getInt("employee_id"); //= rs.getint(1) java인덱스의 0번
				// sql의 number타입 가지고 오려면 java의 int로 가지고 와야함

				String name = rs.getString("first_name"); //= rs.getint(1) java인덱스의 1번
				// sql의 varchar타입 가지고 오려면 java에서는 String
				// getChar가 없기 때문에 모두 getString으로 받기

				int sal = rs.getInt("salary");
				java.sql.Date hdt = rs.getDate("hire_date");
				System.out.println(id + ":" + name + ":" + sal + ":" +sdf.format(hdt));

			}; //rs.next()는 이동한 위치에 값이 없으면 false를 반환함

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
		//7. DB 연결해제
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt !=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		}
		// resultSet close시에 문제가 생기면 catch가서 처리하고 그 다음 진행으로 statement를 close하러 가야함
		// try 위에 한번 쓰고 아랫쪽에 catch 한번 쓰고 try 안에 rs,stmt,con close 다 넣게되면 rs.close하다가 문제 생기면
		// catch로 잡고 stmt진행 없이 바로 끝나버림. 따라서 각각 다 따로 close를 해 주어야함

	}

	public static void add () {
		//1. JDBC 드라이버 설치
		//2. JDBC 드라이버 클래스로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return; // or System.exit?!
		}
		//3. DB와 연결
		Connection con = null ;
		String url = "jdbc:oracle:thin:@loc`alhost:1521:xe"; 
		String user = "hr" ;
		String password = "hr";
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		//4. SQL문 송신 	(ex. SELECT , 	INSERT/UPDATE/DELETE , CREATE/ALTER/DROP)
		Statement stmt = null; 
		//					(executeQuery(),	executeUpdate() ,					)
		//5. 4번의 결과 수신 (ex. 행들	, 		처리 건수	   , 		0			)
		//					(ResultSet  ,		int			  ,						)
		try {
			stmt = con.createStatement();
			String insertSQL = "INSERT INTO customer(id, pwd, name, status) \r\n"
					+ "VALUES ('id9', 'p9', 'n9', 1)";
			int rowcnt = stmt.executeUpdate(insertSQL);
			System.out.println(rowcnt+"건이 추가되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(stmt !=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	public static void main(String[] args) {
		add();
	}

}

//	executeQuery() 대표적인 검색 메서드
//	executeUpdate() DDL,DML 처리를 하기 위한 메서드
