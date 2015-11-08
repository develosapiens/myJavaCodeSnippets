package com.codesse.codetest.wordgame;

import java.util.ArrayList;
import java.util.List;

public class WordUsage
{
	protected static List<LetterUsage> lettersUsages;
	protected static String            myWord;

	protected static void settingLettersUsages()
	{
		lettersUsages = new ArrayList<>();
		for( int i = 0; i < myWord.length(); i++ )
		{
			lettersUsages.add( new LetterUsage( String.valueOf( myWord.charAt( i ) ) ) );
		}
	}

	public WordUsage( String word )
	{
		Tools.checkStringIfEmptyOrNull( word, "WordUsage construction, given parameter word" );
		myWord = word;
		settingLettersUsages();
	}

	protected boolean letterNotValid( String letterToCheck )
	{
		boolean result = true;
		for( LetterUsage lU : lettersUsages )
		{
			if( lU.isLetterNotUsed() )
			{
				if( lU.getLetter().equals( letterToCheck ) )
				{
					lU.setLetterUsed();
					result = false;
					break;
				}
			}
		}
		return result;
	}

	public boolean ifWordValid( String wordToCheck )
	{
		boolean result = true;
		settingLettersUsages();
		for( int i = 0; i < wordToCheck.length(); i++ )
		{
			if( letterNotValid( String.valueOf( wordToCheck.charAt( i ) ) ) )
			{
				result = false;
			}
		}
		return result;
	}
}
