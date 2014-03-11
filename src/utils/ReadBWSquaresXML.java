package utils;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import es.deusto.ingenieria.is.search.formulation.State;
import es.deusto.ingenieria.is.search.xml.StateXMLReader;
import formulation.Environment;
import formulation.Square;

public class ReadBWSquaresXML extends StateXMLReader 
{
	private ArrayList<Square> 	squares;

	public ReadBWSquaresXML(String xmlFile) 
	{
		super(xmlFile);
	}
	
	public State getState() 
	{
		Environment environment = new Environment(this.squares);
		environment.setSelectedIndex(0);
		return environment;
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
	{
		try {		
			if (qName.equals("is:lineofsquares")) 
			{
				this.squares = new ArrayList<Square>();	
			} 
			else if (qName.equals("is:white") || qName.equals("is:black"))
			{
				this.squares.add(new Square(qName.equals("is:white")));
			}
		} 
		catch (Exception ex) 
		{
			System.out.println(this.getClass().getName() + ".startElement(): " + ex);
		}
	}
}