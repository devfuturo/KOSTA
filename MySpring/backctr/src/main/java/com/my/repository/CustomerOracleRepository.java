package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.my.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.sql.MyConnection;
@Repository(value= "customerRepository")
public class CustomerOracleRepository implements CustomerRepository {
	@Autowired
	@Qualifier(value = "dataSource") // 동일 자료형인 경우 Qulifier로 구분한다
	private DataSource ds;

	@Autowired
	private SqlSessionFactory sessionFactory;


	//	insert() 오버라이딩
	@Override
	public void insert(Customer customer) throws AddException {
		// DB와 연결
		Connection con= null;
		// SQL 송신
		PreparedStatement pstmt = null;

		String insertSQL = "INSERT INTO customer(id,pwd,name, address, status, buildingno) VALUES (?,?,?,?,1,?)";

		//

		try {
			//			con = MyConnection.getConnection(); //com.my.sql 패키지의 MyConnection과 연결
			con = ds.getConnection();
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
		SqlSession session = null;
		try {
			session = sessionFactory.openSession(); //	여기에서의 Session은 Connection과 같은 의미
			//	Http에서 받아오는 세션과는 다름
			Customer c = session.selectOne("com.my.mapper.CustomerMapper.selectById", id); 
			//	첫번째 인자 문자열, 두번째 인자 sql구문에 전달 될 parameter값
			//	첫번째 인자 : customerMapper의 namespace(com.my.mapper.CustomerMappe).select의 id속성(selectById)
			//	첫번째 인자의 mapper class 찾아가고 그 id속성으로 들어간 다음
			//	두번째 인자의 id값이 CustomerMapper의 Where절의 #{id}로 감 (전달값)
			//	SQL 구문 -> Oracle로 감 -> 결과 받아옴 -> Customer 객체 형태로 반환 -> c변수에 넣어 반환
			//	selectOne 메서드는 rs.next()로 리턴받는 것까지 함

			if(c == null) {
				throw new FindException("고객이 없습니다"); 
			}
			return c ;
		}catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close(); //	DB와의 연결을 끊겠다는 것이 아님. Connection pool(hikari CP)에다가 돌려준다
				//	Connection 사용하고나면 늘 close 했어야하는데 스프링에서는 Session Close 하지 않아도 됨
			}
		}
	}
}