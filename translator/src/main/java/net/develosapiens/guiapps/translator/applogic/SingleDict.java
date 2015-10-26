package net.develosapiens.guiapps.translator.applogic;

import static net.develosapiens.guiapps.translator.applogic.TransTools.checkDictElementAsParameterNotNull;
import static net.develosapiens.guiapps.translator.applogic.TransTools.checkDictElementListAsParameterNotNull;
import static net.develosapiens.guiapps.translator.applogic.TransTools.checkIntegerAsLinkNumberNotOutOfLimit;
import static net.develosapiens.guiapps.translator.applogic.TransTools.checkSingleDictAsParameterNotNull;
import static net.develosapiens.guiapps.translator.applogic.TransTools.checkStringAsParameterNotNullNotEmpty;

import java.util.ArrayList;
import java.util.Collections;
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
@XmlRootElement( name = "SingleDict" )
public class SingleDict
{
	@XmlElement( name = "the_header" )
	private HeaderOfSD myHeader;

	@XmlElementWrapper( name = "elements" )
	@XmlElement( name = "element" )
	private List<DictElement> elements;

	@XmlTransient
	private Logger logger = LogManager.getLogger( "develosapiens" );

	public SingleDict()
	{
		elements = new ArrayList<DictElement>();
		myHeader = new HeaderOfSD();
	}

	public HeaderOfSD getHeaderOfSD()
	{
		return myHeader;
	}

	public List<DictElement> getElements()
	{
		return elements;
	}

