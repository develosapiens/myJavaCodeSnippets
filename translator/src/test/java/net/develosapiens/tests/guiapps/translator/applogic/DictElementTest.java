package net.develosapiens.tests.guiapps.translator.applogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import net.develosapiens.guiapps.translator.applogic.DictElement;
import net.develosapiens.guiapps.translator.applogic.Link;

public class DictElementTest
{
	private DictElement dE;

	@Before
	public void setUp() throws Exception
	{
		dE = new DictElement();
	}

	@Test
	public void testConstruction()
	{
		assertEquals( "", dE.getMyLanguage() );
		assertEquals( "", dE.getExpression() );
		assertEquals( "", dE.getMyTopic() );
		assertEquals( 0, dE.getExamples().size() );
		assertEquals( 0, dE.getId() );
		assertEquals( 0, dE.getLinks().size() );
		LocalDateTime testTime = LocalDateTime.of( LocalDate.now(), LocalTime.parse( "10:10:10" ) );
		assertEquals( testTime, dE.getTheDate() );
	}

	@Test
	public void testSetIdUnderZeroGetIdNotChanged()
	{
		int idBefore = dE.getId();
		dE.setId( -1 );
		int idAfter = dE.getId();
		assertEquals( idBefore, idAfter );
	}

	@Test
	public void testSetIdAboveZeroGetIdChanged()
	{
		dE.setId( 8 );
		assertEquals( 8, dE.getId() );
	}

	@Test
	public void testSetIdZeroGetIdNotChanged()
	{
		dE.setId( 8 );
		assertEquals( 8, dE.getId() );
		dE.setId( 0 );
		assertEquals( 8, dE.getId() );
	}

	@Test
	public void testSetIdOneGetIdChanged()
	{
		dE.setId( 8 );
		assertEquals( 8, dE.getId() );
		dE.setId( 1 );
		assertEquals( 1, dE.getId() );
	}

	@Test
	public void testSetExpressionOK()
	{
		assertEquals( "", dE.getExpression() );
		dE.setExpression( "develoSapiens" );
		assertEquals( "develoSapiens", dE.getExpression() );
	}

	@Test
	public void testSetExpressionWithNull()
	{
		assertEquals( "", dE.getExpression() );
		dE.setExpression( null );
		assertEquals( "", dE.getExpression() );
	}

	@Test
	public void testSetExpressionWithEmptyString()
	{
		assertEquals( "", dE.getExpression() );
		dE.setExpression( "" );
		assertEquals( "", dE.getExpression() );
	}

	@Test
	public void testSetExpressionWithWhiteSpaces()
	{
		assertEquals( "", dE.getExpression() );
		dE.setExpression( "    " );
		assertEquals( "", dE.getExpression() );
	}

	@Test
	public void testSetMyLanguageOK()
	{
		dE.setMyLanguage( "develoSapiens" );
		assertTrue( "develoSapiens".equals( dE.getMyLanguage() ) );
	}

	@Test
	public void testSetMyLanguageNull()
	{
		dE.setMyLanguage( null );
		assertEquals( "", dE.getMyLanguage() );
	}

	@Test
	public void testSetMyLanguageEmpty()
	{
		dE.setMyLanguage( "" );
		assertTrue( dE.getMyLanguage().isEmpty() );
	}

	@Test
	public void testSetMyLanguageWhiteSpaces()
	{
		dE.setMyLanguage( "    " );
		assertTrue( dE.getMyLanguage().isEmpty() );
	}

	@Test
	public void testSetMyTopicOK()
	{
		dE.setMyTopic( "This is a topic" );
		assertEquals( "This is a topic", dE.getMyTopic() );
	}

	@Test
	public void testSetMyTopicNull()
	{
		dE.setMyTopic( null );
		assertEquals( "", dE.getMyTopic() );
	}

	@Test
	public void testSetMyTopicEmpty()
	{
		dE.setMyTopic( "" );
		assertEquals( "", dE.getMyTopic() );
	}

	@Test
	public void testSetMyTopicWhiteSpaces()
	{
		dE.setMyTopic( "    " );
		assertEquals( "", dE.getMyTopic() );
	}

