package net.develosapiens.proba.sync;

import java.util.ArrayList;
import java.util.List;

public class EntryPoint
{
	protected static final int LIMIT = 6;
	protected List<String>     generatedChars;

	class A implements Runnable
	{
		protected List<String> genChars;

		public A( List<String> chars )
		{
			genChars = chars;
		}

		@Override
		public void run()
		{
			for( int i = 0; i < 33; i++ )
			{
				synchronized( genChars )
				{
					genChars.add( "" + i + " " );
					int counter = 0;
					while( counter < LIMIT )
					{
						counter++;
						genChars.add( getSymbol() );
					}
					genChars.add( "\n" );
				}
				try
				{
					Long millis = (long) ( Math.random() * getRandomAreaLimit() );
					// System.out.println( getSymbol() + " " + millis );
					Thread.sleep( millis );
				}
				catch( InterruptedException e )
				{
					e.printStackTrace();
				}
			}
		}

		protected int getRandomAreaLimit()
		{
			return 80;
		}

		protected String getSymbol()
		{
			return "+";
		}
	}

	class B extends A
	{
		public B( List<String> chars )
		{
			super( chars );
		}

		@Override
		protected String getSymbol()
		{
			return "-";
		}

		@Override
		protected int getRandomAreaLimit()
		{
			return 120;
		}
	}

	public static void main( String[] args )
	{
		EntryPoint ep = new EntryPoint();
		ep.generatedChars = new ArrayList<String>();
		A a = ep.new A( ep.generatedChars );
		B b = ep.new B( ep.generatedChars );
		Thread t1 = new Thread( a );
		Thread t2 = new Thread( b );
		t1.start();
		t2.start();
		try
		{
			t1.join();
		}
		catch( InterruptedException e )
		{
			e.printStackTrace();
		}
		try
		{
			t2.join();
		}
		catch( InterruptedException e )
		{
			e.printStackTrace();
		}
		for( String s : ep.generatedChars )
		{
			System.out.print( s );
		}
	}
}
