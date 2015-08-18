package net.develosapiens.transl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

public class DictElementTest
{
	private Map<String, SortedSet<Integer>> linksSets;
	private DictElement                     dE;

	@Before
	public void setObjectUp()
	{
		int id = 1001;

		String expression = "develoSapiens";

		String ourLang = "A";
		String theirLang = "B";

		SortedSet<Integer> links = new TreeSet<Integer>();
		links.add( 123 );
		links.add( 456 );
		links.add( 789 );

		linksSets = new HashMap<String, SortedSet<Integer>>();
		linksSets.put( theirLang, links );

		dE = new DictElement();
		dE.setId( id );
		dE.setExpression( expression );
		dE.setMyLanguage( ourLang );
		dE.setLinksToALang( theirLang, links );
	}

	@Test
	public void testConstruction()
	{
		int expectedId = 1001;
		String expectedExpressionString = "develoSapiens";
		String ourLang = "A";
		String theirLang = "B";

		assertTrue( dE.getId() == expectedId );
		assertTrue( dE.getExpression().equals( expectedExpressionString ) );
		assertTrue( dE.getLinksToALang( theirLang ).size() == 3 );
		assertTrue( dE.getMyLanguage().equals( ourLang ) );
		assertTrue( dE.getTheDate() == null );
		assertTrue( dE.getMyTopic() == null );
		assertTrue( dE.getExamples() == null );
	}

	@Test
	public void testSettings()
	{
		String defaultTime = "10:01:02";
		int year = 2014;
		int month = 6;
		int dayOfMonth = 14;

		dE.setTheDate( LocalDateTime.of( LocalDate.of( year, month, dayOfMonth ), LocalTime.parse( defaultTime ) ) );
		assertTrue( dE.getTheDate().getMonth().getValue() == 6 );
		assertTrue( dE.getTheDate().getMonthValue() == 6 );
		assertTrue( dE.getTheDate().getYear() == 2014 );
		assertTrue( dE.getTheDate().getDayOfMonth() == 14 );

		assertTrue( dE.getTheDate().getHour() == 10 );
		assertTrue( dE.getTheDate().getMinute() == 1 );
		assertTrue( dE.getTheDate().getDayOfMonth() == 14 );

		int hours = 10;
		int minutes = 1;
		int seconds = 2;
		LocalTime otherTime = LocalTime.of( hours, minutes, seconds );
		assertEquals( otherTime.toString(), dE.getTheDate().toLocalTime().toString() );

		assertEquals( 1, dE.getTheDate().toLocalTime().getMinute() );
		assertEquals( "1", "" + dE.getTheDate().toLocalTime().getMinute() );

		assertTrue( ( "" + dE.getTheDate().toLocalTime().getMinute() ).equals( "1" ) );

		assertFalse( ( "" + dE.getTheDate().toLocalTime().getMinute() ).equals( "01" ) );

		assertEquals( "0" + dE.getTheDate().toLocalTime().getMinute(),
		    dE.getTheDate().toLocalTime().toString().split( ":" )[1] );

		String topic = "Lesson one";
		dE.setMyTopic( topic );

		StringBuffer expectedToStringOutput = new StringBuffer();
		expectedToStringOutput.append( "Expression: develoSapiens\n" );
		expectedToStringOutput.append( "ID: 1001\n" );
		expectedToStringOutput.append( "Language: A\n" );
		expectedToStringOutput.append( "Links:\n" );
		expectedToStringOutput.append( "B:  123, 456, 789\n" );
		expectedToStringOutput.append( "Examples: - NULL -\n" );
		expectedToStringOutput.append( "Topic: Lesson one\n" );
		expectedToStringOutput.append( "Date and time: 2014. június 14.   10:01:02\n" );
		expectedToStringOutput.append( "Active: true\n" );
		assertEquals( dE.toString(), expectedToStringOutput.toString() );
	}

	@Test
	public void testExamples()
	{
		assertNull( dE.getExamples() );
		String topic = "Travelling";
		dE.setMyTopic( topic );
		SortedSet<String> exs = new TreeSet<>();
		exs.add( "alpha" );
		exs.add( "beta" );
		exs.add( "gamma" );
		exs.add( "omega" );
		dE.setExamples( exs );

		int year = 2014;
		int month = 6;
		int dayOfMonth = 14;
		dE.setTheDate( LocalDateTime.of( LocalDate.of( year, month, dayOfMonth ), LocalTime.of( 10, 1, 2 ) ) );

		StringBuffer expectedToStringOutput = new StringBuffer();
		expectedToStringOutput.append( "Expression: develoSapiens\n" );
		expectedToStringOutput.append( "ID: 1001\n" );
		expectedToStringOutput.append( "Language: A\n" );
		expectedToStringOutput.append( "Links:\n" );
		expectedToStringOutput.append( "B:  123, 456, 789\n" );
		expectedToStringOutput.append( "Examples:\n" );
		expectedToStringOutput.append( "alpha\n" );
		expectedToStringOutput.append( "beta\n" );
		expectedToStringOutput.append( "gamma\n" );
		expectedToStringOutput.append( "omega\n" );
		expectedToStringOutput.append( "Topic: Travelling\n" );
		expectedToStringOutput.append( "Date and time: 2014. június 14.   10:01:02\n" );
		expectedToStringOutput.append( "Active: true\n" );

		assertEquals( expectedToStringOutput.toString(), dE.toString() );
	}

	@Test
	public void testGetLinksToALangNonExistentLangWatchTheLOG()
	{
		dE = new DictElement();
		assertEquals( 0, dE.getLinksToALang( "nonExistentLanguage" ).size() );
	}

	@Test
	public void testGetLinksToALangNoLang()
	{
		dE = new DictElement();
		dE.setId( 99 );
		dE.setExpression( "green" );
		dE.getLinksToALang( "nonExistentLang" );
	}

	@Test
	public void testToStringWithEmptyLinkSet()
	{
		dE = new DictElement();
		dE.setId( 66 );
		dE.setExpression( "route" );
		String defaultTime = "10:01:02";
		int year = 2014;
		int month = 6;
		int dayOfMonth = 14;
		dE.setTheDate( LocalDateTime.of( LocalDate.of( year, month, dayOfMonth ), LocalTime.parse( defaultTime ) ) );

		Set<Integer> testSet = new HashSet<Integer>();
		String aLang = "aLang";
		dE.setLinksToALang( aLang, testSet );
		StringBuffer expectedToStringOutput = new StringBuffer();
		expectedToStringOutput.append( "Expression: route\n" );
		expectedToStringOutput.append( "ID: 66\n" );
		expectedToStringOutput.append( "Language: - NULL -\n" );
		expectedToStringOutput.append( "Links:\n" );
		expectedToStringOutput.append( "aLang:   empty.\n" );
		expectedToStringOutput.append( "Examples: - NULL -\n" );
		expectedToStringOutput.append( "Topic:  - NULL -\n" );
		expectedToStringOutput.append( "Date and time: 2014. június 14.   10:01:02\n" );
		expectedToStringOutput.append( "Active: true\n" );

		assertEquals( expectedToStringOutput.toString(), dE.toString() );
	}

