package com.gochinatv.accelarator.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class IndexController {
   
	
	@RequestMapping("login")
	public String login(HttpServletRequest request,Model model){
		return "login";
	}
	
	
	@RequestMapping("doLogin")
	public String doLogin(HttpServletRequest request,Model model){
		// 如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
		String exception = (String) request.getAttribute("errorCodeFailure");
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
		// 此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径，登陆失败还到login页面
		return "login";
	}
	
	
	 
	@RequestMapping("index")
	public String index(Model model){
		return "index";
	}
	
	
	@RequestMapping("logout")
	public String logout(Model model){
		return "redirect:login";
	}
}
