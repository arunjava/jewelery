package com.nura.jewelery.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class IDGenerator {

	private IDGenerator() {}
	
	private static AtomicInteger counter = new AtomicInteger(1000);

	public static String getSalesTxID() {
		if (counter.get() >= 10000) {
			counter.set(1000);
		}
		return "TXNS" + System.currentTimeMillis() + counter.getAndIncrement();
	}

}
