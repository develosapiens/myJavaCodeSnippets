package jobSeeker;

public class MyNotImplementedYetException extends Exception
{
	/** I made this class because of this:
	 *  http://stackoverflow.com/questions/16374189/notimplementedexception-is-internal-proprietary-api
	 */
	private static final long serialVersionUID = -1780016843900305750L;

	public MyNotImplementedYetException(String message)
	{
		super(message);
	}
}
