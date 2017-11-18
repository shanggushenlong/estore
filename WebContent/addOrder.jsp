<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	th {
		background-color: silver;
	}
	#moneyId{
		font-size: 30px;
		color: red;
		font-weight: bold;
	}
</style>
<title>Estore商城</title>
</head>
<body>
	<%--订单页面 --%>
	<h1>增加订单</h1>
	<form action="${ pageContext.request.contextPath }/AddOrderServlet" method="POST">
		收货地址:<textarea rows="5" cols="45" name="receiverinfo"></textarea><br>
		支付方式:<input type="radio" checked="checked">在线支付<br>
		<input type="submit" value="增加订单"> 
	</form>
	<table border="1" width="100%" cellpadding="5px" cellspacing="0px">
			<tr align="center">
				<th>商品名称</th>
				<th>商品种类</th>
				<th>商品单价</th>
				<th>购买数量</th>
				<th>商品总价</th>
			</tr>
			<c:set var="money" value="0"></c:set> <%--设置迭代的变量与初始值 --%>
			<c:forEach items="${sessionScope.cartmap }" var="entry">
				<tr align="center">
					<td>${entry.key.name }</td>
					<td>${entry.key.category }</td>
					<td>${entry.key.price }</td>										
					<td>${entry.value }</td>
					<td>${entry.key.price * entry.value }元</td>
					<c:set var="money" value="${ money + entry.key.price * entry.value }"></c:set>
				</tr>
			</c:forEach>
	</table>
	<div id="moneyId" align="right"> <%--计算所有商品的最后总价,迭代循环,设置一个var变量值 --%>
		总价:${ money }元 <br>
	</div> 
</body>
</html>