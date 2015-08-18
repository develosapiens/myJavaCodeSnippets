package net.develosapiens.transl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DictionarySingle
{
	private Logger            logger    = LogManager.getLogger( "develosapiens" );

	private List<DictElement> elements;
	private String            dictLanguage;
	private LocalDateTime     startTime = LocalDateTime.of( LocalDate.of( 2010, 12, 28 ), LocalTime.of( 10, 10, 10 ) );
	private LocalDateTime     endTime   = LocalDateTime.now();
	protected int             theId     = 0;

	private int               startId   = 0;
	private int               endId     = 0;
	private List<String>      interestingTopics;

	public List<String> getInterestingTopics()
	{
		return interestingTopics;
	}

	public void setInterestingTopics( List<String> interestingTopics )
	{
		this.interestingTopics = interestingTopics;
	}

	public boolean isIdUsedInElements( int id )
	{
		boolean itIs = false;
		if( elements != null )
		{
			for( DictElement dE : elements )
			{
				if( dE.getId() == id )
				{
					itIs = true;
				}
			}
		}
		return itIs;
	}

	public boolean isExpressionUsedInElements( String exp )
	{
		boolean itIs = false;
		if( elements != null )
		{
			for( DictElement d : elements )
			{
				if( d.getExpression().toLowerCase().contains( exp.toLowerCase() )
				    || exp.toLowerCase().contains( d.getExpression().toLowerCase() ) )
				{
					itIs = true;
				}
			}
		}
		return itIs;
	}

	public boolean isExpressionUsedInElementsStrict( String exp )
	{
		boolean itIs = false;
		if( elements != null )
		{
			for( DictElement d : elements )
			{
				if( d.getExpression().toLowerCase().equals( exp.toLowerCase() ) )
				{
					itIs = true;
				}
			}
		}
		return itIs;
	}

	public DictElement newDictElement( String exp )
	{
		DictElement de = null;
		if( exp == null )
		{
			logger.warn( "You tried to add an element with null expression!" );
			throw new IllegalArgumentException( "You tried to add an element with null expression!" );
		}
		else
		{
			if( elements == null )
			{
				elements = new ArrayList<DictElement>();
				theId = 0;
			}

			if( isExpressionUsedInElementsStrict( exp ) )
			{
				logger.warn( "You tried to add an element with an expression (" + exp + ") which we already have!" );
			}
			else
			{
				if( exp.isEmpty() )
				{
					logger.warn( "You tried to add an element with an empty expression!" );
					throw new IllegalArgumentException( "You tried to add an element with an empty expression!" );
				}
				else
				{
					++theId;
					de = new DictElement();
					de.setId( theId );
					de.setExpression( exp );
					de.setMyLanguage( dictLanguage );
					elements.add( de );
				}
			}
		}
		return de;
	}

	public void deleteDictElement( Integer id )
	{
		DictElement toRemove = getElementById( id );
		if( toRemove != null )
		{
			elements.remove( getElementById( id ) );
		}
		else
		{
			logger.warn( "Tried to remove non existent DictElement id: " + id + "!" );
		}
	}

	public DictElement getElementById( Integer id )
	{
		DictElement selectedOne = null;
		if( id == null )
		{
			logger.warn( "Method: getElementById() was called with null parameter!" );
			throw new IllegalArgumentException( "Method: getElementById() was called with null parameter!" );
		}
		for( DictElement dE : elements )
		{
			if( dE.getId() == id )
			{
				selectedOne = dE;
			}
		}
		return selectedOne;
	}

	public List<DictElement> selectByExpression( String exp )
	{
		List<DictElement> theSelectedDictElements = new ArrayList<>();
		if( exp == null )
		{
			logger.warn( "For the method: selectByExpression(String), the argument was null!!!" );
		}
		else if( exp.isEmpty() )
		{
			logger.warn( "For the method: selectByExpression(String), the argument was empty!!!" );
		}
		else
		{
			for( DictElement dE : elements )
			{
				if( dE.getExpression().toLowerCase().contains( ( exp.toLowerCase() ) ) )
				{
					theSelectedDictElements.add( dE );
				}
			}
		}
		return theSelectedDictElements;
	}

	public List<DictElement> selectByExpressionStrict( String exp )
	{
		List<DictElement> theSelectedDictElements = new ArrayList<>();
		if( exp == null )
		{
			logger.warn( "For the method: selectByExpression(String), the argument was null!!!" );
		}
		else if( exp.isEmpty() )
		{
			logger.warn( "For the method: selectByExpression(String), the argument was empty!!!" );
		}
		else
		{
			for( DictElement dE : elements )
			{
				if( dE.getExpression().equalsIgnoreCase( exp ) )
				{
					theSelectedDictElements.add( dE );
				}
			}
		}
		return theSelectedDictElements;
	}

	public List<DictElement> selectByIds()
	{
		List<DictElement> theSelectedDictElements = new ArrayList<>();
		int firstIndex, lastIndex;

		if( startId == 0 )
		{
			firstIndex = 1;
		}
		else
		{
			firstIndex = startId;
		}

		if( endId == 0 )
		{
			lastIndex = theId;
		}
		else
		{
			lastIndex = endId;
		}

		for( DictElement dE : elements )
		{
			if( dE.getId() >= firstIndex && dE.getId() <= lastIndex )
			{
				theSelectedDictElements.add( dE );
			}
		}
		return theSelectedDictElements;
	}

	public List<DictElement> selectByTime()
	{
		List<DictElement> theSelectedDictElements = new ArrayList<>();
		if( startTime.isAfter( endTime ) || startTime.isEqual( endTime ) )
		{
			logger.warn( "startTime: " + startTime.toString() + " endTime: " + endTime.toString() + " anomaly!" );
		}
		else
		{
			for( DictElement dE : elements )
			{
				if( ( dE.getTheDate().isAfter( startTime ) || dE.getTheDate().isEqual( startTime ) )
				    && ( dE.getTheDate().isBefore( endTime ) || dE.getTheDate().isEqual( endTime ) ) )
				{
					theSelectedDictElements.add( dE );
				}
			}
		}
		return theSelectedDictElements;
	}

	public List<DictElement> selectByTopics()
	{
		List<DictElement> theSelectedDictElements = new ArrayList<>();
		if( interestingTopics == null )
		{
			logger.warn( "interestingTopics is null!" );
		}
		else
		{
			for( String aTopic : interestingTopics )
			{
				for( DictElement dE : elements )
				{
					if( dE.getMyTopic().toLowerCase().equals( aTopic.toLowerCase() ) )
					{
						theSelectedDictElements.add( dE );
					}
				}
			}
		}
		return theSelectedDictElements;
	}

	public List<DictElement> getElements()
	{
		return elements;
	}

	public LocalDateTime getStartTime()
	{
		return startTime;
	}

	public void setStartTime( LocalDateTime startTime )
	{
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime()
	{
		return endTime;
	}

	public void setEndTime( LocalDateTime endTime )
	{
		this.endTime = endTime;
	}

	public int getStartId()
	{
		return startId;
	}

	public void setStartId( int startId )
	{
		this.startId = startId;
	}

	public int getEndId()
	{
		return endId;
	}

	public void setEndId( int endId )
	{
		this.endId = endId;
	}

	public String getDictLanguage()
	{
		return dictLanguage;
	}

	public void setDictLanguage( String lang )
	{
		if( lang == null )
		{
			logger.warn( "Can not set language, because it was null!" );
			throw new IllegalArgumentException( "Can not set language, because it was null!" );
		}
		if( lang.isEmpty() )
		{
			logger.warn( "Can not set language, because it was empty string!" );
			throw new IllegalArgumentException( "Can not set language, because it was empty string!" );
		}
		this.dictLanguage = lang;
	}

	public List<DictElement> getDictElementsInSortedListById()
	{
		List<DictElement> sorted = new ArrayList<DictElement>();
		List<Integer> ids = new ArrayList<Integer>();
		for( DictElement dE : elements )
		{
			ids.add( dE.getId() );
		}
		Collections.sort( ids );
		for( int id : ids )
		{
			sorted.add( getElementById( id ) );
		}
		return sorted;
	}

	public List<DictElement> getDictElementsInSortedListByExpression()
	{
		List<DictElement> sorted = new ArrayList<DictElement>();
		sorted.addAll( elements );
		sorted.sort( null );
		return sorted;
	}

	public String toString()
	{
		StringBuffer sB = new StringBuffer();
		sB.append( "DictLanguage: " );
		if( dictLanguage != null )
		{
			sB.append( dictLanguage );
		}
		else
		{
			sB.append( "- NULL -" );
		}
		sB.append( "\n" );
		sB.append( "theID: " + theId + "\n" );
		sB.append( "startId: " + startId + "\n" );
		sB.append( "endId: " + endId + "\n" );
		sB.append( "startTime: " );
		if( startTime == null )
		{
			sB.append( " - NULL -" );
		}
		else
		{
			sB.append( startTime.toString() );
		}
		sB.append( "\n" );
		sB.append( "endTime: " );
		if( endTime == null )
		{
			sB.append( " - NULL -" );
		}
		else
		{
			sB.append( endTime.toString() );
		}
		sB.append( "\n" );
		sB.append( "interestingTopics:\n" );
		if( interestingTopics == null )
		{
			sB.append( " - NULL -\n" );
		}
		else
		{
			for( String s : interestingTopics )
			{
				sB.append( s );
				sB.append( "\n" );
			}
		}
		sB.append( "\nDictElements:\n" );
		if( elements == null )
		{
			sB.append( " - NULL -\n" );
		}
		else
		{
			for( DictElement d : elements )
			{
				sB.append( d );
				sB.append( "--\n" );
			}
		}
		return sB.toString();
	}

	public boolean equals( DictionarySingle otherDS )
	{
		if( this.dictLanguage == null || otherDS.dictLanguage == null )
		{
			if( this.dictLanguage != null || otherDS.dictLanguage != null )
			{
				return false;
			}
		}
		else if( !this.dictLanguage.equals( otherDS.dictLanguage ) )
		{
			return false;
		}

		if( this.startId != otherDS.startId )
		{
			return false;
		}
		if( this.endId != otherDS.endId )
		{
			return false;
		}
		if( this.theId != otherDS.theId )
		{
			return false;
		}

		if( otherDS.getStartTime() != null && this.getStartTime() != null )
		{
			if( !this.getStartTime().equals( (LocalDateTime) otherDS.getStartTime() ) )
			{
				return false;
			}
		}
		if( this.getStartTime() == null )
		{
			if( otherDS.getStartTime() != null )
			{
				return false;
			}
		}
		if( otherDS.getStartTime() == null )
		{
			if( this.getStartTime() != null )
			{
				return false;
			}
		}

		if( otherDS.getEndTime() != null && this.getEndTime() != null )
		{
			if( !this.getEndTime().equals( (LocalDateTime) otherDS.getEndTime() ) )
			{
				return false;
			}
		}
		if( this.getEndTime() == null )
		{
			if( otherDS.getEndTime() != null )
			{
				return false;
			}
		}
		if( otherDS.getEndTime() == null )
		{
			if( this.getEndTime() != null )
			{
				return false;
			}
		}

		if( this.interestingTopics == null || otherDS.interestingTopics == null )
		{
			if( this.interestingTopics != null || otherDS.interestingTopics != null )
			{
				return false;
			}
		}
		else
		{
			for( String iTops : this.interestingTopics )
			{
				if( !otherDS.interestingTopics.contains( iTops ) )
				{
					return false;
				}
			}
			for( String iTops : otherDS.interestingTopics )
			{
				if( !this.interestingTopics.contains( iTops ) )
				{
					return false;
				}
			}
		}

		if( this.elements != null && otherDS.elements != null )
		{
			for( DictElement dE : this.getElements() )
			{
				DictElement other = otherDS.getElementById( dE.getId() );
				if( !dE.equals( other ) )
				{
					return false;
				}
			}
		}

		return true;
	}
}
