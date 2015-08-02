package net.develosapiens.net.urlreachablecheck;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.develosapiens.net.urlreachablecheck.helper.URLCheckResultValues;

public class EntryPoint
{
	protected static List<URLCheckResultValues>	connectStatusResponses;
	protected static Logger											logger = LogManager.getLogger( "develoSapiens" );

	public static void main( String[] args )
	{
		NetConnectionTester check = new NetConnectionTester( logger );
		connectStatusResponses = check.checkConn();
		fakeResultDisplay( connectStatusResponses );
	}

	protected static void fakeResultDisplay( List<URLCheckResultValues> theResults )
	{
		System.out.println( "Result of net check using URL connections:" );
		for( URLCheckResultValues result : theResults )
		{
			System.out.println( "Connection to " + result.getCheckedTarget() + " has the response: "
			    + result.getCheckResultCodeNumber() + " (" + result.getCheckResultMessage() + ")" );
		}
	}
}
