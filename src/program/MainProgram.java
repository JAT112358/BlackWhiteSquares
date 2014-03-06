package program;

import components.Window;
import formulation.BWSProblem;
import gui.Start;

public class MainProgram 
{
	public static void main(String[] args) 
	{
		try 
		{
			BWSProblem problem = new BWSProblem();			
			problem.addInitialState(problem.gatherInitialPercepts());
			
			Window.getInstance().setContainer(new Start(problem));
			Window.getInstance().setVisible(true);	
		} 
		catch (Exception ex) 
		{
			System.err.println("% [Main Program] Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}