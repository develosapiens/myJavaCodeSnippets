package secondBiggest;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPreProcessor 
{
	@Test(expected=IllegalArgumentException.class)
	public void testZeroLengthArray() 
	{
		String[] args = {};
		PreProcessor proc = new PreProcessor();
		proc.createIntArray(args);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testOneElemetInArray()
	{
		String[] args = { "3" };
		PreProcessor proc = new PreProcessor();
		proc.createIntArray(args);
	}
	
	@Test
	public void testStringArrayToIntArrayTwoDifferentElements()
	{
		String[] args = { "3", "2" };
		PreProcessor proc = new PreProcessor();
		assertEquals(2, (int) SecondBiggestSeeker.findIt(proc.createIntArray(args)));
	}
	
	@Test
	public void testStringArrayToIntArrayTwoSameElements()
	{
		String[] args = { "3", "3" };
		PreProcessor proc = new PreProcessor();
		assertNull(SecondBiggestSeeker.findIt(proc.createIntArray(args)));
	}
	
	
}
