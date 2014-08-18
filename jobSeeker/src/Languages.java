package jobSeeker;

public enum Languages 
{
    JAVA("Java"), CSharp("C#");
    
    private String lang;
    
    private Languages(String langStr)
    {
    	lang = langStr;
    }
    
    public String toString()
    {
    	return lang;
    }
}
