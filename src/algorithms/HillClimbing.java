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

		while ( ! localBestFound) {
				if (problem.isFinalState(currentNode.getState())){
					localBestFound = true;
				}else{
					bestSuccessorNode = this.expand(currentNode, problem, new ArrayList<State>(), 
							new ArrayList<State>()).get(0);
					
					System.out.println("search..."+bestSuccessorNode.getH());
					System.out.println("search2..."+currentNode.getH());
					
					if (bestSuccessorNode != null) {
						if(currentNode.compareTo(bestSuccessorNode) == 1 || currentNode.compareTo(bestSuccessorNode) == 0) {
							localBestFound = true;
						} else {
							currentNode = bestSuccessorNode;
						}
					}
				}
			
		}
		if (localBestFound) {
			return currentNode;
		} else {
			return null;
		}
	}

	protected List<Node> expand(Node node, Problem problem, List<State> generatedStates, List<State> expandedStates) {
		List<Node> bestSuccessor = new ArrayList<Node>();
		Node bestSuccessorNode = null;
		Node currentNode = null;
		Node successorNode = null;
		State currentState = null;
		State successorState = null;
		
		//If the current node and problem aren't null
		if (node != null && problem != null) {
			//Make the current state the state kept in the node.
			currentState = node.getState();		
			//If current state is not null
			if (currentState != null) {
				currentNode = new Node(currentState);
				if( ! problem.isFullyObserved(currentState)) {
					System.out.println("no es observable");
					currentNode.setState(problem.gatherPercepts(currentState));
					currentState = currentNode.getState();
				}
				bestSuccessorNode = currentNode;
				bestSuccessorNode.setH(this.getEvaluationFunction().calculateH(bestSuccessorNode));
				
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
						successorNode.setH(this.getEvaluationFunction().calculateH(successorNode));
						
						//Add the new node to the list of successor nodes.
						if(successorNode.compareTo(bestSuccessorNode) == -1) {
							bestSuccessorNode = successorNode;
						}
					}
				}
			}
		}	
		bestSuccessor.add(bestSuccessorNode);
		return bestSuccessor;
	}
}