package net.develosapiens.tests.guiapps.translator.applogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import net.develosapiens.guiapps.translator.applogic.DictElement;
import net.develosapiens.guiapps.translator.applogic.HeaderOfSD;
import net.develosapiens.guiapps.translator.applogic.Link;
import net.develosapiens.guiapps.translator.applogic.SingleDict;

public class SingleDictTest
{
	private SingleDict sD;

	@Before
	public void setUp() throws Exception
	{
		sD = new SingleDict();
		sD.getHeaderOfSD().setDictLanguage( "develoSapiens" );
	}

	@Test
	public void testConstruction()
	{
		sD = new SingleDict();
		assertEquals( 0, sD.getElements().size() );
		assertEquals( "", sD.getHeaderOfSD().getDictLanguage() );
		assertNotNull( sD.getHeaderOfSD().getStartTime() );
	}

	@Test
	public void testSetElements()
	{
		List<DictElement> elist = new ArrayList<>();
		DictElement de = new DictElement();
		de.setExpression( "one" );
		de.setMyLanguage( "English" );
		elist.add( de );
		de = new DictElement();
		de.setExpression( "two" );
		de.setMyLanguage( "English" );
		elist.add( de );
		de = new DictElement();
		de.setExpression( "three" );
		de.setMyLanguage( "English" );
		elist.add( de );
		de = new DictElement();
		de.setExpression( "four" );
		de.setMyLanguage( "English" );
		elist.add( de );
		de = new DictElement();
		de.setExpression( "five" );
		de.setMyLanguage( "English" );
		elist.add( de );
		elist.add( null );
		assertEquals( 6, elist.size() );
		sD.setElements( elist );
		assertEquals( 5, sD.getElements().size() );
	}

	@Test
	public void testSetElementsWithNullParamGetElementsNotChanged()
	{
		List<DictElement> elist = new ArrayList<>();
		DictElement de = new DictElement();
		de.setExpression( "one" );
		de.setMyLanguage( "English" );
		elist.add( de );
		de = new DictElement();
		de.setExpression( "two" );
		de.setMyLanguage( "English" );
		elist.add( de );
		de = new DictElement();
		de.setExpression( "three" );
		de.setMyLanguage( "English" );
		elist.add( de );
		de = new DictElement();
		de.setExpression( "four" );
		de.setMyLanguage( "English" );
		elist.add( de );
		de = new DictElement();
		de.setExpression( "five" );
		de.setMyLanguage( "English" );
		elist.add( de );
		elist.add( null );
		assertEquals( 6, elist.size() );
		sD.setElements( elist );
		assertEquals( 5, sD.getElements().size() );
		sD.setElements( null );
		assertEquals( 5, sD.getElements().size() );
	}

	@Test
	public void testSelectElementsByExpression()
	{
		createSomeDEsWithData();
		List<DictElement> selecteds = sD.selectElementsByExpression( "code" );
		assertEquals( 10, selecteds.size() );
		selecteds = sD.selectElementsByExpression( "epsylon" );
		assertEquals( 1, selecteds.size() );
		selecteds = sD.selectElementsByExpression( "some alpha code" );
		assertEquals( 1, selecteds.size() );
	}

	@Test
	public void testGetDictElementsInSortedListById()
	{
		createSomeDEsWithData();
		List<DictElement> des = sD.getDictElementsInSortedListById();
		int previousNumber = 0;
		boolean everyDeltaIsOne = true;
		for( DictElement d : des )
		{
			if( ( d.getId() - 1 ) != previousNumber )
			{
				everyDeltaIsOne = false;
			}
			previousNumber++;
		}
		assertTrue( everyDeltaIsOne );
		assertEquals( des.size(), sD.getElements().size() );
	}

	@Test
	public void testGetElementByExpressionWithNullParamGetEmptyListBack()
	{
		assertNull( sD.getElementByExpression( null ) );
	}

	@Test
	public void testAddNewDictElementWithNullStringParamGetNotAddedElement()
	{
		sD.addNewDictElement( "alpha" );
		sD.addNewDictElement( "Beta" );
		assertEquals( 2, sD.getElements().size() );
		sD.addNewDictElement( null );
		assertEquals( 2, sD.getElements().size() );
	}

