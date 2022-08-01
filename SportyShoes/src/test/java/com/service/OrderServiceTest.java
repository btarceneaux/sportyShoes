package com.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.bean.LineItem;
import com.bean.Orders;
import com.bean.Products;
import com.main.SportyShoesApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SportyShoesApplication.class)
public class OrderServiceTest 
{
	@Autowired
	OrdersService service;
	
	@Autowired
	ProductService productService;
	
	@Test
	public void storeOrder()
	{
		
		Optional<Products> myProduct = productService.getProductById(2);
		assertEquals(2, myProduct.get().getProductId());
		
		Orders myOrder = new Orders();
		myOrder.setUserId(2);
		
		LineItem lineItem = new LineItem();
		lineItem.setOrderId(myOrder.getOrderId());
		lineItem.setItemId(3);
		lineItem.setQuantity(3);
		lineItem.setLineItemTotal(myProduct.get().getProductPrice() * lineItem.getQuantity());
		
		// This will need to be changed to reflect the actual cost
		myOrder.setOrderTotal(lineItem.getQuantity() * 100);
		
		List<LineItem> lineItemList = new ArrayList<>();
		lineItemList.add(lineItem);
		assertEquals(1, lineItemList.size());
		
		myOrder.setLineItem(lineItemList);
		
		int result = service.storeOrder(myOrder);
        
		//assertEquals(1, result);
		
	}

}
