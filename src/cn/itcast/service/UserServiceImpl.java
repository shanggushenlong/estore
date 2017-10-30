package cn.itcast.service;

import java.util.UUID;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.Users;
import cn.itcast.exception.MsgException;
import cn.itcast.factory.BasicFacotry;
import cn.itcast.utils.MD5Utils;

public class UserServiceImpl implements UserService{

	private  UserDao dao = BasicFacotry.getFactory().getInstance(UserDao.class);
	
	@Override
	public void registUser(Users users) {
		
		//1.检查用户名是否已经存在,如果已经存在,则提示用户用户已经存在
		if(dao.findUserByName(users.getUsername()) != null){
			throw new MsgException("用户名已经存在");
		}
		//2.添加用户到数据库
		users.setPassword(MD5Utils.md5(users.getPassword()));   //使用MD5加密
		users.setRole("user");
		users.setState(0);
		users.setActivecode(UUID.randomUUID().toString());
		dao.addUser(users);
		//3.发送激活邮件
	}

	@Override
	public Users findUserByNameAndPsw(String username, String password) {
		
		return dao.findUserByNameAndPsw(username,password);
	}

}
