package net.develosapiens.guiapps.translator.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class AboutTab extends JPanel
{
	public AboutTab()
	{
		add( new DeveloSapiensLogo( ParameterBuilder.getTheParameterBuilder().Props4dSLogoAbout() ), BorderLayout.CENTER );
	}
}
