package com.atguigu.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crud.bean.Orders;
import com.atguigu.crud.bean.OrdersExample;
import com.atguigu.crud.bean.OrdersExample.Criteria;
import com.atguigu.crud.bean.User;
import com.atguigu.crud.dao.OrdersMapper;

@Service
public class OrderService {
	
	@Autowired
	OrdersMapper ordersMapper;

	public void add(Orders orders) {
		// TODO Auto-generated method stub
		ordersMapper.insert(orders);
	}

	public void updateOrder(String r6_Order) {
		// TODO Auto-generated method stub
		Orders order = new Orders();
		order.setOid(r6_Order);
		order.setState(2);
		ordersMapper.updateByPrimaryKeySelective(order);
	}

	public List<Orders> findAll(User user) {
		OrdersExample ordersExample = new OrdersExample();
		Criteria criteria = ordersExample.createCriteria();
		criteria.andUidEqualTo(user.getUid());
		
		return ordersMapper.selectByExample(ordersExample);
	}

	public Orders findOne(String oid) {
		// TODO Auto-generated method stub
		return ordersMapper.selectByPrimaryKey(oid);
	}

	public void confirm(String oid) {
		Orders orders = new Orders();
		orders.setOid(oid);
		orders.setState(4);
		ordersMapper.updateByPrimaryKey(orders);
	}

	public List<Orders> adminFindAll() {
		// TODO Auto-generated method stub
		return ordersMapper.selectByExample(null);
	}

	public void adminUpdateState(Orders order) {
		// TODO Auto-generated method stub
		ordersMapper.updateByPrimaryKey(order);
	}

	public List<Orders>  findAllByState(String state) {
		// TODO Auto-generated method stub
		Short state2 = new Short(state);
		OrdersExample ordersExample = new OrdersExample();
		Criteria criteria = ordersExample.createCriteria();
		criteria.andStateEqualTo(state2);
		return ordersMapper.selectByExample(ordersExample);
	}
	
}
