package net.develosapiens.guiapps.translator.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeveloSapiensLogo extends JPanel
{
	private BufferedImage	dSlogo;
	private int						offsetX;
	private int						offsetY;
	private int						logoPanelX;
	private int						logoPanelY;
	private int						X;
	private int						Y;
	private int						red, green, blue, alpha;
	private Logger				logger	 = LogManager.getLogger( "develosapiens" );
	private String				logoPath = null;

	public DeveloSapiensLogo( Properties mySettings )
	{
		logoPath = mySettings.getProperty( "LogoPath" );
		logoPanelX = Integer.parseInt( mySettings.getProperty( "LogoPanelX" ) );
		logoPanelY = Integer.parseInt( mySettings.getProperty( "LogoPanelY" ) );
		offsetX = Integer.parseInt( mySettings.getProperty( "LogoOffX" ) );
		offsetY = Integer.parseInt( mySettings.getProperty( "LogoOffY" ) );
		X = Integer.parseInt( mySettings.getProperty( "LogoX" ) );
		Y = Integer.parseInt( mySettings.getProperty( "LogoY" ) );
		red = Integer.parseInt( mySettings.getProperty( "LogoBackgroundColorRed" ) );
		green = Integer.parseInt( mySettings.getProperty( "LogoBackgroundColorGreen" ) );
		blue = Integer.parseInt( mySettings.getProperty( "LogoBackgroundColorBlue" ) );
		alpha = Integer.parseInt( mySettings.getProperty( "LogoBackgroundColorAlpha" ) );

		try
		{
			dSlogo = ImageIO.read( new File( logoPath ) );
		}
		catch( IOException ex )
		{
			logger.warn( "Loading " + logoPath + " failed!" );
		}
		setPreferredSize( new Dimension( logoPanelX, logoPanelY ) );
		setBackground( new Color( red, green, blue, alpha ) );
	}

	@Override
	protected void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		g.drawImage( dSlogo, offsetX, offsetY, X, Y, null );
	}
}