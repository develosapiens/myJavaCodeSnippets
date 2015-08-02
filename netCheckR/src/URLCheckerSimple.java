package net.develosapiens.net.urlreachablecheck;

import static net.develosapiens.net.urlreachablecheck.helper.InnerConfiguration.EXCEPTION_CONNECT;
import static net.develosapiens.net.urlreachablecheck.helper.InnerConfiguration.EXCEPTION_CONNECT_I;
import static net.develosapiens.net.urlreachablecheck.helper.InnerConfiguration.EXCEPTION_CONNECT_II;
import static net.develosapiens.net.urlreachablecheck.helper.InnerConfiguration.EXCEPTION_IO;
import static net.develosapiens.net.urlreachablecheck.helper.InnerConfiguration.EXCEPTION_IO_RESPONSE_MESSAGE;
import static net.develosapiens.net.urlreachablecheck.helper.InnerConfiguration.EXCEPTION_NO_ROUTE_RESPONSE_MESSAGE;
import static net.develosapiens.net.urlreachablecheck.helper.InnerConfiguration.EXCEPTION_NO_ROUTE_TO_HOST_I;
import static net.develosapiens.net.urlreachablecheck.helper.InnerConfiguration.EXCEPTION_NO_ROUTE_TO_HOST_II;
import static net.develosapiens.net.urlreachablecheck.helper.InnerConfiguration.EXCEPTION_RESPONSE_CODE_NUMBER;
import static net.develosapiens.net.urlreachablecheck.helper.InnerConfiguration.EXCEPTION_SOCKET;
import static net.develosapiens.net.urlreachablecheck.helper.InnerConfiguration.EXCEPTION_UNKNOWN_HOST;
import static net.develosapiens.net.urlreachablecheck.helper.InnerConfiguration.EXCEPTION_UNKNOWN_HOST_I;
import static net.develosapiens.net.urlreachablecheck.helper.InnerConfiguration.EXCEPTION_UNKNOWN_HOST_II;
import static net.develosapiens.net.urlreachablecheck.helper.InnerConfiguration.EXCEPTION_URL_I;
import static net.develosapiens.net.urlreachablecheck.helper.InnerConfiguration.EXCEPTION_URL_II;
import static net.develosapiens.net.urlreachablecheck.helper.InnerConfiguration.EXCEPTION_URL_RESPONSE_MESSAGE;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.develosapiens.net.urlreachablecheck.helper.ConfigReader;
import net.develosapiens.net.urlreachablecheck.helper.URLCheckResultValues;

public class URLCheckerSimple implements URLchecker
{
	private int								timeOut;
	private final String			CONFIG_FILE_NAME = "conf/run.properties";
	private Properties				argsProps;
	private String						method;
	private HttpURLConnection	actualConnect;

	public URLCheckerSimple( int tout )
	{
		timeOut = tout;
		argsProps = ConfigReader.getConfig( CONFIG_FILE_NAME );
		method = argsProps.getProperty( "method" );
		NetConnectionTester.logger.info( "method:      " + method );
	}

