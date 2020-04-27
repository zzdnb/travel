package cn.itcast.travel.test;

import cn.itcast.travel.dao.impl.UserDaoImpl;
import org.junit.Test;

/**
 * @author 张振东
 * @version V1.0
 * @Title:
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date：
 */
public class JDBCTest {
	@Test
	public void test1(){
		System.out.println(new UserDaoImpl().finByUsername("333"));
	}
}
