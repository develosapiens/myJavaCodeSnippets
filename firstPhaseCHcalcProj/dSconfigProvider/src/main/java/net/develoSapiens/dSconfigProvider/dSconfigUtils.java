package net.develoSapiens.dSconfigProvider;

import java.util.HashMap;
import java.util.Map;

import net.develoSapiens.dSloggerProvider.dSLoggerProvider;

abstract class dSconfigUtils
{
	protected final static String DEFAULT_PROPFILENAME             = "prop.erties";
	protected static boolean      DEFAULT_ADDITIVE                 = false;
	protected dSLoggerProvider    logger                           = null;
	protected String              fileName                         = DEFAULT_PROPFILENAME;
	protected boolean             isAdditive                       = DEFAULT_ADDITIVE;

	private static final String   GIVEN_FILENAME_WAS_EMPTY_WARNING = "WARNING! Method was called with empty string filename argument! Filename is: "
	                                                                   + DEFAULT_PROPFILENAME + " now!";
	private static final String   GIVEN_FILENAME_WAS_NULL_WARNING  = "WARNING! Method was called with null filename argument! Filename is: "
	                                                                   + DEFAULT_PROPFILENAME + " now!";
	private static final String   GIVEN_LOGGER_WAS_NULL_WARNING    = "WARNING! Constructor was called with null logger argument! I instantiated a new one!";
	private static final String   MAP1_WAS_NULL_WARNING            = "Map1 was null, created a new one.";
	private static final String   MAP2_WAS_NULL_WARNING            = "Map2 was null, created a new one.";

	public final void doAction()
	{
		doAction( fileName );
	}

	abstract public void doAction( String fileName );

	protected Map<String, String> mergeMaps( Map<String, String> map1, Map<String, String> map2 )
	{
		if( map1 == null )
		{
			map1 = new HashMap<String, String>();
			logger.info( MAP1_WAS_NULL_WARNING );
		}
		if( map2 == null )
		{
			map2 = new HashMap<String, String>();
			logger.info( MAP2_WAS_NULL_WARNING );
		}

		Map<String, String> tmpMap = new HashMap<>();

		for( String aKey : map1.keySet() )
		{
			tmpMap.put( aKey, map1.get( aKey ) );
		}

		if( isAdditive )
		{
			for( String keyInMap2 : map2.keySet() )
			{
				if( !map1.containsKey( keyInMap2 ) )
				{
					tmpMap.put( keyInMap2, map2.get( keyInMap2 ) );
				}
			}
		}

		map2.clear();
		for( String keys : tmpMap.keySet() )
		{
			map2.put( keys, tmpMap.get( keys ) );
		}
		return map2;
	}

	public dSconfigUtils( dSLoggerProvider logr, String fN, boolean addOrNot )
	{
		logger = checkLogger( logr );
		fileName = filenameCheckNotNullNotEmpty( fN, logger );
		isAdditive = addOrNot;
	}

	protected final static String filenameCheckNotNullNotEmpty( String fN, dSLoggerProvider logger )
	{
		if( fN == null )
		{
			logger.debug( GIVEN_FILENAME_WAS_NULL_WARNING );
			fN = DEFAULT_PROPFILENAME;
		}
		if( fN.isEmpty() )
		{
			logger.debug( GIVEN_FILENAME_WAS_EMPTY_WARNING );
			fN = DEFAULT_PROPFILENAME;
		}
		return fN;
	}

	protected final static dSLoggerProvider checkLogger( dSLoggerProvider logger )
	{
		if( logger == null )
		{
			logger = new dSLoggerProvider();
			logger.debug( GIVEN_LOGGER_WAS_NULL_WARNING );
		}
		return logger;
	}

	public final void setAdditive( boolean isAdd )
	{
		isAdditive = isAdd;
	}

	public final boolean getAdditive()
	{
		return isAdditive;
	}

}
