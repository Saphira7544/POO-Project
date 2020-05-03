package main;

public class Instance {
	
	int [] values; 		// Line of values from 0 to ri-1
	int class_variable;	// Class variable
	int range_Cvar = 0;		// Max value of class variable
	/**
	 * Receives a String[] containing the elements of a line from the file and converts
	 * each element to an integer so it can be used 
	 * 
	 * @param elements : Line from the file divided by commas
	 */
	public Instance(String line) {
		
		String[] elements = line.split("\\s*,\\s*"); 
		int size = elements.length;

	    values = new int [ size - 1 ];
	    
	    for(int i = 0; i < size -1; i++) {
	    	
	         values[i] = Integer.parseInt(elements[i]);   
	    }
	    
	    class_variable = Integer.parseInt(elements[size-1]); //last position
	    if(class_variable > range_Cvar) {
	    	range_Cvar = class_variable;
	    }
	}
	
	/**
	 * Gets the value of the Class Variable for this line
	 * 
	 * @return class variable from line k
	 */
	public  int getClassVariable() {
		
		return class_variable;
	}
	
	/**
	 * Gets the value x_ik for this line
	 * @param i : feature from the line
	 * @return	returns the value of the corresponding feature from this line
	 */
	public int getValue(int i) {
		return values[i];
	}
	
	/**
	 * Gets full line from the input file
	 * @return array with values from a line in the input file
	 */
	public int[] getArray(){
		return values;
	}
	
	public int getClassRange() {
		return range_Cvar;
	}
}
