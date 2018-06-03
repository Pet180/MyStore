package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.bean.Area;
import cn.tedu.store.bean.City;
import cn.tedu.store.bean.Province;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.service.DictService;

@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {
	@Resource
	private DictService dictService;
	@RequestMapping("/showProvince.do")
	@ResponseBody
	public ResponseResult<List<Province>> showProvince(){
		System.out.println("DictController.showProvince()");
		//1.创建rr对象
		ResponseResult<List<Province>> rr = new ResponseResult<List<Province>>();
		rr.setData(dictService.getProvince());
		return rr;
	}
	@RequestMapping("/showCity.do")
	@ResponseBody
	public ResponseResult<List<City>> showCity(String provinceCode){
		System.out.println("DictController.showCity()");
		ResponseResult<List<City>> rr = new ResponseResult<List<City>>();
		rr.setData(dictService.getCity(provinceCode));
		return rr;
	}
	@RequestMapping("/showArea.do")
	@ResponseBody
	public ResponseResult<List<Area>> showArea(String cityCode){
		System.out.println("DictController.showArea()");
		ResponseResult<List<Area>> rr = new ResponseResult<List<Area>>();
		rr.setData(dictService.getArea(cityCode));
		return rr;
	}
	
}
