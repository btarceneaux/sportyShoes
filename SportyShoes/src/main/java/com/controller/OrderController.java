package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	List<Product> = new ArrayList<>();
	
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
    	Product myProduct = (Product)req.getAttribute("product");
    	
    	// Check to see if the same item was added to the cart. If so, don't add anything.
    	if(myProductList.size() > 0)
    	{
    		for(Product p : myProductList)
        	{
    			if(p.getProductId() == myProduct.getProductId())
    			{
    				return "addFailure";
    			}
        	}
    		
    	}
    	
    	

    	int quantity = Integer.parseInt(req.getParameter("quantitySelection"));
    	int myOrderNumber = getGreatestOrderNumber() + 1;
   
    	LineItem myLineItem = new LineItem();
    	myLineItem.setItemId(prId);
    	myLineItem.setOrderId(myOrderNumber);
    	myLineItem.setQuantity(quantity);
    	myLineItem.setMyProduct(myProduct);
   	
    	//Add item to the list
    	lineItemList.add(myLineItem);
    	
//    	//Now that the line item has been set, take them back to the page to select products

    	return "addSuccess";
    	
    	
//    	Date date = new Date();
//    	

//    	System.out.println("Product Id : " + productId);
//    	System.out.println("Quantity : " + quantity);
//    	
//    	//Get the selected product.
//    	List<Product> tempProductList = orderService.getSelectedProduct(productId); 
//    	//Since there is only one element we need to assign this to a regular "Product" instead of a list.
//    	Product tempProduct =tempProductList.get(0);
//    	
//    	// Now use the information to populate the Order Object
//    	// Get the greatest order number and add 1 to it. 
//    	
//		myOrder.setPurchaseDate(date);
//		System.out.println("The date is " + date);
//		myOrder.setPrice(tempProduct.getProductPrice());
//    	
//		req.setAttribute("quantityOrdered", quantity);
//		req.setAttribute(null, req);
//		
//		return "";
	}
    
    @GetMapping("/checkOutItems")
    public String checkOutItems(HttpServletRequest req)
    {
    	System.out.println("Checkout");
    	
    	return "checkout";
    }

}
