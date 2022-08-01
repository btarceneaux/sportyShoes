package com.service;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import com.bean.User;
import com.main.SportyShoesApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SportyShoesApplication.class)
public class UserServiceTest {


	
	@Autowired
	UserService service; 
	
	@Test
	public void getAllUsers_basic()
	{
		List<User> testUser = new ArrayList<User>();
		testUser = service.getAllUsers();
		
		int userId = testUser.get(0).getUserId();
		assertEquals(1, userId);
	}
	
	@Test
	public void getUserById_basic()
	{
		Optional<User> testUser = Optional.empty();
		testUser = service.getUserById(1);
		
		assertEquals(1, testUser.get().getUserId());
		assertEquals("admin@gmail.com", testUser.get().getEmailAddress());
		assertEquals("654321", testUser.get().getPwd());
	}


	@Test
	@DirtiesContext
	public void storeUser_basic()
	{
		User myUser = new User();
		
		myUser.setFirstName("Jacob");
		myUser.setLastName("Richardson");
		myUser.setEmailAddress("jacob.richardson@gmail.com");
		myUser.setPwd("hjkl");
		myUser.setMyAddress("1111 11th St");
		myUser.setMyCity("Wichita");
		myUser.setMyState("KS");
		myUser.setMyZipCode(11111);
		int result = service.storeUser(myUser);
		
		//User newUser = service.getUserByEmailAddress(myUser.getEmailAddress()).get(0);
		assertEquals(1, result);
		
		//Clean up the database
		service.removeUser(myUser);
	}
//	
//	@Test
//	@DirtiesContext
//	public void login_basic()
//	{
//		User myUser = new User();
//		
//		myUser.setFirstName("Jacob");
//		myUser.setLastName("Richardson");
//		myUser.setEmailAddress("jacob.richardson@gmail.com");
//		myUser.setPassword("hjkl");
//		myUser.setMyAddress("2222 22nd St");
//		myUser.setMyCity("Fort Bragg");
//		myUser.setMyState("WY");
//		myUser.setMyZipCode(22222);
//		//myUser.setUserId(3);
//		
//		int result = service.login(myUser, "user");
//		assertEquals(1, result);
//		
//		//Clean up the database
//		service.removeUser(myUser);
//	}
//	
//	@Test
//	@DirtiesContext
//	public void login_admin_basic()
//	{
//		User testUser = new User();
//		testUser = service.getUserByEmailAddress("admin@gmail.com").get(0);
//		
//		int result = service.login(testUser, "user");
//		assertNotEquals(7, result );
//		assertEquals("admin@gmail.com", testUser.getEmailAddress());
//		assertEquals("654321", testUser.getPassword());
//		
//		int finalResult = service.login(testUser, "admin");
//		assertNotEquals(7, finalResult );
//		
//	}

}
