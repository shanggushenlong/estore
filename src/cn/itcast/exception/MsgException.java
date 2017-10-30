package cn.itcast.exception;
//自定义异常类
public class MsgException extends RuntimeException{
	
	public MsgException(){};
	
	public MsgException(String msg){
		super(msg);   //执行父类的构造方法
	}
	
}
