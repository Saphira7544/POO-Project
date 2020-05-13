package bayes;

import files.*;
import structure.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class NaiveBayesClassifier {
	
	int[] classification;
	private final TrainSet TrainData;
	private final TestSet TestData;
	private final Tree tree;
	List<Instance> Instances;
	
	
	public NaiveBayesClassifier(TrainSet TrainData, TestSet TestData, Tree tree) {
		this.TrainData = TrainData;
		this.TestData = TestData;
		this.tree = tree;
		
		Instances = TestData.getInstances();
	}
	
	/**
	 * 
	 * @param tree
	 * @param TestInst
	 */
	public void calcPB(Tree tree, Instance Instances, int idx) {
		
		// Initialise nodes' counts
		//Set<Node> keys = tree.getDAG().keySet();
		Node classNode =  tree.getClassNode(); 
		double highestProb = 0;
		Node root = tree.getRoot();
		//classification[idx] = -1;
		
		int[] TestInst = Instances.getArray();
		
		System.out.println("\n\n INSTANCE " + idx);
		
		for (int c = 0; c < TestData.getClassRange()+1; c++) {
			boolean flag = false;
			double P_B = 1;
			
			P_B *= classNode.theta_c[c];
			System.out.println(P_B);
			Set<Node> keys = tree.getDAG().keySet();
			for (Node key : keys) {
				
				if(key.getIndex() == -1) {
					break;
				}

				// Running through the edges of a certain node
				for(int i = 0; i < tree.getDAG().get(key).size(); i++) {
					
					int j;
					int k;
										
					Node child = tree.getDAG().get(key).get(i).getChild();
					
					if(key.equals(root) && !flag) {

						k = TestInst[key.getIndex()];
						P_B *= key.theta[key.getIndex()][key.getIndex()][k][c];	
						System.out.println(P_B);
						System.out.println("pai:"+ (key.getIndex()+1) + "	Theta["+(key.getIndex()+1)+"]["+(key.getIndex()+1)+"]["+(k+1)+"]["+(c+1)+"] = "+ key.theta[key.getIndex()][key.getIndex()][k][c]);
						flag = true;
					
					}					

					j = TestInst[key.getIndex()];
					
					// gets from the test file the yi equivalent to the son's index
					k = TestInst[child.getIndex()];
					
					P_B *= key.theta[child.getIndex()][j][k][c];
						
					//System.out.println("pai:"+ (key.getIndex()+1) + "	Theta["+(child.getIndex()+1)+"]["+(j+1)+"]["+(k+1)+"]["+(c+1)+"] = "+ key.theta[child.getIndex()][j][k][c]);
					//System.out.println(P_B);
					//}
					
					//System.out.println("PB( Xi=" + (child.getIndex()+1) + "|pai=" + (key.getIndex()+1) + ",C=" + c + ") = " + P_B);
				}	
				
			}
			
			//System.out.println("Instance " + idx + "	c " + c + "	  PB " + P_B);
			if (P_B > highestProb) {
				highestProb = P_B;
				classification[idx] = c;
				//System.out.println(" IF	Class[idx] " + classification[idx] + "	curr c " +  c);
				flag = true;
			}
		}
	}
	
	public void instanceCalcPB() {
		
		classification = TestData.getClassification();
		
		for(int i = 0; i < TestData.get_N(); i++) {
			calcPB(tree, Instances.get(i), i );
		}		
	}
		
	/**
	 * 	
	 * @return
	 */
	public double getAccuracy(){
		
		double trueClass = 0;
		
		for(int i = 0; i < TestData.get_N(); i++) {
			Instance inst = Instances.get(i);
			if(inst.getClassVariable() == classification[i]) trueClass++;	
			System.out.println("Indstance: " + (i+1) + "	predict:" + classification[i] + "	actual" + inst.getClassVariable());

		}		
		return trueClass/TestData.get_N() * 100.0;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getSensitivity(){
		
		double truePositive = 0;
		double falseNegative = 0;
		
		//considering positive the 0 
		for(int i = 0; i < TestData.get_N(); i++) {
			Instance inst = Instances.get(i);
			if(inst.getClassVariable() == classification[i] && classification[i] == 0) truePositive++;		
			if(inst.getClassVariable() != classification[i] && classification[i] == 1) falseNegative++;			
		}
		
		return truePositive/(falseNegative + truePositive);
	}
	/**
	 * 
	 * @return
	 */
	public double getSpecificity(){

		double trueNegative = 0;
		double falsePositive = 0;
		
		//considering positive the 0 
		for(int i = 0; i < TestData.get_N(); i++) {
			Instance inst = Instances.get(i);
			if(inst.getClassVariable() == classification[i] && classification[i] == 1) trueNegative++;		
			if(inst.getClassVariable() != classification[i] && classification[i] == 0) falsePositive++;			
		}
		
		return trueNegative/(trueNegative + falsePositive);
	}
	/**
	 * 
	 * @return
	 */
	public double getF1score(){
		double precision = getPrecision();
		double sensitivity = getSensitivity();
		
		return 2*(precision*sensitivity)/(precision+sensitivity);
	}
	
	/**
	 * 
	 * @return
	 */
	private double getPrecision(){
		
		double truePositive = 0;
		double falsePositive = 0;
		
		//considering positive the 0 
		for(int i = 0; i < TestData.get_N(); i++) {
			Instance inst = Instances.get(i);
			if(inst.getClassVariable() == classification[i] && classification[i] == 0) truePositive++;		
			if(inst.getClassVariable() != classification[i] && classification[i] == 0) falsePositive++;			
		}
		
		return truePositive/(falsePositive + truePositive);
	}

	@Override
	public String toString() {
		return "NaiveBayesClassifier [classification=" + Arrays.toString(classification) + ", Instances=" + Instances
				+ "]";
	}
	
}
