package com.gochinatv.accelarator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class IndexController {
   
	
	@RequestMapping(value={"","login"})
	public String login(Model model){
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
