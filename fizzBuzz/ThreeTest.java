package fizzBuzz;

import static org.junit.Assert.*;

import org.junit.Test;

public class ThreeTest 
{
	@Test
	public void testFizzBuzzToPrint() 
	{
		Three t = new Three();
		assertEquals("Fizz", t.FizzBuzzToPrint());
	}
}
