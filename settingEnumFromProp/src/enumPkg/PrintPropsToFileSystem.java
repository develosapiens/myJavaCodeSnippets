package enumPkg;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class PrintPropsToFileSystem
{
	public void doIt(String outputFileName, Properties prop  )
	{
		OutputStream output = null;

		try
		{
			output = new FileOutputStream( outputFileName );

			prop.store( output, null );
		}
		catch( IOException io )
		{
			io.printStackTrace();
		}
		finally
		{
			if( output != null )
			{
				try
				{
					output.close();
				}
				catch( IOException e )
				{
					e.printStackTrace();
				}
			}
		}
	}
}
