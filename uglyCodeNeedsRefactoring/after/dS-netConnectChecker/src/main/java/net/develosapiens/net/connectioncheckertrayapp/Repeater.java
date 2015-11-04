package net.develosapiens.net.connectioncheckertrayapp;

import java.util.List;

import org.apache.logging.log4j.Logger;

import net.develosapiens.net.urlreachablecheck.NetConnectionTester;
import net.develosapiens.net.urlreachablecheck.helper.URLCheckResultValues;

public class Repeater
{
	protected NetConnectionTester	netConTest;
	private GUIBuilder						gBuilder;
	private final int							GOOD_HTTP_RESPONSE_CODE_NUMBER = 200;
	private boolean								goOn;

	public Repeater( Logger logR )
	{
		netConTest = new NetConnectionTester( logR );
		goOn = true;
		gBuilder = new GUIBuilder( logR, this );
	}

	public void doIteration( final long timeInterval )
	{
		Runnable runnable = new Runnable()
		{
			public void run()
			{
				while( true )
				{
					if( goOn )
					{
						coreEngine();
					}
					try
					{
						Thread.sleep( timeInterval );
					}
					catch( InterruptedException e )
					{
						e.printStackTrace();
					}
				}
			}
		};
		Thread thread = new Thread( runnable );
		thread.start();
	}

	public boolean getGoOn()
	{
		return goOn;
	}

	public void toggleAction()
	{
		goOn = !goOn;
	}

	public void coreEngine()
	{
		List<URLCheckResultValues> responses = netConTest.checkConn();
		boolean areResponsesAllGood = resultListProcessor4GUI( responses );
		gBuilder.drawGui( responses, areResponsesAllGood );
		responses.clear();
	}

	private boolean resultListProcessor4GUI( List<URLCheckResultValues> theList )
	{
		boolean FLAG = true;
		for( URLCheckResultValues value : theList )
		{
			if( value.getCheckResultCodeNumber() != GOOD_HTTP_RESPONSE_CODE_NUMBER )
			{
				FLAG = false;
				break;
			}
		}
		return FLAG;
	}
}