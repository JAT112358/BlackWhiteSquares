package formulation;

import java.util.ArrayList;

import utils.ReadBWSquaresXML;
import es.deusto.ingenieria.is.search.formulation.State;

public class Environment extends State
{
	private ArrayList<Square> 	squares;
	private int					selected;
	
	public Environment(ArrayList<Square> squares) 
	{
		this.squares = squares;
	}
	
	public ArrayList<Square> getSquares()
	{
		return this.squares;
	}
	
	public int getSelectedIndex()
	{
		return this.selected;
	}
	
	public void setSelectedIndex(int selected)
	{
		this.selected = selected;
	}
	
	// HOMEWORK 2/4 [Punto 4]
	public boolean canMove(int positions) 
	{
		return selected < squares.size() && (positions == Move.ONE || 
			   (squares.get(selected).isWhite() && positions == Move.TWO) || 
			   ( ! squares.get(selected).isWhite() && positions == Move.FOUR)); 
	}
	
	// HOMEWORK 2/4 [Punto 4]
	public void move(int positions) 
	{
		if(selected + positions <= squares.size()) {
			selected += positions;
		} else {
			System.out.println("Final: " + selected);
		}
	}
	
	// HOMEWORK 2/4 [Punto 3]
	public boolean equals(Object obj) 
	{
		return obj != null && obj instanceof Environment && ((Environment)obj).selected == this.selected;
	}

	public String toString() 
	{
		String text = "";
		for(int i = 0; i < squares.size(); i++)
		{
			text += squares.get(i);
			if(i == selected)
			{
				text +="*";
			}
			text += " ";
		}
		return text;
	}
	
	public boolean isFinalState()
	{
		return selected > this.squares.size() - 1;
	}
	
	public Environment clone() 
	{
		Environment newEnvironment = new Environment(this.squares);
		newEnvironment.setSelectedIndex(this.selected);
		return newEnvironment;
	}
	
	// HOMEWORK 2/4 [Punto 7]
	public static void main (String [] args)
	{
		ReadBWSquaresXML reader = new ReadBWSquaresXML("data/blackwhitesquares1.xml");
		Environment e = (Environment) reader.getState();
		
		// SQUARES
		System.out.println("Squares: " + e.toString());
		
		// CAN MOVE
		if(e.canMove(Move.TWO))
		{
			System.out.println("Can move " + Move.TWO + " positions!");
			// MOVE
			e.move(Move.TWO);
			System.out.println("Squares: " + e.toString());
		} else {
			System.out.println("Can not move " + Move.TWO + " positions :(");
		}
	}
}