	@Test
	public void testAddNewDictElementWithEmptyStringParamGetNotAddedElement()
	{
		sD.addNewDictElement( "alpha" );
		sD.addNewDictElement( "Beta" );
		assertEquals( 2, sD.getElements().size() );
		sD.addNewDictElement( "" );
		assertEquals( 2, sD.getElements().size() );
	}

	@Test
	public void testAddNewDictElementWithWhiteSpacesStringParamGetNotAddedElement()
	{
		sD.addNewDictElement( "alpha" );
		sD.addNewDictElement( "Beta" );
		assertEquals( 2, sD.getElements().size() );
		sD.addNewDictElement( "    " );
		assertEquals( 2, sD.getElements().size() );
	}

	@Test
	public void testAddNewDictElementWithStringParamWeAlreadyHaveGetNotAddedElement()
	{
		sD.addNewDictElement( "alpha" );
		sD.addNewDictElement( "Beta" );
		assertEquals( 2, sD.getElements().size() );
		sD.addNewDictElement( "beta" );
		assertEquals( 2, sD.getElements().size() );
	}

	@Test
	public void testAddNewDictElementWithOKStringParamGetAddedNewElement()
	{
		DictElement dE = sD.addNewDictElement( "Beta" );
		assertEquals( sD.getHeaderOfSD().getTheId(), dE.getId() );
		assertTrue( "Beta".equals( dE.getExpression() ) );
		assertFalse( "beta".equals( dE.getExpression() ) );
		assertTrue( sD.isExpressionUsedInElements( "beta" ) );
	}

	@Test
	public void testAddNewDictElementWithOKStringParamGetAddedNewElementIndexIncreasedByOne()
	{
		sD.addNewDictElement( "alpha" );
		sD.addNewDictElement( "Beta" );
		sD.addNewDictElement( "Beta" );
		sD.addNewDictElement( "Omega" );
		assertEquals( sD.getHeaderOfSD().getTheId(), sD.getElements().size() );
		assertEquals( 3, sD.getElements().size() );
	}

	@Test
	public void testAddNewDictElementWithOKStringParamGetAddedNewElementLanguageIsSetWellAutomatically()
	{
		DictElement dE = sD.addNewDictElement( "Beta" );
		assertEquals( sD.getHeaderOfSD().getDictLanguage(), dE.getMyLanguage() );
		assertEquals( "develoSapiens", dE.getMyLanguage() );
		assertEquals( sD.getHeaderOfSD().getDictLanguage(), "develoSapiens" );
	}

	@Test
	public void testDeleteDictElementWithNullParamGetElementsSizeNotChanged()
	{
		createSomeDEs();
		assertEquals( 5, sD.getElements().size() );
		sD.addNewDictElement( null );
		assertEquals( 5, sD.getElements().size() );
	}

	@Test
	public void testDeleteDictElementWithNegativeParamGetNoElementDeleted()
	{
		createSomeDEs();
		assertEquals( 5, sD.getElements().size() );
		sD.deleteDictElement( sD.getElementById( -6 ) );
		assertEquals( 5, sD.getElements().size() );
	}

	@Test
	public void testDeleteDictElementWithOKExpressionParamWeHaveGetListSizeDecreasedByOne()
	{
		createSomeDEs();
		assertEquals( 5, sD.getElements().size() );
		sD.deleteDictElement( sD.getElementByExpression( "alpha" ) );
		assertEquals( 4, sD.getElements().size() );
	}

	@Test
	public void testDeleteDictElementWithOKIndexParamWeHaveGetListSizeDecreasedByOne()
	{
		createSomeDEs();
		assertEquals( 5, sD.getElements().size() );
		sD.deleteDictElement( sD.getElementById( 2 ) );
		assertEquals( 4, sD.getElements().size() );
	}

	@Test
	public void testDeleteDictElementWithOKExpressionParamWeDontHaveGetListSizeNotChanged()
	{
		createSomeDEs();
		assertEquals( 5, sD.getElements().size() );
		sD.deleteDictElement( sD.getElementByExpression( "Venus" ) );
		assertEquals( 5, sD.getElements().size() );
	}

