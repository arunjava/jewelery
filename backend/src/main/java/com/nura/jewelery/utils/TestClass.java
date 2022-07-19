package com.nura.jewelery.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class TestClass {

	public static void main(String[] args) {
		ExpressionParser parser = new SpelExpressionParser();
		String expVal = "? * 7 / 100".replaceFirst("\\?", "100");
		Expression exp = parser.parseExpression(expVal);
		System.out.println(exp.getValue());
		
	}

	class Values {

		private Map<String, String> offerType = new HashMap<>();

		/**
		 * @return the offerType
		 */
		public Map<String, String> getOfferType() {
			return offerType;
		}

		/**
		 * @param offerType the offerType to set
		 */
		public void setOfferType(Map<String, String> offerType) {
			this.offerType = offerType;
		}

	}

}
