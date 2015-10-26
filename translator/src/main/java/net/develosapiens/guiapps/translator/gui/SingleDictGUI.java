package net.develosapiens.guiapps.translator.gui;

import java.awt.Dimension;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import net.develosapiens.guiapps.translator.applogic.SingleDict;

public class SingleDictGUI extends JPanel
{
	/**
	 * 
	 */
	private static final long	serialVersionUID = 2015100400000000002L;
	private String						myLang					 = null;
	private int								width;
	private int								height;
	private ParameterBuilder	paramBuilder		 = null;
	private Properties				props						 = null;
	private SingleDict				sdict						 = null;
	private ResourceBundle		tags						 = null;

	public SingleDictGUI( SingleDict sdict )
	{
		this.sdict = sdict;
		myLang = sdict.getHeaderOfSD().getDictLanguage();
		setName( myLang );
		paramBuilder = ParameterBuilder.getTheParameterBuilder();
		props = paramBuilder.getSDGUIProps();
		tags = paramBuilder.getTheTags();
		width = Integer.parseInt( props.getProperty( "SDGUIminWidth" ) );
		height = Integer.parseInt( props.getProperty( "SDGUIminHeight" ) );

		createWSAS();
		createLogo();

		setMinimumSize( new Dimension( width, height ) );
		setVisible( true );
	}

	private void createLogo()
	{
		DeveloSapiensLogo logo = new DeveloSapiensLogo( ParameterBuilder.getTheParameterBuilder().Props4dSLogo() );
		add( logo );
	}

	private void createWSAS()
	{
		WordsStudyAreaSetter WSAS = null;

		Properties WSASprops = paramBuilder.getWordStudyAreaProps();

		WSASprops.setProperty( "theId", ( (Integer) sdict.getHeaderOfSD().getTheId() ).toString() );
		WSASprops.setProperty( "startId", ( (Integer) sdict.getHeaderOfSD().getStartId() ).toString() );
		WSASprops.setProperty( "endId", ( (Integer) sdict.getHeaderOfSD().getEndId() ).toString() );

		WSASprops.setProperty( "WSASlabel", tags.getString( "WSASlabel" ) );
		WSASprops.setProperty( "WSASlabelToolTip", tags.getString( "WSASlabelToolTip" ) );
		WSASprops.setProperty( "WSASupperLimitLabel", tags.getString( "WSASupperLimitLabel" ) );
		WSASprops.setProperty( "WSASlowerLimitLabel", tags.getString( "WSASlowerLimitLabel" ) );
		WSAS = new WordsStudyAreaSetter( WSASprops );
		add( WSAS );
	}
}
