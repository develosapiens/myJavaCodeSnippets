package net.develosapiens.tests.guiapps.translator.applogic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import net.develosapiens.guiapps.translator.applogic.DictElement;
import net.develosapiens.guiapps.translator.applogic.Link;
import net.develosapiens.guiapps.translator.applogic.SingleDict;
import net.develosapiens.guiapps.translator.applogic.TransTools;

public class TransToolsTest
{

	private TransTools tt;

	@Before
	public void setUp() throws Exception
	{
		tt = new TransTools();
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckStringAsParameterNotNullNotEmptyWithNullString()
	{
		assertFalse( tt.checkStringAsParameterNotNullNotEmpty( null ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckStringAsParameterNotNullNotEmptyWithEmptyString()
	{
		assertFalse( tt.checkStringAsParameterNotNullNotEmpty( "" ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckStringAsParameterNotNullNotEmptyWithWhiteSpacesString()
	{
		assertFalse( tt.checkStringAsParameterNotNullNotEmpty( "    " ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckStringAsParameterNotNullNotEmptyWithString()
	{
		assertTrue( tt.checkStringAsParameterNotNullNotEmpty( "aaa" ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckIntegerListAsParameterNotNullWithNull()
	{
		assertFalse( tt.checkIntegerListAsParameterNotNull( null ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckIntegerListAsParameterNotNull()
	{
		assertTrue( tt.checkIntegerListAsParameterNotNull( new ArrayList<Integer>() ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void isAlreadySetNotEmptyWithEmptyString()
	{
		assertFalse( tt.isAlreadySetNotEmpty( "" ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void isAlreadySetNotEmpty()
	{
		assertTrue( tt.isAlreadySetNotEmpty( "cccc" ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckIntegerAsLinkNumberNotOutOfLimitWithNull()
	{
		assertFalse( tt.checkIntegerAsLinkNumberNotOutOfLimit( null ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckIntegerAsLinkNumberNotOutOfLimitWithUnderLimit()
	{
		assertFalse( tt.checkIntegerAsLinkNumberNotOutOfLimit( 0 ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckIntegerAsLinkNumberNotOutOfLimit()
	{
		assertTrue( tt.checkIntegerAsLinkNumberNotOutOfLimit( 1 ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void checkLocalDateTimeAsDateNotNullWithNull()
	{
		assertFalse( tt.checkLocalDateTimeAsDateNotNull( null ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void checkLocalDateTimeAsDateNotNull()
	{
		assertTrue( tt.checkLocalDateTimeAsDateNotNull( LocalDateTime.of( 2011, 4, 8, 10, 10, 11 ) ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckIntAsStartOrEndIDTrue()
	{
		assertTrue( tt.checkIntAsStartOrEndID( 0 ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckIntAsStartOrEndIDFalse()
	{
		assertFalse( tt.checkIntAsStartOrEndID( -1 ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckIntAsDictElementIDTrue()
	{
		assertTrue( tt.checkIntAsDictElementID( 1 ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckIntAsDictElementIDFalse()
	{
		assertFalse( tt.checkIntAsDictElementID( 0 ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckStringListAsParameterNotNullWithNull()
	{
		assertFalse( tt.checkStringListAsParameterNotNull( null ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckStringListAsParameterNotNull()
	{
		assertTrue( tt.checkStringListAsParameterNotNull( new ArrayList<String>() ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckLinkListAsParameterNotNullWithNull()
	{
		assertFalse( tt.checkLinkListAsParameterNotNull( null ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckLinkListAsParameterNotNull()
	{
		assertTrue( tt.checkLinkListAsParameterNotNull( new ArrayList<Link>() ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckLinkAsParameterNotNullWithNull()
	{
		assertFalse( tt.checkLinkAsParameterNotNull( null ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckLinkAsParameterNotNull()
	{
		assertTrue( tt.checkLinkAsParameterNotNull( new Link() ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckDictElementAsParameterNotNullWithNull()
	{
		assertFalse( tt.checkDictElementAsParameterNotNull( null ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckDictElementAsParameterNotNull()
	{
		assertTrue( tt.checkDictElementAsParameterNotNull( new DictElement() ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckWhetherStringIsInTheListEqualsICaseFalse()
	{
		assertFalse( tt.checkWhetherStringIsInTheListEqualsICase( "omega", getAStringList() ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckWhetherStringIsInTheListEqualsICaseTrue()
	{
		assertTrue( tt.checkWhetherStringIsInTheListEqualsICase( "beta", getAStringList() ) );
	}

	private List<String> getAStringList()
	{
		List<String> list = new ArrayList<String>();
		list.add( "alpha" );
		list.add( "beta" );
		list.add( "gamma" );
		return list;
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckDictElementListAsParameterNotNull()
	{
		assertFalse( tt.checkDictElementListAsParameterNotNull( null ) );
		assertTrue( tt.checkDictElementListAsParameterNotNull( new ArrayList<DictElement>() ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckSingleDictAsParameterNotNull()
	{
		assertFalse( tt.checkSingleDictAsParameterNotNull( null ) );
		assertTrue( tt.checkSingleDictAsParameterNotNull( new SingleDict() ) );
	}

	@SuppressWarnings( "static-access" )
	@Test
	public void testCheckSingleDictListAsParameterNotNull()
	{
		assertFalse( tt.checkSingleDictListAsParameterNotNull( null ) );
		assertTrue( tt.checkSingleDictListAsParameterNotNull( new ArrayList<SingleDict>() ) );
	}

	@Test
	public void testFileCheckerOK()
	{
		assertTrue( TransTools.checkIfFileExists( "conf/log4j2.xml" ) );
	}

	@Test
	public void testFileCheckerNull()
	{
		assertFalse( TransTools.checkIfFileExists( null ) );
	}

	@Test
	public void testFileCheckerEmpty()
	{
		assertFalse( TransTools.checkIfFileExists( "" ) );
	}

	@Test
	public void testFileCheckerWhiteSpaces()
	{
		assertFalse( TransTools.checkIfFileExists( "    " ) );
	}

	@Test
	public void testFileCheckerNonExistent()
	{
		assertFalse( TransTools.checkIfFileExists( "conf/nonExistentFile" ) );
	}

	@Test
	public void testFileCheckerDirectory()
	{
		assertFalse( TransTools.checkIfFileExists( "conf/" ) );
	}

	@Test
	public void testCheckPropertiesAsParamOk()
	{
		Properties p = null;
		assertFalse( TransTools.checkPropertiesAsParameterNotNull( p ) );
	}

	@Test
	public void testCheckPropertiesAsParamNull()
	{
		Properties p = new Properties();
		assertTrue( TransTools.checkPropertiesAsParameterNotNull( p ) );
	}

}
