import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import bn.base.Domain;
import bn.base.StringValue;
import bn.core.Assignment;
import bn.core.BayesianNetwork;
import bn.core.Distribution;
import bn.core.RandomVariable;
import bn.core.Value;
import bn.parser.BIFParser;
import bn.parser.XMLBIFParser;

public class RejectionSamplingInferencer {	
	
	//Command line: java -cp "./bin" RejectionSamplingInferencer 100000 aima-wet-grass.xml R S true
	             // java -cp "./bin" RejectionSamplingInferencer 500000 aima-alarm.xml B J true M true
	             // java -cp "./bin" RejectionSamplingInferencer 100000 sachs.xml Akt Erk LOW
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException{
		
		List<String> commandline = new ArrayList<String>();
		
		for(int i = 0; i<args.length; i++) {
			commandline.add(args[i]);
		}
		
		//identity N
		int N = Integer.parseInt(commandline.get(0));
		System.out.println("Get "+N+" random assignments!!!");
		
		//identity file and store in BayesianNetwork
		BayesianNetwork network;
		String filename = commandline.get(1);
		if(type(filename).equals("xml")) {
			XMLBIFParser parser = new bn.parser.XMLBIFParser();
			network = parser.readNetworkFromFile(filename);
		}else {
			BIFParser parser = new BIFParser(new FileInputStream(filename));
			network = parser.parseNetwork();
		}	
		
		//identity query variable
		String variablename = commandline.get(2);
		RandomVariable X = network.getVariableByName(variablename);
		System.out.println("The query variable is: "+X);
				
		//identify evidence variable
		Assignment e = new bn.base.Assignment();
		for(int i=3;i<commandline.size();i+=2) {
			                                                          
			e.put(network.getVariableByName(commandline.get(i)), new StringValue(commandline.get(i+1)) );
			
		}
		System.out.println("The evidence variable are: "+e);
		
		
		//create an inferencer
		RejectionSamplingInferencer inferencer = new RejectionSamplingInferencer();
		
		Distribution dist = inferencer.RejectionSampling(X,  e, network, N);
		
		System.out.println("The query variable is: "+X);
		System.out.println("The evidence variable are: "+e);
		
		System.out.println(dist);
		
	}
	
	//constructor
	public RejectionSamplingInferencer() {
		
	}
		
	public Assignment PriorSample(BayesianNetwork network) {
		
		Random generator = new Random();
		
		//Sort the variables in topological order
		List<RandomVariable> vars = network.getVariablesSortedTopologically();
		Assignment a = new bn.base.Assignment();
		Iterator<RandomVariable> it = vars.iterator();
		
		//for all variables
		while(it.hasNext()) {
			
			double index = generator.nextDouble();
			
			//get a random variable topologically
			RandomVariable variable = it.next();
			
			//{LOW=0.423, AVG=0.482, HIGH=0.095}}
			//index = 0.6
			bn.core.Domain d = variable.getDomain();
			
			double sum = 0;
			for(Value v : d) {
				
				a.put(variable, v);
				sum += network.getProbability(variable, a);
				if(index <= sum) {
					a.put(variable, v);
					break;
				}
				
			}
		}
		
		return a;
	}
	
	
	
	public Distribution RejectionSampling(RandomVariable X, Assignment e, BayesianNetwork network, int N) {
		
		Distribution Q = new bn.base.Distribution(X);
		Iterator<Value> it = X.getDomain().iterator();
		Map<Value, Integer> ValueCountMap = new HashMap<Value, Integer>();
		while(it.hasNext()) {
			ValueCountMap.put(it.next(), 0);
		}
		
		for(int i=0;i<N;i++) {
			Iterator<Value> iterator = X.getDomain().iterator();
			Assignment x = PriorSample(network);

			if(x.containsAll(e)) {
				System.out.println();
				
				while(iterator.hasNext()) {

					Value v = iterator.next();
					if(x.get(X).equals(v)) {
						int count = ValueCountMap.get(v);
						count++;
						ValueCountMap.put(v, count);
						//System.out.format("%s:  %s:  N[%s] = %s\n", i, x, v, count);
						Q.set(v, count);
					}
					
				}
				
			}else {
				
				//System.out.print("."); // if the assignment does not contain a correct value for the evidence variable, print "." instead!!!
			}
			
		}
		
		System.out.println();
		System.out.println("Distribution before normalization: "+ValueCountMap);
		Q.normalize();
		return Q;
		
	}
	
	public static String type(String name) {
		if(name.toLowerCase().endsWith(".xml")) {
			return new String("xml");
		}else if(name.toLowerCase().endsWith(".bif")) {
			return new String("bif");
		}else {
			return null;
		}
		
	}
	

}
