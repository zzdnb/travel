package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author 张振东
 * @version V1.0
 * @Title:
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date：
 */
public class UserDaoImpl implements UserDao {
	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


	@Override
	public User finByUsername(String username) {
		User user = null;
		try {
			String sql = "select * from tab_user where username=?";
			user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);

		} catch (Exception e) {

		}
		return user;

	}

	@Override
	public void save(User user) {
		String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code)values(?,?,?,?,?,?,?,?,?) ";
		template.update(sql, user.getUsername(),
				user.getPassword(),
				user.getName(),
				user.getBirthday(),
				user.getSex(),
				user.getTelephone(),
				user.getEmail(),
				user.getStatus(),
				user.getCode());
	}

	@Override
	public User findByCode(String code) {
		String sql ="select * from tab_user where code=?";
		User user=null;
		try {
			 user=template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),code);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void updateStatus(User user) {
    String sql ="update tab_user set status='Y'where uid=?";
    template.update(sql,user.getUid());
	}

	@Override
	public User finByUsernameAndPassword(String username,String password) {
		User user = null;
		try {
			String sql = "select * from tab_user where username=?and password=?";
			user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);

		} catch (Exception e) {

		}
		return user;

	}
}
