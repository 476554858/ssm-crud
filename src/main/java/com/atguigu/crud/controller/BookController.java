package com.atguigu.crud.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.crud.bean.Book;
import com.atguigu.crud.service.BookService;
import com.atguigu.crud.service.CategoryService;


@Controller
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value="/book/findAll",method=RequestMethod.GET)
	public String findAll(HttpServletRequest request){
		
		List<Book> bookList = bookService.findAll();
		request.setAttribute("booklist",bookList);
		
		return "jsps/book/list";
	}
	
	/**
	 * 根据分类查看所有书籍
	 * @param cid
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/book/findByCategory",method=RequestMethod.GET)
	public String findByCategory(@RequestParam("cid")String cid,HttpServletRequest request){
		
		List<Book> bookList = bookService.findByCategory(cid);
		
		request.setAttribute("booklist",bookList);
		
		return "jsps/book/list";
	}
	
	@RequestMapping(value="/book/findBybid",method=RequestMethod.GET)
	public String findBybid(@RequestParam("bid")String bid,HttpServletRequest request){
		
		Book book = bookService.findBybid(bid);
		request.setAttribute("book",book);
		return "jsps/book/desc";
	}
	
	/**
	 * 查看所有书籍
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/book/adinFindAll",method=RequestMethod.GET)
	public String adminFindAll(HttpServletRequest request){
		
		List<Book> bookList = bookService.findAll();
		request.setAttribute("booklist",bookList);
		
		return "adminjsps/admin/book/list";
	}
	
	/**
	 * 根据ID查看书籍
	 * @param bid
	 * @param request
	 * @return
	 */
	@RequestMapping("/book/adminFindBybid/{bid}")
	public String test(@PathVariable String bid,HttpServletRequest request){
		Book book = bookService.findBybid(bid);
		request.setAttribute("book", book);
		request.setAttribute("categoryList", categoryService.findAll());
		
		return "adminjsps/admin/book/desc";
	}
	
	/**
	 * 删除
	 * @param book
	 * @param request
	 * @return
	 */
	@RequestMapping(value="book/del",method=RequestMethod.POST)
	public String del(Book book,HttpServletRequest request){
		
		bookService.delete(book.getBid());
		request.setAttribute("booklist", bookService.findAll());
		return "adminjsps/admin/book/list";
	}
	
	
	/**
	 * 修改
	 * @param book
	 * @param request
	 * @return
	 */
	@RequestMapping(value="book/mod",method=RequestMethod.POST)
	public String mod(Book book,HttpServletRequest request){
	
		book.setCid(null);
		bookService.mod(book);
		request.setAttribute("booklist", bookService.findAll());
		return "adminjsps/admin/book/list";
	}
	
	@RequestMapping(value="book/add",method=RequestMethod.POST)
	public String add(Book book,@RequestParam("file")MultipartFile file,HttpSession session,
			HttpServletRequest request) throws IllegalStateException, IOException{
		String uid = UUID.randomUUID().toString().substring(0, 5);
		book.setBid(uid);
		String filename = file.getOriginalFilename();
		if(filename.endsWith("jpg")||filename.endsWith("png")||filename.endsWith("gif")){
			String leftPath = "D:/Java/myWorkSpace/ssm-crud/src/main/webapp/book_img";
			String realPath = request.getServletContext().getRealPath("/book_img/");
			System.out.println(realPath);
			File file2 = new File(leftPath,filename);
			file.transferTo(file2);
			book.setImage("book_img/"+filename);
			
			bookService.save(book);
			
			request.setAttribute("booklist", bookService.findAll());
			
			return "adminjsps/admin/book/list";
		}else{
			request.setAttribute("msg", "上传格式不正确，请重新上传");
			return "";
		}
	
	}
	
	
	
	
	
	
	
	
	
	
	
}
