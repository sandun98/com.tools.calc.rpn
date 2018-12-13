package com.tools.calc.rpn.operations;

import java.math.BigDecimal;
import java.util.Stack;

public class PushOpertaion extends Operation {

	private BigDecimal num1;
	private Stack<BigDecimal> stack;

	public PushOpertaion(Stack<BigDecimal> stack, BigDecimal in) {
		super();
		num1 = in;
		this.stack = stack;
	}

	@Override
	public Object run() {
		return stack.push(num1);
	}

	@Override
	public void undo() {
		stack.pop();
	}

}
