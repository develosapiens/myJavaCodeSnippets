package net.develosapiens.guiapps.translator.applogic;

import static net.develosapiens.guiapps.translator.applogic.TransTools.checkIntegerAsLinkNumberNotOutOfLimit;
import static net.develosapiens.guiapps.translator.applogic.TransTools.checkIntegerListAsParameterNotNull;
import static net.develosapiens.guiapps.translator.applogic.TransTools.checkStringAsParameterNotNullNotEmpty;
import static net.develosapiens.guiapps.translator.applogic.TransTools.isAlreadySetNotEmpty;

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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlRootElement( name = "Link" )
public class Link implements Comparable<Link>
{

	private String language;

	@XmlElementWrapper( name = "linkNumbers" )
	@XmlElement( name = "linkNumber" )
	private List<Integer> linkNumbers;

	@XmlTransient
	private Logger logger = LogManager.getLogger( "develosapiens" );

	public Link()
	{
		language = "";
		linkNumbers = new ArrayList<Integer>();
	}

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage( String language )
	{
		if( checkStringAsParameterNotNullNotEmpty( language ) )
		{
			this.language = language;
		}
	}

	public List<Integer> getLinkNumbers()
	{
		return linkNumbers;
	}

	public void setLinkNumbers( List<Integer> linkNumbers )
	{
		if( checkIntegerListAsParameterNotNull( linkNumbers ) && isAlreadySetNotEmpty( language ) )
		{
			for( Integer i : linkNumbers )
			{
				addingALinkNumber( i );
			}
		}
	}

	public void addingALinkNumber( Integer i )
	{
		if( isAlreadySetNotEmpty( language ) && checkIntegerAsLinkNumberNotOutOfLimit( i ) )
		{
			if( linkNumbers.contains( i ) )
			{
				logger.warn( "addALinkNumber parameter (" + i + ") already in the list, nothing to do!" );
			}
			else
			{
				linkNumbers.add( i );
				Collections.sort( linkNumbers );
			}
		}
	}

	public void deletingALinkNumber( Integer lN )
	{
		if( checkIntegerAsLinkNumberNotOutOfLimit( lN ) )
		{
			if( linkNumbers.contains( lN ) )
			{
				linkNumbers.remove( lN );
			}
			else
			{
				logger.warn( "There is no link number: " + lN + " in linkNumbers. Nothing to do!" );
			}
		}
	}

	public String toString()
	{
		StringBuilder sB = new StringBuilder();
		if( language.isEmpty() )
		{
			sB.append( "No language set yet" );
		}
		else
		{
			sB.append( language + ": " );
			Iterator<Integer> iter = linkNumbers.iterator();
			while( iter.hasNext() )
			{
				sB.append( iter.next() );
				if( iter.hasNext() )
				{
					sB.append( ", " );
				}
			}
		}
		return sB.toString();
	}

	public boolean equals( Link otherLink )
	{
		boolean result = true;
		if( otherLink == null )
		{
			result = false;
		}
		else
		{
			if( this.language.equals( otherLink.language ) )
			{
				if( !isInside( this, otherLink ) || !isInside( otherLink, this ) )
				{
					logger.info( "!isInside( this, otherLink ) || !isInside( otherLink, this )" );
					result = false;
				}
			}
			else
			{
				logger.info( "!this.language.equals( otherLink.language )" );
				result = false;
			}
		}
		return result;
	}

	@Override
	public int compareTo( Link o )
	{
		return language.compareTo( o.language );
	}

	private boolean isInside( Link linkA, Link linkB )
	{
		for( Integer i : linkA.linkNumbers )
		{
			if( !linkB.linkNumbers.contains( i ) )
			{
				return false;
			}
		}
		return true;
	}
}
