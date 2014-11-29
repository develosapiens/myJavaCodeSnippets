package dSCHcalcProject.XML.FoodTypeXML;


public class FTxmlWriter
{
	// public void foodListSaver(List<EtelFajta> lista, String targetFileName)
	// {
	// try
	// {
	// DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	// DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	// Document docObj4FoodListData = docBuilder.newDocument();
	// Element rootElement =
	// docObj4FoodListData.createElement(MyConstants.XML_ROOT_ELEMENT);
	// docObj4FoodListData.appendChild(rootElement);
	//
	// for(EtelFajta food : lista)
	// {
	// Element etel =
	// docObj4FoodListData.createElement(MyConstants.XML_FOOD_ELEMENT);
	// rootElement.appendChild(etel);
	//
	// Element neve =
	// docObj4FoodListData.createElement(MyConstants.XML_NEVE_ELEMENT);
	// neve.appendChild(docObj4FoodListData.createTextNode(food.getNeve()));
	// etel.appendChild(neve);
	//
	// Element szenhidratTartalom =
	// docObj4FoodListData.createElement(MyConstants.XML_SZENHIDRATTARTALOM_ELEMENT);
	// szenhidratTartalom.appendChild(docObj4FoodListData.createTextNode(Double.toString(food.getSzenhidratTartalom())));
	// etel.appendChild(szenhidratTartalom);
	//
	// Element gyakorisag =
	// docObj4FoodListData.createElement(MyConstants.XML_GYAKORISAG_ELEMENT);
	// gyakorisag.appendChild(docObj4FoodListData.createTextNode(Double.toString(food.getGyakorisag())));
	// etel.appendChild(gyakorisag);
	//
	// Element szenhidratTartalomTipus =
	// docObj4FoodListData.createElement(MyConstants.XML_SZENHIDRATTARTALOMTIPUS_ELEMENT);
	// szenhidratTartalomTipus.appendChild(docObj4FoodListData.createTextNode(food.getSzenhidratTartalomTipusa().toString()));
	// etel.appendChild(szenhidratTartalomTipus);
	// }
	// TransformerFactory transformerFactory = TransformerFactory.newInstance();
	// Transformer transformer = transformerFactory.newTransformer();
	// DOMSource source = new DOMSource(docObj4FoodListData);
	// StreamResult result = new StreamResult(new File(targetFileName));
	// transformer.setOutputProperty(OutputKeys.INDENT,
	// MyConstants.XML_FILE_INDENT_FLAG);
	// transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
	// MyConstants.XML_FILE_INDENT_AMOUNT);
	// transformer.transform(source, result);
	// }
	// catch (Exception e)
	// {
	// e.printStackTrace();
	// }
	// }

}
