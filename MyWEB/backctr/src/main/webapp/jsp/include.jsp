<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include.jsp</title>
</head>
<body>
<%int i = 99; %>
<%-- <div style ="border:1px solid">
	<%@include file="include_sub.jsp" %>
// jsp용 .java파일에 포함 %@include
</div> --%>
<div style ="border:1px solid">
	<jsp:include page="include_sub.jsp"></jsp:include>
<!-- // 실행되어 실행 결과값만 포함하는 것이 jsp:include -->
<!-- 미리선언되어있는 지시자 무시하고 따로 변수 지정하여 사용하고 싶다 : include태그 -->
</div> 
</body>
</html>