$(function() {
    
    //----- 주문내역 보기 START -----

    $.ajax({
        url:'/back/vieworder',
        method:'get',
        success: function(jsonObj) {
            // alert('성공');
            console.log('주문내역 보기 성공');

        // 객체 원본 찾기
        let $orderObj = $('div.vieworder') ;

        let order_no = element.orderNo;
        let order_dt = element.orderDt;
        let prod_no = element.prodNo;
        let order_name= element.prodName;
        let prod_price = element.prodPrice;

        $(jsonObj).each(function(i,element){
            $copyObj = $orderObj.clone();

            let i = element.i;
            let vieworder = '<div>';
            info += <div class="order_no">주문번호</div>
            info += <div class="order_dt">주문일자</div>
            info += <div class="prod_no">상품번호</div>
            info += <div class="prod_name">상품명</div>
            info += <div class="prod_price">가격</div>
            info += '</div>';
            $copyObj.find('div.order_info').html(order_info);
            $copyObj.find('div.order_line').html(order_line);
        
            let order_quantity = element.order_quantity;
            $copyObj.find('div.').html('주문수량'+order_quantity);
            
            $('div.vieworder').append($copyObj);
        });

        },
        error: function(jqXHR){
            alert('주문조회 오류입니다' + jqXHR.status)
        }

    });


});

	// ----- 주문내역보기
	// 요청 서블릿 ViewOrderServlet
	// 요청 url vieworder
	// 요청 방식 get
	// 응답 형식 JSON
	// 응답 구조 [{"orderNo" : 10, 
	//			"orderId":"id9", 
	//			"orderDt":"22/06/21", 
	//			"lines": [
	//			{"orderNo":10,
	//			"orderP":"{"prodNo":"C0001","prodName":"제주비자림콜드브루","prodPrice":1000}", 			
	//			"orderQuantity":3
	//			}, 
	//			{"orderNo":10,
	//			"orderP":"{"prodNo":"G0001","prodName":"보온병","prodPrice":2000}","orderQuantity":3"
	//			}
	//			] 
	//			},
	//			{...}
	//			]