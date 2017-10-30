package cn.itcast.service;

import cn.itcast.domain.Users;

public interface UserService {

	void registUser(Users users);
	
	/**
	 * 根据用户名和密码查找用户
	 * @param username 用户名
	 * @param password 密码
	 * @return         
	 */
	Users findUserByNameAndPsw(String username, String password);

}
