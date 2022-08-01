package com.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
			if(u.getEmailAddress().equals(email) && u.getPwd().equals(password))
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
	
	@PostMapping("/verifyUserInformation")
	public String verifyUserInformation(HttpServletRequest req)
	{
		
		String success = "login";
		String failure = "loginFailure";
		String password = (String) req.getParameter("password");
		String confirmPassword = (String) req.getParameter("confirmPassword");
		String emailAddress = (String) req.getParameter("email");
		
		if(password.equals(confirmPassword))
		{	
			User myUser = new User();
			myUser.setPwd(password);
			myUser.setFirstName((String) req.getParameter("firstName"));
			myUser.setLastName((String) req.getParameter("lastName"));
			myUser.setEmailAddress(emailAddress);
			myUser.setMyAddress((String) req.getParameter("mailingAddress"));
			myUser.setMyCity((String) req.getParameter("city"));
			myUser.setMyState((String) req.getParameter("state"));
			myUser.setMyZipCode(Integer.parseInt(req.getParameter("zipCode")));
					
			//Store the user to the database
			int result = userService.storeUser(myUser);
			if(result == 1)
			{
				System.out.println("Record stored successfully!");
			}
			return success;
			
		}
		else
		{
			return failure;
		}
		
	}
	
}
