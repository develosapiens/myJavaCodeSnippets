package fizzBuzz;

import static org.junit.Assert.*;

import org.junit.Test;

public class ThreeFiveTest 
{
	@Test
	public void testFizzBuzzToPrint() 
	{
		ThreeFive tF = new ThreeFive();
		assertEquals("FizzBuzz", tF.FizzBuzzToPrint());
	}
}
