$(function () {

	function showList(pageNo) {
		$.ajax({
		url: "/backboard/boardlist",
		method: "get",
		data: "currentPage= " + pageNo,
		success: function (jsonObj) {
			// resultBean 형태로 응답
			if (jsonObj.status == 1) {
			let pageBeanObj = jsonObj.t; // pagebean타입

			// 게시글 div를 원본으로한다. 복제본 만든다
			// div.board의 목록을 찾아 복붙 복붙 해 넣는 작업
			let $board = $("div.board").first();
			// 원본을 하나 선택 해 놓고 나머지 게시글 삭제 
			$('div.board').not($board).remove();

			let $boardParent = $board.parent();
			$(pageBeanObj.list).each(function (index, board) {
				console.log(index, board);
				let $boardCopy = $board.clone();
				$boardCopy.find("div.board_no").html(board.boardNo);
				$boardCopy.find("div.board_parent_no").html(board.boardParentNo);
				$boardCopy.find("div.board_title").html(board.boardTitle);
				$boardCopy.find("div.board_dt").html(board.boardDt);
				$boardCopy.find("div.board_id").html(board.boardId);
				$boardCopy.find("div.board_viewcount").html(board.boardViewcount);
				//--- 복제본 만드는 작업 END ----
				// 부모에 붙여넣기
				$boardParent.append($boardCopy);
			});

			let $pagegroup = $("div.pagegroup");
			let $pagegroupHtml = "";
			//내용 채워주는 것
			if (pageBeanObj.startPage > 1) {
				$pagegroupHtml += "<span>PREV</span>";
			}
			for (let i = pageBeanObj.startPage; i <= pageBeanObj.endPage; i++) {
				$pagegroupHtml += "&nbsp;&nbsp";
				if (pageBeanObj.currentPage == i) {
				// 현재 보고있는 페이지인 경우 <span> 만들지 않음
				$pagegroupHtml += i;
				} else {
				$pagegroupHtml += "<span>" + i + "</span>";
				}
			}
			if (pageBeanObj.endPage < pageBeanObj.totalPage) {
				$pagegroupHtml += "&nbsp;&nbsp";
				$pagegroupHtml += "<span>NEXT</span>";
			}

			$pagegroup.html($pagegroupHtml);
			/*pageBeanObj.startPage
					pageBeanObj.endPage
					pageBeanObj.totalPage
					pageBeanObj.currentPage */
			// 위의 변수들 json 문자열로 parsing 해 옴
			} else {
			alert(jsonObj.msg);
			}
		},
		error: function (jqXHR) {
			alert("에러 : " + jqXHR.status);
		},
		});
	}

	// --- 페이지 로드 되자마자 게시글 1페이지 검색 START ---
	showList(1);
	// --- 페이지 로드 되자마자 게시글 1페이지 검색 END ---

	// DOM 트리가 실행될 처음엔 <span>태그 없지만 1 페이지 검색 되면 <span> 태그 만들어 짐
	// --- 페이지 그룹의 페이지를 클릭 START ---

	// 처음 로드할 때 만들어져있는 객체 기준으로 on함수 사용해야함 (기존 객체 : div.pagegroup)
	$("div.pagegroup").on("click", "span", function () {
		let pageNo = $(this).html();
		if (pageNo == "PREV") {
			let firstPage = $(this).parent().children(2); // span 객체의 바로 앞 객체
		pageNo = parseInt($(this).next().html()) - 1; // 문자 타입을 숫자타입으로 변환해야 NEXT 클릭 시 3페이지 노출
		// parseInt 해 주지 않으면 next 페이지 21로 노출됨
		} else if (pageNo == "NEXT") {
		pageNo = parseInt($(this).prev().html()) + 1; //prev()까지는 객체 .html()까지는 객체의 내용
		} else {
		pageNo = parseInt(pageNo);
		}
		alert("보려는 페이지 번호 : " + pageNo);
		showList(pageNo);
	});
	// --- 페이지 그룹의 페이지를 클릭 END ---
});
