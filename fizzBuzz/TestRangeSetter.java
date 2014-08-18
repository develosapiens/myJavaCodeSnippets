package fizzBuzz;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestRangeSetter 
{
	@Test
	public void testCreatingRangeWithZeroArgs() 
	{
		String[] args = {};
		RangeSetter rSetter = new RangeSetter();
		Range range = rSetter.setRange(args);
		assertEquals(1, (int) range.getFirstNumber());
		assertEquals(44, (int) range.getLastNumber());
	}
	
	@Test
	public void testCreatingRangeWithOneArg()
	{
		String[] args = { "5" };
		RangeSetter rSetter = new RangeSetter();
		Range range = rSetter.setRange(args);
		assertEquals(5, (int) range.getFirstNumber());
		assertEquals(48, (int) range.getLastNumber());
	}

	@Test
	public void testCreatingRangeWithTwoArgs()
	{
		String[] args = { "11", "20" };
		RangeSetter rSetter = new RangeSetter();
		Range range = rSetter.setRange(args);
		assertEquals(11, (int) range.getFirstNumber());
		assertEquals(20, (int) range.getLastNumber());
	}

	@Test
	public void testCreatingRangeWithMoreThanTwoArgs()
	{
		String[] args = { "11", "20", "33" };
		RangeSetter rSetter = new RangeSetter();
		Range range = rSetter.setRange(args);
		assertEquals(11, (int) range.getFirstNumber());
		assertEquals(20, (int) range.getLastNumber());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFirstArgIsBigger()
	{
		String[] args = { "40", "33" };
		RangeSetter rSetter = new RangeSetter();
		Range range = rSetter.setRange(args);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSameArgs()
	{
		String[] args = { "40", "40" };
		RangeSetter rSetter = new RangeSetter();
		Range range = rSetter.setRange(args);
	}
}
