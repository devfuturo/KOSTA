package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.sql.MyConnection;

public class ProductOracleRepository implements ProductRepository {

	public ProductOracleRepository() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Product product) throws AddException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Product> selectAll() throws FindException {
		//DB쪽 자료
		//	List<Map<String,Object>> sample = new ArrayList<>();
		//	Map<String,Object> map1 = new HashMap<>();

		//이전에 만들어 둔 product class 재사용
		List<Product> products = new ArrayList<>(); //com.my.dto의 product class 사용 

		//DB와의 연결, 송신
		Connection con = null;
		PreparedStatement pstmt = null;//Statement도 OK
		ResultSet rs = null; //Select 구문의 반환형
		String selectProductAllSQL = "SELECT * FROM product ORDER BY prod_no ASC";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectProductAllSQL);
			rs = pstmt.executeQuery();
			while(rs.next()) { //rs.next() 언제까지 반복할 지 알 수 없음(상품의 수에 따라 최대 행의 수가 달라짐) 행이 없을 때까지 반복
				//이동한 위치에 더이상 행이 없으면 rs.next() false를 반환함 = true일 때까지만 반복해라
				String prod_no = rs.getString("prod_no"); // SQL의 VARCHAR2이 JAVA에는 없기 때문에 getString메서드를 사용하여 
				String prod_name = rs.getString("prod_name"); //컬럼에 대한 값 가지고 오고 String에 넣어주어야함
				int prod_price = rs.getInt("prod_price");
				//	Map<String,Object> map1 = new HashMap<>(); // Map : 가방의 역할 
				//	map1.put("prod_no", prod_no); // 이 자료를 만들어서 map에 넣음 ->  가방의 역할
				//	map1.put("prod_name", prod_name);
				//	map1.put("prod_price", prod_price);
				Product p = new Product(prod_no, prod_name,prod_price);

				//			sample.add(map1);
				products.add(p); //명시화 된 자료구조 (class Product) 사용 > 코드 간결
			}
			if(products.size() == 0) {
				throw new FindException("상품이 없습니다");
			}
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(rs,  pstmt, con); 
		}
	}

	@Override
	public Product selectByProdNo(String prodNo) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //검색 결과에 대한 ResultSet
		String selectProdNo = "SELECT * FROM product WHERE prod_no=?" ;// 최대 1개 행을 검색 해 오는
		
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectProdNo);
			pstmt.setString(1, prodNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String prodName = rs.getString("prod_name"); 
				int prodPrice = rs.getInt("prod_price"); 
				String prodInfo = rs.getString("prod_info"); 
				java.sql.Date prodMfd = rs.getDate("PROD_MFD"); // 컬럼명 대소문자 관계 X 
				
				Product p = new Product(prodNo, prodName, prodPrice, prodInfo, prodMfd);
				return p ;
			} else {
				throw new FindException("상품이 없습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public List<Product> selectByProdNoOrProdName(String word) throws FindException {
		return null;
	}

}
