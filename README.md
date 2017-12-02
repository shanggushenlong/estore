estore网上商城
==== 
一.estore简单电子商城(基于servlet/jsp基础)
-------  
		(1)用户模块
			1).用户注册(验证码校验 js表单校验 后台数据校验 发送激活邮箱)
			2).用户激活(定时器)
			3).用户登录(记住用户名和密码)
			4).用户注销
		(2)商品模块
			1).商品添加(商品图片的上传---基于文件上传)
			2).商品列表
			3).商品详细信息
			4).购物车 增删查改(Session)
		(3)订单模块
			1).生成订单(事务控制)
			2).订单查询(多表设计 多表查询)
			3).删除订单(事务控制)
		(4)支付模块
			1).在线支付(调用第三方接口)
      测试接口
二.开发条件
-------  
    技术选型:Servlet+jsp+tomcat+mysql;
    软件架构:javaee的经典三层架构  接口+配置文件+工厂类实现解耦
    
    导入第三方jar包
      junit  jstl  beanutils mysql驱动 c3p0 dbutils commons-fileupload
      
    数据库表的设计
      用户表: id 用户名 密码 昵称 邮箱 角色 激活状态 激活码 注册时间
      商品表: id 商品名称 商品单价 库存数量 商品种类 描述信息 图片地址
      订单表: id 收货地址 下单时间 点单金额 所属用户
      订单项: 订单id 商品id 购买数量
      
      在mysql数据库中创建一个estore用户(不是root管理员,权限不同)
      创建新用户：
          create user estore identified by 'estore';
      授权：
          grant all on estore.* to estore
      
      创建的表格
        create table users (
          id int primary key auto_increment,
          username varchar(40),
          password varchar(100),
          nickname varchar(40),
          email varchar(100),
          role varchar(100) ,
          state int ,
          activecode varchar(100),
          updatetime timestamp
        );

         create table products(
          id varchar(100) primary key ,
          name varchar(40),
          price double,
          category varchar(40),
          pnum int ,
          imgurl varchar(100),
          description varchar(255)
        );

        create table orders(
          id varchar(100) primary key,
          money double,
          receiverinfo varchar(255),
          paystate int,
          ordertime timestamp,
          user_id int ,
          foreign key(user_id) references users(id)
        );

        create table orderitem(
          order_id varchar(100),
          product_id varchar(100),
          buynum int ,
          primary key(order_id,product_id), #联合主键,两列的值加在一起作为这张表的主键使用
          foreign key(order_id) references orders(id),
          foreign key(product_id) references products(id)
        );
        
    详细开发[开发流程](https://github.com/shanggushenlong/estore/blob/master/src/%E5%BC%80%E5%8F%91%E6%B5%81%E7%A8%8B.txt)
    
三.效果展示
===
		 (https://github.com/shanggushenlong/estore/blob/master/WebContent/img/addProd.png)
    ![Image text](https://github.com/shanggushenlong/estore/blob/master/WebContent/img/login.png)
    ![Image text](https://github.com/shanggushenlong/estore/blob/master/WebContent/img/addProd.png)
    ![Image text](https://github.com/shanggushenlong/estore/blob/master/WebContent/img/prodList.png)
    ![Image text](https://github.com/shanggushenlong/estore/blob/master/WebContent/img/orderList.png)
    ![Image text](https://github.com/shanggushenlong/estore/blob/master/WebContent/img/cart.png)
    ![Image text](https://github.com/shanggushenlong/estore/blob/master/WebContent/img/pay.png)
          
