package net.develosapiens.net.urlreachablecheck.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import net.develosapiens.net.urlreachablecheck.NetConnectionTester;

public class ConfigReader
{
	public static Properties getConfig( String CONFIG_FILE_NAME )
	{
		Properties config = new Properties();
		InputStream confPropFileStream = null;
		try
		{
			NetConnectionTester.logger.info( "Trying to read from: " + CONFIG_FILE_NAME );
			confPropFileStream = new FileInputStream( CONFIG_FILE_NAME );
			config.load( confPropFileStream );
			NetConnectionTester.logger.info( "done." );
			loggingRunConfigProperties( config );
		}
		catch( FileNotFoundException e )
		{
			e.printStackTrace();
			NetConnectionTester.logger.error( e.getMessage() );
		}
		catch( IOException ex )
		{
			ex.printStackTrace();
			NetConnectionTester.logger.error( ex.getMessage() );
		}
		return config;
	}

	protected static void loggingRunConfigProperties( Properties theProps )
	{
		Set<Object> objs = theProps.keySet();
		Iterator<Object> objsIt = objs.iterator();
		while( objsIt.hasNext() )
		{
			String key = objsIt.next().toString();
			String value = theProps.getProperty( key );
			NetConnectionTester.logger.info( key + ":  " + value );
		}
	}
}
