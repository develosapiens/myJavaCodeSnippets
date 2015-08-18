package net.develosapiens.transl;

import net.develosapiens.transl.tools.SaveWordsToFile;
import net.develosapiens.transl.tools.XMLProcessingException;

import org.junit.Test;

public class SaveWordsToFileTest
{

	@Test( expected = XMLProcessingException.class )
	public void test() throws XMLProcessingException
	{
		SaveWordsToFile saver = new SaveWordsToFile();
		saver.writeOut( null );
	}

}
