package net.develosapiens.guiapps.translator.applogic;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TransTools
{
	private static Logger	logger									 = LogManager.getLogger( "develosapiens" );
	private static int		BORDER_4_START_OR_END_ID = 0;
	private static int		BORDER_4_DictElement_ID	 = 1;

	public static final String dictDataPathPrefix	 = "conf/dictionaries/";
	public static final String dictDataPathPostfix = ".dict";

	public static boolean checkStringAsParameterNotNullNotEmpty( String isItOk )
	{
		String caller = Thread.currentThread().getStackTrace()[2].getMethodName();
		boolean result = false;
		if( isItOk == null )
		{
			logger.warn( "Trying to set isItOk (" + caller + " - checkStringAsParameter) but it was null, do nothing!" );
		}
		else if( isItOk.isEmpty() )
		{
			logger.warn( "Trying to set isItOk (" + caller + " - checkStringAsParameter) but it was empty, do nothing!" );
		}
		else if( isItOk.trim().isEmpty() )
		{
			logger.warn( "Trying to set isItOk (" + caller
			    + " - checkStringAsParameter) but it consists only white space(s), do nothing!" );
		}
		else
		{
			result = true;
		}
		return result;
	}

	public static boolean checkIntegerListAsParameterNotNull( List<Integer> isItOk )
	{
		String caller = Thread.currentThread().getStackTrace()[2].getMethodName();
		boolean result = false;
		if( isItOk == null )
		{
			logger.warn(
			    "Trying to set " + isItOk + " (" + caller + " - checkIntegerListAsParameter) but it was null, do nothing!" );
		}
		else
		{
			result = true;
		}
		return result;
	}

	public static boolean isAlreadySetNotEmpty( String toCheck )
	{
		boolean result = false;
		if( toCheck.isEmpty() )
		{
			logger.error( "Language is empty yet, you can not add linknumbers until you set a language string!" );
			// throw new LinkNumberWithoutLanguageException(
			// "Language is empty yet, you can not add linknumbers until you set a
			// language string!" );
		}
		else
		{
			result = true;
		}
		return result;
	}

	public static boolean checkIntegerAsLinkNumberNotOutOfLimit( Integer i )
	{
		String caller = Thread.currentThread().getStackTrace()[2].getMethodName();
		boolean result = false;
		if( i == null )
		{
			logger.warn( "Integer parameter for (" + caller + " - checkIntegerAsLinkNumber) is null, nothing to do!" );
		}
		else if( i < BORDER_4_DictElement_ID )
		{
			logger.warn( "Integer parameter for (" + caller + " - checkIntegerAsLinkNumber) is less than "
			    + BORDER_4_DictElement_ID + ", nothing to do!" );
		}
		else
		{
			result = true;
		}
		return result;
	}

	public static boolean checkLocalDateTimeAsDateNotNull( LocalDateTime date )
	{
		String caller = Thread.currentThread().getStackTrace()[2].getMethodName();
		boolean result = false;
		if( date == null )
		{
			logger.error( "LocalDateTime parameter is null for " + caller + "! Do nothing!" );
		}
		else
		{
			result = true;
		}
		return result;
	}

	public static boolean checkIntAsStartOrEndID( int id )
	{
		return checkIntAsIDNotOutOfLimit( id, BORDER_4_START_OR_END_ID );
	}

	public static boolean checkIntAsDictElementID( int id )
	{
		return checkIntAsIDNotOutOfLimit( id, BORDER_4_DictElement_ID );
	}

	private static boolean checkIntAsIDNotOutOfLimit( int id, int border )
	{
		String caller = Thread.currentThread().getStackTrace()[3].getMethodName();
		boolean result = true;
		if( id < border )
		{
			logger.warn( "Setting theId failed at " + caller + " because the ID was " + id + ", border was: " + border
			    + "! Do nothing!" );
			result = false;
		}
		return result;
	}

	public static boolean checkStringListAsParameterNotNull( List<String> toCheck )
	{
		String caller = Thread.currentThread().getStackTrace()[2].getMethodName();
		boolean result = false;
		if( toCheck == null )
		{
			logger.warn( "List of String for (" + caller + " - checkStringListAsParameter) is null, do nothing!" );
		}
		else
		{
			result = true;
		}
		return result;
	}

	public static boolean checkLinkListAsParameterNotNull( List<Link> toCheck )
	{
		String caller = Thread.currentThread().getStackTrace()[2].getMethodName();
		boolean result = false;
		if( toCheck == null )
		{
			logger.warn( "List of Links for (" + caller + " - checkLinkListAsParameter) is null, do nothing!" );
		}
		else
		{
			result = true;
		}
		return result;
	}

	public static boolean checkLinkAsParameterNotNull( Link toCheck )
	{
		String caller = Thread.currentThread().getStackTrace()[2].getMethodName();
		boolean result = false;
		if( toCheck == null )
		{
			logger.warn( "Link for (" + caller + " - checkLinkAsParameter) is null, do nothing!" );
		}
		else
		{
			result = true;
		}
		return result;
	}

	public static boolean checkDictElementAsParameterNotNull( DictElement toCheck )
	{
		String caller = Thread.currentThread().getStackTrace()[2].getMethodName();
		boolean result = false;
		if( toCheck == null )
		{
			logger.warn( "The DictElement param for (" + caller + " - checkDictElementAsParameter) is null, do nothing!" );
		}
		else
		{
			result = true;
		}
		return result;
	}

	public static boolean checkWhetherStringIsInTheListEqualsICase( String isItIn, List<String> theListToCheck )
	{
		boolean result = false;
		for( String s : theListToCheck )
		{
			if( s.equalsIgnoreCase( isItIn ) )
			{
				result = true;
			}
			logger.info( "(equals) s: " + s + ", isItIn: " + isItIn );
		}
		return result;
	}

	public static boolean checkDictElementListAsParameterNotNull( List<DictElement> deList )
	{
		String caller = Thread.currentThread().getStackTrace()[2].getMethodName();
		boolean result = false;
		if( deList == null )
		{
			logger.warn( "The DictElement List param for (" + caller
			    + " - checkDictElementListAsParameterNotNull) is null, do nothing!" );
		}
		else
		{
			result = true;
		}
		return result;
	}

	public static boolean checkSingleDictAsParameterNotNull( SingleDict toCheck )
	{
		String caller = Thread.currentThread().getStackTrace()[2].getMethodName();
		boolean result = false;
		if( toCheck == null )
		{
			logger
			    .warn( "The SingleDict param for (" + caller + " - checkSingleDictAsParameterNotNull) is null, do nothing!" );
		}
		else
		{
			result = true;
		}
		return result;
	}

	public static boolean checkSingleDictListAsParameterNotNull( List<SingleDict> toCheck )
	{
		String caller = Thread.currentThread().getStackTrace()[2].getMethodName();
		boolean result = false;
		if( toCheck == null )
		{
			logger.warn(
			    "The SingleDict List param for (" + caller + " - checkSingleDictAsParameterNotNull) is null, do nothing!" );
		}
		else
		{
			result = true;
		}
		return result;
	}

	public static boolean checkPropertiesAsParameterNotNull( Properties p )
	{
		String caller = Thread.currentThread().getStackTrace()[2].getMethodName();
		boolean result = false;
		if( p == null )
		{
			logger
			    .warn( "The properties param for (" + caller + " - checkPropertiesAsParameterNotNull) is null, do nothing!" );
		}
		else
		{
			result = true;
		}
		return result;
	}

	public static boolean checkIfFileExists( String filePathString )
	{
		String caller = Thread.currentThread().getStackTrace()[2].getMethodName();
		boolean result = false;
		if( checkStringAsParameterNotNullNotEmpty( filePathString ) )
		{
			File filePathToCheck = new File( filePathString );
			if( filePathToCheck.exists() && !filePathToCheck.isDirectory() )
			{
				result = true;
			}
			else
			{
				logger.warn( "The file: " + filePathString + " (" + caller + ") doesn't exist or directory!" );
			}
		}
		return result;
	}
}
