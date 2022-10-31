package com.promengine;

import static com.promengine.config.ProductConfig.PROD_A;
import static com.promengine.config.ProductConfig.PROD_B;
import static com.promengine.config.ProductConfig.PROD_C;
import static com.promengine.config.ProductConfig.PROD_D;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.promengine.bean.Cart;
import com.promengine.service.PromotionEngine;

class TestPromotion {

	private PromotionEngine engine = null;
	private Cart cart = null;
	private Map<String, Integer> items = null;

	@BeforeEach
	void init() {
		engine = new PromotionEngine();
		cart = new Cart();
		items = new HashMap<String, Integer>();
	}

	@Test
	void checkEmptyCart() {
		assertEquals(engine.calculatePrice(cart), 0);
	}

	/**
	 * 50+15=65
	 */
	@Test
	void checkSimpleCart1() {
		items.put(PROD_A, 1);
		items.put(PROD_D, 1);
		cart.setItems(items);

		assertEquals(engine.calculatePrice(cart), 65);
	}

	/**
	 * 50+30+20=100
	 */
	@Test
	void checkSimpleCart2() {
		items.put(PROD_A, 1);
		items.put(PROD_B, 1);
		items.put(PROD_C, 1);
		cart.setItems(items);

		assertEquals(engine.calculatePrice(cart), 100);
	}
}
