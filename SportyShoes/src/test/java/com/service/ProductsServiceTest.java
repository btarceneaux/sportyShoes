package com.service;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.bean.Products;
import com.main.SportyShoesApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SportyShoesApplication.class)
public class ProductsServiceTest {

	@Autowired
	ProductsService service;
	
	@Test
	public void listAllProducts() 
	{
		List<Products> productList = service.getAllProducts();
		int count = 0;
		for(Products p : productList)
		{
			count++;
			assertEquals(count, p.getProductId());
		}
	}
	
	@Test
	public void getProductById()
	{
		Products myProduct = service.getProductById(15).get();
		
		assertEquals("Mens Athletic Wear", myProduct.getProductCategory());
		assertEquals("Mens Basketball Shorts", myProduct.getProductDesc());
		assertEquals("Nike Dri-FIT", myProduct.getProductName());
		assertEquals(90, myProduct.getProductPrice());
	}
	
	@Test
	public void searchProductByName()
	{
		Products myProduct = service.searchProductByName("Own The Run Shorts");
		assertEquals(17, myProduct.getProductId());
	}
	
	@Test
	public void addProduct()
	{
		Products myProduct = new Products();
		myProduct.setProductCategory("Mens Athletic Socks");
		myProduct.setProductDesc("Adidas Basketball Socks");
		myProduct.setProductName("Adidas And One Socks");
		myProduct.setProductPrice(25);
		
		int result = service.storeProduct(myProduct);
		
		assertEquals(1, result);
	}
	
//	@Test
//	public void deleteProduct()
//	{
//		Products product = service.searchProductByName("Adidas And One Socks");
//		int result = service.deleteProduct(product);
//		
//		assertEquals(1, result);
//	}

}