	public void setElements( List<DictElement> elements )
	{
		if( checkDictElementListAsParameterNotNull( elements ) )
		{
			this.elements = new ArrayList<DictElement>();
			for( DictElement de : elements )
			{
				if( checkDictElementAsParameterNotNull( de ) )
				{
					this.elements.add( de );
				}
			}
		}
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

	public boolean isIdUsedInElements( int id )
	{
		boolean itIs = false;
		for( DictElement dE : elements )
		{
			if( dE.getId() == id )
			{
				itIs = true;
			}
		}
		return itIs;
	}

	public boolean isExpressionUsedInElements( String exp )
	{
		boolean itIs = false;
		for( DictElement d : elements )
		{
			if( d.getExpression().equalsIgnoreCase( exp ) )
			{
				itIs = true;
			}
		}
		return itIs;
	}

	public DictElement addNewDictElement( String exp )
	{
		DictElement de = null;
		if( checkStringAsParameterNotNullNotEmpty( exp ) )
		{
			if( isExpressionUsedInElements( exp ) )
			{
				logger.warn( "You tried to add an element with an expression (" + exp + ") which we already have!" );
			}
			else
			{
				int theId = myHeader.getTheId();
				++theId;
				de = new DictElement();
				de.setId( theId );
				myHeader.setTheId( theId );
				de.setExpression( exp );
				de.setMyLanguage( myHeader.getDictLanguage() );
				elements.add( de );
			}
		}
		return de;
	}

	public void deleteDictElement( DictElement d )
	{
		if( d == null )
		{
			logger.warn( "Tried to remove null DictElement!" );
		}
		else
		{
			if( elements.contains( d ) )
			{
				elements.remove( d );
			}
			else
			{
				logger.warn( "Tried to remove a DictElement but it was not in DictElements' list! Do nothing!" );
			}
		}
	}

	public DictElement getElementById( Integer id )
	{
		DictElement selectedOne = null;
		if( checkIntegerAsLinkNumberNotOutOfLimit( id ) )
		{
			for( DictElement dE : elements )
			{
				if( dE.getId() == id )
				{
					selectedOne = dE;
				}
			}
		}
		return selectedOne;
	}

	public DictElement getElementByExpression( String expr )
	{
		DictElement selectedOne = null;
		if( checkStringAsParameterNotNullNotEmpty( expr ) )
		{
			for( DictElement dE : elements )
			{
				if( dE.getExpression().equalsIgnoreCase( expr ) )
				{
					selectedOne = dE;
				}
			}
		}
		return selectedOne;
	}

	public List<DictElement> selectElementsByExpression( String expr )
	{
		List<DictElement> theSelectedDictElements = new ArrayList<>();
		for( DictElement e : elements )
		{
			if( e.getExpression().toLowerCase().contains( expr.toLowerCase() )
			    || expr.toLowerCase().contains( e.getExpression().toLowerCase() ) )
			{
				theSelectedDictElements.add( e );
			}
		}
		Collections.sort( theSelectedDictElements );
		return theSelectedDictElements;
	}

	public List<DictElement> selectElementsByIds()
	{
		List<DictElement> theSelectedDictElements = new ArrayList<>();
		int firstIndex, lastIndex;

		if( myHeader.getStartId() == 0 )
		{
			firstIndex = 1;
		}
		else
		{
			firstIndex = myHeader.getStartId();
		}

		if( myHeader.getEndId() == 0 )
		{
			lastIndex = myHeader.getTheId();
		}
		else
		{
			lastIndex = myHeader.getEndId();
		}

		for( DictElement dE : elements )
		{
			if( dE.getId() >= firstIndex && dE.getId() <= lastIndex )
			{
				theSelectedDictElements.add( dE );
			}
		}
		return sortElementsByID( theSelectedDictElements );
	}

	private List<DictElement> sortElementsByID( List<DictElement> theSelectedDictElements )
	{
		List<Integer> indexList = new ArrayList<>();
		for( DictElement e : theSelectedDictElements )
		{
			indexList.add( e.getId() );
		}
		Collections.sort( indexList );
		List<DictElement> resultList = new ArrayList<>();
		for( Integer i : indexList )
		{
			for( DictElement d : theSelectedDictElements )
			{
				if( d.getId() == i )
				{
					resultList.add( d );
				}
			}
		}
		return resultList;
	}

	public List<DictElement> selectElementsByTime()
	{
		List<DictElement> theSelectedDictElements = new ArrayList<>();
		if( myHeader.getStartTime().isAfter( myHeader.getEndTime() ) )
		{
			logger.warn( "startTime: " + myHeader.getStartTime().toString() + " endTime: " + myHeader.getEndTime().toString()
			    + " anomaly!" );
		}
		else
		{
			for( DictElement dE : elements )
			{
				if( ( dE.getTheDate().isAfter( myHeader.getStartTime() ) || dE.getTheDate().isEqual( myHeader.getStartTime() ) )
				    && ( dE.getTheDate().isBefore( myHeader.getEndTime() )
				        || dE.getTheDate().isEqual( myHeader.getEndTime() ) ) )
				{
					theSelectedDictElements.add( dE );
				}
			}
		}
		return theSelectedDictElements;
	}

	public List<DictElement> selectElementsByTopics()
	{
		List<DictElement> theSelectedDictElements = new ArrayList<>();

		for( String aTopic : myHeader.getInterestingTopics() )
		{
			for( DictElement dE : elements )
			{
				if( dE.getMyTopic().equalsIgnoreCase( aTopic ) )
				{
					theSelectedDictElements.add( dE );
				}
			}
		}
		return theSelectedDictElements;
	}

	public String toString()
	{
		StringBuilder sB = new StringBuilder();
		sB.append( "Single Dictionary\n" );
		sB.append( myHeader );
		sB.append( "DictElements:\n" );
		for( DictElement e : elements )
		{
			sB.append( e.toString() + "\n" );
		}
		return sB.toString();
	}

	public boolean equals( SingleDict sdict )
	{
		boolean result = false;
		boolean elementsAreEqual = true;

		if( checkSingleDictAsParameterNotNull( sdict ) )
		{
			logger.debug(
			    "this.elements.size(): " + this.elements.size() + "   sdict.elements.size(): " + sdict.elements.size() );
			if( this.elements.size() == sdict.elements.size() )
			{
				for( int index = 0; index < elements.size(); index++ )
				{
					logger.debug( this.elements.get( index ) + "       " + sdict.elements.get( index ) );
					if( !this.elements.get( index ).equals( sdict.elements.get( index ) ) )
					{
						elementsAreEqual = false;
						logger
						    .info( "NOT EQUAL! !this.elements.get( " + index + " ).equals( sdict.elements.get( " + index + " ) )" );
					}
				}
				logger.debug( "elementsAreEqual: " + elementsAreEqual + "   this.myHeader.equals( sdict.myHeader: "
				    + this.myHeader.equals( sdict.myHeader ) );
				if( elementsAreEqual && this.myHeader.equals( sdict.myHeader ) )
				{
					result = true;
				}
				else
				{
					logger.warn( "NOT EQUAL! elementsAreEqual && this.myHeader.equals( sdict.myHeader )" );
				}
			}
			else
			{
				logger.info( "NOT EQUAL! this.elements.size() == sdict.elements.size() was not true" );
			}
		}
		else
		{
			logger.warn( "NOT EQUAL! checkSingleDictAsParameterNotNull" );
		}
		return result;
	}

	public void changeIDs( int idBefore, int idAfter )
	{
		if( isIdUsedInElements( idAfter ) )
		{
			logger
			    .warn( "changeIDs, idAfter: " + idAfter + " is in elements' list, so I can not change another id into it!" );
		}
		else
		{
			if( !isIdUsedInElements( idBefore ) )
			{
				logger.warn( "changeIDs, idBefore: " + idBefore + " is not in elements' list so I can not change it!" );
			}
			else
			{
				getElementById( idBefore ).setId( idAfter );
			}
		}
	}

	public void deleteElementByExpressionAndRearrangeElements( String expression )
	{
		if( checkStringAsParameterNotNullNotEmpty( expression ) )
		{
			if( isExpressionUsedInElements( expression ) )
			{
				deleteElementByIDandRearrangeElements( getElementByExpression( expression ).getId() );
			}
		}
	}

	public void deleteElementByIDandRearrangeElements( int idToDel )
	{
		if( checkIntegerAsLinkNumberNotOutOfLimit( idToDel ) && isIdUsedInElements( idToDel ) )
		{
			deleteDictElement( getElementById( idToDel ) );
			for( int i = idToDel; i <= getElements().size(); i++ )
			{
				changeIDs( i + 1, i );
			}
		}
	}

	public void deleteNumberFromLinkNumbersByLanguage( String lang, int id )
	{
		for( DictElement dE : elements )
		{
			for( Link link : dE.getLinks() )
			{
				if( link.getLanguage().equals( lang ) )
				{
					if( link.getLinkNumbers().contains( id ) )
					{
						logger.debug( "SingleDict, deleteNumberFromLinkNumbersByLanguage deleting: " + id + " id from " + lang
						    + " linkNumbers. (DictElement id: " + dE.getId() + " )" );
						link.deletingALinkNumber( id );
					}
				}
			}
		}
	}

	public void changeNumbersInLinkNumbersByLanguage( String lang, int idBefore, int idAfter )
	{
		for( DictElement dE : elements )
		{
			for( Link link : dE.getLinks() )
			{
				if( link.getLanguage().equals( lang ) )
				{
					if( link.getLinkNumbers().contains( idBefore ) )
					{
						logger.debug( "SingleDict, changeNumbersInLinkNumbersByLanguage changing: " + idBefore + " to " + idAfter
						    + " , " + lang + " linkNumbers. (DictElement id: " + dE.getId() + " )" );
						link.deletingALinkNumber( idBefore );
						link.addingALinkNumber( idAfter );
					}
				}
			}
		}
	}
}
