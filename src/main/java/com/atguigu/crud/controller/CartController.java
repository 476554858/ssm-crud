package com.atguigu.crud.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crud.bean.Book;
import com.atguigu.crud.bean.Cart;
import com.atguigu.crud.bean.CartItem;
import com.atguigu.crud.service.BookService;

@Controller
public class CartController {
	
	@Autowired
	BookService bookService;
	
	@RequestMapping("/cart/add")
	public String add(Book book,HttpSession session,@RequestParam("count")Integer count) throws ServletException, IOException{
			
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart==null){
			return "jsps/user/login";
		}
		
		Book book2 = bookService.findBybid(book.getBid());
		CartItem cartItem = new CartItem();
		
		cartItem.setBook(book2);
		cartItem.setCount(count);
		
		cart.add(cartItem);
		return "jsps/cart/list";
	}
	
	@RequestMapping(value="/cart/delete",method=RequestMethod.GET)
	public String delete(@RequestParam("bid")String bid,HttpSession session){
		Cart cart = (Cart) session.getAttribute("cart");
		cart.delete(bid);
		
		return "jsps/cart/list";
	}
	
	
}
