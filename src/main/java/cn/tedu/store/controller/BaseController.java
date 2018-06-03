package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import cn.tedu.store.bean.User;

public class BaseController {
	//获取用户的id值
	public Integer getId(HttpSession session){
		System.out.println("getId()");
		User user = (User) session.getAttribute("user");
		System.out.println(user);
		System.out.println("----------------------------");
		if (user == null) {
			return null;
		}else {
			return user.getId();
		}
	}
}
