package com.codesse.codetest.wordgame;

public class Tools
{
	public static void checkStringIfEmptyOrNull( String toCheck, String objectOfMessage )
	{
		if( toCheck == null )
		{
			throw new IllegalArgumentException( objectOfMessage + " must not be NULL!" );
		}
		if( toCheck.isEmpty() )
		{
			throw new IllegalArgumentException( objectOfMessage + " must not be empty!" );
		}
	}
}
