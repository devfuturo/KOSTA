$(function () {
	let loginedId = "id9"; //테스트용 
	// 샘플데이터 다 id1로 넣었기 때문에 loginedId를 id1이 아닌 다른 아이디로 변경 시 글 수정,삭제 버튼이 없음

	//function showList(pageNo){
	function showList(url, data) {
		$.ajax({
		// url: '/backboard/boardlist',
		// data: 'currentPage=' + pageNo,
		url: url,
		method: "get",
		data: data,
		success: function (jsonObj) {
			// resultBean 형태로 응답
			if (jsonObj.status == 1) {
			let pageBeanObj = jsonObj.t;

			//게시글 div를 원본으로 한다. 복제본만든다
			// div.board의 목록을 찾아 복붙 복붙 해 넣는 작업
			let $board = $("div.board").first();

			// 원본을 하나 선택 해 놓고 나머지 게시글 삭제
			$("div.board").not($board).remove();
			let $boardParent = $board.parent();
			$(pageBeanObj.list).each(function (index, board) {
				let $boardCopy = $board.clone(); //복제본
				$boardCopy.find("div.board_no").html(board.boardNo);
				$boardCopy.find("div.board_parent_no").html(board.boardParentNo);
				$boardCopy.find("div.board_title").html(board.boardTitle);
				$boardCopy.find("div.board_dt").html(board.boardDt);
				$boardCopy.find("div.board_id").html(board.boardId);
				$boardCopy.find("div.board_viewcount").html(board.boardViewcount);
				$boardCopy.find("div.arrow").addClass("down");
				// resultBean 형태로 응답
				$boardParent.append($boardCopy);
			});

			let $pagegroup = $("div.pagegroup");
			let $pagegroupHtml = "";

			//내용 채워주는 것
			if (pageBeanObj.startPage > 1) {
				$pagegroupHtml += '<span class="prev">PREV</span>';
			}
			for (let i = pageBeanObj.startPage; i <= pageBeanObj.endPage; i++) {
				$pagegroupHtml += "&nbsp;&nbsp;";
				if (pageBeanObj.currentPage == i) {
				//현재페이지인 경우 <span>태그 안만듦
				// $pagegroupHtml += i;
				$pagegroupHtml += '<span class="disabled">' + i + "</span>";
				} else {
				$pagegroupHtml += "<span>" + i + "</span>";
				}
			}
			if (pageBeanObj.endPage < pageBeanObj.totalPage) {
				$pagegroupHtml += "&nbsp;&nbsp;";
				$pagegroupHtml += '<span class="next">NEXT</span>';
			}

			$pagegroup.html($pagegroupHtml);
			} else {
			alert(jsonObj.msg);
			}
		},
		error: function (jqXHR) {
			alert("에러:" + jqXHR.status);
		},
		});
	}

	//---페이지 로드되자 마자 게시글1페이지 검색 START---
	showList("/backboard/boardlist", "currentPage=1");
	//---페이지 로드되자 마자 게시글1페이지 검색 END---

	// DOM 트리가 실행될 처음엔 <span>태그 없지만 1 페이지 검색 되면 <span> 태그 만들어 짐
	//---페이지 그룹의 페이지를 클릭 START---
	$("div.pagegroup").on("click", "span:not(.disabled)", function () {
		// span태그들 중에서 class속성이 disabled가 아닌 객체들(요소들)을 찾아라
		let pageNo = 1;
		//if (!$(this).hasClass("disabled")) { // disabled class속성을 갖지 않는 경우
		if ($(this).hasClass("prev")) {
		pageNo = parseInt($(this).next().html()) - 1; // 문자 타입을 숫자타입으로 변환해야 NEXT 클릭 시 3페이지 노출
		// parseInt 해 주지 않으면 next 페이지 21로 노출됨
		} else if ($(this).hasClass("next")) {
		pageNo = parseInt($(this).prev().html()) + 1; //prev()까지는 객체 .html()까지는 객체의 내용
		} else {
		pageNo = parseInt($(this).html());
		}
		// alert("보려는 페이지번호: " + pageNo);
		let word = $("div.search>div.searchInput>input[name=word]").val().trim();
		let url = "";
		let data = "";
		if (word == "") {
		url = "/backboard/boardlist";
		data = "currentPage=" + pageNo;
		} else {
		url = "/backboard/searchboard";
		data = "currentPage=" + pageNo + "&word=" + word;
		}
		showList(url, data);
		return false;
		// }
	});
	//---페이지 그룹의 페이지를 클릭 END---

	//---검색 클릭 START---
	$("div.search>div.searchInput>a").click(function () {
		let word = $("div.search>div.searchInput>input[name=word]").val().trim();
		let url = "/backboard/searchboard";
		let data = "currentPage=1&word=" + word;
		showList(url, data);
		return false;
	});
	//---검색 클릭 END---

	//---arrow화살표클릭 START---
	$("div.boardlist").on(
		"click",
		"div.board>div.cell>div.summary>div.arrow",
		function () {
		if ($(this).hasClass("down")) {
			let boardNo = $(this).siblings("div.board_no").html();
			let $viewCount = $(this).siblings("div.board_viewcount");
			let $detail = $(this).parents("div.cell").find("div.detail");
			let $boardContent = $detail.find("input.board_content");
			let $modifyNremove = $detail.find("div.modifyNremove");

			$.ajax({
			url: "/backboard/viewboard",
			method: "get",
			data: "boardNo=" + boardNo,
			success: function (jsonObj) {
				if (jsonObj.status == 1) {
				let board = jsonObj.t;
				$viewCount.html(board.boardViewcount);
				if (loginedId == board.boardId) {
					$boardContent.removeAttr("readonly");
					$boardContent.css("outline", "auto");
					$modifyNremove.show();
				} else {
					$boardContent.attr("readonly", "readonly");
					$boardContent.css("outline", "none");
					$modifyNremove.hide();
				}
				$boardContent.val(board.boardContent);
				$detail.show();
				} else {
				alert(jsonObj.msg);
				}
			},
			error: function (jqXHR) {
				alert("에러:" + jqXHR.status);
			},
			});
			$(this).addClass("up");
			$(this).removeClass("down");
		} else if ($(this).hasClass("up")) {
			$(this).addClass("down");
			$(this).removeClass("up");
			$(this).parents("div.cell").find("div.detail").hide();
		}
		}
	);
	//---arrow화살표클릭 END---
});
