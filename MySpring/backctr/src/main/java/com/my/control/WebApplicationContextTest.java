package com.my.control;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
//스프링컨테이너(ApplicationContext)구동
@RunWith(SpringRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})

//@WebAppConfiguration is a class-level annotation that is used to declare that the ApplicationContext
@WebAppConfiguration  //여기까지 되어야 WebApplicationContext가 되는 것
public class WebApplicationContextTest {
	@Autowired
	private WebApplicationContext context; 
	
	private  MockMvc mockMvc;// Mock : 모의 객체 - "흉내"내는 "가짜" 모듈 
	// Mock 완전한 MVC객체는 아니지만 스프링 테스트 해 줄 수 있는 가짜 객체
	// 컨트롤러를 대신 해 줌
	
	@Before 
	public void setup(){
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build(); // 스프링 MVC용 가짜객체 만들어짐
	}
	
	@Test
	public void viewProductTest() throws Exception {
		String uri="/viewproduct";
		MockHttpServletRequestBuilder  mockRequestBuilder =
			MockMvcRequestBuilders.get(uri).accept(org.springframework.http.MediaType.APPLICATION_JSON); 
		// ▲ get방식으로 요청하려면 get(uri)로 사용하고, post방식으로 요청하려면 post(uri)로 사용하면 됨
		mockRequestBuilder.param("prod_no", "C0001"); // parameter(요청 전달 데이터) 값의 이름, 값 전달
		
		//응답 정보 : ResultAction에 담겨있음
		ResultActions resultActions = mockMvc.perform(mockRequestBuilder); // 요청 응답 받기
		resultActions.andExpect(MockMvcResultMatchers.status().isOk()); // status() : 응답 상태코드 200번 응답 성공 예상
//		resultActions.andExpect(MockMvcResultMatchers.content().string("welcome")); // content() : 응답 내용
		resultActions.andExpect(jsonPath("status",is(1))); // 응답된 내용을 예상하는데 
														   // json형태로 응답 (json객체의 status 프로퍼티 값 1임을 예상) 예상
	}
	
//	@Test
	public void loginTest() throws Exception {
		String uri="/login";
		MockHttpServletRequestBuilder mockReqBuilder = MockMvcRequestBuilders.get(uri);
		mockMvc.perform(mockReqBuilder);
	}
//	@Test
	public void boardListTest() {
		String uri="/board/list/1";
		MockHttpServletRequestBuilder mockReqBuilder = 
				MockMvcRequestBuilders.get(uri);
		try {
			ResultActions resultAction1 = 
					mockMvc.perform(mockReqBuilder);
			resultAction1.andDo(MockMvcResultHandlers.print());
			resultAction1.andExpect(status().isOk());
		} catch (Exception e) {
			fail(e.getMessage());
		}		
	}
//	@Test
//	public void boardDetailTest() {
//		String uri = "/board/detail/32";
//		MockHttpServletRequestBuilder mockReqBuilder = 
//				MockMvcRequestBuilders.get(uri);
//		try {
//			ResultActions resultAction1 = 
//					mockMvc.perform(mockReqBuilder);
//			resultAction1.andDo(MockMvcResultHandlers.print());
//		} catch (Exception e) {
//			fail(e.getMessage());
//		}	
//	}
}
