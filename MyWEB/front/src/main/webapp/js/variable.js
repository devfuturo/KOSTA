/*
var로 선언하면 재선언 가능, window 객체 Scope에 선언, 함수 scope에 선언 , Hoisting 됨
var 없이 선언하면 재선언 가능, window 객체 scope에 선언, Hoisting 됨
let으로 선언하면 재선언 불가능, block scope에 선언, Hoisting 안됨

*/

var a =10 ;
var a = 'hello';
console.log(window.a); //hello
var f1 = function(){  //이름없는 함수
    var lv = true ;  //함수 Scope에 선언되는 변수 
    aa = true ;     //window 객체 scope에 선언
    a = true;       //window 객체 scope에 재선언
    var f2 = function(){
        aaa = true; //window 객체 scope에 선언
    }
    f2();
}
f1();
// console.log(lv); // ReferenceError : lv is not defined
console.log(window.aa, aa); //true
console.log(window.aaa, aaa); //true
console.log(window.a, a); //true


// let b =10 ;
// let b = 'hello'; // Cannot redeclacre block-scoped variable 'b'
// console.log(b); //hello
let b =10; //undefined
console.log(window.b); //hello
let f2 = function(){
    let bb = false; //함수 scope에 선언 (함수 내에서만 사용)
    let b = false; //함수 scope에 선언
}
f2();
// console.log(bb);// 함수 scope에 선언 후 함수 밖에서 호출 시 에러 (ReferenceError : lv is not defined)
console.log(b);
 
//Hoisting : 사용을 먼저하고 선언을 나중에 하는 방법
aaaa = new Date();
console.log(aaaa);
var aaaa;

bb = new Date(); //ReferenceError : Cannot access 'bb' before initialization
console.log(bb);
let bb;