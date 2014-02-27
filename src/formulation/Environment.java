package formulation;

import es.deusto.ingenieria.is.search.formulation.State;

public class Environment extends State{

	private boolean[] squares;
	
	public Environment(boolean[] squares) 
	{
		this.squares = squares;
	}
	
	public void move (int pos) 
	{
		
	}
	
	@Override
	public boolean equals(Object arg0) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}
