package fizzBuzz;

public class Range 
{
    private final static int DEFAULT_RANGE = 44;
    private final static int DEFAULT_START = 1;
    private final static int CORRECTION = 1;
    
    private int firstNumber, lastNumber;
    
    public Range()
    {
    	this(DEFAULT_START);
    }
    
    public Range(int start)
    {
    	this(start, start + DEFAULT_RANGE - CORRECTION);
    }

    public Range(int start, int end)
    {
    	setFirstNumber(start);
    	setLastNumber(end);
    }

	public int getFirstNumber() 
	{
		return firstNumber;
	}

	private void setFirstNumber(int firstNumber) 
	{
		this.firstNumber = firstNumber;
	}

	public int getLastNumber() 
	{
		return lastNumber;
	}

	private void setLastNumber(int lastNumber) 
	{
		this.lastNumber = lastNumber;
	}
}
