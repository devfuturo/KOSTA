var customer = {
    id : 'id1',
    name : '한미래',
    info : function(){
      console.log(this.id,this.name); // 자바스크립트에서는 this. 생략 불가능
      // 객체 내의 function에서 현재 객체의 property접근 하고 싶을 떄 꼭 this. 사용해야함   
    }
};

customer.id = 'id9';
customer.info();

var customerArr = [];
// customerArr[0] = customer;

customerArr.push({
  id : 'id1',
  name : '한미래',

},{
  id : 'id2',
  name : '홍길동',

},{
  id : 'id3',
  name : '황초연',
}
);

console.log(customerArr);
console.log(customerArr[2]);