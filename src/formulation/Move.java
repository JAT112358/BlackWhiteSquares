package formulation;

import es.deusto.ingenieria.is.search.formulation.Operator;
import es.deusto.ingenieria.is.search.formulation.State;

public class Move extends Operator {

	public Move()
	{
		// TODO Move action
	}
	
	@Override
	protected State effect(State state) 
	{
		return null;
	}

	@Override
	protected boolean isApplicable(State state) 
	{
		Environment environment = (Environment) state;
		return false;
	}	
}