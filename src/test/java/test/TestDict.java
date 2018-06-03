package test;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.mapper.DictMapper;
import cn.tedu.store.service.DictService;
import cn.tedu.store.service.IDictService;

public class TestDict {
	@Test
	public void testSelectProvince() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		DictMapper dm = ac.getBean("dictMapper",DictMapper.class);
		System.out.println(dm.selectProvince());
		//done
	}
	@Test
	public void testGetProvince() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IDictService ds = ac.getBean("dictService",IDictService.class);
		System.out.println(ds.getProvince());
	}
	@Test
	public void testSelecCity() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		DictMapper dm = ac.getBean("dictMapper", DictMapper.class);
		System.out.println(dm.selecCity("130000"));//河北省的所有城市
	}
	@Test
	public void testGetCity() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IDictService ds = ac.getBean("dictService",IDictService.class);
		System.out.println(ds.getCity("130000"));
	}
	@Test
	public void testSelectArea() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		DictMapper dm = ac.getBean("dictMapper", DictMapper.class);
		System.out.println(dm.selectArea("130200"));
	}
	@Test
	public void testGetArea() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IDictService ds = ac.getBean("dictService", IDictService.class);
		System.out.println(ds.getArea("130200"));
	}
	@Test
	public void testSelectProvinceNameByCode() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		DictMapper dm = ac.getBean("dictMapper", DictMapper.class);
//		System.out.println(dm.selectProvinceNameByCode("130000"));
//		System.out.println(dm.selectCityNameByCode("130100"));
		System.out.println(dm.selectAreaNameByCode("653124"));
	}
	
}
