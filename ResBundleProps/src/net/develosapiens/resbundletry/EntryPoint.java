package net.develosapiens.resbundletry;

import java.util.Locale;
import java.util.ResourceBundle;

public class EntryPoint
{
	public static void main( String[] argos )
	{
		Locale locale = new Locale( "ds", "DS" );

		ResourceBundle labels = ResourceBundle.getBundle( "net.develosapiens.resbundletry.i18n.szoveg", locale );

		System.out.println( labels.getString( "label2" ) );

	}
}
