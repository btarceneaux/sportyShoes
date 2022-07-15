package com.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.bean.Product;
import com.service.ProductService;

@Controller
public class ProductController 
{	
	@Autowired
	ProductService productService;
	
	@GetMapping("/displayProducts")
	public String displayAllProducts(HttpServletRequest req)
	{
		List<Product> productList = productService.getAllProducts();
		req.setAttribute("productList", productList);
		
		return "displayProducts";
	}

}
