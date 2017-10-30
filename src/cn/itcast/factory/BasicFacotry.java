package cn.itcast.factory;
/**
 * 工厂类
 * @author wcq
 *
 */

import cn.itcast.utils.ConfigUtils;

public class BasicFacotry {
	
	//构造方法私有化
	private BasicFacotry(){}  
	
	//内部提供一个静态的实例,这样的结果就是只会有一个实例
	private static BasicFacotry facotry = new BasicFacotry();
	
	//对外提供一个可供外界调用的方法
	public static BasicFacotry getFactory(){
		return facotry;
	}
	
	//对应不同的类型
	@SuppressWarnings("unchecked")
	public <T> T getInstance(Class<T> clz){
		try {
			String infClzName = clz.getSimpleName();
			String impClzName = ConfigUtils.getProp(infClzName); //获取String类型的实现接口的实现类的全路径名 	
			//生成实例
			return (T) Class.forName(impClzName).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
 }




