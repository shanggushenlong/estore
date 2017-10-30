<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Estore商城</title>
</head>
<body>
	<div align="center">
		<h1>商品添加</h1>
		<form action="${ pageContext.request.contextPath }/AddProdServlet" method="POST" enctype="multipart/form-data">
			<table border="1" cellspacing="5px" cellpadding="0px">
				<tr>
					<td>商品名称:</td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>商品单价:</td>
					<td><input type="text" name="price" /></td>
				</tr>
				<tr>
					<td>商品种类:</td>
					<td>
						<select name="category">
							<option>电子数码</option>
							<option>图书杂志</option>
							<option>床上用品</option>
							<option>日用百货</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>库存数量:</td>
					<td><input type="text" name="pnum" /></td>
				</tr>
				<tr>
					<td>商品图片</td>
					<td><input type="file" name="filex" /></td>
				</tr>
				<tr>
					<td>描述信息</td>
					<td><textarea rows="6" cols="40" name="description"></textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="添加商品" />
					</td>
				</tr>
			</table>			
		</form>
	</div>
</body>
</html>



