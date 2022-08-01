package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bean.Orders;

public interface OrdersDao extends JpaRepository<Orders, Integer>
{

}