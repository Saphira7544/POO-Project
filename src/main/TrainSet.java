package main;

import java.io.File;
import java.io.FileNotFoundException;

public class TrainSet extends FileSet{

	Graph graph;
	
	public TrainSet(File file) throws FileNotFoundException {
		super(file);
		
		// Creates a Graph for every feature
		graph = new Graph(getNumbFeatures());

		for (int i = 0; i < nFeatures; i++) {
			
		}

		

		
		// CREATE A NEW GRAPH FOR EACH Xi
	}

}
