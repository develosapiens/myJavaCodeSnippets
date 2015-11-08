package com.codesse.codetest.wordgame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LetterUsageTest
{
	protected String      letter = "A";
	protected LetterUsage usage;

	@Before
	public void setUp()
	{
		usage = new LetterUsage( letter );
	}

	@Test
	public void testConstruction()
	{
		assertTrue( usage.isLetterNotUsed() );
	}

	@Test
	public void testSwitchLetterUsage()
	{
		assertTrue( usage.isLetterNotUsed() );
		usage.setLetterUsed();
		assertFalse( usage.isLetterNotUsed() );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testLetterUsageWithNullLetter()
	{
		usage = new LetterUsage( null );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testLetterUsageWithEmptyLetter()
	{
		usage = new LetterUsage( "" );
	}

	@Test
	public void testGetLetter()
	{
		assertEquals( "A", usage.getLetter() );
	}
}
