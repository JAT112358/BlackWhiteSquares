package utils;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CreateBWSquaresXML {
	public static void create(int length) {
		try { 		
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			Document doc = docBuilder.newDocument();
			
			Element rootElement = doc.createElement("is:lineofsquares");
			Attr at1 = doc.createAttribute("xmlns:is");
			at1.setValue("http://alud2.ingenieria.deusto.es");
			rootElement.setAttributeNode(at1);
			Attr at2 = doc.createAttribute("length");
			at2.setValue(Integer.toString(length));
			rootElement.setAttributeNode(at2);
			Attr at3 = doc.createAttribute("label");
			at3.setValue("BWSquares");
			rootElement.setAttributeNode(at3);
			doc.appendChild(rootElement);
	 
			Element header = doc.createElement("is:distribution");
			rootElement.appendChild(header);
			
			// GENERATE RANDOM SQUARES
			int contW = 0;
			int contB = 0;
			for(int i=0; i<length; i++) {
				Element e = null;
				if(Math.random() < 0.5) {
					if(contW < 3) {
						System.out.print("[W] ");
						e = doc.createElement("is:white");
						contW++;
						contB = 0;
					} else {
						System.out.print("[B] ");
						e = doc.createElement("is:black");
						contB++;
						contW = 0;
					}
				} else {
					if(contB < 3)
					{
						System.out.print("[B] ");
						e = doc.createElement("is:black");
						contB++;
						contW = 0;
					} else {
						System.out.print("[W] ");
						e = doc.createElement("is:white");
						contW++;
						contB = 0;
					}
				}
				header.appendChild(e);
			}
			
//			Vector <Element> elements = new Vector<Element>();
//			elements.add(doc.createElement("is:white"));
//			elements.add(doc.createElement("is:black"));
//			elements.add(doc.createElement("is:white"));
//			elements.add(doc.createElement("is:white"));
//			elements.add(doc.createElement("is:white"));
//			elements.add(doc.createElement("is:black"));
//			elements.add(doc.createElement("is:white"));
//			elements.add(doc.createElement("is:white"));
//			elements.add(doc.createElement("is:black"));
//			elements.add(doc.createElement("is:white"));
//			elements.add(doc.createElement("is:black"));
//			elements.add(doc.createElement("is:white"));
//			elements.add(doc.createElement("is:white"));
//			elements.add(doc.createElement("is:black"));
//			elements.add(doc.createElement("is:black"));
//			elements.add(doc.createElement("is:white"));
//			elements.add(doc.createElement("is:white"));
//			elements.add(doc.createElement("is:white"));
			
//			for(int i=0; i<elements.size(); i++) {
//				header.appendChild(elements.get(i));
//			}
	 
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("data//blackwhitesquares" + length + ".xml"));
	 
			transformer.transform(source, result);
	 
			System.out.println("\nNew level saved!");
	 
		  } catch (ParserConfigurationException pce) {
			  pce.printStackTrace();
		  } catch (TransformerException tfe) {
			  tfe.printStackTrace();
		  }
	}
	
	public static void main(String [] args) {
		CreateBWSquaresXML.create(50);
	}
}