package learn.lc.core;

import learn.math.util.VectorOps;

public class LogisticClassifier extends LinearClassifier {
	
	public LogisticClassifier(double[] weights) {
		super(weights);
	}
	
	public LogisticClassifier(int ninputs) {
		super(ninputs);
	}
	
	/**
	 * A LogisticClassifier uses the logistic update rule
	 * (AIMA Eq. 18.8): w_i \leftarrow w_i+\alpha(y-h_w(x)) \times h_w(x)(1-h_w(x)) \times x_i 
	 */
	public void update(double[] x, double y, double alpha) {
		
		double hw_x = eval(x);
		for(int i=0;i<weights.length;i++) {
			weights[i] = weights[i]+alpha*(y-hw_x)*hw_x*(1.0-hw_x)*x[i]; //logistic update rule
		}
		
	}
	
	/**
	 * A LogisticClassifier uses a 0/1 sigmoid threshold at z=0.
	 */
	public double threshold(double z) {
		
		return 1.0/(1.0+Math.exp((-1.0)*z));
		
	}

}
