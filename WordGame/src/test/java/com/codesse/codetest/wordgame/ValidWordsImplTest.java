package com.codesse.codetest.wordgame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ValidWordsImplTest
{
	protected ValidWordsImpl vW;

	@Before
	public void setUp()
	{
		vW = new ValidWordsImpl();
	}

	@Test
	public void testVectorSizeNotZero()
	{
		assertEquals( 172823, vW.size() );
	}

	@Test
	public void testDoesntContain()
	{
		assertFalse( vW.contains( "develosapiens" ) );
	}

	@Test
	public void testContains()
	{
		assertTrue( vW.contains( "aardvarks" ) );
	}
}
