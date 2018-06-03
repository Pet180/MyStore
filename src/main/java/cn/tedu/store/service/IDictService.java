package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.Area;
import cn.tedu.store.bean.City;
import cn.tedu.store.bean.Province;

public interface IDictService {
	/**
	 * 显示省列表
	 * @return
	 */
	List<Province> getProvince();
	/**
	 * 透过provinceCode找出所有的城市
	 * @param provinceCode
	 * @return
	 */
	List<City> getCity(String provinceCode);
	/**
	 * 透过cityCode取得所有的城市
	 * @param cityCode
	 * @return
	 */
	List<Area> getArea(String cityCode);
}
