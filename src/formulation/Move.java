package formulation;

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
		Environment environment = (Environment) state;
		return environment.canMove(positions);
	}
	
	// HOMEWORK 2/4 [Punto 4]
	protected State effect(State state) 
	{
		Environment newEnvironment = (Environment)((Environment) state);
		newEnvironment.move(positions);
		return newEnvironment;
	}
}