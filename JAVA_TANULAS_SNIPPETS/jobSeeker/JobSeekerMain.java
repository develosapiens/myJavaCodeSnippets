/**
 * My Name is Szakszon Mihály. The hungarian people write family name first.
 * I am a junior programmer. Maybe I applied for a job at your company, and
 * this could be the reason why you read this.
 * 
 *          This little code is a presentation of my coding skills.
 * 
 * Several months ago I had an idea to make my cv to be more effective. I 
 * wrote into the frontpage of my cv something similar to the following line:
 * 
 *              Job myJob = new DeveloperJob("Java", "C#");
 * 
 * It seemed to be a good idea. But why shouldn't I go farther? I decided to
 * write the whole object oriented code around the central idea in java.
 * 
 * I wanted to create a working instance of the first idea. And I did it.
 * I like creating things which can work well. I like to see them working well!
 * I love to create!
 * 
 * The beginning
 * Once upon a time -after being a sysadmin more than fifteen years- I decided
 * to change for an other profession. I always wanted to be a REAL programmer.
 * I applied to Eszterházy Károly Főiskola college in Eger. There I am going
 * to be graduated from Program Design and Informatics B.Sc. next year.
 * 
 * I know several parts of IT. I was a system administrator for a very long
 * time. I used to be a tester and now I have been working as a junior java
 * developer since november 2012. I am a junior java coder, but I often do
 * so-called 'DevOps' work as well. (Because of my sysadmin background).
 * 
 * I am open minded, I am a enthusiastic and impassioned coder. 
 * I adore "Clean Code", agile methodologies like SCRUM, eXtremeProgramming.
 * I like and I would like to go deep in details of modern technologies
 * like  Gradle, Sonar, Jenkins, fitnesse, cucumber, NoSQL, logging techniques 
 * I know that testing is extremly important and I pursue to write as many 
 * tests as I can.
 * 
 * Purposes.
 * I read this:
 * http://programmers.stackexchange.com/questions/136163/whats-the-difference-between-junior-middle-and-senior-developers
 * There is written that junior developers don't know what they don't know.
 * Now I do know that I have to learn a lot. I do know several things I
 * would know better and I am going to do that. For example reflection, 
 * callback objects, design patterns, maven settings, annotation and so on.
 * 
 * I have to learn much more and I am ready to learn much more. 
 * I adore to become a real programmer. I am enthusiastic (and passionate) 
 * about being a "Clean Coder".
 */

package jobSeeker;
import static jobSeeker.Languages.*;

public class JobSeekerMain 
{
	protected static final String EXCEPTION_ILL_ARG = "This went wrong: ";
	protected static final String EXC = "Something went wrong: ";
	
	public static void main(String[] args) 
	{
		Languages[] interestedIn = { JAVA, CSharp };
		MyLifeTrack myLife = MyLifeTrack.getTrack();
		try
		{
	        Job myJob = new DeveloperJob(interestedIn);
	        myLife.addJob(myJob);
	        TheDisplayThing.showIt(myJob);
	        TheDisplayThing.showIt(interestedIn);
		}
		catch(IllegalArgumentException e)
		{
			Logger.error(EXCEPTION_ILL_ARG + e.toString());
			e.printStackTrace();
		}
		catch(Exception e)
		{
			Logger.error(EXC + e.toString());
			e.printStackTrace();
		}
	}
}
