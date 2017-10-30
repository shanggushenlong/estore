package cn.itcast.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import cn.itcast.exception.MsgException;

public class Users implements Serializable{
	
	private int id;
	private String username;
	private String password;
	private String password2;
	private String nickname;
	private String email;
	private String role;
	private int state;
	private String activecode;
	private Timestamp updatetime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getActivecode() {
		return activecode;
	}
	public void setActivecode(String activecode) {
		this.activecode = activecode;
	}
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
	
	public void checkData() throws MsgException {
	
		if("".equals(username)){
			throw new MsgException("用户名不能为空");
		}
		if("".equals(password)){
			throw new MsgException("密码不能为空");
		}
		if("".equals(password2)){
			throw new MsgException("确认密码不能为空");
		}
		if("".equals(nickname)){
			throw new MsgException("昵称不能为空");
		}
		if("".equals(email)){
			throw new MsgException("邮箱不能为空");
		}
		if(!password2.equals(password)){
			throw new MsgException("两次密码不一致");
			
		}
		if(!email.matches("^\\w+@\\w+(\\.\\w+)+$")){
			throw new MsgException("邮箱格式不正确");
		}
		
	}
	
	//
}





























