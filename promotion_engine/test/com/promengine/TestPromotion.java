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

	/**
	 * 130+130+50+50=360
	 */
	@Test
	void checkPromotionA_1() {
		items.put(PROD_A, 8);
		cart.setItems(items);

		assertEquals(engine.calculatePrice(cart), 360);
	}

	/**
	 * 130+130+130+20=410
	 */
	@Test
	void checkPromotionA_2() {
		items.put(PROD_A, 9);
		items.put(PROD_C, 1);
		cart.setItems(items);

		assertEquals(engine.calculatePrice(cart), 410);
	}

	/**
	 * 130+130+50+50+30+15=405
	 */
	@Test
	void checkPromotionA_3() {
		items.put(PROD_A, 8);
		items.put(PROD_B, 1);
		items.put(PROD_D, 1);
		cart.setItems(items);

		assertEquals(engine.calculatePrice(cart), 405);
	}

	/**
	 * 45+45+30=120
	 */
	@Test
	void checkPromotionB_1() {
		items.put(PROD_B, 5);
		cart.setItems(items);

		assertEquals(engine.calculatePrice(cart), 120);
	}

	/**
	 * 45*10=450
	 */
	@Test
	void checkPromotionB_2() {
		items.put(PROD_B, 20);
		cart.setItems(items);

		assertEquals(engine.calculatePrice(cart), 450);
	}

	/**
	 * 130+130+50+45+45+45+30=475
	 */
	@Test
	void checkPromotionAandB_1() {
		items.put(PROD_A, 7);
		items.put(PROD_B, 7);
		cart.setItems(items);

		assertEquals(engine.calculatePrice(cart), 475);
	}

	/**
	 * 130+130+130+50+45+45+45+45+45=665
	 */
	@Test
	void checkPromotionAandB_2() {
		items.put(PROD_A, 10);
		items.put(PROD_B, 10);
		cart.setItems(items);

		assertEquals(engine.calculatePrice(cart), 665);
	}

	/**
	 * 50+30+30=110
	 */
	@Test
	void checkMultiPromotion_1() {
		items.put(PROD_A, 1);
		items.put(PROD_B, 1);
		items.put(PROD_C, 1);
		items.put(PROD_D, 1);
		cart.setItems(items);

		assertEquals(engine.calculatePrice(cart), 110);
	}

	/**
	 * 50+30+120=200
	 */
	@Test
	void checkMultiPromotion_2() {
		items.put(PROD_A, 1);
		items.put(PROD_B, 1);
		items.put(PROD_C, 4);
		items.put(PROD_D, 4);
		cart.setItems(items);

		assertEquals(engine.calculatePrice(cart), 200);
	}

	/**
	 * 50+30+90+60=230
	 */
	@Test
	void checkMultiPromotion_3() {
		items.put(PROD_A, 1);
		items.put(PROD_B, 1);
		items.put(PROD_C, 3);
		items.put(PROD_D, 7);
		cart.setItems(items);

		assertEquals(engine.calculatePrice(cart), 230);
	}
}
