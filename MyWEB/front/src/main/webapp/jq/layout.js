$(function(){
	//로그인 여부를 확인 가능한 서블릿 요청
	//응답 형태 {status:1} - 이미 로그인 된 경우
	//	     	header>nav>a 를  로그아웃으로 바꾸고
	/*	<a href="/back/logout">로그아웃</a>
        <a href="productlist.html">상품</a>
        <a href="viewcart.html">장바구니</a>
	*/

	// 			그 외	   - 로그인 안 된 상태를
	//			header>nav>a 를 로그인, 가입 메뉴가 보이도록
	/* 	<a href="login.html">로그인</a>
    	<a href="signup.html">가입</a>
        <a href="productlist.html">상품</a>
        <a href="viewcart.html">장바구니</a>
	*/		

	 let url = `${backPath}/loginstatus`; //backtick ``-> ECMA스크립트 문법
	//	${} => 변수를 사용하여 문자열 더하겠다
  	//let url='/back/loginstatus';	
	let method = 'GET';
	$.ajax({ 
		url: url,
		method: method,
		success: function(jsonObj){
			let $navObj = $('header>nav');
			let $navObjHtml = '';
	      	if(jsonObj.status == 1){//로그인된경우
		        $navObjHtml += '<a href="vieworder.html">주문내역</a>';
		        $navObjHtml += `<a href="${backPath}/logout">로그아웃</a>`;
			}else{ // 로그인 안 된 경우
				$navObjHtml += '<a href="login.html">로그인</a>';
				$navObjHtml += '<a href="signup.html">가입</a>';
			}
			$navObjHtml += '<a href="productlist.html">상품</a>';
			$navObjHtml += '<a href="viewcart.html">장바구니</a>';
			$navObj.html($navObjHtml); //ajax 결과로 DOM 트리에 객체가 만들어질 것 
		},
		error: function(jqXHR){
			alert('오류:'+jqXHR.status);
		}
	});
	
	// 응답이 올 때까지 기다렸다가 그 다음 메뉴 객체 찾고 클릭이벤트 발생했을 때 ~ 하는 것 X -> ajax 비동기 처리
	// 로그인 되면 로그아웃 DOM 트리가 만들어짐
	// 로그인이 안 되었으면 로그인, 가입용 객체가 DOM트리에 만들어짐 
	// 로그인 여부에 따라 동적으로 바뀜

	//ajax가 일 하던 말던 실행됨 / ajax가 빨리 응답되어 dom트리가 만들어져있을 수도 있지만
	//아닐 수도 있음
	// 향후 추가 될 이벤트 객체 등록 해 둘 수 있음 
	//메뉴 객체들 찾기
	let $menuObj = $('header>nav>a'); 
	// section 첫번째 자식 요소인 article 객체 찾기
	let $articleObj = $('section>article:first');

	// ----- 메뉴 클릭 START -----
	// 메뉴가 클릭되면 article 영역의 innerHTML로 로드
	// $menuObj.click(function(){
	$('header>nav').on('click','a', function(){
	 // 현재 객체 : header>nav / 앞으로 추가될 a태그에서 click이벤트가 발생할 경우(로그아웃 메뉴 클릭 되었을 때)에 대한 이벤트 미리 등록
		let url = $(this).attr('href');
		let title = $(this).html(); // 이 객체의 html내용 (로그인, 가입, 상품, 장구니)
		
		$articleObj.load(url,function(responseText,statusText,xhr){ 
		if(statusText != 'success'){
			// alert(xhr.status + ": " + xhr.statusText);
			if(xhr.status == 404){ // 404번 오류일 때 
			let msg = title+ '자원을 찾을 수 없습니다'; 
			alert(msg);
			}
		}
		if(url==`${backPath}/logout`){ // 로그아웃 버튼 클릭 시 서버로부터 결과 응답받지 않고 주소 url만 변경함
			location.href='css_js_layout.html';
		}
		});
		return false;
	});
	// ----- 메뉴 클릭 END -----

	});
