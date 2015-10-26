package net.develosapiens.guiapps.translator.applogic;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime>
{
	@Override
	public LocalDateTime unmarshal( String dstr ) throws Exception
	{
		return LocalDateTime.parse( dstr );
	}

	@Override
	public String marshal( LocalDateTime ldt ) throws Exception
	{
		return ldt.toString();
	}

}
