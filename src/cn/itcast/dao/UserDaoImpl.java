package cn.itcast.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.itcast.domain.Users;
import cn.itcast.exception.MsgException;
import cn.itcast.utils.DaoUtils;

public class UserDaoImpl implements UserDao{

	@Override
	public Users findUserByName(String username) {
		String sql = "select * from users where username = ?";
		
		QueryRunner runner = new QueryRunner(DaoUtils.getSource());
		
		try {
			return runner.query(sql, new BeanHandler<Users>(Users.class),username) ;
		} catch (SQLException e) {
			throw new MsgException();
		}
	}

	@Override
	public void addUser(Users users) {
		String sql = "insert into users values (null,?,?,?,?,?,?,?,null)";
		
		QueryRunner runner = new QueryRunner(DaoUtils.getSource());

		try {
			runner.update(sql,users.getUsername(),users.getPassword(),users.getNickname(),users.getEmail(),users.getRole(),users.getState(),users.getActivecode());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MsgException();
		};
	}

	@Override
	public Users findUserByNameAndPsw(String username, String password) {
		String sql = "select * from users where username = ? and password =?";
		QueryRunner runner = new QueryRunner(DaoUtils.getSource());
		
		try {
			return runner.query(sql, new BeanHandler<Users>(Users.class),username,password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MsgException();
		}
	}

}
