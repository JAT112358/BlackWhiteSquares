package gui;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridLayout;
import formulation.Square;

public class SquaresPanel extends JPanel 
{
	private static final long 	serialVersionUID = 1766762925756982175L;
	private Square[]			squares;
	
	public SquaresPanel(Square[] squares) 
	{
		this.squares = new Square[squares.length];
		setLayout(new GridLayout(1, squares.length, 0, 0));
		
		Dimension dimension = null;
		if(squares.length > 12)
		{
			int tam = 750/squares.length;
			dimension = new Dimension(tam, tam);
		} else {
			dimension = new Dimension(50, 50);
		}

		for(int i=0; i<squares.length; i++)
		{
			squares[i].setPreferredSize(dimension);
			add(squares[i]);
			this.squares[i] = squares[i];
		}	
	}
}