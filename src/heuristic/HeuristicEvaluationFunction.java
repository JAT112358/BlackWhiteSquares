
package heuristic;

import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.heuristic.EvaluationFunction;
import formulation.Environment;

public class HeuristicEvaluationFunction extends EvaluationFunction {

	// g = El numero de pasos que nos han llevado hasta ese nodo. 
	// El nivel en el que se encuentra el nodo.
	@Override
	public double calculateG(Node arg0) {
		return arg0.getDepth();
	}

	// h = (numero de casillas que nos quedan / numero maximo de movimientos en 1 casilla)
	// El numero maximo de movimientos en este caso es siempre 4 porque en nuestro problema relajado
	// todas las casillas son negras.
	// El resultado se redondea hacia arriba.
	@Override
	public double calculateH(Node arg0) {
		System.out.println("total " + ((Environment) arg0.getState()).getSquares().size());
		System.out.println("selected "+ ((Environment) arg0.getState()).getSelectedIndex());
		
		int total = ((Environment) arg0.getState()).getSquares().size();
		int actual = ((Environment) arg0.getState()).getSelectedIndex();
		int squares = total - actual;
		double result = Math.ceil((double)squares / 4);
		
		System.out.println("Result " + result);
		
		return result;
	}
}