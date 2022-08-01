package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Products;
import com.dao.ProductsDao;

@Service
public class ProductService
{
	@Autowired 
	ProductsDao productDao;
	
	public List<Products> getAllProducts()
	{
		return productDao.findAll();
	}
	
	public Optional<Products> getProductById(int id)
	{
		return productDao.findById(id);
	}
}
