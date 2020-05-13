package files;

import structure.*;
import java.io.File;
import java.io.FileNotFoundException;

public class TrainSet extends FileSet{
	
	private Graph graph = new Graph();
	
	public Graph getGraph() {
		return graph;
	}

	private Node newNode;
	int[] ranges;
	
	public TrainSet(File file) throws FileNotFoundException {
		
		super(file);
		
		// Check the ranges for each Xi
		ranges = new int [nFeatures];
		// Checks the max value of each node
		for(int i = 0; i < Instances.size()  ; i++) {			
			for(int j = 0; j < nFeatures; j++) {
				if(Instances.get(i).getValue(j) > ranges[j]) {
					ranges[j] = Instances.get(i).getValue(j);
				}
			}
		}

		// Creates a new node for each feature and adds it to the graph
		for(int a = 0; a < nFeatures ; a++) {
			newNode = new Node(features[a], ranges[a], a);
			graph.addNode(newNode);
		}	
	}
	
	
	
	/**
	 * Gets max range of a Feature
	 * @param i : Index of Feature
	 * @return Maximum value available in the file for that feature
	 */
	public int getRange(int i) {
		return ranges[i];
	}
	
	/**
	 * Gets max ranges of every Feature
	 * @return Array with ranges of every feature
	 */
	public int[] getRanges() {
		return ranges;
	}
	
	/**
	 * Gets every feature's name
	 * @return String array containing features heading
	 */
	public String[] getFeatures() {
		return features;
	}
}
