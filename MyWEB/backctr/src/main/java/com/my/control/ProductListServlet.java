package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dto.Product;
import com.my.exception.FindException;
import com.my.repository.ProductOracleRepository;
import com.my.repository.ProductRepository;

public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8"); //charset=utf는 대소문자 구분X
		PrintWriter out = response.getWriter();
		
		//--------------------
		ProductRepository repository = new ProductOracleRepository();
		List<Product> products;
		try {
			products = repository.selectAll();
		} catch (FindException e) {
			e.printStackTrace();
			products = new ArrayList<>();
		}
		// ------------------------
		
//		//DB쪽 자료
////		List<Map<String,Object>> sample = new ArrayList<>();
////		Map<String,Object> map1 = new HashMap<>();
//		
//		//이전에 만들어 둔 product class 재사용
//		List<Product> products = new ArrayList<>(); //com.my.dto의 product class 사용 
//
//		//DB와의 연결, 송신
//		Connection con = null;
//		PreparedStatement pstmt = null;//Statement도 OK
//		ResultSet rs = null; //Select 구문의 반환형
//		String selectProductAllSQL = "SELECT * FROM product ORDER BY prod_no ASC";
//		try {
//			con = MyConnection.getConnection();
//			pstmt = con.prepareStatement(selectProductAllSQL);
//			//pstmt.setString(1, ~) 바인드 변수 없기 때문에 해당 구문 세팅 할 필요 X
//			rs = pstmt.executeQuery();
//			while(rs.next()) { //rs.next() 언제까지 반복할 지 알 수 없음(상품의 수에 따라 최대 행의 수가 달라짐) 행이 없을 때까지 반복
//			//이동한 위치에 더이상 행이 없으면 rs.next() false를 반환함 = true일 때까지만 반복해라
//				String prod_no = rs.getString("prod_no"); // SQL의 VARCHAR2이 JAVA에는 없기 때문에 getString메서드를 사용하여 
//				String prod_name = rs.getString("prod_name"); //컬럼에 대한 값 가지고 오고 String에 넣어주어야함
//				int prod_price = rs.getInt("prod_price");
////				Map<String,Object> map1 = new HashMap<>(); // Map : 가방의 역할 
//				//Map 다목적의 역할 (다목적 가방) - 상품 정보가 들어갈 수도 있고, 고객 정보가 들어갈 수도 있음
//				//다목적이기 때문에 정확하지 않을 수 있음 따라서 명확히 할 때는 Class로 설계할 것 (Product만 들어가기 때문)
////				map1.put("prod_no", prod_no); // 이 자료를 만들어서 map에 넣음 ->  가방의 역할
////				map1.put("prod_name", prod_name);
////				map1.put("prod_price", prod_price);
//				Product p = new Product(prod_no, prod_name,prod_price);
//
////				sample.add(map1);
//				products.add(p); //명시화 된 자료구조 (class Product) 사용 > 코드 간결
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			MyConnection.close(rs,  pstmt, con); 
//		}
//		
////		map1.put("prod_no","C0001");
////		map1.put("prod_name","제주비자림콜드브루");
////		map1.put("prod_price",2000);
////		sample.add(map1);
//
////		Map<String,Object> map2 = new HashMap<>();
////		map2.put("prod_no","C0002");
////		map2.put("prod_name","아메리카노");
////		map2.put("prod_price","1000");
////		sample.add(map2);
////		
////		Map<String,Object> map3 = new HashMap<>();
////		map3.put("prod_no","C0003");
////		map3.put("prod_name","바닐라플랫화이트");
////		map3.put("prod_price","1500");
////		sample.add(map3);
		
		String result ="["; //자바스크립트의 []
//		for(int i = 0; i<sample.size(); i++) {
		for(int i = 0; i<products.size(); i++) {
			if(i>0) {
				result += ",";
			}
//			Map<String,Object> map4 = sample.get(i);
			Product p = products.get(i);
//			map4.get("prod_no");
//			map4.get("prod_name");
//			map4.get("prod_price");

//			result += "{"; //자바스크립트 객체{}
//			result += "\"prod_no\":"; result += "\""+ map4.get("prod_no")+"\""; result += ",";
//			result += "\"prod_name\":"; result += "\""+ map4.get("prod_name")+"\""; result += ",";
//			result += "\"prod_price\":"; result += map4.get("prod_price"); // "2000" 대신
//			result += "}";
		
			result += "{"; //자바스크립트 객체{}
			result += "\"prod_no\":"; result += "\""+ p.getProdNo()+"\""; result += ",";
			result += "\"prod_name\":"; result += "\""+ p.getProdName()+"\""; result += ",";
			result += "\"prod_price\":"; result += p.getProdPrice(); // "2000" 대신
			result += "}";
		
		}
//		result += "{";
//		result += "\"prod_no\":"; result += "\"C0002\""; result += ",";
//		result += "\"prod_name\":"; result += "\"아메리카노\""; result += ",";
//		result += "\"prod_price\":"; result += "1000";  // 숫자타입은 "\\"넣어주지 않아도 됨
//		result += "}";
//		result += ",";
//		
//		result += "{";
//		result += "\"prod_no\":"; result += "\"C00023\""; result += ",";
//		result += "\"prod_name\":"; result += "\"바닐라플랫화이트\""; result += ",";
//		result += "\"prod_price\":"; result += "1500";  // 숫자타입은 "\\"넣어주지 않아도 됨
//		result += "}";
		
		result += "]";
		
		out.print(result);
	}

}
