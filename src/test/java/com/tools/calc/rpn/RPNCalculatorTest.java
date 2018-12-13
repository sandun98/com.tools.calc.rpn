package com.tools.calc.rpn;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)

public class RPNCalculatorTest {

	@Test
	public void testWithEmpty() {
		RPNCalculator calculator = new RPNCalculator();
		assertEquals("", calculator.process(""));

	}

	@Test
	public void testWithNull() {
		RPNCalculator calculator = new RPNCalculator();
		assertEquals("", calculator.process(null));

	}

	@Test
	public void testWithOneInput() {
		RPNCalculator calculator = new RPNCalculator();
		assertEquals("4", calculator.process("4"));
	}

	@Test
	public void testWithOneInputAndClear() {
		RPNCalculator calculator = new RPNCalculator();

		assertEquals("", calculator.process("4 clear"));
	}

	@Test
	public void testWithNumberClearNumbersQrt() {
		RPNCalculator calculator = new RPNCalculator();
		calculator.process("4 clear 9 sqrt");

		assertEquals("3", calculator.process("4 clear 9 sqrt"));
	}

	@Test
	public void testNumberNumberSubNumberSub() {
		RPNCalculator calculator = new RPNCalculator();

		assertEquals("0", calculator.process("5 2 - 3 -"));
	}

	@Test
	public void test_5Numbers_Clear_2Numbers_Sub() {
		RPNCalculator calculator = new RPNCalculator();
		calculator.process("1");
		calculator.process("2");
		calculator.process("3");
		calculator.process("4");
		calculator.process("5");
		calculator.process("*");
		calculator.process("clear");

		calculator.process("3");
		calculator.process("4");
		calculator.process("-");

		assertEquals("-1", calculator.process("1 2 3 4 5 * clear 3 4 -"));

	}

	@Test
	public void test_5Numbers_4Multiply() {
		RPNCalculator calculator = new RPNCalculator();
		assertEquals("120", calculator.process("1 2 3 4 5 * * * *"));
	}

	@Test
	public void test_2Numbers_Plus_Num_Div_Num_Num_Mul() {
		RPNCalculator calculator = new RPNCalculator();
		assertEquals("10 24", calculator.process("10 10 + 2 / 3 8 *"));
	}

	@Test
	public void testWithOneInputHat() {
		RPNCalculator calculator = new RPNCalculator();
		assertEquals("4", calculator.process("4 ^ $"));
	}

	@Test
	public void testWithOneInputSqrt() {
		RPNCalculator calculator = new RPNCalculator();
		assertEquals("2", calculator.process("4 sqrt"));
	}

	@Test
	public void testWith2Sqrt() {
		RPNCalculator calculator = new RPNCalculator();
		assertEquals("1.4142135623", calculator.process("2 sqrt"));
	}

	@Test
	public void testWith2Inputs() {
		RPNCalculator calculator = new RPNCalculator();
		assertEquals("4 3", calculator.process("4 3"));

	}

	@Test
	public void testWith2InputsPlus() {
		RPNCalculator calculator = new RPNCalculator();
		assertEquals("7", calculator.process("4 3 +"));

	}

	@Test
	public void testWith2InputsDivide() {
		RPNCalculator calculator = new RPNCalculator();
		assertEquals("2", calculator.process("4 2 /"));

	}

	@Test
	public void test1Num_undo() {
		RPNCalculator calculator = new RPNCalculator();
		assertEquals("", calculator.process("4 undo"));

	}

	@Test
	public void test2Num_undo() {
		RPNCalculator calculator = new RPNCalculator();
		assertEquals("4", calculator.process("4 2 undo"));

	}

	@Test
	public void test2Num_Plus_undo() {
		RPNCalculator calculator = new RPNCalculator();
		assertEquals("4 2", calculator.process("4 2 + undo"));

	}

	@Test
	public void test4Num_2undo_Mul_Num_Mul_undo() {
		RPNCalculator calculator = new RPNCalculator();
		assertEquals("20 5", calculator.process("5 4 3 2 undo undo * 5 * undo"));

	}

	@Test
	public void test_3Numbers_Multiply_Number_Plus_2Multiply_N2Numbers_checkError() {
		RPNCalculator calculator = new RPNCalculator();
		calculator.process("1 2 3 * 5 + * * 6 5");

		assertEquals("operator * (position :8): insufficient parameters", calculator.getError());
	}

	@Test
	public void test_3Numbers_Multiply_Number_Plus_2Multiply_N2Numbers_checkStack() {
		RPNCalculator calculator = new RPNCalculator();

		assertEquals("11", calculator.process("1 2 3 * 5 + * * 6 5"));

	}

}
