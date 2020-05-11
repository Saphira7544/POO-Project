package bayes;

import files.*;
import structure.*;

import java.util.List;
import java.util.Set;

public class NaiveBayesClassifier {
	
	private int[] classification;
	private final TrainSet TrainData;
	private final TestSet TestData;
	
	public NaiveBayesClassifier(TrainSet TrainData, TestSet TestData) {
		this.TrainData = TrainData;
		this.TestData = TestData;
	}
	
	/**
	 * 
	 * @param tree
	 * @param TestInst
	 */
	public void calcPB(Tree tree, int []TestInst) {
		
		// Initialise nodes' counts
		Set<Node> keys = tree.getDAG().keySet();
		Node classNode =  tree.getClassNode(); 
		classification = new int[TrainData.get_N()];
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
					
					P_B *= key.theta[child.getIndex()][j][k][c];
				}
			}
			
			if (P_B > highestProb) {
				highestProb = P_B;
			}
		}
	}
		
		
	public double getAccuracy(){
		List<Instance> list = TestData.getInstances();
		double trueClass = 0;
		
		for(int i = 0; i < TrainData.get_N(); i++) {
			Instance inst = list.get(i);
			if(inst.getValue(TrainData.get_n()+1) == classification[i]) trueClass++;			
		}
		
		return trueClass/TrainData.get_N();
	}
		
	public double getSensitivity(){
	
		List<Instance> list = TestData.getInstances();
		double truePositive = 0;
		double falseNegative = 0;
		int cIdx = TrainData.get_n()+1;
		
		//considering positive the 0 
		for(int i = 0; i < TrainData.get_N(); i++) {
			Instance inst = list.get(i);
			if(inst.getValue(cIdx) == classification[i] && classification[i] == 0) truePositive++;		
			if(inst.getValue(cIdx) != classification[i] && classification[i] == 1) falseNegative++;			
		}
		
		return truePositive/(falseNegative + truePositive);
	}
	
	public double getSpecificity(){
		List<Instance> list = TestData.getInstances();
		double trueNegative = 0;
		double falsePositive = 0;
		int cIdx = TrainData.get_n()+1;
		
		//considering positive the 0 
		for(int i = 0; i < TrainData.get_N(); i++) {
			Instance inst = list.get(i);
			if(inst.getValue(cIdx) == classification[i] && classification[i] == 1) trueNegative++;		
			if(inst.getValue(cIdx) != classification[i] && classification[i] == 0) falsePositive++;			
		}
		
		return trueNegative/(trueNegative + falsePositive);
	}
	
	public double getF1score(){
		double precision = getPrecision();
		double sensitivity = getSensitivity();
		
		return 2*(precision*sensitivity)/(precision+sensitivity);
	}
	
	private double getPrecision(){
		
		List<Instance> list = TestData.getInstances();
		double truePositive = 0;
		double falsePositive = 0;
		int cIdx = TrainData.get_n()+1;
		
		//considering positive the 0 
		for(int i = 0; i < TrainData.get_N(); i++) {
			Instance inst = list.get(i);
			if(inst.getValue(cIdx) == classification[i] && classification[i] == 0) truePositive++;		
			if(inst.getValue(cIdx) != classification[i] && classification[i] == 0) falsePositive++;			
		}
		
		return truePositive/(falsePositive + truePositive);
	}
	
}
