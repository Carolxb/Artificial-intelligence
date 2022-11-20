package Project2;

import java.util.*;


public class ModelChecking {
	
	
	
	public static void main(String[] args) {
		
		/*
		 * Q1
		 * let P=1, Q=2
		 * {P,P->Q} equivalent to { {1} , {-1,2} }
		 * 
		 */
		
		//initialize Q1 KB
		List<Clause> KB1 = new ArrayList<Clause>();
		int[] c = {1};
		int[] c0 = {-1,2};
		KB1.add(new Clause(c));
		KB1.add(new Clause(c0));
		
		System.out.println("--------------Question 1--------------");
		System.out.print("Knowledge base: ");
		for(Clause clause: KB1) {
			clause.print();
			System.out.print(" ");
		}
		System.out.println();
		System.out.println();
		
		List<Clause> Q1a = new ArrayList<Clause>();
		int[] ca1 = {2};
		Q1a.add(new Clause(ca1));
		System.out.print("Q: ");
		for(Clause clause: Q1a) {
			clause.print();
			System.out.print(" ");
		}
		System.out.println();
		System.out.println(TT_entails(KB1, Q1a));
		

		/*
		 * Q2
		 * let P1,1=1, P1,2=2, P1,3=3, P2,1=4, P2,2=5, P3,1=6, B1,1=7, B1,2=8, B2,1=9
		 * R1: {-1}
		 * R2: {-7,2,4}, {-2,7}, {-4,7} calculating by hand
		 * R3: {-9,1,5,6}, {-1,9}, {-5,9}, {-6,9} calculating by hand
		 * R7: {-8,1,5,3}, {-1,8}, {-5,8}, {-3,8} calculating by hand
		 * 
		 */

		
		//initialize Q2 KB
		List<Clause> KB2 = new ArrayList<Clause>();
		
		//R1
		int[] c1 = {-1};
		//R2
		int[] c2 = {-7,2,4};
		int[] c3 = {-2,7};
		int[] c4 = {-4,7};
		//R3
		int[] c5 = {-9,1,5,6};
		int[] c6 = {-1,9};
		int[] c7 = {-5,9};
		int[] c8 = {-6,9};
		//R7
		int[] c9 = {-8,1,5,3};
		int[] c10 = {-1,8};
		int[] c11 = {-5,8};
		int[] c12 = {-3,8};
		
		KB2.add(new Clause(c1));
		KB2.add(new Clause(c2));
		KB2.add(new Clause(c3));
		KB2.add(new Clause(c4));
		KB2.add(new Clause(c5));
		KB2.add(new Clause(c6));
		KB2.add(new Clause(c7));
		KB2.add(new Clause(c8));
		KB2.add(new Clause(c9));
		KB2.add(new Clause(c10));
		KB2.add(new Clause(c11));
		KB2.add(new Clause(c12));
		
		System.out.println("--------------Question 2--------------");
		System.out.print("Knowledge base: ");
		for(Clause clause: KB2) {
			clause.print();
			System.out.print(" ");
		}
		System.out.println();
		System.out.println();
		
		// add R4: {-7}
		System.out.println("R4: {-7}");
		int[] R4 = {-7};
		KB2.add(new Clause(R4));
		
		
		List<Clause> Q2a1 = new ArrayList<Clause>();
		int[] ca2 = {-2};
		Q2a1.add(new Clause(ca2));
		System.out.print("-P1,2: ");
		for(Clause clause: Q2a1) {
			clause.print();
			System.out.print(" ");
		}
		System.out.println();
		System.out.println(TT_entails(KB2, Q2a1));
		
		List<Clause> Q2a2 = new ArrayList<Clause>();
		int[] ca3 = {-4};
		Q2a2.add(new Clause(ca3));
		System.out.print("-P2,1: ");
		for(Clause clause: Q2a2) {
			clause.print();
			System.out.print(" ");
		}
		System.out.println();
		System.out.println(TT_entails(KB2, Q2a2));
		
		List<Clause> Q2a3 = new ArrayList<Clause>();
		int[] ca4 = {5};
		Q2a3.add(new Clause(ca4));
		System.out.print("P2,2: ");
		for(Clause clause: Q2a3) {
			clause.print();
			System.out.print(" ");
		}
		System.out.println();
		System.out.println(TT_entails(KB2, Q2a3));
		
		List<Clause> Q2a4 = new ArrayList<Clause>();
		int[] ca5 = {-5};
		Q2a4.add(new Clause(ca5));
		System.out.print("-P2,2: ");
		for(Clause clause: Q2a4) {
			clause.print();
			System.out.print(" ");
		}
		System.out.println();
		System.out.println(TT_entails(KB2, Q2a4));
		System.out.println();
		
		
		// add R5: {9}
		System.out.println("R5: {9}");
		int[] R5 = {9};
		KB2.add(new Clause(R5));
		
		List<Clause> Q2a5 = new ArrayList<Clause>();
		int[] ca6 = {5,6};
		Q2a5.add(new Clause(ca6));
		System.out.print("P2,2 OR P3,1: ");
		for(Clause clause: Q2a5) {
			clause.print();
			System.out.print(" ");
		}
		System.out.println();
		System.out.println(TT_entails(KB2, Q2a5));
			
		List<Clause> Q2a6 = new ArrayList<Clause>();
		int[] ca7 = {5};
		Q2a6.add(new Clause(ca7));
		System.out.print("P2,2: ");
		for(Clause clause: Q2a6) {
			clause.print();
			System.out.print(" ");
		}
		System.out.println();
		System.out.println(TT_entails(KB2, Q2a6));
		
		List<Clause> Q2a7 = new ArrayList<Clause>();
		int[] ca8 = {-5};
		Q2a7.add(new Clause(ca8));
		System.out.print("-P2,2: ");
		for(Clause clause: Q2a7) {
			clause.print();
			System.out.print(" ");
		}
		System.out.println();
		System.out.println(TT_entails(KB2, Q2a7));
		
		List<Clause> Q2a8 = new ArrayList<Clause>();
		int[] ca9 = {6};
		Q2a8.add(new Clause(ca9));
		System.out.print("P3,1: ");
		for(Clause clause: Q2a8) {
			clause.print();
			System.out.print(" ");
		}
		System.out.println();
		System.out.println(TT_entails(KB2, Q2a8));
		
		List<Clause> Q2a9 = new ArrayList<Clause>();
		int[] ca10 = {-6};
		Q2a9.add(new Clause(ca10));
		System.out.print("-P3,1: ");
		for(Clause clause: Q2a9) {
			clause.print();
			System.out.print(" ");
		}
		System.out.println();
		System.out.println(TT_entails(KB2, Q2a9));
		System.out.println();
		
		// add R6: {-8}
		System.out.println("R6: {-8}");
		int[] R6 = {-8};
		KB2.add(new Clause(R6));
		
		List<Clause> Q2a10 = new ArrayList<Clause>();
		int[] ca11 = {-5};
		Q2a10.add(new Clause(ca11));
		System.out.print("-P2,2: ");
		for(Clause clause: Q2a10) {
			clause.print();
			System.out.print(" ");
		}
		System.out.println();
		System.out.println(TT_entails(KB2, Q2a10));

		List<Clause> Q2a11 = new ArrayList<Clause>();
		int[] ca12 = {6};
		Q2a11.add(new Clause(ca12));
		System.out.print("P3,1: ");
		for(Clause clause: Q2a11) {
			clause.print();
			System.out.print(" ");
		}
		System.out.println();
		System.out.println(TT_entails(KB2, Q2a11));
		
		
		/*
		 * Q3
		 * let Mythical=1, Immortal=2, Mammal=3, Horned=4, Magical=5
		 * KB includes:
		 * {-1,2}
		 * {1,-2}, {1,3}
		 * {-2,4}, {-3,4}
		 * {-4,5}
		 *  
		 */
		
		//initialize Q3 KB
		List<Clause> KB3 = new ArrayList<Clause>();
		int[] c13 = {-1,2};
		int[] c14 = {1,-2};
		int[] c15 = {1,3};
		int[] c16 = {-2,4};
		int[] c17 = {-3,4};
		int[] c18 = {-4,5};
		
		KB3.add(new Clause(c13));
		KB3.add(new Clause(c14));
		KB3.add(new Clause(c15));
		KB3.add(new Clause(c16));
		KB3.add(new Clause(c17));
		KB3.add(new Clause(c18));

		System.out.println("--------------Question 3--------------");
		System.out.print("Knowledge base: ");
		for(Clause clause: KB3) {
			clause.print();
			System.out.print(" ");
		}
		System.out.println();
		System.out.println();
		
		List<Clause> Q3a1 = new ArrayList<Clause>();
		int[] ca13 = {1};
		Q3a1.add(new Clause(ca13));
		System.out.print("Mythical: ");
		for(Clause clause: Q3a1) {
			clause.print();
			System.out.print(" ");
		}
		System.out.println();
		System.out.println(TT_entails(KB3, Q3a1));
		
		List<Clause> Q3a2 = new ArrayList<Clause>();
		int[] ca14 = {5};
		Q3a2.add(new Clause(ca14));
		System.out.print("Magical: ");
		for(Clause clause: Q3a2) {
			clause.print();
			System.out.print(" ");
		}
		System.out.println();
		System.out.println(TT_entails(KB3, Q3a2));
		
		List<Clause> Q3a3 = new ArrayList<Clause>();
		int[] ca15 = {4};
		Q3a3.add(new Clause(ca15));
		System.out.print("Magical: ");
		for(Clause clause: Q3a3) {
			clause.print();
			System.out.print(" ");
		}
		System.out.println();
		System.out.println(TT_entails(KB3, Q3a3));
		
		
	}
	

	
	
	
	public static boolean TT_entails (List<Clause> KB, List<Clause> alpha) {
		
		List<Integer> symbols = new ArrayList<Integer>();
		
		for (int i=0; i<KB.size(); i++) {
			for (int j=0; j<KB.get(i).size(); j++) {
				if (!symbols.contains(KB.get(i).get(j))) {
					
					symbols.add(KB.get(i).get(j));
					
				}
			}
		}
		for (int i=0; i<alpha.size(); i++) {
			for (int j=0; j<alpha.get(i).size(); j++) {
				if (!symbols.contains(alpha.get(i).get(j))) {
					
					symbols.add(alpha.get(i).get(j));
					
				}
			}
		}
		
		Map<Integer, Boolean> model = new HashMap<Integer, Boolean>();
		return TT_checkAll(KB, alpha, symbols, model);
	}
	
	
	public static boolean TT_checkAll (List<Clause> KB, List<Clause> alpha, List<Integer> symbols, Map<Integer, Boolean> model) {
		
		if (symbols.isEmpty()) {
			
			if (PL_true(KB, model)) {
				
				/*
				System.out.println("Model: "+model.toString());
				System.out.println("!!!PL_true = true...");
				System.out.println("PL == true, model find!!!!!!!!! with alpha:"+PL_true(alpha, model));
				System.out.println();
				*/
				return PL_true(alpha, model);
			} else {
				//System.out.println("Model: "+model.toString());
				//System.out.println("PL_true = false...");
				return true;
			}
			
		} else {
			//p
			int p = symbols.get(0);
			//rest
			List<Integer> rest = new ArrayList<Integer>();
			for (int i=1; i<symbols.size(); i++) {
				rest.add(symbols.get(i));
			}
			
			return (TT_checkAll(KB, alpha, rest, union(model, p, Boolean.TRUE)) && TT_checkAll(KB, alpha, rest, union(model, p, Boolean.FALSE)));
		}
	}
	
