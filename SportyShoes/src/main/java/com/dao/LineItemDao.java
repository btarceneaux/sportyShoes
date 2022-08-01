package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bean.LineItem;

@Repository
public interface LineItemDao extends JpaRepository<LineItem, Integer>
{

}
