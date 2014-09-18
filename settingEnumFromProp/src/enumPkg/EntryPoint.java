// Thanks for that great post can be seen here: http://stackoverflow.com/questions/4908973/java-property-file-as-enum
// and for this tutorial here: http://www.mkyong.com/java/java-properties-file-examples/

package enumPkg;

import java.util.Properties;

public class EntryPoint 
{
	public static void main(String[] args)
	{
		Constants c1 = Constants.PROP1;
		Constants c2 = Constants.PROP2;
		System.out.println(c1.getValue());
		System.out.println(c2.getValue());
		
		Properties prop = new Properties();
		prop.setProperty( "firstProp", "alpha" );
		prop.setProperty( "secondProp", "beta" );
		prop.setProperty( "thirdProp", "delta" );
		
		PrintPropsToFileSystem propsOut = new PrintPropsToFileSystem();
		propsOut.doIt( "config.properties", prop );
	}
}
