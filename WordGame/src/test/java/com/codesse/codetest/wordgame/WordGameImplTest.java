package com.codesse.codetest.wordgame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class WordGameImplTest
{
	protected WordGameImpl myWordGame;

	@Before
	public void setUp()
	{
		myWordGame = new WordGameImpl();
	}

	@Test
	public void testScoreWord()
	{
		int score = 0;
		score = myWordGame.submitWord( "Player one", "word" );
		assertEquals( 4, score );
	}

	@Test
	public void testWordGetScore()
	{
		int score = 0;
		myWordGame.submitWord( "Player one", "word" );
		score = myWordGame.getScoreAtPosition( 0 );
		assertEquals( 4, score );
	}

	@Test
	public void testDeveloSapiens()
	{
		int score = 0;
		score = myWordGame.submitWord( "Player one", "develoSapiens" );
		assertEquals( 0, score );
	}

	@Test
	public void testGlly()
	{
		int score;
		score = myWordGame.submitWord( "Player one", "glly" );
		assertEquals( 0, score );
		assertTrue( myWordGame.leaderboard.isEmpty() );
	}

	@Test
	public void testAdder()
	{
		int score;
		score = myWordGame.submitWord( "Player one", "adder" );
		assertEquals( 0, score );
		assertTrue( myWordGame.leaderboard.isEmpty() );
	}

	@Test
	public void testTale()
	{
		int score;
		score = myWordGame.submitWord( "Player one", "tale" );
		assertEquals( 0, score );
		assertTrue( myWordGame.leaderboard.isEmpty() );
	}

	@Test
	public void testWoolly()
	{
		int score;
		score = myWordGame.submitWord( "Player one", "woolly" );
		assertEquals( 6, score );
		assertFalse( myWordGame.leaderboard.isEmpty() );
	}

	@Test
	public void testWord()
	{
		int score;
		score = myWordGame.submitWord( "Player one", "word" );
		assertEquals( 4, score );
		assertFalse( myWordGame.leaderboard.isEmpty() );
	}

	@Test
	public void testAll()
	{
		int score;
		score = myWordGame.submitWord( "Player one", "all" );
		assertEquals( 3, score );
		assertFalse( myWordGame.leaderboard.isEmpty() );
	}

	@Test
	public void testHallOfFameTableSizeIsLessThanMaxSize()
	{
		myWordGame.submitWord( "Player two", "word" );
		myWordGame.submitWord( "Player one", "glly" );
		myWordGame.submitWord( "Player one", "woolly" );
		myWordGame.submitWord( "Player three", "all" );
		myWordGame.submitWord( "Player one", "world" );
		myWordGame.submitWord( "Player four", "aardvark" );
		myWordGame.submitWord( "Player two", "aardvarks" );
		myWordGame.submitWord( "Player three", "aardwolf" );
		myWordGame.submitWord( "Player one", "abandoned" );
		myWordGame.submitWord( "Player one", "develosapiens" );
		myWordGame.submitWord( "Player two", "abbreviation" );
		myWordGame.submitWord( "Player four", "really" );
		myWordGame.submitWord( "Player four", "alley" );
		myWordGame.submitWord( "Player three", "wrong" );
		myWordGame.submitWord( "Player three", "dreary" );
		myWordGame.submitWord( "Player four", "growled" );
		myWordGame.submitWord( "Player three", "ordinary" );
		assertEquals( 9, myWordGame.leaderboard.size() );
		StringBuilder sB = new StringBuilder();
		sB.append( "Position: 0, Player's name: Player four, submitted word: growled, score: 7\n" );
		sB.append( "Position: 1, Player's name: Player one, submitted word: woolly, score: 6\n" );
		sB.append( "Position: 2, Player's name: Player four, submitted word: really, score: 6\n" );
		sB.append( "Position: 3, Player's name: Player three, submitted word: dreary, score: 6\n" );
		sB.append( "Position: 4, Player's name: Player one, submitted word: world, score: 5\n" );
		sB.append( "Position: 5, Player's name: Player four, submitted word: alley, score: 5\n" );
		sB.append( "Position: 6, Player's name: Player three, submitted word: wrong, score: 5\n" );
		sB.append( "Position: 7, Player's name: Player two, submitted word: word, score: 4\n" );
		sB.append( "Position: 8, Player's name: Player three, submitted word: all, score: 3\n" );
		assertTrue( myWordGame.toString().equals( sB.toString() ) );
	}

	@Test
	public void testHallOfFameTableSizeIsMaxSize()
	{
		assertNull( myWordGame.getPlayerNameAtPosition( 0 ) );
		assertNull( myWordGame.getWordEntryAtPosition( 0 ) );
		assertNull( myWordGame.getScoreAtPosition( 0 ) );
		int score;
		score = myWordGame.submitWord( "Player two", "word" );
		assertEquals( 4, score );
		score = myWordGame.submitWord( "Player one", "glly" );
		assertEquals( 0, score );
		score = myWordGame.submitWord( "Player one", "woolly" );
		assertEquals( 6, score );
		score = myWordGame.submitWord( "Player three", "all" );
		assertEquals( 3, score );
		assertEquals( "Player three", myWordGame.getPlayerNameAtPosition( 2 ) );
		assertEquals( "all", myWordGame.getWordEntryAtPosition( 2 ) );
		score = myWordGame.submitWord( "Player one", "world" );
		assertEquals( 5, score );
		score = myWordGame.submitWord( "Player four", "aardvark" );
		assertEquals( 0, score );
		score = myWordGame.submitWord( "Player two", "aardvarks" );
		assertEquals( 0, score );
		score = myWordGame.submitWord( "Player three", "aardwolf" );
		assertEquals( 0, score );
		score = myWordGame.submitWord( "Player one", "abandoned" );
		assertEquals( 0, score );
		score = myWordGame.submitWord( "Player one", "develosapiens" );
		assertEquals( 0, score );
		score = myWordGame.submitWord( "Player two", "abbreviation" );
		assertEquals( 0, score );
		score = myWordGame.submitWord( "Player three", "loanword" );
		assertEquals( 8, score );
		score = myWordGame.submitWord( "Player four", "really" );
		assertEquals( 6, score );
		score = myWordGame.submitWord( "Player four", "alley" );
		assertEquals( 5, score );
		score = myWordGame.submitWord( "Player three", "wrong" );
		assertEquals( 5, score );
		score = myWordGame.submitWord( "Player three", "dreary" );
		assertEquals( 6, score );
		score = myWordGame.submitWord( "Player four", "growled" );
		assertEquals( 7, score );
		score = myWordGame.submitWord( "Player three", "ordinary" );
		assertEquals( 0, score );
		score = myWordGame.submitWord( "Player one", "rally" );
		assertEquals( 5, score );
		score = myWordGame.submitWord( "Player two", "road" );
		assertEquals( 4, score );
		score = myWordGame.submitWord( "Player three", "roar" );
		assertEquals( 4, score );
		score = myWordGame.submitWord( "Player four", "real" );
		assertEquals( 4, score );
		assertEquals( 10, myWordGame.leaderboard.size() );
		StringBuilder sB = new StringBuilder();
		sB.append( "Position: 0, Player's name: Player three, submitted word: loanword, score: 8\n" );
		sB.append( "Position: 1, Player's name: Player four, submitted word: growled, score: 7\n" );
		sB.append( "Position: 2, Player's name: Player one, submitted word: woolly, score: 6\n" );
		sB.append( "Position: 3, Player's name: Player four, submitted word: really, score: 6\n" );
		sB.append( "Position: 4, Player's name: Player three, submitted word: dreary, score: 6\n" );
		sB.append( "Position: 5, Player's name: Player one, submitted word: world, score: 5\n" );
		sB.append( "Position: 6, Player's name: Player four, submitted word: alley, score: 5\n" );
		sB.append( "Position: 7, Player's name: Player three, submitted word: wrong, score: 5\n" );
		sB.append( "Position: 8, Player's name: Player one, submitted word: rally, score: 5\n" );
		sB.append( "Position: 9, Player's name: Player two, submitted word: word, score: 4\n" );
		assertTrue( myWordGame.toString().equals( sB.toString() ) );
		assertEquals( "dreary", myWordGame.getWordEntryAtPosition( 4 ) );
		assertEquals( "Player three", myWordGame.getPlayerNameAtPosition( 4 ) );
		assertNull( myWordGame.getPlayerNameAtPosition( 10 ) );
		assertNull( myWordGame.getPlayerNameAtPosition( -1 ) );
		assertNull( myWordGame.getWordEntryAtPosition( 10 ) );
		assertNull( myWordGame.getWordEntryAtPosition( -1 ) );
		assertNull( myWordGame.getScoreAtPosition( 10 ) );
		assertNull( myWordGame.getScoreAtPosition( -1 ) );
		assertEquals( 8, (int) myWordGame.getScoreAtPosition( 0 ) );
	}
}
