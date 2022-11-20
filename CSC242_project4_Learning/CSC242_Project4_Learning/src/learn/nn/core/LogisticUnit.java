package learn.nn.core;

/**
 * A LogisticUnit is a Unit that uses a sigmoid
 * activation function.
 */
public class LogisticUnit extends NeuronUnit {
	
	/**
	 * The activation function for a LogisticUnit is a 0-1 sigmoid
	 * centered at z=0: 1/(1+e^(-z)). (AIMA Fig 18.7)
	 */
	@Override
	public double activation(double z) {
		
		return 1.0/(1.0+Math.exp((-1.0)*z));
		
	}
	
	/**
	 * Derivative of the activation function for a LogisticUnit.
	 * For g(z)=1/(1+e^(-z)), g'(z)=g(z)*(1-g(z)) (AIMA p. 727).
	 * @see https://calculus.subwiki.org/wiki/Logistic_function#First_derivative
	 */
	public double activationPrime(double z) {
		double y = activation(z);
		return y * (1.0 - y);
	}

	/**
	 * Update this unit's weights using the logistic regression
	 * gradient descent learning rule (AIMA Eq 18.8).
	 * Remember: If there are n input attributes in vector x,
	 * then there are n+1 weights including the bias weight w_0. 
	 */
	@Override
	public void update(double[] x, double y, double alpha) {
		
		double hw_x=h_w(x);
		setWeight(0, getWeight(0)+alpha*(y-hw_x)*hw_x*(1.0-hw_x)); //update w_0
		for(int i=1;i<incomingConnections.size();i++) {
			
			setWeight(i, getWeight(i)+alpha*(y-hw_x)*hw_x*(1.0-hw_x)*x[i-1]);

		}
		
	}
	
}
