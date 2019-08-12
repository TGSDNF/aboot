package com.wxw.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxw.model.Admin;
import com.wxw.service.AdminService;
//demo1
@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	/**
	 * 登录跳转
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/login")
	public String loginGet(Model model) {
		return "login";
	}

	/**
	 * 登录
	 * 
	 * @param admin
	 * @param model
	 * @param httpSession
	 * @return
	 */
	@PostMapping("/admin/login")
	public String loginPost(Admin admin, Model model, HttpSession httpSession) {
		Admin adminRes = adminService.findByNameAndPassword(admin);
		if (adminRes != null) {
			httpSession.setAttribute("admin", adminRes);
			return "redirect:dashboard";
		} else {
			model.addAttribute("error", "用户名或密码错误，请重新登录！");
			return "login";
		}
	}
	//验证用户名是否重复
	@ResponseBody
	@PostMapping("/admin/check")
	public Map<String ,Object> checkName(Admin admin) throws Exception{
		Map<String, Object> map = new HashMap<>() ;
		Admin admin1 = adminService.checkName(admin);
		if(admin1 !=null) {
			map.put("msg", "用户名已被使用");
			map.put("code", "300");			
		}else {
			map.put("msg", "");
			map.put("code", "200");
		}
		return map ; 
	}

	/**
	 * 注册
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/register")
	public String registerGet(Model model) {
		return "register";
	}
	@PostMapping("/admin/register")
	public String registerPost(Model model,Admin admin,HttpSession httpSession) {
		 adminService.insert(admin);
		 httpSession.setAttribute("admin", admin);
			return "login";
	}
	
	/**
	 * 仪表板页面
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/dashboard")
	public String dashboard(Model model) {
		return "dashboard";
	}
}
