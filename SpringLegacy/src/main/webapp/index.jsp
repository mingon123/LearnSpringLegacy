<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC</title>
</head>    
<body>       
<a href="${pageContext.request.contextPath}/hello.do">HelloController</a><br>
<a href="${pageContext.request.contextPath}/search/internal.do?query=blue">SearchController - internal.do</a><br><!-- required=false 작성시 ?query=blue 를 삭제해도 실행됨 -->


</body>
</html>