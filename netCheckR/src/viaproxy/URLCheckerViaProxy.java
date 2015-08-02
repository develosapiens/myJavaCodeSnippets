package net.develosapiens.net.urlreachablecheck.viaproxy;

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

import net.develosapiens.net.urlreachablecheck.NetConnectionTester;
import net.develosapiens.net.urlreachablecheck.URLchecker;
import net.develosapiens.net.urlreachablecheck.helper.ConfigReader;
import net.develosapiens.net.urlreachablecheck.helper.URLCheckResultValues;

public class URLCheckerViaProxy implements URLchecker
{
	protected String					proxyHost, proxyPort, domain, userName, passWord, method;
	protected Properties			proxyArgsProps;
	protected final String		CONFIG_FILE_NAME = "conf/viaproxy/proxy.properties";
	private int								timeOut;
	private HttpURLConnection	actualConnect;

	public URLCheckerViaProxy( int tout )
	{
		proxyArgsProps = ConfigReader.getConfig( CONFIG_FILE_NAME );
		proxyHost = proxyArgsProps.getProperty( "proxyHost" );
		proxyPort = proxyArgsProps.getProperty( "proxyPort" );
		domain = proxyArgsProps.getProperty( "domain" );
		userName = proxyArgsProps.getProperty( "username" );
		passWord = proxyArgsProps.getProperty( "password" );
		method = proxyArgsProps.getProperty( "method" );
		NetConnectionTester.logger.info( "Proxy:       " + proxyHost );
		NetConnectionTester.logger.info( "Port:        " + proxyPort );
		NetConnectionTester.logger.info( "Domain:      " + domain );
		NetConnectionTester.logger.info( "User name:   " + userName );
		NetConnectionTester.logger.info( "Password:    " + passWord );
		NetConnectionTester.logger.info( "method:      " + method );
		timeOut = tout;
	}

	@Override
	public URLCheckResultValues getStatusReport( String urlString2Check )
	{
		URLCheckResultValues actualCheckResult = null;
		Properties systemSettings = System.getProperties();
		systemSettings.put( "proxySet", "true" );
		systemSettings.put( "http.proxyHost", proxyHost );
		systemSettings.put( "http.proxyPort", proxyPort );

		try
		{
			URL url2check = new URL( urlString2Check ); // throws
			                                            // MalformedURLException
			NetConnectionTester.logger.trace( "Trying to connect to " + urlString2Check + " via proxy..." );
			HttpURLConnection.setFollowRedirects( false );
			actualConnect = (HttpURLConnection) url2check.openConnection(); // throws
			actualConnect.setConnectTimeout( timeOut );
			actualConnect.setReadTimeout( timeOut );
			// IOException
			sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
			String toEncode = domain + userName + ":" + passWord;
			NetConnectionTester.logger.trace( "To encode: " + toEncode );
			String encodedUserPwd = encoder.encode( toEncode.getBytes() );

			NetConnectionTester.logger.info( "Proxy auth:" );
			NetConnectionTester.logger.info( "Basic " + encodedUserPwd );

			actualConnect.setRequestProperty( "Proxy-Authorization", "Basic " + encodedUserPwd );
			actualConnect.setRequestMethod( method ); // GET / HEAD
			                                          // throws ProtocolException
			actualConnect.setInstanceFollowRedirects( false );

			actualConnect.connect();

			//@formatter:off
			actualCheckResult = new URLCheckResultValues(
					actualConnect.getResponseCode(),     // throws IOEception
					actualConnect.getResponseMessage(),  // throws IOEception
					urlString2Check);
			//@formatter:on
			if( NetConnectionTester.logger.isDebugEnabled() )
			{
				traceLogger( actualConnect );
			}
			//@formatter:off
			//
			// HttpURLConnection
			//
			// HTTP_OK                 HTTP_ACCEPTED               HTTP_BAD_GATEWAY            HTTP_BAD_METHOD
			// HTTP_BAD_REQUEST        HTTP_CLIENT_TIMEOUT         HTTP_CREATED                HTTP_CONFLICT
			// HTTP_PROXY_AUTH         HTTP_ENTITY_TOO_LARGE       HTTP_FORBIDDEN              HTTP_GATEWAY_TIMEOUT
			// HTTP_INTERNAL_ERROR     HTTP_NOT_FOUND              HTTP_PROXY_AUTH             HTTP_USE_PROXY
			// HTTP_NOT_ACCEPTABLE     HTTP_NOT_AUTHORITATIVE      HTTP_NOT_IMPLEMENTED        HTTP_PAYMENT_REQUIRED
			// HTTP_UNAVAILABLE        HTTP_UNSUPPORTED_TYPE
			//
			//@formatter:on
			NetConnectionTester.logger.info( "Return with actualCheckResult:" );
			NetConnectionTester.logger.info( "Target URL was:       " + actualCheckResult.getCheckedTarget() );
			NetConnectionTester.logger.info( "Response code number: " + actualCheckResult.getCheckResultCodeNumber() );
			NetConnectionTester.logger.info( "Response message:     " + actualCheckResult.getCheckResultMessage() );
			NetConnectionTester.logger.trace( "Connection to " + urlString2Check + " via proxy was succeeded." );
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
