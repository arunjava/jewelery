package com.nura.jewelery.utils;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public final class ExpressionParserUtil {

	private ExpressionParserUtil() {

	}

	public static Object parseExp(String expression, String replaceVal) {
		ExpressionParser parser = new SpelExpressionParser();
		String expVal = expression.replaceFirst("\\?", replaceVal);
		Expression exp = parser.parseExpression(expVal);
		return exp.getValue();
	}

}
