package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.Goods;

public interface IGoodsService {
	/**
	 * 取得热门商品的信息。
	 * @param cartegoryId
	 * @param offset
	 * @param count
	 * @return
	 */
	List<Goods>getGoodsByCategory(Integer cartegoryId,Integer offset,Integer count);
	/**
	 * 查询分类商品数量
	 * @param categoryId
	 * @return
	 */
	Integer getCount(Integer categoryId);
	/**
	 * 透过id找到商品信息
	 * @param id
	 * @return
	 */
	Goods GetGoodsById(Integer id);
}
