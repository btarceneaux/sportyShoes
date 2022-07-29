package com.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.bean.LineItem;
import com.bean.Product;
import com.service.OrdersService;

@Controller
@EntityScan("com.bean")
public class OrderController 
{
	List<Product> myProductList= new ArrayList<>();
	
	@Autowired
	OrdersService orderService;

	protected List<LineItem> lineItemList = new ArrayList<>();
	
	int prId = 0;
	
	public int getGreatestOrderNumber()
	{
		int numberOfOrders = orderService.getGreatestOrderNumber();
		
		return numberOfOrders;
	}
	
	@GetMapping("/displaySelection")
	public String viewSelection(HttpServletRequest req)
	{
		System.out.println("Getting parameters from form");
		String orderProductId = req.getParameter("id");
		
		//Get the selected product.
		List<Product> product = orderService.getSelectedProduct(Integer.parseInt(orderProductId));
		req.setAttribute("product", product);
		
		System.out.println("Order ID : " + orderProductId);
		req.setAttribute("productId", orderProductId);
		prId = Integer.parseInt(orderProductId);
		
		return "displaySelection";
	}
	
    @PostMapping("/addItemsToCart")
	public String addItemsToCart(HttpServletRequest req)
	{  
    	String selectedProductId = req.getParameter("requestedProductId");
    	List<Product> myProductList = orderService.getSelectedProduct(Integer.parseInt(selectedProductId));
    	
    	System.out.println("The size of the list is " + lineItemList.size());
    	Product myProduct = myProductList.get(0);
    	
    	String success = "addSuccess";
    	String failure = "addFailure";
    	int prId =  Integer.parseInt(selectedProductId);
    	System.out.println("After the assignment of selectedProductId");
    	boolean isSuccess = false;
    	
    	// Check to see if the same item was added to the cart. If so, don't add anything.
    	boolean itemFound = false;
    	
    	if(lineItemList.size() > 0)
    	{
    		for(LineItem l : lineItemList)
        	{
    			if(l.getItemId() == prId)
    			{
    				itemFound = true;
    			}
        	}
    	}
    	
    	if(itemFound == false)
		{
			int quantity = Integer.parseInt(req.getParameter("quantitySelection"));
        	int myOrderNumber = getGreatestOrderNumber() + 1;
       
        	LineItem myLineItem = new LineItem();
        	myLineItem.setItemId(prId);
        	myLineItem.setOrderId(myOrderNumber);
        	myLineItem.setQuantity(quantity);
        	myLineItem.setMyProduct(myProduct);
       	
        	//Add item to the list
        	lineItemList.add(myLineItem);
        	
        	isSuccess = true;
		}
 
    	if (isSuccess == true)
    	{
    		return success;
    	}
    	else
    	{
    		return failure;
    	}
	}
    
    @GetMapping("/checkOutItems")
    public String checkOutItems(HttpServletRequest req)
    {
    	System.out.println("Checkout");
    	req.setAttribute("myCart", lineItemList);
    	return "checkout";
    }
    
    @PostMapping("/removeFromCart")
    public String removeFromCart(HttpServletRequest req)
    {
    	//First get the item id for removal
    	int itemSelection = Integer.parseInt(req.getParameter("id"));
    	int index = 0;
    	int foundIndex = 0;
    	System.out.println("selected item " + itemSelection);
    	
    	for(LineItem l : lineItemList)
    	{
    		if(l.getItemId() == itemSelection)
    		{
    			System.out.println("The item to be removed has been found");
    			foundIndex = index;
    		}
    		index++;
    	}
    	
    	lineItemList.remove(foundIndex);
    	
    	req.setAttribute("myCart", lineItemList);
    	return "checkout"; 
    }

}
