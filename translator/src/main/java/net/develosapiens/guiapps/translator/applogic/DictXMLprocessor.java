package net.develosapiens.guiapps.translator.applogic;

import static net.develosapiens.guiapps.translator.applogic.TransTools.checkIfFileExists;
import static net.develosapiens.guiapps.translator.applogic.TransTools.dictDataPathPostfix;
import static net.develosapiens.guiapps.translator.applogic.TransTools.dictDataPathPrefix;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;

public class DictXMLprocessor
{
	public static SingleDict readDictionaryFromXML( String DictDataXMLFileName )
	{
		if( !checkIfFileExists( dictDataPathPrefix + DictDataXMLFileName + dictDataPathPostfix ) )
		{
			LogManager.getLogger( "checkIfFileExists... " + dictDataPathPrefix + DictDataXMLFileName + dictDataPathPostfix
			    + " doesn't exist!" );
		}
		SingleDict dict = null;
		try
		{
			JAXBContext jaxbContext = JAXBContext.newInstance( SingleDict.class );
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			dict = (SingleDict) jaxbUnmarshaller
			    .unmarshal( new File( dictDataPathPrefix + DictDataXMLFileName + dictDataPathPostfix ) );
		}
		catch( JAXBException e )
		{
			e.printStackTrace();
			LogManager.getLogger( "develosapiens" ).error( "readDictionaryFromXML\n" + e.getMessage() );
		}

		return dict;
	}

	public static void writeDictionaryToXML( SingleDict sDict )
	{
		try
		{
			JAXBContext context = JAXBContext.newInstance( SingleDict.class );
			Marshaller m = context.createMarshaller();
			m.setProperty( javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8" );
			m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
			m.marshal( sDict,
			    new File( dictDataPathPrefix + sDict.getHeaderOfSD().getDictLanguage() + dictDataPathPostfix ) );
		}
		catch( JAXBException e )
		{
			e.printStackTrace();
			LogManager.getLogger( "develosapiens" ).error( e.getMessage() );
		}
	}
}
