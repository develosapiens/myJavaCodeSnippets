package secondBiggest;

public class SecondBiggestMain 
{
	
	public static void main(String[] args) 
	{
		try
		{
			// This is a dead train. It is hard to understand the lines like this:
		    Displayer.writeThis(SecondBiggestSeeker.findIt(new PreProcessor().createIntArray(args)));
		}
		catch(IllegalArgumentException ie)
		{
			ie.printStackTrace();
		}
		
		// I don't like dead trains. I like to do thing this way:
		try
		{
			PreProcessor proci = new PreProcessor();
			Integer[] intArr = proci.createIntArray(args);
			int result = SecondBiggestSeeker.findIt(intArr);
			Displayer.writeThis(result);
		}
		catch(IllegalArgumentException ie)
		{
			ie.printStackTrace();
		}
	}
}
