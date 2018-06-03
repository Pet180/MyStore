package test;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.Address;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;

public class TestAddress {
	@Test
	public void testInsert() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		AddressMapper am = ac.getBean("addressMapper", AddressMapper.class);
		Address a = new Address();
		a.setUid(1);
		a.setRecvName("Peter");
		a.setRecvProvince("test");
		a.setRecvCity("test");
		a.setRecvArea("test");
		a.setRecvAddress("testAddress");
		a.setRecvPhone("09100000");
		am.insert(a);
		System.out.println("插入地址成功");
	}
	@Test
	public void testSelectByUid() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		AddressMapper am = ac.getBean("addressMapper", AddressMapper.class);
		System.out.println(am.selectByUid(1));
	}
	@Test
	public void testAddAddress() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IAddressService as = ac.getBean("addressService", IAddressService.class);
		Address address = new Address();
		address.setId(1);
		address.setUid(1);
		address.setRecvName("Peter");
		address.setRecvProvince("130000");
		address.setRecvCity("130100");
		address.setRecvArea("130102");
		address.setRecvAddress("中鼎大厦");
		address.setRecvPhone("13800138000");
		as.addAddress(address);
	}
	@Test
	public void testGetAddressByUid() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IAddressService as = ac.getBean("addressService", IAddressService.class);
		System.out.println(as.getAddressByUid(1).size());
	}
	@Test
	public void testSetCancel() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		AddressMapper am = ac.getBean("addressMapper", AddressMapper.class);
		System.out.println(am.setCancel(10));
	}
	@Test
	public void testSetDefault() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		AddressMapper am = ac.getBean("addressMapper", AddressMapper.class);
		System.out.println(am.setDefault(3));
	}
	@Test
	public void testServiceSetDefault() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IAddressService as = ac.getBean("addressService", IAddressService.class);
		as.setDefault(10, 5);
	}
	@Test
	public void testSelectAddressById() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		AddressMapper am = ac.getBean("addressMapper", AddressMapper.class);
		System.out.println(am.selectAddressById(1));
	}
	@Test
	public void testGetAddressById() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IAddressService as = ac.getBean("addressService", IAddressService.class);
		System.out.println(as.getAddressById(1));
	}
	@Test
	public void testUpdateById() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		AddressMapper am = ac.getBean("addressMapper", AddressMapper.class);
		Address address = new Address();
		address.setId(4);
		address.setRecvName("小林");
		address.setRecvProvince("110000");
		address.setRecvCity("110100");
		address.setRecvArea("110101");
		address.setRecvAddress("中鼎大厦8层");
		address.setRecvPhone("13900139000");
		address.setRecvTel("");
		address.setRecvZip("");
		address.setRecvTag("家");
		am.updateById(address);
	}
	@Test
	public void testUpdateAddressById() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IAddressService as = ac.getBean("addressService", IAddressService.class);
		Address address = new Address();
		address.setId(4);
		address.setRecvName("小刘");
		address.setRecvProvince("110000");
		address.setRecvCity("110100");
		address.setRecvArea("110101");
		address.setRecvAddress("中鼎大厦7层");
		address.setRecvPhone("13900139000");
		address.setRecvTel("");
		address.setRecvZip("");
		address.setRecvTag("公司");
		as.updateAddressById(address);
	}
	@Test
	public void testDeleteAddress() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		AddressMapper am = ac.getBean("addressMapper", AddressMapper.class);
		am.deleteAddress(7);
	}
	@Test
	public void testServiceDeleteAddress() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IAddressService as = ac.getBean("addressService", IAddressService.class);
		as.deleteAddress(5);
	}
	
}

