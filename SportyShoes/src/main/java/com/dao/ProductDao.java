package com.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.bean.Product;

@Repository
public class ProductDao
{
	@PersistenceContext
	EntityManagerFactory emf;
	
	public List<Product> getAllProducts()
	{
		System.out.println("Getting Data Now");
		EntityManager manager = emf.createEntityManager();
		Query myQuery = manager.createQuery("SELECT p FROM product p");
		
		List<Product> tempProductList = myQuery.getResultList();
		
		return tempProductList;
	}
}
