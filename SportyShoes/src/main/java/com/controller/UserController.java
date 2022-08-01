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
	boolean loggedIn = false;
	
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
		User verifiedUser = new User();
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String typeOfUser = req.getParameter("typeOfUser");
		int result = 0;
		System.out.println("The email address is " + email);
		
		List<User> returnedUser = userService.getUserByEmailAddress(email);
		for(User u : returnedUser)
		{
			if(u.getEmailAddress().equals(email) && u.getPassword().equals(password))
			{
				verifiedUser = u;
				result = userService.login(u, typeOfUser);
				System.out.println("The result is " + result);
			}
		}
		
		
		
		if(result == 1)
		{
			req.setAttribute("id", verifiedUser.getUserId());
			loggedIn = true;
			return "loginSuccess";
		}
		
		if(result == 7)
		{
			req.setAttribute("id", verifiedUser.getUserId());
			loggedIn = true;
			return "adminHome";
		}
		else
		{
			return "loginFailure";
		}
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
		String failure = "loginFailure";
		String password = (String) req.getAttribute("password");
		String confirmPassword = (String) req.getAttribute("confirmPassword");
		String emailAddress = (String) req.getAttribute("email");
		
		if(password.equals(confirmPassword) && !emailAddress.equals("admin@gmail.com") )
		{
			myAddress.setAddress((String) req.getAttribute("mailingAddress"));
			myAddress.setCity((String) req.getAttribute("city"));
			myAddress.setState((String) req.getAttribute("state"));
			myAddress.setZip((String) req.getAttribute("zipCode"));
			
			myUser.setPassword(password);
			myUser.setFirstName((String) req.getAttribute("firstName"));
			myUser.setLastName((String) req.getAttribute("lastName"));
			myUser.setEmailAddress(emailAddress);
			myUser.setUserAddress(myAddress);
			
			return success;
		}
		else
		{
			return failure;
		}
		
	}
	
}
