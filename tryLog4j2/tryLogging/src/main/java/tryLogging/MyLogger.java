package tryLogging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyLogger
{
	private static final Logger logger                = LogManager.getLogger( MyLogger.class.getName() );

	private static final String LOGLEVEL_HEADER_FATAL = "This is  fatal   level, and the message is:  ";
	private static final String LOGLEVEL_HEADER_ERROR = "This is  error   level, and the message is:  ";
	private static final String LOGLEVEL_HEADER_WARN  = "This is  warning level, and the message is:  ";
	private static final String LOGLEVEL_HEADER_INFO  = "This is  info    level, and the message is:  ";
	private static final String LOGLEVEL_HEADER_DEBUG = "This is  debug   level, and the message is:  ";
	private static final String LOGLEVEL_HEADER_TRACE = "This is  trace   level, and the message is:  ";

	public void justSayIt( String toSay )
	{
		logger.fatal( LOGLEVEL_HEADER_FATAL + toSay );
		logger.error( LOGLEVEL_HEADER_ERROR + toSay );
		logger.warn( LOGLEVEL_HEADER_WARN + toSay );
		logger.info( LOGLEVEL_HEADER_INFO + toSay );
		logger.debug( LOGLEVEL_HEADER_DEBUG + toSay );
		logger.trace( LOGLEVEL_HEADER_TRACE + toSay );
	}
}