	@Test
	public void testToStringWithTwoLinkSetsToTheSameLang()
	{
		dE = new DictElement();
		dE.setId( 66 );
		dE.setExpression( "route" );

		String defaultTime = "10:01:02";
		int year = 2014;
		int month = Month.JUNE.getValue();
		int dayOfMonth = 14;
		dE.setTheDate( LocalDateTime.of( LocalDate.of( year, month, dayOfMonth ), LocalTime.parse( defaultTime ) ) );

		Set<Integer> testSet = new HashSet<Integer>();
		testSet.add( 111 );
		testSet.add( 112 );
		testSet.add( 113 );
		String bLang = "bLang";
		dE.setLinksToALang( bLang, testSet );
		StringBuffer expectedToStringOutput = new StringBuffer();
		expectedToStringOutput.append( "Expression: route\n" );
		expectedToStringOutput.append( "ID: 66\n" );
		expectedToStringOutput.append( "Language: - NULL -\n" );
		expectedToStringOutput.append( "Links:\n" );
		expectedToStringOutput.append( "bLang:  111, 112, 113\n" );
		expectedToStringOutput.append( "Examples: - NULL -\n" );
		expectedToStringOutput.append( "Topic:  - NULL -\n" );
		expectedToStringOutput.append( "Date and time: 2014. június 14.   10:01:02\n" );
		expectedToStringOutput.append( "Active: true\n" );

		assertEquals( expectedToStringOutput.toString(), dE.toString() );
		Set<Integer> testSet2 = new HashSet<Integer>();
		testSet2.add( 110 );
		testSet2.add( 107 );
		testSet2.add( 155 );
		testSet2.add( 113 );
		dE.setLinksToALang( bLang, testSet2 );
		expectedToStringOutput = new StringBuffer();
		expectedToStringOutput.append( "Expression: route\n" );
		expectedToStringOutput.append( "ID: 66\n" );
		expectedToStringOutput.append( "Language: - NULL -\n" );
		expectedToStringOutput.append( "Links:\n" );
		expectedToStringOutput.append( "bLang:  107, 110, 111, 112, 113, 155\n" );
		expectedToStringOutput.append( "Examples: - NULL -\n" );
		expectedToStringOutput.append( "Topic:  - NULL -\n" );
		expectedToStringOutput.append( "Date and time: 2014. június 14.   10:01:02\n" );
		expectedToStringOutput.append( "Active: true\n" );
		assertEquals( expectedToStringOutput.toString(), dE.toString() );

		bLang = "cLang";
		testSet2 = new HashSet<Integer>();
		testSet2.add( 10 );
		testSet2.add( 1107 );
		testSet2.add( 145 );
		testSet2.add( 103 );
		dE.setLinksToALang( bLang, testSet2 );
		expectedToStringOutput = new StringBuffer();
		expectedToStringOutput.append( "Expression: route\n" );
		expectedToStringOutput.append( "ID: 66\n" );
		expectedToStringOutput.append( "Language: - NULL -\n" );
		expectedToStringOutput.append( "Links:\n" );
		expectedToStringOutput.append( "bLang:  107, 110, 111, 112, 113, 155\n" );
		expectedToStringOutput.append( "cLang:  10, 103, 145, 1107\n" );
		expectedToStringOutput.append( "Examples: - NULL -\n" );
		expectedToStringOutput.append( "Topic:  - NULL -\n" );
		expectedToStringOutput.append( "Date and time: 2014. június 14.   10:01:02\n" );
		expectedToStringOutput.append( "Active: true\n" );
		assertEquals( expectedToStringOutput.toString(), dE.toString() );
	}

	@Test
	public void testTopicGetSet()
	{
		assertNull( dE.getMyTopic() );
		String topic = "Lesson 2.";
		dE.setMyTopic( topic );
		assertEquals( topic, dE.getMyTopic() );
	}

	@Test
	public void testNulls()
	{
		dE = new DictElement();
		dE.setId( 99 );
		dE.setExpression( "green" );
		assertEquals( null, dE.getMyLanguage() );
		assertNull( dE.getMyTopic() );
		assertNull( dE.getExamples() );
		dE.toString();
	}

