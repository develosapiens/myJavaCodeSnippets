package fizzBuzz;

public class FizzBuzzFactory 
{
	protected static IFizzBuzzNum threeInstance = null;
	protected static IFizzBuzzNum getThreeIns()
	{
		if(threeInstance == null)
		{
			threeInstance = new Three();
		}
		return threeInstance;
	}

	protected static IFizzBuzzNum fiveInstance = null;
	protected static IFizzBuzzNum getFiveIns()
	{
		if(fiveInstance == null)
		{
			fiveInstance = new Five();
		}
		return fiveInstance;
	}

	protected static IFizzBuzzNum threeFiveInstance = null;
	protected static IFizzBuzzNum getThreeFiveIns()
	{
		if(threeFiveInstance == null)
		{
			threeFiveInstance = new ThreeFive();
		}
		return threeFiveInstance;
	}

	protected static IFizzBuzzNum noneInstance = null;
	protected static IFizzBuzzNum getNoneIns()
	{
		if(noneInstance == null)
		{
			noneInstance = new NoneOfThreeFive();
		}
		return noneInstance;
	}

	public static IFizzBuzzNum getFizzBuzzInstance(FizzBuzzType type) 
	{
		IFizzBuzzNum result = null;
		switch (type)
		{
		case THREE:      result = getThreeIns();           break;
		case FIVE:       result = getFiveIns();            break;
		case THREEFIVE:  result = getThreeFiveIns();       break;
		case NONE:       result = getNoneIns();            break;
		default:         result = null;                    break;
		}
		return result;
	}
}
