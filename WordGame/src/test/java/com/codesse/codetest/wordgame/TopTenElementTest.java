package com.codesse.codetest.wordgame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TopTenElementTest
{
	@SuppressWarnings( "unused" )
	@Test( expected = IllegalArgumentException.class )
	public void testConstructionWithNullName()
	{
		TopTenElement elem = new TopTenElement( null, "word" );
	}

	@SuppressWarnings( "unused" )
	@Test( expected = IllegalArgumentException.class )
	public void testConstructionWithEmptyName()
	{
		TopTenElement elem = new TopTenElement( "", "word" );
	}

	@SuppressWarnings( "unused" )
	@Test( expected = IllegalArgumentException.class )
	public void testConstructionWithNullWord()
	{
		TopTenElement elem = new TopTenElement( "Player", null );
	}

	@SuppressWarnings( "unused" )
	@Test( expected = IllegalArgumentException.class )
	public void testConstructionWithEmptyWord()
	{
		TopTenElement elem = new TopTenElement( "Player", "" );
	}

	@SuppressWarnings( "unused" )
	@Test( expected = IllegalArgumentException.class )
	public void testConstructionWithBotNull()
	{
		TopTenElement elem = new TopTenElement( null, null );
	}

	@SuppressWarnings( "unused" )
	@Test( expected = IllegalArgumentException.class )
	public void testConstructionWithBotEmpty()
	{
		TopTenElement elem = new TopTenElement( "", "" );
	}

	@SuppressWarnings( "unused" )
	@Test( expected = IllegalArgumentException.class )
	public void testConstructionWithNullEmpty()
	{
		TopTenElement elem = new TopTenElement( null, "" );
	}

	@SuppressWarnings( "unused" )
	@Test( expected = IllegalArgumentException.class )
	public void testConstructionWithEmptyNull()
	{
		TopTenElement elem = new TopTenElement( "", null );
	}

	@Test
	public void testGetName()
	{
		TopTenElement elem = new TopTenElement( "Player", "word" );
		assertEquals( "Player", elem.getName() );
	}

	@Test
	public void testGetScore()
	{
		TopTenElement elem = new TopTenElement( "Player", "word" );
		assertEquals( 4, elem.getScore() );
	}

	@Test
	public void testGetWord()
	{
		TopTenElement elem = new TopTenElement( "Player", "word" );
		assertEquals( "word", elem.getWord() );
	}

	@Test
	public void testCompareLess()
	{
		TopTenElement elem1 = new TopTenElement( "Player1", "word" );
		TopTenElement elem2 = new TopTenElement( "Player2", "read2" );
		assertTrue( elem1.compareTo( elem2 ) < 0 );
	}

	@Test
	public void testCompareEquals()
	{
		TopTenElement elem1 = new TopTenElement( "Player1", "word" );
		TopTenElement elem2 = new TopTenElement( "Player2", "read" );
		assertTrue( elem1.compareTo( elem2 ) == 0 );
	}

	@Test
	public void testCompareMore()
	{
		TopTenElement elem1 = new TopTenElement( "Player1", "word1" );
		TopTenElement elem2 = new TopTenElement( "Player2", "read" );
		assertTrue( elem1.compareTo( elem2 ) > 0 );
	}
}
