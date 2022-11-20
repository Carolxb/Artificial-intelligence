package Project2;



import java.util.*;


public class Clause {
	
	public int size;
	public List<Integer> clause;
	
	public Clause() {
		
		this.size = 0;
		clause = new ArrayList<Integer>();
		
	}
	
	public Clause(int[] value) {
		
		this.size = 0;//change
		this.clause = new ArrayList<Integer>();
		for(int i=0;i<value.length;i++) {
			
			this.clause.add(value[i]);
			this.size++;//change
			
			
		}
		
	}
	
	//checked
	public void add(int a) {
		this.clause.add(a);
		this.size++;
	}
	
	//checked
	public int get(int index) {
		
		return this.clause.get(index);

	}
	
	//checked
	public void print() {
		System.out.print("{");
		for(int i=0;i<clause.size();i++) {
			
			System.out.print(clause.get(i));
			
			if(i<clause.size()-1) {
				System.out.print(",");
			}
		}
		System.out.print("}");
		
	}
	
	//checked
	public int size() {
		
		return this.size;
	}
	

}
