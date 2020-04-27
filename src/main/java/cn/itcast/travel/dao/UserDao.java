package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

/**
 * @author 张振东
 * @version V1.0
 * @Title:
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date：
 */
public interface UserDao {
	public User finByUsername(String username);
	public void save(User user);

	User findByCode(String code);


	void updateStatus(User user);

	User finByUsernameAndPassword(String username,String password);
}

