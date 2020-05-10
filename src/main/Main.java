package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;

public class Main {
	
	public static Graph graph = new Graph(); 
	
	
	public static void main(String[] args) {
		
		
		// Exits if arguments are not correctly input
		if(args.length != 3) { 
			System.err.println("Necessary arguments: TrainFile TestFile Score(LL or MDL)");
			System.exit(-1);
		}
		
		// Start counting the time it takes to build the TANBC
		long starttime1 = System.currentTimeMillis();
		
		File TrainFile = new File(args[0]);		
		TrainSet TrainData = null;
		try {	
		//Graph g = new Graph();
			TrainData = new TrainSet(TrainFile);
			// update in the main the graph and nodes created with the information from the Train File
			graph = TrainData.getGraph(); 
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ScoreModel scoreModel = null;
		if ("LL".equals(args[2])) {
			scoreModel = new LL_model();
		} else if ("MDL".equals(args[2])) {
			scoreModel = new MDL_model();
		} else {
			System.err.println("Third argument must be LL or MDL to pick Score Model.");
			System.exit(-1);
		}
		
		
		graph.setTrainData( TrainData );

		graph.updateNodeCounts();
		graph.createHalfEdges();

		graph.setAllWeights(scoreModel);	

		graph.createCompleteGraph();	

		Tree tree = new Tree(graph.getDAG(), graph.getClassNode());

		tree.applyPrim();
		
		System.out.println(tree);		
		
		long endtime1 = System.currentTimeMillis();
		
		// RESULTS //
		Set<Node> keys = tree.getDAG().keySet();
		System.out.println("Classifier: \n	Parent : Child");
		
		for(Node key:keys) {
			for(int i = 0; i < tree.getDAG().get(key).size(); i++) {
				Node child = tree.getDAG().get(key).get(i).getChild();
				System.out.println( "	" + key + " : " + child.getKey());
			}
		}
		
		System.out.println("Time to build:\n	" + (endtime1 - starttime1) / 1000.0 + " seconds");
		
		tree.createTAN(TrainData.getClassRange());
		tree.calcThetas();
		tree.calcThetaC(TrainData.get_N());
		
	}	
}
