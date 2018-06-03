package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.bean.Goods;
import cn.tedu.store.mapper.GoodsMapper;
@Service
public class GoodsService implements IGoodsService{
	@Resource
	private GoodsMapper goodsMapper;
	public List<Goods> getGoodsByCategory(Integer cartegoryId, Integer offset, Integer count) {
		return goodsMapper.select(cartegoryId, offset, count);
	}
	public Integer getCount(Integer categoryId) {
		return goodsMapper.selectCount(categoryId);
	}
	public Goods GetGoodsById(Integer id) {
		return goodsMapper.selectGoodsById(id);
	}

}
