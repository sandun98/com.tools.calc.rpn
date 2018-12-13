package com.tools.calc.rpn.operations;

import java.math.BigDecimal;
import java.util.Stack;

public abstract class ArithmeticOperator extends Operation {
	protected String type;

	protected Stack<BigDecimal> stack;

	protected abstract void addOperand(BigDecimal num);

}
