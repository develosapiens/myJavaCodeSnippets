package net.develosapiens.guiapps.translator.applogic;

import static net.develosapiens.guiapps.translator.applogic.TransTools.checkDictElementAsParameterNotNull;
import static net.develosapiens.guiapps.translator.applogic.TransTools.checkIntAsDictElementID;
import static net.develosapiens.guiapps.translator.applogic.TransTools.checkLinkAsParameterNotNull;
import static net.develosapiens.guiapps.translator.applogic.TransTools.checkLinkListAsParameterNotNull;
import static net.develosapiens.guiapps.translator.applogic.TransTools.checkLocalDateTimeAsDateNotNull;
import static net.develosapiens.guiapps.translator.applogic.TransTools.checkStringAsParameterNotNullNotEmpty;
import static net.develosapiens.guiapps.translator.applogic.TransTools.checkStringListAsParameterNotNull;
import static net.develosapiens.guiapps.translator.applogic.TransTools.checkWhetherStringIsInTheListEqualsICase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlRootElement( name = "DictElement" )
public class DictElement implements Comparable<DictElement>
{
	private int		 id;
	private String expression;
	private String myLanguage;
	private String myTopic;

	@XmlJavaTypeAdapter( type = LocalDateTime.class, value = LocalDateTimeAdapter.class )
	private LocalDateTime	theDate;
	private boolean				active;

	@XmlElementWrapper( name = "links" )
	@XmlElement( name = "link" )
	private List<Link> links;

	@XmlElementWrapper( name = "examples" )
	@XmlElement( name = "example" )
	private List<String> examples;

	@XmlTransient
	private final String TIMESTRING = "10:10:10";

	@XmlTransient
	private Logger logger = LogManager.getLogger( "develosapiens" );

	public DictElement()
	{
		id = 0;
		expression = "";
		myLanguage = "";
		myTopic = "";
		theDate = LocalDateTime.of( LocalDate.now(), LocalTime.parse( TIMESTRING ) );
		active = true;
		links = new ArrayList<Link>();
		examples = new ArrayList<String>();
	}

	public int getId()
	{
		return id;
	}

	public void setId( int id )
	{
		if( checkIntAsDictElementID( id ) )
		{
			this.id = id;
		}
	}

	public String getExpression()
	{
		return expression;
	}

	public void setExpression( String expression )
	{
		if( checkStringAsParameterNotNullNotEmpty( expression ) )
		{
			this.expression = expression;
		}
	}

	public String getMyLanguage()
	{
		return myLanguage;
	}

	public void setMyLanguage( String myLanguage )
	{
		if( checkStringAsParameterNotNullNotEmpty( myLanguage ) )
		{
			this.myLanguage = myLanguage;
		}
	}

	public String getMyTopic()
	{
		return myTopic;
	}

	public void setMyTopic( String myTopic )
	{
		if( checkStringAsParameterNotNullNotEmpty( myTopic ) )
		{
			this.myTopic = myTopic;
		}
	}

	public LocalDateTime getTheDate()
	{
		return theDate;
	}

