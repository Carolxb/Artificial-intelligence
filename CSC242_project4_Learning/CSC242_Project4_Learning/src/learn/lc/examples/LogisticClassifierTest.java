package learn.lc.examples;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import learn.lc.core.DecayingLearningRateSchedule;
import learn.lc.core.Example;
import learn.lc.core.LogisticClassifier;

public class LogisticClassifierTest {
	/*
	 * 1. enter src
	 * 2. javac learn/lc/examples/LogisticClassifierTest.java
	 * 3. java learn.lc.examples.LogisticClassifierTest earthquake-clean.data.txt 100000 0.3 L_earthquake_clean_alpha_report
	 * 	  java learn.lc.examples.LogisticClassifierTest earthquake-clean.data.txt 100000 0 L_earthquake_clean_decay_report
	 * 
	 *    java learn.lc.examples.LogisticClassifierTest earthquake-noisy.data.txt 100000 0.3 L_earthquake_noisy_alpha_report
	 *    java learn.lc.examples.LogisticClassifierTest earthquake-noisy.data.txt 200000 0 L_earthquake_noisy_decay_report
	 *    
	 *    java learn.lc.examples.LogisticClassifierTest house-votes-84.data.num.txt 30000 0.95 L_HouseVotes_alpha_report
	 *    java learn.lc.examples.LogisticClassifierTest house-votes-84.data.num.txt 30000 0 L_HouseVotes_decay_report
	 * 4. report will be stored in the src directory
	 * 
	 */
	public static void main(String[] args) throws IOException {
		
		Reader reader = new Reader();
		String filename = args[0];
		int nsteps = Integer.parseInt(args[1]);
		double alpha = Double.parseDouble(args[2]);
		String outputName = args[3];
		double[] accuracystep= new double[nsteps+1]; // To store accuracy during the loop
		
		                                        
		List<Example> examples = reader.readFile(filename);
		int ninputs = reader.getNinputs();
		LogisticClassifier p = new LogisticClassifier(ninputs) {
			//Calculate the accuracy if using a soft threshold
			public void trainingReport(List<Example> examples, int stepnum, double[] accuracies) {
				double L_accuracy = 1.0 - squaredErrorPerSample(examples);
				accuracies[stepnum] = L_accuracy;
				if(stepnum % 100 == 0) System.out.println(stepnum + "\t" + L_accuracy);
			}	
		};
		
		// raw accuracy
		accuracystep[0] = p.accuracy(examples);
		if(alpha == 0) {
			
			p.train(examples, nsteps, new DecayingLearningRateSchedule(), accuracystep); 
			System.out.println("Testing File: " + filename);
			System.out.println("Training Time: " + nsteps);
			System.out.println("Alpha is Decaying with Time");
			
		}else {
			
			p.train(examples, nsteps, alpha, accuracystep);
			System.out.println("Testing File: " + filename);
			System.out.println("Training Time: " + nsteps);
			System.out.println("Alpha: " + alpha);
		}
		
		//generate output (iteration time, accuracy)
		try {
			
			FileWriter fw = new FileWriter(outputName+".csv", false); 
			fw.write("# Weight Updates , Accuracy\n");
			for(int i = 0; i < accuracystep.length; i++) {
				fw.write(String.valueOf(i) + "," + String.valueOf(accuracystep[i]) + "\n");
			}
			fw.flush();
			fw.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		
	}

}
