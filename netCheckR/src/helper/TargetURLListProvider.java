package net.develosapiens.net.urlreachablecheck.helper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import net.develosapiens.net.urlreachablecheck.NetConnectionTester;

public class TargetURLListProvider
{
	public static List<String> getTheTargets( final String targetsFile2load )
	{
		List<String> theTargets = new ArrayList<String>();
		try
		{
			NetConnectionTester.logger.info( "Trying to load " + targetsFile2load );
			theTargets = Files.readAllLines( Paths.get( targetsFile2load ), Charset.forName( "UTF-8" ) );
			NetConnectionTester.logger.info( "done." );
		}
		catch( IOException e )
		{
			e.printStackTrace();
			NetConnectionTester.logger.error( "IO Error! " + targetsFile2load );
			NetConnectionTester.logger.error( e.getMessage() );
		}
		return theTargets;
	}
}
