package algorithms;

import java.util.ArrayList;
import java.util.List;
import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.heuristic.EvaluationFunction;
import es.deusto.ingenieria.is.search.algorithms.heuristic.HeuristicSearchMethod;
import es.deusto.ingenieria.is.search.formulation.Operator;
import es.deusto.ingenieria.is.search.formulation.Problem;
import es.deusto.ingenieria.is.search.formulation.State;

/**
 * Class defining the Hill Climbing method.
 */
public class HillClimbing extends HeuristicSearchMethod {

	/**
	 * Constructor method.
	 * @param function evaluation function to be used by Hill Climbing.
	 */
	public HillClimbing(EvaluationFunction function) {
		super(function);
	}

	public Node search(Problem problem, State initialState) {

		Node currentNode = null;
		Node bestSuccessorNode = null;
		boolean localBestFound = false;
		
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
		return currentNode;
	}

	protected Node expand(Node node, Problem problem) {
		List<Node> bestSuccessor = new ArrayList<Node>();
		Node bestSuccessorNode = null;
		Node successorNode = null;
		State currentState = null;
		State successorState = null;
		
		//If the current node and problem aren't null
		if (node != null && problem != null) {
			//Make the current state the state kept in the node.
			currentState = node.getState();		
			//If current state is not null
			if (currentState != null) {
				
				currentState = problem.gatherPercepts(currentState);
				
				//process the list of problem operators
				for (Operator operator : problem.getOperators()) {
					//Apply the operator to the current state
					successorState = operator.apply(currentState);
					//If the operator was applicable, a new successor state was generated
					if (successorState != null) {						
						//make a new node to keep the new successor state
						successorNode = new Node(successorState);
						//Set values for the various node's attributes
						successorNode.setOperator(operator.getName());
						successorNode.setParent(node);
						successorNode.setDepth(node.getDepth() + 1);
						//evaluation function = heuristic function
						successorNode.setG(this.getEvaluationFunction().calculateG(successorNode));
						successorNode.setH(this.getEvaluationFunction().calculateH(successorNode));
						
						//Add the new node to the list of successor nodes.
						bestSuccessor.add(successorNode);
					}
				}
				bestSuccessor.get(0).setG(this.getEvaluationFunction().calculateG(bestSuccessor.get(0)));
				bestSuccessor.get(0).setH(this.getEvaluationFunction().calculateH(bestSuccessor.get(0)));
				bestSuccessorNode = bestSuccessor.get(0);

				for(int i=1; i<bestSuccessor.size(); i++) {
					bestSuccessor.get(i).setG(this.getEvaluationFunction().calculateG(bestSuccessor.get(i)));
					bestSuccessor.get(i).setH(this.getEvaluationFunction().calculateH(bestSuccessor.get(i)));
					if(bestSuccessor.get(i).compareTo(bestSuccessorNode) <= 0) {
						bestSuccessorNode = bestSuccessor.get(i);
					}
				}
			}
		}	
		return bestSuccessorNode;
	}
}