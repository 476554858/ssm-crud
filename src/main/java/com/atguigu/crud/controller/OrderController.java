package com.atguigu.crud.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crud.bean.Cart;
import com.atguigu.crud.bean.CartItem;
import com.atguigu.crud.bean.Orderitem;
import com.atguigu.crud.bean.Orders;
import com.atguigu.crud.bean.User;
import com.atguigu.crud.service.OrderService;
import com.atguigu.crud.utils.PaymentUtil;
import com.sun.mail.imap.protocol.UID;


@Controller
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value="/order/add",method=RequestMethod.GET)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Cart cart = (Cart) session.getAttribute("cart");
		
		User user = (User) session.getAttribute("user_s");
		if(user==null){
			
		}
		Orders orders = new Orders();
		String uid = UUID.randomUUID().toString().substring(0,6);
		orders.setOid(uid);
		orders.setAddress("浙江省杭州市滨江区联庄一区");
		orders.setOrdertime(new Date());
		orders.setState(1);
		orders.setTotal(cart.getTotal());
		orders.setUid(user.getUid());
		
		List<Orderitem> listIOrderitems = new ArrayList<Orderitem>();
		
		Map<String, CartItem> map = cart.getMap();
		
		for(Map.Entry<String, CartItem> entry:map.entrySet()){
			Orderitem orderitem = new Orderitem();
			String uid2 = UUID.randomUUID().toString().substring(0,6);
			
			orderitem.setCount(entry.getValue().getCount());
			orderitem.setIid(uid2);
			orderitem.setSubtotal(entry.getValue().getSubTotal());
			orderitem.setBook(entry.getValue().getBook());
			orderitem.setOrder(orders);
			listIOrderitems.add(orderitem);	
		}
		orders.setList(listIOrderitems);
		
		cart.clear();
		request.setAttribute("o", orders);
		
		orderService.add(orders);
		
		return "jsps/order/desc";
	}
	
	/**
	 * 付款
	 * @param pd_FrpId
	 * @param order
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/order/goBank",method=RequestMethod.POST)
	public String goBank(@RequestParam("pd_FrpId")String pd_FrpId,Orders order,HttpServletResponse response,
			HttpServletRequest request) throws IOException{
		
		System.out.println(order.getOid());
		System.out.println(pd_FrpId);
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("merchantInfo.properties");
		Properties props = new Properties();
		props.load(inputStream);
		// 业务类型
				String p0_Cmd = "Buy";
				// 商户编号
				String p1_MerId = props.getProperty("p1_MerId");
				// 商户订单号
				String p2_Order = request.getParameter("oid");
				// 支付金额
				String p3_Amt = "0.01";
				// 交易币种
				String p4_Cur = "CNY";
				// 商品名称
				String p5_Pid = "";
				// 商品种类
				String p6_Pcat = "";
				// 商品描述
				String p7_Pdesc = "";
				// 商户接收支付成功数据的地址
				String p8_Url = props.getProperty("p8_Url");
				// 送货地址
				String p9_SAF = "";
				// 商户扩展信息
				String pa_MP = "";
				// 银行编码
				/*String pd_FrpId = request.getParameter("pd_FrpId");*/
				// 应答机制
				String pr_NeedResponse = "1";

				String keyValue = props.getProperty("keyValue");

				String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
						p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
						pd_FrpId, pr_NeedResponse, keyValue);
		
				StringBuilder url = new StringBuilder(props.getProperty("url"));
				url.append("?p0_Cmd=").append(p0_Cmd);
				url.append("&p1_MerId=").append(p1_MerId);
				url.append("&p2_Order=").append(p2_Order);
				url.append("&p3_Amt=").append(p3_Amt);
				url.append("&p4_Cur=").append(p4_Cur);
				url.append("&p5_Pid=").append(p5_Pid);
				url.append("&p6_Pcat=").append(p6_Pcat);
				url.append("&p7_Pdesc=").append(p7_Pdesc);
				url.append("&p8_Url=").append(p8_Url);
				url.append("&p9_SAF=").append(p9_SAF);
				url.append("&pa_MP=").append(pa_MP);
				url.append("&pd_FrpId=").append(pd_FrpId);
				url.append("&pr_NeedResponse=").append(pr_NeedResponse);
				url.append("&hmac=").append(hmac);
				System.out.println(url.toString());
				response.sendRedirect(url.toString());
		return null;
	}
	
	/**
	 * 付款完成返回地址
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/order/back",method=RequestMethod.GET)
	public String back(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		System.out.println("back"+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
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
		String hmac = request.getParameter("hmac");
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("merchantInfo.properties");
		Properties props = new Properties();
		props.load(inputStream);
		String keyValue = props.getProperty("keyValue");
		boolean bool = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
				r8_MP, r9_BType, keyValue);
		if(!bool){
			request.setAttribute("msg", "订单不正确");
			return "jsps/msg";
		}
		//获取订单状态
		orderService.updateOrder(r6_Order);
		
		if(r9_BType.equals("2")){
			response.getWriter().print("success");
		}
		request.setAttribute("msg", "支付成功");
		
		return "jsps/msg";
	}
	
	/**
	 * 查看用户所有订单
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/order/findAll",method=RequestMethod.GET)
	public String myOrders(HttpSession session,HttpServletRequest request){
		
		User user = (User) session.getAttribute("user_s");
		List<Orders> listOrders = orderService.findAll(user);
		request.setAttribute("orderList", listOrders);
		return "jsps/order/list";
	}
	
	
	/**
	 * 查看一个订单去付款
	 * @param oid
	 * @return
	 */
	@RequestMapping("/order/load/{oid}")
	public String load(@PathVariable String oid,HttpServletRequest request){
		Orders orders = orderService.findOne(oid);
		request.setAttribute("o", orders);
		return "jsps/order/desc";
	}
	
	/**
	 * 确认收货
	 * @param oid
	 * @param request
	 * @return
	 */
	@RequestMapping("/order/confirm/{oid}")
	public String confirm(@PathVariable String oid,HttpServletRequest request){
		
		orderService.confirm(oid);
		request.setAttribute("msg", "交易结束");
		return "jsps/msg";
	}
	
	/**
	 * 管理员查看所有订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/adminOrder/findAll",method=RequestMethod.GET)
	public String adminOrders(HttpServletRequest request){
		List<Orders> orderList = orderService.adminFindAll();
		request.setAttribute("orderList", orderList);
		
		return "adminjsps/admin/order/list";
	}
	
	/**
	 * 发货
	 * @param oid
	 * @return
	 */
	@RequestMapping("/adminOrder/DeliverGoods/{oid}")
	public String DeliverGoods(@PathVariable String oid,HttpServletRequest request){
		Orders order = new Orders();
		order.setOid(oid);
		order.setState(3);
		
		orderService.adminUpdateState(order);
		List<Orders> orderList = orderService.adminFindAll();
		request.setAttribute("orderList", orderList);
		return "/adminjsps/admin/order/list";
	}
	
	
	@RequestMapping(value="/adminOrder/findAllByState",method=RequestMethod.GET)
	public String findAllByState(@RequestParam String state,HttpServletRequest request){
		List<Orders> orderList = orderService.findAllByState(state);
	
		request.setAttribute("orderList", orderList);
		return "/adminjsps/admin/order/list";
	}
	
	
	
	
}
