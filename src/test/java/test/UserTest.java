package test;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;

public class UserTest {
	@Test
	public void testInsert() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		UserMapper um = ac.getBean("userMapper", UserMapper.class);
		User user = new User();
		user.setUsername("admin123");
		user.setPassword("test");
		user.setEmail("24hr@tedu.cn");
		user.setPhone("0920000000");
		um.insert(user);
		System.out.println("写入完成！");
		ac.close();
	}
	@Test
	public void testSelectByUsername() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		UserMapper um = ac.getBean("userMapper", UserMapper.class);
		User user =um.selectByUsername("admin123");
		System.out.println(user);
		ac.close();
	}
	@Test
	public void testRegister() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IUserService us = ac.getBean("userService", IUserService.class);
		User user = new User();
		user.setUsername("admin124");
		user.setPassword("test");
		user.setEmail("23hr@tedu.cn");
		user.setPhone("0922000000");
		us.register(user);
		System.out.println("注册成功");
		ac.close();
	}
	@Test
	public void testSelectByEmail() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		UserMapper um = ac.getBean("userMapper", UserMapper.class);
		System.out.println(um.selectByEmail("23hr@tedu.cn"));
		ac.close();
	}
	@Test
	public void testCheckEmail() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IUserService us = ac.getBean("userService", IUserService.class);
		System.out.println(us.checkEmail("hr@tedu.cn"));
		ac.close();
	}
	@Test
	public void testSelectByPhone() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		UserMapper um = ac.getBean("userMapper", UserMapper.class);
		System.out.println(um.selectByPhone("0920"));
		ac.close();
	}
	@Test
	public void testCheckPhone() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IUserService us = ac.getBean("userService", IUserService.class);
		System.out.println(us.checkPhone("0920"));
	}
	@Test
	public void testCheckUsername() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IUserService us = ac.getBean("userService", IUserService.class);
		System.out.println(us.checkUsername("admin125"));
	}
	@Test
	public void testLogin() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IUserService us = ac.getBean("userService", IUserService.class);
		System.out.println(us.login("wtf", "test"));
	}
	@Test
	public void testPassword() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		UserMapper um = ac.getBean("userMapper", UserMapper.class);
		User user = new User();
		user.setId(10);
//		user.setPassword("111111");
//		user.setUsername("adminIII");
//		user.setGender(1);
//		user.setPhone("13800138132");
		user.setEmail("ggg@hot.cn");
		um.update(user);
	}
	@Test
	public void testSelectById() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		UserMapper um = ac.getBean("userMapper", UserMapper.class);
		User user = um.selectById(1);
		System.out.println(user);
	}
	@Test
	public void testCheckPassword() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IUserService us = ac.getBean("userService", IUserService.class);
		try {
			us.checkPassword(1, "123123", "111111");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void testUpdateUser() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IUserService us = ac.getBean("userService", IUserService.class);
		us.updateUser(10, "PeterJack", 1, "13800138111", "ggg@hot.cn");
	}
	@Test
	public void testUpdateImageById() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		UserMapper um = ac.getBean("userMapper", UserMapper.class);
		um.updateImageById("Test", 11);
	}
	@Test
	public void serviceTestUpdateImageById() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IUserService us = ac.getBean("userService", IUserService.class);
		us.updateImageById("TEST", 11);
	}
}

