package com.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Table;

@Entity
public class Orders 
{
	@Id
	private int orderId;
	private int userId;
	private float orderTotal;
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
	//private List<LineItem> lineItem = new ArrayList<LineItem>();
	private Date orderDate;
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getOrderDate() {
		return orderDate;
	}

//	public List<LineItem> getLineItem() {
//		return lineItem;
//	}
//
//	public void setLineItem(List<LineItem> lineItem) {
//		this.lineItem = lineItem;
//	}

	public Orders() 
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


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public float getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(float orderTotal) {
		this.orderTotal = orderTotal;
	}

}