	@Test
	public void testDeleteDictElementWithOKIndexParamWeDontHaveGetListSizeNotChanged()
	{
		createSomeDEs();
		assertEquals( 5, sD.getElements().size() );
		assertNull( sD.getElementById( 8 ) );
		sD.deleteDictElement( sD.getElementById( 8 ) );
		assertEquals( 5, sD.getElements().size() );
	}

	@Test
	public void testTryingToDeleteDictElementWhichIsNotInTheListGetListSizeNotChanged()
	{
		createSomeDEs();
		assertEquals( 5, sD.getElements().size() );
		sD.deleteDictElement( new DictElement() );
		assertEquals( 5, sD.getElements().size() );
	}

	@Test
	public void testIsIdUsedInElements()
	{
		createSomeDEs();
		assertTrue( sD.isIdUsedInElements( 1 ) );
		assertTrue( sD.isIdUsedInElements( 2 ) );
		assertTrue( sD.isIdUsedInElements( 3 ) );
		assertTrue( sD.isIdUsedInElements( 4 ) );
		assertTrue( sD.isIdUsedInElements( 5 ) );
		assertFalse( sD.isIdUsedInElements( 6 ) );
	}

	private void createSomeDEs()
	{
		sD.addNewDictElement( "alpha" );
		sD.addNewDictElement( "beta" );
		sD.addNewDictElement( "gamma" );
		sD.addNewDictElement( "epsylon" );
		sD.addNewDictElement( "omega" );
	}

	@Test
	public void testGetElementByIdWithNullParamGetNull()
	{
		assertNull( sD.getElementById( null ) );
	}

	@Test
	public void testGetElementByIdWithParamWeHaveGetThatElement()
	{
		createSomeDEs();
		assertEquals( "epsylon", sD.getElementById( 4 ).getExpression() );
		assertEquals( "alpha", sD.getElementById( 1 ).getExpression() );
	}

	@Test
	public void testGetElementByIdWithParamWeDontHaveGetNull()
	{
		createSomeDEs();
		assertNull( sD.getElementById( 11 ) );
		assertNotNull( sD.getElementById( 1 ) );
	}

	@Test
	public void testGetElementByIdWithNegativeParamGetNull()
	{
		createSomeDEs();
		assertNull( sD.getElementById( -1 ) );
	}

	@Test
	public void testGetElementByIdWithZeroParamGetNull()
	{
		assertNull( sD.getElementById( 0 ) );
		createSomeDEs();
		assertNull( sD.getElementById( 0 ) );
	}

	@Test
	public void testSelectElementsByIDsWithEndIdLessThanStartId()
	{
		createSomeDEsWithData();
		sD.getHeaderOfSD().setStartId( 7 );
		sD.getHeaderOfSD().setEndId( 2 );
		assertEquals( 0, sD.selectElementsByIds().size() );
	}

	@Test
	public void testSelectElementsByIDsWithZeroStartId()
	{
		createSomeDEsWithData();
		sD.getHeaderOfSD().setStartId( 0 );
		sD.getHeaderOfSD().setEndId( 4 );
		assertEquals( 4, sD.selectElementsByIds().size() );
	}

	@Test
	public void testSelectElementsByIDsWithNegativeStartId()
	{
		createSomeDEsWithData();
		sD.getHeaderOfSD().setStartId( -8 );
		sD.getHeaderOfSD().setEndId( 4 );
		assertEquals( 4, sD.selectElementsByIds().size() );
	}

	@Test
	public void testSelectElementsByIDsWithZeroEndId()
	{
		createSomeDEsWithData();
		sD.getHeaderOfSD().setStartId( 1 );
		sD.getHeaderOfSD().setEndId( 0 );
		assertEquals( 10, sD.selectElementsByIds().get( 9 ).getId() );
		assertEquals( 10, sD.selectElementsByIds().size() );
		sD.getHeaderOfSD().setStartId( 4 );
		sD.getHeaderOfSD().setEndId( 0 );
		assertEquals( 10, sD.selectElementsByIds().get( 6 ).getId() );
		assertEquals( 7, sD.selectElementsByIds().size() );
		sD.getHeaderOfSD().setStartId( 9 );
		sD.getHeaderOfSD().setEndId( 0 );
		assertEquals( 2, sD.selectElementsByIds().size() );
	}

