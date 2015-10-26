package net.develosapiens.tests.guiapps.translator.applogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import net.develosapiens.guiapps.translator.applogic.HeaderOfSD;

public class HeaderOfSDTest
{
	private HeaderOfSD header;

	@Before
	public void setUp() throws Exception
	{
		header = new HeaderOfSD();
	}

	@Test
	public void testConstructionDefaults()
	{
		assertEquals( "", header.getDictLanguage() );
		assertEquals( 0, header.getInterestingTopics().size() );
		assertEquals( 0, header.getTheId() );
		assertEquals( 0, header.getStartId() );
		assertEquals( 0, header.getEndId() );
		LocalDateTime referenceDateTimeStart = LocalDateTime.of( LocalDate.of( 2015, 8, 20 ),
		    LocalTime.parse( "11:11:11" ) );
		LocalDateTime referenceDateTimeEnd = LocalDateTime.of( LocalDate.now(), LocalTime.parse( "11:11:11" ) );
		assertEquals( referenceDateTimeStart, header.getStartTime() );
		assertEquals( referenceDateTimeEnd, header.getEndTime() );
	}

	@Test
	public void testSetDictLanguageWithNullGetDictLanguageEmpty()
	{
		header.setDictLanguage( null );
		assertEquals( "", header.getDictLanguage() );
	}

	@Test
	public void testSetDictLanguageWithEmptyGetDictLanguageEmpty()
	{
		header.setDictLanguage( "" );
		assertEquals( "", header.getDictLanguage() );
	}

	@Test
	public void testSetDictLanguageWithWhiteSpacesGetDictLanguageEmpty()
	{
		header.setDictLanguage( "    " );
		assertEquals( "", header.getDictLanguage() );
	}

	@Test
	public void testSetDictLanguageOKGetItBack()
	{
		String dL = "develoSapiens";
		header.setDictLanguage( dL );
		assertEquals( dL, header.getDictLanguage() );
	}

	@Test
	public void testStartDateWithNullGetStartDateNotChanged()
	{
		LocalDateTime dateBefore = header.getStartTime();
		header.setStartTime( null );
		LocalDateTime dateAfter = header.getStartTime();
		assertEquals( dateBefore, dateAfter );
	}

	@Test
	public void testEndDateWithNullGetEndDateNotChanged()
	{
		LocalDateTime dateBefore = header.getEndTime();
		header.setEndTime( null );
		LocalDateTime dateAfter = header.getEndTime();
		assertEquals( dateBefore, dateAfter );
	}

	@Test
	public void testTheIdUnderZeroGetNotChanged()
	{
		int theIdBefore = header.getTheId();
		header.setTheId( -1 );
		int theIdAfter = header.getTheId();
		assertEquals( theIdBefore, theIdAfter );
	}

	@Test
	public void testTheIdNotNegativeGetItBack()
	{
		header.setTheId( 101 );
		assertEquals( 101, header.getTheId() );
		header.setTheId( 0 );
		assertEquals( 0, header.getTheId() );
	}

	@Test
	public void testStartIdUnderZeroGetIdNotChanged()
	{
		int testIdBefore = header.getStartId();
		header.setStartId( -1 );
		int testIdAfter = header.getStartId();
		assertEquals( testIdBefore, testIdAfter );
	}

	@Test
	public void testStartIdNotNegativeGetItBack()
	{
		header.setStartId( 113 );
		assertEquals( 113, header.getStartId() );
		header.setStartId( 0 );
		assertEquals( 0, header.getStartId() );
	}

	@Test
	public void testEndIdUnderZeroGotIdNotChanged()
	{
		int testIdBefore = header.getEndId();
		header.setEndId( -4 );
		int testIdAfter = header.getEndId();
		assertEquals( testIdBefore, testIdAfter );
	}

	@Test
	public void testEndIdNotNegativeGetItBack()
	{
		header.setEndId( 113 );
		assertEquals( 113, header.getEndId() );
		header.setEndId( 0 );
		assertEquals( 0, header.getEndId() );
	}

	@Test
	public void testSetInterestingTopicsWithNullGetTheSameSizeOfItAsBefore1()
	{
		int sizeOfInTopicsBefore = header.getInterestingTopics().size();
		header.setInterestingTopics( null );
		int sizeOfInTopicsAfter = header.getInterestingTopics().size();
		assertEquals( sizeOfInTopicsBefore, sizeOfInTopicsAfter );
	}

