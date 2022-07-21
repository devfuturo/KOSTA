var arr = [1, 10, 3];
console.log(arr.length); //3
console.log(arr[0]); //1
arr.push(7); //배열 가장 끝에 추가하는 함수 => 1, 10, 3, 7
for (var i=0; i<arr.length; i++){
    console.log('인덱스'+i, arr[i]);
}
var last = arr.pop(); //배열의 마지막 요소 
console.log('제거된 마지막 요소값', last); //

arr.forEach(function(item, idex){
    console.log( '인덱스'+indexedDB, item);
});


var leng = arr.unshift(9);//배열의 가장 앞에 추가 => 9, 1, 10, 3
console.log('추가된 후의 배열길이', length) //4
arr.forEach(function(item, index){
    console.log('인덱스'+index, item); 
});

var first = arr.shift();
console.log('제거된 처음 요소 값', first);

arr.splice(2, 0, 8);  //배열의 index에 추가 2번 index에 8을 추가
arr.forEach(function(item, index){
    console.log('인덱스'+index, item);
});

var index = arr.indexOf(1); //arr.indexOf(99); //반환값-1
console.log('값1의 인덱스 위치',index);

var arr2 = ['JAVA', 'HTML','CSS', 'JS'];
var str = arr2.join(); //배열 요소들을 문자열로 만듦 'JAVA,HTML,CSS,JS'
console.log(str);

//문자열을 배열로 바꾸기
var srt1 = 'HELLO';
var arr3 = str.split('');
console.log(arr3);

var arr4 = str.split(',');
console.log(arr4);