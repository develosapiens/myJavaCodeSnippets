package net.develoSapiens.dSconfigProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.develoSapiens.dSloggerProvider.dSLoggerProvider;

import org.junit.Test;

public class TestdSconfigWriter
{
	private Map<String, String> testMap      = new HashMap<String, String>();
	private String              testFileName = "prop.erties";

	private List<String> loadFile( String fileName )
	{
		List<String> lines = new ArrayList<>();
		Charset charset = Charset.forName( "UTF-8" );
		try( BufferedReader reader = Files.newBufferedReader( Paths.get( fileName ), charset ) )
		{
			String line = null;
			while( ( line = reader.readLine() ) != null )
			{
				lines.add( line );
			}
		}
		catch( IOException x )
		{
			System.err.format( "IOException: %s%n", x );
		}
		return lines;
	}

	private String delTestFile( String filename )
	{
		try
		{
			File file = new File( filename );

			if( file.delete() )
			{
				return "DELETED";
			}
			else
			{
				return "FAILED";
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return "How could this happen?";
	}

	@Test
	public void testConstructorEmpty()
	{
		delTestFile( testFileName );
		dSconfigWriter writer = new dSconfigWriter();
		writer.doAction();
		assertEquals( "DELETED", delTestFile( testFileName ) );
	}

	@Test
	public void testConstructorNullLogger()
	{
		delTestFile( testFileName );
		dSconfigWriter writer = new dSconfigWriter( (dSLoggerProvider) null );
		writer.doAction();
		assertEquals( "DELETED", delTestFile( testFileName ) );
	}

	@Test
	public void testConstructorNullString()
	{
		delTestFile( testFileName );
		dSconfigWriter writer = new dSconfigWriter( (String) null );
		writer.doAction();
		assertEquals( "DELETED", delTestFile( testFileName ) );
	}

	@Test
	public void testConstructorNullNull()
	{
		delTestFile( testFileName );
		dSconfigWriter writer = new dSconfigWriter( null, null );
		writer.doAction();
		assertEquals( "DELETED", delTestFile( testFileName ) );
	}

	@Test
	public void testConstructorNullNullFalse()
	{
		delTestFile( testFileName );
		dSconfigWriter writer = new dSconfigWriter( null, null, false );
		writer.doAction();
		assertEquals( "DELETED", delTestFile( testFileName ) );
	}

	@Test
	public void testConstructorNullNullTrue()
	{
		delTestFile( testFileName );
		dSconfigWriter writer = new dSconfigWriter( null, null, true );
		writer.doAction();
		assertEquals( "DELETED", delTestFile( testFileName ) );
	}

	@Test
	public void testConstructorString()
	{
		String tfn = "test.xml";
		delTestFile( tfn );
		dSconfigWriter writer = new dSconfigWriter( tfn );
		writer.doAction();
		assertEquals( "DELETED", delTestFile( tfn ) );
	}

	@Test
	public void testConstructorLogger()
	{
		delTestFile( testFileName );
		dSconfigWriter writer = new dSconfigWriter( new dSLoggerProvider() );
		writer.doAction();
		assertEquals( "DELETED", delTestFile( testFileName ) );
	}

	@Test
	public void testConstructorLoggerString()
	{
		String tFN = "Test.xml";
		delTestFile( tFN );
		dSconfigWriter writer = new dSconfigWriter( new dSLoggerProvider(), tFN );
		writer.doAction();
		assertEquals( "DELETED", delTestFile( tFN ) );
	}

	@Test
	public void testConstructorLoggerStringFalse()
	{
		String tFName = "Test.xml";
		delTestFile( tFName );
		dSconfigWriter writer = new dSconfigWriter( new dSLoggerProvider(), tFName, false );
		writer.doAction();
		assertEquals( "DELETED", delTestFile( tFName ) );
	}

	@Test
	public void testConstructorLoggerStringTrue()
	{
		String tFName = "Test.xml";
		delTestFile( tFName );
		dSconfigWriter writer = new dSconfigWriter( new dSLoggerProvider(), tFName, true );
		writer.doAction();
		assertEquals( "DELETED", delTestFile( tFName ) );
	}

	@Test
	public void testDoActionInnerStoreNullDoNothing()
	{
		delTestFile( testFileName );
		dSconfigWriter writer = new dSconfigWriter();
		writer.setInnerDependingOnThisMap( null );
		writer.doAction();
		assertEquals( "FAILED", delTestFile( testFileName ) );
	}

	@Test
	public void testDoActionInnerStoreDefaultEmpty()
	{
		delTestFile( testFileName );
		dSconfigWriter writer = new dSconfigWriter();
		writer.doAction();
		assertEquals( "DELETED", delTestFile( testFileName ) );
	}

	@Test
	public void testSetInnerStoreWithNullMapAndEmptyInnerStore()
	{
		delTestFile( testFileName );
		dSconfigWriter writer = new dSconfigWriter();
		writer.setInnerStore( null );
		writer.doAction();
		assertEquals( "DELETED", delTestFile( testFileName ) );
	}

	@Test
	public void testSetInnerStoreWithEmptyMapAndNonEmptyInnerStore()
	{
		delTestFile( testFileName );
		dSconfigWriter writer = new dSconfigWriter();
		Map<String, String> tMap = new HashMap<>();
		tMap.put( "AAA", "aaa" );
		tMap.put( "BBB", "bbb" );
		tMap.put( "CCC", "ccc" );
		writer.setInnerDependingOnThisMap( tMap );

		writer.setInnerStore( new HashMap<String, String>() );
		writer.doAction();

		List<String> writtenFileLines = loadFile( testFileName );
		assertTrue( writtenFileLines.size() == 0 );
		assertEquals( "DELETED", delTestFile( testFileName ) );
	}

	@Test
	public void testSetInnerStoreWithMapAndEmptyInnerStore()
	{
		delTestFile( testFileName );
		dSconfigWriter writer = new dSconfigWriter();
		Map<String, String> tMap = new HashMap<>();
		tMap.put( "AAA", "aaa" );
		tMap.put( "BBB", "bbb" );
		tMap.put( "CCC", "ccc" );

		writer.setInnerStore( tMap );
		writer.doAction();

		List<String> writtenFileLines = loadFile( testFileName );
		assertTrue( writtenFileLines.size() == 3 );
		assertEquals( "AAA=aaa", writtenFileLines.get( 0 ) );
		assertEquals( "BBB=bbb", writtenFileLines.get( 1 ) );
		assertEquals( "CCC=ccc", writtenFileLines.get( 2 ) );
		assertEquals( "DELETED", delTestFile( testFileName ) );
	}

	@Test
	public void testSetInnerStoreWithMapAndInnerStoreAdditiveDefault()
	{
		delTestFile( testFileName );
		dSconfigWriter writer = new dSconfigWriter();
		Map<String, String> tMap1 = new HashMap<>();
		Map<String, String> tMap2 = new HashMap<>();
		tMap1.put( "AAA", "aaa" );
		tMap1.put( "BBB", "bbb" );
		tMap1.put( "CCC", "ccc" );
		tMap1.put( "TTT", "ZZZ" );
		tMap1.put( "111", "111" );
		writer.setInnerStore( tMap1 );

		tMap2.put( "AAA", "alpha" );
		tMap2.put( "BBB", "bravo" );
		tMap2.put( "222", "222" );
		tMap2.put( "CCC", "ccc" );
		tMap2.put( "111", "111" );

		writer.setInnerStore( tMap2 );

		writer.doAction();

		List<String> writtenFileLines = loadFile( testFileName );
		assertEquals( 5, writtenFileLines.size() );
		assertEquals( "111=111", writtenFileLines.get( 0 ) );
		assertEquals( "222=222", writtenFileLines.get( 1 ) );
		assertEquals( "AAA=alpha", writtenFileLines.get( 2 ) );
		assertEquals( "BBB=bravo", writtenFileLines.get( 3 ) );
		assertEquals( "CCC=ccc", writtenFileLines.get( 4 ) );
		assertEquals( "DELETED", delTestFile( testFileName ) );
	}

	@Test
	public void testSetInnerStoreWithMapAndInnerStoreAdditiveFalse()
	{
		delTestFile( testFileName );
		dSconfigWriter writer = new dSconfigWriter();
		Map<String, String> tMap1 = new HashMap<>();
		Map<String, String> tMap2 = new HashMap<>();
		tMap1.put( "AAA", "aaa" );
		tMap1.put( "BBB", "bbb" );
		tMap1.put( "CCC", "ccc" );
		tMap1.put( "TTT", "ZZZ" );
		tMap1.put( "111", "111" );
		writer.setInnerStore( tMap1 );

		tMap2.put( "AAA", "alpha" );
		tMap2.put( "BBB", "bravo" );
		tMap2.put( "222", "222" );
		tMap2.put( "CCC", "ccc" );
		tMap2.put( "111", "111" );

		writer.setInnerStore( tMap2, false );

		writer.doAction();

		List<String> writtenFileLines = loadFile( testFileName );
		assertEquals( 5, writtenFileLines.size() );
		assertEquals( "111=111", writtenFileLines.get( 0 ) );
		assertEquals( "222=222", writtenFileLines.get( 1 ) );
		assertEquals( "AAA=alpha", writtenFileLines.get( 2 ) );
		assertEquals( "BBB=bravo", writtenFileLines.get( 3 ) );
		assertEquals( "CCC=ccc", writtenFileLines.get( 4 ) );
		assertEquals( "DELETED", delTestFile( testFileName ) );
		assertFalse( writer.getAdditive() );
	}

	@Test
	public void testSetInnerStoreWithMapAndInnerStoreAdditiveTrue()
	{
		delTestFile( testFileName );
		dSconfigWriter writer = new dSconfigWriter();
		Map<String, String> tMap1 = new HashMap<>();
		Map<String, String> tMap2 = new HashMap<>();
		tMap1.put( "AAA", "aaa" );
		tMap1.put( "BBB", "bbb" );
		tMap1.put( "CCC", "ccc" );
		tMap1.put( "TTT", "ZZZ" );
		tMap1.put( "111", "111" );
		writer.setInnerStore( tMap1 );

		tMap2.put( "AAA", "alpha" );
		tMap2.put( "BBB", "bravo" );
		tMap2.put( "222", "222" );
		tMap2.put( "CCC", "ccc" );
		tMap2.put( "111", "111" );

		writer.setInnerStore( tMap2, true );

		writer.doAction();

		List<String> writtenFileLines = loadFile( testFileName );
		assertEquals( 6, writtenFileLines.size() );
		assertEquals( "111=111", writtenFileLines.get( 0 ) );
		assertEquals( "222=222", writtenFileLines.get( 1 ) );
		assertEquals( "AAA=alpha", writtenFileLines.get( 2 ) );
		assertEquals( "BBB=bravo", writtenFileLines.get( 3 ) );
		assertEquals( "CCC=ccc", writtenFileLines.get( 4 ) );
		assertEquals( "TTT=ZZZ", writtenFileLines.get( 5 ) );
		assertEquals( "DELETED", delTestFile( testFileName ) );
		assertTrue( writer.getAdditive() );
	}

	@Test
	public void testSetInnerStoreWithMapAndInnerStoreSetAdditiveTrueSeparatedly()
	{
		delTestFile( testFileName );
		dSconfigWriter writer = new dSconfigWriter();
		Map<String, String> tMap1 = new HashMap<>();
		Map<String, String> tMap2 = new HashMap<>();
		tMap1.put( "AAA", "aaa" );
		tMap1.put( "BBB", "bbb" );
		tMap1.put( "CCC", "ccc" );
		tMap1.put( "TTT", "ZZZ" );
		tMap1.put( "111", "111" );
		writer.setInnerStore( tMap1 );

		tMap2.put( "AAA", "alpha" );
		tMap2.put( "BBB", "bravo" );
		tMap2.put( "222", "222" );
		tMap2.put( "CCC", "ccc" );
		tMap2.put( "111", "111" );

		writer.setAdditive( true );

		writer.setInnerStore( tMap2 );

		writer.doAction();

		List<String> writtenFileLines = loadFile( testFileName );
		assertEquals( 6, writtenFileLines.size() );
		assertEquals( "111=111", writtenFileLines.get( 0 ) );
		assertEquals( "222=222", writtenFileLines.get( 1 ) );
		assertEquals( "AAA=alpha", writtenFileLines.get( 2 ) );
		assertEquals( "BBB=bravo", writtenFileLines.get( 3 ) );
		assertEquals( "CCC=ccc", writtenFileLines.get( 4 ) );
		assertEquals( "TTT=ZZZ", writtenFileLines.get( 5 ) );
		assertEquals( "DELETED", delTestFile( testFileName ) );
		assertTrue( writer.getAdditive() );
	}
}
