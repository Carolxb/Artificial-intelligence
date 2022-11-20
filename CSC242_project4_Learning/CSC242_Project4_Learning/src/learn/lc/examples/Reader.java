package learn.lc.examples;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


import learn.lc.core.Example;


public class Reader {
	
	private int ninputs;
	
	//constructor
	public Reader() {
		
	}
	
	//method to read file
	public List<Example> readFile(String name) throws IOException {
		
		List<Example> examples= new ArrayList<Example> ();
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("learn/lc/examples/"+name));
			String line = reader.readLine();
			for(;;) {
				//store x1, x2, y
				String[] words = line.split(",");
				
				ninputs = words.length;
				//inputs store x0, x1, x2
				double[] inputs = new double[words.length];
				inputs[0] = 1.0; //initialize x0
				for(int i=1;i<words.length;i++) {	
					
					inputs[i]= Double.parseDouble(words[i-1]);
					
				}
				double output = Double.parseDouble(words[words.length-1]);
				examples.add(new Example(inputs, output));
				
				//read another line
				line = reader.readLine();
				
				//end reading
				if (line == null) {break;}
				
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return examples;
	}
	
	//get number of "weight"; used to initialize PerceptronClassifier; 
	public int getNinputs() {
		return ninputs;
	}
	
	
	
	
	

}
