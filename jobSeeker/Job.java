package jobSeeker;

public interface Job 
{
	public void setKnownLanguages(Languages[] langs);
    public Languages[] getKnownLanguages();
    public String getJobType();
}
