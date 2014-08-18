package jobSeeker;

public class TheDisplayThing 
{
    protected static final String NO_ENTRIES = "There is nothing to show you!";
	protected static final String HEADER4LANGS = "I like:";
	
    public static void showIt(Job jobToShow)
    {
    	System.out.println(jobToShow);
    }

	public static void showIt(Languages[] langs) 
	{
		if(langs.length == 0)
		{
			System.out.println(NO_ENTRIES);
		}
		else
		{
		    System.out.print(HEADER4LANGS);
		    for(Languages oneElement : langs)
		    {
			    System.out.print(" " + oneElement);
		    }
		    System.out.println();
		}
	}
}
