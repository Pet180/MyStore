package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.Address;

public interface IAddressService {
	/**
	 * 添加收货地址
	 * @param address
	 */
	void addAddress(Address address);
	/**
	 * 透过uid找到该用户的所有收货地址
	 * @param uid
	 * @return
	 */
	List<Address> getAddressByUid(Integer uid);
	/**
	 * 设置默认
	 * @param uid
	 * @param id
	 */
	void setDefault(Integer uid,Integer id);
	/**
	 * 透过id取得收件人住址
	 * @param id
	 * @return
	 */
	Address getAddressById(Integer id);
	/**
	 * 透过id修改收货地址
	 * @param address
	 */
	void updateAddressById(Address address);
	/**
	 * 删除收件人住址
	 * @param id
	 */
	void deleteAddress(Integer id);
}
