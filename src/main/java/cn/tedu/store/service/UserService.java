package cn.tedu.store.service;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.exc.PasswordNotMatchException;
import cn.tedu.store.service.exc.UserNameAlreadyExistException;
import cn.tedu.store.service.exc.UserNotFoundException;

@Service
public class UserService implements IUserService {
	@Resource
	private UserMapper userMapper;
	/**
	 * 确认用户名是否存在
	 * 若不存在，则调用userMapper.insert()写入用户信息。
	 * 若存在则抛异常。
	 * @param user
	 */
	public void register(User user) {
		if (userMapper.selectByUsername(user.getUsername()) == null) {
			String salt = "你喜欢编程吗?";
			String newp = DigestUtils.md5Hex(user.getPassword()+salt);
			user.setPassword(newp);
			userMapper.insert(user);
		}else {
			throw new UserNameAlreadyExistException("用户名已存在");
		}
	}
	/**
	 *	确认邮箱是否存在
	 *	存在，则返回true;不存在返回false。 
	 * @param email
	 */
	public boolean checkEmail(String email) {
		return userMapper.selectByEmail(email) > 0;
	}
	/**
	 * 确认电话是否存在
	 * @param phone
	 * @return 若电话存在，返回true;否则返回false。
	 */
	public boolean checkPhone(String phone) {
		return userMapper.selectByPhone(phone) > 0;
	}
	public boolean checkUsername(String username) {
		//调用UserMapper的selectByPhone()
		User user = userMapper.selectByUsername(username);
		if(user == null){
			//若返回null，则回传false。
			return false;
		}else {
			return true;
		}
	}
	public User login(String username, String password) {
		//1.调用userMapper.selectByUsername()，返回user对象
		User user = userMapper.selectByUsername(username);
		//2.判断user对象是否为null
		if (user == null) {
		//3.若为null，抛出UserNotFoundException异常
			throw new UserNotFoundException("该用户名不存在");
		}else {
			//4.若非null，从user对象中在比对password。
			String salt = "你喜欢编程吗?";
			String newP = DigestUtils.md5Hex(password+ salt);
			if (user.getPassword().equals(newP)) {
				//5.比对password若是true，返回user对象。
				return user;
			}else {
				//6.若为false，抛出PasswordNotMatchException
				throw new PasswordNotMatchException("密码错误");
			}
		}
	}
	public void checkPassword(Integer id, String oldPwd, String newPwd) {
		System.out.println("UserService.checkPassword()");
		//1.调用持久层的selectById()，返回user对象
		User user = userMapper.selectById(id);
		System.out.println(user);
		//2.判断user对象不为null，获取当前password
		if (user!=null) {
			//3.获取密码与oldPwd进行判断
			String salt = "你喜欢编程吗?";
			String newOpwd = DigestUtils.md5Hex(oldPwd+salt);
			if (user.getPassword().equals(newOpwd)) {
				//4.密码正确，返回true
				User updateUser = new User();
				updateUser.setId(id);
				updateUser.setPassword(DigestUtils.md5Hex(newPwd+salt));
				//5.调用持久层的update()
				userMapper.update(updateUser);
				System.out.println("密码修改成功");
			} else {
				//6.密码错误，返回false，抛出PasswordNotMatchException
				throw new PasswordNotMatchException("密码错误");
			}
		}
	}
	public void updateUser(Integer id, String username, Integer gender, String phone, String email) {
		System.out.println("UserService.updateUser()");
		//1.User user = new User();
		User user = new User();		
		//2.判断username是否存在？
		User u1 = userMapper.selectByUsername(username);
		System.out.println("u1: "+u1);
		if (u1==null) {
			//3.若为null，表示表中没有重复username。user.setUsername(username);
			user.setUsername(username);
		}else {
			//4.若不为null，再判断...
			User u2 = userMapper.selectById(id);
			System.out.println("u2: "+u2);
			//5.判断用户名是否为登录用户名
			if (u2!=null) {
				if (u2.getUsername().equals(username)) {
					//6.若u2!=null，不作操作。
				} else {
					//7.抛出异常
					throw new UserNameAlreadyExistException("用户名重复，请重新输入");
				}
			}
		}
		//页面有五个文本框可以改变。
		user.setId(id);
		user.setGender(gender);
		user.setPhone(phone);
		user.setEmail(email);
		//8.userMapper.update(user);
		userMapper.update(user);
	}
	//透过id找到user对象
	public User getUserById(Integer id) {
		System.out.println("UserService.getUserById()");
		return userMapper.selectById(id);
	}
	public void updateImageById(String image, Integer id) {
		userMapper.updateImageById(image, id);
	}

}