	@Test
	public void testSelectElementsByIDsWithEndIdBiggerThanLastIDGetEndIDTheLastOne()
	{
		createSomeDEsWithData();
		sD.getHeaderOfSD().setStartId( 1 );
		sD.getHeaderOfSD().setEndId( 100 );
		assertEquals( 10, sD.selectElementsByIds().get( 9 ).getId() );
		assertEquals( 10, sD.selectElementsByIds().size() );
		sD.getHeaderOfSD().setStartId( 4 );
		sD.getHeaderOfSD().setEndId( 1110 );
		assertEquals( 10, sD.selectElementsByIds().get( 6 ).getId() );
		assertEquals( 7, sD.selectElementsByIds().size() );
		sD.getHeaderOfSD().setStartId( 9 );
		sD.getHeaderOfSD().setEndId( 10000 );
		assertEquals( 2, sD.selectElementsByIds().size() );
	}

	@Test
	public void testSelectElementsByIDsBothAreOverLimit()
	{
		createSomeDEsWithData();
		sD.getHeaderOfSD().setStartId( -99 );
		sD.getHeaderOfSD().setEndId( 100 );
		assertEquals( 10, sD.selectElementsByIds().get( 9 ).getId() );
		assertEquals( 10, sD.selectElementsByIds().size() );
	}

	@Test
	public void testSelectElementsByIDs1()
	{
		createSomeDEsWithData();
		sD.getHeaderOfSD().setStartId( 3 );
		sD.getHeaderOfSD().setEndId( 5 );
		assertEquals( 5, sD.selectElementsByIds().get( 2 ).getId() );
		assertEquals( 3, sD.selectElementsByIds().size() );
	}

	@Test
	public void testSelectElementsByTimeWithEndTimeEarlier()
	{
		createSomeDEsWithData();
		sD.getHeaderOfSD().setStartTime( LocalDateTime.parse( "2001-03-12T10:10:10" ) );
		sD.getHeaderOfSD().setEndTime( LocalDateTime.parse( "2001-03-12T10:10:09" ) );
		assertEquals( 0, sD.selectElementsByTime().size() );
	}

	@Test
	public void testSelectElementsByTimeWithEndTimeParamLaterThanLastOne()
	{
		createSomeDEsWithData();
		sD.getHeaderOfSD().setStartTime( LocalDateTime.parse( "2001-03-22T10:10:11" ) );
		sD.getHeaderOfSD().setEndTime( LocalDateTime.parse( "2011-03-12T10:10:09" ) );
		assertEquals( 5, sD.selectElementsByTime().size() );
	}

	@Test
	public void testSelectElementsByTimeWithOKTimes()
	{
		createSomeDEsWithData();
		sD.getHeaderOfSD().setStartTime( LocalDateTime.parse( "2001-03-02T10:10:10" ) );
		sD.getHeaderOfSD().setEndTime( LocalDateTime.parse( "2001-04-02T16:10:08" ) );
		assertEquals( 4, sD.selectElementsByTime().size() );
		sD.getHeaderOfSD().setStartTime( LocalDateTime.parse( "2001-04-02T16:10:11" ) );
		sD.getHeaderOfSD().setEndTime( LocalDateTime.parse( "2001-05-04T10:10:09" ) );
		assertEquals( 2, sD.selectElementsByTime().size() );
		assertEquals( 8, sD.selectElementsByTime().get( 0 ).getId() );
		assertEquals( 9, sD.selectElementsByTime().get( 1 ).getId() );
	}

	@Test
	public void testSelectElementsByTimeStartAndEndTimesAreEqual()
	{
		createSomeDEsWithData();
		sD.getHeaderOfSD().setStartTime( LocalDateTime.parse( "2001-03-22T10:10:10" ) );
		sD.getHeaderOfSD().setEndTime( LocalDateTime.parse( "2001-03-22T10:10:10" ) );
		assertEquals( 1, sD.selectElementsByTime().size() );
	}

