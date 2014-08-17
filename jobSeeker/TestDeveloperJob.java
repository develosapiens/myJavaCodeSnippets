package jobSeeker;

import static jobSeeker.Languages.CSharp;
import static jobSeeker.Languages.JAVA;
import static org.junit.Assert.*;

import org.junit.Test;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class TestDeveloperJob 
{
	@Test(expected=IllegalArgumentException.class)
	public void testGetJobTypeNull4Langs() 
	{
		@SuppressWarnings(value = { "unused" })
		DeveloperJob dJ = new DeveloperJob(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetJobTypeNoLangs() 
	{
		@SuppressWarnings(value = { "unused" })
		DeveloperJob dJ = new DeveloperJob(new Languages[]{ });
	}

	@Test
	public void testGetJobType1LangCS() 
	{
		DeveloperJob dJ = new DeveloperJob(new Languages[]{ CSharp });
		assertEquals("Computer Programmer", dJ.getJobType());
	}

	@Test
	public void testGetJobType1LangJava() 
	{
		DeveloperJob dJ = new DeveloperJob(new Languages[]{ JAVA });
		assertEquals("Computer Programmer", dJ.getJobType());
	}

	@Test
	public void testGetJobType2Langs() 
	{
		DeveloperJob dJ = new DeveloperJob(new Languages[]{ JAVA, CSharp });
		assertEquals("Computer Programmer", dJ.getJobType());
	}

	@Test
	public void testToString2Langs() 
	{
		DeveloperJob dJ = new DeveloperJob(new Languages[]{ JAVA, CSharp });
		assertEquals("I am a Computer Programmer\nOther infos:\nNeeded knowledge: Java, C#", dJ.toString());
	}

	@Test
	public void testToString1LangCS() 
	{
		DeveloperJob dJ = new DeveloperJob(new Languages[]{ CSharp });
		assertEquals("I am a Computer Programmer\nOther infos:\nNeeded knowledge: C#", dJ.toString());
	}

	@Test
	public void testToString1LangJava() 
	{
		DeveloperJob dJ = new DeveloperJob(new Languages[]{ JAVA });
		assertEquals("I am a Computer Programmer\nOther infos:\nNeeded knowledge: Java", dJ.toString());
	}
	
	@Test(expected=NotImplementedException.class)
	public void testSetKnownLanguagesBoth()
	{
		DeveloperJob dJ = new DeveloperJob(new Languages[]{ JAVA });
		dJ.setKnownLanguages(new Languages[]{ CSharp, JAVA});
	}
	
	@Test(expected=NotImplementedException.class)
	public void testSetKnownLanguagesJava()
	{
		DeveloperJob dJ = new DeveloperJob(new Languages[]{ JAVA });
		dJ.setKnownLanguages(new Languages[]{ JAVA});
	}
	
	@Test(expected=NotImplementedException.class)
	public void testSetKnownLanguagesCS()
	{
		DeveloperJob dJ = new DeveloperJob(new Languages[]{ JAVA });
		dJ.setKnownLanguages(new Languages[]{ CSharp });
	}
	
	@Test(expected=NotImplementedException.class)
	public void testSetKnownLanguagesToNull()
	{
		DeveloperJob dJ = new DeveloperJob(new Languages[]{ JAVA });
		dJ.setKnownLanguages( null );
	}

	@Test(expected=NotImplementedException.class)
	public void testSetKnownLanguagesToEmptyLangArray()
	{
		DeveloperJob dJ = new DeveloperJob(new Languages[]{ JAVA });
		dJ.setKnownLanguages( new Languages[]{ } );
	}
	
	@Test
	public void testGetKnownLanguagesBothJavaFirst()
	{
		DeveloperJob dJ = new DeveloperJob(new Languages[]{ JAVA, CSharp });
		Languages[] langArr = dJ.getKnownLanguages();
		assertEquals("Java", langArr[0].toString());
		assertEquals("C#", langArr[1].toString());
		assertEquals(2, langArr.length);
	}

	@Test
	public void testGetKnownLanguagesBothCSFirst()
	{
		DeveloperJob dJ = new DeveloperJob(new Languages[]{ CSharp, JAVA });
		Languages[] langArr = dJ.getKnownLanguages();
		assertEquals("C#", langArr[0].toString());
		assertEquals("Java", langArr[1].toString());
		assertEquals(2, langArr.length);
	}
	
	@Test
	public void testGetKnownLanguagesCS()
	{
		DeveloperJob dJ = new DeveloperJob(new Languages[]{ CSharp });
		Languages[] langArr = dJ.getKnownLanguages();
		assertEquals("C#", langArr[0].toString());
		assertEquals(1, langArr.length);
	}

	@Test
	public void testGetKnownLanguagesJava()
	{
		DeveloperJob dJ = new DeveloperJob(new Languages[]{ JAVA });
		Languages[] langArr = dJ.getKnownLanguages();
		assertEquals("Java", langArr[0].toString());
		assertEquals(1, langArr.length);
	}
}
