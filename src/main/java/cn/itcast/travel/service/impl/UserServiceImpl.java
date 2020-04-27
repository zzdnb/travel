package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

/**
 * @author 张振东
 * @version V1.0
 * @Title:
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date：
 */
public class UserServiceImpl implements UserService {
	UserDao userDao = new UserDaoImpl();

	//查询用户是否存在
	//添加用户信息
	@Override
	public boolean regist(User user) {
		User u = userDao.finByUsername(user.getUsername());
		if (u != null) {
			return false;
		}

		//设置激活码
		user.setCode(UuidUtil.getUuid());
		user.setStatus("N");
		userDao.save(user);
		//设置激活状态
		String content = "<a href='http://localhost/travel/activeUserServlet?code=" + user.getCode() + "'>点击激活【黑马旅游网】</a>";
		MailUtils.sendMail(user.getEmail(), content, "激活邮件");
		return true;
	}

	//激活用户
	@Override
	public boolean active(String code) {
		//根据激活码查询用户
		User user = userDao.findByCode(code);
		if (user != null) {
			//调用dao的修改激活状态的方法
			userDao.updateStatus(user);
			return true;
		} else {
			return false;
		}


	}

	/**
	 * 登录
	 * @param user
	 * @return
	 */
	@Override
	public User login(User user) {
		return userDao.finByUsernameAndPassword(user.getUsername(),user.getPassword());
	}
}
