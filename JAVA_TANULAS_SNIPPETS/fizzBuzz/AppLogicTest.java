package fizzBuzz;

import static org.junit.Assert.*;

import org.junit.Test;

public class AppLogicTest 
{
	@Test
	public void test10() 
	{
		assertTrue(AppLogic.getFizzBuzz(10) instanceof Five);
	}

	@Test
	public void test1() 
	{
		assertTrue(AppLogic.getFizzBuzz(1) instanceof NoneOfThreeFive);
	}
	
	@Test
	public void test9() 
	{
		assertTrue(AppLogic.getFizzBuzz(9) instanceof Three);
	}
	
	@Test
	public void test15() 
	{
		assertTrue(AppLogic.getFizzBuzz(15) instanceof ThreeFive);
	}

	@Test
	public void testThrees()
	{
		int[] numArr4Testing = { 3, 6, 9, 12, 18, 21, 24, 27, 33, 36};
		for (int i : numArr4Testing)
		{
			assertTrue(AppLogic.getFizzBuzz(i)  instanceof Three);
		}
	}
	
	@Test
	public void testFives()
	{
		int[] numArr4Testing = { 5, 10, 20, 25, 35, 40, 50, 55};
		for (int i : numArr4Testing)
		{
			assertTrue(AppLogic.getFizzBuzz(i)  instanceof Five);
		}
	}
	
	@Test
	public void testThreeFives()
	{
		int[] numArr4Testing = { 15, 30, 45, 60 };
		for (int i : numArr4Testing)
		{
			assertTrue(AppLogic.getFizzBuzz(i)  instanceof ThreeFive);
		}
	}
	
	@Test
	public void testNone()
	{
		int[] numArr4Testing = { 2, 4, 7, 8, 11, 13, 14, 16, 17, 19, 22, 23, 26, 28, 29 };
		for (int i : numArr4Testing)
		{
			assertTrue(AppLogic.getFizzBuzz(i)  instanceof NoneOfThreeFive);
		}
	}
}
