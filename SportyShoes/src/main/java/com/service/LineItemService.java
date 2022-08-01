package com.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bean.LineItem;
import com.dao.LineItemDao;

public class LineItemService {
	
	@Autowired
	LineItemDao lad;
	
	public int saveLineItem(LineItem myLineItem)
	{
		lad.save(myLineItem);
		
		return 1;
	}

}
