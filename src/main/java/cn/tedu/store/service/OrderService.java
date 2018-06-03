package cn.tedu.store.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.bean.Goods;
import cn.tedu.store.bean.Order;
import cn.tedu.store.bean.OrderItem;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.mapper.GoodsMapper;
import cn.tedu.store.mapper.OrderMapper;
import cn.tedu.store.vo.CartVo;

@Service
public class OrderService implements IOrderService {
	@Resource
	private CartMapper cartMapper;
	@Resource
	private OrderMapper orderMapper;
	@Resource
	private GoodsMapper goodsMapper;
	
	public List<CartVo> getOrderByIds(Integer uid, Integer[] ids) {
		// 0.定义数组，orderList
		// 1.调用持久层方法selectCart()，得到集合
		// 2.遍历集合，得到CartVo对象，获取id
		// 3.id和数组的id值比较，如果相同,把对象添加到orderList中。
		// 4.return orderList;
		List<CartVo> orderList = new ArrayList<CartVo>();
		//先前存入的购物车CartVo对象的集合，被存在数据库中。
		List<CartVo> UserCartList = cartMapper.selectCart(uid);
		for (CartVo cv : UserCartList) {
			//ids是页面上用户勾选中要买的产品id数组
			for (Integer id : ids) {
				if (cv.getId()==id) {
					orderList.add(cv);
					break;//找到后，其余勾选的id不需要再比较。
				}
			}
		}
		return orderList;
	}
	public void addOrder(Order order, List<CartVo> listVo) {
		System.out.println("OrderService.addOrder()");
		orderMapper.insertOrder(order);
		for (CartVo cart : listVo) {
			OrderItem orderItem = new OrderItem();
			orderItem.setUid(cart.getUid());
			orderItem.setGoodsid(cart.getGoodsId());
			orderItem.setImage(cart.getImage());
			orderItem.setPrice(cart.getPrice());
			orderItem.setCount(cart.getCount());
			orderItem.setPaymentstatus(0);
			orderItem.setOrderstatus(0);
			orderItem.setOrderid(order.getId());
			orderMapper.insertOrderItem(orderItem);
			//根据id查询商品信息
			Goods goods = goodsMapper.selectGoodsById(Integer.parseInt(cart.getGoodsId()));
			//修改库存
			goodsMapper.updateNumById(cart.getGoodsId(), goods.getNum()-cart.getCount());
		}
	}
	public void updateItem(Integer oid) {
		orderMapper.updatePaymentStatusByOrderId(oid);
	}
	

}
