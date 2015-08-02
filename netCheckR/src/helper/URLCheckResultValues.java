package net.develosapiens.net.urlreachablecheck.helper;

public class URLCheckResultValues
{
	protected Integer	CheckResultCodeNumber;
	protected String	CheckResultMessage;
	protected String	CheckedTarget;

	public URLCheckResultValues( Integer number, String message, String target )
	{
		CheckResultCodeNumber = number;
		CheckResultMessage = message;
		CheckedTarget = target;
	}

	public Integer getCheckResultCodeNumber()
	{
		return CheckResultCodeNumber;
	}

	public String getCheckResultMessage()
	{
		return CheckResultMessage;
	}

	public String getCheckedTarget()
	{
		return CheckedTarget;
	}
}
