$(function(){
    // alert(window.location.search);
    let queryString = location.search.substring(1); //0번 인덱스는 '?' 이니까 1번째 인덱스부터 가지고 오도록
    //queryString : 사용자가 입력 데이터를 전달하는 방법중의 하나로, url 주소에 미리 협의된 데이터를 파라미터를 통해 넘기는 것을 말한다.

    $.ajax({
        //url: "/back/viewproduct",
   		url: `${backPath}/viewproduct`,
        method:'get',
        // data: 'prod_no='+'C0001', //요청전달 데이터
        data : queryString,
        success:function(jsonObj){
            if(jsonObj.status == 1){
                // console.log(jsonObj);
                let prod_no = jsonObj.p.prodNo;
                let prod_name = jsonObj.p.prodName;
                let prod_price = jsonObj.p.prodPrice;
                let prod_mfd = jsonObj.p.prodMfd;
                let prod_info = jsonObj.p.prodInfo;
//              $('div.viewproduct>img').attr('src','../images/'+ prod_no+'.jpg');
                //:
                $('div.viewproduct>img').attr('src', '../images/' + prod_no + '.jpg').attr('alt', prod_name);
                $('div.viewproduct ul>li>span.prod_no').html(prod_no);
                $('div.viewproduct ul>li>span.prod_name').html(prod_name);
                $('div.viewproduct ul>li>span.prod_price').html(prod_price);
                $('div.viewproduct ul>li>span.prod_mfd').html(prod_mfd);
                $('div.viewproduct ul>li>span.prod_info').html(prod_info);
            }else {
                alert(jsonObj.msg);
            }
        },
        error: function(){
            alert('오류:'+jqXHR.status);
        
        }
    });
    
    // ----- 장바구니 넣기 버튼 클릭 START -----
    $('div.viewproduct ul>li>button').click(function(){
        let prod_no = $('div.viewproduct ul>li>span.prod_no').html(); // text (맞는지모름)'상품번호 :'  가지고옴
        let quantity = $('div.viewproduct ul>li>input[name=quantity]').val(); // value로 가지고 와야함
        // alert("장바구니버튼 클릭:");
        $.ajax({
     	 	//url: '/back/addcart',
      		url:`${backPath}/addcart`,
            method:'get',
            data:{prod_no: prod_no, quantity: quantity},
            success : function(){ // 응답결과 무시
                ///viewproduct.html 파일에 장바구니 추가 시 보여 줄 작은 div창을 가려뒀다가 이 때 보여주면 됨   
                //모달창? div영역이 화면에 보이는 동안에 다른 영역을 선택하지 못하도록 하는 방법도 있음
                $('div.viewproduct div.result').show(); // 가려져 있다가 성공하면 div창 보여지는 것
            },
            error : function(jqXHR){
                alert('오류'+jqXHR.status);
            }
        });
        return false;
    });
    // ----- 장바구니 버튼 클릭 END -----


    // ----- 상품 목록보기 버튼 클릭 START -----
    $('div.result>button.productlist').click(function(){
        $('nav>a[href="productlist.html"]').trigger('click'); // 메뉴의 클릭이벤트 강제 발생
    });
    // ----- 상품 목록보기 버튼 클릭 END -----

    
    //----- 장바구니 보기 버튼 클릭 START -----
    $('div.result>button.viewcart').click(function(){
        $('nav>a[href="viewcart.html"]').trigger('click');
    });
    //----- 장바구니 보기 버튼 클릭 END -----

});