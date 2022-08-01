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

import com.bean.Address;
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
		testUser = service.getUserById(2);
		
		assertEquals(2, testUser.get().getUserId());
		assertEquals("sarah.davidson@sarah.com", testUser.get().getEmailAddress());
		assertEquals("abcdefg", testUser.get().getPassword());
	}


	@Test
	@DirtiesContext
	public void storeUser_basic()
	{
		User myUser = new User();
		Address myAddress = new Address();
		//myAddress.setAddressId(4);
		myAddress.setAddress("5555 55th St.");
		myAddress.setCity("New York");
		myAddress.setState("NY");
		myAddress.setZip("55555");
		
		myUser.setFirstName("Jacob");
		myUser.setLastName("Richardson");
		myUser.setEmailAddress("jacob.richardson@gmail.com");
		myUser.setPassword("hjkl");
		myUser.setUserAddress(myAddress);
		//myUser.setUserId(3);
		
		service.storeUser(myUser);
		
		User newUser = service.getUserByEmailAddress(myUser.getEmailAddress()).get(0);
		assertEquals("jacob.richardson@gmail.com", newUser.getEmailAddress());
		
		//Clean up the database
		service.removeUser(myUser);
	}
	
	@Test
	@DirtiesContext
	public void login_basic()
	{
		User myUser = new User();
		Address myAddress = new Address();
		//myAddress.setAddressId(4);
		myAddress.setAddress("5555 55th St.");
		myAddress.setCity("New York");
		myAddress.setState("NY");
		myAddress.setZip("55555");
		
		myUser.setFirstName("Jacob");
		myUser.setLastName("Richardson");
		myUser.setEmailAddress("jacob.richardson@gmail.com");
		myUser.setPassword("hjkl");
		myUser.setUserAddress(myAddress);
		//myUser.setUserId(3);
		
		int result = service.login(myUser, "user");
		assertEquals(1, result);
		
		//Clean up the database
		service.removeUser(myUser);
	}
	
	@Test
	@DirtiesContext
	public void login_admin_basic()
	{
		User testUser = new User();
		testUser = service.getUserByEmailAddress("admin@gmail.com").get(0);
		
		int result = service.login(testUser, "user");
		assertNotEquals(7, result );
		assertEquals("admin@gmail.com", testUser.getEmailAddress());
		assertEquals("654321", testUser.getPassword());
		
		int finalResult = service.login(testUser, "admin");
		assertNotEquals(7, finalResult );
		
	}

}
