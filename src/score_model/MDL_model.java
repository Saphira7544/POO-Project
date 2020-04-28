package score_model;

public class MDL_model extends LL_model{

	@Override
	public double calc_weight(Edge edge, Graph graph) {
		
		double LL_weight = super.calc_weight(edge, graph);
		
		double weight = 0;
		int qi = 0;	// SUBSTITUTE	//
		int ri = 0;	// VALUES		//
		int s = 0;	// HERE			//
		int N = 0;	// 				//
		
		weight = LL_weight - (s*(ri-1)*(qi-1)) / 2 * Math.log(N);
		
		return weight;
	}
}
