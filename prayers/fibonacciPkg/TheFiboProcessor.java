package fibonacciPkg;

public class TheFiboProcessor 
{
	public int getFiboValue(int index)
	{
		if(index < 3)
		{
			return 1;
		}
		
		return getFiboValue(index - 1) + getFiboValue(index -2);
	}
}
