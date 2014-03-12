package formulation;

import utils.ReadBWSquaresXML;
import es.deusto.ingenieria.is.search.formulation.Operator;
import es.deusto.ingenieria.is.search.formulation.State;

public class Move extends Operator {
	public static enum Positions {
		ONE(1),
		TWO(2),
		FOUR(4);
		
		private int positions;
		
		private Positions(int positions) {
	        this.positions = positions;
	    }
		
		private int getPositions() {
			return this.positions;
		}
	}
	
	private Positions 		positions;
	
	public Move(Positions positions) {
		this.positions = positions;
	}

	// HOMEWORK 2/4 [Punto 4]
	protected boolean isApplicable(State state) {
		Environment currentEnvironment = (Environment) state;
		Environment newEnvironment = currentEnvironment.clone();	
		newEnvironment.setSelectedIndex(currentEnvironment.getSelectedIndex());
		switch(positions.getPositions()) {
			case 2:
				return currentEnvironment.getSquares().get(currentEnvironment.getSelectedIndex()).isWhite();
			case 4:
				return ! currentEnvironment.getSquares().get(currentEnvironment.getSelectedIndex()).isWhite();
			default:
				return true;
		}
	}
	
	// HOMEWORK 2/4 [Punto 4]
	protected State effect(State state) {
		Environment newEnvironment = ((Environment) state).clone();
		newEnvironment.move(positions.getPositions());
		return newEnvironment;
	}
	
	public String getName() {
		return this.positions.toString();
	}
	
	// HOMEWORK 2/4 [Punto 7]
	public static void main (String [] args) {
		ReadBWSquaresXML reader = new ReadBWSquaresXML("data/blackwhitesquares1.xml");
		Environment e = (Environment) reader.getState();
		
		// INITIAL
		System.out.println("Initial state: " +e.toString());
		
		// CONSTRUCTOR
		Move move = new Move(Move.Positions.TWO);
		
		// IS APPLICABLE
		if(move.isApplicable(e)) {
			System.out.println("Is applicable!");
			// EFFECT
			Environment newEnvironment = (Environment) move.effect(e);
			// FINAL WITH EFFECT
			System.out.println("Final state: " + newEnvironment.toString());
		} else {
			System.out.println("Is not applicable :(");
		}
	}
}