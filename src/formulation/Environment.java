package formulation;

import java.util.ArrayList;
import es.deusto.ingenieria.is.search.formulation.State;

public class Environment extends State
{
	private ArrayList<Square> squares;
	
	public Environment(ArrayList<Square> squares) 
	{
		this.squares = squares;
	}
	
	// HOMEWORK 2/4 [Punto 4]
	public int getSelectedSquare()
	{
		boolean enc = false;
		int i = 0;
		while( ! enc && i<squares.size())
		{
			if(squares.get(i).isSelected())
			{
				enc = true;
			} else {
				i++;
			}
		}
		return i;
	}
	
	// HOMEWORK 2/4 [Punto 4]
	public boolean canMove(int positions)
	{
		Square square = squares.get(getSelectedSquare());
		return positions == Move.ONE || 
			   (square.isWhite() && positions == Move.TWO) || 
			   ( ! square.isWhite() && positions == Move.FOUR); 
	}
	
	// HOMEWORK 2/4 [Punto 4]
	public void move(int positions)
	{
		int pos = getSelectedSquare();
		squares.get(pos).setSelected(false);
		if(pos + positions <= squares.size())
		{
			squares.get(pos+positions).setSelected(true);
		}
	}
	
	// HOMEWORK 2/4 [Punto 3]
	public boolean equals(Object obj) 
	{
		if(obj != null && obj instanceof Environment)
		{
			for(int i=0; i<squares.size(); i++)
			{
				if( ! ((Environment)obj).squares.get(i).equals(squares.get(i)))
				{
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public String toString() 
	{
		String text = "";
		for(int i=0; i<squares.size(); i++)
		{
			text += squares.get(i);
		}
		return text;
	}
}