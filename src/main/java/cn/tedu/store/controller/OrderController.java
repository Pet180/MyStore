package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.store.bean.Address;
import cn.tedu.store.bean.Order;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IOrderService;
import cn.tedu.store.vo.CartVo;

@RequestMapping("/order")
@Controller
public class OrderController extends BaseController {

	@Resource
	private IOrderService orderService;
	@Resource
	private IAddressService addressService;

	/*@RequestMapping("/showOrderConfirm.do")
	public String showOrderConfirm() {
		System.out.println("OrderController.showOrderConfirm()");
		return "orderConfirm";
	}*/

	/*
	 * url:/order/orderConfirm.do 
	 * 请求参数:uid,ids 
	 * 请求方式：get 
	 * 响应方式:forward
	 */
	@RequestMapping("/showOrderConfirm.do")
	public String showOrderConfirm(HttpSession session, Integer[] ids, ModelMap map) {
		System.out.println("OrderController.orderConfirm()");
		// 获取集合
		List<CartVo> listVo = orderService.getOrderByIds(this.getId(session), ids);
		// 设置集合属性
		map.addAttribute("listVo", listVo);
		// 获取地址的集合
		List<Address> listAddress = addressService.getAddressByUid(this.getId(session));
		// 设置lisAddress到map中
		map.addAttribute("listAddress", listAddress);
		session.setAttribute("listVo", listVo);
		System.out.println(listAddress);
		return "orderConfirm";
	}
	/*
	 * url:/order/addOrder.do
	 * request param: session
	 * request method:post
	 * response method:forward
	 * 
	 */
	@RequestMapping("/addOrder.do")
	public String addOrder(HttpSession session){
		System.out.println("OrderController.addOrder()");
		Order order = new Order();
		order.setUid(this.getId(session));
		List<CartVo> listVo = (List<CartVo>) session.getAttribute("listVo");
		orderService.addOrder(order, listVo);
		session.setAttribute("oid", order.getId());
		return "payment";
	}
	/*
	 * url:/order/payment.do
	 * request param:session
	 * request method:get
	 * response method:forward
	 * 
	 */
	@RequestMapping("/payment.do")
	public String payment(HttpSession session){
		System.out.println("OrderController.payment()");
		Integer oid = (Integer) session.getAttribute("oid");
		orderService.updateItem(oid);
		return "pay_success";
		
	}
}
