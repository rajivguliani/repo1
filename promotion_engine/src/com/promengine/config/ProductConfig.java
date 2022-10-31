package com.promengine.config;

import java.util.HashMap;
import java.util.Map;

public class ProductConfig {

	public static String PROD_A = "A";
	public static String PROD_B = "B";
	public static String PROD_C = "C";
	public static String PROD_D = "D";

	private static Map<String, Double> rateMap = null;

	static {
		rateMap = new HashMap<String, Double>();
		rateMap.put(PROD_A, 50.0);
		rateMap.put(PROD_B, 30.0);
		rateMap.put(PROD_C, 20.0);
		rateMap.put(PROD_D, 15.0);
	}

	public static double getPrice(String pProduct) {
		double price = 0;
		if (pProduct != null) {
			if (rateMap.containsKey(pProduct)) {
				price = rateMap.get(pProduct);
			}
		}
		return price;
	}
}
