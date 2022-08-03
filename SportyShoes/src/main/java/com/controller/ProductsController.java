package com.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bean.LineItem;
import com.bean.Products;
import com.service.ProductsService;

@Controller
public class ProductsController 
{	
	@Autowired
	ProductsService productService;
	
	@GetMapping("/displayProducts")
	public String displayAllProducts(HttpServletRequest req)
	{
		List<Products> productList = productService.getAllProducts();
		req.setAttribute("productList", productList);
		
		return "displayProducts";
	}
	
	@GetMapping("/manageProducts")
	public String manageProducts(HttpServletRequest req)
	{
		List<Products> productList = productService.getAllProducts();
		req.setAttribute("productList", productList);
		
		return "manageProducts";
	}
	
	@GetMapping("/deleteProduct")
	public String deleteProduct(HttpServletRequest req)
	{
		
		
		//First get the item id for removal
    	int itemSelection = Integer.parseInt(req.getParameter("id"));
    	
    	//First search for the product to be deleted. 
    	Products myProduct = productService.getProductById(itemSelection).get();
    	
    	System.out.println("Now deleting " + myProduct.getProductName());
    	
    	// Next delete the product.
    	productService.deleteProduct(myProduct);
    	
    	return "deleteSuccess"; 
	}
	
	@PostMapping("/addProduct")
	public String addProduct(HttpServletRequest req)
	{
		String productName = req.getParameter("productName");
		String productDescription = req.getParameter("productDescription");
		String productCategory = req.getParameter("productCategory");
		Float price = Float.parseFloat(req.getParameter("price"));
		
		Products myProduct = new Products();
		myProduct.setProductName(productName);
		myProduct.setProductDesc(productDescription);
		myProduct.setProductCategory(productCategory);
		myProduct.setProductPrice(price);
		
		//First search to see if the product exists
		Products tempProduct = productService.searchProductByName(productName);
		
		if(tempProduct == null)
		{
			productService.storeProduct(myProduct);
			return "addProductSuccess";
		}
		else
		{
			return "productAddError";
		}
		
	}
}
