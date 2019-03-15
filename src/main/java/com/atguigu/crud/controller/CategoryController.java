package com.atguigu.crud.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atguigu.crud.bean.Category;
import com.atguigu.crud.service.CategoryService;




@Controller
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	/**
	 * 用户查看分类
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/category/findAllCategory",method=RequestMethod.GET)  
	public String findAllCatetory(Model model){
		
		
		List<Category> categoryList = categoryService.findAll();
		
		model.addAttribute("categoryList", categoryList);
	
		
		return "jsps/left";
	}
	
	/**
	 * 管理员查看分类
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/category/adminFindAllCategory",method=RequestMethod.GET)  
	public String aminFindAllCatetory(Model model){
	
		
		List<Category> categoryList = categoryService.findAll();
		
		model.addAttribute("categoryList", categoryList);
	
		
		return "adminjsps/admin/category/list";
	}
	
	/**
	 * 根据cid查看分类
	 * @param cid
	 * @param request
	 * @return
	 */
	@RequestMapping("/category/editBefore/{cid}")
	public String editBefore(@PathVariable String cid,HttpServletRequest request){
		
		Category category = categoryService.findOneByCid(cid);
		
		request.setAttribute("c", category);
		return "adminjsps/admin/category/mod";
	}
	
	
	@RequestMapping(value="category/edit",method=RequestMethod.POST)
	public String edit(Category category,Model model){
		
		categoryService.edit(category);
		List<Category> categoryList = categoryService.findAll();
		
		model.addAttribute("categoryList", categoryList);
		
		return "adminjsps/admin/category/list";
	}
	
	@RequestMapping(value="category/addCategory",method=RequestMethod.POST)
	public String addCategory(Category category,Model model){
		String uid = UUID.randomUUID().toString().substring(0,6);
		category.setCid(uid);
		categoryService.save(category);
		List<Category> categoryList = categoryService.findAll();
		
		model.addAttribute("categoryList", categoryList);
		return "adminjsps/admin/category/list";
	}
	
	/**
	 * 删除分类
	 * @param cid
	 * @param model
	 * @return
	 */
	@RequestMapping("category/delete/{cid}")
	public String categoryDelete(@PathVariable String cid,Model model){
		
		categoryService.deleteCategory(cid);
		List<Category> categoryList = categoryService.findAll();
		
		model.addAttribute("categoryList", categoryList);
		
		return "adminjsps/admin/category/list";
	}
	
	
	
	
	
	
	
	
}
