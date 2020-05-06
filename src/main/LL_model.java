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
					
					int Nijkc = node.Nijkc[node.getIndex()][j][k][c]; 	
					int Nc = node.Nc[c];		
					int NikcJ = edge.getChild().Nijc[k][c];	
					int NijcK = node.Nijc[j][c];
					System.out.println("Nc - " + Nc + "  Nijkc - " + Nijkc + "  NikcJ - " +NikcJ+ "  NijcK - " +NijcK);	
					if (Nijkc != 0 && Nc != 0) {
	
						weight += (double) Nijkc/N *log2((double)(Nijkc*Nc)/(double)(NikcJ*NijcK));
						//System.out.println("wtf "+ (Nijkc/N) + " wtf "+ Nijkc + " wtf "+ N + " " + (double) 23/100);
						//System.out.println( (float) (Nijkc/N) + " "+ log2(88) + " "+ (Nijkc*Nc) + " "+ (NikcJ*NijcK));
					}
				}
			}
		}
		System.out.println(weight);		
		return weight;	
	}
}
