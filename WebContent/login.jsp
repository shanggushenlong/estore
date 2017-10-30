<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	window.onload = function(){
		document.getElementsByName("username")[0].value = decodeURI('${cookie.rename.value}');
	}
	
</script>
<title>Estore登录</title>
</head>
<body>
	<div align="center">
		<h1>Estore登录</h1>
		<form action="${ pageContext.request.contextPath }/LoginServlet" method="post">
			<table>
				<tr>
					<td>用户名:</td>
					<td><input type="text" name="username" /></td>
				</tr>
				<tr>
					<td>密码:</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="remname" value="true" 
						<c:if test="${ cookie.rename != null }">checked='checked'</c:if>
					/>记住用户名</td>
					<td><input type="checkbox" name="autologin" value="true"/>30天内</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="登录" /></td>
				</tr>	
			</table>
		</form>
	</div>	
</body>
</html>