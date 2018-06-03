package test;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.mapper.GoodsCategoryMapper;
import cn.tedu.store.service.GoodsCategoryService;

public class TetsGoodsCategory {
	@Test
	public void testSelectCategoryByParentId() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		GoodsCategoryMapper gcm = ac.getBean("goodsCategoryMapper", GoodsCategoryMapper.class);
		System.out.println(gcm.selectCategoryByParentId(161, 0, 3));
	}
	
	@Test
	public void testGetGoodsCategory() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		GoodsCategoryService gcs = ac.getBean("goodsCategoryService", GoodsCategoryService.class);
		System.out.println(gcs.getGoodsCategory(161, 0, 3));
	}
}
