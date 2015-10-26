package net.develosapiens.guiapps.translator.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import net.develosapiens.guiapps.translator.ConnectorCallBack;

public class MainGUIFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long		serialVersionUID = 2015100400000000001L;
	private List<SingleDictGUI>	dictGUIs				 = null;
	private List<String>				langsList				 = null;
	private ConnectorCallBack		director				 = null;
	private ResourceBundle			rBundle					 = null;

	public MainGUIFrame( ConnectorCallBack dir )
	{
		rBundle = ParameterBuilder.getTheParameterBuilder().getTheTags();
		director = dir;
		dictGUIs = new ArrayList<>();
		initUI();
	}

	private void initUI()
	{
		setTitle( rBundle.getString( "header_text" ) );
		setSize( 500, 360 );
		langsList = ParameterBuilder.getTheParameterBuilder().getTheLangList();
		JTabbedPane jtabp = new JTabbedPane();
		jtabp.setTabLayoutPolicy( JTabbedPane.SCROLL_TAB_LAYOUT );

		for( String oneFromLangList : langsList )
		{
			// SingleDictGUI sdgui = new SingleDictGUI(
			// director.getDictionary().getSingleDictByLang( oneFromLangList ) );
			SingleDictGUI sdgui = new SingleDictGUI( director.getDictionary().getSingleDictByLang( oneFromLangList ) );
			jtabp.addTab( oneFromLangList, sdgui );
			dictGUIs.add( sdgui );
		}

		jtabp.addTab( rBundle.getString( "AboutTabLabel" ), new AboutTab() );
		add( jtabp );

		setLocationRelativeTo( null );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		setVisible( true );
	}
}
