package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bean.User;

public interface UserDao extends JpaRepository<User, Integer>
{

}
