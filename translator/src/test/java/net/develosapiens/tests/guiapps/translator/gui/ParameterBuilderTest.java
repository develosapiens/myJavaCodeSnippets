package net.develosapiens.tests.guiapps.translator.gui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import net.develosapiens.guiapps.translator.gui.ParameterBuilder;

public class ParameterBuilderTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void testProperty()
	{
		Properties prop = ParameterBuilder.getTheParameterBuilder().getWordStudyAreaProps();
		assertEquals( "120", prop.getProperty( "WSASprefX" ) );
		assertEquals( "50", prop.getProperty( "WSASprefY" ) );
		assertEquals( "5", prop.getProperty( "WSAScolumns" ) );
	}

	@Test
	public void testGetTheParameterBuilderNotNullJustForCoverage()
	{
		assertNotNull( ParameterBuilder.getTheParameterBuilder() );
	}
}
