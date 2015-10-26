package net.develosapiens.tests.guiapps.translator.applogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import net.develosapiens.guiapps.translator.applogic.Link;

public class LinkTest
{
	private Link aLink;

	@Before
	public void setUp() throws Exception
	{
		aLink = new Link();
	}

	@Test
	public void testConstructorGetLangEmptyAndNumbersEmpty()
	{
		assertEquals( "", aLink.getLanguage() );
		assertEquals( 0, aLink.getLinkNumbers().size() );
	}

	@Test
	public void testSetLanguageRightWayAndGetItBack()
	{
		aLink.setLanguage( "English" );
		assertEquals( "English", aLink.getLanguage() );
	}

	@Test
	public void testSetLanguageNullGetEmptyLang()
	{
		aLink.setLanguage( null );
		assertEquals( "", aLink.getLanguage() );
	}

	@Test
	public void testSetLanguageEmptyGetEmptyLang()
	{
		aLink.setLanguage( "" );
		assertEquals( "", aLink.getLanguage() );
	}

	@Test
	public void testSetLanguageWhiteSpacesGetEmptyLang()
	{
		aLink.setLanguage( "     " );
		assertEquals( "", aLink.getLanguage() );
	}

	@Test
	public void testTryToSetLinkNumbersNullAndLanguageIsNotSetYetGetEmptyLinkNumbers()
	{
		aLink.setLinkNumbers( null );
		assertEquals( 0, aLink.getLinkNumbers().size() );
	}

	@Test
	public void testTryToSetLinkNumbersNullAndLanguageIsSetGetEmptyLinkNumbers()
	{
		aLink.setLanguage( "develoSapiens" );
		aLink.setLinkNumbers( null );
		assertEquals( 0, aLink.getLinkNumbers().size() );
	}

	@Test
	public void testTryToSetLinkNumbersEmptyLanguageIsNotSetYetGetLangNotSetException()
	{
		aLink.setLinkNumbers( new ArrayList<Integer>() );
		assertEquals( 0, aLink.getLinkNumbers().size() );
	}

	@Test
	public void testSetLinkNumbersEmptyAndLanguageIsSetGetEmptyLinkNumbers()
	{
		aLink.setLanguage( "develoSapiens" );
		int size = aLink.getLinkNumbers().size();
		aLink.setLinkNumbers( new ArrayList<Integer>() );
		assertEquals( size, aLink.getLinkNumbers().size() );
	}

	@Test
	public void testSetLinkNumbersAndLanguageIsNotSetYetGetLinkNumberWithoutLanguageException()
	{
		List<Integer> nums = new ArrayList<Integer>();
		nums.add( 100 );
		aLink.setLinkNumbers( nums );
		assertEquals( 0, aLink.getLinkNumbers().size() );
	}

	@Test
	public void testSetLinkNumbersAndLanguageIsSetGetLinkNumbersFilteredDuplicatedsDeletedZeroAndNullNotAdded()
	{
		aLink.setLanguage( "develoSapiens" );
		List<Integer> nums = new ArrayList<Integer>();
		nums.add( null );
		assertEquals( 1, nums.size() );
		aLink.setLinkNumbers( nums );
		assertEquals( 0, aLink.getLinkNumbers().size() );
		nums.add( 0 );
		assertEquals( 2, nums.size() );
		aLink.setLinkNumbers( nums );
		assertEquals( 0, aLink.getLinkNumbers().size() );
		nums.add( 100 );
		nums.add( 100 );
		assertEquals( 4, nums.size() );
		aLink.setLinkNumbers( nums );
		assertEquals( 1, aLink.getLinkNumbers().size() );
		nums.add( 101 );
		nums.add( 101 );
		assertEquals( 6, nums.size() );
		aLink.setLinkNumbers( nums );
		assertEquals( 2, aLink.getLinkNumbers().size() );
	}

	@Test
	public void testSetLinkNumbersGetItBackInRightOrder()
	{
		aLink.setLanguage( "develoSapiens" );
		List<Integer> nums = new ArrayList<Integer>();
		nums.add( 128 );
		nums.add( 38 );
		nums.add( 82 );
		nums.add( 11 );
		nums.add( 2228 );
		nums.add( 458 );
		aLink.setLinkNumbers( nums );
		assertEquals( 11, aLink.getLinkNumbers().get( 0 ).intValue() );
		assertEquals( 38, aLink.getLinkNumbers().get( 1 ).intValue() );
		assertEquals( 82, aLink.getLinkNumbers().get( 2 ).intValue() );
		assertEquals( 128, aLink.getLinkNumbers().get( 3 ).intValue() );
		assertEquals( 458, aLink.getLinkNumbers().get( 4 ).intValue() );
		assertEquals( 2228, aLink.getLinkNumbers().get( 5 ).intValue() );
	}

