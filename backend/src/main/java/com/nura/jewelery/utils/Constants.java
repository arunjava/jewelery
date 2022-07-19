package com.nura.jewelery.utils;

public final class Constants {

	public static final String SCHEMA_JEWEL = "jewel";

	public static final String UI_URL = "http://localhost:4200";

	/**
	 * Service response message
	 */
	public static final String RESP_SUCCESS = "Success";
	public static final String RESP_FAILED = "Failed";

	static final String CAL_MONTHS = "MONTHS";
	static final String CAL_YEAR = "YEAR";
	static final String CAL_DAYS = "DAYS";

	public enum SALES_CHARGES {
		WASTAGE_CHARGES, MAKING_CHARGES
	}

	public enum OFFER_TYPES {
		WEIGHT, MONEY, WASTAGE_CHARGES, MAKING_CHARGES
	}
}
