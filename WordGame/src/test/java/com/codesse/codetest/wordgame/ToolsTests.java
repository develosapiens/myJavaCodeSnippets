package com.codesse.codetest.wordgame;

import org.junit.Test;

public class ToolsTests
{
	@Test( expected = IllegalArgumentException.class )
	public void testNull()
	{
		Tools.checkStringIfEmptyOrNull( null, "Just for testing case because of high coverage." );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testEmpty()
	{
		Tools.checkStringIfEmptyOrNull( "", "Just for testing case because of high coverage." );
	}

	@SuppressWarnings( "unused" )
	@Test
	public void testInstantiationJustForCoverage()
	{
		Tools t = new Tools();
	}
}
