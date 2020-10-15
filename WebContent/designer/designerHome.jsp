<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>designer home</title>
</head>
<body>
	<h1>디자이너</h1>
	<div style="display: block; text-align: center;">
		<c:forEach items="${list }" var="designer">
			<ul>
				<li><a href="designerDetail.do?deNo=${designer.deNo }"><span><img
							src="${designer.dePic }" alt=""></span>
						<div>
							<p>
								<span>${designer.deName }</span>
						</div></a></li>
			</ul>
		</c:forEach>
	</div>
</body>
</html>