package cn.tedu.store.service;

import cn.tedu.store.bean.User;

public interface IUserService {
	/**
	 * 注册用户
	 * @param user
	 */
	void register(User user);
	/**
	 * 确认邮箱是否存在
	 * @param email
	 * @return	若邮箱有存在，则返回true;否则返回false。
	 */
	boolean checkEmail(String email);
	/**
	 * 确认电话是否存在
	 * @param phone
	 * @return	若电话存在，返回true;否则返回false。
	 */
	boolean checkPhone(String phone);
	/**
	 * 确认用户名是否存在
	 * @param username
	 * @return	存在返回true,否则返回false
	 */
	boolean checkUsername(String username);
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	User login(String username,String password);
	/**
	 * 确认密码
	 * @param id
	 * @param oldPwd
	 * @param newPwd
	 */
	void checkPassword(Integer id, String oldPwd,String newPwd);
	/**
	 * 修改个人信息
	 * @param id  (到了Controller层会从session取出)
	 * @param username
	 * @param gender
	 * @param phone
	 * @param email
	 */
	void updateUser(Integer id,String username,Integer gender,String phone,String email);
	/**
	 * 透过id找user对象
	 * @param id
	 * @return
	 */
	User getUserById(Integer id);
	/**
	 * 上傳頭像圖片。
	 * @param image
	 * @param id
	 */
	void updateImageById(String image,Integer id);
}

