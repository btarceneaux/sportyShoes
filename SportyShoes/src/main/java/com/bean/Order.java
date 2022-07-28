package com.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Order 
{
	@Id
	private int orderId;
	@ManyToOne
	@JoinColumn(name = "userId", insertable=false, updatable=false)
	private User myUser;
	private Date purchaseDate;
	private float price;
	@OneToMany
	@JoinColumn(name = "orderId",insertable=false, updatable=false )
	private List<LineItem> lineItem = new ArrayList<>();
	
	public List<LineItem> getLineItem() {
		return lineItem;
	}

	public void setLineItem(List<LineItem> lineItem) {
		this.lineItem = lineItem;
	}

	public Order() 
	{
		super();
		System.out.println("An orders object has been created");
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public User getMyUser() {
		return myUser;
	}

	public void setMyUser(User myUser) {
		this.myUser = myUser;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
