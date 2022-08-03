package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Products;
import com.dao.ProductsDao;

@Service
public class ProductsService
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
	
	public Products searchProductByName(String productName)
	{
		List<Products> myProduct = productDao.findByProductName(productName);
		
		if(myProduct.isEmpty())
		{
			return null;
		}
		else
		{
			return myProduct.get(0);
		}

	}
	
	public int storeProduct(Products myProduct)
	{
		if(productDao.findByProductName(myProduct.getProductName()).isEmpty())
		{
			productDao.save(myProduct);
			return 1;
		}
		else 
		{
			return 0;
		}
	}
	
	public int deleteProduct(Products myProduct)
	{
		if(!productDao.findByProductName(myProduct.getProductName()).isEmpty())
		{
			productDao.deleteById(myProduct.getProductId());
			return 1;
		}
		else 
		{
			return 0;
		}
	}
}
