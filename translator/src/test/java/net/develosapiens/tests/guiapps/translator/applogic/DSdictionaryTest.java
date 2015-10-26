package net.develosapiens.tests.guiapps.translator.applogic;

import static net.develosapiens.guiapps.translator.applogic.TransTools.checkIfFileExists;
import static net.develosapiens.guiapps.translator.applogic.TransTools.checkStringAsParameterNotNullNotEmpty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import net.develosapiens.guiapps.translator.applogic.DSdictionary;
import net.develosapiens.guiapps.translator.applogic.SingleDict;

public class DSdictionaryTest
{
	private DSdictionary dSDict;

	@Before
	public void setUp() throws Exception
	{
		List<String> langs = new ArrayList<>();
		langs.add( "magyar" );
		langs.add( "English" );
		langs.add( null );
		langs.add( "" );
		langs.add( "English" );
		langs.add( "     " );
		dSDict = new DSdictionary( langs );
	}

	@Test
	public void testConstructionWithExtremeDictListElements()
	{
		assertEquals( 2, dSDict.getSingleDicts().size() );
		for( SingleDict sd : dSDict.getSingleDicts() )
		{
			assertNotNull( sd );
			assertTrue( checkStringAsParameterNotNullNotEmpty( sd.getHeaderOfSD().getDictLanguage() ) );
		}
	}

	@Test
	public void testConstructionWithNullList()
	{
		dSDict = new DSdictionary( null );
		assertNotNull( dSDict );
		assertEquals( 0, dSDict.getSingleDicts().size() );
	}

	@Test
	public void testConstructionWithNonExistentFile()
	{
		String oneLang = "testTest";
		String fName = "conf/dictionaries/" + oneLang + ".dict";
		if( checkIfFileExists( fName ) )
		{
			File file = new File( fName );
			file.delete();
		}
		List<String> langs = new ArrayList<>();
		langs.add( oneLang );
		dSDict = new DSdictionary( langs );
		assertNotNull( dSDict );
		assertEquals( 1, dSDict.getSingleDicts().size() );
		if( checkIfFileExists( fName ) )
		{
			File file = new File( fName );
			file.delete();
		}
		assertFalse( checkIfFileExists( fName ) );
	}

	@Test
	public void testGetSingleDictByLangNull()
	{
		SingleDict sD = dSDict.getSingleDictByLang( null );
		assertNull( sD );
	}

	@Test
	public void testGetSingleDictByLangEmpty()
	{
		SingleDict sD = dSDict.getSingleDictByLang( "" );
		assertNull( sD );
	}

	@Test
	public void testGetSingleDictByLangWhiteSpaces()
	{
		SingleDict sD = dSDict.getSingleDictByLang( "    " );
		assertNull( sD );
	}

	@Test
	public void testGetSingleDictByLang()
	{
		SingleDict sD = dSDict.getSingleDictByLang( "English" );
		assertNotNull( sD );
	}

	@Test
	public void testToString()
	{
		System.out.println( dSDict );
	}
}
