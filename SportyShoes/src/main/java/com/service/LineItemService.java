package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bean.LineItem;
import com.dao.LineItemDao;

@Service
public class LineItemService {
	
	@Autowired
	LineItemDao lad;
	
	public int saveLineItem(LineItem myLineItem)
	{
		lad.save(myLineItem);
		
		return 1;
	}
	
	public List<LineItem> getLineItemByOrderId(int myOrderId)
	{
		List<LineItem> tempList = lad.findByOrderId(myOrderId);
		
		if(tempList.size() > 0)
		{
			return tempList;
		}
		else
		{
			return null;
		}
	}
	
	public List<LineItem> getAllLineItems( )
	{
		List<LineItem> tempList = lad.findAll();
		
		if(tempList.size() > 0)
		{
			return tempList;
		}
		else
		{
			return null;
		}
	}

}
