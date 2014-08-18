package secondBiggest;

public class PreProcessor 
{
	public static final String TOO_FEW_ELEMENTS_4_ARGUMENT_ARRAY = "There is nothing to do with this so short array of integers!";

    public Integer[] createIntArray(String[] args) throws IllegalArgumentException
    {
		Integer[] integArr = new Integer[args.length];
		
		if(integArr.length > 1)
		{
		    try
		    {
			    for(int i = 0; i < args.length; i++)
			    {
				    integArr[i] = Integer.parseInt(args[i]);
			    }
		    }
		    catch(Exception e)
		    {
			   e.printStackTrace(); 
		    }
		}
		else
		{
			Displayer.writeError(TOO_FEW_ELEMENTS_4_ARGUMENT_ARRAY);
			throw new IllegalArgumentException(TOO_FEW_ELEMENTS_4_ARGUMENT_ARRAY);
		}

    	return integArr;
    }
}
