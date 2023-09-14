package com.yc.spring.bbs.biz;

import com.yc.spring.bbs.bean.User;
import com.yc.spring.bbs.dao.UserDao;
import org.springframework.stereotype.Service;

@Service
public class UserBiz {

	private UserDao uDao;

	public void create(User user) {
		System.out.println("=======UserBiz.create=======");
//		uDao.insert(user);
	}

	public int modify(User user) {
		System.out.println("=======UserBiz.modify=======");
		return 100;
	}

	public void remove(User user) {
		uDao.delete(user);
	}

}