	@Test
	public void testSetInterestingTopicsWithNullGetTheSameSizeOfItAsBefore2()
	{
		List<String> topics = new ArrayList<String>();
		topics.add( "alpha" );
		topics.add( "beta" );
		topics.add( "gamma" );
		header.setInterestingTopics( topics );
		int sizeOfInTopicsBefore = header.getInterestingTopics().size();
		header.setInterestingTopics( null );
		int sizeOfInTopicsAfter = header.getInterestingTopics().size();
		assertEquals( sizeOfInTopicsBefore, sizeOfInTopicsAfter );
		assertEquals( 3, header.getInterestingTopics().size() );
	}

	@Test
	public void testSetInterestingTopicsGetDuplicationsFiltered()
	{
		List<String> topics = new ArrayList<String>();
		topics.add( "alpha" );
		topics.add( "alpha" );
		topics.add( "alpha" );
		topics.add( "beta" );
		header.setInterestingTopics( topics );
		assertEquals( 2, header.getInterestingTopics().size() );
		assertTrue( "alpha".equals( header.getInterestingTopics().get( 0 ) ) );
		assertTrue( "beta".equals( header.getInterestingTopics().get( 1 ) ) );
	}

	@Test
	public void testSetInterestingTopicsGetWhiteSpaceStringsFiltered()
	{
		List<String> topics = new ArrayList<String>();
		topics.add( "alpha" );
		topics.add( "" );
		topics.add( " " );
		topics.add( "  " );
		topics.add( "   " );
		header.setInterestingTopics( topics );
		assertEquals( 1, header.getInterestingTopics().size() );
		assertTrue( "alpha".equals( header.getInterestingTopics().get( 0 ) ) );
	}

	@Test
	public void testSetInterestingTopicsWithZeroSizeListGetTheSameSizeOfItAsBefore2()
	{
		List<String> topics = new ArrayList<String>();
		topics.add( "alpha" );
		topics.add( "gamma" );
		topics.add( "alpha" );
		topics.add( "beta" );
		header.setInterestingTopics( topics );
		assertEquals( 3, header.getInterestingTopics().size() );
		header.setInterestingTopics( new ArrayList<String>() );
		assertEquals( 0, header.getInterestingTopics().size() );
	}

	@Test
	public void testSetInterestingTopicsNotOrderedGetItBackInRightOrder()
	{
		List<String> topics = new ArrayList<String>();
		topics.add( "gamma" );
		topics.add( "alpha" );
		topics.add( "beta" );
		header.setInterestingTopics( topics );
		assertEquals( "alpha", header.getInterestingTopics().get( 0 ) );
		assertEquals( "beta", header.getInterestingTopics().get( 1 ) );
		assertEquals( "gamma", header.getInterestingTopics().get( 2 ) );
	}

	@Test
	public void testAddingOneTopicGetTopicsListSizeIncreasedByOne()
	{
		assertEquals( 0, header.getInterestingTopics().size() );
		header.addingOneTopic( "one topic" );
		assertEquals( 1, header.getInterestingTopics().size() );
		header.addingOneTopic( "another topic" );
		assertEquals( 2, header.getInterestingTopics().size() );
	}

	@Test
	public void testAddingOneTopicNullGetTheSameSizeOfItAsBefore()
	{
		assertEquals( 0, header.getInterestingTopics().size() );
		header.addingOneTopic( null );
		assertEquals( 0, header.getInterestingTopics().size() );
	}

	@Test
	public void testAddingOneTopicEmptyGetTheSameSizeOfItAsBefore()
	{
		assertEquals( 0, header.getInterestingTopics().size() );
		header.addingOneTopic( "" );
		assertEquals( 0, header.getInterestingTopics().size() );
	}

	@Test
	public void testAddingOneTopicWhiteSpacesGetTheSameSizeOfItAsBefore()
	{
		assertEquals( 0, header.getInterestingTopics().size() );
		header.addingOneTopic( "   " );
		assertEquals( 0, header.getInterestingTopics().size() );
	}

