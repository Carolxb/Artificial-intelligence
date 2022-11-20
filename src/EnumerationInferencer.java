
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;

import bn.parser.BIFParser;
import bn.parser.XMLBIFParser;
import org.xml.sax.SAXException;

import bn.base.StringValue;
import bn.core.Assignment;
import bn.core.BayesianNetwork;
import bn.core.Distribution;
import bn.core.RandomVariable;
import bn.core.Value;
import bn.core.Domain;


public class EnumerationInferencer implements bn.core.Inferencer {
	
	
	//Command line: java -cp "./bin" EnumerationInferencer aima-alarm.xml B J true M true
	             // java -cp "./bin" EnumerationInferencer aima-wet-grass.xml R S true
	             // java -cp "./bin" EnumerationInferencer sachs.xml Akt Erk LOW
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		
		List<String> commandline = new ArrayList<String>();
		
		for(int i = 0; i<args.length; i++) {
			commandline.add(args[i]);
	       // System.out.println("adding '"+ args[i]+"' in the commandline list!");
		}
		System.out.println(commandline.toString());
		
		//identity file and store in BayesianNetwork
		BayesianNetwork network;
		String filename = commandline.get(0);
		if(type(filename).equals("xml")) {
			XMLBIFParser parser = new bn.parser.XMLBIFParser();
			network = parser.readNetworkFromFile(filename);
		}else {
			BIFParser parser = new BIFParser(new FileInputStream(filename));
			network = parser.parseNetwork();
		}	
		
		//identity query variable
		String variablename = commandline.get(1);
		RandomVariable X = network.getVariableByName(variablename);
		System.out.println("The query variable is: "+X);
		
		//identify evidence variable
		Assignment e = new bn.base.Assignment();
		for(int i=2;i<commandline.size();i+=2) {
			                                                          
			e.put(network.getVariableByName(commandline.get(i)), new StringValue(commandline.get(i+1)) );
			
		}
		System.out.println("The evidence variable are: "+e);
		
		//create an inferencer
		EnumerationInferencer inferencer = new EnumerationInferencer();
		System.out.println(X+": "+inferencer.query(X,e,network));
		
		
		
	}
	
	//constructor
	public EnumerationInferencer() {
		
	}
	
	@Override
	public Distribution query(RandomVariable X, Assignment e, BayesianNetwork network) {
		
		Distribution Q = new bn.base.Distribution(X);
		for(Value x : (Domain) X.getDomain()) {
			
			//Update assignment: ex is e extended with X=x
			Assignment ex = e.copy();
			ex.put(X, x);
			
			double q = EnumerateAll(network.getVariablesSortedTopologically(), ex, network);
			Q.set(x, q);
			
		}
		Q.normalize();
		System.out.println("Done!!!");
		return Q;
	}
	
	
	
	public double EnumerateAll(List<RandomVariable> vars, Assignment ex, BayesianNetwork network) {
		
		if(vars.isEmpty()) {
			return 1.0;
		}
		
		RandomVariable Y = vars.get(0);
		List<RandomVariable> Restvars = vars.subList(1, vars.size());
		
		if(ex.containsKey(Y)) { //Y is NOT hidden variable
			
			return network.getProbability(Y, ex)*EnumerateAll(Restvars, ex, network);
			
		}else {  //Y is hidden variable
			
			double sum = 0;
			for(Value y : Y.getDomain()) {
				Assignment newex = ex.copy();
				newex.put(Y, y);
				
				sum+=network.getProbability(Y, newex)*EnumerateAll(Restvars, newex, network);
				
			}
			return sum;
		}
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
