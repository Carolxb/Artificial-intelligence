package bn.base;

/**
 * A StringValue is a String that can be used as a Value.
 */
public class StringValue extends Value<String> {

	/**
	 * Construct and return a new StringValue using the given String.
	 */
	public StringValue(String s) {
		super(s);
	}
	
	/**
	 * Return the String value of this StringValue, if you follow me.
	 */
	public String stringValue() {
		return this.value;
	}

	/**
	 * Test StringValues.
	 */
	public static void main(String[] argv) {
		
		StringValue Red = new StringValue("Red");
		StringValue False = new StringValue("False");
		
		StringValue r = new StringValue("red");
		
		System.out.println("1: "+Red.getStringValue());
		System.out.println("2: "+False.stringValue());
		System.out.println("3: "+r.stringValue());
		
		/*case sensitive 
		 * Red's string value = "Red"
		 *   R's  string value = "red"
		 *   
		 *   Not equal
		 */
		System.out.println("Testing .equals(): "+Red.equals(r));
		
		
		System.out.println(Red.hashCode());
		System.out.println(False.hashCode());
		System.out.println(r.hashCode());
		
		System.out.println(Red);
	}
	
}
