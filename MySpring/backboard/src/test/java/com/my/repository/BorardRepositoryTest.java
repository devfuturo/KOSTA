package com.my.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.my.dto.Board;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
// 스프링 컨테이너 (ApplicationContext) 구동하기 위한 (스프링컨테이너를 시작시키기 위한) 어노테이션
@RunWith(SpringRunner.class) 

//Spring 컨테이너용 XML파일 설정
@ContextConfiguration(locations= { // 스프링 컨테이너 시작하기 위한 설정 파일
			"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class BorardRepositoryTest {
	@Autowired // 멤버변수 자동 주입되도록
	private BoardRepository repository;
	
	@Test
	public void testSelectByPage() throws FindException {
		int currentPage = 1; // sample 행 수 : 7건, 1페이지 3건, 2페이지 3건, 3페이지는 1건
		int cntPerPage = 3;
		int expectedSize = 3;
		int []expectedBoardNoArr = {3, 7, 1};
		List<Board> list = repository.selectByPage(currentPage,cntPerPage);
		assertNotNull(list);
		assertEquals(expectedSize, list.size());
		for(int i=0 ;i<list.size(); i++) {
			assertEquals(list.get(i).getBoardNo(),expectedBoardNoArr[i]);
		}
	}
	@Test
	public void testSelectCount() throws FindException {
		int expectedCnt = 7;
		int cnt = repository.selectCount();
		assertEquals(expectedCnt, cnt);
		
		expectedCnt = 3;
		cnt = repository.selectCount("1번");
		assertEquals(expectedCnt, cnt);
		
	}
	
	@Test
	public void testSelectByWord() throws FindException {
		int expectedSize = 1; // 검색어 "1번"의 총 행수는 3행이다. 1페이지별 2건씩 보면서 2페이지의 행수를 예상
		String word = "1번";
		int currentPage = 2;
		int cntPerPage =2;
		List<Board> list = repository.selectByWord(word, currentPage, cntPerPage);
		assertEquals(expectedSize, list.size());
	}
	
	@Test 
	public void testSelectByBoardNo() throws FindException{
		String expectedBoardTitle = "1번글"; 
		String expectedBoardId = "id1";
		
		int boardNo = 1;
		Board board= repository.selectByBoardNo(boardNo);
		
		assertNotNull(board);
		assertEquals(expectedBoardTitle, board.getBoardTitle());
		assertEquals(expectedBoardId, board.getBoardId());
	}

	@Test
	public void testUpdate() throws ModifyException, FindException{
		//조회수 1증가 테스트
		int boardNo = 1; //1번 글
		
		Board b1 = repository.selectByBoardNo(boardNo); // 글 검색
		assertNotNull(b1);
		int expectedViewcount = b1.getBoardViewcount()+1; // 예상 조회수 : 조회수 1 증가 전의 글 조회수 +1 
		
		Board b = new Board();
		b.setBoardNo(boardNo);
		b.setBoardViewcount(-1);
		repository.update(b); // 조회수 1 증가 작업
		
		// 업데이트 되었는지 확인 하기 위하여 b2를 가지고 오고
		Board b2 = repository.selectByBoardNo(boardNo); // 조회수 1 증가 후의 글 조회수 
		assertEquals(expectedViewcount, b2.getBoardViewcount());
	}

	@Test
	public void testUpdateContent() throws FindException, ModifyException {
		//내용 수정 테스트
		int boardNo = 1;
		Board b1 = repository.selectByBoardNo(boardNo);
		assertNotNull(b1);
		//b1이 null이 아닐 경우에
		String beforeContent = b1.getBoardContent();
		String expectedContent = beforeContent+"a";
		int beforeViewcount = b1.getBoardViewcount();
		
		Board b = new Board();
		b.setBoardNo(boardNo);
		b.setBoardContent(expectedContent);
		repository.update(b);
		
		Board b2 = repository.selectByBoardNo(boardNo);
		assertNotEquals(beforeContent,b2.getBoardContent()); // 이전 내용과 현재 내용 같은지 비교 확인
		assertEquals(expectedContent,b2.getBoardContent()); // 이전 내용과 현재 내용 같은지 비교 확인
		assertEquals(beforeViewcount,b2.getBoardContent()); // 이전 내용과 현재 내용 같은지 비교 확인
	}
	
	@Test(expected = FindException.class)
	public void testDelete() throws RemoveException, FindException {
		int boardNo=1;
		repository.delete(boardNo);
		repository.selectByBoardNo(boardNo);
	}
	
	@Test
	public void testInsert() throws AddException, FindException {
		// 글쓰기용 테스트
		String expectedBoardTitle = "새글";
		String expectedBoardContent ="새글내용";
		String expectedBoardId = "id1";
		
		Board b = new Board();
		b.setBoardTitle(expectedBoardTitle);
		b.setBoardContent(expectedBoardContent);
		b.setBoardId(expectedBoardId);
		
		repository.insert(b);
		
		int boardNo = b.getBoardNo(); // insert 작업 하게 되면 selectKey에 의해 boardNo 설정 되어있을 것
		assertNotEquals(boardNo, 0);
		
		// 글이 검색이 잘 되는지 확인
		Board b1 = repository.selectByBoardNo(boardNo);
		assertNotNull(b1);
		assertEquals(expectedBoardTitle, b1.getBoardTitle());
		assertEquals(expectedBoardContent, b1.getBoardContent());
		assertEquals(expectedBoardId, b1.getBoardId());
		
		System.out.println(b1);
		
	}
	@Test
	public void testInsertReply() throws AddException, FindException {
		// 글쓰기용 테스트
		int expectedBoardParentNo = 8;
		String expectedBoardTitle = "새글_답";
		String expectedBoardContent ="새글답_내용";
		String expectedBoardId = "id1";
		
		Board b = new Board();
		b.setBoardParentNo(expectedBoardParentNo);
		b.setBoardTitle(expectedBoardTitle);
		b.setBoardContent(expectedBoardContent);
		b.setBoardId(expectedBoardId);
		
		repository.insert(b);
		
		int boardNo = b.getBoardNo(); // insert 작업 하게 되면 selectKey에 의해 boardNo 설정 되어있을 것
		assertNotEquals(boardNo, 0);
		
		// 글이 검색이 잘 되는지 확인
		Board b1 = repository.selectByBoardNo(boardNo);
		assertNotNull(b1);
		assertEquals(expectedBoardTitle, b1.getBoardTitle());
		assertEquals(expectedBoardContent, b1.getBoardContent());
		assertEquals(expectedBoardId, b1.getBoardId());
		
		System.out.println(b1);
		
	}
	
}
