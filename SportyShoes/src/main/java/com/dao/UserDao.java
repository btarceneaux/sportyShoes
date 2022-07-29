package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.bean.User;

@Repository
public class UserDao 
{
	@PersistenceUnit
	EntityManagerFactory emf;
	
	public List<User> getAllUsers()
	{
		EntityManager manager = emf.createEntityManager();
		Query myQuery = manager.createQuery("SELECT u FROM User u");
		
		List<User> tempUserList = myQuery.getResultList();
		System.out.println("Getting list of all users.");
		
		manager.close();
		
		return tempUserList;
	}
	
}