package com.tools.calc.rpn.operations;

import java.math.BigDecimal;
import java.util.Stack;

public class UnaryOperation extends ArithmeticOperator {
	private BigDecimal num1;

	public UnaryOperation(String op, Stack<BigDecimal> stack) {
		super();
		if (op.equals("sqrt"))
			type = op;
		else
			throw new RuntimeException("Not a valid operator");
		this.stack = stack;
	}

	@Override
	protected void addOperand(BigDecimal num) {
		if (num1 == null)
			num1 = num;
		else
			throw new RuntimeException("Too many parameters");

	}

	@Override
	public BigDecimal run() {

		int numbersCount = 0;
		while (stack.size() >= 1 && numbersCount < 1) {
			addOperand(stack.pop());
		}
		if (num1 == null)
			throw new RuntimeException("insufficient parameters");
		if (type.equals("sqrt"))

			return stack.push(new BigDecimal(Math.sqrt(num1.doubleValue())));

		return null;
	}

	@Override
	public void undo() {
		stack.pop();
		stack.push(num1);
	}

}
