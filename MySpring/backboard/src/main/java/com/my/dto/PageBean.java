package com.my.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@ToString
public class PageBean<T> {
	// 게시물 목록보기에서 필요한 요소들 모두 들어있어야함
	// 상품 목록, 공지사항 목록 등을 보기 위해서도 재사용 할 수 o 
	// private List<Board> 가 아닌 <T>와 같이 사용하여 사용성 높임 : Type Generic
	// List<T> 클래스 : 목록의 검색, 정렬 및 조작에 사용할 수 있는 메서드를 제공
	private List<T> list; // 목록
	private int currentPage; // 현재페이지
	private int totalPage; // 총 페이지
	private int startPage; // 시작 페이지
	private int endPage; // 끝 페이지
	private int cntPerPageGroup = 2 ; // 페이지 그룹핑의 개수 (하단에 보여 줄 페이지 개수)
 	private String url; // 각 페이지 클릭 시 요청할 url
 	
 	
 	/**
	 * 
	 * @param list 페이지의 목록
	 * @param totalCnt 총 건수
	 * @param currentPage 검색할페이지
	 * @param cntPerPageGroup //페이지그룹별 보여줄 페이지수
	 * @param cntPerPage //한페이지당 보여줄 목록수
	 */
	public PageBean(List<T>list, int totalCnt, int currentPage, int cntPerPageGroup, int cntPerPage) {
		this.list = list;
		this.currentPage = currentPage;
		this.cntPerPageGroup = cntPerPageGroup;
		
		this.totalPage =  (int)Math.ceil((double)totalCnt/cntPerPage);//총페이지수 4, 5
		
		//currentPage  startPage  totalPage endPage
		// 1             1            5       2
		// 2             1                    2
		// 3             3                    4
		// 4             3                    4
		// 5             5                    5
		this.endPage =
				   (int)(Math.ceil((double)currentPage/cntPerPageGroup)*cntPerPageGroup);
		this.startPage = this.endPage - cntPerPageGroup + 1;
		if(this.totalPage <this.endPage) {
			this.endPage = this.totalPage;
		}
	}

}
