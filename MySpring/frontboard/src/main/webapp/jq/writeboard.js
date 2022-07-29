	$(function () {
	//--이미지첨부파일 변경될때  미리보기 START--
	$("div.write>form>div.data>input[name=imageFile]").change(function () {
		let file = this.files[0];
		$("div.image>img.preview").attr("src", URL.createObjectURL(file));
	});
	//--이미지첨부파일 변경될때  미리보기 END--

	//--글쓰기 버튼 클릭 START--
	let $btObj = $("div.write form input[type=button]");
	$btObj.click(function () {
		let $writeFormObj = $("div.write form");
		let formData = new FormData($writeFormObj[0]);
		// 파일 첨부, 업로드가 되기 위하여선 반드시 formData객체가 필요함
		// div.write의 후손을 form 을 강제로 만드는 것?!
		// 제목, 내용 입력 받아야 함 -> 모두 form 태그 안에 있음 -> form 태그는 div class="write" 안에 있음
		// form 객체의 내용을 formData객체로 만들었기 때문에 내용 정보들이 다 들어있음
		let obj = {};
		obj["fr"] = "bonjour";
		obj["en"] = "hello";
		obj["ko"] = "안녕하세요";
		//formData.append("greeting", new Blob([ JSON.stringify(obj) ], {type : "application/json"}));
		formData.append("greeting", JSON.stringify(obj));
		// formData 객체에 임의로 append메서드 이용하여 이름, 값으로 저장 할 수 있음
		// fr - bongjour, en-hello, ko - 안녕하세요
		// formData에는 객체를 저장 할 수 없음
		// stringify() : js객체 -> json문자열로 변환하는 메서드. obj객체의 내용을 json 문자열로 변환하여 greeting에 넣어줌
		// formData 안에 제목정보, 내용정보, append를 한 greeting 정보도 들어있음
		// 제목의 name값이 key , 입력값이 value. 내용의 name값이 key, 입력값이 value, greeting이 key, json.stringify value

		formData.forEach(function (value, key) {
		console.log(key + ":" + value);
		});

		// let obj2 = formData.get("greeting");
		// console.log(obj2);
		let obj2 = formData.get("imageFile");
		console.log(obj2);
		$.ajax({
		url: "/backboard/writeboard",
		method: "post",
		processData: false, //파일업로드용 설정
		contentType: false, //파일업로드용 설정
		data: formData, //파일업로드용 설정

		cache: false, //이미지 다운로드용 설정
		xhrFields: {
			//이미지 다운로드용 설정
			responseType: "blob",
		},
		success: function (responseData) {
			let $img = $("div.image>img.downloadview");
			let url = URL.createObjectURL(responseData);
			$img.attr("src", url);
		},
		error: function (jqXHR, textStatus) {
			//응답실패
			alert("에러:" + jqXHR.status);
		},
		});
		return false;
	});
	//--글쓰기 버튼 클릭 END--
	});