	public void setTheDate( LocalDateTime theDate )
	{
		if( checkLocalDateTimeAsDateNotNull( theDate ) )
		{
			this.theDate = theDate;
		}
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive( boolean active )
	{
		this.active = active;
	}

	public List<String> getExamples()
	{
		return examples;
	}

	public void setExamples( List<String> examples )
	{
		if( checkStringListAsParameterNotNull( examples ) )
		{
			this.examples = new ArrayList<String>();
			for( String s : examples )
			{
				addingOneExample( s );
			}
		}
	}

	public void addingOneExample( String ex )
	{
		if( checkStringAsParameterNotNullNotEmpty( ex ) && !checkWhetherStringIsInTheListEqualsICase( ex, examples ) )
		{
			examples.add( ex );
			Collections.sort( examples );
		}
	}

	public void deletingOneExample( String ex )
	{
		if( checkStringAsParameterNotNullNotEmpty( ex ) )
		{
			examples.remove( ex );
		}
	}

	public List<Link> getLinks()
	{
		return links;
	}

	public void setLinks( List<Link> linkLs )
	{
		if( checkLinkListAsParameterNotNull( linkLs ) )
		{
			links = new ArrayList<Link>();
			for( Link l : linkLs )
			{
				addingALink( l );
			}
		}
	}

	public void addingALink( Link linkToAdd )
	{
		if( checkLinkAsParameterNotNull( linkToAdd ) )
		{
			Link tmpLink;
			if( checkWhetherStringIsInTheListEqualsICase( linkToAdd.getLanguage(), listLinkLangs() ) )
			{
				tmpLink = findALinkByLanguage( linkToAdd.getLanguage() );
			}
			else
			{
				tmpLink = new Link();
				tmpLink.setLanguage( linkToAdd.getLanguage() );
				links.add( tmpLink );
			}
			for( Integer i : linkToAdd.getLinkNumbers() )
			{
				tmpLink.addingALinkNumber( i );
			}
			Collections.sort( links );
		}
	}

	public void deletingALink( Link link )
	{
		if( checkLinkAsParameterNotNull( link ) )
		{
			links.remove( link );
		}
	}

	public Link findALinkByLanguage( String lang )
	{
		Link result = null;
		if( checkStringAsParameterNotNullNotEmpty( lang ) )
		{
			for( Link l : links )
			{
				if( l.getLanguage().equalsIgnoreCase( lang ) )
				{
					result = l;
				}
			}
		}
		return result;
	}

	public String toString()
	{
		StringBuilder sB = new StringBuilder();
		sB.append( "id: " + id + "\n" );
		sB.append( "myLanguage: " + myLanguage + "\n" );
		sB.append( "expression: " + expression + "\n" );
		sB.append( "links:\n" );
		for( Link l : links )
		{
			sB.append( l.getLanguage() + ": " );
			Iterator<Integer> i = l.getLinkNumbers().iterator();
			while( i.hasNext() )
			{
				sB.append( i.next() );
				if( i.hasNext() )
				{
					sB.append( ", " );
				}
			}
			sB.append( "\n" );
		}
		sB.append( "examples:\n" );
		for( String s : examples )
		{
			sB.append( s + "\n" );
		}
		sB.append( "myTopic: " + myTopic + "\n" );
		sB.append( "theDate: " + theDate.getYear() + ". " + theDate.getMonthValue() + ". " + theDate.getDayOfMonth() + ".  "
		    + theDate.getHour() + ":" + theDate.getMinute() + ":" + theDate.getSecond() + "\n" );
		sB.append( "active: " + active + "\n" );
		return sB.toString();
	}

	public boolean equals( DictElement delement )
	{
		if( !checkDictElementAsParameterNotNull( delement ) )
		{
			return false;
		}

		if( delement.getId() != getId() )
		{
			logger.info( "delement.getId() != getId()" );
			return false;
		}

		if( !delement.getExpression().equals( getExpression() ) )
		{
			logger.info( "!delement.getExpression().equals( getExpression() )" );
			return false;
		}

		if( !delement.getMyLanguage().equals( getMyLanguage() ) )
		{
			logger.info( "!delement.getMyLanguage().equals( getMyLanguage() )" );
			return false;
		}

		if( !delement.getMyTopic().equals( getMyTopic() ) )
		{
			logger.info( "!delement.getMyTopic().equals( getMyTopic() )" );
			return false;
		}

		if( !delement.getTheDate().equals( getTheDate() ) )
		{
			logger.info( "!delement.getTheDate().equals( getTheDate() )" );
			return false;
		}

		if( delement.getLinks().size() != getLinks().size() )
		{
			logger.info( "delement.getLinks().size() != getLinks().size()" );
			return false;
		}

		for( int index = 0; index < listLinkLangs().size(); index++ )
		{
			if( !delement.listLinkLangs().get( index ).equals( listLinkLangs().get( index ) ) )
			{
				logger.info( "!delement.listLinkLangs().get( index ).equals( listLinkLangs().get( index ) )" );
				return false;
			}
		}

		if( delement.getExamples().size() != getExamples().size() )
		{
			logger.info( "delement.getExamples().size() != getExamples().size()" );
			return false;
		}

		for( String exs : getExamples() )
		{
			if( !delement.getExamples().contains( exs ) )
			{
				logger.info( "!delement.getExamples().contains( exs )" );
				return false;
			}
		}
		Collections.sort( links );
		Collections.sort( delement.links );
		for( int index = 0; index < links.size(); index++ )
		{
			if( !links.get( index ).equals( delement.links.get( index ) ) )
			{
				logger.info( "!links.get( index ).equals( delement.links.get( index ) )" );
				return false;
			}
		}
		if( delement.isActive() != isActive() )
		{
			logger.info( "delement.isActive() != isActive()" );
			return false;
		}
		return true;
	}

	@Override
	public int compareTo( DictElement o )
	{
		if( checkDictElementAsParameterNotNull( o ) )
		{
			String othersExpression = o.getExpression();
			return expression.compareToIgnoreCase( othersExpression );
		}
		else
		{
			logger.warn( "Tried to compare null! It is not allowed!" );
			throw new IllegalArgumentException( "Tried to compare null! It is not allowed!" );
		}
	}

	public List<String> listLinkLangs()
	{
		List<String> langList = new ArrayList<String>();
		for( Link link : links )
		{
			langList.add( link.getLanguage() );
		}
		Collections.sort( langList );
		return langList;
	}
}
