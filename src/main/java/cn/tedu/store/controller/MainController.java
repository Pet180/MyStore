package cn.tedu.store.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.store.bean.Goods;
import cn.tedu.store.bean.GoodsCategory;
import cn.tedu.store.service.GoodsService;
import cn.tedu.store.service.IGoodsCategoryService;

@Controller
@RequestMapping("/main")
public class MainController {
	@Resource
	private IGoodsCategoryService goodsCategoryService;
	@Resource
	private GoodsService goodsService;
	/**
	 * 显示首页
	 * @return  index.jsp
	 */
	@RequestMapping("/showIndex.do")
	public String showIndex(ModelMap map){
		System.out.println("MainController.showIndex()");
		//定义集合computerList,
		List<GoodsCategory> computerList = new ArrayList<GoodsCategory>();
		//1.调用getCategoryByParentId(161,0,3),返回二级分类,赋值给computerList；
		computerList = goodsCategoryService.getGoodsCategory(161, 0, 3);
		//2.定义集合category161List;
		//遍历二级分类集合，得到二级分类的对象，
		//从对象中得到id，调用getCategoryByParentId(id,null,null)
		//,返回三级分类集合,把三级分类的集合添加到category161List。
		List<List<GoodsCategory>> category161List = new ArrayList<List<GoodsCategory>>();
		for (GoodsCategory gc : computerList) {
			category161List.add(goodsCategoryService.getGoodsCategory(gc.getId(), null, null));
		}
		map.addAttribute("computerList",computerList);
		map.addAttribute("category161List",category161List);
		//设置热门商品到map中
		List<Goods> goodsList = goodsService.getGoodsByCategory(163, 0, 3);
		map.addAttribute("goodsList", goodsList);
		//3.把两个集合分别设置到map
		return "index";
	}
}
