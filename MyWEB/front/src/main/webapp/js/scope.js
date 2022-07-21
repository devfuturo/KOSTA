var a = 'window 객체의 변수';
console.log(window.a);
console.log(a);

// if블럭, for 블럭 등 블럭 영역으로 변수 구분 안 함
for(var i = 0 ; i<3 ; i++){
    console.log(i); // 0, 1, 2
}
console.log(window.i); // 3 블럭영역으로 변수 구분하지 않기 때문에 반복문이 2에서 끝나고 그 다음 숫자인 3이 출력됨

// 함수영역으로 변수 구분함
var b = function(){
    var lv1 = 'outer local variable';
    console.log(a);
    var lv2 = function(){
        var inner = 'inner local variable';
        console.log(lv1); // outer function variable 
    }
    // console.log(inner); // error
    // function 내에 선언한 변수는 그 function 내에서만 사용 가능
    // outer에서 선언한 변수는 inner에서 사용 가능
}

// console.log(lv1); // error
b();
// console.log(lv1); // error

