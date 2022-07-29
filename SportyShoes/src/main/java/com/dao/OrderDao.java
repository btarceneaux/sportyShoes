package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bean.Product;

@Repository
public class OrderDao 
{
	@PersistenceUnit
	EntityManagerFactory emf;
	
	public List<Product> getSelectedProduct(int id)
	{
		EntityManager manager = emf.createEntityManager();
		Query myQuery = manager.createQuery("SELECT p FROM Product p WHERE p.productId = :id").setParameter("id", id);
		
		List<Product> myProduct = myQuery.getResultList();
		return myProduct;
	}
	
	public int getGreatestOrderNumber()
	{
		EntityManager manager = emf.createEntityManager();
		Query myQuery = manager.createQuery("SELECT p FROM Product p");
		
		List<Product> myProduct = myQuery.getResultList();
		int count = 0;
		for(Product pr : myProduct)
		{
			count++;
		}
		manager.close();
		
		return count;
	}
}
