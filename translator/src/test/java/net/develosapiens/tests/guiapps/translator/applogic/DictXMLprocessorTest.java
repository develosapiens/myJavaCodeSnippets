package net.develosapiens.tests.guiapps.translator.applogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import net.develosapiens.guiapps.translator.applogic.DictElement;
import net.develosapiens.guiapps.translator.applogic.DictXMLprocessor;
import net.develosapiens.guiapps.translator.applogic.Link;
import net.develosapiens.guiapps.translator.applogic.SingleDict;

public class DictXMLprocessorTest
{

	private SingleDict sD;

	@Before
	public void setUp() throws Exception
	{
		sD = new SingleDict();
		sD.getHeaderOfSD().setDictLanguage( "testEnglish" );
		sD.getHeaderOfSD().addingOneTopic( "topic one" );
		sD.getHeaderOfSD().addingOneTopic( "topic two" );
		sD.getHeaderOfSD().addingOneTopic( "topic three" );
		sD.addNewDictElement( "kilo" );
		sD.addNewDictElement( "tango" );
		sD.addNewDictElement( "zulu" );
		sD.addNewDictElement( "bravo" );
		sD.addNewDictElement( "charlie" );
		DictElement tmpE = sD.getElementByExpression( "kilo" );
		List<Link> ls = new ArrayList<>();
		Link l1 = new Link();
		Link l2 = new Link();
		Link l3 = new Link();
		l1.setLanguage( "russ" );
		l2.setLanguage( "piresian" );
		l3.setLanguage( "i-nese" );
		ls.add( l1 );
		ls.add( l2 );
		ls.add( l3 );
		l1.addingALinkNumber( 123 );
		l1.addingALinkNumber( 124 );
		l1.addingALinkNumber( 125 );
		List<Integer> listNums = new ArrayList<>();
		listNums.add( 78 );
		listNums.add( 512 );
		l2.setLinkNumbers( listNums );
		l3.addingALinkNumber( 928 );
		l3.addingALinkNumber( 944 );
		l3.addingALinkNumber( 911 );
		tmpE.setLinks( ls );
		tmpE.setMyTopic( "work" );
		tmpE.addingOneExample( "example 1" );
		tmpE.addingOneExample( "example 2" );
		tmpE.setActive( false );
	}

	@Test
	public void testInstantiateJustForTheCovering()
	{
		DictXMLprocessor dic = null;
		assertNull( dic );
		dic = new DictXMLprocessor();
		assertNotNull( dic );
	}

	@Test
	public void testDoWriteDictionaryToXML()
	{
		DictXMLprocessor.writeDictionaryToXML( sD );
	}

	@Test
	public void testDoWriteDictionaryToXMLToThrowExceptionJustForCoverage()
	{
		sD.getHeaderOfSD().setDictLanguage( "non/existent" );
		DictXMLprocessor.writeDictionaryToXML( sD );
	}

	@Test
	public void testDoReadDictionaryFromXML()
	{
		DictXMLprocessor.writeDictionaryToXML( sD );
		assertEquals( "testEnglish", sD.getHeaderOfSD().getDictLanguage() );
		SingleDict sD2 = DictXMLprocessor.readDictionaryFromXML( sD.getHeaderOfSD().getDictLanguage() );
		assertTrue( sD.equals( sD2 ) );
	}

	@Test
	public void testDoReadDictionaryFromXMLWithErrorJustForCoverage()
	{
		SingleDict sd2 = DictXMLprocessor.readDictionaryFromXML( "nonExistentDir/" + sD.getHeaderOfSD().getDictLanguage() );
		assertFalse( sD.equals( sd2 ) );
	}
}
