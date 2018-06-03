package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.vo.CartVo;

public interface ICartService {
	/**
	 * 添加购物车
	 * @param card
	 */
	void addCart(Cart card);
	/**
	 * 透uid找出所有购物车信息
	 * @param uid
	 * @return
	 */
	List<CartVo> getCartByUid(Integer uid);
	/**
	 * 透过id删除购物车
	 * @param id
	 */
	void deleteById(Integer id);
	/**
	 * 批量删除，透过id数组
	 * @param ids
	 */
	void deleteBactchById(Integer[] ids);
	/**
	 * 透过购物车id修改数量
	 * @param id
	 * @param count
	 */
	void updateCountById(Integer id,Integer count);
}
