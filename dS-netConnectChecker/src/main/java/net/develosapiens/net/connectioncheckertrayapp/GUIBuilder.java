package net.develosapiens.net.connectioncheckertrayapp;

import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.Logger;

import com.sun.org.apache.xalan.internal.xsltc.runtime.InternalRuntimeError;

import net.develosapiens.net.urlreachablecheck.helper.URLCheckResultValues;

public class GUIBuilder
{
	private Logger							logger;
	private String							trayName, toggleToOffText, toggleToOnText;
	private Image								trayImage;
	private SystemTray					systemTray;
	private TrayIcon						trayIcon;
	private PopupMenu						popup;
	private MenuItem						exitMenuItem, detailsMenuItem, closeMenuItem, develoSapiensMenuItem, checkNowMenuItem,
	    toggleRunMenuItem;
	private CheckboxMenuItem		showTheErrorOptionPane;
	private TextConstantsSetter	cons;
	private Repeater						repeater;

	public GUIBuilder( Logger lr, Repeater rep )
	{
		logger = lr;
		repeater = rep;
		cons = new TextConstantsSetter( logger );
		trayName = cons.getText( "TRAY_NAME" );
		toggleToOffText = cons.getText( "TOGGLE_TO_OFF_LABEL" );
		toggleToOnText = cons.getText( "TOGGLE_TO_ON_LABEL" );

		if( SystemTray.isSupported() )
		{
			trayImage = Toolkit.getDefaultToolkit().getImage( "images/dS.png" );

			systemTray = SystemTray.getSystemTray();

			trayIcon = new TrayIcon( trayImage, trayName );

			trayIcon.setImageAutoSize( true );
			trayIcon.setToolTip( cons.getText( "TOOL_TIP" ) );

			popup = new PopupMenu();

			develoSapiensMenuItem = new MenuItem( cons.getText( "DEVELOSAPIENS_LABEL" ) );
			detailsMenuItem = new MenuItem( cons.getText( "DETAILS_LABEL" ) );
			exitMenuItem = new MenuItem( cons.getText( "EXIT_LABEL" ) );
			closeMenuItem = new MenuItem( cons.getText( "POPUP_SCROLL_OFF" ) );
			checkNowMenuItem = new MenuItem( cons.getText( "CHECK_NOW_MENU_ITEM_LABEL" ) );
			toggleRunMenuItem = new MenuItem( setToggleLabel() );
			showTheErrorOptionPane = new CheckboxMenuItem( cons.getText( "SHOW_ERROR_PANE_CHECK_BOX_LABEL" ), true );

			addingItemsHead();
			addingItemsTail();

			trayIcon.setPopupMenu( popup );

			ActionListener exitItemClicked = new ActionListener()
			{
				@Override
				public void actionPerformed( ActionEvent e )
				{
					logger.trace( "removing system tray icon..." );
					systemTray.remove( trayIcon );
					System.exit( 0 );
				}
			};
			exitMenuItem.addActionListener( exitItemClicked );

			ActionListener checkNowItemClicked = new ActionListener()
			{
				@Override
				public void actionPerformed( ActionEvent e )
				{
					logger.trace( "Checking required..." );
					repeater.coreEngine();
				}
			};
			checkNowMenuItem.addActionListener( checkNowItemClicked );

			ActionListener toggleItemClicked = new ActionListener()
			{
				@Override
				public void actionPerformed( ActionEvent e )
				{
					logger.trace( "toggle run required..." );
					repeater.toggleAction();
					toggleRunMenuItem.setLabel( setToggleLabel() );
				}
			};
			toggleRunMenuItem.addActionListener( toggleItemClicked );

			try
			{
				logger.trace( "adding system tray icon..." );
				systemTray.add( trayIcon );
			}
			catch( AWTException e )
			{
				logger.error( "ERROR!!! TrayIcon could not be added." );
			}
			catch( Exception e )
			{
				e.printStackTrace();
				logger.error( "ERROR! Adding Tray Icon 2 System Tray Failed!!!" );
			}

		}
		else
		{
			logger.error( "SystemTray is not supported on this system!!!" );
			throw new InternalRuntimeError( "SystemTray is not supported on this system!!!" );
		}
	}

	public void drawGui( List<URLCheckResultValues> results, boolean allRight )
	{
		changeIconIfNeeded( allRight );
		popup.removeAll();
		addingItemsHead();

		for( URLCheckResultValues mItem : results )
		{
			MenuItem mi = new MenuItem( mItem.getCheckResultCodeNumber() + "  " + mItem.getCheckResultMessage() );
			Menu m = new Menu( mItem.getCheckedTarget() );
			m.add( mi );
			popup.add( m );
		}

		addingItemsTail();

		if( !allRight && showTheErrorOptionPane.getState() )
		{
			JOptionPane.showMessageDialog( null, cons.getText( "ERROR_DIALOG_MESSAGE" ), cons.getText( "ERROR_DIALOG_TITLE" ),
			    JOptionPane.ERROR_MESSAGE );
		}
	}

	public void changeIconIfNeeded( boolean allRight )
	{
		if( allRight )
		{
			trayImage = Toolkit.getDefaultToolkit().getImage( "images/YES.png" );
		}
		else
		{
			trayImage = Toolkit.getDefaultToolkit().getImage( "images/NO.png" );
		}
		trayIcon.setImage( trayImage );
	}

	private void addingItemsHead()
	{
		popup.addSeparator();
		popup.addSeparator();
		popup.add( develoSapiensMenuItem );
		popup.addSeparator();
		popup.addSeparator();
		popup.add( detailsMenuItem );
		popup.addSeparator();
	}

	private void addingItemsTail()
	{
		popup.addSeparator();
		popup.addSeparator();
		popup.add( showTheErrorOptionPane );
		popup.addSeparator();
		popup.add( toggleRunMenuItem );
		popup.addSeparator();
		popup.add( checkNowMenuItem );
		popup.addSeparator();
		popup.add( closeMenuItem );
		popup.addSeparator();
		popup.add( exitMenuItem );
	}

	private String setToggleLabel()
	{
		logger.debug( "Asking Repeater about goOn status..." );
		boolean ifGo = repeater.getGoOn();
		logger.debug( "GoOn is: " + ifGo );
		if( ifGo )
		{
			logger.debug( "ifGo is true, so return with toggleToOffText: " + toggleToOffText );
			return toggleToOffText;
		}
		logger.debug( "ifGo is false, so return with toggleToOnText: " + toggleToOnText );
		return toggleToOnText;
	}
}