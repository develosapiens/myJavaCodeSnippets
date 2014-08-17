package jobSeeker;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DeveloperJob implements Job 
{
	protected Languages[] langs;
	protected final String JOBTYPE = "Computer Programmer";
	protected final String LINE_STARTING = "I am a ";
	protected final String LINE_ENDING = "\nOther infos:\nNeeded knowledge:";
	protected final String I_KNOW_NOTHING = " -none-";
	protected final String SEPARATOR = ", ";
	protected final String EXCEPTION__NO_LANGUAGES_AT_ALL = "You do not know programming languages at all? No job for you!";
	protected final String EXCEPTION_NULL_REFERENCE = "Null pointer in the constructor!";
	
	public DeveloperJob(Languages[] langs) throws IllegalArgumentException
	{
		if(langs == null)
		{
			throw new IllegalArgumentException(EXCEPTION_NULL_REFERENCE);
		}
		if(langs.length == 0)
		{
		    throw new IllegalArgumentException(EXCEPTION__NO_LANGUAGES_AT_ALL);
		}
		this.langs = langs;
	}
	
	@Override
	public void setKnownLanguages(Languages[] langs) 
	{
		throw new NotImplementedException();
	}

	@Override
	public Languages[] getKnownLanguages() 
	{
		return langs;
	}
	
	public String getJobType()
	{
		return JOBTYPE;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public String toString()
	{
		StringBuilder wholeLine = new StringBuilder(LINE_STARTING + JOBTYPE + LINE_ENDING);
		if( langs.length != 0 )
		{
			wholeLine.append(" " + langs[0].valueOf(langs[0].name()));
		}
		else
		{
			wholeLine.append(I_KNOW_NOTHING);
		}
		for(int i = 1; i < langs.length; i++)
		{
			wholeLine.append(SEPARATOR + langs[i].valueOf(langs[i].name()));
		}
		return wholeLine.toString();
	}
}
