package fizzBuzz;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestRange 
{
	@Test
	public void testRangeCreationWithNoArgs() 
	{
		Range range = new Range();
		assertEquals(1, range.getFirstNumber());
		assertEquals(44, range.getLastNumber());
	}

	@Test
	public void testRangeCreationWithOneArgs() 
	{
		Range range = new Range(6);
		assertEquals(6, range.getFirstNumber());
		assertEquals(49, range.getLastNumber());
	}

	@Test
	public void testRangeCreationWithTwoArgs() 
	{
		Range range = new Range(10, 20);
		assertEquals(10, range.getFirstNumber());
		assertEquals(20, range.getLastNumber());
	}

	@Test
	public void testRangeCreationWithTwoArgsDecreasing() 
	{
		Range range = new Range(30, 20);
		assertEquals(30, range.getFirstNumber());
		assertEquals(20, range.getLastNumber());
	}

	@Test
	public void testRangeCreationWithTwoSameArgs() 
	{
		Range range = new Range(20, 20);
		assertEquals(20, range.getFirstNumber());
		assertEquals(20, range.getLastNumber());
	}
}
