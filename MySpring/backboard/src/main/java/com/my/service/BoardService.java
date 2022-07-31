package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dto.Board;
import com.my.dto.PageBean;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.repository.BoardRepository;

@Service
public class BoardService {
	private static final int CNT_PER_PAGE = 3 ; // 페이지별 보여줄 목록 수 
	
	@Autowired
	private BoardRepository repository;
	
	/**
	 * 페이지별 게시글 목록과 페이지 그룹정보를 반환한다 
	 * @param currentPage 검색할 페이지
	 * @return 
	 * @throws FindException
	 */
	public PageBean<Board> boardList(int currentPage) throws FindException {
		List<Board> list = repository.selectByPage(currentPage, CNT_PER_PAGE);
		// 전체 행 수 반환 
		int totalCnt = repository.selectCount() ; // 총 행 수 12 , 13
		int totalPage = (int) Math.ceil((double)totalCnt/CNT_PER_PAGE); // 총 페이지 수 4 , 5
		// Math.ceil : 주어진 숫자보다 크거나 같은 숫자 중 가장 작은 숫자를 integer 로 반환합니다.
		// 실수 / 정수 -> 실수 -> 이렇게 되어야 ceil 적용할 수 있음 
		// 나온 실수값을 int 형으로 다시 형변환
		int cntPerPageGroup = 2; // 페이지별 보여줄 페이지 수 
		// currentPage	startPage	totlaPage	endPage
		//		1			1			5			2
		//		2			1			5			2
		//		3			3			5			4 
		//		4			3			5			4
		//		5			5			5			5
		
//		int endPage = (int)Math.ceil((double)cntPerPageGroup/currentPage)*cntPerPageGroup;	// cntPerPageGroup 	
//		// cntPerPageGroup와 currentPage 둘 중 하나를 double(실수) 타입으로 바꿔야 ceil 적용 가능 -> 이를 또 int로 변환해야 함
//		int startPage = endPage - cntPerPageGroup +1 ; // 1,3,5,7,9와 같은 홀수
//		if(totalPage < endPage) {
//			endPage = totalPage;	
//		}
//		PageBean<Board> pb = new PageBean<>();
//		pb.setList(list);
//		pb.setTotalPage(totalPage);
//		pb.setCurrentPage(currentPage);
//		pb.setStartPage(startPage);
//		pb.setEndPage(endPage);
//		pb.setCntPerPageGroup(cntPerPageGroup);
//		return pb;
		
		PageBean<Board> pb1 = new PageBean<>(list, totalCnt, currentPage, cntPerPageGroup, CNT_PER_PAGE);
		return pb1;
	}
	
	/**
	 * 검색어를 이용한 게시글 검색 목록과 페이지 그룹정보를 반환한다
	 * @param word 검색어
	 * @param currentPage 검색할 페이지
	 * @return
	 * @throws FindException
	 */
	public PageBean<Board> searchBoard(String word, int currentPage) throws FindException{
		List<Board> list = repository.selectByWord(word, currentPage, CNT_PER_PAGE);
		int totalCnt = repository.selectCount(word);
		int cntPerPageGroup = 2;
		PageBean<Board> pb1 = new PageBean<>(list, totalCnt, currentPage, cntPerPageGroup, CNT_PER_PAGE);
		return pb1;
	}
	
	/**
	 * 게시글 번호의 조회수를 1 증가한다
	 * 게시글 번호의 게시글을 반환한다
	 * @param boardNo 게시글 번호
	 * @return
	 * @throws FindException
	 */
	public Board viewBoard(int boardNo) throws FindException { // 게시물 보기 작업
		try {
			Board b = new Board();
			b.setBoardNo(boardNo);
			b.setBoardViewcount(-1);
			repository.update(b);
		
			// 게시글 번호의 게시글을 조회
			Board b1 = repository.selectByBoardNo(boardNo); // 글번호에 해당하는 글을 조회
			return b1;
		} catch (ModifyException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
	}
	
	/**
	 * 글쓰기
	 * @param repBoard
	 * @throws AddException
	 */
	public void writeBoard(Board board) throws AddException{
		board.setBoardParentNo(0); // 부모 글번호 무조건 0으로 설정해야 글쓰기가 가능함
		repository.insert(board);
	}
	
	
	/**
	 * 답글쓰기
	 * @param board
	 * @throws AddException
	 */
	public void replyBoard(Board board) throws AddException{
		if(board.getBoardParentNo() == 0) {
			throw new AddException("답글쓰기의 부모글번호가 없습니다");
		}
		repository.insert(board);
	}
	
	/**
	 * 게시글 수정하기
	 * @param board
	 * @throws ModifyException
	 */
	public void modifyBoard(Board board) throws ModifyException {
		board.setBoardParentNo(0); // 부모 글 번호 0으로 설정해야 수정 가능
		repository.update(board);
	}
	
	/**
	 * 답글, 게시글 삭제하기
	 * @param board
	 * @throws RemoveException 
	 * @throws ModifyException
	 */
	public void removeBoard(int boardNo) throws RemoveException {
		Board b = new Board();
		b.setBoardNo(boardNo); // 게시글 번호를 설정해야 삭제 가능?!
		repository.delete(boardNo);
	}
	
	
}

