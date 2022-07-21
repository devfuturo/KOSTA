var a;

// 함수 내용 구현
a = function(){
  alert("함수1"); // 경고창을 띄운다.
};

//함수 호출
a();  // -> 경고창 1번


var b; // 변수 선언
b = a; // 대입
b(); // 함수 호출 -> 경고창 2번째

var c;
c = function(p){
  console.log("매개변수p="+p);
  if( typeof(p) == 'function'){ //typeof(p) -> p의 자료형을 보는 것
    p(); //함수호출, 밑에 c에서 매개변수로 a를 넣었기에 a가 한번 더 호출->3회
  }
}
var str = 'hello'; c(str);
var num = 999;     c(num);
c(a); //함수호출. a의 내용이 출력됨 / 주석 포함 출력

c('hello');
c(function(){
  console.log("콜백함수입니다");
});

d = function(p1, p2, p3){
  if( typeof(p1) == 'function'){
    p1(p2,p3);
    //for(var i=p2; i<=p3; i++){
      // p1(i);
    // }
  }
}

d(function(a,b){
  console.log(a, b, a+b);
},1,2); 
// p1 -> function(a,b){
//  console.log(a, b, a+b);
//}
// p2 -> 1 / p3 ->2

d(function(a,b){
  for(var i =a; i<=b; i++){
    console.log(i);
  }
} //function(){console.log(i);}
,1,10);

// 매개변수 개수와 인자의 개수
var e = function(p1,p2){
  console.log(p1,p2);
}
e(1,2);     //1, 2
e(1);      //1, undefined
e(1,2,3); //1, 2

//반환값
var f = function() {
  return true;
}

var f1 = f();
if (f1) {
  console.log('f함수의 반환값은 true입니다');
}
var f2 = f();
console.log(5+f2);//5+true -> 5+1 ->6

var g = function () {
  console.log('반환값 없는 함수입니다');
}
var g1 = g();
console.log('g함수의 반환값:' + g1); //반환 값이 없는 함수를 변수에 담아 출력할 시 undefined
//------------------------------------------------
//outer-inner function  
var h = function () {
  var lv1 = '지역변수1'; //local value lv
  var lv2 = function () {
    var inner = '지역변수2';
    console.log('lv2함수 내부에서 lv1', lv1); //지역변수 1
    console.log('lv2함수 내부에서 inner', inner); //지역변수 2
  }
  lv2();
  console.log('h함수 내부에서 lv1', lv1); //지역변수 1
  // console.log('h함수 내부에서 inner', inner); //Uncaught ReferenceError: inner is not defined
}                                               //중첩된 inner함수에서는 outer함수 사용할 수 없다
h();