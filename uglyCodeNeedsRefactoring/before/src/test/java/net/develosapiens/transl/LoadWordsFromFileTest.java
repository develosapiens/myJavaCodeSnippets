package net.develosapiens.transl;

import net.develosapiens.transl.tools.LoadWordsFromFile;
import net.develosapiens.transl.tools.XMLProcessingException;

import org.junit.Test;

public class LoadWordsFromFileTest
{

	@Test( expected = XMLProcessingException.class )
	public void test() throws XMLProcessingException
	{
		LoadWordsFromFile loader = new LoadWordsFromFile();
		DictionarySingle diSi = loader.doIt( null );
	}

}
