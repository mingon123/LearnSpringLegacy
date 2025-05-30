<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 게시판 글수정 시작 -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.css" rel="stylesheet">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#content').summernote({
			height:300
		});
		$('.modal input[type="text"]').css('width',530);
	});
</script>
<div class="page-main">
	<h2>글수정</h2>
	<form:form modelAttribute="boardVO" action="write.do" id="write_form" enctype="multipart/form-data">
		<ul>
			<li>
				<form:label path="title">제목</form:label>
				<form:input path="title"/>
				<form:errors path="title" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="content">내용</form:label>
				<form:textarea path="content"/>
				<form:errors path="content" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="upload">이미지</form:label>
				<input type="file" name="upload" id="upload" accept="image/gif,image/png,image/jpeg">
			</li>
		</ul>
		<div class="align-center">
			<form:button>전송</form:button>
			<input type="button" value="목록" onclick="location.href='list.do'">
		</div>
	</form:form>
</div>
<!-- 게시판 글수정 끝 -->