package fizzBuzz;

public enum FizzBuzzType 
{
    THREE("3"), FIVE("5"), THREEFIVE("35"), NONE("0");

    private String type;
    
    private FizzBuzzType(String typeCode)
    {
    	type = typeCode;
    }
    
    public String getType()
    {
    	return toString();
    }
    
    public String toString()
    {
    	return type;
    }
}
