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
		myAddress.setAddressId(2);
		myAddress.setAddress("3333 33rd St.");
		myAddress.setCity("Coarsegold");
		myAddress.setState("CA");
		myAddress.setZip("33333");
		
		myUser.setFirstName("Richard");
		myUser.setLastName("Richardson");
		myUser.setEmailAddress("richard.richardson@richard.com");
		myUser.setPassword("qrstuv");
		myUser.setUserAddress(myAddress);
		myUser.setUserId(3);
		
		service.storeUser(myUser);
		
		Optional<User> newUser = service.getUserById(2);
		assertEquals("sarah.davidson@sarah.com", newUser.get().getEmailAddress());
	}
	
	@Test
	@DirtiesContext
	public void login_basic()
	{
		User myUser = new User();
		Address myAddress = new Address();
		myAddress.setAddressId(2);
		myAddress.setAddress("3333 33rd St.");
		myAddress.setCity("Coarsegold");
		myAddress.setState("CA");
		myAddress.setZip("33333");
		
		myUser.setFirstName("Richard");
		myUser.setLastName("Richardson");
		myUser.setEmailAddress("richard.richardson@richard.com");
		myUser.setPassword("qrstuv");
		myUser.setUserAddress(myAddress);
		myUser.setUserId(3);
		
		service.storeUser(myUser);
		
		int result = service.login(myUser, "user");
		assertEquals(1, result);
	}
	
	@Test
	@DirtiesContext
	public void login_admin_basic()
	{
		Optional<User> testUser = Optional.empty();
		testUser = service.getUserById(1);
		
		User myUser = testUser.get();
		
		int result = service.login(myUser, "user");
		assertNotEquals(7, result );
		assertEquals("admin@gmail.com", myUser.getEmailAddress());
		assertEquals("654321", myUser.getPassword());
		
		int finalResult = service.login(myUser, "admin");
		assertNotEquals(7, finalResult );
		
	}

}
