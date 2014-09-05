package tryLogging;

public class EntryPoint
{

	private static final String THE_MESSAGE = "Thanks a lot for reading develoSapiens.blog.com!";

	public static void main( String[] args )
	{
		MyLogger myLogger = new MyLogger();
		myLogger.justSayIt( THE_MESSAGE );
	}
}
