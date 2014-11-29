package dSCHcalcProject.XML.FoodTypeXML;

public class FTxmlLoader
{
	// public List<EtelFajta> etelListaLoader(List<EtelFajta> lista, String
	// adatFajl)
	// {
	// try
	// {
	// File xMLfromFS = new File(adatFajl);
	// DocumentBuilderFactory docFact = DocumentBuilderFactory.newInstance();
	// DocumentBuilder docBuild = docFact.newDocumentBuilder();
	// Document domDoc = docBuild.parse(xMLfromFS);
	//
	// domDoc.getDocumentElement().normalize();
	// NodeList nodes = domDoc.getElementsByTagName(MyConstants.XML_FOOD_ELEMENT);
	//
	// for (int ind = 0; ind < nodes.getLength(); ind++)
	// {
	// Node node = nodes.item(ind);
	//
	// if (node.getNodeType() == Node.ELEMENT_NODE)
	// {
	// Element etel = (Element) node;
	// lista.add( new EtelFajta(
	// etel.getElementsByTagName(MyConstants.XML_NEVE_ELEMENT).item(0).getTextContent()
	// ,
	// etel.getElementsByTagName(MyConstants.XML_SZENHIDRATTARTALOM_ELEMENT).item(0).getTextContent()
	// ,
	// etel.getElementsByTagName(MyConstants.XML_GYAKORISAG_ELEMENT).item(0).getTextContent()
	// ,
	// etel.getElementsByTagName(MyConstants.XML_SZENHIDRATTARTALOMTIPUS_ELEMENT).item(0).getTextContent()
	// ));
	// }
	// }
	// }
	// catch (Exception e)
	// {
	// e.printStackTrace();
	// }
	// return lista;
	// }

}
