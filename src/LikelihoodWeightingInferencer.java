import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import bn.base.StringValue;
import bn.core.Assignment;
import bn.core.BayesianNetwork;
import bn.core.Distribution;
import bn.core.RandomVariable;
import bn.core.Value;
import bn.parser.BIFParser;
import bn.parser.XMLBIFParser;

public class LikelihoodWeightingInferencer {
	
	//Command line: java -cp "./bin" LikelihoodWeightingInferencer 10000 aima-wet-grass.xml R S true
                 // java -cp "./bin" LikelihoodWeightingInferencer 10000 aima-alarm.xml B J true M true
                 // java -cp "./bin" LikelihoodWeightingInferencer 100000 sachs.xml Akt Erk LOW
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
		
		//create inferencer
		LikelihoodWeightingInferencer inferencer = new LikelihoodWeightingInferencer();
		Distribution dist = inferencer.LikelihoodWeightingSampling(X,  e, network, N);
		
		
		System.out.println(dist);
		


	}
	
	//constructor
	public LikelihoodWeightingInferencer() {
		
	}
	
	public Distribution LikelihoodWeightingSampling(RandomVariable X, Assignment e, BayesianNetwork network, int N) {
		
		Map<Value, Double> ValueWeightMap = new HashMap<Value, Double>();
		Iterator<Value> it = X.getDomain().iterator();
		while(it.hasNext()) {
			ValueWeightMap.put(it.next(), 0.0);
		}
		
		Distribution Q = new bn.base.Distribution(X);
		
		for(int i=0;i<N;i++) {
			
			Iterator<Value> iterator = X.getDomain().iterator();
			Map<Assignment, Double> map = WeightedSample(network, e);
			Assignment x = map.keySet().iterator().next();
			
			while(iterator.hasNext()) {

				Value v = iterator.next();
				if(x.get(X).equals(v)) {
					double weight = ValueWeightMap.get(v);
					weight+=(double) map.get(x);
					ValueWeightMap.put(v, weight);
					//System.out.format("%s:  %s:  N[%s] = %s\n", i, x, v, weight);
					Q.set(v, weight);
				}
				
			}
			
		}
		System.out.println("Distribution before normalization: "+ValueWeightMap);
		Q.normalize();
		return Q;
		
	}
	
	public Map<Assignment, Double> WeightedSample(BayesianNetwork network, Assignment e) {
		double weight = 1;
		
		Random generator = new Random();
		Map<Assignment, Double> map = new HashMap<Assignment, Double>();
		Assignment copy = e.copy();
		
		List<RandomVariable> vars=network.getVariablesSortedTopologically();
		for(RandomVariable v : vars) {
			if(copy.containsKey(v)) {
				weight*=network.getProbability(v, copy);
			}else {
				
				double index = generator.nextDouble();

				//Consider random variable: v
				//{LOW=0.423, AVG=0.482, HIGH=0.095}}
				//index = 0.6
				bn.core.Domain d = v.getDomain();
				
				double sum = 0;
				for(Value value : d) {
					
					copy.put(v, value);
					sum += network.getProbability(v, copy);
					if(index <= sum) {
						copy.put(v, value);
						break;
					}
				}
							
			}
			
		}
		map.put(copy,weight);
		return map;
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
