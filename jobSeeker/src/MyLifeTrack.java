package jobSeeker;

import java.util.ArrayList;
import java.util.List;

public class MyLifeTrack 
{
	protected List<Job> jobs = new ArrayList<>();
	protected static MyLifeTrack thisTrack = null;
	
	protected MyLifeTrack()
	{
	}
	
	public static MyLifeTrack getTrack()
	{
		if(thisTrack == null)
		{
			thisTrack = new MyLifeTrack();
		}
		return thisTrack;
	}
	
	public void addJob(Job job)
	{
		jobs.add(job);
	}
}
