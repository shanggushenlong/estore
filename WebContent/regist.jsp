<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  	<script type="text/javascript">
  		function changeOne(obj){
  			obj.src = "${pageContext.request.contextPath}/ValiImgServlet?time="+new Date().getTime();
  		}
  		
  		function checkForm(){
  			var canSub = true;
			canSub = checkNull("username","用户名不能为空") && canSub;  			
			canSub = checkNull("password","密码不能为空") && canSub; 	
			canSub = checkPsw2() && canSub;	
			canSub = checkNull("nickname","昵称不能为空") && canSub;
			canSub = checkEmail() && canSub; 	
			canSub = checkNull("valistr","验证码不能为空") && canSub;		
  			
  			return canSub;
  		}
  		
  		function checkNull(name,msg){
  			setMsg(name,"");
  			var username = document.getElementsByName(name)[0].value;
  			if(username == ""){
  				setMsg(name,msg);
  				return false;
  			}
  			return true;
  		}
  		
  		function checkPsw2(){
  			var flag = checkNull("password2","确认密码不能为空！");
  			if(flag){
  				var psw = document.getElementsByName("password")[0].value;
				var psw2 = document.getElementsByName("password2")[0].value;
	  			if(psw!="" && psw2!=""){
	  				if(psw != psw2){
	  					setMsg("password2","两次密码不一致！");
	  					flag = false;
	  				}
	  			}
  			}
  			return flag;
  		}
  		
  		function checkEmail(){
  			var flag = checkNull("email","邮箱不能为空！");
  			if(flag){
  				var email = document.getElementsByName("email")[0].value;
  				if(!/^\w+@\w+(\.\w+)+$/.test(email)){
  					setMsg("email","邮箱格式不正确！");
  					flag = false;
  				}
  			}
  			return flag;
  		}
  		
  		function setMsg(name,msg){
  			document.getElementById(name+"_msg").innerHTML="<font color='red'>"+msg+"</font>";
  		}
  	</script>
  </head>
  <body>
  	<center>
  	<h1>Estore电子商城_注册</h1><hr>
  	<font color="red">${msg }</font>
  	<form action="${pageContext.request.contextPath }/RegistServlet" method="POST" onsubmit="return checkForm()">
		<table>
			<tr>
				<td>用户名:</td>
				<td>
					<input type="text" name="username" value="${param.username }" onblur="checkNull('username','用户名不能为空')"/>
					<span id="username_msg"></span>
				</td>
			</tr>
			<tr>
				<td>密码:</td>
				<td>
					<input type="password" name="password" onblur="checkNull('password','密码不能为空')"/>
					<span id="password_msg"></span>
				</td>
			</tr>
			<tr>
				<td>确认密码:</td>
				<td>
					<input type="password" name="password2" onblur="checkPsw2()"/>
					<span id="password2_msg"></span>
				</td>
			</tr>
			<tr>
				<td>昵称:</td>
				<td>
					<input type="text" name="nickname" value="${param.nickname }" onblur="checkNull('nickname','昵称不能为空')"/>
					<span id="nickname_msg"></span>
				</td>
			</tr>
			<tr>
				<td>邮箱:</td>
				<td>
					<input type="text" name="email" value="${param.email }" onblur="checkEmail()"/>
					<span id="email_msg"></span>
				</td>
			</tr>
			<tr>
				<td>验证码:</td>
				<td>
					<input type="text" name="valistr" onblur="checkNull('valistr','验证码不能为空')"/>
					<span id="valistr_msg"></span>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="注册用户"/></td>
				<td><img src="${pageContext.request.contextPath }/ValiImgServlet" onclick="changeOne(this)" style="cursor: pointer;"/></td>
			</tr>
		</table>  	
  	</form>
  	</center>
  	<hr>
  </body>
</html>






















