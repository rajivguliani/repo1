package com.promengine.config;

import static com.promengine.config.ProductConfig.PROD_A;
import static com.promengine.config.ProductConfig.PROD_B;
import static com.promengine.config.ProductConfig.PROD_C;
import static com.promengine.config.ProductConfig.PROD_D;

import java.util.ArrayList;
import java.util.List;

import com.promengine.promotion.MultiProductPromotion;
import com.promengine.promotion.Promotion;
import com.promengine.promotion.SingleProductNItems;

public class PromotionConfig {

	public static List<Promotion> getAllPromotions() {

		List<Promotion> promotionList = new ArrayList<Promotion>();

		SingleProductNItems promotion1 = new SingleProductNItems(PROD_A, 3, 130);
		promotionList.add(promotion1);

		SingleProductNItems promotion2 = new SingleProductNItems(PROD_B, 2, 45);
		promotionList.add(promotion2);

		List<String> productList = new ArrayList<String>();
		productList.add(PROD_C);
		productList.add(PROD_D);
		MultiProductPromotion multiProductPromotion1 = new MultiProductPromotion(productList, 30);
		promotionList.add(multiProductPromotion1);

		return promotionList;
	}
}
