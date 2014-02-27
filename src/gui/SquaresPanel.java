package gui;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;

public class SquaresPanel extends JPanel {

	private static final long serialVersionUID = 1766762925756982175L;

	private JPanel[]	squares;
	
	public SquaresPanel(boolean[] squares) 
	{
		this.squares = new JPanel[squares.length];
		setLayout(new GridLayout(1, squares.length, 0, 0));
		
		Dimension dimension = null;
		if(squares.length > 12)
		{
			int tam = 760/squares.length;
			dimension = new Dimension(tam, tam);
		} else {
			dimension = new Dimension(50, 50);
		}

		for(int i=0; i<squares.length; i++)
		{
			JPanel square = new JPanel();
			if( ! squares[i])
			{
				square.setBackground(Color.BLACK);
			}
			square.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			square.setPreferredSize(dimension);
			add(square);
			this.squares[i] = square;
		}	
	}
}