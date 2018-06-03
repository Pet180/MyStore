package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.vo.CartVo;

public interface CartMapper {
	/**
	 * 插入購物車
	 * 
	 * @param cart
	 */
	void insertCart(Cart cart);

	/**
	 * 透過uid找出用戶購物車
	 * 
	 * @param uid
	 * @return
	 */
	List<CartVo> selectCart(Integer uid);

	/**
	 * 透過id刪除购物車
	 * 
	 * @param id
	 */
	void deleteById(Integer id);

	/**
	 * 批量删除购物车，透过id数组
	 * 
	 * @param ids
	 */
	void deleteBactchById(Integer[] ids);

	/**
	 * 修改购物车的商品数量
	 * @param id
	 * @param count
	 */
	void updateCountById(@Param("id") Integer id, @Param("count") Integer count);
}
