package net.develoSapiens.dSconfigProvider;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import net.develoSapiens.dSloggerProvider.dSLoggerProvider;

public final class dSconfigWriter extends dSconfigUtils
{
	private Map<String, String>  innerStore                    = new HashMap<>();
	private final static boolean IS_FILE_APPEND                = false;
	private static final String  SEPARATOR                     = "=";
	private static final String  DOACTION_WITH_NULL_INNERSTORE = "WARNING! doAction invoked with null innerStore - do nothing!";

	public final void setInnerDependingOnThisMap( Map<String, String> newMap )
	{
		if( newMap == null )
		{
			innerStore = null;
		}
		else if( newMap.isEmpty() )
		{
			innerStore = new HashMap<String, String>();
		}
		else
		{
			innerStore.clear();
			for( String key : newMap.keySet() )
			{
				innerStore.put( key, newMap.get( key ) );
			}
		}
	}

	public dSconfigWriter()
	{
		this( null , null , DEFAULT_ADDITIVE );
	}

	public dSconfigWriter( String fileName )
	{
		this( null , fileName , DEFAULT_ADDITIVE );
	}

	public dSconfigWriter( dSLoggerProvider logger )
	{
		this( logger , null , DEFAULT_ADDITIVE );
	}

	public dSconfigWriter( dSLoggerProvider logger, String fileName )
	{
		this( logger , fileName , DEFAULT_ADDITIVE );
	}

	public dSconfigWriter( dSLoggerProvider logger, String fileName, boolean isAdd )
	{
		super( logger , fileName , isAdd );
	}

	@Override
	public void doAction( String fName )
	{
		if( innerStore == null )
		{
			logger.info( DOACTION_WITH_NULL_INNERSTORE );
		}
		else
		{
			Map<String, String> tMtmp = new TreeMap<>( innerStore );
			Set<Entry<String, String>> set = tMtmp.entrySet();
			Iterator<Entry<String, String>> myIterator = set.iterator();

			try( PrintWriter output = new PrintWriter( new BufferedWriter( new FileWriter( fName, IS_FILE_APPEND ) ) ); )
			{
				while( myIterator.hasNext() )
				{
					Entry<String, String> mEntry = myIterator.next();
					output.println( mEntry.getKey() + SEPARATOR + mEntry.getValue() );
				}
			}
			catch( IOException e )
			{
				logger.error( "IO Exception!" );
				e.printStackTrace();
			}
		}
	}

	public void setInnerStore( Map<String, String> map )
	{
		mergeMaps( map, innerStore );
	}

	public void setInnerStore( Map<String, String> map, boolean isAdd )
	{
		setAdditive( isAdd );
		mergeMaps( map, innerStore );
	}
}
