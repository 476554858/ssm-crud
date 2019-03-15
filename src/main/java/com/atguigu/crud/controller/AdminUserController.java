package com.atguigu.crud.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atguigu.crud.bean.AdminUser;
import com.atguigu.crud.bean.User;
import com.atguigu.crud.service.AdminUserService;
import com.atguigu.crud.service.UserService;

@Controller
public class AdminUserController {
	
	@Autowired
	AdminUserService adminUserService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/adminUser/login",method=RequestMethod.POST)
	public String login(User user,HttpSession session,HttpServletRequest request
			) throws ServletException, IOException{
		System.out.println("开始认证");
		/*AdminUser user = adminUserService.login(adminUser);
		if(user!=null&&user.getApassword().equals(adminUser.getApassword())){
			session.setAttribute("admin", user);
			return "adminjsps/admin/main";
		}else{
			request.setAttribute("msg", "账号或密码错误");
			return "adminjsps/login";
		}*/
		
		
		Subject currentUser = SecurityUtils.getSubject();
		if(!currentUser.isAuthenticated()){
		
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
			token.setRememberMe(true);
			
			try {
				currentUser.login(token);
				session.setAttribute("user_s", userService.login(user));
			} catch (Exception e) {
				request.setAttribute("msg", "用户名或密码错误");
				return "adminjsps/login";
			}
			
		}
				
		return "adminjsps/admin/main";
		
	}
	
	@RequestMapping("/adminUser/quit")
	public String quit(HttpSession session){
		session.removeAttribute("admin");
		return "adminjsps/login";
	}
}
