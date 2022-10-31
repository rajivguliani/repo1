package com.promengine.promotion;

import java.util.List;
import java.util.Map;

import com.promengine.bean.Cart;
import com.promengine.config.ProductConfig;

public class MultiProductPromotion implements Promotion {

	private List<String> products;
	private double promotedPrice;

	public MultiProductPromotion(List<String> pProducts, double pPromotedPrice) {
		this.products = pProducts;
		this.promotedPrice = pPromotedPrice;
	}

	/**
	 * Promotion is only applicable if cart has at least one quantity of all the
	 * items listed in promotion.
	 */
	@Override
	public void applyPromotion(Cart pCart) {
		double discount = 0;
		if (pCart != null) {
			int maxCount = getMaxApplicableCount(pCart);
			if (maxCount > 0) {
				double prodSum = 0;
				for (String product : this.products) {
					prodSum += ProductConfig.getPrice(product);
				}
				discount = maxCount * (prodSum - this.promotedPrice);
				pCart.setCartTotal(pCart.getCartTotal() - discount);
			}
		}
	}

	/**
	 * This method gives max applicable count on which promotion can be applied.
	 * 
	 * @param pCart
	 * @return
	 */
	private int getMaxApplicableCount(Cart pCart) {
		int maxCount = 0;
		if (this.products != null) {
			for (String product : this.products) {
				if (pCart != null) {
					Map<String, Integer> items = pCart.getItems();
					if (items != null && items.containsKey(product)) {
						int quantity = items.get(product);
						if (maxCount == 0) {
							maxCount = quantity;
						} else {
							if (maxCount > quantity) {
								maxCount = quantity;
							}
						}
					} else {
						// Can't apply promotion as not all products are in cart
						maxCount = -1;
						break;
					}
				}
			}
		}
		return maxCount;
	}
}
