package net.develosapiens.transl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import net.develosapiens.transl.tools.LoadWordsFromFile;
import net.develosapiens.transl.tools.SaveWordsToFile;
import net.develosapiens.transl.tools.XMLProcessingException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class DictionarySingleTest
{
	private DictionarySingle dSingle;
	private DictElement      dE;
	private Set<Integer>     linksToOtherLang;

	private String           language  = "English";
	private String           language2 = "asdf";
	private String           language3 = "yxcv";
	private String           language4 = "qwer";

	private String           topic1    = "Lesson one";
	private String           topic2    = "Lesson two";
	private String           topic3    = "Lesson three";
	private String           topic4    = "Lesson four";

	private String           example1  = "example 1";
	private String           example2  = "example 2";
	private String           example3  = "example 3";
	private String           example4  = "example 4";
	private String           example5  = "example 5";
	private String           example6  = "example 6";
	private String           example11 = "example 11";
	private String           example12 = "example 12";
	private String           example13 = "example 13";
	private String           example14 = "example 14";
	private String           example15 = "example 15";
	private String           example16 = "example 16";
	private String           example17 = "example 17";
	private List<String>     interestingTopics;

	@Before
	public void doItBeforeTest()
	{
		dSingle = new DictionarySingle();
		dSingle.setDictLanguage( language );
		interestingTopics = new ArrayList<String>();
		interestingTopics.add( topic2 );
		interestingTopics.add( topic3 );
		dSingle.setInterestingTopics( interestingTopics );

		dE = dSingle.newDictElement( "run-down" );
		dE.setTheDate( LocalDateTime.of( LocalDate.of( 2014, 10, 14 ), LocalTime.parse( "10:10:10" ) ) );
		dE.setMyTopic( topic1 );
		SortedSet<String> examples = new TreeSet<>();
		linksToOtherLang = new HashSet<Integer>();
		linksToOtherLang.add( 654 );
		linksToOtherLang.add( 12223 );
		linksToOtherLang.add( 12734 );
		dE.setLinksToALang( language2, linksToOtherLang );
		examples.add( example1 );
		examples.add( example2 );
		examples.add( example11 );
		dE.setExamples( examples );
		dE.setActive( true );

		dE = dSingle.newDictElement( "bedridden" );
		dE.setTheDate( LocalDateTime.of( LocalDate.of( 2014, 11, 14 ), LocalTime.parse( "10:10:10" ) ) );
		dE.setMyTopic( topic2 );
		linksToOtherLang = new HashSet<Integer>();
		linksToOtherLang.add( 121 );
		linksToOtherLang.add( 94 );
		linksToOtherLang.add( 39 );
		dE.setLinksToALang( language2, linksToOtherLang );
		linksToOtherLang = new HashSet<Integer>();
		linksToOtherLang.add( 23 );
		linksToOtherLang.add( 543 );
		linksToOtherLang.add( 5881 );
		dE.setLinksToALang( language3, linksToOtherLang );
		examples = new TreeSet<>();
		examples.add( example3 );
		examples.add( example4 );
		dE.setExamples( examples );
		dE.setActive( true );

		dE = dSingle.newDictElement( "placid" );
		dE.setTheDate( LocalDateTime.of( LocalDate.of( 2014, 12, 14 ), LocalTime.parse( "10:10:10" ) ) );
		dE.setMyTopic( topic2 );
		linksToOtherLang = new HashSet<Integer>();
		linksToOtherLang.add( 1421 );
		linksToOtherLang.add( 9 );
		dE.setLinksToALang( language4, linksToOtherLang );
		linksToOtherLang = new HashSet<Integer>();
		linksToOtherLang.add( 478 );
		linksToOtherLang.add( 289 );
		dE.setLinksToALang( language2, linksToOtherLang );
		examples = new TreeSet<>();
		examples = new TreeSet<>();
		examples = new TreeSet<>();
		examples.add( example5 );
		examples.add( example6 );
		dE.setExamples( examples );
		dE.setActive( false );

		dE = dSingle.newDictElement( "essential" );
		dE.setTheDate( LocalDateTime.of( LocalDate.of( 2014, 12, 14 ), LocalTime.parse( "10:10:10" ) ) );
		dE.setMyTopic( topic2 );
		linksToOtherLang = new HashSet<Integer>();
		linksToOtherLang.add( 1300 );
		linksToOtherLang.add( 3512 );
		dE.setLinksToALang( language2, linksToOtherLang );
		examples = new TreeSet<>();
		examples.add( example12 );
		dE.setExamples( examples );
		dE.setActive( true );

		dE = dSingle.newDictElement( "to admit" );
		dE.setTheDate( LocalDateTime.of( LocalDate.of( 2014, 12, 16 ), LocalTime.parse( "10:10:10" ) ) );
		dE.setMyTopic( topic3 );
		linksToOtherLang = new HashSet<Integer>();
		linksToOtherLang.add( 1300 );
		linksToOtherLang.add( 3512 );
		dE.setLinksToALang( language2, linksToOtherLang );
		examples = new TreeSet<>();
		dE.setExamples( examples );
		dE.setActive( true );

		dE = dSingle.newDictElement( "congested" );
		dE.setTheDate( LocalDateTime.of( LocalDate.of( 2014, 12, 17 ), LocalTime.parse( "10:10:10" ) ) );
		dE.setMyTopic( topic3 );
		linksToOtherLang = new HashSet<Integer>();
		linksToOtherLang.add( 12228 );
		linksToOtherLang.add( 3500 );
		dE.setLinksToALang( language2, linksToOtherLang );
		examples = new TreeSet<>();
		examples.add( example12 );
		dE.setExamples( examples );
		dE.setActive( true );

		dE = dSingle.newDictElement( "to abet" );
		dE.setTheDate( LocalDateTime.of( LocalDate.of( 2015, 2, 2 ), LocalTime.parse( "10:10:10" ) ) );
		dE.setMyTopic( topic3 );
		linksToOtherLang = new HashSet<Integer>();
		linksToOtherLang.add( 13 );
		linksToOtherLang.add( 35 );
		dE.setLinksToALang( language2, linksToOtherLang );
		examples = new TreeSet<>();
		examples.add( example13 );
		examples.add( example14 );
		examples.add( example15 );
		dE.setExamples( examples );
		dE.setActive( true );

		dE = dSingle.newDictElement( "claimant" );
		dE.setTheDate( LocalDateTime.of( LocalDate.of( 2015, 2, 2 ), LocalTime.parse( "10:10:10" ) ) );
		dE.setMyTopic( topic4 );
		linksToOtherLang = new HashSet<Integer>();
		linksToOtherLang.add( 123 );
		linksToOtherLang.add( 345 );
		linksToOtherLang.add( 45 );
		dE.setLinksToALang( language2, linksToOtherLang );
		linksToOtherLang = new HashSet<Integer>();
		linksToOtherLang.add( 998 );
		linksToOtherLang.add( 3521 );
		dE.setLinksToALang( language4, linksToOtherLang );
		examples = new TreeSet<>();
		examples.add( example16 );
		examples.add( example17 );
		dE.setExamples( examples );
		dE.setActive( true );
	}

	@Ignore
	@Test
	public void showTestObjectsUsingToString()
	{
		for( DictElement e : dSingle.getDictElementsInSortedListByExpression() )
		{
			System.out.println( e + "\n\n" );
		}
	}

	@Test( expected = IllegalArgumentException.class )
	public void testSettingDictLanguageEmptyLang()
	{
		DictionarySingle dS = new DictionarySingle();
		dS.setDictLanguage( "" );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testSettingDictLanguageNull()
	{
		DictionarySingle dS = new DictionarySingle();
		dS.setDictLanguage( null );
	}

	@Test
	public void testSettingDictLanguage()
	{
		DictionarySingle dd = new DictionarySingle();
		dd.setDictLanguage( "develoSapiens" );
		assertEquals( "develoSapiens", dd.getDictLanguage() );
	}

	@Test
	public void testExpressionsSorted()
	{
		StringBuffer sB = new StringBuffer();
		for( DictElement e : dSingle.getDictElementsInSortedListByExpression() )
		{
			sB.append( e.getExpression() + " " );
		}
		assertEquals( "bedridden claimant congested essential placid run-down to abet to admit ", sB.toString() );
	}

	@Test
	public void testIdsSorted()
	{
		StringBuffer sB = new StringBuffer();
		for( DictElement e : dSingle.getDictElementsInSortedListById() )
		{
			sB.append( e.getExpression() + " " );
		}
		assertEquals( "run-down bedridden placid essential to admit congested to abet claimant ", sB.toString() );
	}

	@Test
	public void testContainsElementsAGivenId()
	{
		assertFalse( dSingle.isIdUsedInElements( 99 ) );
		assertTrue( dSingle.isIdUsedInElements( 1 ) );
		assertFalse( dSingle.isIdUsedInElements( 9 ) );
		assertTrue( dSingle.isIdUsedInElements( 2 ) );
		assertTrue( dSingle.isIdUsedInElements( 3 ) );
		assertTrue( dSingle.isIdUsedInElements( 4 ) );
		assertTrue( dSingle.isIdUsedInElements( 5 ) );
		assertTrue( dSingle.isIdUsedInElements( 6 ) );
		assertTrue( dSingle.isIdUsedInElements( 7 ) );
		assertTrue( dSingle.isIdUsedInElements( 8 ) );
	}

	@Test
	public void testContainsElementsAGivenExpressionStrict()
	{
		assertFalse( dSingle.isExpressionUsedInElementsStrict( "Lake Placid" ) );
		assertFalse( dSingle.isExpressionUsedInElementsStrict( "pla" ) );
		assertTrue( dSingle.isExpressionUsedInElementsStrict( "placid" ) );
	}

	@Test
	public void testContainsElementsAGivenExpression()
	{
		assertTrue( dSingle.isExpressionUsedInElements( "Lake Placid" ) );
		assertFalse( dSingle.isExpressionUsedInElements( "Lake Placi" ) );
		assertTrue( dSingle.isExpressionUsedInElements( "placid" ) );
		assertTrue( dSingle.isExpressionUsedInElements( "pla" ) );
	}

	@Test
	public void testSelectByTimeInterval1()
	{
		dSingle.setStartTime( LocalDateTime.of( LocalDate.of( 2014, 10, 20 ), LocalTime.parse( "10:10:10" ) ) );
		dSingle.setEndTime( LocalDateTime.of( LocalDate.of( 2015, 1, 20 ), LocalTime.parse( "10:10:10" ) ) );
		List<Integer> e = new ArrayList<Integer>();
		for( DictElement d : dSingle.selectByTime() )
		{
			e.add( d.getId() );
		}
		assertTrue( e.contains( 2 ) );
		assertTrue( e.contains( 3 ) );
		assertTrue( e.contains( 4 ) );
		assertTrue( e.contains( 5 ) );
		assertTrue( e.contains( 6 ) );

		assertFalse( e.contains( 7 ) );
		assertFalse( e.contains( 1 ) );
		assertFalse( e.contains( 8 ) );
		assertEquals( 5, dSingle.selectByTime().size() );
	}

	@Test
	public void testSelectByTimeInterval2()
	{
		dSingle.setStartTime( LocalDateTime.of( LocalDate.of( 2014, 12, 16 ), LocalTime.parse( "10:10:10" ) ) );
		dSingle.setEndTime( LocalDateTime.of( LocalDate.of( 2014, 12, 17 ), LocalTime.parse( "10:10:10" ) ) );
		assertEquals( 2, dSingle.selectByTime().size() );
		dSingle.setStartTime( LocalDateTime.of( LocalDate.of( 2014, 12, 16 ), LocalTime.parse( "10:10:11" ) ) );
		assertEquals( 1, dSingle.selectByTime().size() );
		dSingle.setStartTime( LocalDateTime.of( LocalDate.of( 2014, 12, 16 ), LocalTime.parse( "10:10:10" ) ) );
		dSingle.setEndTime( LocalDateTime.of( LocalDate.of( 2014, 12, 17 ), LocalTime.parse( "10:10:09" ) ) );
		assertEquals( 1, dSingle.selectByTime().size() );
	}

	@Test
	public void testSelectByTimeInterval3()
	{
		dSingle.setStartTime( LocalDateTime.of( LocalDate.of( 2014, 12, 16 ), LocalTime.parse( "10:10:10" ) ) );
		dSingle.setEndTime( LocalDateTime.of( LocalDate.of( 2014, 12, 17 ), LocalTime.parse( "10:10:09" ) ) );
		assertEquals( 1, dSingle.selectByTime().size() );
	}

	@Test
	public void testSelectByTimeWhereEndTimeIsBeforeStartTimeWatchTheLOG()
	{
		dSingle.setStartTime( LocalDateTime.of( LocalDate.of( 2014, 12, 16 ), LocalTime.parse( "10:10:10" ) ) );
		dSingle.setEndTime( LocalDateTime.of( LocalDate.of( 2014, 12, 12 ), LocalTime.parse( "10:10:09" ) ) );
		assertEquals( 0, dSingle.selectByTime().size() );
	}

	@Test
	public void testSelectByTimeWhereEndTimeIsTheSameAsStartTimeWatchTheLOG()
	{
		dSingle.setStartTime( LocalDateTime.of( LocalDate.of( 2014, 12, 16 ), LocalTime.parse( "10:10:10" ) ) );
		dSingle.setEndTime( dSingle.getStartTime() );
		assertEquals( 0, dSingle.selectByTime().size() );
		dSingle.setEndTime( LocalDateTime.of( LocalDate.of( 2014, 10, 10 ), LocalTime.parse( "12:12:12" ) ) );
		dSingle.setStartTime( dSingle.getEndTime() );
		assertEquals( 0, dSingle.selectByTime().size() );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testDeleteDictElementWithNullParam()
	{
		assertEquals( 8, dSingle.getElements().size() );
		dSingle.deleteDictElement( null );
		assertEquals( 8, dSingle.getElements().size() );
	}

	@Test
	public void testDeleteDictElementByNonExistentId()
	{
		assertEquals( 8, dSingle.getElements().size() );
		dSingle.deleteDictElement( 8989 );
		assertEquals( 8, dSingle.getElements().size() );
	}

	@Test
	public void testDeleteDictElementById()
	{
		assertEquals( 8, dSingle.getElements().size() );
		dSingle.deleteDictElement( 4 );
		assertEquals( 7, dSingle.getElements().size() );
		dSingle.deleteDictElement( 6 );
		assertEquals( 6, dSingle.getElements().size() );
	}

	@Test
	public void testAddingElementByExpression1()
	{
		DictionarySingle dd = new DictionarySingle();
		dd.setDictLanguage( "T" );
		dd.newDictElement( "aaaa" );
		assertEquals( 1, dd.getElements().size() );
		DictElement anElement = dd.getElementById( 1 );
		assertEquals( "aaaa", anElement.getExpression() );
		dd.newDictElement( "aaaaa" );
		assertEquals( 2, dd.getElements().size() );
		dd.newDictElement( "aaa" );
		assertEquals( 3, dd.getElements().size() );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testAddingElementByExpressionWithNullExpression()
	{
		DictionarySingle dd = new DictionarySingle();
		dd.setDictLanguage( "T" );
		dd.newDictElement( null );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testAddingElementByExpressionWithEmptyExpression()
	{
		DictionarySingle dd = new DictionarySingle();
		dd.setDictLanguage( "T" );
		dd.newDictElement( "" );
	}

	@Test
	public void testAddingElementByExpressionWhichWeHaveItAlready()
	{
		DictionarySingle dd = new DictionarySingle();
		dd.setDictLanguage( "T" );
		dd.newDictElement( "aaaa" );
		assertEquals( 1, dd.getElements().size() );
		dd.newDictElement( "aaaa" );
		assertEquals( 1, dd.getElements().size() );
	}

	@Test
	public void testSelectTopicsManuallyWithoutSelectMethod()
	{
		List<DictElement> resultDE = new ArrayList<DictElement>();
		dSingle.setInterestingTopics( null );
		List<String> t = new ArrayList<String>();
		t.add( "Lesson two" );
		t.add( "Lesson four" );
		dSingle.setInterestingTopics( t );
		for( String top : dSingle.getInterestingTopics() )
		{
			for( DictElement d : dSingle.getElements() )
			{
				if( d.getMyTopic().equals( top ) )
				{
					resultDE.add( d );
					assertTrue( d.getId() == 8 || d.getId() == 4 || d.getId() == 3 || d.getId() == 2 );
					assertTrue( d.getId() != 1 && d.getId() != 5 && d.getId() != 6 && d.getId() != 7 );
				}
			}
		}
		assertEquals( 4, resultDE.size() );
	}

	@Test
	public void testSelectTopicsWithSelectMethod()
	{
		List<String> t = new ArrayList<String>();
		t.add( "Lesson one" );
		t.add( "Lesson four" );
		dSingle.setInterestingTopics( t );
		for( DictElement d : dSingle.selectByTopics() )
		{
			assertTrue( d.getId() == 1 || d.getId() == 8 );
			assertTrue( d.getId() != 2 && d.getId() != 3 && d.getId() != 4 && d.getId() != 5 && d.getId() != 6
			    && d.getId() != 7 );
		}
	}

	@Test
	public void testSelectTopicsWithNull()
	{
		dSingle.setInterestingTopics( null );
		assertEquals( 0, dSingle.selectByTopics().size() );
	}

	@Test
	public void testselectByExpression()
	{
		assertEquals( 1, dSingle.selectByExpression( "placid" ).size() );
		assertEquals( 1, dSingle.selectByExpression( "pl" ).size() );
		assertEquals( 0, dSingle.selectByExpression( "placide" ).size() );
		assertEquals( 0, dSingle.selectByExpression( null ).size() );
		assertEquals( 0, dSingle.selectByExpression( "" ).size() );
	}

	@Test
	public void testselectByExpressionStrict()
	{
		assertEquals( 1, dSingle.selectByExpressionStrict( "placid" ).size() );
		assertEquals( 0, dSingle.selectByExpressionStrict( "pl" ).size() );
		assertEquals( 0, dSingle.selectByExpressionStrict( "placide" ).size() );
		assertEquals( 0, dSingle.selectByExpressionStrict( null ).size() );
		assertEquals( 0, dSingle.selectByExpressionStrict( "" ).size() );
	}

	@Test
	public void testSelectByIds()
	{
		assertEquals( 0, dSingle.getStartId() );
		assertEquals( 0, dSingle.getEndId() );
		assertEquals( 8, dSingle.selectByIds().size() );
		dSingle.setStartId( 3 );
		dSingle.setEndId( 7 );
		assertEquals( 5, dSingle.selectByIds().size() );
	}

	@Test
	public void testSetStartIdAfterEndIdWatchTheLOG()
	{
		dSingle.setStartId( 6 );
		dSingle.setEndId( 3 );
		assertEquals( 0, dSingle.selectByIds().size() );
	}

	@Test
	public void testSetStartZero()
	{
		dSingle.setEndId( 3 );
		assertEquals( 3, dSingle.selectByIds().size() );
		dSingle.setEndId( 5 );
		assertEquals( 5, dSingle.selectByIds().size() );
	}

	@Test
	public void testSetEndZero()
	{
		dSingle.setStartId( 6 );
		assertEquals( 3, dSingle.selectByIds().size() );
		dSingle.setStartId( 4 );
		assertEquals( 5, dSingle.selectByIds().size() );
	}

	@Test
	public void testXMLoutAndIn()
	{
		DictionarySingle diSi;
		SaveWordsToFile saver = new SaveWordsToFile();
		LoadWordsFromFile loader = new LoadWordsFromFile();
		try
		{
			saver.writeOut( dSingle );
			diSi = loader.doIt( "English" );
			assertTrue( diSi.equals( dSingle ) );
		}
		catch( XMLProcessingException e )
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testEqualsDictSings()
	{
		DictionarySingle diSi;
		SaveWordsToFile saver = new SaveWordsToFile();
		LoadWordsFromFile loader = new LoadWordsFromFile();
		try
		{
			saver.writeOut( dSingle );
			diSi = loader.doIt( "English" );
			assertTrue( diSi.equals( dSingle ) );
		}
		catch( XMLProcessingException e )
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testEqualsDictSingsStartDateNull()
	{
		DictionarySingle diSi;
		SaveWordsToFile saver = new SaveWordsToFile();
		LoadWordsFromFile loader = new LoadWordsFromFile();
		try
		{
			saver.writeOut( dSingle );
			diSi = loader.doIt( "English" );
			diSi.setStartTime( null );
			assertFalse( diSi.equals( dSingle ) );
			dSingle.setStartTime( null );
			assertTrue( diSi.equals( dSingle ) );
		}
		catch( XMLProcessingException e )
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testEqualsDictSingsEndDateNull()
	{
		DictionarySingle diSi;
		SaveWordsToFile saver = new SaveWordsToFile();
		LoadWordsFromFile loader = new LoadWordsFromFile();
		try
		{
			saver.writeOut( dSingle );
			diSi = loader.doIt( "English" );
			diSi.setEndTime( null );
			assertFalse( diSi.equals( dSingle ) );
			dSingle.setEndTime( null );
			assertTrue( diSi.equals( dSingle ) );
		}
		catch( XMLProcessingException e )
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testEqualsDictSingsInterestingTopicsNull()
	{
		DictionarySingle diSi;
		SaveWordsToFile saver = new SaveWordsToFile();
		LoadWordsFromFile loader = new LoadWordsFromFile();
		try
		{
			saver.writeOut( dSingle );
			diSi = loader.doIt( "English" );
			diSi.setInterestingTopics( null );
			assertFalse( diSi.equals( dSingle ) );
			dSingle.setInterestingTopics( null );
			assertTrue( diSi.equals( dSingle ) );
			assertTrue( dSingle.equals( diSi ) );
		}
		catch( XMLProcessingException e )
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testEqualsDictSinglesButLanguageChangeWontWorkAfterElementCreated()
	{
		DictionarySingle dSingle1 = new DictionarySingle();
		DictionarySingle dSingle2 = new DictionarySingle();
		dSingle1.setDictLanguage( "languag" );
		dSingle2.setDictLanguage( "language" );
		List<String> iTopics1 = new ArrayList<String>();
		List<String> iTopics2 = new ArrayList<String>();
		iTopics1.add( "T" );
		iTopics2.add( "T" );
		iTopics1.add( "TT" );
		iTopics2.add( "TT" );
		iTopics1.add( "TTT" );
		iTopics2.add( "TTT" );
		dSingle1.setInterestingTopics( iTopics1 );
		dSingle2.setInterestingTopics( iTopics2 );
		dSingle1.setStartId( 10 );
		dSingle2.setStartId( 10 );
		dSingle1.setEndId( 22 );
		dSingle2.setEndId( 22 );
		dSingle1.setStartTime( LocalDateTime.of( LocalDate.of( 2010, 10, 11 ), LocalTime.of( 22, 22, 22 ) ) );
		dSingle2.setStartTime( LocalDateTime.of( LocalDate.of( 2010, 10, 11 ), LocalTime.of( 22, 22, 22 ) ) );
		dSingle1.setEndTime( LocalDateTime.of( LocalDate.of( 2011, 10, 11 ), LocalTime.of( 22, 22, 22 ) ) );
		dSingle2.setEndTime( LocalDateTime.of( LocalDate.of( 2011, 10, 11 ), LocalTime.of( 22, 22, 22 ) ) );
		assertFalse( dSingle1.equals( dSingle2 ) );
		assertFalse( dSingle2.equals( dSingle1 ) );
		dSingle1.newDictElement( "NN" );
		dSingle2.newDictElement( "NN" );
		dSingle1.setDictLanguage( "language" );
		assertFalse( dSingle1.equals( dSingle2 ) );
	}

	@Test
	public void testToStringDefault()
	{
		StringBuffer expectedToStringOutput = new StringBuffer();
		expectedToStringOutput.append( "DictLanguage: - NULL -\n" );
		expectedToStringOutput.append( "theID: 0\n" );
		expectedToStringOutput.append( "startId: 0\n" );
		expectedToStringOutput.append( "endId: 0\n" );
		expectedToStringOutput.append( "startTime: 2010-12-28T10:10:10\n" );
		expectedToStringOutput.append( "endTime: 2010-12-28T11:11:11\n" );
		expectedToStringOutput.append( "interestingTopics:\n" );
		expectedToStringOutput.append( " - NULL -\n\n" );
		expectedToStringOutput.append( "DictElements:\n" );
		expectedToStringOutput.append( " - NULL -\n" );

		DictionarySingle dSingle1 = new DictionarySingle();
		dSingle1.setEndTime( LocalDateTime.of( LocalDate.of( 2010, 12, 28 ), LocalTime.of( 11, 11, 11 ) ) );
		String toS = dSingle1.toString();
		assertEquals( expectedToStringOutput.toString(), toS );
	}

	@Test
	public void testToStringDefaultAndInterestingTopics()
	{
		StringBuffer expectedToStringOutput = new StringBuffer();
		expectedToStringOutput.append( "DictLanguage: - NULL -\n" );
		expectedToStringOutput.append( "theID: 0\n" );
		expectedToStringOutput.append( "startId: 0\n" );
		expectedToStringOutput.append( "endId: 0\n" );
		expectedToStringOutput.append( "startTime: 2010-12-28T10:10:10\n" );
		expectedToStringOutput.append( "endTime: 2010-12-28T11:11:11\n" );
		expectedToStringOutput.append( "interestingTopics:\n" );
		expectedToStringOutput.append( "develoSapiens\n" );
		expectedToStringOutput.append( "A 113\n\n" );
		expectedToStringOutput.append( "DictElements:\n" );
		expectedToStringOutput.append( " - NULL -\n" );

		DictionarySingle dSingle1 = new DictionarySingle();
		dSingle1.setEndTime( LocalDateTime.of( LocalDate.of( 2010, 12, 28 ), LocalTime.of( 11, 11, 11 ) ) );
		List<String> interestingTopicsList = new ArrayList<String>();
		interestingTopicsList.add( "develoSapiens" );
		interestingTopicsList.add( "A 113" );
		dSingle1.setInterestingTopics( interestingTopicsList );
		String toS = dSingle1.toString();
		assertEquals( expectedToStringOutput.toString(), toS );
	}

	@Test
	public void testToStringDefaultAndInterestingTopicsAndDictLanguage()
	{
		StringBuffer expectedToStringOutput = new StringBuffer();
		expectedToStringOutput.append( "DictLanguage: lang\n" );
		expectedToStringOutput.append( "theID: 0\n" );
		expectedToStringOutput.append( "startId: 0\n" );
		expectedToStringOutput.append( "endId: 0\n" );
		expectedToStringOutput.append( "startTime: 2010-12-28T10:10:10\n" );
		expectedToStringOutput.append( "endTime: 2010-12-28T11:11:11\n" );
		expectedToStringOutput.append( "interestingTopics:\n" );
		expectedToStringOutput.append( "develoSapiens\n" );
		expectedToStringOutput.append( "A 113\n\n" );
		expectedToStringOutput.append( "DictElements:\n" );
		expectedToStringOutput.append( " - NULL -\n" );

		DictionarySingle dSingle1 = new DictionarySingle();
		dSingle1.setEndTime( LocalDateTime.of( LocalDate.of( 2010, 12, 28 ), LocalTime.of( 11, 11, 11 ) ) );
		List<String> interestingTopicsList = new ArrayList<String>();
		interestingTopicsList.add( "develoSapiens" );
		interestingTopicsList.add( "A 113" );
		dSingle1.setInterestingTopics( interestingTopicsList );
		dSingle1.setDictLanguage( "lang" );
		String toS = dSingle1.toString();
		assertEquals( expectedToStringOutput.toString(), toS );
	}

	@Test
	public void testToStringDefaultAndInterestingTopicsAndDictLanguageButDatesNull()
	{
		StringBuffer expectedToStringOutput = new StringBuffer();
		expectedToStringOutput.append( "DictLanguage: lang\n" );
		expectedToStringOutput.append( "theID: 0\n" );
		expectedToStringOutput.append( "startId: 0\n" );
		expectedToStringOutput.append( "endId: 0\n" );
		expectedToStringOutput.append( "startTime:  - NULL -\n" );
		expectedToStringOutput.append( "endTime:  - NULL -\n" );
		expectedToStringOutput.append( "interestingTopics:\n" );
		expectedToStringOutput.append( "develoSapiens\n" );
		expectedToStringOutput.append( "A 113\n\n" );
		expectedToStringOutput.append( "DictElements:\n" );
		expectedToStringOutput.append( " - NULL -\n" );

		DictionarySingle dSingle1 = new DictionarySingle();
		dSingle1.setStartTime( null );
		dSingle1.setEndTime( null );
		List<String> interestingTopicsList = new ArrayList<String>();
		interestingTopicsList.add( "develoSapiens" );
		interestingTopicsList.add( "A 113" );
		dSingle1.setInterestingTopics( interestingTopicsList );
		dSingle1.setDictLanguage( "lang" );
		String toS = dSingle1.toString();
		assertEquals( expectedToStringOutput.toString(), toS );
	}

	@Test
	public void testToStringDefaultAndInterestingTopicsAndDictLanguageAndDictElement()
	{
		StringBuffer expectedToStringOutput = new StringBuffer();
		expectedToStringOutput.append( "DictLanguage: lang\n" );
		expectedToStringOutput.append( "theID: 1\n" );
		expectedToStringOutput.append( "startId: 0\n" );
		expectedToStringOutput.append( "endId: 0\n" );
		expectedToStringOutput.append( "startTime:  - NULL -\n" );
		expectedToStringOutput.append( "endTime:  - NULL -\n" );
		expectedToStringOutput.append( "interestingTopics:\n" );
		expectedToStringOutput.append( "develoSapiens\n" );
		expectedToStringOutput.append( "A 113\n\n" );
		expectedToStringOutput.append( "DictElements:\n" );
		expectedToStringOutput.append( "Expression: A\n" );

		expectedToStringOutput.append( "ID: 1\n" );
		expectedToStringOutput.append( "Language: lang\n" );
		expectedToStringOutput.append( "Links: - NULL -\n" );
		expectedToStringOutput.append( "Examples: - NULL -\n" );
		expectedToStringOutput.append( "Topic:  - NULL -\n" );
		expectedToStringOutput.append( "Date and time:  - NULL -\n" );
		expectedToStringOutput.append( "Active: true\n" );
		expectedToStringOutput.append( "--\n" );
		DictionarySingle dSingle1 = new DictionarySingle();
		dSingle1.setStartTime( null );
		dSingle1.setEndTime( null );
		List<String> interestingTopicsList = new ArrayList<String>();
		interestingTopicsList.add( "develoSapiens" );
		interestingTopicsList.add( "A 113" );
		dSingle1.setInterestingTopics( interestingTopicsList );
		dSingle1.setDictLanguage( "lang" );
		dSingle1.newDictElement( "A" );
		String toS = dSingle1.toString();
		assertEquals( expectedToStringOutput.toString(), toS );
	}

	@Test
	public void testEqualsDictLangOneNull()
	{
		DictionarySingle dSingle1 = new DictionarySingle();
		DictionarySingle dSingle2 = new DictionarySingle();
		dSingle1.setDictLanguage( "A" );
		assertFalse( dSingle1.equals( dSingle2 ) );
		assertFalse( dSingle2.equals( dSingle1 ) );
	}

	@Test
	public void testEqualsTheIdDiffers()
	{
		DictionarySingle dSingle1 = new DictionarySingle();
		DictionarySingle dSingle2 = new DictionarySingle()
		{
			@Override
			public void setStartId( int i )
			{
				theId = i;
			}
		};
		dSingle2.setStartId( 9 );
		assertFalse( dSingle1.equals( dSingle2 ) );
		assertFalse( dSingle2.equals( dSingle1 ) );
	}

	@Test
	public void testEqualsStartIdDiffers()
	{
		DictionarySingle dSingle1r = new DictionarySingle();
		DictionarySingle dSingle2r = new DictionarySingle();
		dSingle1r.setStartId( 9 );
		assertFalse( dSingle1r.equals( dSingle2r ) );
		assertFalse( dSingle2r.equals( dSingle1r ) );
	}

	@Test
	public void testEqualsEndIdDiffers()
	{
		DictionarySingle dSingle1 = new DictionarySingle();
		DictionarySingle dSingle2 = new DictionarySingle();
		dSingle1.setEndId( 9 );
		assertFalse( dSingle1.equals( dSingle2 ) );
		assertFalse( dSingle2.equals( dSingle1 ) );
	}

	@Test
	public void testEqualsInterestingTopicsOneIsNull()
	{
		DictionarySingle dSingle1e = new DictionarySingle();
		DictionarySingle dSingle2e = new DictionarySingle();
		List<String> iTcs = new ArrayList<String>();
		dSingle2e.setInterestingTopics( iTcs );
		assertFalse( dSingle1e.equals( dSingle2e ) );
		assertFalse( dSingle2e.equals( dSingle1e ) );
	}

	@Test
	public void testEqualsInterestingTopicsBothHaveValues()
	{
		DictionarySingle dSingle111 = new DictionarySingle();
		DictionarySingle dSingle222 = new DictionarySingle();
		List<String> iTcs1 = new ArrayList<String>();
		iTcs1.add( "asdf" );
		iTcs1.add( "qwerty" );
		List<String> iTcs2 = new ArrayList<String>();
		iTcs2.add( "asdf" );
		dSingle111.setInterestingTopics( iTcs1 );
		dSingle222.setInterestingTopics( iTcs2 );
		assertFalse( dSingle111.equals( dSingle222 ) );
		assertFalse( dSingle222.equals( dSingle111 ) );
	}

	@Test
	public void testIsIdUsedInElementsNull()
	{
		DictionarySingle dSingle1aa = new DictionarySingle();
		DictionarySingle dSingle2aa = new DictionarySingle();
		dSingle2aa.setDictLanguage( "A" );
		DictElement dE2 = dSingle2aa.newDictElement( "ewe" );
		assertFalse( dSingle1aa.isIdUsedInElements( 10 ) );
		assertFalse( dSingle2aa.isIdUsedInElements( 10 ) );
	}

	@Test
	public void testIsExpressionUsedInElementsNull()
	{
		DictionarySingle dSingle1aa = new DictionarySingle();
		DictionarySingle dSingle2aa = new DictionarySingle();
		dSingle2aa.setDictLanguage( "V" );
		DictElement dE2 = dSingle2aa.newDictElement( "we" );
		assertFalse( dSingle1aa.isExpressionUsedInElements( "A" ) );
		assertFalse( dSingle2aa.isExpressionUsedInElements( "A" ) );
	}

	@Test
	public void testIsExpressionUsedInElementsStrictNull()
	{
		DictionarySingle dSingle1aa = new DictionarySingle();
		DictionarySingle dSingle2aa = new DictionarySingle();
		dSingle2aa.setDictLanguage( "V" );
		DictElement dE2 = dSingle2aa.newDictElement( "wee" );
		assertFalse( dSingle1aa.isExpressionUsedInElementsStrict( "A" ) );
		assertFalse( dSingle2aa.isExpressionUsedInElementsStrict( "A" ) );
	}

	@Test
	public void testEqualsDictElements2()
	{
		DictionarySingle dSingle1a = new DictionarySingle();
		DictionarySingle dSingle2a = new DictionarySingle();
		dSingle1a.setDictLanguage( "A" );
		dSingle2a.setDictLanguage( "A" );
		DictElement dE1 = dSingle1a.newDictElement( "we" );
		DictElement dE11 = dSingle1a.newDictElement( "we1" );
		DictElement dE2 = dSingle2a.newDictElement( "we" );
		assertFalse( dSingle1a.equals( dSingle2a ) );
		assertFalse( dSingle2a.equals( dSingle1a ) );
	}

	@Test
	public void testEqualsDictElements3()
	{
		DictionarySingle dSingle1a = new DictionarySingle();
		DictionarySingle dSingle2a = new DictionarySingle();
		dSingle2a.setDictLanguage( "A" );
		DictElement dE2 = dSingle2a.newDictElement( "we" );
		assertFalse( dSingle1a.equals( dSingle2a ) );
		assertFalse( dSingle2a.equals( dSingle1a ) );
	}

	@Test
	public void testDateTimes1()
	{
		DictionarySingle dSingle1a = new DictionarySingle();
		DictionarySingle dSingle2a = new DictionarySingle();
		dSingle1a.setStartTime( null );
		dSingle1a.setEndTime( null );
		dSingle2a.setStartTime( null );
		dSingle2a.setEndTime( null );
		assertTrue( dSingle1a.equals( dSingle2a ) );
		assertTrue( dSingle2a.equals( dSingle1a ) );
	}

	@Test
	public void testDateTimes2()
	{
		DictionarySingle dSingle1a = new DictionarySingle();
		DictionarySingle dSingle2a = new DictionarySingle();
		dSingle1a.setStartTime( LocalDateTime.of( LocalDate.of( 2015, 4, 4 ), LocalTime.of( 11, 11, 11 ) ) );
		dSingle1a.setEndTime( LocalDateTime.of( LocalDate.of( 2015, 5, 4 ), LocalTime.of( 11, 11, 11 ) ) );
		dSingle2a.setStartTime( null );
		dSingle2a.setEndTime( null );
		assertFalse( dSingle1a.equals( dSingle2a ) );
		assertFalse( dSingle2a.equals( dSingle1a ) );
	}

	@Test
	public void testDateTimes3()
	{
		DictionarySingle dSingle1a = new DictionarySingle();
		DictionarySingle dSingle2a = new DictionarySingle();
		dSingle1a.setStartTime( LocalDateTime.of( LocalDate.of( 2015, 4, 4 ), LocalTime.of( 11, 11, 11 ) ) );
		dSingle1a.setEndTime( LocalDateTime.of( LocalDate.of( 2015, 5, 4 ), LocalTime.of( 11, 11, 11 ) ) );
		dSingle2a.setStartTime( LocalDateTime.of( LocalDate.of( 2015, 4, 4 ), LocalTime.of( 11, 11, 11 ) ) );
		dSingle2a.setEndTime( LocalDateTime.of( LocalDate.of( 2015, 5, 3 ), LocalTime.of( 11, 11, 11 ) ) );
		assertFalse( dSingle1a.equals( dSingle2a ) );
		assertFalse( dSingle2a.equals( dSingle1a ) );
	}

	@Test
	public void testDateTimes4()
	{
		DictionarySingle dSingle1a = new DictionarySingle();
		DictionarySingle dSingle2a = new DictionarySingle();
		dSingle1a.setStartTime( LocalDateTime.of( LocalDate.of( 2015, 4, 4 ), LocalTime.of( 11, 11, 11 ) ) );
		dSingle1a.setEndTime( LocalDateTime.of( LocalDate.of( 2015, 5, 4 ), LocalTime.of( 11, 11, 11 ) ) );
		dSingle2a.setStartTime( LocalDateTime.of( LocalDate.of( 2015, 4, 4 ), LocalTime.of( 11, 11, 11 ) ) );
		dSingle2a.setEndTime( LocalDateTime.of( LocalDate.of( 2015, 5, 3 ), LocalTime.of( 11, 11, 11 ) ) );
		assertFalse( dSingle1a.equals( dSingle2a ) );
		assertFalse( dSingle2a.equals( dSingle1a ) );
	}

	@Test
	public void testDateTimes5()
	{
		DictionarySingle dSingle1a = new DictionarySingle();
		DictionarySingle dSingle2a = new DictionarySingle();
		dSingle1a.setStartTime( LocalDateTime.of( LocalDate.of( 2015, 4, 4 ), LocalTime.of( 11, 11, 11 ) ) );
		dSingle1a.setEndTime( LocalDateTime.of( LocalDate.of( 2015, 5, 4 ), LocalTime.of( 11, 11, 11 ) ) );
		dSingle2a.setStartTime( LocalDateTime.of( LocalDate.of( 2015, 4, 4 ), LocalTime.of( 11, 11, 11 ) ) );
		dSingle2a.setEndTime( LocalDateTime.of( LocalDate.of( 2015, 5, 5 ), LocalTime.of( 11, 11, 11 ) ) );
		assertFalse( dSingle1a.equals( dSingle2a ) );
		assertFalse( dSingle2a.equals( dSingle1a ) );
	}

	@Test
	public void testDateTimes6()
	{
		DictionarySingle dSingle1a = new DictionarySingle();
		DictionarySingle dSingle2a = new DictionarySingle();
		dSingle1a.setStartTime( LocalDateTime.of( LocalDate.of( 2015, 4, 4 ), LocalTime.of( 11, 11, 11 ) ) );
		dSingle1a.setEndTime( LocalDateTime.of( LocalDate.of( 2015, 5, 4 ), LocalTime.of( 11, 11, 11 ) ) );
		dSingle2a.setStartTime( LocalDateTime.of( LocalDate.of( 2015, 4, 3 ), LocalTime.of( 11, 11, 11 ) ) );
		dSingle2a.setEndTime( LocalDateTime.of( LocalDate.of( 2015, 5, 4 ), LocalTime.of( 11, 11, 11 ) ) );
		assertFalse( dSingle1a.equals( dSingle2a ) );
		assertFalse( dSingle2a.equals( dSingle1a ) );
	}

	@Test
	public void testDateTimes8()
	{
		DictionarySingle dSingle1a = new DictionarySingle();
		DictionarySingle dSingle2a = new DictionarySingle();
		dSingle1a.setStartTime( LocalDateTime.of( LocalDate.of( 2015, 4, 4 ), LocalTime.of( 11, 11, 11 ) ) );
		dSingle1a.setEndTime( LocalDateTime.of( LocalDate.of( 2015, 5, 4 ), LocalTime.of( 11, 11, 11 ) ) );
		dSingle2a.setStartTime( LocalDateTime.of( LocalDate.of( 2015, 4, 4 ), LocalTime.of( 11, 11, 11 ) ) );
		dSingle2a.setEndTime( null );
		assertFalse( dSingle1a.equals( dSingle2a ) );
		assertFalse( dSingle2a.equals( dSingle1a ) );
		dSingle2a.setEndTime( LocalDateTime.of( LocalDate.of( 2015, 5, 4 ), LocalTime.of( 11, 11, 11 ) ) );
		dSingle1a.setEndTime( null );
		assertFalse( dSingle1a.equals( dSingle2a ) );
		assertFalse( dSingle2a.equals( dSingle1a ) );
	}

	@Test
	public void testElementsNullAndNotNull()
	{
		DictionarySingle dSingle1aE = new DictionarySingle();
		DictionarySingle dSingle2aE = new DictionarySingle();
		dSingle1aE.setDictLanguage( "LL" );
		dSingle1aE.newDictElement( "RR" );
		assertFalse( dSingle2aE.equals( dSingle1aE ) );
		assertFalse( dSingle1aE.equals( dSingle2aE ) );
		assertNull( dSingle2aE.getElements() );
	}

	@Test
	public void testElementsNotNullAndNotNull()
	{
		DictionarySingle dSingle1aE = new DictionarySingle();
		DictionarySingle dSingle2aE = new DictionarySingle();
		dSingle1aE.setDictLanguage( "LL" );
		dSingle1aE.newDictElement( "RR" );
		dSingle2aE.setDictLanguage( "LL" );
		dSingle2aE.newDictElement( "RR" );
		assertTrue( dSingle2aE.equals( dSingle1aE ) );
		assertTrue( dSingle1aE.equals( dSingle2aE ) );
	}

	@Test
	public void testElementsNullAndNull()
	{
		DictionarySingle dSingle1aE = new DictionarySingle();
		DictionarySingle dSingle2aE = new DictionarySingle();
		assertTrue( dSingle2aE.equals( dSingle1aE ) );
		assertTrue( dSingle1aE.equals( dSingle2aE ) );
		assertNull( dSingle2aE.getElements() );
		assertNull( dSingle1aE.getElements() );
	}
}
