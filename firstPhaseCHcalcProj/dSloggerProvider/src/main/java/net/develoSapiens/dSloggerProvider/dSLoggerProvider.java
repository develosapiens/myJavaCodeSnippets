package net.develoSapiens.dSloggerProvider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class dSLoggerProvider
{
	private Logger logger;

	public dSLoggerProvider()
	{
		// System.setProperty( XmlConfigurationFactory.CONFIGURATION_FILE_PROPERTY,
		// "conf/log4j2.xml" );
		logger = LogManager.getLogger( dSLoggerProvider.class.getName() );
	}

	public void trace( String message )
	{
		logger.trace( message );
	}

	public void debug( String message )
	{
		logger.debug( message );
	}

	public void info( String message )
	{
		logger.info( message );
	}

	public void warn( String message )
	{
		logger.warn( message );
	}

	public void error( String message )
	{
		logger.error( message );
	}

	public void fatal( String message )
	{
		logger.fatal( message );
	}
}
