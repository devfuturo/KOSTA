//1. 문서의 끝까지 해석 후 dom 트리가 완성되고 화면에 렌더링할 준비가 되면 
//window객체의 load이벤트가 발생한다

//dom트리 작성이 완료 될때까지 기다림
//window객체의 load이벤트 발생을 감시했다가 
//이벤트가 발생하면 function()이 자동호출됨 
window.addEventListener("load", function(){ //window객체의 load event
    //dom트리의 type속성이 text인 input객체 찾기
    var txtObj = document.querySelector('input[type=text]');
    //dom트리의 button객체 찾기
    var btObj = document.querySelector('button');
    //button객체가 click 이벤트가 발생했을 때 function()이 자동호출
    btObj.addEventListener("click",function() {
        alert('클릭되었습니다');
        //다른 조작하고 싶을 때
        txtObj.value = '클릭되었습니다'; // 전달된 파라미터 객체가 가지는 (열거 가능한) 속성의 값들로 이루어진 배열을 리턴합니다 
    });

    //-----------계산기 START---------------
    //DOM트리에서 class속성값이 calculator인 객체의 자식 중 모든 button객체 찾기
    var btArr = document.querySelectorAll('div.calculator>button')  //div객체를 찾은것임. JS에서는 태그개념이 아님 
    //All을 붙여 만족하는 값을 모두 찾음. -> 배열값으로 반환이 됨 
    for(var i=0; i<btArr.length; i++){
        (function(j){  //이름없는 함수를 호출하려면 (function (){})(대입할변수) 소괄호를 두개 열고 닫아서 사용 
            btArr[j].addEventListener('click', function(){
                console.log(j, '버튼클릭되었습니다');
            });
        })(i);
    }
    // i가 0일때 function(j)함수를 호출하여 실행시킴 -> 이 함수는 예약걸어두는것이 아닌 실행후 휘발될 함수임 
    // 변수에 담겨있지 않기에 함수가 바로 실행됨 
    // j가 0,1,2,3,4~~~12인 함수들로 구분되어 배열에 담김. 
    // 함수 scope로 scope를 하나 더 만든 것. 

    // for(var i=0; i<btArr.length; i++){
    //     btArr[i].addEventListener('click', function(){
    //         console.log(i, '버튼클릭되었습니다')
    //     });
    // }          
    // 1) addeventlistener은 지금 당장 실행한다는 것이 아니라, 
    // click했을때 실행해라 라는 의미 
    // 2) 여기서의 i는 윈도우의 객체에서 선언된 i임 for블럭으로 구분되지않음 
    // 예약을 걸어 두는 것 
    // 3) 함수의 인자로 함수가 사용되는 경우 callbackfunction이라고 함 
    // 4) 1부터 12까지 for문돌면서 준비만 해놓음 -> 최후에 i는 12가 됨 
    // 5) i가 12까지 증가하면서 dom트리에서 객체를 찾아서 btArr[i]에 callbackfunction을 담아옴. 
    // 6) Click이 감시될때 까지 callback함수 내부에 i를 대입하ㄴㄴㄴ지 않음 btArr에 세팅만 해놓음  
    // 위의 결과로는 어떤 버튼을 클릭하더라도 12가 출력됨 

    btArr.forEach(function(item, index){
        item.addEventListener('click', function(){
            console.log(index, '버튼클릭되었습니다');
        });
    });
    //forEach는 이미 callback함수가 있기 때문에 함수 scope가 필요 없음 여기에서의 index가 j와 같은 의미 
    //배열 사용 시 일반 for문 사용하지말고 forEach사용하기 

    var resultObj = document.querySelector("div.calculator>div.result");
    var resultNum = 0; //계산될 결과값 
    var operator; //연산자
    btArr.forEach(function(item, index){
        item.addEventListener('click', function(){
            // resultObj.innerHTML = index+'번 버튼 클릭되었습니다';  0~9
            // resultObj.innerHTML = item.innerHTML+'번 버튼 클릭되었습니다'; // 1~0
            //객체의 내부 바디(내용)에 접근하고 싶으면 innerHTML로 접근하면 됨
            var inner = this.innerHTML;
            switch(inner) {
                case '+':
                    operator = inner;
                    break;
                case '=' :
                    resultObj.innerHTML = resultNum;
                    operator = undefined;
                    resultNum = 0;
                    break;
                default: //숫자버튼들
                resultObj.innerHTML = inner;
                    if(operator == '+'){
                        resultNum += parseInt(inner);
                    }else {
                        resultNum = parseInt(inner);
                    }
            }

        });
    });
	//-----------계산기 END---------------
	//찾아낸 버튼객체에서 click이벤트가 발생했을 때 function()이 자동호출될것 입니다. 클릭되엇을때 할일은 function에 넣어주기
	//eventlistener를 등록하는 것인데, load라는 이벤트가 발생하는것을 감시했다가 2번째 인자인 function을 호출함 -> js찾아갈 수있음
	//자바스크립트에서는 DOM에만 접근할 수 있고 DOM에서 객체를 반환하여 자바스크립트와 연결해야됨 
	//강사님은 onclick속성 사용 안하고 addEventListener 사용할 것 


	//--------CHECKBOX START -----
  var cbArr = 
    document.querySelectorAll("div.checkbox input[type=checkbox]");
  cbArr.forEach(function(item,index) {
    // item.addEventListener('click',function(){});
  // if (index ==0){ //첫번째 checkbox객체
    
  // }
    console.log(item);  
  });

  cbArr[0].addEventListener('click',function(){
    for(var i = 1; i<cbArr.length;i++){
      cbArr[i].checked = this.checked;
    }
  });
//---------CHECKBOX END--------

//------SELECT START----------
var selectSidoObj = document.querySelector("div.select>select.sido");
var selectSigunguObj = document.querySelector("div.select>select.sigungu");

selectSidoObj.addEventListener('click',function(){
  console.log(this.value,'클릭되었습니다');
});
selectSidoObj.addEventListener('change',function(){
  console.log(this.value,'변경되었습니다.');

  switch(this.value){
    case '서울시':
      selectSigunguObj.innerHTML='';
      var seoul = '<option>구를 선택하세요</option>';
      seoul+='<option>중구</option>';
      seoul+='<option>성동구</option>';
      seoul+='<option>강남구</option>';
      seoul+='<option>강북구</option>';
      seoul+='<option>강서구</option>';
      selectSigunguObj.innerHTML = seoul;
      selectSigunguObj.style.display = 'inline-block'; //none으로 되어있는 display를 block형태로 바꾸는 것
      break;
      case '제주도' :
        // selectSigunguObj.innerHTML = '';
        // for(var i =0;i<selectSigunguObj.childNodes.length;i++){
        //   console.log('before remove length', selectSigunguObj.childNodes.length); //6
        //   selectSigunguObj.removeChild(selectSigunguObj.childNodes[i]); //??
        //   console.log('after remove length', selectSigunguObj.childNodes.length); //5
        // } //remove라는 작업을 length만큼 index 를 이용하여 작업하는 것은 좋은 방법 X
        // for문을 이용하여 요소를 제거하는 작업 XXX
        
        while(selectSigunguObj.hasChildNodes()){
          selectSigunguObj.removeChild(selectSigunguObj.firstchild);
        } //자식이 있는지 확인하고 있다면 removeChild로 자식 요소 제거

        // innerHTML 사용 권장

        // var jeju = '<option>시를 선택하세요</option>';
        // jeju += '<option>제주시</option>';
        // jeju += '<option>서귀포시</option>';
        // selectSigunguObj.innerHTML = jeju;
        var jeju = ['시를 선택하세요', '제주시', '서귀포시'];
        for (var i = 0; i < jeju.length; i++){
          var opt = document.createElement('option');
          var txt = document.createTextNode(jeju[i]);
          opt.appendChild(txt);
          selectSigunguObj.appendChild(opt);
          
        }
        var opt1 = document.createElement('option');
        var txt1 = document.createTextNode('시를 선택하세요');
        opt1.appendChild(txt1);
        selectSigunguObj.appendChild(opt1);

        var opt2 = document.createElement('option');
        var txt2 = document.createTextNode('제주시');
        opt2.appendChild(txt2);
        selectSigunguObj.appendChild(opt2);

        var opt3 = document.createElement('option');
        var txt3 = document.createTextNode('서귀포시');
        opt3.appendChild(txt3);
        selectSigunguObj.appendChild(opt3);


        selectSigunguObj.style.display = 'inline-block'; //none으로 되어있는 display를 block형태로 바꾸는 것
      break;
    default : 
    selectSigunguObj.innerHTML = ''; // 초기화
    selectSigunguObj.style.display = 'none';
    }
  });
  // 값이 바뀔 때만 change 이벤트 발생 ex) 서울시 -> 제주도  
  //------SELECT END----------

  //------keyboard START-------
  // DOM트리에서 div.keyboard의 input 객체 찾기
  var inputObj = document.querySelector("div.keyboard>input[type=text]");
  inputObj.addEventListener('click', function(){
    console.log('input객체 클릭되었습니다.');
  });
  inputObj.addEventListener('focus', function(){
    console.log('input객체 포커스 받았습니다.');
    this.style.color = 'blue';
  });

  // keydown -> keypress -> keyup
  inputObj.addEventListener('keyup', function(event) {
    alert('입력된 키 값:' + event.key);
    if(event.key == 'Enter'){
    }
  });
  //------------------KEBOARD END-----------------------

  //------------------Submit START-----------------------
  //전송관련 이벤트 :버튼의 click 이벤트 -> 폼의 sumit이벤트 -> 폼의 submit 이벤트 기본처리(전송)됨
  //var btSubmitObj = document.querySelector('div.submit>form>button');
  var formObj = document.querySelector('div.submit>form');
  //var textSubmitObj = document.querySelector('div.submit>form>input[name=t]');
  
  // DOM nav 이용하여 button 찾기
  var btSubmitObj = formObj.lastElementChild; // 공백(br)도 child로 보기 때문에 elementChild로 써 주어야함

  //DOM nav 이용하여 imput의 text 찾기
  var textSubmitObj = formObj.firstElementChild;


  btSubmitObj.addEventListener('click', function(){
    alert('전송버튼 클릭 이벤트가 발생했습니다');
  });
  formObj.addEventListener('submit', function(event){
    alert('form의 submit 이벤트가 발생했습니다');
    if(textSubmitObj.value == ''){
      alert('값을 입력하세요');
      event.preventDefault(); // 기본이벤트 처리 금지
    }
  });
  //------------------Submit END----------------------

  // ----------------- a START ------------------------
  // 이동관련 이벤트 : a객체의 클릭이벤트 -> 클릭이벤트 기본처리(이동)
  // 이벤트 전파 : 하위객체에서 발생한 이벤트가 상위객체로 전파 (이벤트 버블링)
  var divAObj = document.querySelector("div.a");
  divAObj.addEventListener('click',function(){
    this.style.backgroundColor = 'yellow';
  });
  var aObj = document.querySelector("a");
  aObj.addEventListener('click',function(event) {
    this.style.backgroundColor = 'green';
    event.preventDefault(); // 기본이벤트처리 금지
    event.stopPropagation(); // 이벤트 전파 중지 / 자식쪽에서의 이벤트가 부모쪽에 전파되는 것을 중지
  });
  // ----------------- a END -------------------------
});