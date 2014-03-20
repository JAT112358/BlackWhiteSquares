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
import es.deusto.ingenieria.is.search.algorithms.blind.BreadthFS;
import es.deusto.ingenieria.is.search.formulation.Problem;
import es.deusto.ingenieria.is.search.formulation.State;
import es.deusto.ingenieria.is.search.xml.StateXMLReader;
import gui.SquaresPanel;

public class BWSProblem extends Problem{	
	private String 	path;
	private boolean	logEnable; 
	
	public BWSProblem(String path) {
		this.createOperators();
		this.path = path;
		this.logEnable = false;
	}
	
	// HOMEWORK 2/4 [Punto 2]
	public State gatherInitialPercepts() {
		StateXMLReader stateXMLReader = new ReadBWSquaresXML(path);
		return stateXMLReader.getState();
	}
	
	// HOMEWORK 2/4 [Punto 6]
	public boolean isFinalState(State state) {
		if (state != null && state instanceof Environment) {
			Environment environment = (Environment)state;
			return (environment.getSelectedIndex() >= environment.getSquares().size());
		} 
		return false;
	}
	
	// HOMEWORK 2/4 [Punto 4]
	protected void createOperators() {
		this.addOperator(new Move(Move.Positions.ONE));
		this.addOperator(new Move(Move.Positions.TWO));
		this.addOperator(new Move(Move.Positions.FOUR));
	}
	
	public void restart() {
		this.addInitialState(gatherInitialPercepts());
	}
	
	public void setLogEnable(boolean enable) {
		this.logEnable = enable;
	}
	
	public void solve(SearchMethod searchMethod, JTextArea console, SquaresPanel squaresPanel) {		
		console.append("----- Start " + searchMethod.getClass().getSimpleName() + " algorithm -----");
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.S");
		Date beginDate = GregorianCalendar.getInstance().getTime();
		console.append("\nStart:\t" + formatter.format(beginDate));
		System.out.println("\n\n* Start '" + searchMethod.getClass().getSimpleName() + "' (" + formatter.format(beginDate) + ")");
		Node finalNode = searchMethod.search(this, this.getInitialStates().get(0));
		Date endDate = GregorianCalendar.getInstance().getTime();		
		console.append("\nEnd:\t" + formatter.format(endDate));
		System.out.println("\n* End   '" + searchMethod.getClass().getSimpleName() + "' (" + formatter.format(endDate) + ")");
		
		long miliseconds = (int) Math.abs(beginDate.getTime() - endDate.getTime());
		long seconds = miliseconds / 1000;
		miliseconds %= 1000;		
		long minutes = seconds / 60;
		seconds %= 60;
		long hours = minutes / 60;
		minutes %= 60;
		
		String time = "Serach lasts:\t";
		time += (hours > 0) ? hours + "h" : "";
		time += (minutes > 0) ? minutes + "m" : "";
		time += (seconds > 0) ? seconds + "s" : "";
		time += (miliseconds > 0) ? miliseconds + "ms" : "";
		
		console.append("\n" + time);
		System.out.println("\n" + time);
		
		if (finalNode != null) {
			console.append("\nSolution found!");
			System.out.println("\n- Solution found!");
			List<String> operators = new ArrayList<String>();
			searchMethod.solutionPath(finalNode, operators);
			if(logEnable)
				searchMethod.createSolutionLog(operators);			
			console.append("\nFinal state: " + finalNode.getState());
			System.out.println("\n- Final state:" + finalNode.getState());
			console.append("\nSteps: ");
			System.out.println("\nSteps: ");
			
			int stepIndex = 0;
			squaresPanel.getSquares().get(0).setStep(stepIndex);
			for(int i=0; i<operators.size(); i++) {
				String movement = "\n - Move ";
				if(Move.Positions.ONE.toString().equals(operators.get(i))){
					movement += "1";
					stepIndex += 1;
				} else if(Move.Positions.TWO.toString().equals(operators.get(i))){
					movement += "2";
					stepIndex += 2;
				} else if(Move.Positions.FOUR.toString().equals(operators.get(i))){
					movement += "4";
					stepIndex +=4;
				}
				if(stepIndex < squaresPanel.getSquares().size()) {
					squaresPanel.getSquares().get(stepIndex).setStep(i+1);
					squaresPanel.updateUI();
				}
				movement += " positions.";
				console.append(movement);
				System.out.println(movement);
			}
			System.out.println("\n");
			console.append("\n\nTotal steps:\t" + operators.size());
		} else {
			console.append("\n- Unable to find the solution! :(");
			System.out.println("\n- Unable to find the solution! :(");
		}
	}
	
	// HOMEWORK 2/4 [Punto 7]
	public static void main (String [] args) {	
		// CONSTRUCTOR
		BWSProblem problem = new BWSProblem("data/blackwhitesquares1.xml");
		
		// GATHER INITIAL PERCEPTS
		System.out.println("Initial percepts: " + problem.gatherInitialPercepts().toString());
		problem.addInitialState(problem.gatherInitialPercepts());
		
		// CHECK IS FINAL STATE		
		Environment environment = new Environment(new ArrayList<Square>());
		environment.setSelectedIndex(19);
		if(problem.isFinalState(environment)) {
			System.out.println("Is final state!");
		} else {
			System.out.println("Is not a final state :(");
		}
		// SOLVE
		problem.solve(BreadthFS.getInstance(), new JTextArea(), new SquaresPanel(((Environment) problem.gatherInitialPercepts()).getSquares()));
	}
}