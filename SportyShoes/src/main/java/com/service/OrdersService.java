package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bean.Orders;
import com.bean.User;
import com.dao.OrdersDao;

@Service
public class OrdersService 
{
	@Autowired
	OrdersDao orderDao;
	
	public List<Orders> getAllOrders()
	{
		List<Orders> orderList = orderDao.findAll();
		if(orderList.size() > 0)
		{
			return orderList;
		}
		else
		{
			return null;
		}
	}
	
	public int storeOrder(Orders myOrder)
	{
		orderDao.save(myOrder);
		
		return 1;
	}

}
