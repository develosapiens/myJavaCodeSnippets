package fizzBuzz;

import static org.junit.Assert.*;

import org.junit.Test;

public class FiveTest 
{
	@Test
	public void testFizzBuzzToPrint() 
	{
		Five f = new Five();
		assertEquals("Buzz", f.FizzBuzzToPrint());
	}
}
