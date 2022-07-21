//1. 문서의 끝까지 해석 후 dom 트리가 완성되고 화면에 렌더링할 준비가 되면 
//window객체의 load이벤트가 발생한다

//dom트리 작성이 완료 될때까지 기다림
//window객체의 load이벤트 발생을 감시했다가 
//이벤트가 발생하면 function()이 자동호출됨
window.addEventListener("load", function(){ //window객체의 load event
    //dom트리의 tyoe속성이 input객체 찾기
    var txtObj = document.querySelector("input[type=text]");
    //dom트리의 button객체 찾기
    var btObj = document.querySelector("button");
    //button객체가 click 이벤트가 발생했을 때 function()이 자동호출
    btObj.addEventListener("click",function() {
        alert('클릭되었습니다');
        //다른 조작하고 싶을 때
        txtObj.value = '클릭되었습니다';
    });
});
//onclick속성 사용 안함