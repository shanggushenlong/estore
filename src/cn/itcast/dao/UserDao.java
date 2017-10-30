package cn.itcast.dao;

import cn.itcast.domain.Users;

public interface UserDao {
	/**
	 * 根据用户名查找用户
	 * @param username
	 */
	Users findUserByName(String username);
	
	/**
	 * 向数据库中添加用户
	 * @param users  封装了用户信息的bean
	 */
	void addUser(Users users);
	
	/**
	 * 根据用户名和密码查找用户
	 * @param username 用户名
	 * @param password 密码
	 * @return         
	 */
	Users findUserByNameAndPsw(String username, String password);

}
