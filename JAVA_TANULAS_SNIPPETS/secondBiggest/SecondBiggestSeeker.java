package secondBiggest;

public class SecondBiggestSeeker 
{
	private static Integer secMax;
	private static Integer max;

	public static Integer findIt(Integer[] intArr) 
	{
		secMax = null;
		max = null;
		
		if(intArr == null)
		{
			return null;
		}		
		else if(intArr.length < 2)
		{
			return null;
		}
		
		max = intArr[0];
		
		for(int i = 1; i < intArr.length; i++)
		{
			if(intArr[i] > max)
			{
				secMax = max;
				max = intArr[i];
			}
			else if(secMax == null && intArr[i] < max)
			{
				secMax = intArr[i];
			}
			else if(intArr[i] < max && intArr[i] > secMax)
			{
				secMax = intArr[i];
			}
		}
		
		return secMax;
	}
	
}
