package com.codesse.codetest.wordgame;

public class TopTenElement implements Comparable<TopTenElement>
{
	protected int    score;
	protected String playerName;
	protected String word;

	public TopTenElement( String playerName, String word )
	{
		Tools.checkStringIfEmptyOrNull( playerName, "Player's name" );
		Tools.checkStringIfEmptyOrNull( word, "Player'solution word" );
		this.playerName = playerName;
		this.word = word;
		score = word.length();
	}

	public int getScore()
	{
		return score;
	}

	public String getName()
	{
		return playerName;
	}

	public String getWord()
	{
		return word;
	}

	@Override
	public int compareTo( TopTenElement toCompare )
	{
		return score - toCompare.getScore();
	}
}