	//checked
	public static Map<Integer, Boolean> union (Map<Integer, Boolean> model, int p, Boolean type) {
		
		model.put(p, type);
		return model;
		
	}
	
	public static boolean EvaluatingModel(List<Clause> KB, Map<Integer, Boolean> model) {
		for (Clause c:KB) {
			for (int i=0;i<c.size();i++) {
				
				// c.get(i) = integers in the clause
				// model.get(c.get(i)) = ith integer's boolean value
				// map.keySet().contains(-9) testing whether the model includes -9 or not
				
				if(model.keySet().contains(-c.get(i))) { //situation which c.get(i) has a inverse
					
					if(model.get(c.get(i)) == model.get(-c.get(i))) {
						//System.out.println("Impossible situation!!!");
						//System.out.println();
						return false;
						
					}else {
						
						continue;
						
					}
				
				}
			
			}
			
		}
		//System.out.println("Model "+model.toString()+" is vaild!!!");
		return true;
				
	}
	
	
	public static boolean PL_true(List<Clause> KB, Map<Integer, Boolean> model) {
		  
		 List<Boolean> KBResult = new ArrayList<Boolean>();
		 
		 //System.out.println("Testing model...");
		 
		 if(EvaluatingModel(KB, model) == false) {
			 return false;
		 }
		
		// System.out.println("$ valid model");
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
	
	
	
	
	
	
}

