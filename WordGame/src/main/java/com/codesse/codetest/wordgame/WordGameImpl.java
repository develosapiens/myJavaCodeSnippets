package com.codesse.codetest.wordgame;

import java.util.ArrayList;
import java.util.List;

public class WordGameImpl implements WordGame
{
	protected final static String LETTERsSET          = "areallylongword";
	protected ValidWordsImpl      validWords;
	protected List<TopTenElement> leaderboard;
	protected WordUsage           wordUsage;
	protected final int           hallOfFameSizeLimit = 10;

	public WordGameImpl()
	{
		validWords = new ValidWordsImpl();
		leaderboard = new ArrayList<>();
		wordUsage = new WordUsage( LETTERsSET );
	}

	protected void tryToInsertIntoLeaderboard( TopTenElement elem )
	{
		if( leaderboard.isEmpty() )
		{
			leaderboard.add( elem );
		}
		else
		{
			boolean elemNotAddedYet = true;

			for( int i = 0; i < leaderboard.size(); i++ )
			{
				int resultOfcomparison = elem.compareTo( leaderboard.get( i ) );
				if( resultOfcomparison > 0 )
				{
					leaderboard.add( i, elem );
					elemNotAddedYet = false;
					break;
				}
			}
			if( leaderboard.size() > hallOfFameSizeLimit )
			{
				leaderboard.remove( hallOfFameSizeLimit );
			}
			if( elemNotAddedYet && leaderboard.size() < hallOfFameSizeLimit )
			{
				leaderboard.add( elem );
			}
		}
	}

	@Override
	public int submitWord( String playerName, String word )
	{
		int result = 0;
		synchronized( leaderboard )
		{
			if( validWords.contains( word ) && wordUsage.ifWordValid( word ) )
			{
				result = word.length();
				TopTenElement element = new TopTenElement( playerName, word );
				tryToInsertIntoLeaderboard( element );
			}
		}
		return result;
	}

	@Override
	public String getPlayerNameAtPosition( int position )
	{
		String result = null;
		if( !leaderboard.isEmpty() && position >= 0 && position < leaderboard.size() )
		{
			result = leaderboard.get( position ).getName();
		}
		return result;
	}

	@Override
	public String getWordEntryAtPosition( int position )
	{
		String result = null;
		if( !leaderboard.isEmpty() && position >= 0 && position < leaderboard.size() )
		{
			result = leaderboard.get( position ).getWord();
		}
		return result;
	}

	@Override
	public Integer getScoreAtPosition( int position )
	{
		Integer result = null;
		if( !leaderboard.isEmpty() && position >= 0 && position < leaderboard.size() )
		{
			result = leaderboard.get( position ).getScore();
		}
		return result;
	}

	public String toString()
	{
		StringBuilder sB = new StringBuilder();
		for( int i = 0; i < leaderboard.size(); i++ )
		{
			TopTenElement e = leaderboard.get( i );
			sB.append( "Position: " + i );
			sB.append( ", Player's name: " + e.getName() );
			sB.append( ", submitted word: " + e.getWord() );
			sB.append( ", score: " + e.getScore() );
			sB.append( "\n" );
		}
		return sB.toString();
	}
}
