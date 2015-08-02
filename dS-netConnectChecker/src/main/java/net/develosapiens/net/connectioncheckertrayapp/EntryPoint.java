package net.develosapiens.net.connectioncheckertrayapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.develosapiens.net.urlreachablecheck.helper.ConfigReader;

public class EntryPoint
{
	public static final Logger LOGGER = LogManager.getLogger( "develoSapiens" );

	public static void main( String[] args )
	{
		Repeater repeater = new Repeater( LOGGER );

		LOGGER.info( "Trying to read 'repeat-time-interval' from config file..." );

		long iterMillis = Long
		    .parseLong( ( ConfigReader.getConfig( "conf/netChecker.properties" ).getProperty( "time_interval_millis" ) ) );

		LOGGER.info( "Repeat time interval " + iterMillis + " millisecs" );
		repeater.doIteration( iterMillis );
	}
}
