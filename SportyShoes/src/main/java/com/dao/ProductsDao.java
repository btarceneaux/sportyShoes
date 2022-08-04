package com.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bean.Products;

public interface ProductsDao extends JpaRepository<Products, Integer>
{
	List<Products> findByProductName(String myProductName);
	List<Products> findByProductCategory(String productCategory);
}
