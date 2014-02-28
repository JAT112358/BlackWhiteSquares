package utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import es.deusto.ingenieria.is.search.formulation.State;
import es.deusto.ingenieria.is.search.xml.StateXMLReader;
import formulation.Environment;

public class ReadBWSquaresXML extends StateXMLReader {

	private boolean [] 	squares;

	public ReadBWSquaresXML(String xmlFile) 
	{
		super(xmlFile);
	}
	
	public State getState() 
	{
		return new Environment(this.squares);
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
	{
		
		try {		
			
			if (qName.equals("is:lineofsquares")) 
			{
				this.squares = new boolean[Integer.parseInt(attributes.getValue("length"))];
				for (int i=0; i<Integer.parseInt(attributes.getValue("length")); i++)
				{
					
				}
					
			} 
			else if (qName.equals("is:distribution"))
			{
				this.squares[0] = true;
			} 
		} 
		catch (Exception ex) 
		{
			System.out.println(this.getClass().getName() + ".startElement(): " + ex);
		}
	}
}