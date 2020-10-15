<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header.jsp" %>  

<body>
	<h1>문의글 작성</h1>
	<form name="form" method="post" action="qnaWrite.do" enctype="multipart/form-data">
		<label>Title</label>
		<input type="text" name="title"><br>
		<label>Content</label>
		<textarea rows="8" cols="65" name="content"></textarea><br>
		<div id="buttons" style="float: right">
			<input type="submit" value="제출하기" class="submit">
			<input type="reset" value="초기화" class="cancel">
			<input type="file" name="upload">
		</div>
	</form>
</body>
<%@ include file="../footer.jsp" %>  