package com.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.bean.Product;

@Repository
public class ProductDao
{
	@PersistenceUnit
	EntityManagerFactory emf;
	
	public List<Product> getAllProducts()
	{
		System.out.println("Getting Data Now");
		EntityManager manager = emf.createEntityManager();
		Query myQuery = manager.createQuery("SELECT p FROM Product p");
		
		List<Product> tempProductList = myQuery.getResultList();
		System.out.println("Returning list of products from the database");
		return tempProductList;
	}
}
