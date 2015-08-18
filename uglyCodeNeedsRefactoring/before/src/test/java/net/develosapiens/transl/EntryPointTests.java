package net.develosapiens.transl;

import static org.junit.Assert.assertTrue;
import net.develosapiens.transl.EntryPoint;

import org.junit.Test;

public class EntryPointTests
{
	@Test
	public void firstTest()
	{
		EntryPoint eP = new EntryPoint();
		eP.main( null );
		assertTrue( true );
	}
}
