package cn.tedu.store.controller;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.store.bean.Goods;
import cn.tedu.store.service.IGoodsService;


@RequestMapping("/goods")
@Controller
public class GoodsController extends BaseController{
	@Resource
	private IGoodsService goodsService;
	@RequestMapping("/showSearch.do")
	public String showSearch(
						Integer page,
						Integer categoryId,
						ModelMap map) {
		System.out.println("GoodsController.showSearch()");
		//从index.jsp超级链接进入控制器，page为null，应该显示第一页的数据
		if (page==null) {
			page=1;
		}
		int offset = (page-1)*12;
		List<Goods> goodsList = goodsService.getGoodsByCategory(categoryId, offset, 12);
		int counts = goodsService.getCount(categoryId);
		int pages = counts%12==0?counts/12:counts/12+1;
		map.addAttribute("pages",pages);
		map.addAttribute("count", counts);
		map.addAttribute("goodsList",goodsList);
		map.addAttribute("categoryId", categoryId);
		map.addAttribute("currentPage", page);
		return "search";
	}
	@RequestMapping("/goodsInfo.do")
	public String goodsInfo(Integer goodsId,Integer categoryId,ModelMap map){
		System.out.println("GoodsController.goodsInfo()");
		List<Goods> goodsList = goodsService.getGoodsByCategory(categoryId, 0, 4);
		map.addAttribute("goodsList", goodsList);
		map.addAttribute("goods", goodsService.GetGoodsById(goodsId));
		return "product_details";
		
	}
}
