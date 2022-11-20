package Project2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Detail_Informed_Version {
	
	public static void main(String args[]) throws IOException {
		
		/*
		List<Clause> KBtesting = readCNF("C:\\Users\\ltb20\\eclipse-workspace\\CSC242\\src\\Project2\\quinn.cnf");
		System.out.println(KBtesting.size());
		*/
		List<Clause> KB1 = new ArrayList<Clause>();
		int[] c = {1,3,-4};
		int[] c0 = {4};
		int[] c1 = {2,-3};
		KB1.add(new Clause(c));
		KB1.add(new Clause(c0));
		KB1.add(new Clause(c1));
		System.out.println("--------------Part 3 Question 1--------------");
		System.out.println();
		System.out.print("Knowledge base: ");
		for(Clause clause: KB1) {
			clause.print();
			System.out.print(" ");
		}
		System.out.println();
		System.out.println();
		
		try {
			Map<Integer, Boolean> result = GSAT(KB1,5,3);
			System.out.println();
			System.out.println("***=> The final model that satisfies KB: "+result.toString());
			
		}catch(NullPointerException e) {
			System.out.println("------------------Try bigger Max_flip and Max_tries!------------------");
		}
		
		
	}
	
	
	
	public static Map<Integer, Boolean> GSAT(List<Clause> KB, int Max_flips, int Max_tries){
		
		for(int i=0;i<Max_tries;i++ ) {
			
			System.out.println("----->"+(i+1)+"th TRY starts:");
			
			//generate a random valid assignment
			Map<Integer, Boolean> T = randomGenerateAssignment(KB);
			
			for(int j=0;j<Max_flips;j++) {
				
				System.out.println();
				System.out.println("--->"+(j+1)+"th FLIP starts:");
				
				if( PL_true(KB, T) ) {//all clauses in KB equal Boolean.TRUE //PL_true???
					
					System.out.println("Find a vaild model, return it immediately!!!");
					System.out.println("Model: "+T.toString());
					return T;
					
				}
				System.out.println("Current model does not satisfy KB!!!");
				
				//variable with largest increase in # of Clauses
				int p = DetermineWhichToFlip(KB,T);
				boolean pValue = T.get(p);
				
				//update T with reversed p
				T.put(p, ReverseValue(pValue));
				if(T.keySet().contains(-p)) {
					
					T.put(-p, pValue);
				}
				System.out.println("Model is updated!!!!");
				System.out.println("--->"+(j+1)+"th FLIP ends.");
				
			}
			System.out.println("----->"+(i+1)+"th TRY ends.");
			System.out.println();
			
		}
		
		System.out.println("No satisfying assignment found!!!");
		return null;
		
	}
	
	public static int NumofCorrectClauses(List<Clause> KB, Map<Integer, Boolean> model) {
		int count = 0;
		
		//evaluating each clause accordingly
		for (Clause c:KB) {
			for (int i=0;i<c.size();i++) {
				
				 // model.get(c.get(i)) = ith integer's boolean value
				 if(model.get(c.get(i)) == true) {
					 
					 count++;
					 break;
					 
				 }
	
			 }
			 
		 }
		return count;
	}
	
	public static int DetermineWhichToFlip(List<Clause> KB, Map<Integer, Boolean> model) {
		
		List<Integer> availableList = new ArrayList<Integer>();
		int FinalVariable = 0;
		int MAX = 0;
		
		System.out.println();
		System.out.println("Current max: "+MAX);
		System.out.println();
		
		int times = 0;
		
		for(int key : model.keySet()) {
			
			System.out.println("Original Model: "+model.toString());
			
			//avoid disrupting the original data
			Map<Integer, Boolean> alt = new HashMap<Integer, Boolean>();
			alt.putAll(model);
			
			boolean pastvalue = alt.get(key);
			
			alt.put(key, ReverseValue(pastvalue));
			if(alt.keySet().contains(-key)) {
				
				alt.put(-key, pastvalue);
			}
			
			times++;
			System.out.println("Filp the "+times+"th variable");
			System.out.println("Alternative model: "+alt.toString());
			
			int num = NumofCorrectClauses(KB, alt);
			System.out.println("It makes "+ num+" clauses true");
			System.out.println();
			
			if(num > MAX) {
				
				System.out.println("-> Changing "+key+"'s boolean value can get "+num+" correct clauses");
				System.out.println("-> Therefore, set max as: "+num+", set final variable as: "+key);
				System.out.println();
				availableList.clear();
				MAX = num;
				availableList.add(key);
				
			}else if(num == MAX) {
				
				System.out.println("current num == max, add this key  "+key+"  into the availableList");
				System.out.println();
				availableList.add(key);
				
			}else {
				System.out.println("current num < max, do nothing!");
				System.out.println();
			}
		
		}
		System.out.println("current list: "+availableList.toString());
		Random random = new Random();
		int index = random.nextInt(availableList.size());
		System.out.println("Random chooes index:"+index+", which is "+availableList.get(index));
		System.out.println();
		FinalVariable = availableList.get(index);

		return FinalVariable;
	}
	
	
	//method to get the inverse boolean value
	public static boolean ReverseValue(boolean b) {
		if(b == true) {
			return false;
		}else {
			return true;
		}
		
	}
	
	//method to generate random valid assignment
	public static Map<Integer, Boolean> randomGenerateAssignment(List<Clause> KB){
		
		Random random = new Random();
		Map<Integer, Boolean> model = new HashMap<Integer, Boolean>();
		
		//collect all the none-repeated symbols in a list
		List<Integer> symbols = new ArrayList<Integer>();
		for (int i=0; i<KB.size(); i++) {
			for (int j=0; j<KB.get(i).size(); j++) {
				if (!symbols.contains(KB.get(i).get(j))) {
					
					symbols.add(KB.get(i).get(j));
					
				}
			}
		}
		System.out.println("Symbols include: "+symbols.toString());
		
		//Assign random value to each symbol
		for(int i=0;i<symbols.size();i++) {
			
			
			if(model.keySet().contains(-symbols.get(i))) {// when adding -1's boolean value, detecting already assigned a boolean value to 1 
				System.out.println("Detect an inverse");
				System.out.println("Its reverse: "+(-symbols.get(i))+" already exist with boolean value: "+model.get(-symbols.get(i)));
				System.out.println("Assign:"+symbols.get(i)+" -> "+ReverseValue(model.get(-symbols.get(i))));
				model.put(symbols.get(i), ReverseValue(model.get(-symbols.get(i))));
				
				
			}else {
				
				boolean b = random.nextBoolean();
				System.out.println("Assign:"+symbols.get(i)+" -> "+b);
				model.put(symbols.get(i), b);
				
			}

		}
		
		System.out.println("Random valid model: "+model.toString());
		
		return model;
		
	}
	
	//method to whether T satisfies KB
	public static boolean PL_true(List<Clause> KB, Map<Integer, Boolean> model) {
		  
		 List<Boolean> KBResult = new ArrayList<Boolean>();
		
		 for (Clause c:KB) {
		   
			 List<Boolean> ClauseResult = new ArrayList<Boolean>();
			 for (int i=0;i<c.size();i++) {
				 // c.get(i) = integers in the clause
				 // model.get(c.get(i)) = ith integer's boolean value
				 ClauseResult.add(model.get(c.get(i)));
			 }
			 
			 if (ClauseResult.contains(Boolean.TRUE)) {
				 KBResult.add(Boolean.TRUE);
			 } else {
				 KBResult.add(Boolean.FALSE);
			 }
		 }
		 
		 if (KBResult.contains(Boolean.FALSE)) {
		   return false;
		 } else { 
		   return true;  
		 }

	}
	
	//read .cnf file method
	public static List<Clause> readCNF(String filePath) throws IOException{
		
		List<String> integers = new ArrayList<String>();
		List<Clause> KB = new ArrayList<Clause>();
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();
			for(;;) {
				if (line.startsWith("c") || line.startsWith("C")) {
					line = reader.readLine(); 
					continue;
				} else if (line.startsWith("p cnf ")){
					System.out.println(line); 
					line = reader.readLine();
				} else {
					System.out.println(line); 
					String[] words = line.split("[ ,  .   /    ?     ]+");
					//System.out.println(words.length);
					for (int i=0;i<words.length;i++) {
						if (!words[i].equals(""))
						integers.add(words[i]);
						//System.out.println(words[i]);
					}
					line = reader.readLine();
				}
				if (line == null) {
					break;
				}
			}
			
			Clause clause = new Clause();
			for (int i=0; i<integers.size(); i++) {
				if (!integers.get(i).equals("0")) {
					
					clause.add(Integer.parseInt(integers.get(i)));
					
				} else {
					
					clause.print();
					KB.add(clause);
					clause = new Clause();
				}
				
				//System.out.print(integers.get(i)+", ");
			}
			System.out.println();
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return KB;

	}
	
	
}
