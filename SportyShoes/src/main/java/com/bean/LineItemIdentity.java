package com.bean;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class LineItemIdentity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int itemId;
	private int orderId;
	
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
	
	public LineItemIdentity(int itemId, int orderId) {
		super();
		this.itemId = itemId;
		this.orderId = orderId;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(itemId, orderId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineItemIdentity other = (LineItemIdentity) obj;
		return itemId == other.itemId && orderId == other.orderId;
	}	
	
	
}
