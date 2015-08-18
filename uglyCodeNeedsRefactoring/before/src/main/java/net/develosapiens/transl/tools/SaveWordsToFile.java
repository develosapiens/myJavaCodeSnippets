package net.develosapiens.transl.tools;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.develosapiens.transl.DictElement;
import net.develosapiens.transl.DictionarySingle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SaveWordsToFile
{
	private Logger logger = LogManager.getLogger( "develosapiens" );

	public void writeOut( DictionarySingle dS ) throws XMLProcessingException
	{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try
		{
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement( "SingleDictionary" );
			doc.appendChild( rootElement );
			Element header = doc.createElement( "Header" );
			rootElement.appendChild( header );
			Element elements = doc.createElement( "Elements" );
			rootElement.appendChild( elements );

			// DictionarySingle: dictLanguage
			Element dictLanguage = doc.createElement( "dictLanguage" );
			dictLanguage.appendChild( doc.createTextNode( dS.getDictLanguage() ) );
			header.appendChild( dictLanguage );

			// DictionarySingle: startTime
			Element startTime = doc.createElement( "startTime" );
			startTime.appendChild( doc.createTextNode( dS.getStartTime().toString() ) );
			header.appendChild( startTime );

			// DictionarySingle: endTime
			Element endTime = doc.createElement( "endTime" );
			endTime.appendChild( doc.createTextNode( dS.getEndTime().toString() ) );
			header.appendChild( endTime );

			// DictionarySingle: startId
			Element startId = doc.createElement( "startId" );
			startId.appendChild( doc.createTextNode( ( (Integer) dS.getStartId() ).toString() ) );
			header.appendChild( startId );

			// DictionarySingle: endId
			Element endId = doc.createElement( "endId" );
			endId.appendChild( doc.createTextNode( ( (Integer) dS.getEndId() ).toString() ) );
			header.appendChild( endId );

			// DictionarySingle: interestingTopics
			Element interestingTopics = doc.createElement( "interestingTopics" );
			header.appendChild( interestingTopics );

			// DictionarySingle: topic
			for( String t : dS.getInterestingTopics() )
			{
				Element topic = doc.createElement( "topic" );
				topic.appendChild( doc.createTextNode( t ) );
				interestingTopics.appendChild( topic );
			}

			for( DictElement dE : dS.getDictElementsInSortedListById() )
			{
				Element element = doc.createElement( "element" );

				// DictElement: id
				Element id = doc.createElement( "id" );
				id.appendChild( doc.createTextNode( ( (Integer) dE.getId() ).toString() ) );
				element.appendChild( id );

				// DictElement: expression
				Element expression = doc.createElement( "expression" );
				expression.appendChild( doc.createTextNode( dE.getExpression() ) );
				element.appendChild( expression );

				// DictElement: links
				Element links4langs = doc.createElement( "links4langs" );

				for( String s : dE.getLinks().keySet() )
				{
					Element links4aLang = doc.createElement( "links4aLang" );
					Element aLang = doc.createElement( "aLang" );
					aLang.appendChild( doc.createTextNode( s ) );
					links4aLang.appendChild( aLang );
					for( Integer i : dE.getLinksToALang( s ) )
					{
						Element aLink = doc.createElement( "aLink" );
						aLink.appendChild( doc.createTextNode( ( (Integer) i ).toString() ) );
						links4aLang.appendChild( aLink );
					}
					links4langs.appendChild( links4aLang );
				}
				element.appendChild( links4langs );

				element.appendChild( links4langs );

				// DictElement: myLanguage
				Element myLanguage = doc.createElement( "myLanguage" );
				myLanguage.appendChild( doc.createTextNode( dE.getMyLanguage() ) );
				element.appendChild( myLanguage );

				// DictElement: examples
				Element examples = doc.createElement( "examples" );
				for( String s : dE.getExamples() )
				{
					Element example = doc.createElement( "example" );
					example.appendChild( doc.createTextNode( s ) );
					examples.appendChild( example );
				}
				element.appendChild( examples );

				// DictElement: theDate
				Element theDate = doc.createElement( "theDate" );
				theDate.appendChild( doc.createTextNode( dE.getTheDate().toString() ) );
				element.appendChild( theDate );

				// DictElement: myTopic
				Element myTopic = doc.createElement( "myTopic" );
				myTopic.appendChild( doc.createTextNode( dE.getMyTopic() ) );
				element.appendChild( myTopic );

				// DictElement: active
				Element active = doc.createElement( "active" );
				if( dE.isActive() )
				{
					active.appendChild( doc.createTextNode( "true" ) );
				}
				else
				{
					active.appendChild( doc.createTextNode( "false" ) );
				}
				element.appendChild( active );

				elements.appendChild( element );
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty( OutputKeys.INDENT, "yes" );
			transformer.setOutputProperty( "{http://xml.apache.org/xslt}indent-amount", "2" );
			DOMSource source = new DOMSource( doc );
			StreamResult result = new StreamResult( new File( "conf/DICTIONARY4" + dS.getDictLanguage() + ".xml" ) );

			transformer.transform( source, result );
		}
		catch( Exception e )
		{
			logger.error( e.getMessage() );
			e.printStackTrace();
			throw new XMLProcessingException( "dS-SaveWordsToFile\n" + e.getMessage() );
		}
	}
}
