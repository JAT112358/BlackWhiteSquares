package formulation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import components.Window;
import algorithms.HillClimbing;
import utils.ReadBWSquaresXML;
import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.SearchMethod;
import es.deusto.ingenieria.is.search.formulation.Problem;
import es.deusto.ingenieria.is.search.formulation.State;
import es.deusto.ingenieria.is.search.xml.StateXMLReader;
import gui.SquaresPanel;
import heuristic.HeuristicEvaluationFunction;

public class BWSProblem extends Problem{	
	private String 	path;
	private boolean	logEnable; 
	
	public BWSProblem(String path) {
		this.createOperators();
		this.path = path;
		this.logEnable = false;
	}
	
	public State gatherInitialPercepts() {
		StateXMLReader stateXMLReader = new ReadBWSquaresXML(path);
		return stateXMLReader.getState();
	}
	
	public State gatherPercepts(State state) {
		Environment environment = (Environment)state;
		if(environment.getSquares().get(environment.getSelectedIndex()).getColor() == 'x') {
			String color = "";
			do {
				color = JOptionPane.showInputDialog(Window.getInstance(), "Introduce the color (b/w): ");
			} while(color == null || (color.charAt(0) != 'b' && color.charAt(0) != 'w'));
			((Environment) state).getSquares().get(((Environment) state).getSelectedIndex()).setColor(color.charAt(0));
		}
		return state;
	}
	
	public boolean isFinalState(State state) {
		if (state != null && state instanceof Environment) {
			Environment environment = (Environment)state;
			return (environment.getSelectedIndex() >= environment.getSquares().size());
		} 
		return false;
	}
	
	protected void createOperators() {
		this.addOperator(new Move(Move.Positions.ONE));
		this.addOperator(new Move(Move.Positions.TWO));
		this.addOperator(new Move(Move.Positions.FOUR));
	}
	
	public void restart() {
		this.getInitialStates().set(0, gatherInitialPercepts());		
	}
	
	public void setLogEnable(boolean enable) {
		this.logEnable = enable;
	}
	
	public boolean isFullyObserved(State state) {
		if (state != null && state instanceof Environment) {
			Environment environment = (Environment)state;
			for(Square square : environment.getSquares()) {
				if(square.getColor() == 'x') {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public void solve(SearchMethod searchMethod, JTextArea console, SquaresPanel squaresPanel) {		
		console.append("----- Start " + searchMethod.getClass().getSimpleName() + " algorithm -----");
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.S");
		Date beginDate = GregorianCalendar.getInstance().getTime();
		long start = System.nanoTime();
		console.append("\nStart:\t" + formatter.format(beginDate));
		System.out.println("\n\n* Start '" + searchMethod.getClass().getSimpleName() + "' (" + formatter.format(beginDate) + ")");
		Node finalNode = searchMethod.search(this, this.getInitialStates().get(0));
		Date endDate = GregorianCalendar.getInstance().getTime();
		long end = System.nanoTime();
		console.append("\nEnd:\t" + formatter.format(endDate));
		System.out.println("\n* End   '" + searchMethod.getClass().getSimpleName() + "' (" + formatter.format(endDate) + ")");
		
		long miliseconds = Math.abs(beginDate.getTime() - endDate.getTime());
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
		time +=  miliseconds + "ms";
		
		console.append("\n" + time);
		console.append("\nSerach lasts:\t" + (end - start) + "ns");
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
					
			ArrayList<Square> finalSquares = ((Environment) finalNode.getState()).getSquares();
			
			int stepIndex = 0;
			finalSquares.get(0).setStep(stepIndex);
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
				if(stepIndex < finalSquares.size()) {
					finalSquares.get(stepIndex).setStep(i+1);
				}
				movement += " positions.";
				console.append(movement);
				System.out.println(movement);
			}
			squaresPanel.setFinalState(finalSquares);
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
		BWSProblem problem = new BWSProblem("data/blackwhitesquaresPartialpercepts1.xml");
		problem.addInitialState(problem.gatherInitialPercepts());
		
		// SOLVE
		problem.solve(new HillClimbing(new HeuristicEvaluationFunction()), new JTextArea(), new SquaresPanel(((Environment) problem.gatherInitialPercepts()).getSquares()));
	}
}