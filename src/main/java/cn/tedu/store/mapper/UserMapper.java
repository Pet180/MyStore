package cn.tedu.store.mapper;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.User;
/**
 * 对用户管理模块的持久层完成数据酷的操作
 * @author peterchien
 *
 */
public interface UserMapper {
	/**
	 * 插入用户信息
	 * @param user 从页面获得之用户信息
	 */
	void insert(User user);
	/**
	 * 	根据用户名，确认是否存在
	 * @param username
	 * @return 若有该用户名，返回user对象，若没有该用户名，则返回null。
	 */
	User selectByUsername(String username);
	/**
	 *	透过邮箱查询
	 *	
	 * @param email
	 * @return	若返回1表示邮箱存在;若返回0,邮箱不存在。  
	 */
	Integer selectByEmail(String email);
	/**
	 * 透过电话查询
	 * @param phone
	 * @return	返回1表示电话已存在，返回0表示电话不存在。
	 */
	Integer selectByPhone(String phone);
	/**
	 * 修改用户信息
	 * @param user
	 */
	void update(User user);
	/**
	 * 透过id找用户信息
	 * @param id
	 * @return	返回user对象
	 */
	User selectById(Integer id);
	/**
	 * 保存上傳頭像的數據
	 * @param image
	 * @param id
	 */
	void updateImageById(@Param("image")String image,@Param("id")Integer id);
}
