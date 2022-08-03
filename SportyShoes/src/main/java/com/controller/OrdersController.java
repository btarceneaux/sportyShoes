package com.controller;

//import java.util.Date;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.bean.LineItem;
import com.bean.Orders;
import com.bean.Products;
import com.bean.User;
import com.service.OrdersService;
import com.service.ProductsService;
import com.service.UserService;

@Controller
@EntityScan("com.bean")
public class OrdersController 
{
	List<Products> myProductList= new ArrayList<>();
	
	@Autowired
	OrdersService orderService;

	@Autowired
	ProductsService productService;
	
	@Autowired
	UserService userService;
	
	protected List<LineItem> lineItemList = new ArrayList<>();
	
	int prId = 0;
	
	@GetMapping("/displaySelection")
	public String viewSelection(HttpServletRequest req)
	{
		System.out.println("Getting parameters from form. Currently in order controller");
		String orderProductId = req.getParameter("id");
		
		//Get the selected product.
		Optional<Products> product = productService.getProductById(Integer.parseInt(orderProductId));
		Products myProduct = product.get();
		List<Products> tempProductList = new ArrayList<Products>();
		tempProductList.add(myProduct);
		
		req.setAttribute("myProductList", tempProductList);
		req.setAttribute("productId", orderProductId);
		
		prId = Integer.parseInt(orderProductId);
		
		return "displaySelection";
	}
	
    @PostMapping("/addItemsToCart")
	public String addItemsToCart(HttpServletRequest req)
	{  
    	//String selectedProductId = req.getParameter("requestedProductId");
    	Optional<Products> product = productService.getProductById(prId);
    	// Change to regular list
    	Products myProduct = product.get();
		List<Products> tempProductList = new ArrayList<Products>();
		tempProductList.add(myProduct);
    	
    	System.out.println("The size of the list is " + lineItemList.size());
    	
    	Products mySelectedProduct = tempProductList.get(0);
    	float price = mySelectedProduct.getProductPrice();
    	
    	String success = "addSuccess";
    	String failure = "addFailure";
    	//int myProductId =  Integer.parseInt(selectedProductId);
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
       
        	LineItem myLineItem = new LineItem();
        	myLineItem.setItemId(prId);
        	myLineItem.setQuantity(quantity);
        	myLineItem.setMyProduct(myProduct);
        	myLineItem.setLineItemTotal(price * quantity);
       	
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
    
    @GetMapping("/removeFromCart")
    public String removeFromCart(HttpServletRequest req)
    {
    	if(lineItemList.size() < 1)
    	{
    		return "index";
    	}
    	
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
    
    
    @PostMapping("/storeOrder")
    public String storeOrderInformation(HttpServletRequest req)
    {
    	String userId = (String) req.getParameter("id");
    	System.out.println("User id is " + userId);
    	
    	Optional<User> optionalUser = userService.getUserById(Integer.parseInt(userId));
    	User myUser = optionalUser.get();
    	
//    	// Try a new date format
//    	Date date = new Date();
//    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    	String formattedDate = sdf.format(date);
//    	java.sql.Date myDate = java.sql.Date.valueOf(formattedDate);

    	// Attach line item list to order object.
    	Orders myOrder =  new Orders();
    	
    	// insert userid
    	myOrder.setUserId(myUser.getUserId());
    	//insert line item list
    	myOrder.setLineItem(lineItemList);
    
    	// insert order total
    	float orderTotal = 0;
    	for(LineItem li : lineItemList)
    	{
    		orderTotal += li.getLineItemTotal();
    	}
    	myOrder.setOrderTotal(orderTotal);
    
    	// Store order to database
    	int status = orderService.storeOrder(myOrder);
    	
    	if(status != 1)
    	{
    		System.out.println("Error! Order was not stored successfully!");
    	}
    	else
    	{
    		System.out.println("The order was processed correctly. ");
    	}
    	
    	req.setAttribute("myOrder", myOrder);
    	return "orderCompleted";
    }

}
