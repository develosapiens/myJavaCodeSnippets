/** 1 1 2 3 5 8 13 21 */
package fibonacciPkg;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestTheFiboProcessor 
{
	private TheFiboProcessor fP;
	
	@Before
	public void setUp() throws Exception 
	{
		fP = new TheFiboProcessor();
	}

	@Test
	public void testFirstElement() 
	{
		assertEquals(1, fP.getFiboValue(1));
	}
	
	@Test
	public void testSecondElement() 
	{
		assertEquals(1, fP.getFiboValue(2));
	}
	
	@Test
	public void testThirdElement() 
	{
		assertEquals(2, fP.getFiboValue(3));
	}
	
	@Test
	public void testFourthElement() 
	{
		assertEquals(3, fP.getFiboValue(4));
	}
	
	@Test
	public void testFifthElement() 
	{
		assertEquals(5, fP.getFiboValue(5));
	}
	
	@Test
	public void testSixthElement() 
	{
		assertEquals(8, fP.getFiboValue(6));
	}
	
	@Test
	public void testSeventhElement() 
	{
		assertEquals(13, fP.getFiboValue(7));
	}
	
	@Test
	public void testEighthElement() 
	{
		assertEquals(21, fP.getFiboValue(8));
	}
	
	@Test
	public void testNinethElement() 
	{
		assertEquals(34, fP.getFiboValue(9));
	}
}
