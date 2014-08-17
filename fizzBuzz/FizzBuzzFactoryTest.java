package fizzBuzz;

import static org.junit.Assert.*;

import org.junit.Test;

public class FizzBuzzFactoryTest 
{

	@Test
	public void testThreeTypeToPrint() 
	{
		assertEquals("Fizz", FizzBuzzFactory.getFizzBuzzInstance(FizzBuzzType.THREE).FizzBuzzToPrint());
	}

	@Test
	public void testFiveTypeToPrint() 
	{
		assertEquals("Buzz", FizzBuzzFactory.getFizzBuzzInstance(FizzBuzzType.FIVE).FizzBuzzToPrint());
	}

	@Test
	public void testThreeFiveTypeToPrint() 
	{
		assertEquals("FizzBuzz", FizzBuzzFactory.getFizzBuzzInstance(FizzBuzzType.THREEFIVE).FizzBuzzToPrint());
	}

	@Test
	public void testNoneToPrint() 
	{
		assertNull(FizzBuzzFactory.getFizzBuzzInstance(FizzBuzzType.NONE).FizzBuzzToPrint());
	}
	
	@Test
	public void testSinglesNONE()
	{
		assertTrue(FizzBuzzFactory.getFizzBuzzInstance(FizzBuzzType.NONE) == FizzBuzzFactory.getFizzBuzzInstance(FizzBuzzType.NONE));
	}
	
	@Test
	public void testSinglesTHREE()
	{
		assertTrue(FizzBuzzFactory.getFizzBuzzInstance(FizzBuzzType.THREE) == FizzBuzzFactory.getFizzBuzzInstance(FizzBuzzType.THREE));
	}
	
	@Test
	public void testSinglesFIVE()
	{
		assertTrue(FizzBuzzFactory.getFizzBuzzInstance(FizzBuzzType.FIVE) == FizzBuzzFactory.getFizzBuzzInstance(FizzBuzzType.FIVE));
	}
	
	@Test
	public void testSinglesTHREEFIVE()
	{
		assertTrue(FizzBuzzFactory.getFizzBuzzInstance(FizzBuzzType.THREEFIVE) == FizzBuzzFactory.getFizzBuzzInstance(FizzBuzzType.THREEFIVE));
	}
}