	@Test
	public void testSelectElementsByTopicsWithNonExistentTopicGetEmptyList()
	{
		createSomeDEsWithData();
		sD.getHeaderOfSD().setInterestingTopics( new ArrayList<>() );
		sD.getHeaderOfSD().addingOneTopic( "four" );
		sD.getHeaderOfSD().addingOneTopic( "family" );
		assertTrue( sD.selectElementsByTopics().isEmpty() );
	}

	@Test
	public void testSelectElementsByTopicsWithExistentTopicsGetThoseElementsWhichHaveTheTopics()
	{
		createSomeDEsWithData();
		sD.getHeaderOfSD().setInterestingTopics( new ArrayList<>() );
		sD.getHeaderOfSD().addingOneTopic( "lesson four" );
		sD.getHeaderOfSD().addingOneTopic( "lesson four" );
		sD.getHeaderOfSD().addingOneTopic( "lesson six" );
		assertEquals( 2, sD.selectElementsByTopics().size() );
		sD.getHeaderOfSD().addingOneTopic( "lesson two" );
		assertEquals( 5, sD.selectElementsByTopics().size() );
	}

	private void createSomeDEsWithData()
	{
		DictElement de1 = sD.addNewDictElement( "alpha code" );
		DictElement de2 = sD.addNewDictElement( "beta code" );
		DictElement de3 = sD.addNewDictElement( "gamma code" );
		DictElement de4 = sD.addNewDictElement( "epsylon code" );
		DictElement de5 = sD.addNewDictElement( "omega code" );
		DictElement de6 = sD.addNewDictElement( "zulu code" );
		DictElement de7 = sD.addNewDictElement( "delta code" );
		DictElement de8 = sD.addNewDictElement( "charlie code" );
		DictElement de9 = sD.addNewDictElement( "tango code" );
		DictElement de10 = sD.addNewDictElement( "bravo code" );
		de3.setActive( false );
		de7.setActive( false );
		de10.setActive( false );
		de1.setMyTopic( "Lesson one" );
		de2.setMyTopic( "Lesson one" );
		de3.setMyTopic( "Lesson two" );
		de4.setMyTopic( "Lesson two" );
		de5.setMyTopic( "Lesson two" );
		de6.setMyTopic( "Lesson three" );
		de7.setMyTopic( "Lesson four" );
		de8.setMyTopic( "Lesson five" );
		de9.setMyTopic( "Lesson five" );
		de10.setMyTopic( "Lesson six" );
		de1.setTheDate( LocalDateTime.parse( "2001-02-02T10:10:10" ) );
		de1.setTheDate( LocalDateTime.parse( "2001-02-02T15:10:10" ) );
		de2.setTheDate( LocalDateTime.parse( "2001-02-10T10:10:10" ) );
		de3.setTheDate( LocalDateTime.parse( "2001-03-02T10:10:10" ) );
		de4.setTheDate( LocalDateTime.parse( "2001-03-12T10:10:10" ) );
		de5.setTheDate( LocalDateTime.parse( "2001-03-22T10:10:10" ) );
		de6.setTheDate( LocalDateTime.parse( "2001-04-02T10:10:10" ) );
		de7.setTheDate( LocalDateTime.parse( "2001-04-02T16:10:10" ) );
		de8.setTheDate( LocalDateTime.parse( "2001-04-22T10:10:10" ) );
		de9.setTheDate( LocalDateTime.parse( "2001-05-02T10:10:10" ) );
		de10.setTheDate( LocalDateTime.parse( "2001-05-04T10:10:10" ) );
	}

	@Test
	public void testToStringWithDefaultsByConstructor()
	{
		StringBuilder sb = new StringBuilder();
		sb.append( "Single Dictionary\n" );
		sb.append( "Dictionary header:\n" );
		sb.append( "Dict language: develoSapiens\n" );
		sb.append( "Auto ID: 0\n" );
		sb.append( "Start ID: 0\n" );
		sb.append( "End ID: 0\n" );
		sb.append( "Start date: 2015. 8. 20. 11:11:11\n" );
		sb.append( "End date: " + LocalDate.now().getYear() + ". " + LocalDate.now().getMonthOfYear() + ". "
		    + LocalDate.now().getDayOfMonth() + ". 11:11:11\n" );
		sb.append( "Interesting topics:\n" );
		sb.append( "DictElements:\n" );
		assertEquals( sb.toString(), sD.toString() );
	}

