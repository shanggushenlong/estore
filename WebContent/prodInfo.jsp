<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	#imgDiv{
		float: left;
	}
	#infoDiv{
		font-size: 16px;
		font-weight: bold;
		color: threeddarkshadow;
		float: left;
		position: relative;
		top: 100px;
		left: 40px;
	}
	#buyBmp{ 
		margin-top: 10px; 
	}
</style>
<title>Estore商城</title>
</head>
<body>
	<h2><font color="red">${prod.name}</font></h2><hr>
	<div>
		<div id="imgDiv">
			<img src="${ pageContext.request.contextPath }/ImgServlet?id=${prod.id}"/>
		</div>
		<div id="infoDiv">
			商品名称:${ prod.name }<br>
			商品种类:${ prod.category }<br>
			商品单价:${ prod.price }<br>
			库存数量:${ prod.pnum }<br>
			描述信息:${ prod.description }<br>
			<a href="${ pageContext.request.contextPath }/AddCartSertvlet?id=${prod.id}"><img src="${ pageContext.request.contextPath }/img/buy.bmp" id="buyBmp"/></a>
		</div>
	</div>
	<hr style="clear: both;">  <%--添加css样式,使得hr线不浮动 --%>
</body>
</html>