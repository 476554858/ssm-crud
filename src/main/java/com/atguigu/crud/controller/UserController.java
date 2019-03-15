package com.atguigu.crud.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ys.mail.Mail;
import cn.ys.mail.MailUtils;

import com.atguigu.crud.bean.Cart;
import com.atguigu.crud.bean.Msg;
import com.atguigu.crud.bean.User;
import com.atguigu.crud.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	/**
	 * 登录
	 * @param user
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	public String login(User user,HttpSession session,HttpServletRequest request){
		/*System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		
		User currentUser = userService.login(user);
		if(currentUser!=null){
			session.setAttribute("user_s", currentUser);
			session.setAttribute("cart",new Cart());
			return "jsps/main";
		}else{
			request.setAttribute("msg", "用户名或密码错误或邮箱认证未激活");
			return "jsps/user/login";
		}*/
		Subject currentUser = SecurityUtils.getSubject();
		if(!currentUser.isAuthenticated()){
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
			token.setRememberMe(true);
			
			try {
				currentUser.login(token);
				session.setAttribute("user_s", userService.login(user));
				session.setAttribute("cart",new Cart());
			} catch (Exception e) {
				request.setAttribute("msg", "用户名或密码错误或邮箱认证未激活");
				return "jsps/user/login";
			}
			
		}
				
		return "jsps/main";
	}
	
	/**
	 * 校验用户名
	 * @param username
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="user/checkUserName",method=RequestMethod.POST)
	public Map<String, String> checkUserName(@RequestParam("username")String username,HttpServletRequest request){
	
		 Boolean b = userService.checkUserName(username);
		 Map<String, String> map = new HashMap<String, String>();
	
		 if(b){
			map.put("username", "用户名可用");
			
		 }else{
			map.put("username", "用户名已被占用");
		 }
		 
		return map;
	}
	
	
	/**
	 * 注册
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/user/regist",method=RequestMethod.POST)
	public String regist(@Valid User user,BindingResult result,HttpServletRequest request) throws IOException{
		String uid = UUID.randomUUID().toString().substring(0,6);
		user.setUid(uid);
		user.setCode(uid+uid);
	
		if(result.hasErrors()){
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = result.getFieldErrors();
			for(FieldError fieldError:errors){
				map.put(fieldError.getField(),fieldError.getDefaultMessage());
			}
			request.setAttribute("msg", map);
			
			return "jsps/user/regist";
		}else{
				userService.add(user);
				
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("emailtempe.properties");
		
				Properties prpos = new Properties();
				prpos.load(inputStream);
				
				String host = prpos.getProperty("host");
				String username = prpos.getProperty("username");
				String password = prpos.getProperty("password");
				String from = prpos.getProperty("from");
				String subject= prpos.getProperty("subject");
				String content = prpos.getProperty("content");
			
				Session session = MailUtils.createSession(host, username, password);
			
				String to = user.getEmail();
				
				content = MessageFormat.format(content, user.getCode());
			
				Mail mail = new Mail(from,to,subject,content);
		
				try {
					MailUtils.send(session, mail);
			
				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
				request.setAttribute("msg", "邮件发送成功");
					
		}
			
		return "jsps/msg";
	}
	
	/**
	 * 激活
	 * @param code
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/user/active",method=RequestMethod.GET)
	public String active(@RequestParam("code")String code,HttpServletRequest request){

		boolean b = userService.active(code);
		
		if(b){
			request.setAttribute("msg", "激活成功");
		}else{
			request.setAttribute("msg", "激活失败");
		}
		
		return "jsps/msg";
	}
	
	@RequestMapping("/user/quit")
	public String quit(HttpSession session){
		System.out.println("退出");
		session.removeAttribute("user_s");
		return "jsps/user/login";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
