package net.develosapiens.guiapps.translator.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordsStudyAreaSetter extends JPanel
{
	/**
	 * 
	 */
	private static final long	serialVersionUID = 2015100600000000001L;
	private final int					STEP						 = 1;
	private int								upperLimit			 = -1;
	private int								lowerLimit			 = 1;
	private int								firstId					 = 1;
	private int								lastId					 = -1;
	private int								numberOfColumnsForText;
	private int								WSASprefXlabel;
	private int								WSASprefYlabel;
	private int								WSASprefXspin;
	private int								WSASprefYspin;
	private int								theId;
	private int								startId;
	private int								endId;
	private String						WSASlabel;
	private String						WSASlabelToolTip;
	private String						WSASupperLimitLabel;
	private String						WSASlowerLimitLabel;
	private JPanel						uPanel;
	private JPanel						lPanel;
	private Logger						logger					 = LogManager.getLogger( "develosapiens" );

	public WordsStudyAreaSetter( Properties wsasProps )
	{
		// Enumeration<?> enume = wsasProps.propertyNames();
		// while( enume.hasMoreElements() )
		// {
		// String key = (String) enume.nextElement();
		// String value = wsasProps.getProperty( key );
		// System.out.println( key + ", " + value );
		// }
		// spinner = new JSpinner( new SpinnerNumberModel( actualPosition, minId(1),
		// maxId, STEP ) );

		// ParameterBuilder:
		WSASprefXlabel = Integer.parseInt( wsasProps.getProperty( "WSASprefXlabel" ) );
		WSASprefYlabel = Integer.parseInt( wsasProps.getProperty( "WSASprefYlabel" ) );
		WSASprefXspin = Integer.parseInt( wsasProps.getProperty( "WSASprefXspin" ) );
		WSASprefYspin = Integer.parseInt( wsasProps.getProperty( "WSASprefYspin" ) );
		numberOfColumnsForText = Integer.parseInt( wsasProps.getProperty( "WSAScolumns" ) );

		// sdict.getHeaderOfSD().getTheId() ).toString():
		theId = Integer.parseInt( wsasProps.getProperty( "theId" ) );
		startId = Integer.parseInt( wsasProps.getProperty( "startId" ) );
		endId = Integer.parseInt( wsasProps.getProperty( "endId" ) );
		System.out.println( "theId: " + theId );
		System.out.println( "startId: " + startId );
		System.out.println( "endId: " + endId );
		// tags.getString( "WSASlabel" ):
		WSASlabel = wsasProps.getProperty( "WSASlabel" );
		WSASlabelToolTip = wsasProps.getProperty( "WSASlabelToolTip" );
		WSASupperLimitLabel = wsasProps.getProperty( "WSASupperLimitLabel" );
		WSASlowerLimitLabel = wsasProps.getProperty( "WSASlowerLimitLabel" );

		if( endId < startId )
		{
			logger.warn( "End id was less than start id therefore I have set end id equal to start id!" );
			endId = startId;
		}
		if( theId > 0 )
		{
			if( endId == 0 )
			{
				upperLimit = theId - 1;
			}
			else
			{
				upperLimit = endId;
			}

			if( startId == 0 )
			{
				lowerLimit = 1;
			}
			else
			{
				lowerLimit = startId;
			}
			firstId = 1;
			lastId = theId - 1;
		}
		else
		{
			upperLimit = 0;
			lowerLimit = 0;
			firstId = 0;
			lastId = 0;
		}
		setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
		setPreferredSize( new Dimension( WSASprefXlabel + WSASprefXspin, WSASprefYspin + 30 ) );
		setMinimumSize( new Dimension( WSASprefXlabel + WSASprefXspin, WSASprefYspin + 30 ) );
		setMaximumSize( new Dimension( WSASprefXlabel + WSASprefXspin, WSASprefYspin + 30 ) );
		setBorder( BorderFactory.createTitledBorder( WSASlabel ) );
		setToolTipText( WSASlabelToolTip );
		uPanel = getSpinnerRow( WSASupperLimitLabel, numberOfColumnsForText, upperLimit, lowerLimit, lastId );
		lPanel = getSpinnerRow( WSASlowerLimitLabel, numberOfColumnsForText, lowerLimit, firstId, upperLimit );
		add( uPanel );
		add( lPanel );
	}

	private JPanel getSpinnerRow( String label, int columns, int position, int min, int max )
	{
		logger.debug(
		    "getSpinnerRow() instantiation: label - " + label + ", pos: " + position + ", min: " + min + ", max: " + max );
		JPanel panel = new JPanel();
		panel.setLayout( new BoxLayout( panel, BoxLayout.X_AXIS ) );
		JPanel lp = new JPanel();
		JLabel l = new JLabel( label );
		lp.setPreferredSize( new Dimension( WSASprefXlabel, WSASprefYlabel ) );
		lp.add( l );
		panel.add( lp );
		JPanel sp = new JPanel();
		JSpinner spin = new JSpinner( new SpinnerNumberModel( position, min, max, STEP ) );
		sp.setPreferredSize( new Dimension( WSASprefXspin, WSASprefYspin ) );
		Component spinnerEditor = spin.getEditor();
		JFormattedTextField jftf = ( (JSpinner.DefaultEditor) spinnerEditor ).getTextField();
		jftf.setColumns( columns );
		sp.add( spin );
		panel.add( sp );
		return panel;
	}
}
