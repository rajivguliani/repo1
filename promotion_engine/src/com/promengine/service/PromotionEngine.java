package com.promengine.service;

import java.util.Map;

import com.promengine.bean.Cart;
import com.promengine.config.ProductConfig;

public class PromotionEngine {

	public double calculatePrice(Cart pCart) {
		// Calculate price without promotion
		calculatePriceWithoutPromotion(pCart);

		// TODO: Apply promotions

		return pCart.getCartTotal();
	}

	private void calculatePriceWithoutPromotion(Cart pCart) {
		double cartTotal = 0;
		if (pCart != null) {
			Map<String, Integer> items = pCart.getItems();
			if (items != null) {
				for (Map.Entry<String, Integer> entry : items.entrySet()) {
					String product = entry.getKey();
					int quantity = entry.getValue();
					double prodPrice = ProductConfig.getPrice(product) * quantity;
					cartTotal += prodPrice;
				}
			}
		}
		pCart.setCartTotal(cartTotal);
	}
}
