package model;

import structure.*;

public class MDL_model extends LL_model{

	@Override
	public double calc_weight(Edge edge, Node node, int N, int s) {
		
		double LL_weight = super.calc_weight(edge, node, N, s);
		
		double weight = 0;
		int qi = node.getRange();	
		int ri = edge.getChild().getRange();	
		
		weight = LL_weight - (s*(ri-1)*(qi-1)) / 2 * Math.log(N); // log refers to base e, same as ln
		
		return weight;
	}
}
