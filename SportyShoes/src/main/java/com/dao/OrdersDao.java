package com.dao;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bean.Orders;

public interface OrdersDao extends JpaRepository<Orders, Integer>
{
	List<Orders> findByOrderDate(Date orderDate);
	List<Orders> findByOrderId(int orderId);
}