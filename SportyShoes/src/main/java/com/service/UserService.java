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
	
	public int removeUser (User myUser)
	{
		User u = userDao.findByEmailAddress(myUser.getEmailAddress()).get(0);
		if(u.getEmailAddress().equals(myUser.getEmailAddress()))
		{
			userDao.delete(u);
			return 1;
		}
		else
		{
			return 0;
		}
		
	}
	
	public int storeUser(User myUser)
	{
		if(userDao.findByEmailAddress(myUser.getEmailAddress()).isEmpty())
		{
			userDao.save(myUser);
			return 1;
		}
		else
		{
			
			return 0;
		}
	}
	
	public int login(User myUser, String typeOfUser)
	{
		System.out.println("in login");
		// Try to get user with the given email address
		
		if(userDao.findByEmailAddress(myUser.getEmailAddress()).isEmpty())
		{
			return 0;
		}
		
		User requestedUser = userDao.findByEmailAddress(myUser.getEmailAddress()).get(0);
		if (!requestedUser.getEmailAddress().equals("admin@gmail.com") && requestedUser.getPwd().equals(myUser.getPwd())
				&& typeOfUser.equals("user"))
		{
			return 1;
		}
		else if (requestedUser.getEmailAddress().equals("admin@gmail.com") && requestedUser.getPwd().equals(myUser.getPwd())
				&& typeOfUser.equals("admin"))
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
