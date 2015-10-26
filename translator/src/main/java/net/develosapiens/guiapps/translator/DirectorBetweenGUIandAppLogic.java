package net.develosapiens.guiapps.translator;

import static net.develosapiens.guiapps.translator.applogic.TransTools.checkStringAsParameterNotNullNotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.develosapiens.guiapps.translator.applogic.DSdictionary;
import net.develosapiens.guiapps.translator.gui.MainGUIFrame;
import net.develosapiens.guiapps.translator.gui.ParameterBuilder;

public class DirectorBetweenGUIandAppLogic implements ConnectorCallBack
{
	private Properties	 tagsProp;
	private MainGUIFrame mainGui;
	private List<String> langs;
	private DSdictionary dSDict;
	private Logger			 logger	= LogManager.getLogger( "develosapiens" );

	public void runTheDictionaries()
	{
		ParameterBuilder paraB = ParameterBuilder.getTheParameterBuilder();

		langs = new ArrayList<String>();
		for( String s : paraB.getTheLangList() )
		{
			if( checkStringAsParameterNotNullNotEmpty( s ) )
			{
				if( !langs.contains( s ) )
				{
					langs.add( s );
					logger.debug( "runTheDictionaries - langs add: " + s );
				}
			}
		}

		dSDict = new DSdictionary( langs );

		tagsProp = new Properties();
		tagsProp.setProperty( "header_text", paraB.getTheTags().getString( "header_text" ) );

		mainGui = new MainGUIFrame( this );
	}

	@Override
	public DSdictionary getDictionary()
	{
		return dSDict;
	}
}
