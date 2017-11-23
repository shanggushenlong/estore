<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Estore商城</title>
<style type="text/css">
	tr{
		text-align: center;
	}
</style>
</head>
<body>
	<h1>订单列表页面</h1>
	<c:forEach items="${ requestScope.list }" var="oi">
			订单id:${ oi.order.id }<br>
			所属用户:${ oi.users.username }<br>
			下单时间:${ oi.order.ordertime }<br>
			收货地址:${ oi.order.receiverinfo }<br>
			支付状态:
				<c:if test="${ oi.order.paystate == 0}">
					<font color="red">未支付</font>
					<a href="${ pageContext.request.contextPath }/DelOrderServlet?id=${oi.order.id}">删除订单</a>
					<a href="#">在线支付</a>
				</c:if>
				<c:if test="${ oi.order.paystate != 0 }">
					<font color="red">已支付</font>
				</c:if>
				<br>
			订单金额:${ oi.order.money }<br>
			<table width="100%" border="1" cellpadding="5px" cellspacing="0px">
				<tr bgcolor="silver">
					<th>商品图片</th>
					<th width="30%">商品id</th>
					<th>商品数量</th>
					<th>购买数量</th>
					<th>金额</th>
				</tr>
				<c:forEach items="${ oi.map }" var="entry">
					<tr>
						<td><img src="${ pageContext.request.contextPath }/ImgServlet?id=${entry.key.id}&size=s"></td>
						<td>${ entry.key.id }</td>
						<td>${ entry.key.name }</td>
						<td>${ entry.value }</td>
						<td>${ entry.key.price * entry.value }元</td>
					</tr>
				</c:forEach>
			</table>
	</c:forEach>
</body>
</html>
