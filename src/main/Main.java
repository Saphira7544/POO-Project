package main;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	
	public static Graph graph = new Graph(); 
	
	
	public static void main(String[] args) {
		
		
		// Exits if arguments are not correctly input
		if(args.length != 3) { 
			System.err.println("Necessary arguments: TrainFile TestFile Score(LL or MDL)");
			System.exit(-1);
		}
		
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
		graph.createAllEdges();

		graph.setAllWeights(scoreModel);	

		graph.createCompleteGraph();		

		Tree tree = new Tree(graph.getDAG(), graph.getClassNode());

		tree.applyPrim();
		tree.createTAN(TrainData.getClassRange());
		tree.calcThetaC(TrainData.get_N());
		
		System.out.println(tree);		
		
		tree.calcThetas(); //dá null pointer exception

		

	}	
}
