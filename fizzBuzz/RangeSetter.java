package fizzBuzz;

public class RangeSetter 
{
	private final int INDEX_FIRST  = 0;
	private final int INDEX_LAST   = 1;
	private final int ONE_ARG  = 1;
	private final String SAME_ARGS_EXCEPTION_MESSAGE = "Two first args are the same!";
	private final String FIRST_ARG_IS_BIGGER_EXCEPTION_MESSAGE = "First argument is bigger than the second!";
	
    public Range setRange(String[] args)
    {
    	Range range = null;
    	int first;
    	int last;
    	
    	if( args.length > ONE_ARG )
    	{
    		first = Integer.parseInt(args[INDEX_FIRST]);
    		last = Integer.parseInt(args[INDEX_LAST]);

    		if( first == last )
    	    {
    		    throw new IllegalArgumentException(SAME_ARGS_EXCEPTION_MESSAGE);
    	    }
    	    if( first > last )
    	    {
    		    throw new IllegalArgumentException(FIRST_ARG_IS_BIGGER_EXCEPTION_MESSAGE);
    	    }
    	    range = new Range( first, last );
    	}
    	else if( args.length == ONE_ARG )
    	{
    		range = new Range(Integer.parseInt(args[INDEX_FIRST]));
    	}
    	else
    	{
    		range = new Range();
    	}
    	return range;
    }
}
