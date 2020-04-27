package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

/**
 * @author 张振东
 * @version V1.0
 * @Title:
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date：
 */
public interface UserService {
	boolean regist(User user);

	boolean active(String code);

	User login(User user);
}
