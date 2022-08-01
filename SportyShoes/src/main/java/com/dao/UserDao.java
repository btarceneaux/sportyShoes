package com.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bean.User;

public interface UserDao extends JpaRepository<User, Integer>
{
	// Need a way to get a user by email.
	List<User> findByEmailAddress(String emailAddress);

}
