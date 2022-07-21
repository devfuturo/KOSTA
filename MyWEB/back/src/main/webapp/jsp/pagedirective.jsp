<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.FileInputStream"%> <!-- import로 페이지 지시자 추가 -->
<%@page buffer="1024kb" %> <!-- 버퍼크기 크게 설정 -> autoflush를 못하게 만드는 것 -->
<!-- 이 경우 1부터 10000까지 한번에 출력 가능 --> 
<%@page errorPage="err.jsp" %>  <!-- 마치 try ~ catch를 한 것과 같은 효과 (즉시 err.jsp로 forward됨) -->
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pagedirective.jsp</title>
</head>
<body>
<%for(int i=1; i<=10000; i++){
%><span><%=i%></span>
<%}
%>

<%
FileInputStream fis = null;
fis = new FileInputStream("a.txt");
%>
</body>
</html>