package net.develosapiens.guiapps.translator.gui;

import static net.develosapiens.guiapps.translator.applogic.TransTools.checkStringAsParameterNotNullNotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.develosapiens.guiapps.translator.applogic.PropProvider;

public class ParameterBuilder
{
	private ResourceBundle					tags								= null;
	private Properties							configuration				= null;
	private static ParameterBuilder	theParameterBuilder	= null;
	private Logger									logger							= LogManager.getLogger( "develosapiens" );

	private ParameterBuilder()
	{
		PropProvider pP = new PropProvider();
		configuration = pP.readSettings();
		tags = ResourceBundle.getBundle( "net.develosapiens.guiapps.translator.gui.i18n.tags4gui", getTheLocale() );
	}

	public static ParameterBuilder getTheParameterBuilder()
	{
		if( theParameterBuilder == null )
		{
			theParameterBuilder = new ParameterBuilder();
		}
		return theParameterBuilder;
	}

	public Properties getTheConf()
	{
		return configuration;
	}

	public Locale getTheLocale()
	{
		String lang = configuration.getProperty( "gui_language" ).split( "_" )[0];
		String country = configuration.getProperty( "gui_language" ).split( "_" )[1];
		Locale locale = new Locale( lang, country );
		logger.info( "Selected locale: " + lang + "_" + country );
		return locale;
	}

	public Properties getWordStudyAreaProps()
	{
		Properties resultProp = new Properties();
		resultProp.setProperty( "WSASprefXlabel", configuration.getProperty( "WSASprefXlabel", "130" ) );
		resultProp.setProperty( "WSASprefYlabel", configuration.getProperty( "WSASprefYlabel", "60" ) );
		resultProp.setProperty( "WSASprefXspin", configuration.getProperty( "WSASprefXspin", "100" ) );
		resultProp.setProperty( "WSASprefYspin", configuration.getProperty( "WSASprefYspin", "60" ) );
		resultProp.setProperty( "WSAScolumns", configuration.getProperty( "WSAScolumns", "5" ) );
		return resultProp;
	}

	public Properties getSDGUIProps()
	{
		Properties resultProp = new Properties();
		resultProp.setProperty( "SDGUIminWidth", configuration.getProperty( "SDGUIminWidth", "600" ) );
		resultProp.setProperty( "SDGUIminHeight", configuration.getProperty( "SDGUIminHeight", "450" ) );
		return resultProp;
	}

	public List<String> getTheLangList()
	{
		String[] tmp = configuration.getProperty( "languagesList" ).split( "," );
		List<String> result = new ArrayList<>();
		for( int i = 0; i < tmp.length; i++ )
		{
			if( checkStringAsParameterNotNullNotEmpty( tmp[i] ) )
			{
				if( !result.contains( tmp[i] ) )
				{
					result.add( tmp[i] );
				}
			}
		}
		return result;
	}

	public ResourceBundle getTheTags()
	{
		return tags;
	}

	public Properties Props4dSLogoAbout()
	{
		Properties props = new Properties();
		props.setProperty( "LogoPath",
		    configuration.getProperty( "AboutLogoPath", "images/develoSApiensOldNewspaperTypes09.png" ) );
		props.setProperty( "LogoPanelX", configuration.getProperty( "AboutLogoPanelX", "320" ) );
		props.setProperty( "LogoPanelY", configuration.getProperty( "AboutLogoPanelY", "100" ) );
		props.setProperty( "LogoOffX", configuration.getProperty( "AboutLogoOffX", "10" ) );
		props.setProperty( "LogoOffY", configuration.getProperty( "AboutLogoOffY", "10" ) );
		props.setProperty( "LogoX", configuration.getProperty( "AboutLogoX", "300" ) );
		props.setProperty( "LogoY", configuration.getProperty( "AboutLogoY", "80" ) );
		props.setProperty( "LogoBackgroundColorRed", configuration.getProperty( "AboutLogoBackgroundColorRed", "6" ) );
		props.setProperty( "LogoBackgroundColorGreen", configuration.getProperty( "AboutLogoBackgroundColorGreen", "66" ) );
		props.setProperty( "LogoBackgroundColorBlue", configuration.getProperty( "AboutLogoBackgroundColorBlue", "6" ) );
		props.setProperty( "LogoBackgroundColorAlpha", configuration.getProperty( "AboutLogoBackgroundColorAlpha", "50" ) );
		return props;
	}

	public Properties Props4dSLogo()
	{
		Properties props = new Properties();
		props.setProperty( "LogoPath",
		    configuration.getProperty( "LogoPath", "images/develoSApiensOldNewspaperTypes09.png" ) );
		props.setProperty( "LogoPanelX", configuration.getProperty( "LogoPanelX", "170" ) );
		props.setProperty( "LogoPanelY", configuration.getProperty( "LogoPanelY", "60" ) );
		props.setProperty( "LogoOffX", configuration.getProperty( "LogoOffX", "10" ) );
		props.setProperty( "LogoOffY", configuration.getProperty( "LogoOffY", "10" ) );
		props.setProperty( "LogoX", configuration.getProperty( "LogoX", "150" ) );
		props.setProperty( "LogoY", configuration.getProperty( "LogoY", "40" ) );
		props.setProperty( "LogoBackgroundColorRed", configuration.getProperty( "LogoBackgroundColorRed", "6" ) );
		props.setProperty( "LogoBackgroundColorGreen", configuration.getProperty( "LogoBackgroundColorGreen", "66" ) );
		props.setProperty( "LogoBackgroundColorBlue", configuration.getProperty( "LogoBackgroundColorBlue", "6" ) );
		props.setProperty( "LogoBackgroundColorAlpha", configuration.getProperty( "LogoBackgroundColorAlpha", "0" ) );
		return props;
	}
}
