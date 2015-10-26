package net.develosapiens.guiapps.translator.applogic;

import static net.develosapiens.guiapps.translator.applogic.TransTools.checkIntAsStartOrEndID;
import static net.develosapiens.guiapps.translator.applogic.TransTools.checkLocalDateTimeAsDateNotNull;
import static net.develosapiens.guiapps.translator.applogic.TransTools.checkStringAsParameterNotNullNotEmpty;
import static net.develosapiens.guiapps.translator.applogic.TransTools.checkStringListAsParameterNotNull;
import static net.develosapiens.guiapps.translator.applogic.TransTools.checkWhetherStringIsInTheListEqualsICase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
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
// @XmlType
// @XmlType(propOrder = { "field2", "field1",.. })
@XmlRootElement( name = "Header" )
public class HeaderOfSD
{
	private String dictLanguage;

	@XmlJavaTypeAdapter( type = LocalDateTime.class, value = LocalDateTimeAdapter.class )
	private LocalDateTime startTime;

	@XmlJavaTypeAdapter( type = LocalDateTime.class, value = LocalDateTimeAdapter.class )
	private LocalDateTime endTime;

	private int theId, startId, endId;

	@XmlElementWrapper( name = "topics" )
	@XmlElement( name = "topic" )
	private List<String> interestingTopics;

	@XmlTransient
	private final String timeString = "11:11:11";

	@XmlTransient
	private Logger logger = LogManager.getLogger( "develosapiens" );

	public HeaderOfSD()
	{
		dictLanguage = "";
		interestingTopics = new ArrayList<String>();
		startId = 0;
		endId = 0;
		theId = 0;
		startTime = LocalDateTime.of( LocalDate.of( 2015, 8, 20 ), LocalTime.parse( timeString ) );
		endTime = LocalDateTime.of( LocalDate.now(), LocalTime.parse( timeString ) );
	}

	public String getDictLanguage()
	{
		return dictLanguage;
	}

	public void setDictLanguage( String dictLanguage )
	{
		if( checkStringAsParameterNotNullNotEmpty( dictLanguage ) )
		{
			this.dictLanguage = dictLanguage;
		}
	}

	public LocalDateTime getStartTime()
	{
		return startTime;
	}

	public void setStartTime( LocalDateTime startTime )
	{
		if( checkLocalDateTimeAsDateNotNull( startTime ) )
		{
			this.startTime = startTime;
		}
	}

	public LocalDateTime getEndTime()
	{
		return endTime;
	}

	public void setEndTime( LocalDateTime endTime )
	{
		if( checkLocalDateTimeAsDateNotNull( endTime ) )
		{
			this.endTime = endTime;
		}
	}

	public int getTheId()
	{
		return theId;
	}

	public void setTheId( int theId )
	{
		if( checkIntAsStartOrEndID( theId ) )
		{
			this.theId = theId;
		}
	}

	public int getStartId()
	{
		return startId;
	}

	public void setStartId( int startId )
	{
		if( checkIntAsStartOrEndID( startId ) )
		{
			this.startId = startId;
		}
	}

	public int getEndId()
	{
		return endId;
	}

	public void setEndId( int endId )
	{
		if( checkIntAsStartOrEndID( endId ) )
		{
			this.endId = endId;
		}
	}

	public List<String> getInterestingTopics()
	{
		return interestingTopics;
	}

	public void setInterestingTopics( List<String> interestingTopics )
	{
		if( checkStringListAsParameterNotNull( interestingTopics ) )
		{
			this.interestingTopics = new ArrayList<String>();
			for( String s : interestingTopics )
			{
				addingOneTopic( s );
			}
		}
	}

	public void addingOneTopic( String topicToAdd )
	{
		if( checkStringAsParameterNotNullNotEmpty( topicToAdd )
		    && !checkWhetherStringIsInTheListEqualsICase( topicToAdd, interestingTopics ) )
		{
			interestingTopics.add( topicToAdd );
			Collections.sort( interestingTopics );
		}
	}

	public void deletingOneTopic( String topicToDel )
	{
		if( checkStringAsParameterNotNullNotEmpty( topicToDel ) )
		{
			interestingTopics.remove( topicToDel );
		}
	}

	public String toString()
	{
		StringBuilder sB = new StringBuilder();
		sB.append( "Dictionary header:\n" );
		sB.append( "Dict language: " + dictLanguage + "\n" );
		sB.append( "Auto ID: " + theId + "\n" );
		sB.append( "Start ID: " + startId + "\n" );
		sB.append( "End ID: " + endId + "\n" );
		sB.append(
		    "Start date: " + startTime.getYear() + ". " + startTime.getMonthValue() + ". " + startTime.getDayOfMonth()
		        + ". " + startTime.getHour() + ":" + startTime.getMinute() + ":" + startTime.getSecond() + "\n" );
		sB.append( "End date: " + endTime.getYear() + ". " + endTime.getMonthValue() + ". " + endTime.getDayOfMonth() + ". "
		    + endTime.getHour() + ":" + endTime.getMinute() + ":" + endTime.getSecond() + "\n" );
		sB.append( "Interesting topics:\n" );
		for( String iT : interestingTopics )
		{
			sB.append( iT + "\n" );
		}
		return sB.toString();
	}

	public boolean equals( HeaderOfSD otherHeader )
	{

		if( !otherHeader.dictLanguage.equals( dictLanguage ) )
		{
			logger.info( "!otherHeader.dictLanguage.equals( dictLanguage )" );
			return false;
		}

		if( !otherHeader.startTime.equals( startTime ) )
		{
			logger.info( "!otherHeader.startTime.equals( startTime )" );
			return false;
		}

		if( !otherHeader.endTime.equals( endTime ) )
		{
			logger.info( "!otherHeader.endTime.equals( endTime )" );
			return false;
		}

		if( otherHeader.getTheId() != getTheId() )
		{
			logger.info( "otherHeader.getTheId() != getTheId()" );
			return false;
		}

		if( otherHeader.getStartId() != getStartId() )
		{
			logger.info( "otherHeader.getStartId() != getStartId()" );
			return false;
		}

		if( otherHeader.getEndId() != getEndId() )
		{
			logger.info( "otherHeader.getEndId() != getEndId()" );
			return false;
		}

		if( otherHeader.interestingTopics.size() != interestingTopics.size() )
		{
			logger.info( "otherHeader.interestingTopics.size() != interestingTopics.size()" );
			return false;
		}
		Collections.sort( otherHeader.interestingTopics );
		Collections.sort( interestingTopics );
		for( int index = 0; index < interestingTopics.size(); index++ )
		{
			if( !otherHeader.interestingTopics.get( index ).equals( interestingTopics.get( index ) ) )
			{
				logger.info(
				    "!otherHeader.interestingTopics.get( " + index + " ).equals( interestingTopics.get( " + index + " ) )" );
				return false;
			}
		}
		return true;
	}
}
