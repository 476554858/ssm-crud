package com.atguigu.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crud.bean.Category;
import com.atguigu.crud.dao.CategoryMapper;



@Service
public class CategoryService {
	
	@Autowired
	CategoryMapper categoryMapper;
	/**
	 * 查看图书分类
	 * @return 
	 */
	public List<Category> findAll() {
		return categoryMapper.selectByExample(null);
	}
	
	public Category findOneByCid(String cid) {
		// TODO Auto-generated method stub
		return categoryMapper.selectByPrimaryKey(cid);
	}

	/**
	 * 修改
	 * @param category
	 */
	public void edit(Category category) {
		
		categoryMapper.updateByPrimaryKey(category);
	}

	public void save(Category category) {
		// TODO Auto-generated method stub
		categoryMapper.insert(category);
	}

	public void deleteCategory(String cid) {
		// TODO Auto-generated method stub
		categoryMapper.deleteByPrimaryKey(cid);
	}

}
