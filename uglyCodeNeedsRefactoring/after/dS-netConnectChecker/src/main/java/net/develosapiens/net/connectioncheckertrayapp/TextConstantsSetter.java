package net.develosapiens.net.connectioncheckertrayapp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.Logger;

public class TextConstantsSetter
{
	private final String				confF	= "conf/netChecker.properties";
	private Map<String, String>	usedTexts;

	public TextConstantsSetter( Logger logR )
	{
		Properties getLangTextFileName = new Properties();
		usedTexts = new HashMap<String, String>();

		try
		{
			logR.info( "Trying to load " + confF + " to get textsFile parameter from it..." );
			getLangTextFileName.load( new FileInputStream( confF ) );
			logR.info( "...done." );
		}
		catch( IOException e1 )
		{
			e1.printStackTrace();
			logR.error( "Reading " + confF + " failed!!!" );
			logR.error( e1.getMessage() );
		}

		String fileToLoad = getLangTextFileName.getProperty( "textsFile" );
		try( BufferedReader br = new BufferedReader( new FileReader( "conf/" + fileToLoad ) ) )
		{
			String line;
			logR.debug( "Texts loaded:" );
			while( ( line = br.readLine() ) != null )
			{
				String[] parts = line.split( "=" );
				usedTexts.put( parts[0], parts[1] );
				logR.debug( "Key:  " + parts[0] + "  Value:  " + parts[1] );
			}
		}
		catch( IOException e )
		{
			e.printStackTrace();
			logR.error( "Reading conf/" + fileToLoad + " failed!!!" );
			logR.error( e.getMessage() );
		}
	}

	public String getText( String s )
	{
		return usedTexts.get( s );
	}
}
