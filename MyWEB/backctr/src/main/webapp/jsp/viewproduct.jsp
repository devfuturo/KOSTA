<%@page import="com.my.dto.Product"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>viewproduct.jsp</title>
<link rel="stylesheet" href="./css/viewproduct.css"> 
<!-- html태그는 jsp가 결과값을 다 만들어 응답을 한 후에 웹브라우저에서 실행하는 태그. 
	 웹브라우저 차원에서의 경로 기준 (웹브라우저 주소 기준)
	 따라서 back 밑의 css 를 찾아야하고 css 밑의 viewproduct.css찾아야함. -->
	<!--  html에서의 경로 - 웹브라우저 기준
	 jsp에서의 경로 - 서버 기준 -->
</head>
<body>
	<%Product p = (Product)request.getAttribute("p"); %>
	<jsp:include page="../html/header.html"></jsp:include> 
	<!-- jsp:include 태그는 서버단에서 실행됨. 경로를 현재 jsp 기준에서 시작 
			클라이언트(웹브라우저,프론트)에서 실행X-->
	
	<section>
		<article>
			<div class="viewproduct">
				<img src="/front/images/<%=p.getProdNo()%>.jpg" alt="<%=p.getProdName()%>">
				<div class="detail">
					<ul>
						<li>상품번호:<%=p.getProdNo()%></li>
						<li>상품명:<%=p.getProdName()%></li>
						<li>상품가격:<%=p.getProdPrice()%></li>
						
						<li>상품제조일자:<%=p.getProdMfd()%> </li>
						<li>상품상세: <%=p.getProdInfo()%></li>
						<li>수량:
							<input type="number" max="9" min="1" value="1">
						</li>
						<li><button>장바구니 넣기</button></li>
					</ul>
				</div>
			</div>
		</article>
	</section>
	<jsp:include page="../html/footer.html"></jsp:include>
	
</body>
</html>