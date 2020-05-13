package bayes;

import files.*;
import structure.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class NaiveBayesClassifier {
	
	int[] classification;
	double[][][] confMatrix;
	private final TestSet TestData;
	private final Tree tree;
	List<Instance> Instances;
	
	
	public NaiveBayesClassifier(TestSet TestData, Tree tree) {
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
				
		for (int c = 0; c < TestData.getClassRange()+1; c++) {
			boolean flag = false;
			double P_B = 1;
			
			P_B *= classNode.theta_c[c];
			
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
						flag = true;					
					}					

					j = TestInst[key.getIndex()];					
					// gets from the test file the yi equivalent to the son's index
					k = TestInst[child.getIndex()];
					
					P_B *= key.theta[child.getIndex()][j][k][c];
						
				}					
			}
			
			if (P_B > highestProb) {
				highestProb = P_B;
				classification[idx] = c;
			}
		}
	}
	
	public void instanceCalcPB() {
		
		classification = TestData.getClassification();
		
		for(int i = 0; i < TestData.get_N(); i++) {
			calcPB(tree, Instances.get(i), i );
		}		
		
		getMetrics();
	}
	
	private void getMetrics() {
		int range = TestData.getClassRange()+1;
		// stores for each possible value of C the a 2*2 confusion matrix with TP, TN, FP, FN
		confMatrix = new double[range][2][2];
		int i = 0;
		
		for(Instance inst: Instances) {
			int output = inst.getClassVariable();
			int actual =  classification[i];
			
			//true positive
			if(output == actual) {
				confMatrix[actual][0][0]++;
				for(int j = 0; j < range; j++) {
					//true negative
					if(j != actual) confMatrix[j][0][1]++;
				}
			}
			if(output != actual) {
				//false positive
				confMatrix[actual][1][0]++;
				//false negative
				confMatrix[output][1][1]++;
			}

			i++;
		}		
	}
		
	/**
	 * 	
	 * @return
	 */
	public String getAccuracy(){
		int range = TestData.getClassRange()+1;
		
		double accuracy = 0;
		double total = 0;
		String result = new String("accuracy : ");
		
		for(int i = 0; i < range; i++) {
			accuracy = confMatrix[i][0][0]/TestData.get_N();
			total += accuracy;
		}		
		result +=  total;
		
		return result;
	}
	/**
	 * 
	 * @return
	 */
	public String getSpecificity(){
		
		double[] countClass = TestData.getCountClass();
		int range = TestData.getClassRange()+1;
		
		String result = new String("specificity : [ ");
		double sensitivity = 0;
		double average = 0;
		
		//considering positive the 0 
		for(int i = 0; i < range; i++) {
			sensitivity = confMatrix[i][0][1]/(confMatrix[i][0][1] + confMatrix[i][1][0]);
			result += i + ": " + sensitivity + ",  ";	
			average += sensitivity * countClass[i];
		}
		
		result += average/TestData.get_N() + "]";
		
		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSensitivity(){
		double[] countClass = TestData.getCountClass();
		int range = TestData.getClassRange()+1;
		
		String result = new String("sensitivity : [ ");
		double sensitivity = 0;
		double average = 0;
		
		//considering positive the 0 
		for(int i = 0; i < range; i++) {
			sensitivity = confMatrix[i][0][0]/(confMatrix[i][0][0] + confMatrix[i][1][1]);
			result += i + ": " + sensitivity + ",  ";	
			average += sensitivity * countClass[i];
		}
		
		result += average/TestData.get_N() + "]";
		
		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getF1score(){
		double[] countClass = TestData.getCountClass();
		int range = TestData.getClassRange()+1;
		
		String result = new String("f1score : [ ");
		double sensitivity = 0;
		double precision = 0;
		double f1score = 0;
		double average = 0;
		
		//considering positive the 0 
		for(int i = 0; i < range; i++) {
			sensitivity = confMatrix[i][0][0]/(confMatrix[i][0][0] + confMatrix[i][1][1]);
			precision = confMatrix[i][0][0]/(confMatrix[i][0][0] + confMatrix[i][1][0]);
			f1score = 2*(precision*sensitivity)/(precision+sensitivity);
			
			average += f1score * countClass[i];
			
			result += i + ": " + f1score + ",  ";	
		}
		
		result += average/TestData.get_N() + "]";
		
		return result;
	}
	
	@Override
	public String toString() {
		return "NaiveBayesClassifier [classification=" + Arrays.toString(classification) + ", Instances=" + Instances
				+ "]";
	}
	
}
