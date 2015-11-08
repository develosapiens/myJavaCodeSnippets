package com.codesse.codetest.wordgame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class WordUsageTest
{
	protected final String initialString = "areallylongword";
	protected WordUsage    wUsage;

	@Before
	public void setUp()
	{
		wUsage = new WordUsage( initialString );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testConstructionWithNull()
	{
		wUsage = new WordUsage( null );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testConstructionWithEmptyWord()
	{
		wUsage = new WordUsage( "" );
	}

	@Test
	public void testSizeOfWordUsage()
	{
		assertEquals( initialString.length(), wUsage.lettersUsages.size() );
	}

	@Test
	public void testConstructionAndLetterUsagesCreation()
	{
		StringBuilder sB = new StringBuilder();
		for( LetterUsage lUsage : wUsage.lettersUsages )
		{
			sB.append( lUsage.getLetter() );
		}
		assertEquals( initialString, sB.toString() );
	}

	@Test
	public void testIfWordValid()
	{
		assertTrue( wUsage.ifWordValid( "all" ) );
		assertTrue( wUsage.ifWordValid( "word" ) );
		assertTrue( wUsage.ifWordValid( "woolly" ) );
		assertTrue( wUsage.ifWordValid( "world" ) );
		assertTrue( wUsage.ifWordValid( "glly" ) );
		assertFalse( wUsage.ifWordValid( "omega" ) );
		assertFalse( wUsage.ifWordValid( "tale" ) );
		assertFalse( wUsage.ifWordValid( "glply" ) );
		assertFalse( wUsage.ifWordValid( "adder" ) );
	}
}
