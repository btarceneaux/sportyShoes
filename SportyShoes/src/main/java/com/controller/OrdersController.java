package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.service.LineItemService;
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
	
	@Autowired
	LineItemService lineItemService;
	
	// Attach line item list to order object.
	Orders myOrder =  new Orders();
	
	protected List<LineItem> lineItemList = new ArrayList<>();
	
	int prId = 0;
	
	public int getLargestOrderId()
	{
		List<Orders> tempOrderList = orderService.getAllOrders();
		
		int largestOrderId = 0;
		for(Orders tempOrder : tempOrderList)
		{
			if(tempOrder.getOrderId() > largestOrderId)
			{
				largestOrderId = tempOrder.getOrderId();
			}
		}
		
		return largestOrderId;
	}
	
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
        	myLineItem.setLineItemPrice(price);
        	myLineItem.setOrderId((getLargestOrderId() + 1));
        	
        	System.out.println("The order Id beign stored in the database is " + myLineItem.getOrderId());
       	
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
    	
    	// Try a new date format
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String formattedDate = sdf.format(date);
    	java.sql.Date myDate = java.sql.Date.valueOf(formattedDate);

    	//Get largest order id
    	List<Orders> tempOrderList = orderService.getAllOrders();
    	int tmpOrderNumber = (getLargestOrderId() + 1); // use function instead
    	
    	myOrder.setOrderId((getLargestOrderId() + 1 ));
    	myOrder.setUserId(myUser.getUserId());
//    	myOrder.setLineItem(lineItemList); what happens when we do this last
    	myOrder.setOrderDate(myDate);
        
    	// insert order total and order number in line item list
    	float orderTotal = 0;
    	for(LineItem li : lineItemList)
    	{
    		li.setOrderId(tmpOrderNumber);
    		orderTotal += li.getLineItemPrice() * li.getQuantity();
    	}
    	
    	myOrder.setOrderTotal(orderTotal);
    
    	// Store order to database
    	int status = orderService.storeOrder(myOrder);
    	
    	int lineItemStatus = 0;
    	//Try to update line item separately
    	for(LineItem li : lineItemList)
    	{
    		lineItemStatus = lineItemService.saveLineItem(li);
    		if(lineItemStatus == 1)
    		{
    			System.out.println("Line item saved successfully.");
    		}
    		else
    		{
    			System.out.println("Line item not saved.");
    		}
    	}
    	
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
    
    @GetMapping("/manageOrders")
    public String manageOrders(HttpServletRequest req)
    {
    	//Get the selected product.
    	List<Orders> orderList = orderService.getAllOrders();

    	//This will be used to display orders by Category.
    	List<Products> tempProductList = productService.getAllProducts();
    	List<String> tempCategoriesList = new ArrayList<>();
    	
    	for(Products p : tempProductList)
    	{
    		// If it doesn't already have the category, put it in the list
    		if(!tempCategoriesList.contains(p.getProductCategory()))
    		{
    			tempCategoriesList.add(p.getProductCategory());
    		}
    	}
    	
    	req.setAttribute("allOrders", orderList);
    	req.setAttribute("allCategories", tempCategoriesList);
    	
    	return "manageOrders";
    }
    
    @GetMapping("/displayLineItem")
    public String displayLineItem(HttpServletRequest req)
    {
    	int requestedOrderId = Integer.parseInt(req.getParameter("orderId")) ;
    	List<LineItem> lineItemList = lineItemService.getLineItemByOrderId(requestedOrderId);
    	
    	req.setAttribute("liList", lineItemList);
    	
    	return "displayLineItems";
    }
    
    @GetMapping("/displayAllOrders")
    public String displayAllOrders(HttpServletRequest req)
    {
    	List<Orders> orderList = orderService.getAllOrders();

    	req.setAttribute("allOrders", orderList);
    	return "displayAllOrders";
    }
    
    @GetMapping("/displayOrdersByDate")
    public String displayOrdersByDate(HttpServletRequest req) throws ParseException
    {

    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Date parsed = sdf.parse(req.getParameter("selectedDate"));
    	String formattedDate = sdf.format(parsed);
    	java.sql.Date myDate = java.sql.Date.valueOf(formattedDate);
    	
    	List<Orders> orderList = orderService.findByDate(myDate);
    	
    	req.setAttribute("allOrders", orderList);
    	return "displayAllOrders";
    }
    
    @GetMapping("/displayOrdersByCategory")
    public String displayOrdersByProductCategory(HttpServletRequest req)
    {
    	//First get the selected category
    	String selectedCategory = req.getParameter("category");
    	
    	//Next get all the products within that category
    	List<Products> tempProductList = productService.findByProductCategory(selectedCategory);
    	
    	//Now get all of the product ids associated with the category
    	List<Integer> productIdList = new ArrayList<>();
    	for(Products p : tempProductList)
    	{
    		productIdList.add(p.getProductId());
    	}
    	
    	// Now that we have all the product ids, search for the line items that have that product id.
    	List<LineItem> tempLineItemList =  lineItemService.getAllLineItems();
    	List<LineItem> selectedCategoryLineItem = new ArrayList<>();
    	
    	for(LineItem li : tempLineItemList)
    	{
    		for(int i : productIdList)
    		{
    			System.out.println("The productId is : " + i);
    			System.out.println("Line item id : " + li.getItemId());
    			if(i == li.getItemId())
    			{
    				selectedCategoryLineItem.add(li);
    			}
    		}
    	}
    	
    	//Finally figure out which order is associated with the line items
    	List<Integer> orderIdList = new ArrayList<>();
    	List<Orders> orderList = new ArrayList<>();
    	
    	for(LineItem li : selectedCategoryLineItem)
    	{
    		//get order number associated with line item
    		if(!orderIdList.contains(li.getOrderId()))
    		{
    			orderIdList.add(li.getOrderId());
    		}
    	}
    	
    	//Now that we have the order numbers, we can now put the associated orders in a list.
    	for(int i : orderIdList)
    	{
    		orderList.add(orderService.findByOrderId(i));
    	}
    	
    	req.setAttribute("allOrders", orderList);
    	
    	return "displayAllOrders";
    }

}
