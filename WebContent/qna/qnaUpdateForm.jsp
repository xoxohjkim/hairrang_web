<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function QnaUpdate() {
		window.opener.top.location.href = "qnaUpdate.do?title="
				+ document.getElementById("title").value + "&content="
				+ document.getElementById("content").value + "&no="
		document.getElementById("no").value;
		window.close()
	};
	function QnaCancel() {
		self.close();
	};
</script>
</head>
<body>
	<input type="hidden" value="${title }" id="title">
	<input type="hidden" value="${content }" id="content">
	<input type="hidden" id="no" value="${no}" name="no"> 정말로
	수정하시겠습니까?
	<input type="button" value="수정" onclick="QnaUpdate()">
	<input type="button" value="취소" onclick="QnaCancel()">
</body>
</html>