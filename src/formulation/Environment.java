package formulation;

import java.util.ArrayList;

import utils.ReadBWSquaresXML;
import es.deusto.ingenieria.is.search.formulation.State;

public class Environment extends State {
	private ArrayList<Square> 	squares;
	private int					selected;
	
	public Environment(ArrayList<Square> squares) {
		this.squares = squares;
	}
	
	public ArrayList<Square> getSquares() {
		return this.squares;
	}
	
	public int getSelectedIndex() {
		return this.selected;
	}
	
	public void setSelectedIndex(int selected) {
		this.selected = selected;
	}
	
	// HOMEWORK 2/4 [Punto 4]
	public void move(int positions) {
		if(selected + positions <= squares.size()) {
			selected += positions;
		}
	}
	
	// HOMEWORK 2/4 [Punto 3]
	public boolean equals(Object obj) {
		return obj != null && obj instanceof Environment && ((Environment)obj).selected == this.selected;
	}

	public String toString() {
		String text = "";
		for(int i = 0; i < squares.size(); i++) {
			text += squares.get(i).toString();
			if(i == selected) {
				text += "*";
			}
			text += " ";
		}
		return text;
	}
	
	public Environment clone() {
		Environment newEnvironment = new Environment(this.squares);
		newEnvironment.setSelectedIndex(this.selected);
		return newEnvironment;
	}
	
	// HOMEWORK 2/4 [Punto 7]
	public static void main (String [] args) {
		ReadBWSquaresXML reader = new ReadBWSquaresXML("data/blackwhitesquares1.xml");
		
		// CONSTRUCTOR
		Environment e1 = (Environment) reader.getState();
		
		// SQUARES
		System.out.println("Squares: " + e1.toString());
		
		// MOVE
		e1.move(2);
		System.out.println("Squares: " + e1.toString());

		// EQUALS & CLONE
		Environment e2 = e1.clone();
		if(e2.equals(e1)) {
			System.out.println("Are equals!");
		} else {
			System.out.println("Are not equals :(");
		}
	}
}