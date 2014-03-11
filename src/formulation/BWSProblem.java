package formulation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JTextArea;

import utils.ReadBWSquaresXML;
import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.SearchMethod;
import es.deusto.ingenieria.is.search.algorithms.blind.BreadthFSwithLog;
import es.deusto.ingenieria.is.search.formulation.Problem;
import es.deusto.ingenieria.is.search.formulation.State;
import es.deusto.ingenieria.is.search.xml.StateXMLReader;

public class BWSProblem extends Problem
{	
	public BWSProblem() 
	{
		this.createOperators();
	}
	
	// HOMEWORK 2/4 [Punto 2]
	public State gatherInitialPercepts() 
	{
		StateXMLReader stateXMLReader = new ReadBWSquaresXML("data/blackwhitesquares1.xml");
		return stateXMLReader.getState();
	}
	
	// HOMEWORK 2/4 [Punto 6]
	public boolean isFinalState(State state) 
	{
		if (state != null && state != null && state instanceof Environment) 
		{
			Environment environment = (Environment)state;
			return (environment.getSelectedIndex() >= environment.getSquares().size());
		} 
		return false;
	}
	
	// HOMEWORK 2/4 [Punto 4]
	protected void createOperators() 
	{
		this.addOperator(new Move(Move.ONE));
		this.addOperator(new Move(Move.TWO));
		this.addOperator(new Move(Move.FOUR));
	}
	
	public void solve(SearchMethod searchMethod, JTextArea console) 
	{		
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.S");
		Date beginDate = GregorianCalendar.getInstance().getTime();
		console.append("\n\n* Start '" + searchMethod.getClass().getSimpleName() + "' (" + formatter.format(beginDate) + ")");
		System.out.println("\n\n* Start '" + searchMethod.getClass().getSimpleName() + "' (" + formatter.format(beginDate) + ")");
		Node finalNode = searchMethod.search(this, this.getInitialStates().get(0));
		Date endDate = GregorianCalendar.getInstance().getTime();		
		console.append("\n* End   '" + searchMethod.getClass().getSimpleName() + "' (" + formatter.format(endDate) + ")");
		System.out.println("\n* End   '" + searchMethod.getClass().getSimpleName() + "' (" + formatter.format(endDate) + ")");
		
		long miliseconds = (int) Math.abs(beginDate.getTime() - endDate.getTime());
		long seconds = miliseconds / 1000;
		miliseconds %= 1000;		
		long minutes = seconds / 60;
		seconds %= 60;
		long hours = minutes / 60;
		minutes %= 60;
		
		String time = "* Serach lasts: ";
		time += (hours > 0) ? hours + " h " : " ";
		time += (minutes > 0) ? minutes + " m " : " ";
		time += (seconds > 0) ? seconds + "s " : " ";
		time += (miliseconds > 0) ? miliseconds + "ms " : " ";
		
		console.append("\n" + time);
		System.out.println("\n" + time);
		
		if (finalNode != null) {
			console.append("\n- Solution found!     :)");
			System.out.println("\n- Solution found!     :)");
			List<String> operators = new ArrayList<String>();
			searchMethod.solutionPath(finalNode, operators);
			searchMethod.createSolutionLog(operators);			
			console.append("\n- Final state:" + finalNode.getState());
			System.out.println("\n- Final state:" + finalNode.getState());
		} else {
			console.append("\n- Unable to find the solution! :(");
			System.out.println("\n- Unable to find the solution! :(");
		}
	}
	
	// HOMEWORK 2/4 [Punto 7]
	public static void main (String [] args) 
	{	
		// CONSTRUCTOR
		BWSProblem problem = new BWSProblem();
		
		// GATHER INITIAL PERCEPTS
		Environment e = (Environment) problem.gatherInitialPercepts();
		System.out.println("Initial percepts: " + e.toString());
		
		problem.addInitialState(e);
		
		// CREATE OPERATORS
		problem.createOperators();
		
		// SOLVE
		problem.solve(BreadthFSwithLog.getInstance(), new JTextArea());
		
		// IS FINAL STATE
		if(problem.isFinalState(e))
		{
			System.out.println("Is a final state!");
		} else {
			System.out.println("Is not a final state :(");
		}
	}
}