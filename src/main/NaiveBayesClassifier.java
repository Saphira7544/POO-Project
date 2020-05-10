package main;

import java.util.Set;

public class NaiveBayesClassifier {
	
	/**
	 * 
	 * @param tree
	 * @param trainData
	 */
	public static void calcPB(Tree tree, int []TestInst, TrainSet TrainData) {
		
		// Initialise nodes' counts
		Set<Node> keys = tree.getDAG().keySet();
		Node classNode =  tree.getClassNode(); 
		double highestProb = 0;
		
		for (int c = 0; c < TrainData.getClassRange(); c++) {
			
			double P_B = 1;
			
			for (Node key : keys) {
				
				P_B *= classNode.theta_c[c]; 
				
				// Running through the edges of a certain node
				for(int i = 0; i < tree.getDAG().get(key).size(); i++) {
					
					// FAZER CASO EM QUE NÃO TEM PAI AKA ROOT!!!!!!!!!!!!!!
					
					
					Node child = tree.getDAG().get(key).get(i).getChild();
					// gets from the test file the yi equivalent to the parent's index
					int j = TestInst[child.getIndex()];
					// gets from the test file the yi equivalent to the son's index
					int k = TestInst[key.getIndex()];
					
					P_B *= child.theta[j][k][c];
				}
			}
			
			if (P_B > highestProb) {
				highestProb = P_B;
			}
		}
		
		
	}
		
		
	
		
		
		
	
}
