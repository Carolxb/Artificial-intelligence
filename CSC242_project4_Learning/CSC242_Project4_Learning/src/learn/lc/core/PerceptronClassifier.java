package learn.lc.core;

import java.util.Arrays;

import learn.math.util.VectorOps;

public class PerceptronClassifier extends LinearClassifier {
	
	public PerceptronClassifier(double[] weights) {
		super(weights);
	}
	
	public PerceptronClassifier(int ninputs) {
		super(ninputs);
	}
	
	/**
	 * A PerceptronClassifier uses the perceptron learning rule
	 * (AIMA Eq. 18.7): w_i \leftarrow w_i+\alpha(y-h_w(x)) \times x_i 
	 */
	public void update(double[] x, double y, double alpha) {
		
		double hw_x = eval(x);
		for(int i=0;i<weights.length;i++) {
			weights[i] = weights[i]+alpha*(y-hw_x)*x[i]; //perceptron learning rule
		}

	}
	
	/**
	 * A PerceptronClassifier uses a hard 0/1 threshold.
	 */
	public double threshold(double z) {
		if(z >= 0) {
			return 1.0;
		}
		return 0.0;
	}
	
}
