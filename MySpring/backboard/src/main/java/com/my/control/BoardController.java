package com.my.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.dto.Board;
import com.my.dto.PageBean;
import com.my.dto.ResultBean;
import com.my.exception.FindException;
import com.my.service.BoardService;
@Controller
public class BoardController {
	@Autowired
	private BoardService service;

	@GetMapping("boardlist")
	@ResponseBody
	public ResultBean<PageBean<Board>> list(@RequestParam(required = false, defaultValue="1") int currentPage) {
		// parameter값 전달이 안 되어도 상관 없고, 전달 되지 않을 시 초기값 1로 주겠다.
		// controller가 front에게 응답할 dto를 더 만들어도 좋음 -> ResultBean
		ResultBean<PageBean<Board>> rb = new ResultBean<>();
		try {
			PageBean<Board> pb = service.boardList(currentPage);
			rb.setStatus(1);
			rb.setT(pb);
		} catch (FindException e) {
			e.printStackTrace();
			rb.setStatus(0);
			rb.setMsg(e.getMessage());
		}
		return rb;
	}
	
	@GetMapping("searchboard")
	@ResponseBody
	public ResultBean<PageBean<Board>> search(
			@RequestParam(required = false, defaultValue="1") int currentPage, 
			@RequestParam(required = false, defaultValue="") String word){
		ResultBean<PageBean<Board>> rb = new ResultBean<>();
		PageBean<Board> pb ;
		try {
			if("".equals(word)) {
				pb = service.boardList(currentPage);
			}else {
				pb= service.searchBoard(word, currentPage);
			}
			rb.setStatus(1);
			rb.setT(pb);
		}catch(FindException e) {
			e.printStackTrace();
			rb.setStatus(0);
			rb.setMsg(e.getMessage());
		}
		return rb;
	}
	
	@GetMapping("viewboard")
	@ResponseBody
	public ResultBean<Board> viewBoard(int boardNo){
		ResultBean<Board> rb = new ResultBean<>();
		try {
			Board b = service.viewBoard(boardNo);
			rb.setStatus(1);
			rb.setT(b);
		} catch (FindException e) {
			e.printStackTrace();
			rb.setStatus(0);
			rb.setMsg(e.getMessage());
		}
		return rb;
	}
}
