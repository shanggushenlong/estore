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
		font-size: 35px;
		color: red;
		font-weight: bold;
	}
	.imgC{
		cursor: pointer;    <%--手形状,点击时候变化--%>
	}
	
</style>
<script type="text/javascript">
	<%--当数量栏的输入框的值改变的时候,触发一个js函数,onchange事件--%>
	function changeNum(id,obj,oldNum) {
		var newNum = obj.value;
		if(newNum == 0){
			//设置弹窗:如果返回true(确定),就删除这个商品,跳转到删除的servlet
			if(confirm("确定要删除这件商品吗?")){
				window.location.href = "${pageContext.request.contextPath}/DelCartServlet?id="+id;
				return;
			}else{
				obj.value = oldNum;   //如果返回false,(取消),就将input框内的值改为原有的oldNum的值
				return ;
			}
		}
		if(!/^[1-9][0-9]*$/.test(newNum)){  //使用正则表达式检验是否为数字
			alert("数量只能是数字");
			obj.value = oldNum;
			return;
		}
		//产生一个新的href值
		window.location.href = "${pageContext.request.contextPath}/UpdateCartServlet?id="+id+"&num="+obj.value;
	}
</script>
<title>Estore商城</title>
</head>
<body>
	<h1 align="center">我的购物车</h1><hr>
	<div align="right">
		<a href="${ pageContext.request.contextPath }/index.jsp">回到主页</a>
		<a href="${ pageContext.request.contextPath }/ProdListServlet">继续购物</a>
		<a href="${ pageContext.request.contextPath }/ClearCartServlet">清空购物车</a>
	</div>
	<c:if test="${empty sessionScope.cartmap }">
		<h3 align="center">
			<a href="${pageContext.request.contextPath }/ProdListServlet">购物车空空如也...请去购物吧!</a>
		</h3>
	</c:if>
	<c:if test="${not empty sessionScope.cartmap }">
		<table border="1" width="100%" cellpadding="5px" cellspacing="0px">
			<tr align="center">
				<th>商品图片</th>
				<th>商品名称</th>
				<th>商品种类</th>
				<th>商品单价</th>
				<th>购买数量</th>
				<th>商品总价</th>
				<th>删除</th>
			</tr>
			<c:set var="money" value="0"></c:set> <%--设置迭代的变量与初始值 --%>
			<c:forEach items="${sessionScope.cartmap }" var="entry">
				<tr align="center">
					<td><img src="${ pageContext.request.contextPath }/ImgServlet?id=${entry.key.id}&size=s"></td>
					<td>${entry.key.name }</td>
					<td>${entry.key.category }</td>
					<td>${entry.key.price }</td>										
					<td>
						<img src="${ pageContext.request.contextPath }/img/del.png" class="imgC">      <%--将当前的id和value值传入 --%>
						<input type="text" value="${entry.value }" style="width: 35px;" onchange="changeNum('${entry.key.id }',this,'${entry.value }')">
						<img src="${ pageContext.request.contextPath }/img/add.png" class="imgC">
					</td>
					<td>${entry.key.price * entry.value }元</td>
					<td><a href="${ pageContext.request.contextPath }/DelCartServlet?id=${entry.key.id}">删除</a></td>
					<c:set var="money" value="${ money + entry.key.price * entry.value }"></c:set>
				</tr>
			</c:forEach>
		</table>
		<div id="moneyId" align="right">总价:${ money }元</div>  <%--计算所有商品的最后总价,迭代循环,设置一个var变量值 --%>
	</c:if>
</body>
</html>
	

