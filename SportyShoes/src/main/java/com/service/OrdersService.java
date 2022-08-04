package com.service;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bean.Orders;
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
	
	public List<Orders> findByDate(Date orderDate)
	{
		List<Orders> orderList = orderDao.findByOrderDate(orderDate);
		if(orderList.size() > 0)
		{
			return orderList;
		}
		else
		{
			return null;
		}
	}

	public Orders findByOrderId(int orderId)
	{
		Orders myOrder = orderDao.findByOrderId(orderId).get(0);
		
		return myOrder;
	}
}
