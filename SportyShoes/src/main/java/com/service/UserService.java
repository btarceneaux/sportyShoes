package com.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bean.User;
import com.dao.UserDao;

@Service
public class UserService 
{
	@Autowired
	UserDao userDao;
	
	public List<User> getAllUsers()
	{		
		return userDao.findAll();
	}
	
	public String removeUser (User myUser)
	{
		User u = userDao.findByEmailAddress(myUser.getEmailAddress()).get(0);
		if(u.getEmailAddress() == myUser.getEmailAddress())
		{
			userDao.delete(u);
		}
		
		return "Record deleted successfully";
	}
	
	public String storeUser(User myUser)
	{
		Optional<User> u = userDao.findById(myUser.getUserId());
		if (u.isPresent())
		{
			return "Record was not stored";
		}
		else
		{
			userDao.save(myUser);
			return "Record stored successfully";
		}
	}
	
	public int login(User myUser, String typeOfUser)
	{
		System.out.println("in login");
		// Try to get user with the given email address
		User requestedUser = userDao.findByEmailAddress(myUser.getEmailAddress()).get(0);
		
		if (requestedUser.getEmailAddress() != "admin@gmail.com" && requestedUser.getPassword() == myUser.getPassword())
		{
			return 1;
		}
		else if (requestedUser.getEmailAddress() == "admin@gmail.com" && requestedUser.getPassword() == myUser.getPassword())
		{
			return 7;
		}
		else
		{
			return 0;
		}
	}
	
	public Optional<User> getUserById(int id)
	{
		Optional<User> myUser = userDao.findById(id);
		
		return myUser;
	}
	
	public List<User> getUserByEmailAddress(String emailAddress)
	{
		List<User> myUser = userDao.findByEmailAddress(emailAddress);
		
		return myUser;
	}
	
	
	
}
