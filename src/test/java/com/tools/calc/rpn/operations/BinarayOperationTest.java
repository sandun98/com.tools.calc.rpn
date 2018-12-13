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
public class BinarayOperationTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private Stack<BigDecimal> getStack(BigDecimal... bigDecimals) {
		Stack<BigDecimal> stack = new Stack<>();
		for (BigDecimal bigDecimal : bigDecimals) {
			stack.push(bigDecimal);
		}
		return stack;
	}

	@Test
	public void createOperator() {
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("insufficient parameters");
		ArithmeticOperator operator = new BinarayOperation("+", getStack());
		operator.run();
	}

	@Test

	public void testWithOneInput() {
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("insufficient parameters");
		ArithmeticOperator operator = new BinarayOperation("+", getStack(new BigDecimal(1)));
		operator.addOperand(new BigDecimal(1));
		operator.run();
	}

	@Test
	public void testWith2Inputs() {
		ArithmeticOperator operator = new BinarayOperation("+", getStack(new BigDecimal(2), new BigDecimal(1)));

		assertThat(new BigDecimal(3) == operator.run());
	}

	@Test
	public void testWith3Inputs() {

		ArithmeticOperator operator = new BinarayOperation("+",
				getStack(new BigDecimal(2), new BigDecimal(1), new BigDecimal(2)));
		operator.run();
	}

	@Test
	public void testAddWith2Inputs() {
		ArithmeticOperator operator = new BinarayOperation("+", getStack(new BigDecimal(2), new BigDecimal(1)));

		assertThat(new BigDecimal(3) == operator.run());
	}

	@Test
	public void testSubWith2Inputs() {
		ArithmeticOperator operator = new BinarayOperation("-", getStack(new BigDecimal(2), new BigDecimal(1)));

		assertThat(new BigDecimal(1) == operator.run());
	}

	@Test
	public void testMulWith2Inputs() {
		ArithmeticOperator operator = new BinarayOperation("*", getStack(new BigDecimal(2), new BigDecimal(1)));

		assertThat(new BigDecimal(2) == operator.run());
	}

	@Test
	public void testDivWith2Inputs() {
		ArithmeticOperator operator = new BinarayOperation("/", getStack(new BigDecimal(2), new BigDecimal(4)));

		assertThat(new BigDecimal(2) == operator.run());
	}

}
