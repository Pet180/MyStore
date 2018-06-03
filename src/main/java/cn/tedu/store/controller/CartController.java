package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.vo.CartVo;

/*
 * url: /cart/addCart.do
 * 请求参数:uid(session),goodsId,count(根据页面分析)
 * 请求方式：get
 * 响应方式:responseBody不需要返回视图，只需显示添加页面讯息
 */
@RequestMapping("/cart")
@Controller
public class CartController extends BaseController {
	@Resource
	private ICartService cartService;
	@RequestMapping("/addCart.do")
	@ResponseBody
	public ResponseResult<Void> addCart(HttpSession session, String goodsId,Integer count){
		System.out.println("CartController.addCart()");
		//1.创建rr对象
		ResponseResult<Void> rr = new ResponseResult<Void>();
		//2.调用业务层方法
		Cart cart = new Cart();
		cart.setUid(this.getId(session));
		cart.setGoodsId(goodsId);
		System.out.println("goodsId:"+goodsId);
		cart.setCount(count);
		cartService.addCart(cart);
		//3.设置状态码与信息
		rr.setState(1);
		rr.setMessage("成功加入购物车");
		return rr;
		
	}
	/*
	 * 显示购物车
	 * url:/cart/showCart.do
	 * 请求参数:uid(session)
	 * 请求方式:get
	 * 响应方式:forward
	 */
	@RequestMapping("/showCart.do")
	public String showCart(HttpSession session,ModelMap map){
		System.out.println("CartController.showCart()");
		//1.调用业务层的方法
		List<CartVo> listCartVo = cartService.getCartByUid(this.getId(session));
		//2.将信息加入ModelMap
		map.addAttribute("listCartVo", listCartVo);
		System.out.println(listCartVo);
		//3.return rr
		//4.将cart.html改为jsp文档
		return "cart";
		
	}
	/*
	 * url:/cart/deleteById.do
	 * 请求参数:id
	 * 请求方式:get
	 * 响应方式:redirect
	 */
	@RequestMapping("/deleteById.do")
	public String deleteById(Integer id){
		System.out.println("CartController.deleteById()");
		cartService.deleteById(id);
		return "redirect:../cart/showCart.do";
		
	}
	/*
	 * url:/cart/deleteBatchById.do
	 * 请求参数:ids
	 * 请求方式:get
	 * 响应方式:redirect
	 */
	@RequestMapping("/deleteBatchById.do")
	public String deleteBatchById(Integer[]ids){
		System.out.println("CartController.deleteBatchById()");
		cartService.deleteBactchById(ids);
		return "redirect:../cart/showCart.do";
	}
	/*
	 * url:/cart/updateCountById.do
	 * 请求参数：id , count
	 * 请求方式:get
	 * 响应方式:responseBody
	 * 
	 */
	@RequestMapping("/updateCountById.do")
	@ResponseBody
	public ResponseResult<Void> updateCountById(Integer id,Integer count){
		System.out.println("CartController.updateCountById()");
		ResponseResult<Void> rr = new ResponseResult<Void>(); 
		System.out.println("id:"+id+" count:"+count);
		cartService.updateCountById(id, count);
		rr.setState(1);
		rr.setMessage("成功修改");
		return rr;
		
	}
}