	@Test
	public void testAddALinkNumberNegativeGetSizeTheSameAsBefore()
	{
		aLink.setLanguage( "develoSapiens" );
		int sizeBefore = aLink.getLinkNumbers().size();
		aLink.addingALinkNumber( -1 );
		int sizeAfter = aLink.getLinkNumbers().size();
		assertEquals( sizeBefore, sizeAfter );
	}

	@Test
	public void testAddALinkNumberNULLGetSizeTheSameAsBefore()
	{
		aLink.setLanguage( "develoSapiens" );
		int sizeBefore = aLink.getLinkNumbers().size();
		aLink.addingALinkNumber( null );
		int sizeAfter = aLink.getLinkNumbers().size();
		assertEquals( sizeBefore, sizeAfter );
	}

	@Test
	public void testAddALinkNumberZeroGetSizeTheSameAsBefore()
	{
		aLink.setLanguage( "develoSapiens" );
		int sizeBefore = aLink.getLinkNumbers().size();
		aLink.addingALinkNumber( 0 );
		int sizeAfter = aLink.getLinkNumbers().size();
		assertEquals( sizeBefore, sizeAfter );
	}

	@Test
	public void testAddALinkNumberWhichAlreadyInGetSizeTheSameAsBefore()
	{
		aLink.setLanguage( "develoSapiens" );
		aLink.addingALinkNumber( 20 );
		aLink.addingALinkNumber( 200 );
		int sizeBefore = aLink.getLinkNumbers().size();
		aLink.addingALinkNumber( 200 );
		int sizeAfter = aLink.getLinkNumbers().size();
		assertEquals( sizeBefore, sizeAfter );
	}

	@Test
	public void testAddALinkNumberLanguageIsNotSetYetGetLinkNumberNotAdded()
	{
		aLink.addingALinkNumber( 20 );
		assertEquals( 0, aLink.getLinkNumbers().size() );
	}

	@Test
	public void testAddALinkNumberWhichIsnotInGetSizeIncreasedByOne()
	{
		aLink.setLanguage( "develoSapiens" );
		assertEquals( 0, aLink.getLinkNumbers().size() );
		aLink.addingALinkNumber( 20 );
		assertEquals( 1, aLink.getLinkNumbers().size() );
		aLink.addingALinkNumber( 200 );
		assertEquals( 2, aLink.getLinkNumbers().size() );
	}

	@Test
	public void testDeletingALinkNumberRightAfterConstructor()
	{
		aLink.deletingALinkNumber( 30 );
		assertEquals( 0, aLink.getLinkNumbers().size() );
	}

	@Test
	public void testDeletingALinkNumberWhichWeHaveGetListSizeDecreased()
	{
		aLink.setLanguage( "develoSapiens" );
		aLink.addingALinkNumber( 30 );
		aLink.addingALinkNumber( 300 );
		aLink.deletingALinkNumber( 30 );
		assertEquals( 1, aLink.getLinkNumbers().size() );
	}

	@Test
	public void testDeletingALinkNumberWhichWeHaveNotGetListSizeUnChanged()
	{
		aLink.setLanguage( "develoSapiens" );
		aLink.addingALinkNumber( 400 );
		aLink.addingALinkNumber( 4000 );
		aLink.deletingALinkNumber( 40 );
		assertEquals( 2, aLink.getLinkNumbers().size() );
	}

	@Test
	public void testDeletingALinkNumberNullGetListSizeUnChanged()
	{
		aLink.setLanguage( "develoSapiens" );
		aLink.addingALinkNumber( 60 );
		aLink.addingALinkNumber( 600 );
		aLink.deletingALinkNumber( null );
		assertEquals( 2, aLink.getLinkNumbers().size() );
	}

	@Test
	public void testDeletingALinkNumberZeroGetListSizeUnChanged()
	{
		aLink.setLanguage( "develoSapiens" );
		aLink.addingALinkNumber( 500 );
		aLink.addingALinkNumber( 5500 );
		aLink.deletingALinkNumber( 0 );
		assertEquals( 2, aLink.getLinkNumbers().size() );
	}

	@Test
	public void testDeletingALinkNumberNegativeGetListSizeUnChanged()
	{
		aLink.setLanguage( "develoSapiens" );
		aLink.addingALinkNumber( 60 );
		aLink.addingALinkNumber( 600 );
		aLink.deletingALinkNumber( -7 );
		assertEquals( 2, aLink.getLinkNumbers().size() );
	}

	@Test
	public void testToStringWithDefaultSettingsGetEquals()
	{
		assertEquals( "No language set yet", aLink.toString() );
	}

	@Test
	public void testToStringWithAdditionalSettingsGetEquals()
	{
		aLink.setLanguage( "English" );
		aLink.addingALinkNumber( 7 );
		aLink.addingALinkNumber( 77 );
		aLink.addingALinkNumber( 777 );
		assertEquals( "English: 7, 77, 777", aLink.toString() );
	}

	@Test
	public void testEqualsWithDefaultsTwoInstancesGetTrue()
	{
		Link link1 = new Link();
		Link link2 = new Link();
		assertTrue( link1.equals( link2 ) );
		assertTrue( link2.equals( link1 ) );
	}

