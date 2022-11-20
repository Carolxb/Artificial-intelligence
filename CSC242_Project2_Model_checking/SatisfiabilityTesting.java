package Project2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SatisfiabilityTesting {
	
	public static void main(String args[]) throws IOException {
		
		String n_queensFirstPart = "C:\\Users\\ltb20\\eclipse-workspace\\CSC242\\src\\Project2\\nqueens_";//important; END by "...\\nquees_"
		String quinn = "C:\\Users\\ltb20\\eclipse-workspace\\CSC242\\src\\Project2\\quinn.cnf";
		String zebra = "C:\\Users\\ltb20\\eclipse-workspace\\CSC242\\src\\Project2\\zebra_v155_c1135.cnf";
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("--------------------------CSC242 Satisfiability Testing--------------------------");
		System.out.println("=> Which problem do you want to test? (enter q1, q2, or q3)");
		String question = scanner.next();
		
		if (question.equalsIgnoreCase("q1"))  {
			
			//default value
			int flips = 5;
			int tries = 3;
			System.out.println("=> Please enter the Max_flips (recommend 5; 0 for default value):");
			int f = scanner.nextInt();
			if(f==0) {
				System.out.println("=> Using default Max_flips=5");
			}else {
				flips = f;
				System.out.println("=> Set Max_flips as: "+flips);
			}
			System.out.println("=> Please enter the Max_tries (recommend 3; 0 for default value):");
			int t = scanner.nextInt();
			if(t==0) {
				System.out.println("=> Using default Max_tries=3");
			}else {
				tries= t;
				System.out.println("=> Set Max_tries as: "+tries);
			}
			/*
			 * Part3 Q1
			 * 
			 * {1,3,-4} {4} {2,-3}: 5 flips, 3 tries
			 * 
			 */
			List<Clause> KB1 = new ArrayList<Clause>();
			int[] c = {1,3,-4};
			int[] c0 = {4};
			int[] c1 = {2,-3};
			KB1.add(new Clause(c));
			KB1.add(new Clause(c0));
			KB1.add(new Clause(c1));
			System.out.println("<===================Part 3 Question 1===================>");
			System.out.println();
			System.out.print("Knowledge base: ");
			for(Clause clause: KB1) {
				clause.print();
				System.out.print(" ");
			}
			System.out.println();
			System.out.println();
			
			try {
				Map<Integer, Boolean> result = GSAT(KB1,flips,tries);//5 flips, 3 tries
				System.out.println();
				System.out.println("***=> The Part3Q1 final model that satisfies KB: "+result.toString());
				
			}catch(NullPointerException e) {
				System.out.println("------------------Try bigger Max_flip and Max_tries!------------------");
			}
			
			System.out.println();
			
			
		}else if(question.equalsIgnoreCase("q2")){
			
			System.out.println("Enter N for N-queens [4/8/12/16]: ");
			String situation = scanner.next();
			List<Clause> KB2 = readCNF(n_queensFirstPart+situation+".cnf");
			System.out.println("Finish loading nqueens_"+situation+".cnf");
			int flips;
			int tries;
			
			if(situation.equalsIgnoreCase("4")) {
				flips = 30;
				tries = 20;
				System.out.println("=> Please enter the Max_flips (recommend 30; 0 for default value):");
				int f = scanner.nextInt();
				if(f==0) {
					System.out.println("=> Using default Max_flips=30");
				}else {
					flips = f;
					System.out.println("=> Set Max_flips as: "+flips);
				}
				System.out.println("=> Please enter the Max_tries (recommend 20; 0 for default value):");
				int t = scanner.nextInt();
				if(t==0) {
					System.out.println("=> Using default Max_tries=20");
				}else {
					tries= t;
					System.out.println("=> Set Max_tries as: "+tries);
				}
			} else if (situation.equalsIgnoreCase("8")) {
				flips = 60;
				tries = 30;
				System.out.println("=> Please enter the Max_flips (recommend 60; 0 for default value):");
				int f = scanner.nextInt();
				if(f==0) {
					System.out.println("=> Using default Max_flips=60");
				}else {
					flips = f;
					System.out.println("=> Set Max_flips as: "+flips);
				}
				System.out.println("=> Please enter the Max_tries (recommend 30; 0 for default value):");
				int t = scanner.nextInt();
				if(t==0) {
					System.out.println("=> Using default Max_tries=30");
				}else {
					tries= t;
					System.out.println("=> Set Max_tries as: "+tries);
				}
			} else if (situation.equalsIgnoreCase("12")) {
				flips = 100;
				tries = 50;
				System.out.println("=> Please enter the Max_flips (recommend 100; 0 for default value):");
				int f = scanner.nextInt();
				if(f==0) {
					System.out.println("=> Using default Max_flips=100");
				}else {
					flips = f;
					System.out.println("=> Set Max_flips as: "+flips);
				}
				System.out.println("=> Please enter the Max_tries (recommend 50; 0 for default value):");
				int t = scanner.nextInt();
				if(t==0) {
					System.out.println("=> Using default Max_tries=50");
				}else {
					tries= t;
					System.out.println("=> Set Max_tries as: "+tries);
				}
			}else {
				flips = 150;
				tries = 100;
				System.out.println("=> Please enter the Max_flips (the larger the better; 0 for default value):");
				int f = scanner.nextInt();
				if(f==0) {
					System.out.println("=> Using default Max_flips=150");
				}else {
					flips = f;
					System.out.println("=> Set Max_flips as: "+flips);
				}
				System.out.println("=> Please enter the Max_tries (the larger the better; 0 for default value):");
				int t = scanner.nextInt();
				if(t==0) {
					System.out.println("=> Using default Max_tries=100");
				}else {
					tries= t;
					System.out.println("=> Set Max_tries as: "+tries);
				}
				
			}
			
			/*
			 * Part3 Q2
			 * 
			 * nqueens_4.cnf : 30 flips, 20 tries
			 * nqueens_8.cnf : 60 flips, 30 tries
			 * nqueens_12.cnf : 100 flips, 50 tries
			 * nqueens_16.cnf : no appropriate value for flips and tries, the bigger; the better.
			 */
			
			System.out.println("<===================Part 3 Question 2===================>");
			System.out.println();
			System.out.print("Knowledge base: ");
			for(Clause clause: KB2) {
				clause.print();
				System.out.print(" ");
			}
			System.out.println();
			System.out.println();
			
			try {
				Map<Integer, Boolean> result = GSAT(KB2, flips, tries);
				System.out.println();
				System.out.println("***=> The Part3Q2 final model that satisfies KB: "+result.toString());
				
			}catch(NullPointerException e) {
				System.out.println("------------------Try bigger Max_flip and Max_tries!------------------");
			}
			
			System.out.println();
			
			
		}else {
			/*
			 * Part3 Q3
			 * 
			 * quinn.cnf : 10 flips, 5 tries
			 * 
			 * par8-1-c.cnf : 60 flips, 30 tries
			 * 
			 */
			System.out.println("Testing quinn.cnf (16 variables & 18 clauses):");
			int flips = 10;
			int tries = 5;
			System.out.println("=> Please enter the Max_flips (recommend 10; 0 for default value):");
			int f = scanner.nextInt();
			if(f==0) {
				System.out.println("=> Using default Max_flips=10");
			}else {
				flips = f;
				System.out.println("=> Set Max_flips as: "+flips);
			}
			System.out.println("=> Please enter the Max_tries (recommend 5; 0 for default value):");
			int t = scanner.nextInt();
			if(t==0) {
				System.out.println("=> Using default Max_tries=5");
			}else {
				tries= t;
				System.out.println("=> Set Max_tries as: "+tries);
			}
			
			List<Clause> KB3 = readCNF(quinn);
			System.out.println("<===================Part 3 Question 3 a===================>");
			System.out.println();
			System.out.print("Knowledge base: ");
			for(Clause clause: KB3) {
				clause.print();
				System.out.print(" ");
			}
			System.out.println();
			System.out.println();
			
			try {
				Map<Integer, Boolean> result = GSAT(KB3,flips,tries);
				System.out.println();
				System.out.println("***=> The Part3Q3a final model that satisfies KB: "+result.toString());
				
			}catch(NullPointerException e) {
				System.out.println("------------------Try bigger Max_flip and Max_tries!------------------");
			}
			
			System.out.println();
			System.out.println("==========================================================================================");
			System.out.println();
			System.out.println("Moving to the next question...");
			System.out.println();
			System.out.println("Testing zebra_v155_c1135.cnf (155 variables & 1135 clauses):");
			int Flips = 500;
			int Tries = 500;
			System.out.println("=> Please enter the Max_flips (the larger the better; 0 for default value):");
			int F = scanner.nextInt();
			if(F==0) {
				System.out.println("=> Using default Max_flips=500");
			}else {
				Flips = F;
				System.out.println("=> Set Max_flips as: "+Flips);
			}
			System.out.println("=> Please enter the Max_tries (the larger the better; 0 for default value):");
			int T = scanner.nextInt();
			if(T==0) {
				System.out.println("=> Using default Max_tries=500");
			}else {
				Tries= T;
				System.out.println("=> Set Max_tries as: "+Tries);
			}
			
			List<Clause> KB4 = readCNF(zebra);
			System.out.println("<===================Part 3 Question 3 b===================>");
			System.out.println();
			System.out.print("Knowledge base: ");
			for(Clause clause: KB4) {
				clause.print();
				System.out.print(" ");
			}
			System.out.println();
			System.out.println();
			
			try {
				Map<Integer, Boolean> result = GSAT(KB4,80,200);
				System.out.println();
				System.out.println("***=> The Part3Q3b final model that satisfies KB: "+result.toString());
				
			}catch(NullPointerException e) {
				System.out.println("------------------Try bigger Max_flip and Max_tries!------------------");
			}
			
			System.out.println();
		}
		
		System.out.println("Finish testing!!!");
		
	}
	
	
	// GSAT
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
	
	// heuristic function to get the number of correct clauses of certain model
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
	
	// method to determine which variable to flip; return 0 if in a local max
	public static int DetermineWhichToFlip(List<Clause> KB, Map<Integer, Boolean> model) {
		
		List<Integer> availableList = new ArrayList<Integer>();
		int FinalVariable = 0;
		int MAX = 0; 
				
		for(int key : model.keySet()) {
			
			//avoid disrupting the original data
			Map<Integer, Boolean> alt = new HashMap<Integer, Boolean>();
			alt.putAll(model);
			
			boolean pastvalue = alt.get(key);
			
			alt.put(key, ReverseValue(pastvalue));
			if(alt.keySet().contains(-key)) {
				
				alt.put(-key, pastvalue);
			}
			
		
			int num = NumofCorrectClauses(KB, alt);
			
			if(num > MAX) {
				availableList.clear();
				MAX = num;
				availableList.add(key);
				
			}else if(num == MAX) {
				availableList.add(key);
				
			}
		
		}
		Random random = new Random();
		int index = random.nextInt(availableList.size());
		System.out.println("Choose to flip: "+availableList.get(index)+", which would generate "+MAX+" true clauses!");
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
		
		//Assign random value to each symbol
		for(int i=0;i<symbols.size();i++) {
			
			
			if(model.keySet().contains(-symbols.get(i))) {// when adding -1's boolean value, detecting already assigned a boolean value to 1 
				
				model.put(symbols.get(i), ReverseValue(model.get(-symbols.get(i))));
			
			}else {
				
				boolean b = random.nextBoolean();
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
					line = reader.readLine();
				} else {
					String[] words = line.split("[ ,  .   /    ?     ]+");
					for (int i=0;i<words.length;i++) {
						if (!words[i].equals(""))
						integers.add(words[i]);
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
					
					KB.add(clause);
					clause = new Clause();
				}
				
			}
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return KB;

	}
	
}
