package com.tools.calc.rpn;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

import com.tools.calc.rpn.operations.BinarayOperation;
import com.tools.calc.rpn.operations.Operation;
import com.tools.calc.rpn.operations.PushOpertaion;
import com.tools.calc.rpn.operations.UnaryOperation;

public class RPNCalculator {
	private String error;

	Stack<BigDecimal> stack = new Stack<>();

	Stack<Operation> history = new Stack<>();

	private int position;

	public String getError() {
		return error;
	}

	public String process(String in) {
		if (in == null)
			return "";
		String[] ins = in.split(" ");
		for (String input : ins) {
			input(input);
		}

		return getStackContent();
	}

	private String getStackContent() {
		String stackContent = "";
		for (BigDecimal value : stack) {

			if (value.scale() > 10)
				value = value.setScale(10, RoundingMode.DOWN);
			stackContent += value.toString() + " ";
		}
		return stackContent.trim();

	}

	private Operation pushOpertaion(String val) {
		if (error == null) {
			Operation o = new PushOpertaion(stack, new BigDecimal(val));
			o.run();
			return o;
		}
		return null;

	}

	public void input(String in) {
		try {
			position++;
			Operation operation = null;
			if (isBinaryOperator(in)) {
				operation = performBinary(in);
			} else if (isUnaryOperator(in)) {
				operation = performUnary(in);
			} else if (isNonArithmeticOperator(in)) {
				performNonArithmetic(in);
			} else {
				operation = pushOpertaion(in);
			}
			if (operation != null)
				history.push(operation);
		} catch (NumberFormatException e) {
			error = ("operator " + in + " (position :" + position + "): unkown operator");

		} catch (Exception e) {
			error = ("operator " + in + " (position :" + position + "): " + e.getMessage());
		}

	}

	private boolean isBinaryOperator(String in) {
		if (in.equalsIgnoreCase("+") || in.equalsIgnoreCase("*") || in.equalsIgnoreCase("/")
				|| in.equalsIgnoreCase("-"))
			return true;
		return false;

	}

	private boolean isNonArithmeticOperator(String in) {
		if (in.equalsIgnoreCase("clear") || in.equalsIgnoreCase("undo"))
			return true;
		return false;

	}

	private boolean isUnaryOperator(String in) {
		if (in.equalsIgnoreCase("sqrt"))
			return true;
		return false;

	}

	private Operation performBinary(String symbol) {
		BinarayOperation operator = new BinarayOperation(symbol, stack);
		operator.run();
		return history.push(operator);

	}

	private void performNonArithmetic(String in) {
		if (in.equalsIgnoreCase("clear")) {
			clear();

		} else if (in.equalsIgnoreCase("undo")) {
			undo();

		}

	}

	private void clear() {
		stack.clear();
		error = null;
		position = 0;

	}

	private void undo() {
		history.pop().undo();
	}

	private Operation performUnary(String symbol) {
		UnaryOperation operator = new UnaryOperation(symbol, stack);
		operator.run();
		return operator;
	}

}
