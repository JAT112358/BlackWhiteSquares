package program;

import javax.swing.UIManager;

import components.Window;
import formulation.BWSProblem;
import gui.Start;

public class MainProgram 
{
	public static void main(String[] args) 
	{
		try 
		{
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			
			BWSProblem problem = new BWSProblem();			
			problem.addInitialState(problem.gatherInitialPercepts());
			
			Window.getInstance().setContainer(new Start(problem));
			Window.getInstance().setVisible(true);	
		} 
		catch (Exception e) 
		{
			System.err.println("% [Main Program] Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}