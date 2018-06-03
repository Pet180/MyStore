package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.Order;
import cn.tedu.store.vo.CartVo;

public interface IOrderService {
	/**
	 * 取得订单数据
	 * 
	 * @param uid
	 * @param ids
	 * @return
	 */
	List<CartVo> getOrderByIds(Integer uid, Integer[] ids);

	/**
	 * 加入订单
	 * 
	 * @param order
	 * @param listVo
	 */
	void addOrder(Order order, List<CartVo> listVo);

	/**
	 *透过oid修改paymentStatus 
	 * @param oid
	 */
	void updateItem(Integer oid);
}
