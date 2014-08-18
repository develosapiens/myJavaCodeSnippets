package secondBiggest;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSecondBiggestSeeker 
{
	@Test
	public void testNullArray()
	{
		assertNull(SecondBiggestSeeker.findIt(null));
	}
	
	@Test
	public void testEmptyArray()
	{
	    assertNull(SecondBiggestSeeker.findIt(new Integer[0]));
	}
	
	@Test
	public void testArrayOneElement()
	{
		Integer[] intArr = new Integer[1];
	    assertNull(SecondBiggestSeeker.findIt(intArr));
	}
	
	@Test
	public void testIncreasingArray1to20() 
	{
		Integer[] intArr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 
				11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		int result;
		result = SecondBiggestSeeker.findIt(intArr);
		assertEquals(19, result);
	}

	@Test
	public void testDecreasingArray1From20() 
	{
		Integer[] intArr = { 20, 19, 18, 17, 16, 15, 14, 13, 12, 11,
				             10,  9,  8,  7,  6,  5,  4,  3,  2,  1 };
		int result;
		result = SecondBiggestSeeker.findIt(intArr);
		assertEquals(19, result);
	}

	@Test
	public void testTwoSameELementsNull()
	{
		Integer[] iA = new Integer[2];
		iA[0] = 4;
		iA[1] = 4;
		assertNull(SecondBiggestSeeker.findIt(iA));
	}

	@Test
	public void testThreeSameELementsNull()
	{
		Integer[] iA = { 7, 7, 7 };
		assertNull(SecondBiggestSeeker.findIt(iA));
	}
	
	@Test
	public void testFourSameELementsNull()
	{
		Integer[] iA = { 5, 5, 5, 5 };
		assertNull(SecondBiggestSeeker.findIt(iA));
	}
	
	@Test 
	public void testDecreasing1()
	{
		Integer[] iA = { 7, 7, 7, 6 };
		assertEquals((long) 6, (long) SecondBiggestSeeker.findIt(iA));
	}	
	
	@Test 
	public void testIecreasing1()
	{
		Integer[] iA = { 6, 7, 7, 7 };
		assertEquals((long) 6, (long) SecondBiggestSeeker.findIt(iA));
	}

	@Test 
	public void testBellShape1()
	{
		Integer[] iA = { 6, 7, 7, 7, 6 };
		assertEquals((long) 6, (long) SecondBiggestSeeker.findIt(iA));
	}

	@Test 
	public void testBellShape2()
	{
		Integer[] iA = { 6, 7, 9, 7, 6 };
		assertEquals((long) 7, (long) SecondBiggestSeeker.findIt(iA));
	}

	@Test 
	public void testBellShape3()
	{
		Integer[] iA = { 6, 7, 10, 8, 9 };
		assertEquals((long) 9, (long) SecondBiggestSeeker.findIt(iA));
	}	
	
	@Test 
	public void testBellShape4Left()
	{
		Integer[] iA = { 0, 1, 0, 0, 0 };
		assertEquals((long) 0, (long) SecondBiggestSeeker.findIt(iA));
	}
	
	@Test 
	public void testBellShape5Right()
	{
		Integer[] iA = { 0, 0, 0, 1, 0 };
		assertEquals((long) 0, (long) SecondBiggestSeeker.findIt(iA));
	}

	@Test 
	public void testVShape1()
	{
		Integer[] iA = { 1, 1, 0, 1, 1 };
		assertEquals((long) 0, (long) SecondBiggestSeeker.findIt(iA));
	}

	@Test 
	public void testVShape2()
	{
		Integer[] iA = { 2, 1, 0, 1, 2 };
		assertEquals((long) 1, (long) SecondBiggestSeeker.findIt(iA));
	}

	@Test 
	public void testVShape4()
	{
		Integer[] iA = { 2, 1, 0, 2, 1, 1, 1, 1, 1, 1 };
		assertEquals((long) 1, (long) SecondBiggestSeeker.findIt(iA));
	}

	@Test 
	public void testVShape5()
	{
		Integer[] iA = { 2, 1, 0, 2, 2, 1, 2, 2, 2, 2, 2, 2 };
		assertEquals((long) 1, (long) SecondBiggestSeeker.findIt(iA));
	}

	@Test 
	public void testVVVVShape1()
	{
		Integer[] iA = { 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1 };
		assertEquals((long) 1, (long) SecondBiggestSeeker.findIt(iA));
	}
}
