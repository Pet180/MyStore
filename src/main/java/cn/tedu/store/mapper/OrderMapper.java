package cn.tedu.store.mapper;

import cn.tedu.store.bean.Order;
import cn.tedu.store.bean.OrderItem;

public interface OrderMapper {
	/**
	 * 插入订单
	 * @param order
	 */
	void insertOrder(Order order);
	/**
	 * 插入订单详情
	 * @param orderItem
	 */
	void insertOrderItem(OrderItem orderItem);
	/**
	 * 透过orderid修改付款状态
	 * @param oid
	 */
	void updatePaymentStatusByOrderId(Integer oid);
}
