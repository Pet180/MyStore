package test;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.mapper.GoodsMapper;
import cn.tedu.store.service.GoodsService;
import cn.tedu.store.service.IGoodsService;

public class TestGoods {
	@Test
	public void test() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		GoodsMapper gm = ac.getBean("goodsMapper", GoodsMapper.class);
//		System.out.println(gm);
		System.out.println(gm.select(163, 0, 3));
	}
	@Test
	public void testgetGoodsByCategory() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		GoodsMapper gm = ac.getBean("goodsMapper", GoodsMapper.class);
//		System.out.println(gm.select(163,0,3));
//		System.out.println(gm.selectCount(163));
		System.out.println(gm.selectGoodsById(100000424));
	}
	@Test
	public void testService() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IGoodsService gs = ac.getBean("goodsService",IGoodsService.class);
//		System.out.println(gs.getCount(163));		//getCount:46
		System.out.println(gs.GetGoodsById(100000424));

	}
	@Test
	public void testSelectGoodsById() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		GoodsMapper gm = ac.getBean("goodsMapper", GoodsMapper.class);
		System.out.println(gm.selectGoodsById(10000044));
	}
	@Test
	public void testUpdateNumById() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		GoodsMapper gm = ac.getBean("goodsMapper", GoodsMapper.class);
		gm.updateNumById("10000001", 99997);//pass
	}
}
