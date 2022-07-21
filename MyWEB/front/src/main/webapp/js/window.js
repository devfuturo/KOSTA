//DOM트리 완성까지 기다리기(dom트리에 완벽히 접근이 불가하기 때문)
// 미리 만들어진 meta태그 정도에만 접근 가능
function displayTime(element){
		var today = new Date();
		var month = today.getMonth()+1;
		var date = today.getDate();
			
		var now = '<h1>';
		now += today.getFullYear();
		now += '-';
		now += month<10? '0'+month : month;
		now += '-';
		now += date<10? '0'+date : date;
		now += '-';
		now += today.getHours() + ':' + today.getMinutes() + ':' + today.getSeconds();
		now += '</h1>';
		element.innerHTML = now;
}

window.addEventListener('load', function(){ //load이벤트 실행되면 접근 준비 되었다는 뜻
    var windowID; //undefined자료값
    var btClose = this.document.querySelector('button.close');
    // ----- 새창띄우기 버튼 클릭 START -----
    var btOpen = 
    	this.document.querySelector('button.open');
    btOpen.addEventListener('click',function(){
        var url = 'js_popup.html'; 'http://www.google.com/';
        var target = 'first';
        var features = 'width=300px, height=300px';
        windowID = window.open(url, target, features);
        this.style.display = 'none'; // 새창띄우기버튼 사라짐
        btClose.style.display = 'inline'; //새창 닫기버튼 보여짐
    });
    // ----- 새창띄우기 버튼 클릭 END -----
    
    // ----- 새창닫기 버튼 클릭 START -----
     btClose.addEventListener('click',function(){
        windowID.close();
        this.style.display = 'none'; // 새창닫기버튼 사라짐
        btOpen.style.display = 'inline'; //새창 띄우기버튼 보여짐
    });
    // ----- 새창닫기 버튼 클릭 END -----
    
		// ----- 5초 후 버튼 클릭 START -----
		var btTimeout = document.querySelector("button.timeout");
		btTimeout.addEventListener('click',function(){
			window.setTimeout(function(){
				alert('5초가 지났습니다');
			}, 5*1000);
		});
		// ----- 5초 후 버튼 클릭 END -----

		// ----- 현재 시간값 출력 (div객체를 찾아) START -----
		var divObj = document.querySelector('div');

		displayTime(divObj);
		// ----- 현재 시간값 출력 (div객체를 찾아) END -----
		

		// ----- 1초간격 버튼 클릭 START -----
		var intervalID;
    var btInterval = document.querySelector('button.interval');
		btInterval.addEventListener('click', function(){
			// window.setInterval(function(){
			// 	displayTime(divObj);
			// }, 1000);
			intervalID = window.setInterval(displayTime,1000,divObj);
		});
		// ----- 1초간격 버튼 클릭 END -----

    // ----- 1초간격 해제 버튼 클릭 START -----
    var btClear = this.document.querySelector('button.clear');
    btClear.addEventListener('click', function(){
      window.clearInterval(intervalID);
    });
    // ----- 1초간격 해제 버튼 클릭 END -----

  }); 

