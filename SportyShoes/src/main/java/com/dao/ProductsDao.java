package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bean.Products;

public interface ProductsDao extends JpaRepository<Products, Integer>
{
	
}
