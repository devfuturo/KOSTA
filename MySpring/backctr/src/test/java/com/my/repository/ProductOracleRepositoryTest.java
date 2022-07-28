package com.my.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.my.dto.Product;
import com.my.exception.FindException;
// 스프링 컨테이너 (ApplicationContext) 구동하기 위한 (스프링컨테이너를 시작시키기 위한) 어노테이션
@RunWith(SpringRunner.class)

//Spring 컨테이너용 XML파일 설정
@ContextConfiguration(locations={ // 스프링 컨테이너 시작하기 위한 설정 파일
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})

public class ProductOracleRepositoryTest {
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired // 멤버변수 자동 주입되도록
	private ProductOracleRepository repository;
	
	@Test
	public void testSelectByProdNo() throws FindException {
		String prodNo ="C0001";
		String expectedProdName = "제주비자림콜드브루";
		int expectedProdPrice = 4000;
		Product p = repository.selectByProdNo(prodNo);
		
		// logger.debug(expectedProdName.equals(p.getProdName())); //true, false
		// assert -> 단정짓는다. 확신한다
		// 단정지은 것이 true가 되면 test 통과
		assertEquals(expectedProdName, p.getProdName()); // 예상된 이름이랑 실제로 찾아온 값(이름)과 같다고 단정지음
		//assertEquals(expectedProdPrice, p.getProdPrice());
		assertTrue(expectedProdPrice == p.getProdPrice()); // 위 구문과 같은 것을 확인함
		assertNotNull(p); // p 객체는 null이 아닐거야 하고 단정
	}

	@Test
	public void testSelectAll() throws FindException {
		int expectedSize = 4; // DB에 저장 되어있는 상품의 수
		List<Product> list = repository.selectAll();
		assertTrue(expectedSize == list.size());
	}
}
