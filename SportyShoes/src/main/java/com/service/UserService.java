package com.service;

import java.util.List;

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
		return userDao.getAllUsers();
	}
}
