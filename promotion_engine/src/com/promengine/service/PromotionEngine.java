package com.promengine.service;

import com.promengine.bean.Cart;

public class PromotionEngine {

	public double calculatePrice(Cart pCart) {
		return pCart.getCartTotal();
	}
}
