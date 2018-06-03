package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.bean.Address;

public interface AddressMapper {
	/**
	 * 插入用户地址
	 * @param address
	 */
	void insert(Address address);
	/**
	 * 透过uid找地址
	 * @param uid
	 * @return
	 */
	List<Address> selectByUid(Integer uid);
	//将当前登录用户的所有地址设置is_default=0
	Integer setCancel(Integer uid);
	//将被选中的地址设置is_default=1
	Integer setDefault(Integer id);
	/**
	 * 透过id找到对应的收货人住址
	 * @param id
	 * @return
	 */
	Address selectAddressById(Integer id);
	/**
	 * 透过id修改收货地址
	 * @param address
	 */
	void updateById(Address address);
	/**
	 * 透过id删除收件地址
	 * @param id
	 */
	void deleteAddress(Integer id);
}
