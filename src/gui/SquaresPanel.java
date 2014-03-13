package gui;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import formulation.Square;

public class SquaresPanel extends JPanel {
	private static final long 	serialVersionUID = 1766762925756982175L;
	private ArrayList<Square>	squares;				
	
	public SquaresPanel(ArrayList<Square> squares) {
		this.squares = squares;
		setLayout(new GridLayout(1, squares.size(), 0, 0));
		for(int i=0; i<squares.size(); i++) {
			squares.get(i).setPreferredSize(new Dimension(50, 50));
			add(squares.get(i));
		}
		setPreferredSize(new Dimension(50 * squares.size() + 10, 50));
		setMinimumSize(new Dimension(50 * squares.size() + 10, 50));
		setOpaque(false);
	}
	
	public ArrayList<Square> getSquares() {
		return this.squares;
	}
	
	public void restart() {
		for(Square square: squares) {
			square.setStep(-1);
		}
		updateUI();
	}
}