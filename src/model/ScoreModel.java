package model;

import structure.*;

/**
 * Abstract class, it's incomplete and general and therefore needs to be extended by actual score models
 * @author 
 *
 */
public abstract class ScoreModel {
	
	/**
	 *  Abstract method to be completed in the Models extended by it
	 * @param edge : Edge whose weight is going to be calculated
	 * @param graph : graph containing the elements to calculate the edge
	 * @return	returns the weight of the edge
	 */
	public abstract double calc_weight(Edge edge, Node node, int N, int s);
	
	protected static final double ln2 = Math.log(2);
	
	/**
	 * Function that calculates the logarithm of base 2
	 * @param number : log_2(number), number whose logarithm is to be calculated
	 * @return : returns the logarithm of the said number
	 */
	protected final double log2(double number) {
		double log2Value = Math.log(number) / ScoreModel.ln2;
		if (Double.isNaN(log2Value))
			throw new RuntimeException("Error calculating log2(" + number + "): NaN");
		return log2Value;
	}
}
