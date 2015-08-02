package net.develosapiens.net.urlreachablecheck;

import net.develosapiens.net.urlreachablecheck.helper.URLCheckResultValues;

public interface URLchecker
{
	public URLCheckResultValues getStatusReport( String urlString2Check );
}
