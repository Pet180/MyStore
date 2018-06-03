package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.bean.Area;
import cn.tedu.store.bean.City;
import cn.tedu.store.bean.Province;

public interface DictMapper {
	/**
	 * 
	 * @return
	 */
	List<Province> selectProvince();
	/**
	 * 透过provincceCode获得所有城市
	 * @param provinceCode
	 * @return
	 */
	List<City> selecCity(String provinceCode);
	/**
	 * 透过cityCode获得所有区县
	 * @param cityCode
	 * @return
	 */
	List<Area> selectArea(String cityCode);
	/**
	 * 透过provincceCode找到对应的省名
	 * @param provinceCode
	 * @return
	 */
	String selectProvinceNameByCode(String provinceCode);
	/**
	 * 透过cityCode获得对应的城市名
	 * @param cityCode
	 * @return
	 */
	String selectCityNameByCode(String cityCode);
	/**
	 * 透过areaCode获取对应的区名
	 * @param areaCode
	 * @return
	 */
	String selectAreaNameByCode(String areaCode);
}
