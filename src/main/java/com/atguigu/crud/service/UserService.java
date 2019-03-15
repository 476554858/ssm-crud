package com.atguigu.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crud.bean.User;
import com.atguigu.crud.bean.UserExample;
import com.atguigu.crud.bean.UserExample.Criteria;
import com.atguigu.crud.dao.UserMapper;

@Service
public class UserService {
	
	@Autowired
	UserMapper userMapper;
	
	/**
	 * 校验用户名
	 * @param username
	 * @return
	 */
	public boolean checkUserName(String username) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andUsernameEqualTo(username);
		long count = userMapper.countByExample(userExample);
		return count==0;
	}
	
	/**
	 * 添加用户
	 * @param user
	 */
	public void add(User user) {
	
		userMapper.insert(user);
	}
	
	/**
	 * 激活用户
	 * @param code
	 */
	public boolean active(String code) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andCodeEqualTo(code);
		
		List<User> userList = userMapper.selectByExample(userExample);
		User user = userList.get(0);
		user.setState(true);
		
		int count = userMapper.updateByPrimaryKey(user);
	
		return count!=0;
	}

	/**
	 * 登录
	 * @param user
	 * @return
	 */
	public User login(User user) {
		
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andUsernameEqualTo(user.getUsername());
		criteria.andPasswordEqualTo(user.getPassword());
		criteria.andStateEqualTo(true);
		
		List<User> userList = userMapper.selectByExample(userExample);
		if(userList.size()>0){
			User currentUser = userList.get(0);
			return currentUser;
		}else{
			return null;
		}
			
	}
	
	public User getUserByUserName(String username){
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andStateEqualTo(true);
		
		List<User> userList = userMapper.selectByExample(userExample);
		if(userList.size()>0){
			User currentUser = userList.get(0);
			return currentUser;
		}else{
			return null;
		}
		}
	
	
	
	
}
