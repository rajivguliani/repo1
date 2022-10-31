package com.promengine.bean;

import java.util.Map;

public class Cart {
	private Map<String, Integer> items;
	private double cartTotal = 0;

	public Map<String, Integer> getItems() {
		return items;
	}

	public void setItems(Map<String, Integer> items) {
		this.items = items;
	}

	public double getCartTotal() {
		return cartTotal;
	}

	public void setCartTotal(double cartTotal) {
		this.cartTotal = cartTotal;
	}
}
