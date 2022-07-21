console.log("THIS IS JS"); 
var a;
console.log(typeof(a)); //undefined
a = 123;
console.log(typeof (a)); //number
a = 'hello';
console.log(typeof(a));//string
a = true;
console.log(typeof(a));//boolean
a = function() {}; //함수도 변수에 대입 가능
console.log(typeof(a));//function
a = {};
console.log(typeof(a));//object