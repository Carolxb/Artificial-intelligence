import java.io.IOException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;
import bn.parser.XMLBIFParser;
import org.xml.sax.SAXException;

import bn.base.StringValue;
import bn.core.Assignment;
import bn.core.BayesianNetwork;
import bn.core.RandomVariable;
import bn.core.Value;



public class PriorSampler  {
	
	
	//Command line: java -cp "./bin" PriorSampler 100 aima-wet-grass.xml
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException{
		/*
		List<String> commandline = new ArrayList<String>();
		
		for(int i = 0; i<args.length; i++) {
			commandline.add(args[i]);
	       // System.out.println("adding '"+ args[i]+"' in the commandline list!");
		}
		
		//identity N
		int N = Integer.parseInt(commandline.get(0));
		System.out.println("Get "+N+" random assignments!!!");
		
		//identity ".xml" file
		String filename = commandline.get(1);
		*/
		String filename = "aima-alarm.xml";
		XMLBIFParser parser = new bn.parser.XMLBIFParser();
		BayesianNetwork network = parser.readNetworkFromFile(filename);
		System.out.println(network);
		
		for(int i=0;i<10;i++) {
			
			Assignment a = PriorSample(network);
			System.out.println(a);
			
		}
		
	}
	
	
	
	
public static Assignment PriorSample(BayesianNetwork network) {
		
		
		Random generator = new Random();
		Value TRUE = new StringValue("true");
		Value FALSE = new StringValue("false");
		
		//Sort the variables in topological order
		List<RandomVariable> vars = network.getVariablesSortedTopologically();
		Assignment a = new bn.base.Assignment();
		Iterator<RandomVariable> it = vars.iterator();
		
		//for all variables
		while(it.hasNext()) {
			
			double index = generator.nextDouble();
			//get a random variable topologically
			RandomVariable variable = it.next();
			a.put(variable, TRUE);
			if(index < network.getProbability(variable, a) ) {
				a.put(variable, TRUE );
			}else {
				a.put(variable, FALSE );
			}
		}
		
		return a;
	}
	

}
