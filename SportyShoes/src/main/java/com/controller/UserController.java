package com.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.bean.User;
import com.service.UserService;

@Controller
public class UserController
{
	@Autowired
	UserService userService;
	
	@GetMapping("/displayUsers")
	public String displayAllUsers(HttpServletRequest req)
	{
		List<User> userList = userService.getAllUsers();
		req.setAttribute("userList", userList);
		
		return "displayUsers";
	}
}