	@Override
	public URLCheckResultValues getStatusReport( String urlString2Check )
	{
		URLCheckResultValues actualCheckResult = null;
		try
		{
			URL myURL = new URL( urlString2Check ); // java.net.NoRouteToHostException:
			HttpURLConnection.setFollowRedirects( false );
			actualConnect = (HttpURLConnection) myURL.openConnection();
			actualConnect.setReadTimeout( timeOut );
			actualConnect.setConnectTimeout( timeOut );
			actualConnect.setRequestMethod( method ); // HEAD / GET OR
			                                          // .setRequestMethod("GET")
			actualConnect.setInstanceFollowRedirects( false );

			actualConnect.connect();

			NetConnectionTester.logger.trace( "actualConnect.getResponseCode(): " + actualConnect.getResponseCode() );
			NetConnectionTester.logger.trace( "actualConnect.getResponseMessage(): " + actualConnect.getResponseMessage() );
			NetConnectionTester.logger.trace( "The target URL to check / urlString2Check: " + urlString2Check );
			//@formatter:off
			actualCheckResult = new URLCheckResultValues(
					actualConnect.getResponseCode(), 
					actualConnect.getResponseMessage(), 
					urlString2Check);
			//@formatter:on
			if( NetConnectionTester.logger.isDebugEnabled() )
			{
				traceLogger( actualConnect );
			}
			NetConnectionTester.logger.info( "Return with actualCheckResult:" );
			NetConnectionTester.logger.info( "Target URL was:       " + actualCheckResult.getCheckedTarget() );
			NetConnectionTester.logger.info( "Response code number: " + actualCheckResult.getCheckResultCodeNumber() );
			NetConnectionTester.logger.info( "Response message:     " + actualCheckResult.getCheckResultMessage() );
		}
		catch( NoRouteToHostException ex )
		{
			ex.printStackTrace();
			//@formatter:off
			NetConnectionTester.logger.error( 
					                                 EXCEPTION_NO_ROUTE_TO_HOST_I + " " 
			                                   + urlString2Check + " " 
					                               + EXCEPTION_NO_ROUTE_TO_HOST_II 
					                             );
			
			NetConnectionTester.logger.error( ex.getMessage() );
			
			actualCheckResult = new URLCheckResultValues( 
					                                 EXCEPTION_RESPONSE_CODE_NUMBER, 
					                                 EXCEPTION_NO_ROUTE_RESPONSE_MESSAGE,
			                                     urlString2Check 
			                                  );
			//@formatter:on
		}
		catch( MalformedURLException | ProtocolException e )
		{
			e.printStackTrace();
			//@formatter:off
			NetConnectionTester.logger.error( 
					                                  EXCEPTION_URL_I + " " 
			                                    + urlString2Check + " "
			                                    + EXCEPTION_URL_II 
			                                 );
			
			NetConnectionTester.logger.error( e.getMessage() );
			
			actualCheckResult = new URLCheckResultValues( 
					                                          EXCEPTION_RESPONSE_CODE_NUMBER,
					                                          EXCEPTION_URL_RESPONSE_MESSAGE, 
			                                              urlString2Check 
			                                             );
			//@formatter:on
		}
		catch( UnknownHostException uhe )
		{
			uhe.printStackTrace();
			NetConnectionTester.logger.error( EXCEPTION_UNKNOWN_HOST );
			NetConnectionTester.logger.error( uhe.getMessage() );
			actualCheckResult = new URLCheckResultValues( EXCEPTION_RESPONSE_CODE_NUMBER,
			    EXCEPTION_UNKNOWN_HOST_I + " " + urlString2Check + " " + EXCEPTION_UNKNOWN_HOST_II, urlString2Check );
		}
		catch( ConnectException ex )
		{
			ex.printStackTrace();
			NetConnectionTester.logger.error( EXCEPTION_CONNECT );
			NetConnectionTester.logger.error( ex.getMessage() );
			actualCheckResult = new URLCheckResultValues( EXCEPTION_RESPONSE_CODE_NUMBER,
			    EXCEPTION_CONNECT_I + " " + urlString2Check + " " + EXCEPTION_CONNECT_II, urlString2Check );
		}
		catch( SocketException sex )
		{
			// sex.printStackTrace();
			NetConnectionTester.logger.error( EXCEPTION_SOCKET );
			NetConnectionTester.logger.error( sex.getMessage() );
			actualCheckResult = new URLCheckResultValues( EXCEPTION_RESPONSE_CODE_NUMBER, EXCEPTION_SOCKET, urlString2Check );
		}
		catch( IOException e )
		{
			e.printStackTrace();
			//@formatter:off
			NetConnectionTester.logger.error( EXCEPTION_IO );
			
			NetConnectionTester.logger.error( e.getMessage() );
			
			actualCheckResult = new URLCheckResultValues( 
					                                           EXCEPTION_RESPONSE_CODE_NUMBER, 
					                                           EXCEPTION_IO_RESPONSE_MESSAGE,
			                                               urlString2Check 
			                                             );
			//@formatter:on
		}
		catch( Exception e )
		{
			e.printStackTrace();
			NetConnectionTester.logger.error( e.getMessage() );
		}
		finally
		{
			if( actualConnect != null )
			{
				actualConnect.disconnect();
			}
		}
		return actualCheckResult;
	}

	private void traceLogger( HttpURLConnection actualConnect )
	{
		NetConnectionTester.logger.debug( "traceLogger starts here" );
		Map<String, List<String>> headers = actualConnect.getHeaderFields();
		logTheMaps( headers, "HEADER FIELDS:" );
		NetConnectionTester.logger.debug( "end of header fields." );
		if( method.equals( "HEAD" ) )
		{
			Map<String, List<String>> requestProperties = actualConnect.getRequestProperties();
			logTheMaps( requestProperties, "REQUEST PROPERTIES:" );
			NetConnectionTester.logger.debug( "end of request properties." );
		}
		else
		{
			NetConnectionTester.logger.debug( "Skipping request properties!." );
		}
	}

	private void logTheMaps( Map<String, List<String>> theMapToLog, String logMessage )
	{
		NetConnectionTester.logger.debug( logMessage );
		for( String theKeys : theMapToLog.keySet() )
		{
			List<String> values = theMapToLog.get( theKeys );
			StringBuilder sb = new StringBuilder();
			for( String valu : values )
			{
				sb.append( valu );
				sb.append( " " );
			}
			NetConnectionTester.logger.debug( theKeys + ": " + sb.toString() );
		}
	}
}
