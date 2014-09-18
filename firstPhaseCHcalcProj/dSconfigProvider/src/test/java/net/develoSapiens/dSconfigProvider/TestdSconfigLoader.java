package net.develoSapiens.dSconfigProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.develoSapiens.dSloggerProvider.dSLoggerProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestdSconfigLoader
{
	private String testFileName = "test.file";

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

	@Before
	public void creatingFileForInput()
	{
		Map<String, String> testMap = new HashMap<>();
		dSconfigWriter writer = new dSconfigWriter();

		testMap.put( "AAA", "alpha" );
		testMap.put( "BBB", "bravo" );
		testMap.put( "CCC", "charlie" );
		testMap.put( "444", "delta" );

		writer.setInnerStore( testMap );
		writer.doAction( testFileName );
	}

	@After
	public void tidyingUp()
	{
		delTestFile( testFileName );
	}

	@Test
	public void testConstructorEmpty()
	{
		Map<String, String> testMap = new HashMap<>();
		dSconfigWriter writer = new dSconfigWriter();

		testMap.put( "AAA", "alpha" );
		testMap.put( "BBB", "bravo" );
		testMap.put( "CCC", "charlie" );
		testMap.put( "444", "delta" );

		writer.setInnerStore( testMap );
		writer.doAction();

		dSconfigLoader loader = new dSconfigLoader();
		loader.doAction();

		String result = delTestFile( "prop.erties" );
		assertEquals( "DELETED", result );
	}

	@Test
	public void testConstructorNullLoggerAndGetInnerStoreEmpty()
	{
		Map<String, String> testMap = new HashMap<>();
		dSconfigWriter writer = new dSconfigWriter();

		String fName = "constructor.test1";
		testMap.put( "AAA", "alpha" );
		testMap.put( "BBB", "bravo" );
		testMap.put( "CCC", "charlie" );
		testMap.put( "444", "delta" );

		writer.setInnerStore( testMap );
		writer.doAction( fName );

		dSconfigLoader loader = new dSconfigLoader( (dSLoggerProvider) null );
		loader.doAction( fName );

		testMap = loader.getInnerStore();

		assertEquals( "alpha", testMap.get( "AAA" ) );
		assertEquals( "bravo", testMap.get( "BBB" ) );
		assertEquals( "charlie", testMap.get( "CCC" ) );
		assertEquals( "delta", testMap.get( "444" ) );

		delTestFile( fName );
	}

	@Test
	public void testConstructorNullStringAndGetInnerStoreEmpty()
	{
		Map<String, String> testMap = new HashMap<>();
		dSconfigWriter writer = new dSconfigWriter();

		String fName = "prop.erties";
		testMap.put( "AAA", "aaaa" );
		testMap.put( "BBB", "bbbbb" );
		testMap.put( "CCC", "charlie" );
		testMap.put( "444", "delta" );

		writer.setInnerStore( testMap );
		writer.doAction( fName );

		dSconfigLoader loader = new dSconfigLoader( (String) null );
		loader.doAction( fName );

		testMap = loader.getInnerStore();

		assertEquals( "aaaa", testMap.get( "AAA" ) );
		assertEquals( "bbbbb", testMap.get( "BBB" ) );
		assertEquals( "charlie", testMap.get( "CCC" ) );
		assertEquals( "delta", testMap.get( "444" ) );

		delTestFile( fName );
	}

	@Test
	public void testConstructorNullNullAndGetInnerStoreEmpty()
	{
		Map<String, String> testMap = new HashMap<>();
		dSconfigWriter writer = new dSconfigWriter();

		String fName = "prop.erties";
		testMap.put( "AAA", "aaaa" );
		testMap.put( "BBB", "bbbbb" );
		testMap.put( "CCC", "charlie" );
		testMap.put( "444", "delta" );

		writer.setInnerStore( testMap );
		writer.doAction( fName );

		dSconfigLoader loader = new dSconfigLoader( null, null );
		loader.doAction( fName );

		testMap = loader.getInnerStore();

		assertEquals( "aaaa", testMap.get( "AAA" ) );
		assertEquals( "bbbbb", testMap.get( "BBB" ) );
		assertEquals( "charlie", testMap.get( "CCC" ) );
		assertEquals( "delta", testMap.get( "444" ) );

		delTestFile( fName );
	}

	@Test
	public void testConstructorNullNullFalseAndGetInnerStoreEmpty()
	{
		Map<String, String> testMap = new HashMap<>();
		dSconfigWriter writer = new dSconfigWriter();

		String fName = "prop.erties";
		testMap.put( "AAA", "aaaa" );
		testMap.put( "BBB", "bbbbb" );
		testMap.put( "CCC", "charlie" );
		testMap.put( "444", "delta" );

		writer.setInnerStore( testMap );
		writer.doAction( fName );

		dSconfigLoader loader = new dSconfigLoader( null, null, false );
		loader.doAction( fName );

		testMap = loader.getInnerStore();

		assertEquals( "aaaa", testMap.get( "AAA" ) );
		assertEquals( "bbbbb", testMap.get( "BBB" ) );
		assertEquals( "charlie", testMap.get( "CCC" ) );
		assertEquals( "delta", testMap.get( "444" ) );

		delTestFile( fName );
	}

	@Test
	public void testConstructorNullNullTrueAndGetInnerStoreEmpty()
	{
		Map<String, String> testMap = new HashMap<>();
		dSconfigWriter writer = new dSconfigWriter();

		String fName = "prop.erties";
		testMap.put( "AAA", "aaaa" );
		testMap.put( "BBB", "bbbbb" );
		testMap.put( "CCC", "charlie" );
		testMap.put( "444", "delta" );

		writer.setInnerStore( testMap );
		writer.doAction( fName );

		dSconfigLoader loader = new dSconfigLoader( null, null, true );
		loader.doAction( fName );

		testMap = loader.getInnerStore();

		assertEquals( "aaaa", testMap.get( "AAA" ) );
		assertEquals( "bbbbb", testMap.get( "BBB" ) );
		assertEquals( "charlie", testMap.get( "CCC" ) );
		assertEquals( "delta", testMap.get( "444" ) );

		delTestFile( fName );
	}

	@Test
	public void testConstructorStringAndGetInnerStoreEmpty()
	{
		Map<String, String> testMap = new HashMap<>();
		dSconfigWriter writer = new dSconfigWriter();

		String fName = "propX.erties";
		testMap.put( "AAA", "aaaa" );
		testMap.put( "BBB", "bbbbb" );
		testMap.put( "CCC", "charlie" );
		testMap.put( "444", "delta" );

		writer.setInnerStore( testMap );
		writer.doAction( fName );

		dSconfigLoader loader = new dSconfigLoader( fName );
		loader.doAction( fName );

		testMap = loader.getInnerStore();

		assertEquals( "aaaa", testMap.get( "AAA" ) );
		assertEquals( "bbbbb", testMap.get( "BBB" ) );
		assertEquals( "charlie", testMap.get( "CCC" ) );
		assertEquals( "delta", testMap.get( "444" ) );

		delTestFile( fName );
	}

	@Test
	public void testConstructorLoggerAndGetInnerStoreEmpty()
	{
		Map<String, String> testMap = new HashMap<>();
		dSconfigWriter writer = new dSconfigWriter();

		String fName = "propY.erties";
		testMap.put( "AAA", "aaaa" );
		testMap.put( "BBB", "bbbbb" );
		testMap.put( "CCC", "charlie" );
		testMap.put( "444", "delta" );

		writer.setInnerStore( testMap );
		writer.doAction( fName );

		dSconfigLoader loader = new dSconfigLoader( new dSLoggerProvider() );
		loader.doAction( fName );

		testMap = loader.getInnerStore();

		assertEquals( "aaaa", testMap.get( "AAA" ) );
		assertEquals( "bbbbb", testMap.get( "BBB" ) );
		assertEquals( "charlie", testMap.get( "CCC" ) );
		assertEquals( "delta", testMap.get( "444" ) );

		delTestFile( fName );
	}

	@Test
	public void testConstructorLoggerStringAndGetInnerStoreEmpty()
	{
		Map<String, String> testMap = new HashMap<>();
		dSconfigWriter writer = new dSconfigWriter();

		String fName = "propXY.erties";
		testMap.put( "AAA", "aaaa" );
		testMap.put( "BBB", "bbbbb" );
		testMap.put( "CCC", "charlie" );
		testMap.put( "444", "delta" );

		writer.setInnerStore( testMap );
		writer.doAction( fName );

		dSconfigLoader loader = new dSconfigLoader( new dSLoggerProvider(), fName );
		loader.doAction( fName );

		testMap = loader.getInnerStore();

		assertEquals( "aaaa", testMap.get( "AAA" ) );
		assertEquals( "bbbbb", testMap.get( "BBB" ) );
		assertEquals( "charlie", testMap.get( "CCC" ) );
		assertEquals( "delta", testMap.get( "444" ) );

		delTestFile( fName );
	}

	@Test
	public void testConstructorLoggerStringFalseAndGetInnerStoreEmpty()
	{
		Map<String, String> testMap = new HashMap<>();
		dSconfigWriter writer = new dSconfigWriter();

		String fName = "propXZUY.erties";
		testMap.put( "AAA", "aaaa" );
		testMap.put( "BBB", "bbbbb" );
		testMap.put( "CCC", "charlie" );
		testMap.put( "444", "delta" );

		writer.setInnerStore( testMap );
		writer.doAction( fName );

		dSconfigLoader loader = new dSconfigLoader( new dSLoggerProvider(), fName, false );
		loader.doAction( fName );

		testMap = loader.getInnerStore();

		assertEquals( "aaaa", testMap.get( "AAA" ) );
		assertEquals( "bbbbb", testMap.get( "BBB" ) );
		assertEquals( "charlie", testMap.get( "CCC" ) );
		assertEquals( "delta", testMap.get( "444" ) );

		delTestFile( fName );
	}

	@Test
	public void testConstructorLoggerStringTrueAndGetInnerStoreEmpty()
	{
		Map<String, String> testMap = new HashMap<>();
		dSconfigWriter writer = new dSconfigWriter();

		String fName = "propXZUY.erties";
		testMap.put( "AAA", "aaaa" );
		testMap.put( "BBB", "bbbbb" );
		testMap.put( "CCC", "charlie" );
		testMap.put( "444", "delta" );

		writer.setInnerStore( testMap );
		writer.doAction( fName );

		dSconfigLoader loader = new dSconfigLoader( new dSLoggerProvider(), fName, true );
		loader.doAction( fName );

		testMap = loader.getInnerStore();

		assertEquals( "aaaa", testMap.get( "AAA" ) );
		assertEquals( "bbbbb", testMap.get( "BBB" ) );
		assertEquals( "charlie", testMap.get( "CCC" ) );
		assertEquals( "delta", testMap.get( "444" ) );

		delTestFile( fName );
	}

	@Test
	public void testDoActionNullToInnerStoreDoNothing()
	{
		dSconfigLoader loader = new dSconfigLoader();
		loader.setInnerDependingOnThisMap( null );
		Map<String, String> testMap1 = loader.getInnerStore();
		assertNotNull( testMap1 );
	}

	@Test
	public void testDoActionDefaultInnerStoreDoNothing()
	{
		dSconfigLoader loader = new dSconfigLoader();
		Map<String, String> testMap1 = loader.getInnerStore();
		assertNotNull( testMap1 );
	}

	@Test
	public void testGetInnerStoreWithNullMapAndEmptyInnerStore()
	{
		dSconfigLoader loader = new dSconfigLoader();
		Map<String, String> testMap1 = loader.getInnerStore( null );
		assertNotNull( testMap1 );
	}

	@Test
	public void testGetInnerStoreWithEmptyMapAndNonEmptyInnerStore()
	{
		dSconfigLoader loader = new dSconfigLoader();
		Map<String, String> testMap1 = new HashMap<>();

		testMap1.put( "AAA", "aaaa" );
		testMap1.put( "BBB", "bbbbb" );
		testMap1.put( "CCC", "charlie" );
		testMap1.put( "555", "delta" );

		loader.setInnerDependingOnThisMap( testMap1 );
		Map<String, String> testMap2 = loader.getInnerStore( new HashMap<String, String>() );

		assertEquals( "aaaa", testMap2.get( "AAA" ) );
		assertEquals( "bbbbb", testMap2.get( "BBB" ) );
		assertEquals( "charlie", testMap2.get( "CCC" ) );
		assertEquals( "delta", testMap2.get( "555" ) );
	}

	@Test
	public void testGetInnerStoreWithMapAndEmptyInnerStore()
	{
		dSconfigLoader loader = new dSconfigLoader();
		Map<String, String> testMap = new HashMap<>();
		testMap.put( "AAA", "aaaa" );
		testMap.put( "BBB", "bbbbb" );
		testMap.put( "CCC", "charlie" );
		testMap.put( "555", "delta" );

		Map<String, String> testMap2 = loader.getInnerStore( testMap );
		assertTrue( testMap2.isEmpty() );
	}

	@Test
	public void testGetInnerStoreWithMapAndInnerStoreAdditiveDefault()
	{
		dSconfigLoader loader = new dSconfigLoader();
		Map<String, String> testMap1 = new HashMap<>();

		testMap1.put( "AAA", "aaaa" );
		testMap1.put( "BBB", "bbbbb" );
		testMap1.put( "CCC", "charlie" );
		testMap1.put( "555", "delta" );

		loader.setInnerDependingOnThisMap( testMap1 );

		Map<String, String> testMap2 = new HashMap<>();

		testMap2.put( "AAA", "alpha" );
		testMap2.put( "BBB", "bravo" );
		testMap2.put( "CCC", "charlie" );
		testMap2.put( "111", "1111" );

		Map<String, String> testMap3 = loader.getInnerStore( testMap2 );

		assertEquals( "aaaa", testMap3.get( "AAA" ) );
		assertEquals( "bbbbb", testMap3.get( "BBB" ) );
		assertEquals( "charlie", testMap3.get( "CCC" ) );
		assertEquals( "delta", testMap3.get( "555" ) );
		assertNull( testMap3.get( "111" ) );
	}

	@Test
	public void testGetInnerStoreWithMapAndInnerStoreAdditiveFalse()
	{
		dSconfigLoader loader = new dSconfigLoader();
		Map<String, String> testMap1 = new HashMap<>();

		testMap1.put( "AAA", "aaaa" );
		testMap1.put( "BBB", "bbbbb" );
		testMap1.put( "CCC", "charlie" );
		testMap1.put( "555", "delta" );

		loader.setInnerDependingOnThisMap( testMap1 );

		Map<String, String> testMap2 = new HashMap<>();

		testMap2.put( "AAA", "alpha" );
		testMap2.put( "BBB", "bravo" );
		testMap2.put( "CCC", "charlie" );
		testMap2.put( "111", "1111" );

		Map<String, String> testMap3 = loader.getInnerStore( testMap2, false );

		assertEquals( "aaaa", testMap3.get( "AAA" ) );
		assertEquals( "bbbbb", testMap3.get( "BBB" ) );
		assertEquals( "charlie", testMap3.get( "CCC" ) );
		assertEquals( "delta", testMap3.get( "555" ) );
		assertNull( testMap3.get( "111" ) );
	}

	@Test
	public void testGetInnerStoreWithMapAndInnerStoreAdditiveTrue()
	{
		dSconfigLoader loader = new dSconfigLoader();
		Map<String, String> testMap1 = new HashMap<>();

		testMap1.put( "AAA", "aaaa" );
		testMap1.put( "BBB", "bbbbb" );
		testMap1.put( "CCC", "charlie" );
		testMap1.put( "555", "delta" );

		loader.setInnerDependingOnThisMap( testMap1 );

		Map<String, String> testMap2 = new HashMap<>();

		testMap2.put( "AAA", "alpha" );
		testMap2.put( "BBB", "bravo" );
		testMap2.put( "CCC", "charlie" );
		testMap2.put( "111", "1111" );

		Map<String, String> testMap3 = loader.getInnerStore( testMap2, true );

		assertEquals( "aaaa", testMap3.get( "AAA" ) );
		assertEquals( "bbbbb", testMap3.get( "BBB" ) );
		assertEquals( "charlie", testMap3.get( "CCC" ) );
		assertEquals( "delta", testMap3.get( "555" ) );
		assertEquals( "1111", testMap3.get( "111" ) );
	}

	@Test
	public void testGetInnerStoreWithMapAndInnerStoreSetAdditiveTrueSeparatedly()
	{
		dSconfigLoader loader = new dSconfigLoader();
		Map<String, String> testMap1 = new HashMap<>();

		testMap1.put( "AAA", "aaaa" );
		testMap1.put( "BBB", "bbbbb" );
		testMap1.put( "CCC", "charlie" );
		testMap1.put( "555", "delta" );

		loader.setInnerDependingOnThisMap( testMap1 );

		Map<String, String> testMap2 = new HashMap<>();

		testMap2.put( "AAA", "alpha" );
		testMap2.put( "BBB", "bravo" );
		testMap2.put( "CCC", "charlie" );
		testMap2.put( "111", "1111" );

		loader.setAdditive( true );
		Map<String, String> testMap3 = loader.getInnerStore( testMap2 );

		assertEquals( "aaaa", testMap3.get( "AAA" ) );
		assertEquals( "bbbbb", testMap3.get( "BBB" ) );
		assertEquals( "charlie", testMap3.get( "CCC" ) );
		assertEquals( "delta", testMap3.get( "555" ) );
		assertEquals( "1111", testMap3.get( "111" ) );
	}
}
