package com.my.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.exception.FindException;

@Repository(value= "productRepository")
public class ProductOracleRepository implements ProductRepository {

	//	oracleRepository에는 반드시 DataSource가 있어야함
	//	repository가 DataSource 객체를 직접 만드느 것이 아님 외부로부터 주입받아 의존관계 완성
	@Autowired // 알아서 데이터 타입의 객체를 찾아 자동 주입(생성자, Setter/Getter 메서드 만들어 주지 않아도 됨)
	private SqlSessionFactory sqlSessionFactory;
	//	@Qualifier(value = "dataSource")
	//	private DataSource ds; //javax.sql의 DataSource. 
	//	public ProductOracleRepository() {} // 매개변수 없는 생성자
	//	public ProductOracleRepository(DataSource ds) { // DataSource 외부에서 주입받을 수 있도록 생성자 
	//		this.ds = ds;
	//	}
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void insert(Product product) throws AddException {

	}

	@Override
	public List<Product> selectAll() throws FindException {	// List타입으로 반환
		//DB쪽 자료
		//	List<Map<String,Object>> sample = new ArrayList<>();
		//	Map<String,Object> map1 = new HashMap<>();

		//이전에 만들어 둔 product class 재사용
		List<Product> products = new ArrayList<>(); //com.my.dto의 product class 사용 
		SqlSession session = null;

		try {
			session= sqlSessionFactory.openSession();
			products = session.selectList("com.my.mapper.ProductMapper.selectAll") ; //parameter값이 없기 때문에 두번째 인자 없음
			// 여러 행 검색 해 와야기 때문에 selectList() 사용 selectOne() X

			if(products.size() == 0) {
				throw new FindException("상품이 없습니다");
			}
			return products;
		}catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public Product selectByProdNo(String prodNo) throws FindException {
		SqlSession session = null;

		try {
			// System.out.println("SYSOUT : prodNo in productoraclerepository selectByProdNo:" + prodNo);
			// sysout 대신 사용
			logger.debug("debug prodNo:" + prodNo);
			logger.info("info prodNo:" + prodNo);
			logger.warn("warn prodNo:" + prodNo);
			logger.error("error prodNo:" + prodNo);
			session = sqlSessionFactory.openSession();
			Product p = session.selectOne("com.my.mapper.ProductMapper.selectByProdNo", prodNo);
			if(p == null) {
				throw new FindException ("상품이 없습니다");
			}
			return p;
		}catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<Product> selectByProdNoOrProdName(String word) throws FindException {
		//여러 값 반환되어야하기 때문에 List 타입
		List<Product> products = new ArrayList<>(); //com.my.dto의 product class 사용 
		SqlSession session = null;

		try {
			session= sqlSessionFactory.openSession();
			HashMap<String, String> hashMap = new HashMap<>();
			hashMap.put("word", word);
			hashMap.put("order","prod_name DESC");
			products = session.selectList("com.my.mapper.ProductMapper.selectByProdNoOrProdName", 
//											"%"+word+"%"); //ProductMapper에 있는 parameter의 name값?! // ProductMapper의 #{word}에 "%"+word+"% 이 형태로 전달됨
//											word); 
											hashMap);
			return products;
		}catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
}




