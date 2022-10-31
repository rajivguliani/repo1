package com.promengine.service;

import java.util.List;
import java.util.Map;

import com.promengine.bean.Cart;
import com.promengine.config.ProductConfig;
import com.promengine.config.PromotionConfig;
import com.promengine.promotion.Promotion;

public class PromotionEngine {

	public double calculatePrice(Cart pCart) {
		// Calculate price without promotion
		calculatePriceWithoutPromotion(pCart);

		// Apply promotions
		applyPromotions(pCart);

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

	private void applyPromotions(Cart pCart) {
		// Get all available promotions
		List<Promotion> promotionList = PromotionConfig.getAllPromotions();

		if (promotionList != null && promotionList.size() > 0) {
			for (Promotion promotion : promotionList) {
				promotion.applyPromotion(pCart);
			}
		}
	}

}
