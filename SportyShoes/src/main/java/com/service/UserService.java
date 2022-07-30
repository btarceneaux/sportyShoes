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
		//Should take care of username and password
		Optional<User> u = userDao.findById(myUser.getUserId());
		//Regular user
		if (u.isPresent() && u.get().getEmailAddress() != "admin@gmail.com")
		{
			return 1;
		}
		else if (u.isPresent() && u.get().getEmailAddress() == "admin@gmail.com")
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
	
	
}
