package com.tools.calc.rpn.operations;

import java.math.BigDecimal;
import java.util.Stack;

public class BinarayOperation extends ArithmeticOperator {

	public BinarayOperation(String op, Stack<BigDecimal> stack) {
		super();

		if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/"))
			type = op;
		else
			throw new RuntimeException("Not a valid operator");
		this.stack = stack;

	}

	private BigDecimal num1;
	private BigDecimal num2;

	protected void addOperand(BigDecimal num) {
		if (num1 != null && num2 != null)
			throw new RuntimeException("too many parameters");
		if (num1 == null)
			num1 = num;
		else if (num2 == null)
			num2 = num;

	}

	@Override
	public BigDecimal run() {

		int numbersCount = 0;
		if (stack.size() >= 2) {
			while (stack.size() >= 1 && numbersCount < 2) {
				addOperand((stack.pop()));
				numbersCount++;
			}
		}

		if (num1 == null || num2 == null)
			throw new RuntimeException("insufficient parameters");
		else if (type.equals("+"))
			return stack.push(num1.add(num2));
		else if (type.equals("-"))
			return stack.push(num2.subtract(num1));
		else if (type.equals("*"))
			return stack.push(num1.multiply(num2));
		else if (type.equals("/")) {
			BigDecimal va = stack.push(num2.divide(num1));
			return va;
		}
		throw new RuntimeException("Not a valid operation");

	}

	@Override
	public void undo() {
		stack.pop();
		stack.push(num2);
		stack.push(num1);
	}

}
