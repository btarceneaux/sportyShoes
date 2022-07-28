package com.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bean.LineItem;
import com.dao.LineItemDao;

public class LineItemService {
	
	@Autowired
	LineItemDao lad;
	
	public int saveLineItem(LineItem myLineItem)
	{
		try 
		{
			lad.save(myLineItem);
			return 1;
		} catch (Exception e) {
			System.out.println("An error has occured : " + e);
			return 0;
		}
	}

}
