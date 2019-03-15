package com.atguigu.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crud.bean.Book;
import com.atguigu.crud.bean.BookExample;
import com.atguigu.crud.bean.BookExample.Criteria;
import com.atguigu.crud.dao.BookMapper;

@Service
public class BookService {
	
	@Autowired
	BookMapper bookMapper;

	public List<Book> findAll() {
		// TODO Auto-generated method stub
		return bookMapper.selectByExample(null);
	}

	public List<Book> findByCategory(String cid) {
		BookExample bookExample = new BookExample();
		Criteria criteria = bookExample.createCriteria();
		criteria.andCidEqualTo(cid);
		return bookMapper.selectByExample(bookExample);
	}

	public Book findBybid(String bid) {
			
		return bookMapper.selectByPrimaryKey(bid);
	}

	public void delete(String bid) {
		// TODO Auto-generated method stub
		bookMapper.deleteByPrimaryKey(bid);
	}

	public void mod(Book book) {
		// TODO Auto-generated method stub
		bookMapper.updateByPrimaryKeySelective(book);
	}

	public void save(Book book) {
		// TODO Auto-generated method stub
		bookMapper.insert(book);
	}

}
