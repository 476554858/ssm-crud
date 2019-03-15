package com.atguigu.crud.bean;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	
	private Map<String, CartItem> map = new LinkedHashMap<String, CartItem>();
	
	
	public Map<String, CartItem> getMap() {
		return map;
	}



	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}



	/**
	 * 添加购物车
	 * @param cartItem
	 */
	public void add(CartItem cartItem){
		if(map.containsKey(cartItem.getBook().getBid())){
			CartItem cartItem2 = map.get(cartItem.getBook().getBid());
			cartItem2.setCount(cartItem2.getCount()+cartItem.getCount());
			map.put(cartItem.getBook().getBid(), cartItem2);
		}
			map.put(cartItem.getBook().getBid(), cartItem);
	}
	
	
	
	/**
	 * 获取购物车 信息
	 * @return
	 */
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	
	/**
	 * 购物车总价格
	 * @return
	 */
	public double getTotal(){
		double total = 0;
		for(CartItem cartItem:map.values()){
			total += cartItem.getSubTotal();
			
		}
		return total;
	}
	
	public void delete(String bid){
		map.remove(bid);
	}
	
	/**
	 * 清空购物车
	 */
	public void clear(){
		map.clear();
	}
	
}
