<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>first.jsp</title>
</head>
<body>
FIRST JSP. jsp용 java 파일이 자동 만들어짐.

<%-- <%int i; %> 		 //지역변수 자동 초기화 X  
	 <%out.print(i); %>  //지역변수 초기화 되지 않아 사용 불가 --%>
	 
<%-- Scriptlet --%>
<%int i = 99; %>
<%out.print(i); %>

<hr> 
<%-- Expression --%>
<%=i %>

<hr> 
<%---- Declaration --%>
<%! int i; %>
<%=i %> <%-- 99 지역변수가 우선되기 때문에 99 출력 --%>

<hr>
<%=this.i %>
</html>
