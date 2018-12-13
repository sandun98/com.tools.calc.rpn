package com.tools.calc.rpn.operations;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Stack;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)

public class UnaryOperationTest {
	private Stack<BigDecimal> getStack(BigDecimal... bigDecimals) {
		Stack<BigDecimal> stack = new Stack<>();
		for (BigDecimal bigDecimal : bigDecimals) {
			stack.push(bigDecimal);
		}
		return stack;
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testInvalidOperator() {
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("Not a valid operator");
		ArithmeticOperator operator = new UnaryOperation("+", getStack());
		operator.run();
	}

	@Test

	public void testInsufficientParameters() {
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("insufficient parameters");
		ArithmeticOperator operator = new UnaryOperation("sqrt", getStack());
		operator.run();
	}

	@Test
	public void testTooManyParametersSqrt() {
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("Too many parameters");
		ArithmeticOperator operator = new UnaryOperation("sqrt", getStack(new BigDecimal(2), new BigDecimal(2)));

		operator.run();

	}

	@Test
	public void testNumberSqrt() {

		ArithmeticOperator operator = new UnaryOperation("sqrt", getStack(new BigDecimal(9)));
		assertThat(new BigDecimal(3) == operator.run());

	}

}
