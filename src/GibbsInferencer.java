import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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

public class GibbsInferencer {
	
	
	// Command line: java -cp "./bin" GibbsInferencer 30000 aima-wet-grass.xml R S true
		          // java -cp "./bin" GibbsInferencer 30000 aima-alarm.xml B J true M true
	              // java -cp "./bin" GibbsInferencer 100000 sachs.xml Akt Erk LOW
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		
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
		
		GibbsInferencer inferencer = new GibbsInferencer();
		Distribution dist = inferencer.Gibbs_Ask(X,  e, network, N);
		

		System.out.println("The query variable is: "+X);
		System.out.println("The evidence variable are: "+e);
		
		System.out.println(dist);
		
		
		
	}
	
	public GibbsInferencer() {
		
	}
	
	
	public Distribution Gibbs_Ask(RandomVariable X, Assignment e, BayesianNetwork network, int N) {
		
		Distribution Q = new bn.base.Distribution(X);
		List<RandomVariable> vars = network.getVariablesSortedTopologically();
		List<RandomVariable> NonevidenceVars = getNonEvidenceVariables(vars, e); // also in topological order
		Assignment x = e.copy();
		
		Initialize_x(x, NonevidenceVars, network);
		
		Iterator<Value> it = X.getDomain().iterator();
		Map<Value, Integer> ValueCountMap = new HashMap<Value, Integer>();
		while(it.hasNext()) {
			ValueCountMap.put(it.next(), 0);
		}
		
		//should have a complete assignment 
		for(int i=0;i<N;i++) {
			for(RandomVariable var: NonevidenceVars) {
				
				Value value = GibbsSampleValue(var, x, network);
				x.put(var, value);
				
				Iterator<Value> iterator = X.getDomain().iterator();
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
				
			}
			
		}
		System.out.println();
		System.out.println("Distribution before normalization: "+ValueCountMap);
		Q.normalize();
		return Q;
	}
	
	public List<RandomVariable> getNonEvidenceVariables(List<RandomVariable> vars, Assignment e){
		
		List<RandomVariable> list = new ArrayList<RandomVariable>();
		
		for(RandomVariable var : vars) {
			
			if(e.containsKey(var)) {//this is a evidence variable
				
				continue;
				
			}else {//this is NOT a evidence variable
				
				list.add(var);
				
			}
			
		}
		return list;
	}
	
	public void Initialize_x(Assignment e, List<RandomVariable> vars, BayesianNetwork network) {
		Iterator<RandomVariable> it = vars.iterator();
		Random generator = new Random();
		
		while(it.hasNext()) {
			
			double index = generator.nextDouble();
			//get a random variable topologically
			RandomVariable variable = it.next();
			
			//{LOW=0.423, AVG=0.482, HIGH=0.095}}
			//index = 0.6
			bn.core.Domain d = variable.getDomain();
			
			double sum = 0;
			//low, avg, high
			for(Value v : d) {
				
				e.put(variable, v);
				sum += network.getProbability(variable, e);
				if(index <= sum) {
					e.put(variable, v);
					break;
				}
				
			}
			
		}
		
	}
	
	public Value GibbsSampleValue(RandomVariable var,Assignment e, BayesianNetwork network) {
		
		Distribution dist = new bn.base.Distribution(var);
		Random generator = new Random();
		Assignment copy = e.copy();
		
		//generate dist for var using markov blanket
		Set<RandomVariable> children = network.getChildren(var);
		for(Value value : var.getDomain()) {
			
			copy.put(var, value);
			double p = network.getProbability(var, copy); //the fisrt part of P( x | mb(X) )
			for(RandomVariable childrenVariable : children) {
				
				p*=network.getProbability(childrenVariable, copy);
				
			}
			dist.put(value, p);
		}
		//{LOW=0.423, AVG=0.482, HIGH=0.095}}
		dist.normalize();

		//generate hidden varaible's value based on its Markov Blanket dist
		double index = generator.nextDouble();
		
		double sum = 0;
		for(Value v : dist.keySet()) {
			
			sum += dist.get(v);
			if(index <= sum) {
				return v;
			}
			
		}
		
		return null;	
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
