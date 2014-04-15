package algorithms;

import java.util.ArrayList;
import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.heuristic.EvaluationFunction;
import es.deusto.ingenieria.is.search.algorithms.log.SearchLog;
import es.deusto.ingenieria.is.search.formulation.Problem;
import es.deusto.ingenieria.is.search.formulation.State;

/**
 * Class defining the Hill Climbing method.
 */
public class HillClimbingwithLog extends HillClimbing {

	/**
	 * Constructor method.
	 * @param function evaluation function to be used by Hill Climbing.
	 */
	public HillClimbingwithLog(EvaluationFunction function){
		super(function);
	}
	
	public Node search(Problem problem, State initialState) {
		
		Node currentNode = null;
		Node bestSuccessorNode = null;
		boolean localBestFound = false;

		//Defines and initializes the search log.
		SearchLog searchLog = this.createSearchLog();
		
		currentNode = new Node(initialState);	
		currentNode.setG(this.getEvaluationFunction().calculateG(currentNode));
		currentNode.setH(this.getEvaluationFunction().calculateH(currentNode));

		while ( ! localBestFound) {			

			bestSuccessorNode = this.expand(currentNode, problem);
			
			if (bestSuccessorNode != null) {
				if(currentNode.compareTo(bestSuccessorNode) == 1) {
					localBestFound = true;
				} else {
					currentNode = bestSuccessorNode;
				}
				
				if (bestSuccessorNode != null) {
					if(problem.isFinalState(bestSuccessorNode.getState())) {
						currentNode = bestSuccessorNode;
						localBestFound = true;
					}
				}
			}
		}
		ArrayList<Node> bestSuccessorNodeList = new ArrayList<Node>();
		bestSuccessorNodeList.add(currentNode);
		searchLog.writeLog(bestSuccessorNodeList);
		
		// closes the search log.
		this.closeSearchLog(searchLog);
		
		return currentNode;
	}
}