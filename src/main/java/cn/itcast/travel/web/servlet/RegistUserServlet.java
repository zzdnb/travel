package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author 张振东
 * @version V1.0
 * @Title:
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date：
 */
@WebServlet("/registUserServlet")
public class RegistUserServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
         String check =request.getParameter("check");
         System.out.println(check);
         //从session中获取验证码
		HttpSession session = request.getSession();
		String checkcode_server=(String)session.getAttribute("CHECKCODE_SERVER");
		System.out.println(checkcode_server);
		session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只使用一次
		if(!checkcode_server.equalsIgnoreCase(check)){
			ResultInfo info = new ResultInfo();
			info.setFlag(false);
			info.setErrorMsg("验证码错误");
			//将info对象转换为json
			ObjectMapper mapper= new ObjectMapper();
			String json=mapper.writeValueAsString(info);
			//将json数剧写回到客户端
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(json);
			return;
		}
//获取数据
		Map<String, String[]> parameterMap = request.getParameterMap();
		User user = new User();
		//封装对象
		try {
			BeanUtils.populate(user, parameterMap);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		UserService userService = new UserServiceImpl();
		boolean flag = userService.regist(user);
		ResultInfo info = new ResultInfo();
		//响应结果
		if (flag) {
			info.setFlag(true);
		} else {
			info.setFlag(false);
			info.setErrorMsg("注册失败");
		}
		//将info对象转换为json
		ObjectMapper mapper= new ObjectMapper();
		String json=mapper.writeValueAsString(info);
		//将json数剧写回到客户端
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);


	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
}
