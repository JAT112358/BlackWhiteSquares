package formulation;

import utils.ReadBWSquaresXML;
import es.deusto.ingenieria.is.search.formulation.Operator;
import es.deusto.ingenieria.is.search.formulation.State;

public class Move extends Operator 
{
	public static int 	ONE = 1;
	public static int 	TWO = 2;
	public static int 	FOUR = 4;
	
	private int 		positions;
	
	public Move(int positions)
	{
		this.positions = positions;
	}

	// HOMEWORK 2/4 [Punto 4]
	protected boolean isApplicable(State state) 
	{
		Environment currentEnvironment = (Environment) state;
		if(currentEnvironment.canMove(positions))
		{
			Environment newEnvironment = currentEnvironment.clone();	
			newEnvironment.setSelectedIndex(currentEnvironment.getSelectedIndex());
			return ! newEnvironment.isFinalState();
			// return (newEnv.getQueenAttacks(queen) == 0);
		} else {
			return false;
		}
	}
	
	// HOMEWORK 2/4 [Punto 4]
	protected State effect(State state) 
	{
		Environment newEnvironment = ((Environment) state).clone();
		System.out.println("Selected: " + newEnvironment.toString());
		newEnvironment.move(positions);
		return newEnvironment;
	}
	
	// HOMEWORK 2/4 [Punto 7]
	public static void main (String [] args)
	{
		ReadBWSquaresXML reader = new ReadBWSquaresXML("data/blackwhitesquares1.xml");
		Environment e = (Environment) reader.getState();
		
		// INITIAL
		System.out.println("Initial state: " +e.toString());
		
		// CONSTRUCTOR
		Move move = new Move(Move.TWO);
		
		// IS APPLICABLE
		if(move.isApplicable(e))
		{
			System.out.println("Is applicable!");
			// EFFECT
			move.effect(e);
			// FINAL WITH EFFECT
			System.out.println("Final state: " + e.toString());
		} 
		else 
		{
			System.out.println("Is not applicable :(");
		}
	}
}