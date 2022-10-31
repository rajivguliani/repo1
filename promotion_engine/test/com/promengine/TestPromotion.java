package com.promengine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.promengine.bean.Cart;
import com.promengine.service.PromotionEngine;

class TestPromotion {

	private PromotionEngine engine = null;
	private Cart cart = null;

	@BeforeEach
	void init() {
		engine = new PromotionEngine();
		cart = new Cart();
	}

	@Test
	void checkEmptyCart() {
		assertEquals(engine.calculatePrice(cart), 0);
	}
}
