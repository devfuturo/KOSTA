$(function(){
    //-----  혼자 결과값 테스트 하는 법
    // let jsonObj =[{p:{prodNo:'C0001',prodName:제주비자림콜드브루}, quantity:1}, {p:{prodNo:'C0002',prodName:아메리카노}, quantity:2},
    // {p:{prodNo:'G0001',prodName:보온병}, quantity:3},{p:{prodNo:'G0002',prodName:텀블러}, quantity:4}]; 

    // $(jsonObj).each(function(index,element){
    //     $copyObj = $itemObj.clone(); // 복제본 만들기
        
    //     let p = element.p ;
    //     $copyObj.find('div.product').html(p); // 후손 찾기
    //     let quantity = element.quantity ;
    //     $copyObj.find('div.quantity').html(quantity);
        
    //     $('div.viewcart').append($copyObj); //부모인 $copyObj viewcart에 추가
    // }); // 배열의 0번부터 마지막 index까지 반복하면서 element 꺼내오는 것
        //------------------

    // ----- 장바구니 보기 START ------
     $.ajax({
       // url: '/back/viewcart',
        url:`${backPath}/viewcart`,
        method: 'get',
        success: function(jsonObj){
            
            //item 객체의 원본 찾는다
            let $itemObj = $('div.viewcart>div.item');
            
            $(jsonObj).each(function(index,element){
                $copyObj = $itemObj.clone(); // 복제본 만들기
                
                let p = element.p ;
                let product = '<ul>';
                product += '<li class="prod_no">상품번호<span>' + p.prodNo + '</span></li>'
                product += '<li class="prod_name">상품명<span>' + p.prodName +'</span></li>'
                product += '<li class="prod_price">상품가격<span>'+ p.prodPrice +'</span></li>'
                product += '</ul>'
                $copyObj.find('div.product').html(product); // 후손 찾기

                let quantity = element.quantity ;
                $copyObj.find('div.quantity').html('수량:<span>'+quantity+'</span>');
                
                $('div.viewcart').append($copyObj); //부모인 $copyObj viewcart에 추가
            }); // 배열의 0번부터 마지막 index까지 반복하면서 element 꺼내오는 것
            
        },
        error: function(jqXHR){
            alert('오류'+jqXHR.status);   
        }
    }); 
     // ----- 장바구니 보기 END ------

    // ----- 주문하기 버튼 클릭 START ------
    $('div.viewcart>div.addorder>button').click(function(){
        //데이터베이스에 누적되도록함
        $.ajax({
//           url:'/back/addorder',
            url:`${backPath}/addorder`,
            method: 'get',
            //요청시에 전달 할 데이터 따로 없음
            success: function(){
                if(jsonObj.status == 1){ // 주문 성공 경우
                    alert(json.msg);
                    // location.href="";// alert 띄우고 지금 사용중인 url 재요청
                    $('nav>a[href="productlist.html"]').trigger('click'); // 주문 성공 시 상품목록으로 가기
                }else if(jsonObj.status == 0){ // 주문실패 1번 케이스) 로그인이 안 되어 주문 불가한 경우
                    //장바구니 넣기 까지는 로긘과 상관 없이 가능하도록
                    alert(json.msg);
                    $('nav>a[href="login.html"]').trigger('click'); // 로그인 화면으로 이동
                }else if(jsonObj.status == -1){ // 주문실패 2번 케이스) 주문 실패 (네트웤, 서버 자원 문제 X는데 주문이 제대로 안 된 케이스)
                //backEnd 테이블 주문 상세 테이블에 자료 추가하다가 실패한 경우(큰 수량 주문 시 자료형 넘어 갈 경우)가 있을 수 있음
                    alert(json.msg);
                    $('nav>a[href="productlist.html"]').trigger('click');
                }
            },
            error: function(jqXHR){
                alert('오류'+jqXHR.status);  
            }
        });
    });

    // ----- 주문하기 버튼 클릭 END ------
});