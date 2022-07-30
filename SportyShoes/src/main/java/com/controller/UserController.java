package com.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bean.Address;
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
	
	@GetMapping("/loginToSite")
	public String loginToSite(HttpServletRequest req)
	{
		User myUser = new User();
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String typeOfUser = req.getParameter("typeOfUser");
		
		int result = userService.login(myUser, typeOfUser);
		
		return "";
	}
	
	@GetMapping("/createUser")
	public String createUser(HttpServletRequest req)
	{
		return "createUser";	
	}
	
	@PostMapping("/verifyUser")
	public String verifyUserInformation(HttpServletRequest req)
	{
		Address myAddress = new Address();
		User myUser = new User();
		String success = "login";
		String failure = "createUser";
		String password = (String) req.getAttribute("password");
		String confirmPassword = (String) req.getAttribute("confirmPassword");
		
		if(password.equals(confirmPassword))
		{
			myAddress.setAddress((String) req.getAttribute("mailingAddress"));
			myAddress.setCity((String) req.getAttribute("city"));
			myAddress.setState((String) req.getAttribute("state"));
			myAddress.setZip((String) req.getAttribute("zipCode"));
			
			myUser.setPassword(password);
			myUser.setFirstName((String) req.getAttribute("firstName"));
			myUser.setLastName((String) req.getAttribute("lastName"));
			myUser.setEmailAddress((String) req.getAttribute("email"));
			myUser.setUserAddress(myAddress);
			
			return success;
		}
		else
		{
			return failure;
		}
		
	}
	
}
