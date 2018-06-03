package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.bean.Address;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.mapper.DictMapper;

@Service
public class AddressService implements IAddressService {
	@Resource
	private AddressMapper addressMapper;
	@Resource
	private DictMapper dictMapper;

	public void addAddress(Address address) {
		System.out.println("AddressService.address()");
		// 1.设置省市区名称
		String provinceName = dictMapper.selectProvinceNameByCode(address.getRecvProvince());
		String cityName = dictMapper.selectCityNameByCode(address.getRecvCity());
		String areaName = dictMapper.selectAreaNameByCode(address.getRecvArea());
		// 2.从address取得code，调用dictMapper取得name
		// 将省市区串街称一个字串。
		String recvDistrict = provinceName + cityName + areaName;
		// address.setDistric(str);
		address.setRecvDistrict(recvDistrict);
		// 2.isDefault设置
		// 调用selectByUid，返回list。
		List<Address> listAddr = addressMapper.selectByUid(address.getUid());
		// 若list.size()>0，address的isDefault设置值为0，否则为1。
		if (listAddr.size() > 0) {
			address.setIsDefault(0);
		} else {
			address.setIsDefault(1);
		}
		// 3. adddressMapp.insert(address);
		addressMapper.insert(address);
	}

	public List<Address> getAddressByUid(Integer uid) {
		System.out.println("AddressService.getAddressByUid()");
		return addressMapper.selectByUid(uid);
	}

	public void setDefault(Integer uid, Integer id) {
		System.out.println("AddressService.setDefault()");
		// 1.调用持久层方法setCancel()，若方法返回0，抛出RuntimException异常。
		if (addressMapper.setCancel(uid) == 0) {
			throw new RuntimeException("修改失败");
		}
		// 2.调用持久层方法setDefault(),若方法返回0，抛出RuntimException异常。
		if (addressMapper.setDefault(id) == 0) {
			throw new RuntimeException("修改失败");
		}
	}
	public Address getAddressById(Integer id) {
		System.out.println("AddressService.getAddressById()");
		return addressMapper.selectAddressById(id);
	}

	public void updateAddressById(Address address) {
		System.out.println("AddressService.updateAddressById()");
		//???：从address中获取code，然后通过DictMapper提供的方法，得到name，3个name连接成字符串
		address.setRecvDistrict(dictMapper.selectProvinceNameByCode(address.getRecvProvince())
				+dictMapper.selectCityNameByCode(address.getRecvCity())
				+dictMapper.selectAreaNameByCode(address.getRecvArea()));
		//address.setRecvDistrict(???);
		//调用持久层方法updateById(address);
		addressMapper.updateById(address);
	}

	public void deleteAddress(Integer id) {
		System.out.println("AddressService.deleteAddress()");
		addressMapper.deleteAddress(id);
	}
	

}
