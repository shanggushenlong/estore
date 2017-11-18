package cn.itcast.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 通过这个工具类控制事物
 * @author wcq
 *
 */
public class TransactionManager {
	
	//构造方法私有化
	private TransactionManager(){
	}
	
	//通过引入ThreadLocal类,保证每个线程在自己的内部都有一个线程
	private static ThreadLocal<Connection> conn_local = new ThreadLocal<Connection>(){
		protected Connection initialValue() {
			return DaoUtils.getConn();
		};
	};

	public static void startTran(){
		try {
			conn_local.get().setAutoCommit(false);   //将自己内部线程(connection)事物开启
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static void commit(){
		try {
			conn_local.get().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static void rollback(){
		try {
			conn_local.get().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static Connection getConnection(){
		return conn_local.get();
	}
	public static void release(){
		try {
			conn_local.get().close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		conn_local.remove();
	}
}








