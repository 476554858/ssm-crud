package com.atguigu.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crud.bean.AdminUser;
import com.atguigu.crud.bean.AdminUserExample;
import com.atguigu.crud.bean.AdminUserExample.Criteria;
import com.atguigu.crud.dao.AdminUserMapper;

@Service
public class AdminUserService {
	
	@Autowired
	AdminUserMapper adminUserMapper;

	public AdminUser login(AdminUser adminUser) {
		/*AdminUserExample example = new AdminUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andAnameEqualTo(adminUser.getAname());*/
		return adminUserMapper.selectByPrimaryKey(adminUser.getAname());
		
	}
}
