$(function() {
    // 1. 비동기 일처리
    $.ajax({
//        url:'http://localhost:8888/back/productlist', // 요청 하고 callback function을 실행해라
        url :`${backPath}/productlist`,       
        //type 쓰지 않으면 기본 GET 방식
        success: function(jsonObj){
            //jsonObj은 배열형태, 반복처리하면서
              //div.td 객체를 DOM트리에서 찾아 복사 붙여넣기 (응답받은 여러 상품에 적용해야하기 때문에. 하나의 상품만 X)
              //붙여넣기 한 div.td객체의 하위 객체 중 img 객체의 src 속성값을 상품번호.jpg alt 속성값을 상품명으로 설정
              
              //div.td 객체 찾기
              let $tdObj = $('div.td');
              
              $(jsonObj).each(function(index,item){
                console.log(item.prod_no+":"+item.prod_name+":"+item.prod_price);
                let $copyObj = $tdObj.clone(); //DOM 트리에서 td객체 복제
                let $imgObj = $copyObj.find("img"); // td 객체 복제하여 img 속성 찾기
                $imgObj.attr("src", "../images/" + item.prod_no + ".jpg");
                // alert($imgObj.attr("src"));
                $imgObj.attr("alt", item.prod_name);
                $copyObj.find("li.prod_name").html(item.prod_name);

                // 반복수행하면서 만들어 낸 복제본을 table 마지막 자식으로 추가(붙여넣기)
                $('div.table').append($copyObj); //table객체 찾아 붙여넣기
                //append - last child로 추가 

            });
            $tdObj.hide();
        },
        error: function(jqXHR){
            alert('오류:' + jqXHR.status);  //404, 500, 200-JSON문자열문제
            //JSON 문자열 문제 : 200번 에러코드 
        }
    });

    
    // 2. 1번 작업의 응답결과 오기 전까지(1번 작업이 마무리 되기 전에 2번이 실행될 때) div.td 객체는 1개 밖에 없음 > 원본객체 1개(복제되기 전)
    // 1번이 다 완료된 후 2번이 실행되는 것이 X -> 비동기 작업이기 때문에 1번이 오던말던 2번 작업 실행하러 감 
    
    //div.table 객체 찾기
    let $tableObj = $('div.table');
    //div.td 객체 찾기 
    let $tdObj = $('div.td');
    
    
    //-- div.td 객체 클릭 START --
    // $tdObj.click(function(){ 

    // on() -> 아직 dom트리에 on method가 없다 해도 나중에 추가될 객체에도 이벤트 처리 미리 등록 해 둘 수 있음
    $tableObj.on('click', 'div.td',function(){   
        //상품 상세보기 url요청 > 상세보기 화면을 보여줌
        //ajax로 응답을 받아 응답된 내용을 어디에 보여줄 것인가가 중요
        let src = $(this).find('img').attr('src'); //이 객체의 img 중에 arc 속성 가지고 옴 -> ../images/C0001.jpg
        console.log('src',src);
        let arr = src. split('/'); // 문자열 파싱 -> slice , split
        console.log('arr',arr); 
        let prod_no = arr[arr.length-1].split('.')[0];  //C0001.jpg 
        console.log('prod_no',prod_no);
        // location.href = "http://localhost:8888/back/viewproduct?prod_no=" +prod_no ;
        location.href ="/front/html/viewproduct.html?prod_no=" + prod_no;
    });
    //-- div.td 객체 클릭 START --
});