	@Test
	public void testSetTheDateGetItBack()
	{
		dE.setTheDate( LocalDateTime.of( 2011, 1, 4, 6, 7, 8 ) );
		String str = "2011-01-04 06:07:08";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" );
		LocalDateTime dateTime = LocalDateTime.parse( str, formatter );
		assertEquals( dE.getTheDate(), dateTime );
	}

	@SuppressWarnings( "unused" )
	@Test
	public void testSetTheDateNullGetItNotChanged()
	{
		LocalDateTime ldt = LocalDateTime.parse( "2015-08-04T10:11:30" );
		dE.setTheDate( null );
		assertTrue( dE.getTheDate().equals( LocalDateTime.of( LocalDate.now(), LocalTime.parse( "10:10:10" ) ) ) );
	}

	@Test
	public void testDefaultActive()
	{
		assertTrue( dE.isActive() );
	}

	@Test
	public void testActive()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		dE1.setActive( false );
		assertEquals( !dE1.isActive(), dE2.isActive() );
		assertFalse( dE1.equals( dE2 ) );
		dE2.setActive( false );
		assertEquals( dE1.isActive(), dE2.isActive() );
		assertEquals( dE1.isActive(), dE2.isActive() );
	}

	@Test
	public void testExamplesNullSizeNotChanges()
	{
		assertEquals( 0, dE.getExamples().size() );
		dE.setExamples( null );
		assertEquals( 0, dE.getExamples().size() );
		dE.setExamples( getSomeExamples() );
		assertEquals( 4, dE.getExamples().size() );
		dE.setExamples( null );
		assertEquals( 4, dE.getExamples().size() );
	}

	@Test
	public void testExamplesEmptySizeZero()
	{
		dE.setExamples( new ArrayList<String>() );
		assertEquals( true, dE.getExamples().isEmpty() );
		dE.setExamples( getSomeExamples() );
		assertEquals( 4, dE.getExamples().size() );
		dE.setExamples( new ArrayList<String>() );
		assertEquals( 0, dE.getExamples().size() );
		assertEquals( true, dE.getExamples().isEmpty() );
	}

	@Test
	public void testExamplesNullExampleFiltered()
	{
		List<String> exaList = getSomeExamples();
		assertEquals( 4, exaList.size() );
		dE.setExamples( exaList );
		assertEquals( 4, dE.getExamples().size() );
		exaList.add( null );
		assertEquals( 5, exaList.size() );
		dE.setExamples( exaList );
		assertEquals( 4, dE.getExamples().size() );
	}

	@Test
	public void testExamplesDuplicatedExamplesFiltered()
	{
		List<String> exaList = getSomeExamples();
		assertEquals( 4, exaList.size() );
		exaList.add( "jupiter" );
		assertEquals( 5, exaList.size() );
		dE.setExamples( exaList );
		assertEquals( 4, dE.getExamples().size() );
	}

	@Test
	public void testExamplesWhiteSpaceExamplesFiltered()
	{
		List<String> exaList = getSomeExamples();
		assertEquals( 4, exaList.size() );
		exaList.add( "    " );
		assertEquals( 5, exaList.size() );
		dE.setExamples( exaList );
		assertEquals( 4, dE.getExamples().size() );
	}

	@Test
	public void testExamplesEmptyExamplesFiltered()
	{
		List<String> exaList = getSomeExamples();
		assertEquals( 4, exaList.size() );
		exaList.add( "" );
		assertEquals( 5, exaList.size() );
		dE.setExamples( exaList );
		assertEquals( 4, dE.getExamples().size() );
	}

	private List<String> getSomeExamples()
	{
		List<String> exList = new ArrayList<>();
		exList.add( "Venus" );
		exList.add( "Mars" );
		exList.add( "Neptune" );
		exList.add( "Jupiter" );
		return exList;
	}

	@Test
	public void testAddingOneExampleNull()
	{
		assertEquals( 0, dE.getExamples().size() );
		dE.addingOneExample( null );
		assertEquals( 0, dE.getExamples().size() );
	}

	@Test
	public void testAddingOneExampleEmpty()
	{
		assertEquals( 0, dE.getExamples().size() );
		dE.addingOneExample( "" );
		assertEquals( 0, dE.getExamples().size() );
	}

	@Test
	public void testAddingOneExampleAlreadyHave()
	{
		assertEquals( 0, dE.getExamples().size() );
		dE.addingOneExample( "alpha beta" );
		assertEquals( 1, dE.getExamples().size() );
		dE.addingOneExample( "Alpha BETA" );
		assertEquals( 1, dE.getExamples().size() );
	}

	@Test
	public void testAddingOneExampleDontHaveYet()
	{
		assertEquals( 0, dE.getExamples().size() );
		dE.addingOneExample( "alpha beta" );
		assertEquals( 1, dE.getExamples().size() );
		dE.addingOneExample( "omega" );
		assertEquals( 2, dE.getExamples().size() );
	}

	@Test
	public void testDeletingOneExample()
	{
		assertEquals( 0, dE.getExamples().size() );
		dE.addingOneExample( "alpha beta" );
		assertEquals( 1, dE.getExamples().size() );
		dE.deletingOneExample( "alpha beta" );
		assertEquals( 0, dE.getExamples().size() );
	}

	@Test
	public void testDeletingOneExampleNull()
	{
		assertEquals( 0, dE.getExamples().size() );
		dE.addingOneExample( "alpha beta" );
		assertEquals( 1, dE.getExamples().size() );
		dE.deletingOneExample( null );
		assertEquals( 1, dE.getExamples().size() );
	}

	@Test
	public void testDeletingOneExampleEmpty()
	{
		assertEquals( 0, dE.getExamples().size() );
		dE.addingOneExample( "alpha beta" );
		assertEquals( 1, dE.getExamples().size() );
		dE.deletingOneExample( "" );
		assertEquals( 1, dE.getExamples().size() );
	}

	@Test
	public void testDeletingOneExampleDiff()
	{
		assertEquals( 0, dE.getExamples().size() );
		dE.addingOneExample( "alpha beta" );
		assertEquals( 1, dE.getExamples().size() );
		dE.deletingOneExample( "Alpha Beta" );
		assertEquals( 1, dE.getExamples().size() );
	}

	@Test
	public void testSetLinksNullGetSizeNotChanged()
	{
		dE.setLinks( getASizedTwoLinkList() );
		assertEquals( 2, dE.getLinks().size() );
		dE.setLinks( null );
		assertEquals( 2, dE.getLinks().size() );
		assertTrue( "lang one".equals( dE.getLinks().get( 0 ).getLanguage() ) );
		assertTrue( "lang two".equals( dE.getLinks().get( 1 ).getLanguage() ) );
	}

	@Test
	public void testSetLinksEmptyGetSizeZero()
	{
		dE.setLinks( getASizedTwoLinkList() );
		assertEquals( 2, dE.getLinks().size() );
		dE.setLinks( new ArrayList<Link>() );
		assertEquals( 0, dE.getLinks().size() );
	}

	@Test
	public void testSetLinksOkGetNewList()
	{
		dE.setLinks( getASizedTwoLinkList() );
		assertEquals( 2, dE.getLinks().size() );
		dE.setLinks( getASizedThreeLinkList() );
		assertEquals( 3, dE.getLinks().size() );
		assertEquals( "ceda", dE.getLinks().get( 2 ).getLanguage() );
	}

	@Test
	public void testSetLinksDuplicatedLangsFiltered()
	{
		List<Link> tmpLinks = getASizedThreeLinkList();
		assertEquals( 3, tmpLinks.size() );
		assertEquals( 3, tmpLinks.get( 0 ).getLinkNumbers().size() );
		assertEquals( 3, tmpLinks.get( 1 ).getLinkNumbers().size() );
		assertEquals( 3, tmpLinks.get( 2 ).getLinkNumbers().size() );
		tmpLinks.get( 2 ).setLanguage( "alpha" );
		dE.setLinks( tmpLinks );
		assertEquals( 3, tmpLinks.size() );
		assertEquals( 3, tmpLinks.get( 0 ).getLinkNumbers().size() );
		assertEquals( 3, tmpLinks.get( 1 ).getLinkNumbers().size() );
		assertEquals( 3, tmpLinks.get( 2 ).getLinkNumbers().size() );
		assertEquals( 5, dE.getLinks().get( 0 ).getLinkNumbers().size() );
		assertEquals( 2, dE.getLinks().size() );
	}

	@Test
	public void testSetLinksNullLinkDoesNotCount()
	{
		List<Link> tmpLinks = getASizedThreeLinkList();
		tmpLinks.add( null );
		assertEquals( 4, tmpLinks.size() );
		dE.setLinks( tmpLinks );
		assertEquals( 3, dE.getLinks().size() );
	}

	private List<Link> getASizedTwoLinkList()
	{
		List<Link> linkList = new ArrayList<>();
		Link l1 = new Link();
		l1.setLanguage( "lang one" );
		List<Integer> n1 = new ArrayList<>();
		n1.add( 1 );
		n1.add( 2 );
		n1.add( 3 );
		l1.setLinkNumbers( n1 );
		linkList.add( l1 );
		Link l2 = new Link();
		l2.setLanguage( "lang two" );
		List<Integer> n2 = new ArrayList<>();
		n2.add( 1 );
		n2.add( 2 );
		n2.add( 3 );
		l2.setLinkNumbers( n2 );
		linkList.add( l2 );
		return linkList;
	}

	private List<Link> getASizedThreeLinkList()
	{
		List<Link> linkList = new ArrayList<>();
		Link l1 = new Link();
		l1.setLanguage( "alpha" );
		List<Integer> n1 = new ArrayList<>();
		n1.add( 1 );
		n1.add( 2 );
		n1.add( 3 );
		l1.setLinkNumbers( n1 );
		linkList.add( l1 );
		Link l2 = new Link();
		l2.setLanguage( "beta" );
		List<Integer> n2 = new ArrayList<>();
		n2.add( 1 );
		n2.add( 2 );
		n2.add( 3 );
		l2.setLinkNumbers( n2 );
		linkList.add( l2 );
		Link l3 = new Link();
		l3.setLanguage( "ceda" );
		List<Integer> n3 = new ArrayList<>();
		n3.add( 11 );
		n3.add( 2 );
		n3.add( 23 );
		l3.setLinkNumbers( n3 );
		linkList.add( l3 );
		return linkList;
	}

	@Test
	public void testAddingALink()
	{
		assertEquals( 0, dE.getLinks().size() );
		Link link = new Link();
		link.setLanguage( "Alang" );
		link.addingALinkNumber( 100 );
		link.addingALinkNumber( 101 );
		dE.addingALink( link );
		assertEquals( 1, dE.getLinks().size() );
	}

	@Test
	public void testAddingALinkAlreadyHave()
	{
		assertEquals( 0, dE.getLinks().size() );
		Link link1 = new Link();
		link1.setLanguage( "Alang" );
		assertEquals( "Alang", link1.getLanguage() );
		link1.addingALinkNumber( 100 );
		link1.addingALinkNumber( 101 );
		assertEquals( 2, link1.getLinkNumbers().size() );
		assertEquals( 100, (int) link1.getLinkNumbers().get( 0 ) );
		assertEquals( 101, (int) link1.getLinkNumbers().get( 1 ) );
		dE.addingALink( link1 );
		assertEquals( 1, dE.getLinks().size() );
		Link link2 = new Link();
		link2.setLanguage( "Alang" );
		link2.addingALinkNumber( 120 );
		link2.addingALinkNumber( 121 );
		dE.addingALink( link2 );
		assertEquals( 1, dE.getLinks().size() );
	}

	@Test
	public void testAddingALinkDontHave()
	{
		assertEquals( 0, dE.getLinks().size() );
		Link link1 = new Link();
		link1.setLanguage( "Alang" );
		link1.addingALinkNumber( 100 );
		link1.addingALinkNumber( 101 );
		dE.addingALink( link1 );
		Link link2 = new Link();
		link2.setLanguage( "Blang" );
		link2.addingALinkNumber( 120 );
		link2.addingALinkNumber( 121 );
		dE.addingALink( link2 );
		assertEquals( 2, dE.getLinks().size() );
	}

	@Test
	public void testAddingALinkNull()
	{
		assertEquals( 0, dE.getLinks().size() );
		dE.addingALink( null );
		assertEquals( 0, dE.getLinks().size() );
	}

	@Test
	public void testDeletingALink()
	{
		assertEquals( 0, dE.getLinks().size() );
		Link link = new Link();
		link.setLanguage( "Alang" );
		link.addingALinkNumber( 100 );
		link.addingALinkNumber( 101 );
		dE.addingALink( link );
		assertEquals( 1, dE.getLinks().size() );
		Link linkToDel = dE.findALinkByLanguage( "Alang" );
		dE.deletingALink( linkToDel );
		assertEquals( 0, dE.getLinks().size() );
	}

	@Test
	public void testDeletingALinkWeDontHave()
	{
		assertEquals( 0, dE.getLinks().size() );
		Link link = new Link();
		link.setLanguage( "Alang" );
		link.addingALinkNumber( 100 );
		link.addingALinkNumber( 101 );
		dE.addingALink( link );
		assertEquals( 1, dE.getLinks().size() );
		Link linkToDel = new Link();
		dE.deletingALink( linkToDel );
		assertEquals( 1, dE.getLinks().size() );
	}

	@Test
	public void testDeletingALinkNull()
	{
		assertEquals( 0, dE.getLinks().size() );
		Link link = new Link();
		link.setLanguage( "Alang" );
		link.addingALinkNumber( 100 );
		link.addingALinkNumber( 101 );
		dE.addingALink( link );
		assertEquals( 1, dE.getLinks().size() );
		dE.deletingALink( null );
		assertEquals( 1, dE.getLinks().size() );
	}

	@Test
	public void testFindALinkByLanguageNull()
	{
		assertNull( dE.findALinkByLanguage( null ) );
	}

	@Test
	public void testFindALinkByLanguageEmpty()
	{
		assertNull( dE.findALinkByLanguage( "" ) );
	}

	@Test
	public void testFindALinkByLanguage()
	{
		List<Link> links = new ArrayList<Link>();
		Link l1 = new Link();
		l1.setLanguage( "alpha" );
		l1.addingALinkNumber( 101 );
		l1.addingALinkNumber( 102 );
		l1.addingALinkNumber( 103 );
		Link l2 = new Link();
		l2.setLanguage( "beta" );
		l2.addingALinkNumber( 201 );
		l2.addingALinkNumber( 202 );
		l2.addingALinkNumber( 203 );
		Link l3 = new Link();
		l3.setLanguage( "gamma" );
		l3.addingALinkNumber( 301 );
		l3.addingALinkNumber( 302 );
		l3.addingALinkNumber( 303 );
		links.add( l1 );
		links.add( l2 );
		links.add( l3 );
		dE.setLinks( links );
		assertEquals( (Integer) 303, dE.findALinkByLanguage( "gamma" ).getLinkNumbers().get( 2 ) );
		assertEquals( (Integer) 202, dE.findALinkByLanguage( "beta" ).getLinkNumbers().get( 1 ) );
		assertEquals( (Integer) 101, dE.findALinkByLanguage( "alpha" ).getLinkNumbers().get( 0 ) );
	}

	@Test
	public void testToStringWithDefaults()
	{
		LocalDate testTime = LocalDate.now();
		StringBuilder sB = new StringBuilder();
		sB.append( "id: 0\n" );
		sB.append( "myLanguage: \n" );
		sB.append( "expression: \n" );
		sB.append( "links:\n" );
		sB.append( "examples:\n" );
		sB.append( "myTopic: \n" );
		sB.append( "theDate: " + testTime.getYear() + ". " + testTime.getMonthValue() + ". " + testTime.getDayOfMonth()
		    + ".  10:10:10\n" );
		sB.append( "active: true\n" );
		String expected = sB.toString();
		assertEquals( expected, dE.toString() );
	}

	@Test
	public void testToStringWithSettings()
	{
		dE.setActive( false );
		dE.setExpression( "develosapiens" );
		dE.setMyTopic( "Lesson 1" );
		dE.setId( 100 );
		dE.setMyLanguage( "English" );
		List<String> examples = new ArrayList<String>();
		examples.add( "A" );
		examples.add( "AA" );
		examples.add( "A" );
		examples.add( "AB" );
		dE.setExamples( examples );
		assertEquals( 3, dE.getExamples().size() );
		List<Link> links = new ArrayList<Link>();
		Link link = new Link();
		link.setLanguage( "someLang" );
		List<Integer> linkNumbers = new ArrayList<Integer>();
		linkNumbers.add( 9009 );
		linkNumbers.add( 9009 );
		linkNumbers.add( 909 );
		linkNumbers.add( 903 );
		linkNumbers.add( 903 );
		link.setLinkNumbers( linkNumbers );
		links.add( link );
		dE.setLinks( links );
		LocalDate testTime = LocalDate.now();
		StringBuilder sB = new StringBuilder();
		sB.append( "id: 100\n" );
		sB.append( "myLanguage: English\n" );
		sB.append( "expression: develosapiens\n" );
		sB.append( "links:\n" );
		sB.append( "someLang: 903, 909, 9009\n" );
		sB.append( "examples:\n" );
		sB.append( "A\n" );
		sB.append( "AA\n" );
		sB.append( "AB\n" );
		sB.append( "myTopic: Lesson 1\n" );
		sB.append( "theDate: " + testTime.getYear() + ". " + testTime.getMonthValue() + ". " + testTime.getDayOfMonth()
		    + ".  10:10:10\n" );
		sB.append( "active: false\n" );
		String expected = sB.toString();
		assertEquals( expected, dE.toString() );
	}

	@Test
	public void testEqualsDefaultsGetTrue()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		assertTrue( dE1.equals( dE2 ) );
	}

	@Test
	public void testEqualsNullGetFalse()
	{
		assertFalse( dE.equals( null ) );
	}

	@Test
	public void testEqualsExpressions()
	{
		DictElement dE1 = new DictElement();
		dE1.setExpression( "develoSapiens" );
		DictElement dE2 = new DictElement();
		assertFalse( dE1.equals( dE2 ) );
		dE2.setExpression( "develoSapiens" );
		assertTrue( dE1.equals( dE2 ) );
	}

	@Test
	public void testEqualsLanguages()
	{
		DictElement dE1 = new DictElement();
		dE1.setMyLanguage( "develoSapiens" );
		DictElement dE2 = new DictElement();
		assertFalse( dE1.equals( dE2 ) );
		dE2.setMyLanguage( "develoSapiens" );
		assertTrue( dE1.equals( dE2 ) );
	}

	@Test
	public void testEqualsTopics()
	{
		DictElement dE1 = new DictElement();
		dE1.setMyTopic( "develoSapiens" );
		DictElement dE2 = new DictElement();
		assertFalse( dE1.equals( dE2 ) );
		dE2.setMyTopic( "develoSapiens" );
		assertTrue( dE1.equals( dE2 ) );
	}

	@Test
	public void testEqualsTheDate()
	{
		DictElement dE1 = new DictElement();
		dE1.setTheDate( LocalDateTime.of( 2011, 1, 1, 10, 12, 10 ) );
		DictElement dE2 = new DictElement();
		assertFalse( dE1.equals( dE2 ) );
		dE2.setTheDate( LocalDateTime.of( 2011, 1, 1, 10, 12, 10 ) );
		assertTrue( dE1.equals( dE2 ) );
	}

	@Test
	public void testEqualsIds()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		dE2.setId( 9 );
		assertFalse( dE1.equals( dE2 ) );
		dE1.setId( 9 );
		assertTrue( dE1.equals( dE2 ) );
		dE1.setId( 19 );
		assertFalse( dE1.equals( dE2 ) );
		dE2.setId( 19 );
		assertTrue( dE1.equals( dE2 ) );
		dE2.setId( -19 );
		assertTrue( dE1.equals( dE2 ) );
	}

	@Test
	public void testEqualsLinks1LangDiff()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		List<Integer> nums1 = new ArrayList<Integer>();
		nums1.add( 7 );
		nums1.add( 8 );
		nums1.add( 9 );
		nums1.add( 7 );
		Link link1 = new Link();
		link1.setLanguage( "Alang" );
		link1.setLinkNumbers( nums1 );
		dE1.addingALink( link1 );
		List<Integer> nums2 = new ArrayList<Integer>();
		nums2.add( 9 );
		nums2.add( 7 );
		nums2.add( 9 );
		nums2.add( 8 );
		Link link2 = new Link();
		link2.setLanguage( "Blang" );
		link2.setLinkNumbers( nums2 );
		dE2.addingALink( link2 );
		assertFalse( dE1.equals( dE2 ) );
	}

	@Test
	public void testEqualsLinks1LangEqual()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		List<Integer> nums1 = new ArrayList<Integer>();
		nums1.add( 7 );
		nums1.add( 8 );
		nums1.add( 9 );
		nums1.add( 7 );
		Link link1 = new Link();
		link1.setLanguage( "Alang" );
		link1.setLinkNumbers( nums1 );
		dE1.addingALink( link1 );
		List<Integer> nums2 = new ArrayList<Integer>();
		nums2.add( 9 );
		nums2.add( 7 );
		nums2.add( 9 );
		nums2.add( 8 );
		Link link2 = new Link();
		link2.setLanguage( "Alang" );
		link2.setLinkNumbers( nums2 );
		dE2.addingALink( link2 );
		assertTrue( dE1.equals( dE2 ) );
	}

	@Test
	public void testEqualsLinks2NumsDiff()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		List<Integer> nums1 = new ArrayList<Integer>();
		nums1.add( 7 );
		nums1.add( 8 );
		nums1.add( 9 );
		nums1.add( 7 );
		Link link1 = new Link();
		link1.setLanguage( "Alang" );
		link1.setLinkNumbers( nums1 );
		dE1.addingALink( link1 );
		List<Integer> nums2 = new ArrayList<Integer>();
		nums2.add( 7 );
		nums2.add( 8 );
		nums2.add( 6 );
		nums2.add( 7 );
		Link link2 = new Link();
		link2.setLanguage( "Alang" );
		link2.setLinkNumbers( nums2 );
		dE2.addingALink( link2 );
		assertFalse( dE1.equals( dE2 ) );
	}

	@Test
	public void testEqualsLinks2NumsEqual()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		List<Integer> nums1 = new ArrayList<Integer>();
		nums1.add( 7 );
		nums1.add( 8 );
		nums1.add( 9 );
		nums1.add( 7 );
		Link link1 = new Link();
		link1.setLanguage( "Alang" );
		link1.setLinkNumbers( nums1 );
		dE1.addingALink( link1 );
		List<Integer> nums2 = new ArrayList<Integer>();
		nums2.add( 9 );
		nums2.add( 8 );
		nums2.add( 9 );
		nums2.add( 7 );
		Link link2 = new Link();
		link2.setLanguage( "Alang" );
		link2.setLinkNumbers( nums2 );
		dE2.addingALink( link2 );
		assertTrue( dE1.equals( dE2 ) );
	}

	@Test
	public void testEqualsLinks3NumsListSizeDiff()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		List<Integer> nums1 = new ArrayList<Integer>();
		nums1.add( 7 );
		nums1.add( 8 );
		nums1.add( 9 );
		nums1.add( 7 );
		Link link1 = new Link();
		link1.setLanguage( "Alang" );
		link1.setLinkNumbers( nums1 );
		dE1.addingALink( link1 );
		List<Integer> nums2 = new ArrayList<Integer>();
		nums2.add( 7 );
		nums2.add( 8 );
		Link link2 = new Link();
		link2.setLanguage( "Alang" );
		link2.setLinkNumbers( nums2 );
		dE2.addingALink( link2 );
		assertFalse( dE1.equals( dE2 ) );
	}

	@Test
	public void testEqualsLinks4NumListSizeEqNumsDiff()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		List<Integer> nums1 = new ArrayList<Integer>();
		nums1.add( 8 );
		nums1.add( 9 );
		Link link1 = new Link();
		link1.setLanguage( "Alang" );
		link1.setLinkNumbers( nums1 );
		dE1.addingALink( link1 );
		List<Integer> nums2 = new ArrayList<Integer>();
		nums2.add( 7 );
		nums2.add( 8 );
		Link link2 = new Link();
		link2.setLanguage( "Alang" );
		link2.setLinkNumbers( nums2 );
		dE2.addingALink( link2 );
		assertFalse( dE1.equals( dE2 ) );
	}

	@Test
	public void testEqualsLinks5EqNumsInRightOrder()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		List<Integer> nums1 = new ArrayList<Integer>();
		nums1.add( 8 );
		nums1.add( 7 );
		Link link1 = new Link();
		link1.setLanguage( "Alang" );
		link1.setLinkNumbers( nums1 );
		dE1.addingALink( link1 );
		List<Integer> nums2 = new ArrayList<Integer>();
		nums2.add( 7 );
		nums2.add( 8 );
		Link link2 = new Link();
		link2.setLanguage( "Alang" );
		link2.setLinkNumbers( nums2 );
		dE2.addingALink( link2 );
		assertTrue( dE1.equals( dE2 ) );
	}

	@Test
	public void testEqualsLinks6LinksNumDiff()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		List<Integer> nums1 = new ArrayList<Integer>();
		nums1.add( 7 );
		nums1.add( 8 );
		nums1.add( 9 );
		nums1.add( 7 );
		Link link1 = new Link();
		link1.setLanguage( "Alang" );
		link1.setLinkNumbers( nums1 );
		dE1.addingALink( link1 );
		List<Integer> nums2 = new ArrayList<Integer>();
		nums2.add( 7 );
		nums2.add( 8 );
		nums2.add( 9 );
		Link link2 = new Link();
		link2.setLanguage( "Alang" );
		link2.setLinkNumbers( nums2 );
		List<Integer> nums3 = new ArrayList<Integer>();
		nums3.add( 7 );
		nums3.add( 8 );
		Link link3 = new Link();
		link3.setLanguage( "Clang" );
		link3.setLinkNumbers( nums3 );
		dE2.addingALink( link2 );
		dE2.addingALink( link3 );
		assertFalse( dE1.equals( dE2 ) );
	}

	@Test
	public void testEqualsExamplesSizeDiff()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		dE1.addingOneExample( "alpha" );
		dE1.addingOneExample( "beta" );
		dE2.addingOneExample( "beta" );
		assertFalse( dE1.equals( dE2 ) );
	}

	@Test
	public void testEqualsExamplesSizeEq()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		dE1.addingOneExample( "alpha" );
		dE2.addingOneExample( "alpha" );
		dE1.addingOneExample( "beta" );
		dE2.addingOneExample( "beta" );
		assertTrue( dE1.equals( dE2 ) );
	}

	@Test
	public void testEqualsActives()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		dE1.setActive( false );
		assertFalse( dE1.equals( dE2 ) );
		dE2.setActive( false );
		assertTrue( dE1.equals( dE2 ) );
		dE1.setActive( true );
		assertFalse( dE1.equals( dE2 ) );
		dE2.setActive( true );
		assertTrue( dE1.equals( dE2 ) );
	}

	@Test
	public void testEqualsExamplesContent()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		dE1.addingOneExample( "alpha" );
		dE1.addingOneExample( "beta" );
		dE2.addingOneExample( "beta" );
		dE2.addingOneExample( "omega" );
		assertFalse( dE1.equals( dE2 ) );
	}

	@Test
	public void testCompareEquals()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		dE1.setExpression( "alpha" );
		dE2.setExpression( "alpha" );
		assertEquals( 0, dE1.compareTo( dE2 ) );
		assertEquals( 0, dE2.compareTo( dE1 ) );
	}

	@Test
	public void testCompareDifferents()
	{
		DictElement dE1 = new DictElement();
		DictElement dE2 = new DictElement();
		dE1.setExpression( "alpha" );
		dE2.setExpression( "beta" );
		assertTrue( 0 > dE1.compareTo( dE2 ) );
		assertTrue( 0 < dE2.compareTo( dE1 ) );
	}

	@Test( expected = IllegalArgumentException.class )
	public void testCompareNullGetIllegalArgumentException()
	{
		DictElement dE1 = new DictElement();
		dE1.setExpression( "alpha" );
		dE1.compareTo( null );
	}

	@Test
	public void testListLinkLangsDoesItCreateRightOrder()
	{
		Link l1 = new Link();
		Link l2 = new Link();
		Link l3 = new Link();
		l1.setLanguage( "epsylon" );
		l2.setLanguage( "omega" );
		l3.setLanguage( "alpha" );
		dE.addingALink( l2 );
		dE.addingALink( l3 );
		dE.addingALink( l1 );
		List<String> langList = dE.listLinkLangs();
		assertEquals( "alpha", langList.get( 0 ) );
		assertEquals( "epsylon", langList.get( 1 ) );
		assertEquals( "omega", langList.get( 2 ) );
	}
}
