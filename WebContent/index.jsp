<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Estore商城</title>
</head>
<body>
	
		<h1>Estore商城</h1>
		<%--通过jsp内置对象sessionScope判断用户是否为null,为空表明用户没有登录 --%>
		<c:if test="${sessionScope.users == null }">
			欢迎回来,游客!<br>
			<a href="${ pageContext.request.contextPath }/regist.jsp">注册</a>
			<a href="${ pageContext.request.contextPath }/login.jsp">登录</a>
		</c:if>
		<c:if test="${sessionScope.users != null }">
			欢迎回来,${ sessionScope.users.username }!
			<a href="${ pageContext.request.contextPath }/addProd.jsp">商品添加</a>
			<a href="${ pageContext.request.contextPath }/ProdListServlet">商品列表</a>
			<a href="${ pageContext.request.contextPath }/LogoutServlet">注销</a>
		</c:if>
</body>
</html>