$(function() {
  // 아이디 저장 CHECKBOX locatStorage처리 완성하세요 (js/login.js내용 참고하여 jquery로 완성)
	//--아이디 입력객체찾기
  let $inputId = $('input[name=id]');

	//비밀번호입력 객체찾기
	let $inputPwd = $("input[name=pwd]");

  //----- form 전송 START -----
  let $form = $('form');
  $form.submit(function(){
  	    //let url = 'http://localhost:8888/back/login';
   		let url = `${backPath}/login`;
		let inputIdValue, inputPwdValue;
		inputIdValue = $inputId.val(); // 사용자가 입력해준 아이디값
		inputPwdValue = $inputPwd.val(); // 사용자가 입력해 준 비밀번호값

    let data = 'id=' + inputIdValue + '&pwd=' + inputPwdValue;
  //   $('section>article:first').load(url, data ,function(responseText,statusText,xhr){
  //     if (statusText != 'success') { // 응답오류인 경우
  //     	alert(xhr.status + ":" + xhr.statusText);
  //     }else{ // 응답성공인 경우 
	// 			let jsonObj = JSON.parse(responseText); //{status:1} 문자열을 json객체로 변환
	// 			if(jsonObj.status == 1){ // 로그인 성공된 경우

	// 			}else if(jsonObj.status == 2) { // 로그인 실패한 경우
	// 				alert('로그인 실패');
	// 			}
	// 		}
  // });

	// html페이지, 텍스트 로드할 때만 load함수 사용 적절
	// 응답내용에 따라 다르게 처리하는 경우(동적인 경우) load 함수 적절하지X
  $.ajax({
    url : url, 
    method: 'post',
    data: data,
    //success: function(responseText){
	//let jsonObj = JSON.parse(responseText);
	success : function(jsonObj){
			if(jsonObj.status ==1){ // 로그인 성공
				location.href = ''; // 현재 사용중인 주소로 새요청
			}else{ // 로그인 실패 (jsonObj.status == 2)
				alert('로그인 실패'); 
			}
			// $('section>article:first').html(responseText);
    },
    error: function(jqXHR, statusText, errorThrown){
    	alert(jqXHR.status + ":" + jqXHR.statusText);
    } 
  });
  	console.log(data);
  	return false; // = event.preventDefault(); + event.stopPropagation() 
        // form 객체의 이벤트 처리와 이벤트 전파까지 막는 방법 -> submit이벤트의 기본처리인 전송을 금지하고 ajax방식으로 요청하겠다는 뜻
    });

});