package main;

/**
 * Calculate weight of the edge using the Log-Likelihood (LL) Method
 */
public class LL_model extends ScoreModel{
	
	@Override
	public double calc_weight(Edge edge, Node node, int N, int s) {
		double weight = 0;
		int qi = node.getRange();	
		int ri = edge.getChild().getRange();	
			
		for( int j = 0; j < qi; j++ ) { 
			
			for( int k = 0; k < ri; k++ ) {

				for( int c = 0; c < s; c++ ) {
					
					int Nijkc = node.getNijkc()[node.getIndex()][j][k][c]; 	
					int Nc = node.getNc()[c];		
					int NikcJ = edge.getChild().getNijc()[k][c];	
					int NijcK = node.getNijc()[j][c];
					System.out.println("Nc - " + Nc + "  Nijkc - " + Nijkc);	
					if (Nijkc != 0 && Nc != 0) {
						weight += (Nijkc/N)*ScoreModel.log2((Nijkc*Nc)/(NikcJ*NijcK));
				
					}
				}
			}
		}
		System.out.println(weight);		
		return weight;	
	}
}
