package cn.itcast.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.factory.BasicFacotry;
import cn.itcast.service.OrderService;
import cn.itcast.utils.PaymentUtil;
/**
 * 易宝支付回调地址,该Servlet会在支付成功后 进行调用----- 支付公司 、客户
 * @author wcq
 *
 */
@WebServlet("/CallBackServlet")
public class CallBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获得回调所有数据
				String p1_MerId = request.getParameter("p1_MerId");
				String r0_Cmd = request.getParameter("r0_Cmd");
				String r1_Code = request.getParameter("r1_Code");
				String r2_TrxId = request.getParameter("r2_TrxId");
				String r3_Amt = request.getParameter("r3_Amt");
				String r4_Cur = request.getParameter("r4_Cur");
				String r5_Pid = request.getParameter("r5_Pid");
				String r6_Order = request.getParameter("r6_Order");
				String r7_Uid = request.getParameter("r7_Uid");
				String r8_MP = request.getParameter("r8_MP");
				String r9_BType = request.getParameter("r9_BType");
				String rb_BankId = request.getParameter("rb_BankId");
				String ro_BankOrderId = request.getParameter("ro_BankOrderId");
				String rp_PayDate = request.getParameter("rp_PayDate");
				String rq_CardNo = request.getParameter("rq_CardNo");
				String ru_Trxtime = request.getParameter("ru_Trxtime");
				// 身份校验 --- 判断是不是支付公司通知你
				String hmac = request.getParameter("hmac");
				String keyValue = ResourceBundle.getBundle("merchantInfo").getString("keyValue");
				
				// 自己对上面数据进行加密 --- 比较支付公司发过来hamc
				boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,r8_MP, r9_BType, keyValue);
				//由于网络传输的东西可能会被篡改,所以进行校验
				if(isValid){
					//如果数据正常,可以相信
					if("1".equals(r9_BType)){
						//浏览器重定向访问回到网站,不能相信
						response.getWriter().write("支付成功!1秒后回到主页...");
						response.setHeader("Refresh", "1;url="+request.getContextPath()+"/index.jsp");
					}else if("2".equals(r9_BType)){
						//服务器点对点通信,可以相信
						OrderService service = BasicFacotry.getFactory().getInstance(OrderService.class);
						//修改订单的支付状态
						service.updateState(r6_Order,1);
					}else{
						throw new RuntimeException("错误的参数信息!"); //由易宝支付返回的结果 
					}
				}else{
					throw new RuntimeException("数据被篡改了"); 
				}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
