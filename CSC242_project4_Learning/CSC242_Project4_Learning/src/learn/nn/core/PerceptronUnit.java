package learn.nn.core;

/**
 * A PerceptronUnit is a Unit that uses a hard threshold
 * activation function.
 */
public class PerceptronUnit extends NeuronUnit {
	
	/**
	 * The activation function for a Perceptron is a hard 0/1 threshold
	 * at z=0. (AIMA Fig 18.7)
	 */
	@Override
	public double activation(double z) {
		if(z >= 0) {
			return 1.0;
		}
		return 0.0;
	}
	
	/**
	 * Update this unit's weights using the Perceptron learning
	 * rule (AIMA Eq 18.7).
	 * Remember: If there are n input attributes in vector x,
	 * then there are n+1 weights including the bias weight w_0. 
	 */
	@Override
	public void update(double[] x, double y, double alpha) {
		
		double hw_x = h_w(x);
		setWeight(0, getWeight(0)+alpha*(y-hw_x)); //update w_0
		for(int i=1;i<incomingConnections.size();i++) {
			
			setWeight(i, getWeight(i)+alpha*(y-hw_x)*x[i-1]);
			
		}
	}
}
