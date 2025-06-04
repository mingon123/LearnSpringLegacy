<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 상단 시작 -->
<h2 class="align-center">회원제 게시판</h2>
<div class="align-right">
	<c:if test="${!empty user && !empty user.photo}">
	<img src="${pageContext.request.contextPath}/member/photoView.do" width="25" height="25" class="my-photo">
	</c:if>
	<c:if test="${!empty user && empty user.photo}">
	<img src="${pageContext.request.contextPath}/resources/images/face.png" width="25" height="25" class="my-photo">
	</c:if>
	
	<c:if test="${!empty user}">
	<%-- 아이디 또는 별명 표시 --%>
	[<span>${user.userName}</span>]
	<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
	</c:if>
	<c:if test="${empty user}">
	<a href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a>
	<a href="${pageContext.request.contextPath}/member/login.do">로그인</a>
	</c:if>
	<a href="${pageContext.request.contextPath}/main/main.do">홈으로</a>
</div>
<!-- 상단 끝 -->