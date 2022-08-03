package com.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class LineItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int lineItemId;
	private int itemId;
	private int orderId;
	private int quantity;
	private float lineItemTotal;
	@OneToOne
    private Products myProduct;
	
	public LineItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public float getLineItemTotal() {
		return lineItemTotal;
	}

	public void setLineItemTotal(float lineItemTotal) {
		this.lineItemTotal = lineItemTotal;
	}

	public int getLineItemId() {
		return lineItemId;
	}

	public void setLineItemId(int lineItemId) {
		this.lineItemId = lineItemId;
	}

	public Products getMyProduct() {
		return myProduct;
	}

	public void setMyProduct(Products myProduct) {
		this.myProduct = myProduct;
	}
	
}
