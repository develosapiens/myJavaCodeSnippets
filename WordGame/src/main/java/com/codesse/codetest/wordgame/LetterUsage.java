package com.codesse.codetest.wordgame;

public class LetterUsage
{
	protected String  theLetter;
	protected boolean letterNotUsed;

	public LetterUsage( String letter )
	{
		Tools.checkStringIfEmptyOrNull( letter, "LetterUsage constructor. letter actual parameter" );
		theLetter = letter;
		letterNotUsed = true;
	}

	public boolean isLetterNotUsed()
	{
		return letterNotUsed;
	}

	public void setLetterUsed()
	{
		letterNotUsed = false;
	}

	public String getLetter()
	{
		return theLetter;
	}
}
