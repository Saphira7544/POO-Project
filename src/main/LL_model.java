package main;

/**
 * Calculate weight of the edge using the Log-Likelihood (LL) Method
 */
public class LL_model extends ScoreModel{
	
	@Override
	public double calc_weight(Edge edge, Graph graph) {
		double weight = 0;
		int qi = 0;	// SUBSTITUTE	//
		int ri = 0;	// VALUES		//
		int s = 0;	// HERE			//
		
		for(int j = 0; j < qi; j++) { /////////////// INSERIR DIREÇÕES CORRETAS DE qi ri E s////////////////////////
			for(int k = 0; k < ri; k++) {
				for(int c = 0; c < s; c++) {
					
					int Nijkc = 0; 	// PUT		//
					int N = 0;		// REAL		//
					int Nc = 0;		// VALUES	//
					int NikcJ = 0;	// HERE		//
					int NijcK = 0;
										
					weight += (Nijkc/N)*ScoreModel.log2((Nijkc*Nc)/(NikcJ*NijcK));
					
				}
			}
		}
				
		return weight;	
	}

}
