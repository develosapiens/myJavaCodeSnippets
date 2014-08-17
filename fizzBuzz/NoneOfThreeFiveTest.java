package fizzBuzz;

import static org.junit.Assert.*;

import org.junit.Test;

public class NoneOfThreeFiveTest 
{
	@Test
	public void testFizzBuzzToPrint() 
	{
		NoneOfThreeFive nOTF = new NoneOfThreeFive();
		assertNull(nOTF.FizzBuzzToPrint());
	}
}