	@Test
	public void testToStringWithCustomSettings()
	{
		HeaderOfSD myHead = sD.getHeaderOfSD();
		myHead.setDictLanguage( "English" );
		myHead.setEndId( 99 );
		myHead.setEndTime( LocalDateTime.parse( "2002-04-02T16:10:10" ) );
		myHead.setStartId( 23 );
		myHead.setStartTime( LocalDateTime.parse( "2001-04-02T16:10:10" ) );
		List<String> tops = new ArrayList<>();
		tops.add( "delta" );
		tops.add( "Delta" );
		tops.add( null );
		tops.add( "Bravo" );
		tops.add( "bravo" );
		tops.add( "" );
		tops.add( "     " );
		myHead.setInterestingTopics( tops );
		DictElement dE = sD.addNewDictElement( "Beta" );
		dE.addingOneExample( "exa 1" );
		dE.addingOneExample( "exa 2" );
		dE.addingOneExample( "exa 3" );
		dE.setActive( false );
		dE.setMyTopic( "Bravo" );
		StringBuilder sb = new StringBuilder();
		Link l1 = new Link();
		Link l2 = new Link();
		l1.setLanguage( "langone" );
		l2.setLanguage( "langother" );
		l1.addingALinkNumber( 101 );
		l1.addingALinkNumber( 102 );
		l1.addingALinkNumber( 103 );
		l2.addingALinkNumber( 501 );
		l2.addingALinkNumber( 502 );
		l2.addingALinkNumber( 503 );
		List<Link> links = new ArrayList<>();
		links.add( l1 );
		links.add( l2 );
		links.add( l1 );
		dE.setLinks( links );
		sb.append( "Single Dictionary\n" );
		sb.append( "Dictionary header:\n" );
		sb.append( "Dict language: English\n" );
		sb.append( "Auto ID: 1\n" );
		sb.append( "Start ID: 23\n" );
		sb.append( "End ID: 99\n" );
		sb.append( "Start date: 2001. 4. 2. 16:10:10\n" );
		sb.append( "End date: 2002. 4. 2. 16:10:10\n" );
		sb.append( "Interesting topics:\n" );
		sb.append( "Bravo\n" );
		sb.append( "delta\n" );
		sb.append( "DictElements:\n" );
		sb.append( "id: 1\n" );
		sb.append( "myLanguage: English\n" );
		sb.append( "expression: Beta\n" );
		sb.append( "links:\n" );
		sb.append( "langone: 101, 102, 103\n" );
		sb.append( "langother: 501, 502, 503\n" );
		sb.append( "examples:\n" );
		sb.append( "exa 1\n" );
		sb.append( "exa 2\n" );
		sb.append( "exa 3\n" );
		sb.append( "myTopic: Bravo\n" );
		sb.append( "theDate: " + LocalDate.now().getYear() + ". " + LocalDate.now().getMonthOfYear() + ". "
		    + LocalDate.now().getDayOfMonth() + ".  10:10:10\n" );
		sb.append( "active: false\n\n" );
		assertEquals( sb.toString(), sD.toString() );
	}

	@Test
	public void testEqualsElementsDiffLink()
	{
		SingleDict sd1 = settingSDup();
		SingleDict sd2 = settingSDup();
		Link linkToRemove = sd1.getElementByExpression( "kilo" ).findALinkByLanguage( "piresian" );
		sd1.getElementByExpression( "kilo" ).deletingALink( linkToRemove );
		assertFalse( sd1.equals( sd2 ) );
	}

	@Test
	public void testEqualsElementsSizeDiff()
	{
		SingleDict sd1 = settingSDup();
		SingleDict sd2 = settingSDup();
		sd2.deleteDictElement( sd2.getElementByExpression( "kilo" ) );
		assertFalse( sd1.equals( sd2 ) );
	}

	@Test
	public void testEqualsElementsAndHeaderDiff()
	{
		SingleDict sd1 = settingSDup();
		SingleDict sd2 = settingSDup();
		sd2.getHeaderOfSD().setEndId( 4 );
		sd2.getElementById( 1 ).setActive( false );
		assertFalse( sd1.equals( sd2 ) );
	}

