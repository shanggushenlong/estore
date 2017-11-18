<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Estore商城</title>
</style>
</head>
<body>
	<div align="center">
		<h1>商品列表</h1>
		<div align="right">
			<a href="${ pageContext.request.contextPath }/index.jsp">回到主页</a>
			<a href="${ pageContext.request.contextPath }/cart.jsp">我的购物车</a>
		</div>
		<table border="1" width="100%">
			<tr bgcolor="silver">
				<th>商品图片</th>
				<th>商品名称</th>
				<th>商品价格</th>
				<th>库存状态</th>
			</tr>
			<c:forEach items="${ requestScope.list }" var="prod">
				<tr align="center">
					<td><a href="${ pageContext.request.contextPath }/ProdInfoServlet?id=${prod.id}"><img  src="${ pageContext.request.contextPath }/ImgServlet?id=${prod.id}&&size=s"></a></td>
					<td>${ prod.name }</td>
					<td>${ prod.price }元</td>
					<td>
						<c:if test="${ prod.pnum > 0 }"><font color="blue">有货</font></c:if>
						<c:if test="${ prod.pnum <= 0 }"><font color="red"> 无货</font></c:if>
					</td>
				</tr>	
			</c:forEach>			
		</table>
	</div>
</body>
</html>