	@Test
	public void testAddingOneTopicWhiteSpacesAndNonWhiteSpacesGetOneMoreTopic1()
	{
		assertEquals( 0, header.getInterestingTopics().size() );
		header.addingOneTopic( "  alpha  " );
		assertEquals( 1, header.getInterestingTopics().size() );
		assertEquals( "  alpha  ", header.getInterestingTopics().get( 0 ) );
	}

	@Test
	public void testAddingOneTopicWhiteSpacesAndNonWhiteSpacesGetOneMoreTopic2()
	{
		assertEquals( 0, header.getInterestingTopics().size() );
		header.addingOneTopic( "  alpha  beta    " );
		assertEquals( 1, header.getInterestingTopics().size() );
		assertEquals( "  alpha  beta    ", header.getInterestingTopics().get( 0 ) );
	}

	@Test
	public void testAddingOneTopicAlreadyHaveGetTheSameSizeAsBefore()
	{
		assertEquals( 0, header.getInterestingTopics().size() );
		header.addingOneTopic( "one topic" );
		assertEquals( 1, header.getInterestingTopics().size() );
		header.addingOneTopic( "one topic" );
		assertEquals( 1, header.getInterestingTopics().size() );
	}

	@Test
	public void testDeletingOneTopicGetTheSizeDecreasingByOne()
	{
		assertEquals( 0, header.getInterestingTopics().size() );
		header.addingOneTopic( "one topic" );
		assertEquals( 1, header.getInterestingTopics().size() );
		header.addingOneTopic( "another topic" );
		assertEquals( 2, header.getInterestingTopics().size() );
		header.deletingOneTopic( "another topic" );
		assertEquals( 1, header.getInterestingTopics().size() );
	}

	@Test
	public void testDeletingOneTopicWhichDoesntExistGetTheSizeTheSameAsBefore()
	{
		assertEquals( 0, header.getInterestingTopics().size() );
		header.addingOneTopic( "one topic" );
		assertEquals( 1, header.getInterestingTopics().size() );
		header.addingOneTopic( "another topic" );
		assertEquals( 2, header.getInterestingTopics().size() );
		header.deletingOneTopic( "other topic" );
		assertEquals( 2, header.getInterestingTopics().size() );
	}

	@Test
	public void testDeletingOneTopicNullGetTheSizeTheSameAsBefore()
	{
		assertEquals( 0, header.getInterestingTopics().size() );
		header.addingOneTopic( "one topic" );
		assertEquals( 1, header.getInterestingTopics().size() );
		header.addingOneTopic( "another topic" );
		assertEquals( 2, header.getInterestingTopics().size() );
		header.deletingOneTopic( null );
		assertEquals( 2, header.getInterestingTopics().size() );
	}

	@Test
	public void testDeletingOneTopicEmptyGetTheSizeTheSameAsBefore()
	{
		assertEquals( 0, header.getInterestingTopics().size() );
		header.addingOneTopic( "one topic" );
		assertEquals( 1, header.getInterestingTopics().size() );
		header.addingOneTopic( "another topic" );
		assertEquals( 2, header.getInterestingTopics().size() );
		header.deletingOneTopic( "" );
		assertEquals( 2, header.getInterestingTopics().size() );
	}

	@Test
	public void testToStringWithDefaults()
	{
		LocalDate lD = LocalDate.now();
		StringBuilder sB = new StringBuilder();
		sB.append( "Dictionary header:\n" );
		sB.append( "Dict language: \n" );
		sB.append( "Auto ID: 0\n" );
		sB.append( "Start ID: 0\n" );
		sB.append( "End ID: 0\n" );
		sB.append( "Start date: 2015. 8. 20. 11:11:11\n" );
		sB.append( "End date: " + lD.getYear() + ". " + lD.getMonthValue() + ". " + lD.getDayOfMonth() + ". 11:11:11\n" );
		sB.append( "Interesting topics:\n" );
		assertEquals( sB.toString(), header.toString() );
	}