	@Test
	public void testEqualsHeadersDiff()
	{
		SingleDict sd1 = settingSDup();
		SingleDict sd2 = settingSDup();
		sd2.getHeaderOfSD().setEndId( 4 );
		assertFalse( sd1.equals( sd2 ) );
	}

	private SingleDict settingSDup()
	{
		SingleDict sdi = new SingleDict();
		sdi.getHeaderOfSD().setDictLanguage( "testEnglish" );
		sdi.getHeaderOfSD().addingOneTopic( "topic one" );
		sdi.getHeaderOfSD().addingOneTopic( "topic two" );
		sdi.getHeaderOfSD().addingOneTopic( "topic three" );
		sdi.addNewDictElement( "kilo" );
		sdi.addNewDictElement( "tango" );
		sdi.addNewDictElement( "zulu" );
		sdi.addNewDictElement( "bravo" );
		sdi.addNewDictElement( "charlie" );
		DictElement tmpE = sdi.getElementByExpression( "kilo" );
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
		return sdi;
	}

	@Test
	public void testChangeIDsAfterIDAlreadyHaveGetNoChangeAndLogWarn()
	{
		createSomeDEsWithData();
		assertEquals( "gamma code", sD.getElementById( 3 ).getExpression() );
		sD.changeIDs( 3, 8 );
		assertEquals( "gamma code", sD.getElementById( 3 ).getExpression() );
	}

	@Test
	public void testChangeIDsBeforeIDIsNotGetNoChangeAndLogWarnJustForCoverage()
	{
		createSomeDEsWithData();
		sD.changeIDs( 11, 20 );
	}

	@Test
	public void testChangeIDs()
	{
		createSomeDEsWithData();
		assertEquals( "gamma code", sD.getElementById( 3 ).getExpression() );
		sD.changeIDs( 3, 17 );
		assertEquals( "gamma code", sD.getElementById( 17 ).getExpression() );
	}

	@Test
	public void testChangeIDsBecauseOfDeletingOneElementAndMakeIDsStepByOne()
	{
		createSomeDEsWithData();
		assertEquals( "gamma code", sD.getElementById( 3 ).getExpression() );
		assertEquals( "epsylon code", sD.getElementById( 4 ).getExpression() );
		sD.deleteDictElement( sD.getElementById( 3 ) );
		for( int i = 3; i <= sD.getElements().size(); i++ )
		{
			sD.changeIDs( i + 1, i );
		}
		assertEquals( "epsylon code", sD.getElementById( 3 ).getExpression() );
	}

	@Test
	public void testChangeIDsBecauseOfDeletingOneElementAndMakeIDsStepByOneNoSuchID()
	{
		createSomeDEsWithData();
		assertEquals( "gamma code", sD.getElementById( 3 ).getExpression() );
		sD.deleteDictElement( sD.getElementById( 13 ) );
		for( int i = 3; i <= sD.getElements().size(); i++ )
		{
			sD.changeIDs( i + 1, i );
		}
		assertEquals( "epsylon code", sD.getElementById( 4 ).getExpression() );
	}

	@Test
	public void testChangeIDsBecauseOfDeletingOneElementAndMakeIDsStepByOneUsingMethod()
	{
		createSomeDEsWithData();
		assertEquals( "gamma code", sD.getElementById( 3 ).getExpression() );
		assertEquals( "epsylon code", sD.getElementById( 4 ).getExpression() );
		sD.deleteElementByIDandRearrangeElements( 3 );
		assertEquals( "epsylon code", sD.getElementById( 3 ).getExpression() );
	}

	@Test
	public void testChangeIDsBecauseOfDeletingOneElementAndMakeIDsStepByOneUsingMethodNoSuchID()
	{
		createSomeDEsWithData();
		assertEquals( "gamma code", sD.getElementById( 3 ).getExpression() );
		sD.deleteElementByIDandRearrangeElements( 13 );
		assertEquals( "epsylon code", sD.getElementById( 4 ).getExpression() );
	}