	@Test
	public void testEqualsWithSameLangsGetTrue()
	{
		Link link1 = new Link();
		Link link2 = new Link();
		link1.setLanguage( "alphaLang" );
		link2.setLanguage( "alphaLang" );
		assertTrue( link1.equals( link2 ) );
		assertTrue( link2.equals( link1 ) );
	}

	@Test
	public void testEqualsWithDifferentLangsGetFalse()
	{
		Link link1 = new Link();
		Link link2 = new Link();
		link1.setLanguage( "alphaLang" );
		link2.setLanguage( "betaLang" );
		assertFalse( link1.equals( link2 ) );
		assertFalse( link2.equals( link1 ) );
	}

	@Test
	public void testEqualsWithOtherLinkNullGetFalse()
	{
		Link link1 = new Link();
		assertFalse( link1.equals( null ) );
	}

	@Test
	public void testEqualsWithDifferentLinkNumbers1GetFalse()
	{
		Link link1 = new Link();
		Link link2 = new Link();
		link1.setLanguage( "alphaLang" );
		link2.setLanguage( "alphaLang" );
		link1.addingALinkNumber( 3333 );
		link1.addingALinkNumber( 3233 );
		link1.addingALinkNumber( 3323 );
		link2.addingALinkNumber( 3333 );
		link2.addingALinkNumber( 3233 );
		link2.addingALinkNumber( 3323 );
		link2.addingALinkNumber( 4 );
		assertFalse( link1.equals( link2 ) );
	}

	@Test
	public void testEqualsWithDifferentLinkNumbers2GetFalse()
	{
		Link link1 = new Link();
		Link link2 = new Link();
		link1.setLanguage( "alphaLang" );
		link2.setLanguage( "alphaLang" );
		link1.addingALinkNumber( 3333 );
		link1.addingALinkNumber( 3233 );
		link1.addingALinkNumber( 3323 );
		link1.addingALinkNumber( 4 );
		link2.addingALinkNumber( 3333 );
		link2.addingALinkNumber( 3233 );
		link2.addingALinkNumber( 3323 );
		assertFalse( link1.equals( link2 ) );
	}

	@Test
	public void testEqualsWithSameLinkNumbersGetTrue()
	{
		Link link1 = new Link();
		Link link2 = new Link();
		link1.setLanguage( "alphaLang" );
		link2.setLanguage( "alphaLang" );
		link1.addingALinkNumber( 3333 );
		link1.addingALinkNumber( 3233 );
		link1.addingALinkNumber( 3323 );
		link2.addingALinkNumber( 3333 );
		link2.addingALinkNumber( 3233 );
		link2.addingALinkNumber( 3323 );
		assertTrue( link1.equals( link2 ) );
	}

	@Test
	public void testCompareLinks1GetTheyAreEqualReturnZero()
	{
		Link link1 = new Link();
		Link link2 = new Link();
		link1.setLanguage( "alphaLang" );
		link2.setLanguage( "alphaLang" );
		link1.addingALinkNumber( 3333 );
		link2.addingALinkNumber( 3333 );
		assertEquals( 0, link1.compareTo( link2 ) );
	}

	@Test
	public void testCompareLinks2GetTheyAreDifferentReturnNegative()
	{
		Link link1 = new Link();
		Link link2 = new Link();
		link1.setLanguage( "alphaLang" );
		link2.setLanguage( "betaLang" );
		link1.addingALinkNumber( 3333 );
		link2.addingALinkNumber( 3333 );
		assertTrue( link1.compareTo( link2 ) < 0 );
	}

	@Test
	public void testCompareLinks3GetTheyAreDifferentReturnPositive()
	{
		Link link1 = new Link();
		Link link2 = new Link();
		link1.setLanguage( "betaLang" );
		link2.setLanguage( "alphaLang" );
		link1.addingALinkNumber( 3333 );
		link2.addingALinkNumber( 3333 );
		assertTrue( link1.compareTo( link2 ) > 0 );
	}

	@Test
	public void testLinkNumbersInCorrectOrderUsingAddingALinkNumber()
	{
		Link link1 = new Link();
		link1.setLanguage( "alphaLang" );
		link1.addingALinkNumber( 567 );
		link1.addingALinkNumber( 3323 );
		link1.addingALinkNumber( 129 );
		link1.addingALinkNumber( 19 );
		link1.addingALinkNumber( 1229 );
		assertEquals( 567, (int) link1.getLinkNumbers().get( 2 ) );
		assertEquals( 3323, (int) link1.getLinkNumbers().get( 4 ) );
		assertEquals( 129, (int) link1.getLinkNumbers().get( 1 ) );
		assertEquals( 19, (int) link1.getLinkNumbers().get( 0 ) );
		assertEquals( 1229, (int) link1.getLinkNumbers().get( 3 ) );
	}
}
