package test;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.service.CartService;
import cn.tedu.store.service.ICartService;

public class TestCart {
	@Test
	public void test() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		CartMapper cm = ac.getBean("cartMapper",CartMapper.class);
		Cart c = new Cart();
		c.setUid(1);
		c.setGoodsId("100");
		cm.insertCart(c);
	}
	@Test
	public void testServiceAddCart() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		CartMapper cm = ac.getBean("cartMapper",CartMapper.class);
		Cart cart = new Cart();
		cart.setUid(1);
		cart.setGoodsId("10000044");
		cm.insertCart(cart);
	}
	@Test
	public void testSelectCart() {
		AbstractApplicationContext ac = new  ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		CartMapper cm = ac.getBean("cartMapper",CartMapper.class);
		System.out.println(cm.selectCart(1).size());
	}
	@Test
	public void testGetCartByUid() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		ICartService cs = ac.getBean("cartService",ICartService.class);
		System.out.println(cs.getCartByUid(1).size());
	}
	@Test
	public void testDeleteById() {
		AbstractApplicationContext ac = new  ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		CartMapper cm = ac.getBean("cartMapper",CartMapper.class);
//		cm.deleteById(16);
		cm.deleteBactchById(new Integer []{11,12});
	}
	@Test
	public void testServiceDeleteById() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		ICartService cs = ac.getBean("cartService", CartService.class);
//		cs.deleteById(15);
		cs.updateCountById(8, 99);
	}
	@Test
	public void testDeleteBatchById() {
		AbstractApplicationContext ac = new  ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		CartMapper cm = ac.getBean("cartMapper",CartMapper.class);
		cm.deleteBactchById(new Integer[]{14,13});
		
	}
	@Test
	public void testUpdateCountById() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		CartMapper cm = ac.getBean("cartMapper",CartMapper.class);
		cm.updateCountById(20, 99);

	}
	
}
