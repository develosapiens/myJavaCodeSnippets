package jobSeeker;

public interface Job 
{
	public void setKnownLanguages(Languages[] langs) throws Exception;
    public Languages[] getKnownLanguages();
    public String getJobType();
}
