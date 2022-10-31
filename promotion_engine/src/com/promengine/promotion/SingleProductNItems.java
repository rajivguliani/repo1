package com.promengine.promotion;

import java.util.Map;

import com.promengine.bean.Cart;
import com.promengine.config.ProductConfig;

public class SingleProductNItems implements Promotion {

	private String product;
	private int quota;
	private double promotedPrice;

	public SingleProductNItems(String pProduct, int pQuota, double pPromotedPrice) {
		this.product = pProduct;
		this.quota = pQuota;
		this.promotedPrice = pPromotedPrice;
	}

	/**
	 * Promotion is applicable only if cart has more quantity than quota for given
	 * product
	 * 
	 */
	@Override
	public void applyPromotion(Cart pCart) {
		double discount = 0;
		if (pCart != null) {
			Map<String, Integer> items = pCart.getItems();
			if (items != null && items.containsKey(this.product)) {
				int quantity = items.get(this.product);
				if (quantity >= this.quota) {
					discount = ((ProductConfig.getPrice(product) * this.quota) - this.promotedPrice)
							* (int) (quantity / this.quota);
				}
				pCart.setCartTotal(pCart.getCartTotal() - discount);
			}
		}
	}
}
