<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Estore提示您:</h1>
	服务器出错了哦,亲~~~
	
	<!-- 在页面打印显示错误信息 -->
	<!-- 使用内置对象exception -->
	<h3 >${ pageContext.exception.message }<br> <!-- 打印错误信息 -->  </h3>
		<c:forEach items="${ pageContext.exception.stackTrace }"  var="st">
			${ st }<br>
		</c:forEach>
		
</body>
</html>