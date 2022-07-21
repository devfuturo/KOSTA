$(function() {
	//아이디 입력객체찾기
	let $inputId = $('input[name=id]');

    //가입버튼객체찾기
    let $btSubmit = $('input[type=submit]');
  
    //--아이디중복확인버튼 클릭 START--
    let $btIdupchk = $('input.iddupchk');
    $btIdupchk.click(function(){
		$.ajax({
      		//url: 'http://localhost:8888/back/iddupchk',
     		url: `${backPath}/iddupchk`,
			method :'GET',
			data: {id : $inputId.val()},
			success: function(jsonObj){
				if(jsonObj.status == 1){ // 사용 가능한 아이디인 경우
					$btSubmit.show();
					alert(jsonObj.msg);
				}else{
					alert(jsonObj.msg);
				}
			},
			error: function(jqXHR){
				alert('오류'+jqXHR.status); //404, 500, 200 과 같은 에러코드값만 보겠다.
			}
		}); return false ; //일반 button은 눌러도 아무 기능이 없기 때문에 return false해 주지 않아도 됨
    }) 
    //--아이디중복확인버튼 클릭 END--

    
    //--아이디입력란에 포커스 START--
    $inputId = $('input[name=id]');
    $inputId.focus(function(){
      $btSubmit.hide();
    });

	// 우편번호 버튼 객체 찾기
	let $btsearchzipcode = $('input.searchzipcode');
	$btsearchzipcode.click(function() {
	window.open('http://localhost:8888/front/html/searchzip.html',"_blank",'width=700,height=500');
	});

	//가입 버튼 클릭 이벤트 발생 -> 폼 서브밋 이벤트 발생 -> 기본처리(전송)
	// 폼 객체 찾기
	let $form = $('form');
	$form.submit(function(){
		
	// 비밀번호 일치 확인
	// 비밀번호 객체찾기
	let $pwd = $('form input[name=pwd]');
	let $pwd1 = $('#pwd1'); //# : id명으로 찾는 것
	if($pwd.val() != $pwd1.val()){
		alert('비밀번호가 일치하지 않습니다');
		$pwd.focus();
		return false; //
	}

	// let idValue = $('input[name=id]').val(); //아이디 입력값
	// let nameValue= $('input[name=name]').val(); //주소입력값
	// let addrValue= $('input[name=addr]').val(); //주소입력값
	// let buildingnoValue= $('input[name=buildingno]').val();//건물번호값
  
    //let url = '/back/signup';
    let url = `${backPath}/signup`;
	let data = $(this).serialize(); // form안에 값들을 한번에 전송 가능한 data로 만들 수 있음
	// let data = {id: idValue,
	// 						pwd:$pwd,
	// 						name:nameValue,
	// 						addr:addrValue,
	// 						buildingno:buildingno};
	//문자열로 만드는 것보다 객체로 만드는 것을 권장

	// 요청 전달 양이 많을 경우 post방식
	alert(data);
	$.ajax({
		url:url,
		method: 'post',
		data:data,
		// success: function(responseText){
		// 	let jsonObj = JSON.parse(responseText);
		success:function(jsonObj){
			alert(jsonObj.msg);
			},
			error:function(jqXHR){
				alert('에러코드:'+ jqXHR.status);
			}
		});
		 return false; // 기본 이벤트 처리 막아둠
	});


});