	@Test
	public void testToStringWithCustomSettings()
	{
		header.setDictLanguage( "urdu" );
		header.setStartId( 100 );
		header.setEndId( 199 );
		header.setStartTime( LocalDateTime.of( LocalDate.of( 2014, 10, 10 ), LocalTime.of( 10, 10, 10 ) ) );
		header.setEndTime( LocalDateTime.of( LocalDate.of( 2015, 5, 6 ), LocalTime.of( 10, 10, 10 ) ) );
		header.setTheId( 200 );
		List<String> iTops = new ArrayList<String>();
		iTops.add( "alpha" );
		iTops.add( "omega" );
		iTops.add( "beta" );
		header.setInterestingTopics( iTops );
		StringBuilder sB = new StringBuilder();
		sB.append( "Dictionary header:\n" );
		sB.append( "Dict language: urdu\n" );
		sB.append( "Auto ID: 200\n" );
		sB.append( "Start ID: 100\n" );
		sB.append( "End ID: 199\n" );
		sB.append( "Start date: 2014. 10. 10. 10:10:10\n" );
		sB.append( "End date: 2015. 5. 6. 10:10:10\n" );
		sB.append( "Interesting topics:\n" );
		sB.append( "alpha\n" );
		sB.append( "beta\n" );
		sB.append( "omega\n" );
		assertEquals( sB.toString(), header.toString() );
	}

	@Test
	public void testEquals()
	{
		HeaderOfSD h1 = new HeaderOfSD();
		HeaderOfSD h2 = new HeaderOfSD();
		assertTrue( h1.equals( h2 ) );
	}

	@Test
	public void testEqualsLangDiff()
	{
		HeaderOfSD h1 = new HeaderOfSD();
		HeaderOfSD h2 = new HeaderOfSD();
		h1.setDictLanguage( "A" );
		assertFalse( h1.equals( h2 ) );
	}

	@Test
	public void testEqualsStartTimeDiff()
	{
		HeaderOfSD h1 = new HeaderOfSD();
		HeaderOfSD h2 = new HeaderOfSD();
		h1.setStartTime( LocalDateTime.of( 2000, 10, 1, 12, 12, 10 ) );
		assertFalse( h1.equals( h2 ) );
	}

	@Test
	public void testEqualsEndTimeDiff()
	{
		HeaderOfSD h1 = new HeaderOfSD();
		HeaderOfSD h2 = new HeaderOfSD();
		h1.setEndTime( LocalDateTime.of( 2000, 10, 1, 12, 12, 10 ) );
		assertFalse( h1.equals( h2 ) );
	}

	@Test
	public void testEqualsStartIdDiff()
	{
		HeaderOfSD h1 = new HeaderOfSD();
		HeaderOfSD h2 = new HeaderOfSD();
		h1.setStartId( 3 );
		assertFalse( h1.equals( h2 ) );
	}

	@Test
	public void testEqualsEndIdDiff()
	{
		HeaderOfSD h1 = new HeaderOfSD();
		HeaderOfSD h2 = new HeaderOfSD();
		h1.setEndId( 3 );
		assertFalse( h1.equals( h2 ) );
	}

	@Test
	public void testEqualsTheIdDiff()
	{
		HeaderOfSD h1 = new HeaderOfSD();
		HeaderOfSD h2 = new HeaderOfSD();
		h1.setTheId( 3 );
		assertFalse( h1.equals( h2 ) );
		h2.setTheId( 3 );
		assertTrue( h1.equals( h2 ) );
	}

	@Test
	public void testEqualsTopics()
	{
		HeaderOfSD h1 = new HeaderOfSD();
		HeaderOfSD h2 = new HeaderOfSD();
		h1.addingOneTopic( "A" );
		h2.addingOneTopic( "A" );
		h1.addingOneTopic( "B" );
		h2.addingOneTopic( "B" );
		assertTrue( h1.equals( h2 ) );
	}

	@Test
	public void testEqualsTopicsDiffSizeAndSameSize()
	{
		HeaderOfSD h1 = new HeaderOfSD();
		HeaderOfSD h2 = new HeaderOfSD();
		h2.addingOneTopic( "B" );
		assertFalse( h1.equals( h2 ) );
		h1.addingOneTopic( "B" );
		assertTrue( h1.equals( h2 ) );
	}

	@Test
	public void testEqualsTopicsDiffContent()
	{
		HeaderOfSD h1 = new HeaderOfSD();
		HeaderOfSD h2 = new HeaderOfSD();
		h1.addingOneTopic( "A" );
		h2.addingOneTopic( "B" );
		assertFalse( h1.equals( h2 ) );
	}
}
