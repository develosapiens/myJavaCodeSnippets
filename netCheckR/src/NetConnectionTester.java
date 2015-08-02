package net.develosapiens.net.urlreachablecheck;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.Logger;

import net.develosapiens.net.urlreachablecheck.helper.ConfigReader;
import net.develosapiens.net.urlreachablecheck.helper.TargetURLListProvider;
import net.develosapiens.net.urlreachablecheck.helper.URLCheckResultValues;
import net.develosapiens.net.urlreachablecheck.viaproxy.URLCheckerViaProxy;

public class NetConnectionTester
{
	public static Logger logger;

	protected final String						 USING_PROXY_OPTION_NAME					 = "proxy";
	protected final String[]					 ACCEPTABLE_NEED_PROXY_VALUES			 = { "1", "ok", "yes", "igen", "y", "i" };
	protected final String						 MAIN_CONFIG_FILE_NAME						 = "conf/run.properties";
	protected final String						 TARGET_URLs_LIST_FILE_NAME_SIMPLE = "conf/targetURL.s";
	protected final String						 TARGET_URLs_LIST_FILE_NAME_PROXY	 = "conf/viaproxy/targetURLs.4proxy";
	private List<URLCheckResultValues> URLcheckList;
	private URLchecker								 theURLchecker;
	private String										 targetListFileName;

	public NetConnectionTester( Logger lr )
	{
		logger = lr;
		logger.info( "\nURL reach tester starts" );
		URLcheckList = new ArrayList<>();
	}

	public List<URLCheckResultValues> checkConn()
	{
		int timeOut = Integer
		    .parseInt( ( ConfigReader.getConfig( "conf/run.properties" ).getProperty( "time_out_millis" ) ) );
		if( usingProxy() )
		{
			logger.info( "Instantiating checker via proxy" );
			theURLchecker = new URLCheckerViaProxy( timeOut );
			targetListFileName = TARGET_URLs_LIST_FILE_NAME_PROXY;
		}
		else
		{
			logger.info( "Instantiating checker without proxy" );
			theURLchecker = new URLCheckerSimple( timeOut );
			targetListFileName = TARGET_URLs_LIST_FILE_NAME_SIMPLE;
		}

		for( String target : TargetURLListProvider.getTheTargets( targetListFileName ) )
		{
			URLcheckList.add( theURLchecker.getStatusReport( target ) );
		}
		return URLcheckList;
	}

	protected boolean usingProxy()
	{
		Properties actualProps = ConfigReader.getConfig( MAIN_CONFIG_FILE_NAME );

		if( actualProps.containsKey( USING_PROXY_OPTION_NAME ) )
		{
			//@formatter:off
			String proxyOptionValue = actualProps
					                          .get( USING_PROXY_OPTION_NAME )
					                          .toString()
					                          .toLowerCase();
			//@formatter:on

			for( String acceptableNeedProxyValue : ACCEPTABLE_NEED_PROXY_VALUES )
			{
				if( proxyOptionValue.equals( acceptableNeedProxyValue ) )
				{
					return true;
				}
			} // end of iteration
		} // end of selection

		return false;
	} // end of usingProxy()

} // end of class
