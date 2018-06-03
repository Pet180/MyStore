package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.Goods;

public interface GoodsMapper {
	/**
	 * 取得热门商品信息
	 * @param categoryId
	 * @param offset
	 * @param count
	 * @return
	 */
	List<Goods> select(
			@Param("categoryId") Integer categoryId,
			@Param("offset") Integer offset,
			@Param("count") Integer count
			);
	/**
	 * 查询分类商品下的数量
	 * @param categoryId
	 * @return
	 */
	Integer selectCount(Integer categoryId);
	/**
	 * 透过id找到商品信息
	 * @param id
	 * @return
	 */
	Goods selectGoodsById(Integer id);
	/**
	 * 透过id修改商品库存量
	 * @param id
	 * @param num
	 */
	void updateNumById(@Param("id")String id,@Param("num")Integer num);
}