	@Test
	public void testChangeIDsBecauseOfDeletingOneElementAndMakeIDsStepByOneUsingMethodOutOfLimit()
	{
		createSomeDEsWithData();
		assertEquals( "gamma code", sD.getElementById( 3 ).getExpression() );
		sD.deleteElementByIDandRearrangeElements( -13 );
		assertEquals( "epsylon code", sD.getElementById( 4 ).getExpression() );
	}

	@Test
	public void testDeletingElementByExpAndRearrangeElements()
	{
		createSomeDEsWithData();
		assertEquals( "beta code", sD.getElementById( 2 ).getExpression() );
		assertEquals( "gamma code", sD.getElementById( 3 ).getExpression() );
		sD.deleteElementByExpressionAndRearrangeElements( "beta code" );
		assertEquals( "gamma code", sD.getElementById( 2 ).getExpression() );
	}

	@Test
	public void testDeletingElementByExpAndRearrangeElementsThereIsNoSuchExp()
	{
		createSomeDEsWithData();
		assertEquals( "gamma code", sD.getElementById( 3 ).getExpression() );
		sD.deleteElementByExpressionAndRearrangeElements( "betacode" );
		assertEquals( "gamma code", sD.getElementById( 3 ).getExpression() );
	}

	@Test
	public void testDeletingElementByExpAndRearrangeElementsEmptyString()
	{
		createSomeDEsWithData();
		assertEquals( "gamma code", sD.getElementById( 3 ).getExpression() );
		sD.deleteElementByExpressionAndRearrangeElements( "" );
		assertEquals( "gamma code", sD.getElementById( 3 ).getExpression() );
	}

	@Test
	public void testDeleteNumberFromLinkNumbersByLanguage()
	{
		SingleDict sDct = settingSDup();
		assertTrue( sDct.getElementById( 1 ).findALinkByLanguage( "russ" ).getLinkNumbers().contains( 124 ) );
		sDct.deleteNumberFromLinkNumbersByLanguage( "russ", 124 );
		assertFalse( sDct.getElementById( 1 ).findALinkByLanguage( "russ" ).getLinkNumbers().contains( 124 ) );
	}

	@Test
	public void testChangeNumbersInLinkNumbersByLanguage()
	{
		SingleDict sDct = settingSDup();
		assertTrue( sDct.getElementById( 1 ).findALinkByLanguage( "russ" ).getLinkNumbers().contains( 124 ) );
		assertFalse( sDct.getElementById( 1 ).findALinkByLanguage( "russ" ).getLinkNumbers().contains( 5643 ) );
		sDct.changeNumbersInLinkNumbersByLanguage( "russ", 124, 5643 );
		assertTrue( sDct.getElementById( 1 ).findALinkByLanguage( "russ" ).getLinkNumbers().contains( 5643 ) );
		assertFalse( sDct.getElementById( 1 ).findALinkByLanguage( "russ" ).getLinkNumbers().contains( 124 ) );
	}

	@Test
	public void testDeleteNumberFromLinkNumbersByLanguageJustForCoverage()
	{
		SingleDict sDct = settingSDup();
		assertEquals( 3, sDct.getElementById( 1 ).findALinkByLanguage( "russ" ).getLinkNumbers().size() );
		sDct.deleteNumberFromLinkNumbersByLanguage( "russ", 1244 );
		assertEquals( 3, sDct.getElementById( 1 ).findALinkByLanguage( "russ" ).getLinkNumbers().size() );
	}

	@Test
	public void testChangeNumbersInLinkNumbersByLanguageJustForCoverage()
	{
		SingleDict sDct = settingSDup();
		assertFalse( sDct.getElementById( 1 ).findALinkByLanguage( "russ" ).getLinkNumbers().contains( 1244 ) );
		assertFalse( sDct.getElementById( 1 ).findALinkByLanguage( "russ" ).getLinkNumbers().contains( 5643 ) );
		sDct.changeNumbersInLinkNumbersByLanguage( "russ", 1244, 5643 );
		assertFalse( sDct.getElementById( 1 ).findALinkByLanguage( "russ" ).getLinkNumbers().contains( 1244 ) );
		assertFalse( sDct.getElementById( 1 ).findALinkByLanguage( "russ" ).getLinkNumbers().contains( 5643 ) );
	}
}
