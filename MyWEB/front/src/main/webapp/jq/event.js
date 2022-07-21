// $(document).ready(function() {});
$(function(){
		//dom트리의 type속성이 text인 input객체 찾기
		let $txtObj = $('input[type=text]');
		 //dom트리의 button객체 찾기
		let $btObj = $('button').first(); // jq객체를 반환
		 //button객체가 click 이벤트가 발생했을 때 function()이 자동호출
		$btObj.click(function(){
			alert('클릭되었습니다');
			$txtObj.val('클릭되었습니다'); //다른 조작하고 싶을 때
		});
		//event.js.js보다 코드가 간단함
	
		
		//------ 계산기 START -----
		// $()의 반환형은 jQuery 객체형
		// $()의 (반환된)결과가 여러 객체인 경우 각 인덱스의 요소는 (jq객체가 아니라) js 객체이다.
		// js 객체를 jq객체로 변환하려면 $( js객체) 처리 필요
		// 반복문용 jq 메서드는 each() 

		//DOM트리에서 class속성값이 calculator인 객체의 자식 중 모든 button객체 찾기
		let $btArr = $('div.calculator>button'); // querySelectorAll의 역할을 해 주는게 $임
		// $btArr[0].hide(); //$btArr 객체가 js 객체이기 때문에(여러 객체 반환 때문에) hide 해도 실행되지 X 인덱스 객체를 jq객체로 바꾸어 주어야함 
	
		// js 객체를 jq객체로 변환하기 위해 $(js객체) 처리 
		// $($btArr[0]).hide(); // 실행 됨
		
		// jq에서는 forEach 사용 X
		// $btArr.forEach(function(item,index){
		// 	if(index % 2 == 0){
		// 		item.hide();
		// 	}
		// });
		
		// 반복문용 jq 메서드는 each() 순서도 forEach와 반대(index, item)
		// $btArr.each(function(index, item){
		// 	  $(item).click(function(){
		// 	    let inner = $(this).html();
		
		let $resultObj = $('div.calculator>div.result');
		let resultNum = 0; //계산된 결과값
		let operator; //연산자
		$btArr.click(function(){ //계산기에 css 적용 
			$(this).css('color','red');
		});
			//     switch(inner){
			//       case '+':
			//         operator = inner;
			//         break;
			//       case '=':
			//         $resultObj.html(resultNum);
			//         operator = undefined;
			//         resultNum = 0;
			//         break;
			//       default://숫자버튼들
			//         $resultObj.html(inner);
			//         if(operator == '+'){
			//           resultNum += parseInt(inner);
			//         }else{
			//           resultNum = parseInt(inner);
			//         }
			//     }
			//   });
			// });
		//------ 계산기 END -----
		
		// ----- CHECKBOX START -----
		let $cbArr = $('div.checkbox input[type="checkbox"]');
		let $cbAll = $cbArr.first(); // 첫번째 checkbox
		let $cbOther = $cbArr.not($cbAll); // 나머지 checkbox들
		$cbAll.click(function(){
			let status = $(this).prop('checked');  
			$cbOther.prop('checked', status);
			//checked 프로퍼티값 얻기
		});
		// ----- CHECKBOX END -----
		
		// ----- SELECT START -----
		var $selectSidoObj = $('div.select>select.sido');
		var $selectSigunguObj = $('div.select>select.sigungu');

		$selectSidoObj.click(function(){
			console.log($(this).val(), '클릭되었습니다');
		});

		$selectSidoObj.change(function(){
			console.log($(this).val(),'변경되었습니다');
			switch($(this).val()){
				case '서울시':
					$selectSigunguObj.empty();
					var seoul = '<option>구를 선택하세요</option>';
					seoul+='<option>중구</option>';
					seoul+='<option>성동구</option>';
					seoul+='<option>강남구</option>';
					seoul+='<option>강북구</option>';
					seoul+='<option>강서구</option>';
					$selectSigunguObj.html(seoul);
					$selectSigunguObj.show();  
					break;
					case '제주도' :
						$selectSigunguObj.empty();
						var jeju = ['시를 선택하세요', '제주시', '서귀포시'];
						for (var i = 0; i < jeju.length; i++){
							var $opt =  $('<option>');
							var txt = jeju[i]; //createTextNode는 제외하고 쓰면 됨
							$opt.append(txt);
							$selectSigunguObj.append($opt);	
						}
						$selectSigunguObj.show(); 
					break;
				default : 
				$selectSigunguObj.empty();
				$selectSigunguObj.hide();
				}
			});
		// ----- SELECT END -----
		
		// ----- KEBOARD START -----
		// DOM트리에서 div.keyboard의 input객체찾기
		var $inputObj = $("div.keyboard>input[type=text]");
		$inputObj.click(function(){
			console.log('input 객체 클릭되었습니다.');
		});
		$inputObj.focus(function(){
			console.log('input 객체 포커스 받았습니다.');
			$(this).css('color','blue');
		});
		//keydown-> keypress-> keyup
		$inputObj.keyup(function(event){
			alert('입력된 키 값:'+ event.key)
			if(event.key == 'Enter'){
			}
		});

		// ----- KEBOARD END -----

		// -----Submit START -----
		//전송관련 이벤트 :버튼의 click 이벤트 -> 폼의 sumit이벤트 -> 폼의 submit 이벤트 기본처리(전송)됨
		let $formObj = $('div.submit>form');
		//form 객체의 후손 중 input[type=text] 찾는 작업
		let $textSubmitObj = $formObj.find('input[type=text]'); 
			//form 객체의 후손 중 button  찾는 작업
		let btSubmitObj = $formObj.find('button');
		$btSubmitObj,click(function(){
			alert('전송버튼 클릭이벤트가 발생했습니다');
		});
		$formObj.submit(function(event){
			alert('form의 submit 이벤트가 발생했습니다.');
			if($textSubmitObj.val() == ''){
				alert('값을 입력하세요');
				//event.preventDefault(); // 기본이벤트 처리 금지
				return false; // jq에서 기본이벤트 처리 금지 + 이벤트 전파 중지
			}
		});
		// ----- Submit END -----

	// ----- a START ------
	// 이동관련 이벤트 : a객체의 클릭이벤트 -> 클릭이벤트 기본처리(이동)
  // 이벤트 전파 : 하위객체에서 발생한 이벤트가 상위객체로 전파 (이벤트 버블링)
  var $divAObj = $("div.a");
  $divAObj.click(function(){
    $(this).css('background-color','yellow');
  });
  var $aObj = $("a");
  $aObj.click(function(event) {
    $(this).css('background-color','green');
    // event.preventDefault(); // 기본이벤트처리 금지
    // event.stopPropagation(); // 이벤트 전파 중지 / 자식쪽에서의 이벤트가 부모쪽에 전파되는 것을 중지
		return false;
	});
	// ----- a END ------

	});


