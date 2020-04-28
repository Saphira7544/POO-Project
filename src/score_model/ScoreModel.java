package score_model;

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
	public abstract double calc_weight(Edge edge, Graph graph);

	public static int log2(int x)
	{
	    return (int) (Math.log(x) / Math.log(2));
	}
}
