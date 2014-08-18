package jobSeeker;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestMyLifeTrack {

	@Test
	public void testSingle() 
	{
		MyLifeTrack mLF1 = MyLifeTrack.getTrack();
		MyLifeTrack mLF2 = MyLifeTrack.getTrack();
		assertTrue(mLF1 == mLF2);
	}

}
