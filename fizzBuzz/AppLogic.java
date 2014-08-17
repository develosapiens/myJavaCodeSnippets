package fizzBuzz;

public class AppLogic 
{
    public static IFizzBuzzNum getFizzBuzz(int num)
    {
    	FizzBuzzType type = FizzBuzzType.NONE;
    	if( num % 3 == FizzBuzzConstants.REQUIRED_MODULO_RESULT )
    	{
    		type = FizzBuzzType.THREE;
    	}
    	if ( num % 5 == FizzBuzzConstants.REQUIRED_MODULO_RESULT )
    	{
    		if(type == FizzBuzzType.THREE)
    		{
    			type = FizzBuzzType.THREEFIVE;
    		}
    		else
    		{
    			type = FizzBuzzType.FIVE;
    		}
    	}
    	return FizzBuzzFactory.getFizzBuzzInstance(type);
    }
    
    public static String getFizzBuzzResultString(int num)
    {
    	IFizzBuzzNum actual = getFizzBuzz(num);
    	String result = actual.FizzBuzzToPrint();
    	if(result == null)
    	{
    		result = Integer.toString(num);
    	}
    	else
    	{
    		result = actual.FizzBuzzToPrint();
    	}
    	return result;
    }
}
