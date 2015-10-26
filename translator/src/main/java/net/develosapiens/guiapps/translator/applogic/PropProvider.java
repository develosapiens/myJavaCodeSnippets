package net.develosapiens.guiapps.translator.applogic;

import static net.develosapiens.guiapps.translator.applogic.TransTools.checkStringAsParameterNotNullNotEmpty;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropProvider
{
	private Logger							logger									 = LogManager.getLogger( "develosapiens" );
	private String							configFileName;
	private static final String	DEFAULT_CONFIG_FILE_NAME = "conf/dictionary.properties";
	private Properties					systemProps;

	public PropProvider()
	{
		this( DEFAULT_CONFIG_FILE_NAME );
	}

	public PropProvider( String fName )
	{
		if( checkStringAsParameterNotNullNotEmpty( fName ) )
		{
			configFileName = fName;
		}
		else
		{
			configFileName = DEFAULT_CONFIG_FILE_NAME;
		}
		systemProps = new Properties();
		systemProps.setProperty( "gui_language", "hu_HU" );
		systemProps.setProperty( "languagesList", "magyar,English" );
	}

	public Properties readSettings()
	{
		Properties prop = new Properties( systemProps );
		try( FileReader reader = new FileReader( configFileName ) )
		{
			prop.load( reader );
		}
		catch( IOException e )
		{
			e.printStackTrace();
			logger.warn( e.getMessage() );
		}
		return prop;
	}

	public void writeSettings( Properties prop )
	{
		try( OutputStream output = new FileOutputStream( configFileName ) )
		{
			prop.store( output, null );
		}
		catch( IOException io )
		{
			io.printStackTrace();
			logger.warn( io.getMessage() );
		}
	}
}
