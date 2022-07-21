$(function(){ 
    //우편번호+주소 객체 찾기
    let $zipAddr = $('table tr'); //table>tbody>tr // 공백(space) 그 밑의 자손 다 찾음
    // let $zipCode = $('td[name=zipcode]'); 

    //우편번호, 주소 클릭 이벤트 발생
    $zipAddr.click(function(){
        let $zipCode = $(this).children('td[name=zipcode]').html(); //this -> $zipCode.click 하는 것 전체가 this임
        //tr($zipAddr)의 자식 중에 td태그를 가지고 name이 zipcode인 것을 보겠다.
        let $addr = $(this).children('td[name=addr]').html();
        //zipcode를 부모의 창에다 보내는 것 
        $("#zipcode", parent.opener.document).val($zipCode);
        $("#addr1", parent.opener.document).val($addr);
        window.self.close();
    });

});