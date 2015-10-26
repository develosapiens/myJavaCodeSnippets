package net.develosapiens.guiapps.translator.applogic;

import static net.develosapiens.guiapps.translator.applogic.TransTools.checkIfFileExists;
import static net.develosapiens.guiapps.translator.applogic.TransTools.checkStringAsParameterNotNullNotEmpty;
import static net.develosapiens.guiapps.translator.applogic.TransTools.checkStringListAsParameterNotNull;
import static net.develosapiens.guiapps.translator.applogic.TransTools.dictDataPathPostfix;
import static net.develosapiens.guiapps.translator.applogic.TransTools.dictDataPathPrefix;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DSdictionary
{
	private Logger					 logger				= LogManager.getLogger( "develosapiens" );
	private List<SingleDict> dictionaries	= new ArrayList<SingleDict>();

	// törölni egy single dictionary-t (nyelvet): törölni az aktuális listából
	// a nyelvhez tartozó SingleDiict-et, letörölni a hozzá tartozó XML-t,

	public DSdictionary( List<String> sds )
	{
		if( checkStringListAsParameterNotNull( sds ) )
		{
			for( String lang : sds )
			{
				if( checkStringAsParameterNotNullNotEmpty( lang ) )
				{
					boolean weDontHaveSDwithThatLanguage = true;
					for( SingleDict d : dictionaries )
					{
						if( lang.equalsIgnoreCase( d.getHeaderOfSD().getDictLanguage() ) )
						{
							weDontHaveSDwithThatLanguage = false;
						}
					}
					if( weDontHaveSDwithThatLanguage )
					{
						SingleDict sd;
						if( checkIfFileExists( dictDataPathPrefix + lang + dictDataPathPostfix ) )
						{
							sd = DictXMLprocessor.readDictionaryFromXML( lang );
						}
						else
						{
							sd = new SingleDict();
							sd.getHeaderOfSD().setDictLanguage( lang );
							DictXMLprocessor.writeDictionaryToXML( sd );
							logger.info( "Creating language file for: " + lang );
						}
						dictionaries.add( sd );
					}
				}
			}
		}
	}

	public SingleDict getSingleDictByLang( String neededLang )
	{
		SingleDict result = null;
		if( checkStringAsParameterNotNullNotEmpty( neededLang ) )
		{
			for( SingleDict sd : dictionaries )
			{
				if( sd.getHeaderOfSD().getDictLanguage().equals( neededLang ) )
				{
					result = sd;
				}
			}
		}
		return result;
	}

	public List<SingleDict> getSingleDicts()
	{
		return dictionaries;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for( SingleDict sd : dictionaries )
		{
			sb.append( sd );
		}
		return sb.toString();
	}
}
