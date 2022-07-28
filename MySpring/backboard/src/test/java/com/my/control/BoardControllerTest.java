package com.my.control;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class) // 스프링컨테이너 구동 : ApplicationContext타입의 컨테이너
@ContextConfiguration(locations = 
	{"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) 
// ▲ 설정파일 여러개로 쪼개져 있는 경우 , 이용하여 배열 형태로 주면 됨	
@WebAppConfiguration // WebApplicationContext타입의 컨테이너가 되도록 설정 하나 더 해 주어야 함
public class BoardControllerTest {
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc; // 컨트롤러 테스트를 해 줄 가짜(대리) 객체

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testList() throws Exception {
		// 게시글 페이지별 목록 테스트
		
		// 2 페이지 목록에 대한 단위테스트(데이터 1~ 7번까지 있다는 가정)
		String uri = "/boardlist"; // http://localhost:8888/backboard/boardlist
		String currentPage = "2"; // 글번호 목록 4 6 2
		MockHttpServletRequestBuilder mockRequestBuilder =
				MockMvcRequestBuilders.get(uri).accept(org.springframework.http.MediaType.APPLICATION_JSON); 
		// accept("application/json") 사용 가능 
		// 미디어 타입의 상수를 쓰는 것 -> 오타 났을 시 표준화 된 마임값이 아닌 응답 형식을 사용하여서 오류가 남
		mockRequestBuilder.param("currentPage", currentPage); // 요청 전달데이터 무조건 String 타입이기 때문에 String 으로 보내야 함
		//응답정보: ResultAction
		ResultActions resultActions = mockMvc.perform(mockRequestBuilder); //요청

		// 응답 결과 어떨지 예상
		// 응답 정보 : ResultAction
		resultActions.andExpect(MockMvcResultMatchers.status().isOk()); //응답상태코드200번 성공 예상
		// -- 여기까지 -- 정상 요청 응답 되었는지 확인
		resultActions.andExpect(jsonPath("status",is(1))); // json객체의 status프로퍼티값이 1 예상
	
		org.hamcrest.Matcher<Integer> matcher;
		JsonPathResultMatchers jsonPathMatcher; 
		ResultMatcher resultMatcher;
		// status 존재하는지에 대한 테스트
		jsonPathMatcher = jsonPath("status"); // 응답된 json문자열을 쉽게 찾아갈 수 있는 것
		resultMatcher = jsonPathMatcher.exists(); // exists() -> 위의 status라는 property가 있는가 묻는 것
		resultActions.andExpect(resultMatcher); // 우리가 예상한 exists결과가 true로 예상함 (property가 존재함)
		
		// status 값이 1인가에 대한 테스트
		int expectedStatus = 1 ;
		matcher = org.hamcrest.CoreMatchers.is(expectedStatus); // is함수 -> 그 status값이 얼마인가 비교하고 있음		
		resultMatcher = jsonPath("status", matcher); // jsonpath의 두번째 인자로 is함수의 matcher 사용 (1값과 맞는가)
		resultActions.andExpect(resultMatcher); //  status 값이 1인 것을 예상

		//json객체의 "t"프로퍼티의 자료형은PageBean이고 PageBean의 "list"프로퍼티 자료형은 List이다
		jsonPathMatcher = jsonPath("t.list"); // json property의 t객체 찾고 t안의 list,startPage,endPage property도 찾고?! 
		// . 을 이용하여 t의 하위 객체 찾음
		resultMatcher = jsonPathMatcher.exists();
		resultActions.andExpect(resultMatcher);

		//게시글목록의 크기 t.list.length()
		int expectedListSize = 3;
		//	list 의 각 객체 {} 는 Board타입
		matcher = org.hamcrest.CoreMatchers.is(expectedListSize);		
		resultMatcher = jsonPath("t.list.length()", matcher); //t.list의 length가 몇개인가		
		//t요소의 하위 property 찾아가서 list의 갯수가 3이 맞는가 테스트
		resultActions.andExpect(resultMatcher);

		//게시글목록의 첫번째요소 t.list[0].boardNo
		int expectedBoardNo = 4;
		matcher = org.hamcrest.CoreMatchers.is(expectedBoardNo);		
		resultMatcher = jsonPath("t.list[0].boardNo", matcher);
		//t가 갖고 있는 property의 list요소 중 0번째 인덱스 Board타입의 객체?! 첫번째 게시글의 번호가 4번이라고 예상
		resultActions.andExpect(resultMatcher);

		//게시글페이지그룹의 시작페이지값 t.startPage
		int expectedStartPage = 1;
		matcher = org.hamcrest.CoreMatchers.is(expectedStartPage);
		resultMatcher = jsonPath("t.startPage", matcher); // t가 가지고 있는 property중에 startPage의 값이 1페이지인가
		resultActions.andExpect(resultMatcher); // 매칭될 것을 예상
	}

	
	@Test
	public void testSearch() throws Exception {
		String uri = "/searchboard";
		MockHttpServletRequestBuilder mockRequestBuilder =
				MockMvcRequestBuilders.get(uri).accept(org.springframework.http.MediaType.APPLICATION_JSON);
		mockRequestBuilder.param("word", "번글");
		mockRequestBuilder.param("currentPage", "1");

		ResultActions resultActions = mockMvc.perform(mockRequestBuilder); //요청
		// 응답 결과 어떨지 예상
		resultActions.andExpect(MockMvcResultMatchers.status().isOk()); //응답상태코드200번 성공
		resultActions.andExpect(jsonPath("status",is(1)));

	}
	
	

}
