/** @author Mihaly Szakszon
 * @version 0.0.1 sept 8, 2014.
 *          Szentendre, Hungary EU
 *          develoSapiens.net
 *          develoSapiens.blog.com
 * 
 *          <code>net.develoSapiens.dSconfigProvider</code>
 *          <code>dSconfigProvider</code> */

package net.develoSapiens.dSconfigProvider;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import net.develoSapiens.dSloggerProvider.dSLoggerProvider;

public final class dSconfigLoader extends dSconfigUtils
{
	private Map<String, String> innerStore                      = new HashMap<>();
	private static final int    VALID_NUMBER_OF_PIECES_PER_LINE = 2;
	private static final String INVALID_NUMBER_OF_PROP_PIECES   = "WARNING! Invalid pieces of property line! ";
	private static final String SEPARATOR                       = "=";
	private static final String COMMENT_SYMBOL                  = "#";
	private static final int    KEY_INDEX                       = 0;
	private static final int    VALUE_INDEX                     = 1;

	public dSconfigLoader()
	{
		this( null , null , DEFAULT_ADDITIVE );
	}

	public dSconfigLoader( String fileName )
	{
		this( null , fileName , DEFAULT_ADDITIVE );
	}

	public dSconfigLoader( dSLoggerProvider logger )
	{
		this( logger , null , DEFAULT_ADDITIVE );
	}

	public dSconfigLoader( dSLoggerProvider logger, String fileName )
	{
		this( logger , fileName , DEFAULT_ADDITIVE );
	}

	public dSconfigLoader( dSLoggerProvider logger, String fileName, boolean isAdd )
	{
		super( logger , fileName , isAdd );
	}

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

	@Override
	public void doAction( String fileName )
	{
		innerStore = new HashMap<String, String>();
		String[] darabok = new String[VALID_NUMBER_OF_PIECES_PER_LINE];

		try( BufferedReader reader = new BufferedReader( new InputStreamReader( new FileInputStream( fileName ) ) ); )
		{
			String line = reader.readLine();
			while( line != null )
			{
				if( !line.startsWith( COMMENT_SYMBOL ) )
				{
					darabok = line.split( SEPARATOR );
					if( darabok.length == VALID_NUMBER_OF_PIECES_PER_LINE )
					{
						innerStore.put( darabok[KEY_INDEX], darabok[VALUE_INDEX] );
					}
					else
					{
						logger.warn( INVALID_NUMBER_OF_PROP_PIECES + line );
					}
				}
				line = reader.readLine();
			}
		}
		catch( FileNotFoundException ex )
		{
			logger.error( "ERROR! File not found! " + fileName );
			logger.error( ex.getMessage() );
			ex.printStackTrace();
		}
		catch( IOException ex )
		{
			logger.error( "ERROR! IO EXCEPTION! " + fileName );
			logger.error( ex.getMessage() );
			ex.printStackTrace();
		}
	}

	public Map<String, String> getInnerStore()
	{
		return getInnerStore( new HashMap<String, String>() );
	}

	public Map<String, String> getInnerStore( Map<String, String> inmap )
	{
		return mergeMaps( innerStore, inmap );
	}

	public Map<String, String> getInnerStore( Map<String, String> map, boolean isAdd )
	{
		setAdditive( isAdd );
		return mergeMaps( innerStore, map );
	}
}
