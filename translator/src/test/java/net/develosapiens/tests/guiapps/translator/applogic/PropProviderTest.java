package net.develosapiens.tests.guiapps.translator.applogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import net.develosapiens.guiapps.translator.applogic.PropProvider;

public class PropProviderTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void testPropProviderDefaultConstructor()
	{
		PropProvider pP = new PropProvider();
		Properties prop = pP.readSettings();
		assertTrue( "hu_HU".equals( prop.getProperty( "gui_language" ) ) );
		assertTrue( "magyar,English".equals( prop.getProperty( "languagesList" ) ) );
	}

	@Test
	public void testPropProviderWithNullParam()
	{
		PropProvider pP = new PropProvider( null );
		Properties prop = pP.readSettings();
		assertTrue( "hu_HU".equals( prop.getProperty( "gui_language" ) ) );
		assertTrue( "magyar,English".equals( prop.getProperty( "languagesList" ) ) );
	}

	@Test
	public void testPropProviderWithEmptyStringParam()
	{
		PropProvider pP = new PropProvider( "" );
		Properties prop = pP.readSettings();
		assertTrue( "hu_HU".equals( prop.getProperty( "gui_language" ) ) );
		assertTrue( "magyar,English".equals( prop.getProperty( "languagesList" ) ) );
	}

	@Test
	public void testPropProviderWithWhiteSpacesParam()
	{
		PropProvider pP = new PropProvider( "     " );
		Properties prop = pP.readSettings();
		assertTrue( "hu_HU".equals( prop.getProperty( "gui_language" ) ) );
		assertTrue( "magyar,English".equals( prop.getProperty( "languagesList" ) ) );
	}

	@Test
	public void testPropProviderWithWrongPathParam()
	{
		PropProvider pP = new PropProvider( "nonExistentDir/conf.properties" );
		Properties prop = pP.readSettings();
		assertTrue( "hu_HU".equals( prop.getProperty( "gui_language" ) ) );
		assertTrue( "magyar,English".equals( prop.getProperty( "languagesList" ) ) );
	}

	@Test
	public void testPropProviderCustomConstructor()
	{
		PropProvider pP = new PropProvider( "conf/testDictionary.properties" );
		Properties prop = pP.readSettings();
		assertTrue( "piresian".equals( prop.getProperty( "gui_language" ) ) );
		assertTrue( "alpha,omega".equals( prop.getProperty( "languagesList" ) ) );
	}

	@Test
	public void testPropProviderWriteDefaultConstructor()
	{
		PropProvider pP = new PropProvider();
		Properties testProp = new Properties();
		testProp.setProperty( "gui_language", "a-113" );
		testProp.setProperty( "languagesList", "mountain,magnolia" );
		pP.writeSettings( testProp );
		List<String> rows = new ArrayList<>();
		try( FileReader fR = new FileReader( "conf/dictionary.properties" ) )
		{
			try( BufferedReader br = new BufferedReader( fR ) )
			{
				String line = br.readLine();

				while( line != null )
				{
					if( !line.startsWith( "#" ) )
					{
						rows.add( line );
					}
					line = br.readLine();
				}
			}
			catch( IOException e )
			{
				e.printStackTrace();
			}
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		assertEquals( "languagesList=mountain,magnolia", rows.get( 0 ) );
		assertEquals( "gui_language=a-113", rows.get( 1 ) );
		testProp = new Properties();
		testProp.setProperty( "gui_language", "hu_HU" );
		testProp.setProperty( "languagesList", "magyar,English" );
		pP.writeSettings( testProp );
	}

	@Test
	public void testPropProviderWriteDefaultConstructorWrongPathJustForCoverage()
	{
		PropProvider pP = new PropProvider( "/root/test.properties" );
		Properties testProp = new Properties();
		testProp.setProperty( "gui_language", "a-113" );
		testProp.setProperty( "languagesList", "mountain,magnolia" );
		pP.writeSettings( testProp );
	}
}
