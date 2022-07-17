package com.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "product")
public class Product 
{
	@Id
	@GeneratedValue
	public int product_id;
	public String product_category;
	public String product_name;
	public float product_price;
	public String product_desc;
	
	public Product()
	{
		super();
		System.out.println("A product Object Was Created!");
	}

	public int getProduct_id()
	{
		return product_id;
	}

	public void setProduct_id(int product_id)
	{
		this.product_id = product_id;
	}

	public String getProduct_category()
	{
		return product_category;
	}

	public void setProduct_category(String product_category)
	{
		this.product_category = product_category;
	}

	public String getProduct_name()
	{
		return product_name;
	}

	public void setProduct_name(String product_name)
	{
		this.product_name = product_name;
	}

	public float getProduct_price()
	{
		return product_price;
	}

	public void setProduct_price(float product_price)
	{
		this.product_price = product_price;
	}

	public String getProduct_desc()
	{
		return product_desc;
	}

	public void setProduct_desc(String product_desc)
	{
		this.product_desc = product_desc;
	}
}
