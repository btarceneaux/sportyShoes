package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Product;
import com.dao.OrderDao;

@Service
public class OrdersService 
{
	@Autowired
	OrderDao ordersDao;
	
	public List<Product> getSelectedProduct(int id)
	{
		return ordersDao.getSelectedProduct(id);
	}
	
	public int getGreatestOrderNumber()
	{
		int count = ordersDao.getGreatestOrderNumber();
		
		return count;
	}

}
