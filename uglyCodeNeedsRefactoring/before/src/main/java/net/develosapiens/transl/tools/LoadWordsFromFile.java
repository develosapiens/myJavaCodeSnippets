package net.develosapiens.transl.tools;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.develosapiens.transl.DictElement;
import net.develosapiens.transl.DictionarySingle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LoadWordsFromFile
{
	private Logger logger = LogManager.getLogger( "develosapiens" );

	public DictionarySingle doIt( String l ) throws XMLProcessingException
	{
		DictionarySingle dSin = new DictionarySingle();
		try
		{
			File file = new File( "conf/DICTIONARY4" + l + ".xml" );

			DocumentBuilderFactory docFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuild = docFact.newDocumentBuilder();
			Document domDoc = docBuild.parse( file );
			domDoc.getDocumentElement().normalize();

			// HEADER
			NodeList nl = domDoc.getElementsByTagName( "Header" );
			Node node = nl.item( 0 );

			// if( node.getNodeType() == Node.ELEMENT_NODE )
			// {
			Element anXmlElement = (Element) node;
			dSin.setDictLanguage( anXmlElement.getElementsByTagName( "dictLanguage" ).item( 0 ).getTextContent() );
			dSin.setStartTime( LocalDateTime.parse( anXmlElement.getElementsByTagName( "startTime" ).item( 0 )
			    .getTextContent() ) );
			dSin.setEndTime( LocalDateTime.parse( anXmlElement.getElementsByTagName( "endTime" ).item( 0 ).getTextContent() ) );
			dSin.setStartId( Integer.parseInt( anXmlElement.getElementsByTagName( "startId" ).item( 0 ).getTextContent() ) );
			dSin.setEndId( Integer.parseInt( anXmlElement.getElementsByTagName( "endId" ).item( 0 ).getTextContent() ) );
			List<String> iTopics = new ArrayList<String>();
			for( int i = 0; i < anXmlElement.getElementsByTagName( "topic" ).getLength(); i++ )
			{
				iTopics.add( anXmlElement.getElementsByTagName( "topic" ).item( i ).getTextContent() );
			}
			dSin.setInterestingTopics( iTopics );
			// }

			// ELEMENTS
			Node elementsNode = domDoc.getElementsByTagName( "Elements" ).item( 0 );
			NodeList elementNodes = ( (Element) elementsNode ).getElementsByTagName( "element" );
			for( int index = 0; index < elementNodes.getLength(); index++ )
			{
				// element
				DictElement dE;
				Element oneElementNode = (Element) elementNodes.item( index );
				// System.out.println( oneElementNode.getNodeName() );

				// expression
				Element expressionNode = (Element) oneElementNode.getElementsByTagName( "expression" ).item( 0 );
				// System.out.println( expressionNode.getTextContent() );
				dE = dSin.newDictElement( expressionNode.getTextContent() );

				// id
				Element idNode = (Element) oneElementNode.getElementsByTagName( "id" ).item( 0 );
				// System.out.println( idNode.getTextContent() );
				Integer ID = Integer.parseInt( idNode.getTextContent() );
				// if( dE.getId() != ID )
				// {
				// logger.warn( "Parsed id from xml file (" + ID +
				// ") was not equal to id given by Dictionary single ("
				// + dE.getId() + ") automatically!!!" );
				// throw new IllegalArgumentException( "Parsed id from xml file (" + ID
				// + ") was not equal to id given by Dictionary single (" + dE.getId() +
				// ") automatically!!!" );
				// }

				// myLanguage
				Element myLanguageNode = (Element) oneElementNode.getElementsByTagName( "myLanguage" ).item( 0 );
				// System.out.println( myLanguageNode.getTextContent() );
				String languageFromXML = myLanguageNode.getTextContent();
				// if( !languageFromXML.equals( dE.getMyLanguage() ) )
				// {
				// logger.warn( "Parsed myLang from xml file (" + languageFromXML
				// + ") was not equal to language given by Dictionary single (" +
				// dE.getMyLanguage() + ") automatically!!!" );
				// throw new IllegalArgumentException( "Parsed myLang from xml file (" +
				// languageFromXML
				// + ") was not equal to language given by Dictionary single (" +
				// dE.getMyLanguage() + ") automatically!!!" );
				// }

				// links4langs
				Element links4langsNode = (Element) oneElementNode.getElementsByTagName( "links4langs" ).item( 0 );
				NodeList links4aLangNodes = links4langsNode.getElementsByTagName( "links4aLang" );
				for( int indexOfLinks4aLangNodes = 0; indexOfLinks4aLangNodes < links4aLangNodes.getLength(); indexOfLinks4aLangNodes++ )
				{
					// links4aLang
					Element links4aLangNode = (Element) links4aLangNodes.item( indexOfLinks4aLangNodes );
					// aLang
					Element aLangNode = (Element) links4aLangNode.getElementsByTagName( "aLang" ).item( 0 );
					// System.out.println( aLangNode.getTextContent() );
					SortedSet<Integer> links4lang = new TreeSet<Integer>();
					// aLink
					NodeList aLinkNodes = links4aLangNode.getElementsByTagName( "aLink" );
					for( int indexForaLinkNodes = 0; indexForaLinkNodes < aLinkNodes.getLength(); indexForaLinkNodes++ )
					{
						Element aLinkNode = (Element) aLinkNodes.item( indexForaLinkNodes );
						// System.out.println( aLinkNode.getTextContent() );
						links4lang.add( Integer.parseInt( aLinkNode.getTextContent() ) );
					}
					dE.setLinksToALang( aLangNode.getTextContent(), links4lang );
				}

				// examples
				SortedSet<String> examplesSet = new TreeSet<String>();
				Element examplesNode = (Element) oneElementNode.getElementsByTagName( "examples" ).item( 0 );
				NodeList exampleNodes = examplesNode.getElementsByTagName( "example" );
				for( int indexForExampleNodes = 0; indexForExampleNodes < exampleNodes.getLength(); indexForExampleNodes++ )
				{
					Element exampleNode = (Element) exampleNodes.item( indexForExampleNodes );
					// System.out.println( exampleNode.getTextContent() );
					examplesSet.add( exampleNode.getTextContent() );
				}
				dE.setExamples( examplesSet );

				// myTopic
				Element topicNode = (Element) oneElementNode.getElementsByTagName( "myTopic" ).item( 0 );
				// System.out.println( topicNode.getTextContent() );
				dE.setMyTopic( topicNode.getTextContent() );

				// active
				Element activeNode = (Element) oneElementNode.getElementsByTagName( "active" ).item( 0 );
				// System.out.println( activeNode.getTextContent() );
				if( activeNode.getTextContent().equals( "true" ) )
				{
					dE.setActive( true );
				}
				else
				{
					dE.setActive( false );
				}

				Element theDateNode = (Element) oneElementNode.getElementsByTagName( "theDate" ).item( 0 );
				dE.setTheDate( LocalDateTime.parse( theDateNode.getTextContent() ) );
				// System.out.println( theDateNode.getTextContent() );
			}

		}
		catch( Exception e )
		{
			logger.error( e.getMessage() );
			e.printStackTrace();
			throw new XMLProcessingException( "LoadWordsFromFile\n" + e.getMessage() );
		}
		return dSin;
	}
}
