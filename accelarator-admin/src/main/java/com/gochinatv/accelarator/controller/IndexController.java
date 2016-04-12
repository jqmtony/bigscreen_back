package com.gochinatv.accelarator.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gochinatv.accelarator.dao.entity.User;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.util.SessionUtils;


@Controller
public class IndexController extends BaseController{
   
	
	@RequestMapping("home")
	public String home(){
		return "login";
	}
	
	
	@RequestMapping("login")
	public String login(HttpServletRequest request,Model model){
		// 如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
		String exception = (String) request.getAttribute("shiroLoginFailure");
		
		// 根据shiro返回的异常类路径判断，抛出指定异常信息
		if (exception != null) {
			if (UnknownAccountException.class.getName().equals(exception)) {
				model.addAttribute("error", "账号不存在");// 最终会抛给异常处理器
			} else if (IncorrectCredentialsException.class.getName().equals(exception)) {
				model.addAttribute("error", "用户名/密码错误");
			} else {
				model.addAttribute("error", "未知错误");
			}
		}
		//此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径，登陆失败还到login页面
		return "login";
	}
	
	 
	@RequestMapping("index")
	public String index(){
		HttpSession session = this.getSession();
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		session.setAttribute(SessionUtils.LOGIN_KEY, user);
		return "index";
	}
	
	/**
	 * 无权限
	 * @return
	 */
	@RequestMapping("denied")
	public String denied(){
		return "redirect:denied";
	}
}