	@Test( expected = IllegalArgumentException.class )
	public void testGetLinksToALangWithNullLang()
	{
		dE = new DictElement();
		dE.getLinksToALang( null );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testSetLinksToALangWithNullLinks()
	{
		dE = new DictElement();
		dE.setLinksToALang( "aLang", null );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testSetLinksToALangWithNullLang()
	{
		dE = new DictElement();
		Set<Integer> sSet = new HashSet<Integer>();
		dE.setLinksToALang( null, sSet );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testSetLinksToALangWithNullLangAndNullSet()
	{
		dE = new DictElement();
		dE.setLinksToALang( null, null );
	}

	@Test
	public void testSetLang()
	{
		String lng = "D";
		dE.setMyLanguage( lng );
		assertEquals( lng, dE.getMyLanguage() );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testSetLangEmpty()
	{
		dE.setMyLanguage( "" );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testSetLangNull()
	{
		dE.setMyLanguage( null );
	}

	@Test
	public void testCompareCorrect()
	{
		DictElement d1 = new DictElement();
		d1.setId( 110 );
		d1.setExpression( "alpha" );
		DictElement d2 = new DictElement();
		d2.setId( 110 );
		d2.setExpression( "beta" );
		DictElement d3 = new DictElement();
		d3.setId( 110 );
		d3.setExpression( "beta" );
		DictElement d4 = new DictElement();
		d4.setId( 110 );
		d4.setExpression( "omega" );
		assertTrue( 0 > d1.compareTo( d2 ) );
		assertTrue( 0 == d3.compareTo( d2 ) );
		assertTrue( 0 < d4.compareTo( d1 ) );
	}

	@Test
	public void testRemovingALink()
	{
		assertEquals( 3, dE.getLinksToALang( "B" ).size() );
		dE.removeALinkByLanguage( "B", 456 );
		assertEquals( 2, dE.getLinksToALang( "B" ).size() );
		dE.removeALinkByLanguage( "B", 456 );
		assertEquals( 2, dE.getLinksToALang( "B" ).size() );
		dE.removeALinkByLanguage( "B", 11456 );
		assertEquals( 2, dE.getLinksToALang( "B" ).size() );
		dE.removeALinkByLanguage( "C", 11456 );
		assertEquals( 2, dE.getLinksToALang( "B" ).size() );

	}

	@Test
	public void testRemoveALinksByLanguage()
	{
		String lang = "E";
		assertEquals( 3, dE.getLinksToALang( "B" ).size() );
		assertEquals( 0, dE.getLinksToALang( lang ).size() );
		Set<Integer> lnks = new TreeSet<Integer>();
		lnks.add( 4 );
		lnks.add( 41 );
		lnks.add( 42 );
		lnks.add( 43 );
		dE.setLinksToALang( lang, lnks );
		assertEquals( 4, dE.getLinksToALang( lang ).size() );
		dE.removeAllLinskByLanguage( lang );
		assertEquals( 0, dE.getLinksToALang( lang ).size() );
		dE.removeAllLinskByLanguage( "nonExistentLang" );
		assertEquals( 3, dE.getLinksToALang( "B" ).size() );
	}

	@Test
	public void testAddingALinkToALangWhichExists()
	{
		String theirLang1 = "B";
		String nonExistentLang = "CC";
		Set<Integer> sSet = dE.getLinksToALang( theirLang1 );
		Integer intToAdd = 777;
		assertEquals( 3, sSet.size() );
		assertFalse( sSet.contains( intToAdd ) );
		dE.addALinkToALang( theirLang1, intToAdd );
		StringBuffer expectedToStringOutput = new StringBuffer();
		expectedToStringOutput.append( "Expression: develoSapiens\n" );
		expectedToStringOutput.append( "ID: 1001\n" );
		expectedToStringOutput.append( "Language: A\n" );
		expectedToStringOutput.append( "Links:\n" );
		expectedToStringOutput.append( "B:  123, 456, 777, 789\n" );
		expectedToStringOutput.append( "Examples: - NULL -\n" );
		expectedToStringOutput.append( "Topic:  - NULL -\n" );
		expectedToStringOutput.append( "Date and time:  - NULL -\n" );
		expectedToStringOutput.append( "Active: true\n" );

		assertEquals( expectedToStringOutput.toString(), dE.toString() );
		assertEquals( 4, sSet.size() );
		assertTrue( sSet.contains( intToAdd ) );
		dE.addALinkToALang( nonExistentLang, intToAdd );
	}

	@Test
	public void testAddALinkToALangWithNonExistentLanguageWatchTheLOG()
	{
		dE.addALinkToALang( "nonExistentLang", 100 );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testAddALinkToALangWithNullLanguageWatchTheLOG()
	{
		dE.addALinkToALang( null, 100 );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testAddALinkToALangWithNullIntegerWatchTheLOG()
	{
		dE.addALinkToALang( "B", null );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testAddALinkToALangWithNullLangAndNullIntegerWatchTheLOG()
	{
		dE.addALinkToALang( null, null );
	}

	@Test
	public void testRemovingAllLangs()
	{
		dE.removeAllLinskByLanguage( "B" );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testRemoveALinkByLanguageWithEmptyStringWatchTheLOG()
	{
		dE.removeALinkByLanguage( "", 100 );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testRemoveALinkByLanguageWithNullStringWatchTheLOG()
	{
		dE.removeALinkByLanguage( null, 100 );
	}

	@Test
	public void testRemoveALinkByLanguageWithNonExistentIDWatchTheLOG()
	{
		dE.removeALinkByLanguage( "B", 3 );
	}

	@Test
	public void testRemoveALinkByLanguageWithNonExistentLanguageWatchTheLOG()
	{
		dE.removeALinkByLanguage( "nonExistent", 3 );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testRemoveALinkByLanguageWithNullIntegerWatchTheLOG()
	{
		dE.removeALinkByLanguage( "B", null );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testAddALinkToALangWithEmptyLangStringWatchTheLOG()
	{
		dE.addALinkToALang( "", 27 );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testGetLinksToALangWithEmptyStringLanguageWatchTheLOG()
	{
		dE.getLinksToALang( "" );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testSetLinksToALangWithEmptyStringLanguageWatchTheLOG()
	{
		SortedSet<Integer> sSet = new TreeSet<Integer>();
		dE.setLinksToALang( "", sSet );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testSetTopicWithEmptyStringWatchTheLOG()
	{
		dE.setMyTopic( "" );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testSetTopicWithNullStringWatchTheLOG()
	{
		dE.setMyTopic( null );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testSetExpressionWithEmptyStringWatchTheLOG()
	{
		dE.setExpression( "" );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testSetExpressionWithNullStringWatchTheLOG()
	{
		dE.setExpression( null );
	}

	@Test
	public void testActivationTrueAndFalse()
	{
		assertTrue( dE.isActive() );
		dE.setActive( false );
		assertFalse( dE.isActive() );
	}

	@Test
	public void testSetMapLinks()
	{
		assertEquals( 1, dE.getLinks().size() );
		Map<String, TreeSet<Integer>> map = new HashMap<String, TreeSet<Integer>>();
		TreeSet<Integer> sSet = new TreeSet<Integer>();
		sSet.add( 6 );
		sSet.add( 7 );
		map.put( "A", sSet );
		map.put( "B", sSet );
		dE.setLinks( map );
		assertEquals( 2, dE.getLinks().size() );
	}

	@Test
	public void testDictElementsAreEqual()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		dE1.setActive( true );
		dE2.setActive( true );
		dE1.setExpression( "A" );
		dE2.setExpression( "A" );
		dE1.setId( 1 );
		dE2.setId( 1 );
		dE1.setMyLanguage( "B" );
		dE2.setMyLanguage( "B" );
		dE1.setMyTopic( "C" );
		dE2.setMyTopic( "C" );
		dE1.setTheDate( LocalDateTime.of( LocalDate.of( 2011, 11, 11 ), LocalTime.of( 10, 11, 12 ) ) );
		dE2.setTheDate( LocalDateTime.of( LocalDate.of( 2011, 11, 11 ), LocalTime.of( 10, 11, 12 ) ) );
		SortedSet<String> exps1 = new TreeSet<String>();
		exps1.add( "alpha" );
		exps1.add( "tango" );
		exps1.add( "zulu" );
		SortedSet<String> exps2 = new TreeSet<String>();
		exps2.add( "alpha" );
		exps2.add( "tango" );
		exps2.add( "zulu" );
		dE1.setExamples( exps1 );
		dE2.setExamples( exps2 );
		SortedSet<Integer> linksTree11 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree12 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree21 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree22 = new TreeSet<Integer>();
		String lang11 = "l1";
		String lang12 = "l2";
		String lang21 = "l1";
		String lang22 = "l2";
		linksTree11.add( 23 );
		linksTree11.add( 99 );
		linksTree21.add( 23 );
		linksTree21.add( 99 );
		linksTree12.add( 2001 );
		linksTree12.add( 2002 );
		linksTree12.add( 2003 );
		linksTree22.add( 2001 );
		linksTree22.add( 2002 );
		linksTree22.add( 2003 );
		dE1.setLinksToALang( lang11, linksTree11 );
		dE2.setLinksToALang( lang21, linksTree21 );
		dE1.setLinksToALang( lang12, linksTree12 );
		dE2.setLinksToALang( lang22, linksTree22 );
		assertTrue( dE1.equals( dE2 ) );
	}

	@Test
	public void testDictElementsAreEqualButActive()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		dE1.setActive( false );
		dE2.setActive( true );
		dE1.setExpression( "A" );
		dE2.setExpression( "A" );
		dE1.setId( 1 );
		dE2.setId( 1 );
		dE1.setMyLanguage( "B" );
		dE2.setMyLanguage( "B" );
		dE1.setMyTopic( "C" );
		dE2.setMyTopic( "C" );
		dE1.setTheDate( LocalDateTime.of( LocalDate.of( 2011, 11, 11 ), LocalTime.of( 10, 11, 12 ) ) );
		dE2.setTheDate( LocalDateTime.of( LocalDate.of( 2011, 11, 11 ), LocalTime.of( 10, 11, 12 ) ) );
		SortedSet<String> exps1 = new TreeSet<String>();
		exps1.add( "alpha" );
		exps1.add( "tango" );
		exps1.add( "zulu" );
		SortedSet<String> exps2 = new TreeSet<String>();
		exps2.add( "alpha" );
		exps2.add( "tango" );
		exps2.add( "zulu" );
		dE1.setExamples( exps1 );
		dE2.setExamples( exps2 );
		SortedSet<Integer> linksTree11 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree12 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree21 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree22 = new TreeSet<Integer>();
		String lang11 = "l1";
		String lang12 = "l2";
		String lang21 = "l1";
		String lang22 = "l2";
		linksTree11.add( 23 );
		linksTree11.add( 99 );
		linksTree21.add( 23 );
		linksTree21.add( 99 );
		linksTree12.add( 2001 );
		linksTree12.add( 2002 );
		linksTree12.add( 2003 );
		linksTree22.add( 2001 );
		linksTree22.add( 2002 );
		linksTree22.add( 2003 );
		dE1.setLinksToALang( lang11, linksTree11 );
		dE2.setLinksToALang( lang21, linksTree21 );
		dE1.setLinksToALang( lang12, linksTree12 );
		dE2.setLinksToALang( lang22, linksTree22 );
		assertFalse( dE1.equals( dE2 ) );
		dE2.setActive( false );
		assertTrue( dE1.equals( dE2 ) );
	}

	@Test
	public void testDictElementsAreEqualButExpression()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		dE1.setActive( true );
		dE2.setActive( true );
		dE1.setExpression( "AA" );
		dE2.setExpression( "A" );
		dE1.setId( 1 );
		dE2.setId( 1 );
		dE1.setMyLanguage( "B" );
		dE2.setMyLanguage( "B" );
		dE1.setMyTopic( "C" );
		dE2.setMyTopic( "C" );
		dE1.setTheDate( LocalDateTime.of( LocalDate.of( 2011, 11, 11 ), LocalTime.of( 10, 11, 12 ) ) );
		dE2.setTheDate( LocalDateTime.of( LocalDate.of( 2011, 11, 11 ), LocalTime.of( 10, 11, 12 ) ) );
		SortedSet<String> exps1 = new TreeSet<String>();
		exps1.add( "alpha" );
		exps1.add( "tango" );
		exps1.add( "zulu" );
		SortedSet<String> exps2 = new TreeSet<String>();
		exps2.add( "alpha" );
		exps2.add( "tango" );
		exps2.add( "zulu" );
		dE1.setExamples( exps1 );
		dE2.setExamples( exps2 );
		SortedSet<Integer> linksTree11 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree12 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree21 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree22 = new TreeSet<Integer>();
		String lang11 = "l1";
		String lang12 = "l2";
		String lang21 = "l1";
		String lang22 = "l2";
		linksTree11.add( 23 );
		linksTree11.add( 99 );
		linksTree21.add( 23 );
		linksTree21.add( 99 );
		linksTree12.add( 2001 );
		linksTree12.add( 2002 );
		linksTree12.add( 2003 );
		linksTree22.add( 2001 );
		linksTree22.add( 2002 );
		linksTree22.add( 2003 );
		dE1.setLinksToALang( lang11, linksTree11 );
		dE2.setLinksToALang( lang21, linksTree21 );
		dE1.setLinksToALang( lang12, linksTree12 );
		dE2.setLinksToALang( lang22, linksTree22 );
		assertFalse( dE1.equals( dE2 ) );
		dE2.setExpression( "AA" );
		assertTrue( dE1.equals( dE2 ) );
	}

	@Test
	public void testDictElementsAreEqualButID()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		dE1.setActive( true );
		dE2.setActive( true );
		dE1.setExpression( "A" );
		dE2.setExpression( "A" );
		dE1.setId( 10 );
		dE2.setId( 1 );
		dE1.setMyLanguage( "B" );
		dE2.setMyLanguage( "B" );
		dE1.setMyTopic( "C" );
		dE2.setMyTopic( "C" );
		dE1.setTheDate( LocalDateTime.of( LocalDate.of( 2011, 11, 11 ), LocalTime.of( 10, 11, 12 ) ) );
		dE2.setTheDate( LocalDateTime.of( LocalDate.of( 2011, 11, 11 ), LocalTime.of( 10, 11, 12 ) ) );
		SortedSet<String> exps1 = new TreeSet<String>();
		exps1.add( "alpha" );
		exps1.add( "tango" );
		exps1.add( "zulu" );
		SortedSet<String> exps2 = new TreeSet<String>();
		exps2.add( "alpha" );
		exps2.add( "tango" );
		exps2.add( "zulu" );
		dE1.setExamples( exps1 );
		dE2.setExamples( exps2 );
		SortedSet<Integer> linksTree11 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree12 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree21 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree22 = new TreeSet<Integer>();
		String lang11 = "l1";
		String lang12 = "l2";
		String lang21 = "l1";
		String lang22 = "l2";
		linksTree11.add( 23 );
		linksTree11.add( 99 );
		linksTree21.add( 23 );
		linksTree21.add( 99 );
		linksTree12.add( 2001 );
		linksTree12.add( 2002 );
		linksTree12.add( 2003 );
		linksTree22.add( 2001 );
		linksTree22.add( 2002 );
		linksTree22.add( 2003 );
		dE1.setLinksToALang( lang11, linksTree11 );
		dE2.setLinksToALang( lang21, linksTree21 );
		dE1.setLinksToALang( lang12, linksTree12 );
		dE2.setLinksToALang( lang22, linksTree22 );
		assertFalse( dE1.equals( dE2 ) );
		dE2.setId( 10 );
		assertTrue( dE1.equals( dE2 ) );
	}

	@Test
	public void testDictElementsAreEqualButDate()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		dE1.setActive( true );
		dE2.setActive( true );
		dE1.setExpression( "A" );
		dE2.setExpression( "A" );
		dE1.setId( 1 );
		dE2.setId( 1 );
		dE1.setMyLanguage( "B" );
		dE2.setMyLanguage( "B" );
		dE1.setMyTopic( "C" );
		dE2.setMyTopic( "C" );
		dE1.setTheDate( LocalDateTime.of( LocalDate.of( 2011, 11, 11 ), LocalTime.of( 10, 11, 13 ) ) );
		dE2.setTheDate( LocalDateTime.of( LocalDate.of( 2011, 11, 11 ), LocalTime.of( 10, 11, 12 ) ) );
		SortedSet<String> exps1 = new TreeSet<String>();
		exps1.add( "alpha" );
		exps1.add( "tango" );
		exps1.add( "zulu" );
		SortedSet<String> exps2 = new TreeSet<String>();
		exps2.add( "alpha" );
		exps2.add( "tango" );
		exps2.add( "zulu" );
		dE1.setExamples( exps1 );
		dE2.setExamples( exps2 );
		SortedSet<Integer> linksTree11 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree12 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree21 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree22 = new TreeSet<Integer>();
		String lang11 = "l1";
		String lang12 = "l2";
		String lang21 = "l1";
		String lang22 = "l2";
		linksTree11.add( 23 );
		linksTree11.add( 99 );
		linksTree21.add( 23 );
		linksTree21.add( 99 );
		linksTree12.add( 2001 );
		linksTree12.add( 2002 );
		linksTree12.add( 2003 );
		linksTree22.add( 2001 );
		linksTree22.add( 2002 );
		linksTree22.add( 2003 );
		dE1.setLinksToALang( lang11, linksTree11 );
		dE2.setLinksToALang( lang21, linksTree21 );
		dE1.setLinksToALang( lang12, linksTree12 );
		dE2.setLinksToALang( lang22, linksTree22 );
		assertFalse( dE1.equals( dE2 ) );
		dE2.setTheDate( LocalDateTime.of( LocalDate.of( 2011, 11, 11 ), LocalTime.of( 10, 11, 13 ) ) );
		assertTrue( dE1.equals( dE2 ) );
	}

	@Test
	public void testDictElementsAreEqualButExamples()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		dE1.setActive( true );
		dE2.setActive( true );
		dE1.setExpression( "A" );
		dE2.setExpression( "A" );
		dE1.setId( 1 );
		dE2.setId( 1 );
		dE1.setMyLanguage( "B" );
		dE2.setMyLanguage( "B" );
		dE1.setMyTopic( "C" );
		dE2.setMyTopic( "C" );
		dE1.setTheDate( LocalDateTime.of( LocalDate.of( 2011, 11, 11 ), LocalTime.of( 10, 11, 12 ) ) );
		dE2.setTheDate( LocalDateTime.of( LocalDate.of( 2011, 11, 11 ), LocalTime.of( 10, 11, 12 ) ) );
		SortedSet<String> exps1 = new TreeSet<String>();
		exps1.add( "alpha" );
		exps1.add( "tango" );
		exps1.add( "zulu" );
		SortedSet<String> exps2 = new TreeSet<String>();
		exps2.add( "alpha" );
		exps2.add( "tango" );
		dE1.setExamples( exps1 );
		dE2.setExamples( exps2 );
		SortedSet<Integer> linksTree11 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree12 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree21 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree22 = new TreeSet<Integer>();
		String lang11 = "l1";
		String lang12 = "l2";
		String lang21 = "l1";
		String lang22 = "l2";
		linksTree11.add( 23 );
		linksTree11.add( 99 );
		linksTree21.add( 23 );
		linksTree21.add( 99 );
		linksTree12.add( 2001 );
		linksTree12.add( 2002 );
		linksTree12.add( 2003 );
		linksTree22.add( 2001 );
		linksTree22.add( 2002 );
		linksTree22.add( 2003 );
		dE1.setLinksToALang( lang11, linksTree11 );
		dE2.setLinksToALang( lang21, linksTree21 );
		dE1.setLinksToALang( lang12, linksTree12 );
		dE2.setLinksToALang( lang22, linksTree22 );
		assertFalse( dE1.equals( dE2 ) );
		exps2.add( "zulu" );
		assertTrue( dE1.equals( dE2 ) );
	}

	@Test
	public void testDictElementsAreEqualButLanguages()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		dE1.setActive( true );
		dE2.setActive( true );
		dE1.setExpression( "A" );
		dE2.setExpression( "A" );
		dE1.setId( 1 );
		dE2.setId( 1 );
		dE1.setMyLanguage( "BB" );
		dE2.setMyLanguage( "B" );
		dE1.setMyTopic( "C" );
		dE2.setMyTopic( "C" );
		dE1.setTheDate( LocalDateTime.of( LocalDate.of( 2011, 11, 11 ), LocalTime.of( 10, 11, 12 ) ) );
		dE2.setTheDate( LocalDateTime.of( LocalDate.of( 2011, 11, 11 ), LocalTime.of( 10, 11, 12 ) ) );
		SortedSet<String> exps1 = new TreeSet<String>();
		exps1.add( "alpha" );
		exps1.add( "tango" );
		exps1.add( "zulu" );
		SortedSet<String> exps2 = new TreeSet<String>();
		exps2.add( "alpha" );
		exps2.add( "tango" );
		exps2.add( "zulu" );
		dE1.setExamples( exps1 );
		dE2.setExamples( exps2 );
		SortedSet<Integer> linksTree11 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree12 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree21 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree22 = new TreeSet<Integer>();
		String lang11 = "l1";
		String lang12 = "l2";
		String lang21 = "l1";
		String lang22 = "l2";
		linksTree11.add( 23 );
		linksTree11.add( 99 );
		linksTree21.add( 23 );
		linksTree21.add( 99 );
		linksTree12.add( 2001 );
		linksTree12.add( 2002 );
		linksTree12.add( 2003 );
		linksTree22.add( 2001 );
		linksTree22.add( 2002 );
		linksTree22.add( 2003 );
		dE1.setLinksToALang( lang11, linksTree11 );
		dE2.setLinksToALang( lang21, linksTree21 );
		dE1.setLinksToALang( lang12, linksTree12 );
		dE2.setLinksToALang( lang22, linksTree22 );
		assertFalse( dE1.equals( dE2 ) );
		dE2.setMyLanguage( "BB" );
		assertTrue( dE1.equals( dE2 ) );
	}

	@Test
	public void testDictElementsAreEqualButLinks()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		dE1.setActive( true );
		dE2.setActive( true );
		dE1.setExpression( "A" );
		dE2.setExpression( "A" );
		dE1.setId( 1 );
		dE2.setId( 1 );
		dE1.setMyLanguage( "B" );
		dE2.setMyLanguage( "B" );
		dE1.setMyTopic( "C" );
		dE2.setMyTopic( "C" );
		dE1.setTheDate( LocalDateTime.of( LocalDate.of( 2011, 11, 11 ), LocalTime.of( 10, 11, 12 ) ) );
		dE2.setTheDate( LocalDateTime.of( LocalDate.of( 2011, 11, 11 ), LocalTime.of( 10, 11, 12 ) ) );
		SortedSet<String> exps1 = new TreeSet<String>();
		exps1.add( "alpha" );
		exps1.add( "tango" );
		exps1.add( "zulu" );
		SortedSet<String> exps2 = new TreeSet<String>();
		exps2.add( "alpha" );
		exps2.add( "tango" );
		exps2.add( "zulu" );
		dE1.setExamples( exps1 );
		dE2.setExamples( exps2 );
		SortedSet<Integer> linksTree11 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree12 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree21 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree22 = new TreeSet<Integer>();
		String lang11 = "l1";
		String lang12 = "l2";
		String lang21 = "l1";
		String lang22 = "l2";
		linksTree11.add( 23 );
		linksTree11.add( 99 );
		linksTree21.add( 23 );
		linksTree21.add( 99 );
		linksTree12.add( 2001 );
		linksTree12.add( 2002 );
		linksTree12.add( 2003 );
		linksTree22.add( 2001 );
		linksTree22.add( 2002 );
		dE1.setLinksToALang( lang11, linksTree11 );
		dE2.setLinksToALang( lang21, linksTree21 );
		dE1.setLinksToALang( lang12, linksTree12 );
		dE2.setLinksToALang( lang22, linksTree22 );
		assertFalse( dE1.equals( dE2 ) );
		linksTree22.add( 2003 );
		dE2.setLinksToALang( lang22, linksTree22 );
		assertTrue( dE1.equals( dE2 ) );
	}

	@Test
	public void testDictElementsAreEqualButDateWithNull()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		dE1.setActive( true );
		dE2.setActive( true );
		dE1.setExpression( "A" );
		dE2.setExpression( "A" );
		dE1.setId( 1 );
		dE2.setId( 1 );
		dE1.setMyLanguage( "B" );
		dE2.setMyLanguage( "B" );
		dE1.setMyTopic( "C" );
		dE2.setMyTopic( "C" );
		dE1.setTheDate( null );
		dE2.setTheDate( LocalDateTime.of( LocalDate.of( 2011, 11, 11 ), LocalTime.of( 10, 11, 12 ) ) );
		SortedSet<String> exps1 = new TreeSet<String>();
		exps1.add( "alpha" );
		exps1.add( "tango" );
		exps1.add( "zulu" );
		SortedSet<String> exps2 = new TreeSet<String>();
		exps2.add( "alpha" );
		exps2.add( "tango" );
		exps2.add( "zulu" );
		dE1.setExamples( exps1 );
		dE2.setExamples( exps2 );
		SortedSet<Integer> linksTree11 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree12 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree21 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree22 = new TreeSet<Integer>();
		String lang11 = "l1";
		String lang12 = "l2";
		String lang21 = "l1";
		String lang22 = "l2";
		linksTree11.add( 23 );
		linksTree11.add( 99 );
		linksTree21.add( 23 );
		linksTree21.add( 99 );
		linksTree12.add( 2001 );
		linksTree12.add( 2002 );
		linksTree12.add( 2003 );
		linksTree22.add( 2001 );
		linksTree22.add( 2002 );
		linksTree22.add( 2003 );
		dE1.setLinksToALang( lang11, linksTree11 );
		dE2.setLinksToALang( lang21, linksTree21 );
		dE1.setLinksToALang( lang12, linksTree12 );
		dE2.setLinksToALang( lang22, linksTree22 );
		assertFalse( dE1.equals( dE2 ) );
		dE2.setTheDate( null );
		assertTrue( dE1.equals( dE2 ) );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testExamplesWithNull()
	{
		DictElement dE1 = new DictElement();
		dE1.setExamples( null );
	}

	@Test
	public void testDictElementsAreEqualButLanguagesWithNull()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		dE1.setActive( true );
		dE2.setActive( true );
		dE1.setExpression( "A" );
		dE2.setExpression( "A" );
		dE1.setId( 1 );
		dE2.setId( 1 );
		dE1.setMyLanguage( "BB" );
		dE2.setMyLanguage( "B" );
		dE1.setMyTopic( "C" );
		dE2.setMyTopic( "C" );
		dE1.setTheDate( LocalDateTime.of( LocalDate.of( 2011, 11, 11 ), LocalTime.of( 10, 11, 12 ) ) );
		dE2.setTheDate( LocalDateTime.of( LocalDate.of( 2011, 11, 11 ), LocalTime.of( 10, 11, 12 ) ) );
		SortedSet<String> exps1 = new TreeSet<String>();
		exps1.add( "alpha" );
		exps1.add( "tango" );
		exps1.add( "zulu" );
		SortedSet<String> exps2 = new TreeSet<String>();
		exps2.add( "alpha" );
		exps2.add( "tango" );
		exps2.add( "zulu" );
		dE1.setExamples( exps1 );
		dE2.setExamples( exps2 );
		SortedSet<Integer> linksTree11 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree12 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree21 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree22 = new TreeSet<Integer>();
		String lang11 = "l1";
		String lang12 = "l2";
		String lang21 = "l1";
		String lang22 = "l2";
		linksTree11.add( 23 );
		linksTree11.add( 99 );
		linksTree21.add( 23 );
		linksTree21.add( 99 );
		linksTree12.add( 2001 );
		linksTree12.add( 2002 );
		linksTree12.add( 2003 );
		linksTree22.add( 2001 );
		linksTree22.add( 2002 );
		linksTree22.add( 2003 );
		dE1.setLinksToALang( lang11, linksTree11 );
		dE2.setLinksToALang( lang21, linksTree21 );
		dE1.setLinksToALang( lang12, linksTree12 );
		dE2.setLinksToALang( lang22, linksTree22 );
		assertFalse( dE1.equals( dE2 ) );
		dE2.setMyLanguage( "BB" );
		assertTrue( dE1.equals( dE2 ) );
	}

	@Test
	public void testDictElementsAreEqualButLinksWithNull()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		dE1.setActive( true );
		dE2.setActive( true );
		dE1.setExpression( "A" );
		dE2.setExpression( "A" );
		dE1.setId( 1 );
		dE2.setId( 1 );
		dE1.setMyLanguage( "B" );
		dE2.setMyLanguage( "B" );
		dE1.setMyTopic( "C" );
		dE2.setMyTopic( "C" );
		dE1.setTheDate( LocalDateTime.of( LocalDate.of( 2011, 11, 11 ), LocalTime.of( 10, 11, 12 ) ) );
		dE2.setTheDate( LocalDateTime.of( LocalDate.of( 2011, 11, 11 ), LocalTime.of( 10, 11, 12 ) ) );
		SortedSet<String> exps1 = new TreeSet<String>();
		exps1.add( "alpha" );
		exps1.add( "tango" );
		exps1.add( "zulu" );
		SortedSet<String> exps2 = new TreeSet<String>();
		exps2.add( "alpha" );
		exps2.add( "tango" );
		exps2.add( "zulu" );
		dE1.setExamples( exps1 );
		dE2.setExamples( exps2 );
		SortedSet<Integer> linksTree11 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree12 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree21 = new TreeSet<Integer>();
		SortedSet<Integer> linksTree22 = new TreeSet<Integer>();
		String lang11 = "l1";
		String lang12 = "l2";
		String lang21 = "l1";
		String lang22 = "l2";
		linksTree11.add( 23 );
		linksTree11.add( 99 );
		linksTree21.add( 23 );
		linksTree21.add( 99 );
		linksTree12.add( 2001 );
		linksTree12.add( 2002 );
		linksTree12.add( 2003 );
		linksTree22.add( 2001 );
		linksTree22.add( 2002 );
		dE1.setLinksToALang( lang11, linksTree11 );
		dE2.setLinksToALang( lang21, linksTree21 );
		dE1.setLinksToALang( lang12, linksTree12 );
		dE2.setLinksToALang( lang22, linksTree22 );
		assertFalse( dE1.equals( dE2 ) );
		linksTree22.add( 2003 );
		dE2.setLinksToALang( lang22, linksTree22 );
		assertTrue( dE1.equals( dE2 ) );
	}

	@Test
	public void testActiveFalseWithToString()
	{
		DictElement dEl = new DictElement();
		dEl.setActive( false );
		String s = dEl.toString();
		assertTrue( s.contains( "Active: false" ) );
	}

	@Test
	public void testEqualsDENull()
	{
		DictElement dEl = new DictElement();
		assertFalse( dEl.equals( null ) );
	}

	@Test
	public void testEqualsLangOneOfIsNull()
	{
		DictElement dEl = new DictElement();
		DictElement mocked = new DictElement()
		{
			@Override
			public void setMyLanguage( String s )
			{
				myLanguage = null;
			}
		};
		dEl.setMyLanguage( "w" );
		mocked.setMyLanguage( "w" );
		dEl.setExpression( "E" );
		mocked.setExpression( "E" );
		assertFalse( dEl.equals( mocked ) );
		assertFalse( mocked.equals( dEl ) );
	}

	@Test
	public void testEqualsLangBothAreNull()
	{
		DictElement dEl = new DictElement()
		{
			@Override
			public void setMyLanguage( String s )
			{
				myLanguage = null;
			}
		};
		DictElement mocked = new DictElement()
		{
			@Override
			public void setMyLanguage( String s )
			{
				myLanguage = null;
			}
		};
		dEl.setMyLanguage( "w" );
		mocked.setMyLanguage( "w" );
		dEl.setExpression( "E" );
		mocked.setExpression( "E" );
		assertTrue( dEl.equals( mocked ) );
		assertTrue( mocked.equals( dEl ) );
	}

	@Test
	public void testEqualsDateOneOfIsNull()
	{
		DictElement dEl1 = new DictElement();
		DictElement dEl2 = new DictElement();
		dEl1.setMyLanguage( "w" );
		dEl2.setMyLanguage( "w" );
		dEl1.setExpression( "E" );
		dEl2.setExpression( "E" );
		dEl1.setTheDate( LocalDateTime.of( LocalDate.of( 1111, 11, 11 ), LocalTime.of( 11, 11, 11 ) ) );
		assertFalse( dEl1.equals( dEl2 ) );
		assertFalse( dEl2.equals( dEl1 ) );
	}

	@Test
	public void testEqualsDateBothNull()
	{
		DictElement dEl1 = new DictElement();
		DictElement dEl2 = new DictElement();
		dEl1.setMyLanguage( "w" );
		dEl2.setMyLanguage( "w" );
		dEl1.setExpression( "E" );
		dEl2.setExpression( "E" );
		assertTrue( dEl1.equals( dEl2 ) );
		assertTrue( dEl2.equals( dEl1 ) );
	}

	@Test
	public void testEqualsOneExamplesIsNull()
	{
		DictElement dEl1 = new DictElement();
		DictElement dEl2 = new DictElement();
		SortedSet<String> sSet = new TreeSet<String>();
		dEl1.setExamples( sSet );
		dEl1.setMyLanguage( "w" );
		dEl2.setMyLanguage( "w" );
		dEl1.setExpression( "E" );
		dEl2.setExpression( "E" );
		assertFalse( dEl1.equals( dEl2 ) );
		assertFalse( dEl2.equals( dEl1 ) );
	}

	@Test
	public void testEqualsBothExamplesAreNull()
	{
		DictElement dEl1 = new DictElement();
		DictElement dEl2 = new DictElement();
		dEl1.setMyLanguage( "w" );
		dEl2.setMyLanguage( "w" );
		dEl1.setExpression( "E" );
		dEl2.setExpression( "E" );
		assertTrue( dEl1.equals( dEl2 ) );
		assertTrue( dEl2.equals( dEl1 ) );
	}

	@Test
	public void testEqualsExamplesSizesNotEqual()
	{
		DictElement dEl1 = new DictElement();
		DictElement dEl2 = new DictElement();
		SortedSet<String> sSet1 = new TreeSet<String>();
		sSet1.add( "B" );
		sSet1.add( "BB" );
		dEl1.setExamples( sSet1 );
		SortedSet<String> sSet2 = new TreeSet<String>();
		sSet2.add( "B" );
		dEl2.setExamples( sSet2 );
		dEl1.setMyLanguage( "w" );
		dEl2.setMyLanguage( "w" );
		dEl1.setExpression( "E" );
		dEl2.setExpression( "E" );
		assertFalse( dEl1.equals( dEl2 ) );
		assertFalse( dEl2.equals( dEl1 ) );
	}

	@Test
	public void testEqualsExamplesElementsAreNotEqual()
	{
		DictElement dEl1 = new DictElement();
		DictElement dEl2 = new DictElement();
		SortedSet<String> sSet1 = new TreeSet<String>();
		sSet1.add( "B" );
		sSet1.add( "BB" );
		dEl1.setExamples( sSet1 );
		SortedSet<String> sSet2 = new TreeSet<String>();
		sSet2.add( "B" );
		sSet2.add( "BC" );
		dEl2.setExamples( sSet2 );
		dEl1.setMyLanguage( "w" );
		dEl2.setMyLanguage( "w" );
		dEl1.setExpression( "E" );
		dEl2.setExpression( "E" );
		assertFalse( dEl1.equals( dEl2 ) );
		assertFalse( dEl2.equals( dEl1 ) );
	}

	@Test
	public void testEqualsBothLinksAreNull()
	{
		DictElement dEl1 = new DictElement();
		DictElement dEl2 = new DictElement();
		SortedSet<String> sSet1 = new TreeSet<String>();
		SortedSet<String> sSet2 = new TreeSet<String>();
		dEl1.setExamples( sSet1 );
		dEl2.setExamples( sSet2 );
		dEl1.setMyLanguage( "w" );
		dEl2.setMyLanguage( "w" );
		dEl1.setExpression( "E" );
		dEl2.setExpression( "E" );
		assertTrue( dEl1.equals( dEl2 ) );
		assertTrue( dEl2.equals( dEl1 ) );
	}

	@Test
	public void testEqualsOneLinksIsNull()
	{
		DictElement dEl1 = new DictElement();
		DictElement dEl2 = new DictElement();
		SortedSet<String> sSet1 = new TreeSet<String>();
		SortedSet<String> sSet2 = new TreeSet<String>();
		dEl1.setExamples( sSet1 );
		dEl2.setExamples( sSet2 );
		String lang = "A";
		SortedSet<Integer> sSetLinks = new TreeSet<Integer>();
		sSetLinks.add( 113 );
		dEl1.setLinksToALang( lang, sSetLinks );
		dEl1.setMyLanguage( "w" );
		dEl2.setMyLanguage( "w" );
		dEl1.setExpression( "E" );
		dEl2.setExpression( "E" );
		assertFalse( dEl1.equals( dEl2 ) );
		assertFalse( dEl2.equals( dEl1 ) );
	}

	@Test
	public void testEqualsLinks()
	{
		DictElement dEl1 = new DictElement();
		DictElement dEl2 = new DictElement();
		SortedSet<String> sSet1 = new TreeSet<String>();
		SortedSet<String> sSet2 = new TreeSet<String>();
		dEl1.setExamples( sSet1 );
		dEl2.setExamples( sSet2 );
		String lang1 = "A";
		String lang2 = "A";
		SortedSet<Integer> sSetLinks1 = new TreeSet<Integer>();
		SortedSet<Integer> sSetLinks2 = new TreeSet<Integer>();
		sSetLinks1.add( 113 );
		sSetLinks2.add( 113 );
		dEl1.setLinksToALang( lang1, sSetLinks1 );
		dEl2.setLinksToALang( lang2, sSetLinks2 );
		dEl1.setMyLanguage( "w" );
		dEl2.setMyLanguage( "w" );
		dEl1.setExpression( "E" );
		dEl2.setExpression( "E" );
		assertTrue( dEl1.equals( dEl2 ) );
		assertTrue( dEl2.equals( dEl1 ) );
	}

	@Test
	public void testEqualsLinksMapHaveDifferentLanguagesAsKeys()
	{
		DictElement dEl1 = new DictElement();
		DictElement dEl2 = new DictElement();
		SortedSet<String> sSet1 = new TreeSet<String>();
		SortedSet<String> sSet2 = new TreeSet<String>();
		dEl1.setExamples( sSet1 );
		dEl2.setExamples( sSet2 );
		String lang1 = "A";
		String lang2 = "113";
		SortedSet<Integer> sSetLinks1 = new TreeSet<Integer>();
		SortedSet<Integer> sSetLinks2 = new TreeSet<Integer>();
		sSetLinks1.add( 113 );
		sSetLinks2.add( 113 );
		dEl1.setLinksToALang( lang1, sSetLinks1 );
		dEl2.setLinksToALang( lang2, sSetLinks2 );
		dEl1.setMyLanguage( "w" );
		dEl2.setMyLanguage( "w" );
		dEl1.setExpression( "E" );
		dEl2.setExpression( "E" );
		assertFalse( dEl1.equals( dEl2 ) );
		assertFalse( dEl2.equals( dEl1 ) );
	}

	@Test
	public void testEqualsLinksMapHaveDifferentLanguagesAsKeys2ndInstanceHasWhat1stDoesntHave()
	{
		DictElement dEl1 = new DictElement();
		DictElement dEl2 = new DictElement();
		SortedSet<String> sSet1 = new TreeSet<String>();
		SortedSet<String> sSet2 = new TreeSet<String>();
		dEl1.setExamples( sSet1 );
		dEl2.setExamples( sSet2 );
		String lang1 = "A";
		String lang2 = "113";
		SortedSet<Integer> sSetLinks1 = new TreeSet<Integer>();
		SortedSet<Integer> sSetLinks2 = new TreeSet<Integer>();
		sSetLinks1.add( 113 );
		sSetLinks2.add( 113 );
		dEl1.setLinksToALang( lang1, sSetLinks1 );
		dEl2.setLinksToALang( lang1, sSetLinks1 );
		dEl2.setLinksToALang( lang2, sSetLinks2 );
		dEl1.setMyLanguage( "w" );
		dEl2.setMyLanguage( "w" );
		dEl1.setExpression( "E" );
		dEl2.setExpression( "E" );
		assertFalse( dEl1.equals( dEl2 ) );
		assertFalse( dEl2.equals( dEl1 ) );
	}

	@Test
	public void testEqualsLinksMapHaveSameLanguagesAsKeys1stHasWhat2ndDoesnthaveAsLinkNumber()
	{
		DictElement dEl1 = new DictElement();
		DictElement dEl2 = new DictElement();
		SortedSet<String> sSet1 = new TreeSet<String>();
		SortedSet<String> sSet2 = new TreeSet<String>();
		dEl1.setExamples( sSet1 );
		dEl2.setExamples( sSet2 );
		String lang1 = "A";
		String lang2 = "A";
		SortedSet<Integer> sSetLinks1 = new TreeSet<Integer>();
		SortedSet<Integer> sSetLinks2 = new TreeSet<Integer>();
		sSetLinks1.add( 113 );
		sSetLinks1.add( 3 );
		sSetLinks2.add( 113 );
		dEl1.setLinksToALang( lang1, sSetLinks1 );
		dEl2.setLinksToALang( lang2, sSetLinks2 );
		dEl1.setMyLanguage( "w" );
		dEl2.setMyLanguage( "w" );
		dEl1.setExpression( "E" );
		dEl2.setExpression( "E" );
		assertFalse( dEl1.equals( dEl2 ) );
		assertFalse( dEl2.equals( dEl1 ) );
	}

	@Test
	public void testEqualsLinksMapHaveSameLanguagesAsKeys2ndHasWhat1stDoesnthaveAsLinkNumber()
	{
		DictElement dEl1 = new DictElement();
		DictElement dEl2 = new DictElement();
		SortedSet<String> sSet1 = new TreeSet<String>();
		SortedSet<String> sSet2 = new TreeSet<String>();
		dEl1.setExamples( sSet1 );
		dEl2.setExamples( sSet2 );
		String lang1 = "A";
		String lang2 = "A";
		SortedSet<Integer> sSetLinks1 = new TreeSet<Integer>();
		SortedSet<Integer> sSetLinks2 = new TreeSet<Integer>();
		sSetLinks1.add( 113 );
		sSetLinks2.add( 3 );
		sSetLinks2.add( 113 );
		dEl1.setLinksToALang( lang1, sSetLinks1 );
		dEl2.setLinksToALang( lang2, sSetLinks2 );
		dEl1.setMyLanguage( "w" );
		dEl2.setMyLanguage( "w" );
		dEl1.setExpression( "E" );
		dEl2.setExpression( "E" );
		assertFalse( dEl1.equals( dEl2 ) );
		assertFalse( dEl2.equals( dEl1 ) );
	}
}
