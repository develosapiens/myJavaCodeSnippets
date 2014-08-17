package jobSeeker;

public class TheDisplayThing 
{
    protected static final String NO_ENTRIES = "There is nothing to show you!";
	protected static final String HEADER4LANGS = "I like:\n";
	
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
		System.out.println(HEADER4LANGS + langs.toString());
	}
}
