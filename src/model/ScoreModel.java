package model;

import structure.*;

/**
 * Abstract class, it's incomplete and general and therefore needs to be extended by actual score models
 * @author 
 *
 */
public interface ScoreModel {
	
	/**
	 *  Abstract method to be completed in the Models extended by it
	 * @param edge : Edge whose weight is going to be calculated
	 * @param graph : graph containing the elements to calculate the edge
	 * @return	returns the weight of the edge
	 */
	public abstract double calc_weight(Edge edge, Node node, int N, int s);
	
	public static final double ln2 = Math.log(2);
}
