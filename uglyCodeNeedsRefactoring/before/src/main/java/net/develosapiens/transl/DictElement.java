package net.develosapiens.transl;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DictElement implements Comparable<DictElement>
{
	private int                           id;
	private String                        expression;
	private Map<String, TreeSet<Integer>> links;
	protected String                      myLanguage;
	private SortedSet<String>             examples;
	private LocalDateTime                 theDate;
	private String                        myTopic;
	private Logger                        logger = LogManager.getLogger( "develoSapiens" );
	private boolean                       active = true;

	public Map<String, TreeSet<Integer>> getLinks()
	{
		return (Map<String, TreeSet<Integer>>) links;
	}

	public void setLinks( Map<String, TreeSet<Integer>> links )
	{
		this.links = links;
	}

	public void addALinkToALang( String lang, Integer link )
	{
		if( link == null )
		{
			logger.warn( "addALinkToALang got null Integer argument!" );
			throw new IllegalArgumentException( "addALinkToALang got null Integer argument!" );
		}

		if( lang == null )
		{
			logger.warn( "addALinkToALang got null string argument!" );
			throw new IllegalArgumentException( "addALinkToALang got null string argument!" );
		}

		if( lang.isEmpty() )
		{
			logger.warn( "addALinkToALang method got empty string argument!" );
			throw new IllegalArgumentException( "addALinkToALang method got empty string argument!" );
		}
		if( links.containsKey( lang ) )
		{
			links.get( lang ).add( link );
		}
		else
		{
			logger.warn( "You have just tried to add number: " + link + " as link to a non existent language set (" + lang
			    + ") in this element!! " );
		}
	}

	public void removeALinkByLanguage( String lang, Integer link )
	{
		if( lang == null )
		{
			logger.warn( "removeALinkByLanguage method got null string argument!" );
			throw new IllegalArgumentException( "removeALinkByLanguage method got null string argument!" );
		}

		if( lang.isEmpty() )
		{
			logger.warn( "removeALinkByLanguage method got empty string argument!" );
			throw new IllegalArgumentException( "removeALinkByLanguage method got empty string argument!" );
		}

		if( link == null )
		{
			logger.warn( "removeALinkByLanguage method got null Integer argument!" );
			throw new IllegalArgumentException( "removeALinkByLanguage method got null Integer argument!" );
		}

		if( links.containsKey( lang ) )
		{
			if( links.get( lang ).contains( link ) )
			{
				links.get( lang ).remove( link );
			}
			else
			{
				logger.warn( "Can not find " + link + " in " + lang + " link set!" );
			}
		}
		else
		{
			logger.warn( "Can not select non existent links' set from links' map! Language name as a key was: " + lang );
		}
	}

	public void removeAllLinskByLanguage( String lang )
	{
		if( links.containsKey( lang ) )
		{
			links.remove( lang );
		}
	}

	public Set<Integer> getLinksToALang( String lang )
	{
		if( lang == null )
		{
			logger.warn( "getLinksToALang(String) got null string argument!" );
			throw new IllegalArgumentException( "getLinksToALang(String) got null string argument!" );
		}
		if( lang.isEmpty() )
		{
			logger.warn( "getLinksToALang(String) got empty string argument!" );
			throw new IllegalArgumentException( "getLinksToALang(String) got empty string argument!" );
		}

		SortedSet<Integer> lnks = new TreeSet<Integer>();
		if( links != null )
		{
			if( links.containsKey( lang ) )
			{
				lnks = links.get( lang );
			}
			else
			{
				logger.warn( "There are no links for " + lang + " language!" );
			}
		}
		else
		{
			logger.warn( "Links Map is null yet!" );
		}
		return lnks;
	}

	public void setLinksToALang( final String lang, final Set<Integer> lnks )
	{
		if( lnks == null )
		{
			logger.warn( "setLinksToALang got null Set<Integer> argument!" );
			throw new IllegalArgumentException( "setLinksToALang got null Set<Integer> argument!" );
		}

		if( lang == null )
		{
			logger.warn( "setLinksToALang got null string argument!" );
			throw new IllegalArgumentException( "setLinksToALang got null string argument!" );
		}

		if( lang.isEmpty() )
		{
			logger.warn( "setLinksToALang method got empty string argument!" );
			throw new IllegalArgumentException( "setLinksToALang method got empty string argument!" );
		}

		if( this.links == null )
		{
			links = new HashMap<String, TreeSet<Integer>>();
		}
		if( !links.keySet().contains( lang ) )
		{
			links.put( lang, new TreeSet<Integer>() );
		}
		for( Integer i : lnks )
		{
			links.get( lang ).add( i );
		}
	}

	public String getMyLanguage()
	{
		return myLanguage;
	}

	public void setMyLanguage( String language )
	{
		if( language == null )
		{
			logger.warn( "setLanguage(String) method got null string argument!" );
			throw new IllegalArgumentException( "setLanguage(String) method got null string argument!" );
		}

		if( language.isEmpty() )
		{
			logger.warn( "setLanguage(String) method got empty string argument!" );
			throw new IllegalArgumentException( "setLanguage(String) method got empty string argument!" );
		}
		myLanguage = language;
	}

	public int getId()
	{
		return id;
	}

	public void setId( int i )
	{
		id = i;
	}

	public String getExpression()
	{
		return expression;
	}

	public void setExpression( String exp )
	{
		if( exp == null )
		{
			logger.warn( "setExpression(String) method was called with null argument!" );
			throw new IllegalArgumentException( "setExpression(String) method was called with null argument!" );
		}
		if( exp.isEmpty() )
		{
			logger.warn( "setExpression(String) method was called with empty string argument!" );
			throw new IllegalArgumentException( "setExpression(String) method was called with empty string argument!" );
		}
		expression = exp;
	}

	public TreeSet<String> getExamples()
	{
		return (TreeSet<String>) examples;
	}

	public void setExamples( SortedSet<String> examples )
	{
		if( examples == null )
		{
			logger.warn( "setExamples(String) method was called with null string argument!" );
			throw new IllegalArgumentException( "setExamples(String) method was called with null string argument!" );
		}
		this.examples = examples;
	}

	public LocalDateTime getTheDate()
	{
		return theDate;
	}

	public void setTheDate( LocalDateTime theDate )
	{
		this.theDate = theDate;
	}

	public String getMyTopic()
	{
		return myTopic;
	}

	public void setMyTopic( String topic )
	{
		if( topic == null )
		{
			logger.warn( "setMyTopic(String) method was called with null argument!" );
			throw new IllegalArgumentException( "setMyTopic(String) method was called with null argument!" );
		}
		if( topic.isEmpty() )
		{
			logger.warn( "setMyTopic(String) method was called with empty string argument!" );
			throw new IllegalArgumentException( "setMyTopic(String) method was called with empty string argument!" );
		}
		this.myTopic = topic;
	}

	public String toString()
	{
		StringBuffer sB = new StringBuffer();
		sB.append( "Expression: " + expression + "\n" );
		sB.append( "ID: " + id + "\n" );

		sB.append( "Language: " );
		if( myLanguage != null )
		{
			sB.append( myLanguage );
		}
		else
		{
			sB.append( "- NULL -" );
		}
		sB.append( "\n" );

		sB.append( "Links:" );
		if( links != null )
		{
			List<String> keys = new ArrayList<>( links.keySet() );
			Collections.sort( keys );
			for( String linkKey : keys )
			{
				sB.append( "\n" + linkKey + ":  " );
				Iterator<Integer> it = links.get( linkKey ).iterator();
				if( links.get( linkKey ).isEmpty() )
				{
					sB.append( " empty." );
				}
				else
				{
					while( it.hasNext() )
					{
						sB.append( it.next() );
						if( it.hasNext() )
						{
							sB.append( ", " );
						}
					}
				}
			}
		}
		else
		{
			sB.append( " - NULL -" );
		}
		sB.append( "\n" );

		sB.append( "Examples:" );
		if( examples != null )
		{
			sB.append( "\n" );
			for( String exa : examples )
			{
				sB.append( exa + "\n" );
			}
		}
		else
		{
			sB.append( " - NULL -\n" );
		}

		if( myTopic != null )
		{
			sB.append( "Topic: " + myTopic + "\n" );
		}
		else
		{
			sB.append( "Topic:  - NULL -\n" );
		}

		sB.append( "Date and time: " );
		if( theDate != null )
		{
			sB.append( theDate.getYear() + ". " );
			sB.append( theDate.getMonth().getDisplayName( TextStyle.FULL,
			    new Locale( Locale.getDefault().getLanguage(), Locale.getDefault().getCountry() ) ) );
			sB.append( " " + theDate.getDayOfMonth() + ".   " );
			sB.append( theDate.toLocalTime() + "\n" );
		}
		else
		{
			sB.append( " - NULL -\n" );
		}
		sB.append( "Active: " );
		if( active )
		{
			sB.append( "true" );
		}
		else
		{
			sB.append( "false" );
		}
		sB.append( "\n" );
		return sB.toString();
	}

	@Override
	public int compareTo( DictElement o )
	{
		String othersExpression = o.getExpression();
		return expression.compareToIgnoreCase( othersExpression );
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive( boolean active )
	{
		this.active = active;
	}

	public boolean equals( DictElement dE )
	{
		if( dE == null )
		{
			return false;
		}

		if( dE.getId() != this.getId() )
		{
			return false;
		}

		if( !dE.getExpression().equals( this.getExpression() ) )
		{
			return false;
		}

		if( dE.myLanguage == null || this.myLanguage == null )
		{
			if( dE.myLanguage != null || this.myLanguage != null )
			{
				return false;
			}
		}
		else if( !dE.getMyLanguage().equals( this.getMyLanguage() ) )
		{
			return false;
		}

		if( dE.getTheDate() == null || this.getTheDate() == null )
		{
			if( dE.getTheDate() != null || this.getTheDate() != null )
			{
				return false;
			}
		}
		else if( !dE.getTheDate().equals( (LocalDateTime) this.getTheDate() ) )
		{
			return false;
		}

		if( dE.isActive() != this.isActive() )
		{
			return false;
		}

		if( dE.examples == null || this.examples == null )
		{
			if( dE.examples != null || this.examples != null )
			{
				return false;
			}
		}
		else if( dE.examples.size() != this.examples.size() )
		{
			return false;
		}
		else
		{
			int sizeOfExamples = examples.size();
			String[] myExamplesArray = new String[sizeOfExamples];
			String[] othersExamplesArray = new String[sizeOfExamples];
			int indexForExamples = 0;
			for( String s : this.examples )
			{
				myExamplesArray[indexForExamples] = s;
				indexForExamples++;
			}
			indexForExamples = 0;
			for( String s : dE.examples )
			{
				othersExamplesArray[indexForExamples] = s;
				indexForExamples++;
			}
			for( int i = 0; i < sizeOfExamples; i++ )
			{
				if( !myExamplesArray[i].equals( othersExamplesArray[i] ) )
				{
					return false;
				}
			}

			if( dE.links == null || this.links == null )
			{
				System.out.println( "LinksNull" );
				if( dE.links != null || this.links != null )
				{
					return false;
				}
			}
			else
			{
				Set<String> keys1 = this.links.keySet();
				Set<String> keys2 = dE.links.keySet();
				Iterator<String> iter1 = keys1.iterator();
				Iterator<String> iter2 = keys2.iterator();
				while( iter1.hasNext() )
				{
					String elem = (String) iter1.next();
					if( !keys2.contains( elem ) )
					{
						return false;
					}
					Set<Integer> linksTS1 = this.getLinksToALang( elem );
					Iterator<Integer> iterator4linksTS1 = linksTS1.iterator();
					while( iterator4linksTS1.hasNext() )
					{
						if( !dE.getLinksToALang( elem ).contains( iterator4linksTS1.next() ) )
						{
							return false;
						}
					}
				}
				while( iter2.hasNext() )
				{
					String elem = (String) iter2.next();
					if( !keys1.contains( elem ) )
					{
						return false;
					}
					Set<Integer> linksTS2 = dE.getLinksToALang( elem );
					Iterator<Integer> iterator4linksTS2 = linksTS2.iterator();
					while( iterator4linksTS2.hasNext() )
					{
						if( !this.getLinksToALang( elem ).contains( iterator4linksTS2.next() ) )
						{
							return false;
						}
					}
				}
			}
		}
		return true;
	}
}
