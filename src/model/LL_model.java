package model;

import structure.*;

/**
 * Calculate weight of the edge using the Log-Likelihood (LL) Method
 */
public class LL_model extends ScoreModel{
	
	@Override
	public double calc_weight(Edge edge, Node node, int N, int s) {
		double weight = 0;
		int qi = node.getRange();	// Parent range
		int ri = edge.getChild().getRange();	// Child range
		
		for( int j = 0; j < qi+1; j++ ) { // Parent range
			
			for( int k = 0; k < ri+1; k++ ) { // Child range

				for( int c = 0; c <= s; c++ ) { // Class range

					double Nijkc = node.Nijkc[edge.getChild().getIndex()][j][k][c]; 	
					double Nc = node.Nc[c];		
					double NikcJ = edge.getChild().Nijc[k][c];	
					double NijcK = node.Nijc[j][c];
					
					if (Nijkc != 0 && Nc != 0) {	
						weight += (double) Nijkc/N *log2((double)(Nijkc*Nc)/(double)(NikcJ*NijcK));
					}
				}
			}
		}
		return weight;	
	}
		
}
